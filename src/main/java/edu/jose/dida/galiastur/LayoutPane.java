/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jose.dida.galiastur;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;



public class LayoutPane extends BorderPane {
    private Map<String, Node> pantallas;
    private final HashMap<String, ControladorConNavegabilidad> controladores;
  
    

    public LayoutPane() {
        this.pantallas = new HashMap<>();
        this.controladores = new HashMap<>();
    }
    
    public void cargarPantallas(String nombrePantalla, URL direccionFXML ) throws IOException{
     FXMLLoader cargadorPantallas = new FXMLLoader(direccionFXML);   
     Parent pantalla = cargadorPantallas.load();    
     
     ControladorConNavegabilidad controladorPantalla = cargadorPantallas.getController();
     controladores.put(nombrePantalla, controladorPantalla);
     controladorPantalla.setLayout(this);
     
     pantallas.put(nombrePantalla, pantalla);
    
    }
    
    public ControladorConNavegabilidad getController(String nombrePantalla) throws IOException {
       return controladores.get(nombrePantalla);
        
    }
    
    public void cargarPantallaActual(String nombrePantalla){
        this.setCenter(pantallas.get(nombrePantalla));
    }
    
     public void cargarTop(String nombrePantalla){
        this.setTop(pantallas.get(nombrePantalla));
    }

    public Map<String, Node> getPantallas() {
        return pantallas;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}


