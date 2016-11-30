/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallerfinal;

import UsuarioAdmin.UsuarioAdmin;
import UsuarioModelo.UsuarioModelo;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juansantana
 */
public class Formulario extends javax.swing.JFrame {

    /**
     * Creates new form Formulario
     */
    public Formulario() {
        initComponents();
        Mostrar();
    }
    
    UsuarioAdmin admin = new UsuarioAdmin();
    DefaultTableModel modelot;

    private void Mostrar() {
        String columnas[] = {"Idpersona", "Nombre", "Equipo", "Nacimiento", "Ruta"};
        modelot = new DefaultTableModel(null, columnas);
        List<UsuarioModelo> lista = admin.Consultar();
        String ruta;
        for (int i = 0; i < lista.size(); i++) {
            UsuarioModelo modelo = lista.get(i);
            modelot.addRow(columnas);
            modelot.setValueAt(modelo.getIdpersona(),       i, 0);
            modelot.setValueAt(modelo.getNombre(),          i, 1);
            modelot.setValueAt(modelo.getEquipo(),          i, 2);
            modelot.setValueAt(modelo.getNacimiento(),      i, 3);
            modelot.setValueAt(modelo.getRuta(),            i, 4);
            
            
           
            
        }
        tbTabla.setModel(modelot);
    }

    private void Trasladar() {
        UsuarioModelo modelo = new UsuarioModelo();
        modelo.setIdpersona(Integer.parseInt(tbTabla.getValueAt(tbTabla.getSelectedRow(),   0).toString()));
        modelo.setNombre(tbTabla.getValueAt(tbTabla.getSelectedRow(),                       1).toString());
        modelo.setEquipo(tbTabla.getValueAt(tbTabla.getSelectedRow(),                       2).toString());
        modelo.setNacimiento(java.sql.Date.valueOf(tbTabla.getValueAt(tbTabla.getSelectedRow(),                   3).toString()));
        modelo.setRuta(tbTabla.getValueAt(tbTabla.getSelectedRow(),                         4).toString());
        
        txtNombre.setText(modelo.getNombre());
        //txtEquipo.setText(modelo.getEquipo());
        //txtNacimiento.setText(java.sql.Date.valueOf(modelo.getNacimiento());
        txtNacimiento.setText((modelo.getNacimiento().toString()));
        txtRuta.setText(String.valueOf(modelo.getRuta()));
        txtIdPersona.setText(String.valueOf(modelo.getIdpersona()));
        String ruta;
        
        ruta =  modelo.getRuta();
        
        BufferedImage Original = null;
        try {
            Original = ImageIO.read(new File(txtRuta.getText()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        lbImagen.setIcon(new ImageIcon(Original));
        lbImagen.setVisible(true);
    }
    
    private void Guardar() {
        UsuarioModelo modelo = new UsuarioModelo();
        modelo.setNombre(txtNombre.getText());
        modelo.setEquipo(cbEquipo.getSelectedItem().toString());
        modelo.setRuta(txtRuta.getText());
        modelo.setNacimiento(java.sql.Date.valueOf(txtNacimiento.getText()));
        admin.Guardar(modelo);
        Refrescar();
    }
    private void Refrescar() {
        txtNombre.setText("");
        txtRuta.setText("");
    }
    private void Actualizar() {
        UsuarioModelo modelo = new UsuarioModelo();
        modelo.setNombre(txtNombre.getText());
        modelo.setEquipo(cbEquipo.getSelectedItem().toString());
        modelo.setRuta(txtRuta.getText());
        modelo.setIdpersona(Integer.parseInt(txtIdPersona.getText()));
        modelo.setNacimiento(java.sql.Date.valueOf(txtNacimiento.getText()));
        admin.Actualizar(modelo);
    }
    private void Eliminar() {
        UsuarioModelo modelo = new UsuarioModelo();
        modelo.setIdpersona(Integer.parseInt(txtIdPersona.getText()));
        admin.Eliminar(modelo);
        Refrescar();
    }
    
    
    private void CargarImagen(){
    JFileChooser selector=new JFileChooser();  //selector de archivos
        selector.setDialogTitle("Abrir Imagen");  // titulo de la ventana

        FileNameExtensionFilter filtro2=new FileNameExtensionFilter("Imagenes", "jpg","png","bmp","gif");  //filtro de imagenes
        selector.setFileFilter(filtro2); // aplicar el filtro
        
        int opcion=selector.showOpenDialog(null);  // validar si se seleccion archivo o no
        
        if (opcion==JFileChooser.APPROVE_OPTION) { // si se selecciono archivo devuelve "1"
            try {
                File imagenSeleccionada = selector.getSelectedFile(); //objeto file
                BufferedImage original = ImageIO.read(new File(imagenSeleccionada.getAbsolutePath())); //clase
                lbImagen.setIcon(new ImageIcon(original));
                
                
                String strNombreImagen = imagenSeleccionada.getName();
                
                
                //String Ruta="C:\\Archivos\\imagen.png" + strNombreImagen; 
                //String Ruta="C:\\Archivos\\" + strNombreImagen;  
                String Ruta="/home/juansantana/Archivos/" + strNombreImagen;  
                
                char Arreglo[]=strNombreImagen.toCharArray();
                int punto = 0;
                
                for (int i = 0; i < Arreglo.length; i++) {
                    if (Arreglo[i]=='.'){
                        punto = i;
                }                
                    
                }
                
                String ExtFile = "";
                for (int i = 0; i < Arreglo.length; i++) {
                    if (i > punto) {
                        //ExtFile += Arreglo[i];
                        ExtFile = ExtFile + Arreglo[i];
                        
                    }
                    
                    
                }
                
                //ImageIO.write(original, "png", new File(Ruta));
                ImageIO.write(original, ExtFile, new File(Ruta));
                txtRuta.setText(Ruta);
            } catch (Exception e) {
           e.printStackTrace();
            }   
        
    }
    }
    
    private void MostrarGaleria(){
        
        //frame
        JFrame form = new JFrame();
        form.setSize(800,600);
        
        JPanel panel = new JPanel();

        //datos
        List<UsuarioModelo> lista = admin.Consultar();
        
        
        String ruta;
        //recorrer la ruta
        
        for (int i = 0; i < lista.size(); i++) {
           UsuarioModelo modelo = lista.get(i); 
           
           ruta =  modelo.getRuta();
           JLabel  imagen  = new JLabel(new ImageIcon(ruta));
           
           panel.add(imagen);
           //System.out.println(modelo.getRuta());
        }
        
        form.add(panel);
        form.setVisible(true);
        
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lbNombre = new javax.swing.JLabel();
        lbEdad = new javax.swing.JLabel();
        cbEquipo = new javax.swing.JComboBox<>();
        lbEquipo = new javax.swing.JLabel();
        lbImagen = new javax.swing.JLabel();
        txtNacimiento = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnCargarImagen = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbTabla = new javax.swing.JTable();
        lbRuta = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtRuta = new javax.swing.JTextField();
        btGaleria = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtIdPersona = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Ciclismo");

        lbNombre.setText("Nombre");

        lbEdad.setText("Nacimiento");

        cbEquipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Movistar", "Sky", "Astana" }));

        lbEquipo.setText("Equipo");

        lbImagen.setBackground(new java.awt.Color(53, 127, 2));
        lbImagen.setForeground(java.awt.Color.red);
        lbImagen.setText("Imagen");
        lbImagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txtNacimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNacimientoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnBorrar.setText("Eliminar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnCargarImagen.setText("Cargar Imagen");
        btnCargarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarImagenActionPerformed(evt);
            }
        });

        tbTabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbTablaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbTabla);

        lbRuta.setText("Ruta");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        btGaleria.setText("Galeria");
        btGaleria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGaleriaActionPerformed(evt);
            }
        });

        jLabel2.setText("IdPersona");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnActualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBorrar)
                                .addGap(244, 244, 244)
                                .addComponent(btGaleria))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbRuta)
                                .addGap(58, 58, 58)
                                .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbEquipo)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIdPersona, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCargarImagen)
                            .addComponent(lbImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(149, 149, 149))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(btnCargarImagen)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbEquipo)
                            .addComponent(cbEquipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdPersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbRuta)
                    .addComponent(txtRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnActualizar)
                    .addComponent(btnBorrar)
                    .addComponent(btGaleria))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNacimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNacimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNacimientoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        Guardar();
        Mostrar();
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        Actualizar();
        Mostrar();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        // TODO add your handling code here:
        Eliminar();
        Mostrar();
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btnCargarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarImagenActionPerformed
        // TODO add your handling code here:
        CargarImagen();
        
    }//GEN-LAST:event_btnCargarImagenActionPerformed

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btGaleriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGaleriaActionPerformed
        // TODO add your handling code here:
        MostrarGaleria();
    }//GEN-LAST:event_btGaleriaActionPerformed

    private void tbTablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbTablaMouseClicked
        // TODO add your handling code here:
        Trasladar();
    }//GEN-LAST:event_tbTablaMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Formulario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Formulario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btGaleria;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBorrar;
    private javax.swing.JToggleButton btnCargarImagen;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JComboBox<String> cbEquipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbEdad;
    private javax.swing.JLabel lbEquipo;
    private javax.swing.JLabel lbImagen;
    private javax.swing.JLabel lbNombre;
    private javax.swing.JLabel lbRuta;
    private javax.swing.JTable tbTabla;
    private javax.swing.JTextField txtIdPersona;
    private javax.swing.JTextField txtNacimiento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtRuta;
    // End of variables declaration//GEN-END:variables
}
