/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jose.dida.galiastur;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author jose
 */
public class ImagenListCell extends ListCell<Localidad> {
    
   
    @Override
    protected void updateItem(Localidad elementoSeleccionado, boolean empty){
        if(elementoSeleccionado != null && !empty) {
            
            super.updateItem(elementoSeleccionado, empty);
            setText(elementoSeleccionado.getNombre());
            String nombreImagen = "";
           
            String option = elementoSeleccionado.getNombre();
            
            switch(option) {
                case "Coruña" : 
                      nombreImagen = "banderaGalicia.jpg";
                    break;
                    
                case "Lugo" : 
                      nombreImagen = "banderaGalicia.jpg";
                    break;
                    
                case "Aturias Occidental" : 
                    nombreImagen = "banderaAsturiana.jpg";
                    break; 
                    
                case "Asturias Oriental" : 
                    nombreImagen = "banderaAsturiana.jpg";
                    break;    
            }
       
            Image imagen = new Image(getClass().getResourceAsStream(nombreImagen));
            ImageView imageView = new ImageView(imagen);
            Label labelConImagen = new Label();
            labelConImagen.setGraphic(imageView);
            setGraphic(labelConImagen);
            
        } else {
            setText("");
            setGraphic(null);
        }   
    }
}