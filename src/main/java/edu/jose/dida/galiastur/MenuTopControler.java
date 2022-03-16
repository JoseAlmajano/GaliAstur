/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jose.dida.galiastur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author jose
 */
public class MenuTopControler extends ControladorConNavegabilidad implements Initializable {
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
    }
  
    @FXML
    private void navegarListaOlas(){
        this.layout.cargarPantallaActual("listaOlas");
    }
    
    @FXML
    private void navegarFormularioOla(){
        this.layout.cargarPantallaActual("formularioOla");
    }
    
    @FXML
    private void navegarGrafico(){
        this.layout.cargarPantallaActual("grafico");
    }
    
}
