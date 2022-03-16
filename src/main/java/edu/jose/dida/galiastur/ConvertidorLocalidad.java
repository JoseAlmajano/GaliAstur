/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jose.dida.galiastur;

import javafx.util.StringConverter;

/**
 *
 * @author jose
 */
public class ConvertidorLocalidad extends StringConverter<Localidad> {

    @Override
    public String toString(Localidad localidad) {
        if(localidad.getNombre() == null){
        return "";
        }
        return localidad.getNombre(); 
    }

    @Override
    public Localidad fromString(String string) {
        return null; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
