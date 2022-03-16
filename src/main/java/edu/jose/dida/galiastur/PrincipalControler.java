/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.jose.dida.galiastur;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author jose
 */
public class PrincipalControler extends ControladorConNavegabilidad implements Initializable{

    @FXML
    private VBox principal;
    @FXML
    private ToolBar barraMenu;
    @FXML
    private Label descripcion;
    @FXML
    private ImageView imageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            new LocalidadDao().crearTablaSiNoExiste();
            new OlasDao().crearTablaSiNoExiste();
        } catch (SQLException ex) {
            Logger.getLogger(PrincipalControler.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Algo falla!");
        }
    }    
    
    
    private void navegarListaOlas(){
        
        this.layout.cargarPantallaActual("listaOlas");
    }
}
