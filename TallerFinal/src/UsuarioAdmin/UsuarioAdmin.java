/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UsuarioAdmin;


import UsuarioModelo.UsuarioModelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import newpackage.Conexion;

/**
 *
 * @author Pc12
 */

public class UsuarioAdmin extends Conexion {
    
    public List<UsuarioModelo> Consultar() {

        List<UsuarioModelo> lista = new ArrayList<>();

        Conectar();

        try {

            PreparedStatement sentencia = cnn.prepareCall("CALL `ConsultarPersona`()");
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                UsuarioModelo modelo = new UsuarioModelo();
                modelo.setIdpersona(resultado.getInt(1));
                modelo.setNombre(resultado.getString(2));
                modelo.setEquipo(resultado.getString(3));
                modelo.setNacimiento(resultado.getDate(4));
                modelo.setRuta(resultado.getString(5));
                
                
                lista.add(modelo);
            }
        } catch (Exception e) {

            e.printStackTrace();

        } finally {
            DesConectar();

        }
        return lista;

    }

    public void Guardar(UsuarioModelo modelo) {
        Conectar();
        try {

            PreparedStatement sentencia = cnn.prepareCall("CALL `InsertarPersona`(?,?,?,?)");
            sentencia.setString(1, modelo.getNombre());
            sentencia.setString(2, modelo.getEquipo());
            sentencia.setDate(3, modelo.getNacimiento());
            sentencia.setString(4, modelo.getRuta());
            
            
            int resultado = sentencia.executeUpdate();
            if (resultado == 1) {
                JOptionPane.showMessageDialog(new JFrame(), "Persona Guardada");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Persona NO Guardada");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DesConectar();
        }

    }

    public void Actualizar(UsuarioModelo modelo) {
        Conectar();
        try {

            PreparedStatement sentencia = cnn.prepareCall("CALL `ActualizarPersona`(?,?,?,?,?)");
            sentencia.setInt(   1, modelo.getIdpersona());
            sentencia.setString(2, modelo.getNombre());
            sentencia.setString(3, modelo.getEquipo());
            sentencia.setDate(  4, modelo.getNacimiento());
            sentencia.setString(5, modelo.getRuta());

            int resultado = sentencia.executeUpdate();
            if (resultado == 1) {
                JOptionPane.showMessageDialog(new JFrame(), "Persona Guardada");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Persona NO Guardada");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DesConectar();
        }

    }

    public void Eliminar(UsuarioModelo modelo) {
        Conectar();
        try {

            PreparedStatement sentencia = cnn.prepareCall("CALL `BorrarPersona`(?)");
            sentencia.setInt(1, modelo.getIdpersona());

            int resultado = sentencia.executeUpdate();
            if (resultado == 1) {
                JOptionPane.showMessageDialog(new JFrame(), "Persona Borrada");
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "Persona NO Borrada");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DesConectar();
        }

    }
    
}
