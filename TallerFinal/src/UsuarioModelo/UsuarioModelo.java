/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UsuarioModelo;

import java.sql.Date;

/**
 *
 * @author juansantana
 */
public class UsuarioModelo {
    
    private String Nombre;
    private String Equipo;
    private Date   Nacimiento;
    private String Ruta;
    private int    idpersona;

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the Equipo
     */
    public String getEquipo() {
        return Equipo;
    }

    /**
     * @param Equipo the Equipo to set
     */
    public void setEquipo(String Equipo) {
        this.Equipo = Equipo;
    }

   

    /**
     * @return the Ruta
     */
    public String getRuta() {
        return Ruta;
    }

    /**
     * @param Ruta the Ruta to set
     */
    public void setRuta(String Ruta) {
        this.Ruta = Ruta;
    }

    /**
     * @return the idpersona
     */
    public int getIdpersona() {
        return idpersona;
    }

    /**
     * @param idpersona the idpersona to set
     */
    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    /**
     * @return the Nacimiento
     */
    public Date getNacimiento() {
        return Nacimiento;
    }

    /**
     * @param Nacimiento the Nacimiento to set
     */
    public void setNacimiento(Date Nacimiento) {
        this.Nacimiento = Nacimiento;
    }
    
}

