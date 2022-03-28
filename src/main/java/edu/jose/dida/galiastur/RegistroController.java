/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jose.dida.galiastur;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;

/**
 *
 * @author jose
 */
public class RegistroController extends ControladorConNavegabilidad implements Initializable{
    
    @FXML
    private TextField usuario;
    @FXML
    private TextField contrasena;
    @FXML
    private TextField repetirContrasena;
    @FXML
    private Button botonRegistro;
    @FXML
    private Button botonCancelar;
    private UsuarioDao usuarioDao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarioDao = new UsuarioDao();
        usuarioDao.crearTablaSiNoExiste();
        botonCancelar.managedProperty().bind(botonCancelar.visibleProperty());
        botonRegistro.managedProperty().bind(botonRegistro.visibleProperty());
        
    }

    public String getUsuario() {
        return usuario.getText();
    }


    public String getContrasena() {
        return contrasena.getText();
    }

    public String getRepetirContrasena() {
        return repetirContrasena.getText();
    }
  
    @FXML
    private void cambiarARegistro(){
        repetirContrasena.setVisible(true);
        botonRegistro.setVisible(false);
        botonCancelar.setVisible(true);
    }
    
    @FXML
    private void cambiarALogin(){
        repetirContrasena.setVisible(false);
        botonRegistro.setVisible(true);
        botonCancelar.setVisible(false);
        
    }
    
    @FXML
    private void aceptar(){
        if(this.repetirContrasena.isVisible()) {
            registrarUsuario();
             this.layout.cargarTop("menu");
             mostrarPantallaPrincipal();
        } else {

            boolean existeUsuario = usuarioDao.comprobarUsuario(usuario.getText(), contrasena.getText());
        
           if(existeUsuario){
               this.layout.cargarTop("menu");
               mostrarPantallaPrincipal();
           }
        }
    }
    
    private void guardarUsuario(String nombre, String contrasena, String tipoUsuario) {
       usuarioDao.guardarUsuario(nombre, contrasena, tipoUsuario);
        
    }
    
    private void registrarUsuario() {
        if(getContrasena().equals(getRepetirContrasena())){
           guardarUsuario(getUsuario(), getContrasena(), "admin");
        }
    }
    
    private void mostrarPantallaPrincipal(){
        this.layout.cargarPantallaActual("principal");
    }
    
}
