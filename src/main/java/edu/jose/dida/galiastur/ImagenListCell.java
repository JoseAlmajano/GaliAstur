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
    protected void updateItem(Localidad nombreLocalidad, boolean empty){
        super.updateItem(nombreLocalidad, empty);

            setText(null);
            setGraphic(null);
            
            if(nombreLocalidad != null){
                String nombreImagen = "";
                String option = nombreLocalidad.getNombre();
            
                switch(option) {
                    case "Coruña" : 
                          nombreImagen = "/imagenes/banderaGalicia.jpg";
                        break;

                    case "Lugo" : 
                          nombreImagen = "/imagenes/banderaGalicia.jpg";
                        break;

                    case "Asturias Occidental" :
                        nombreImagen = "/imagenes/banderaAsturiana.jpg";
                        break; 

                    case "Asturias Oriental" : 
                        nombreImagen = "/imagenes/banderaAsturiana.jpg";
                        break;    
                }
                
                Image imagen = new Image(getClass().getResourceAsStream(nombreImagen));
                ImageView imageView = new ImageView(imagen);
                imageView.setFitHeight(20);
                imageView.setFitWidth(40);
                Label labelConImagen = new Label();
                labelConImagen.setGraphic(imageView);
                setText(option);
                setGraphic(labelConImagen);
                
                
            }
    }
}