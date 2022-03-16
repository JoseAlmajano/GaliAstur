/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jose.dida.galiastur;

/**
 *
 * @author jose
 */
class Localidad {
 
    private int idLocalidad;
    private String nombre;

    public Localidad(int idLocalidad, String nombre) {
        this.idLocalidad = idLocalidad;
        this.nombre = nombre;
    }

    Localidad() {
       
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return  nombre ;
    }
    
    

    
    
}
