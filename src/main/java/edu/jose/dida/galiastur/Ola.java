/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jose.dida.galiastur;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 *
 * @author jose
 */
public class Ola {
    
    private final int id;
    private String nombre;
    private String descripcion;
    private int idLocalidad;
    private boolean fondoRocoso;
    private String locales;
   

    public Ola(int id, String nombre, String descripcion, int idLocalidad, boolean fondoRocoso, String locales) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idLocalidad = idLocalidad;
        this.fondoRocoso = fondoRocoso;
        this.locales = locales;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idlocalidad) {
        this.idLocalidad = idlocalidad;
    }

    public boolean isFondoRocoso() {
        return fondoRocoso;
    }

    public void setFondoRocoso(boolean fondoRocoso) {
        this.fondoRocoso = fondoRocoso;
    }

    public String getLocales() {
        return locales;
    }

    public void setLocales(String locales) {
        this.locales = locales;
    }

          
       
    


   
    
}
