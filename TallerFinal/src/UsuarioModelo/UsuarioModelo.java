/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UsuarioModelo;

/**
 *
 * @author juansantana
 */
public class UsuarioModelo {
    
    private String Nombre;
    private String Equipo;
    private int    Edad;
    private String Ruta;

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
     * @return the Edad
     */
    public int getEdad() {
        return Edad;
    }

    /**
     * @param Edad the Edad to set
     */
    public void setEdad(int Edad) {
        this.Edad = Edad;
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
    
}
