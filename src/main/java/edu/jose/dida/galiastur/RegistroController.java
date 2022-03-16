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
    private VBox pantallaRegistro;
    @FXML
    private TextField usuario;
    @FXML
    private TextField contrasena;
    @FXML
    private TextField repetirContrasena;
    @FXML
    private Button botonAceptar;
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
    
 
    /*
    Aquí hago método que hará:
    1.si estamos enregistro, es pq repetirContraseña es visible.
    comprueba que password y repetir password son =, después cogemos información e insertamos en tabla usuario(BBDD)
    
    2.Si estamos en login, repetirContraseña no esté visible
    El metodo dara acceso a la app comprobando si está registrado
    
    */
    @FXML
    private void aceptar(){
        if(this.repetirContrasena.isVisible()) {
            registrarUsuario();
             this.layout.cargarTop("menu");
             mostrarPantallaPrincipal();
        } else {

            boolean existeUsuario = usuarioDao.comprobarUsuario(usuario.getText(), contrasena.getText());
            System.out.println("existe usuario? = " + existeUsuario);
           if(existeUsuario){
               this.layout.cargarTop("menu");
               mostrarPantallaPrincipal();
           }
            
            /*  DEBERES :
            recuperar datos de usuario y contraseña
            crear metodo comprobarUsuario en Usuario dao : select * from usuario
            where nombre usuario = getUsuario y contraseña =  getContraseña;
            Truco : comprueba si resultSet viene vacío
            Crear metodo que devuelve boleano --> true si hay un usuario con ese nombre y esa contraseña en la base */ 
        }
    }
    
    private void guardarUsuario(String nombre, String contrasena, String tipoUsuario) {
       usuarioDao.guardarUsuario(nombre, contrasena, tipoUsuario);
        
    }
    
    private void registrarUsuario() {
        if(getContrasena().equals(getRepetirContrasena())){
            System.err.println("Contraseña correcta");
            
            //mandar info de usuario a la base de datos
           guardarUsuario(getUsuario(), getContrasena(), "admin");
        }
    }
    
    private void mostrarPantallaPrincipal(){
        this.layout.cargarPantallaActual("principal");
    }
    
}
