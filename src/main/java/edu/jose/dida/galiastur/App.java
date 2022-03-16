/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jose.dida.galiastur;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jose
 */
public class App extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        LayoutPane layoutPane = new LayoutPane();
   
        layoutPane.cargarPantallas("registro", RegistroController.class.getResource("Registro.fxml"));
        layoutPane.cargarPantallas("principal", PrincipalControler.class.getResource("Principal.fxml"));
        layoutPane.cargarPantallas("formulario", PrincipalControler.class.getResource("FormularioOla.fxml"));
        layoutPane.cargarPantallas("menu"  ,PrincipalControler.class.getResource("MenuTop.fxml"));
        layoutPane.cargarPantallas("listaOlas", PrincipalControler.class.getResource("ListaOlas.fxml"));
        layoutPane.cargarPantallas("formularioOla", PrincipalControler.class.getResource("FormularioOla.fxml"));
        //layoutPane.cargarPantallas("localidad", PrincipalControler.class.getResource("Localidad.fxml"));
        layoutPane.cargarPantallas("grafico", PrincipalControler.class.getResource("Grafico.fxml"));
        
        //layoutPane.cargarPantallaActual("formulario");
        //layoutPane.cargarPantallaActual("principal");
        layoutPane.cargarPantallaActual("registro");
        
        Scene scene = new Scene(layoutPane);
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
}
