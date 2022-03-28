/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jose.dida.galiastur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose
 */
public class UsuarioDao  {

 public static final String URL_CONEXION = "jdbc:h2:C:/Users/jose/Documents/NetBeansProjects/olas";
 public static final String USUARIO_BBDD  = "root";
 public static final String PASSWORD_BBDD = "";


    public UsuarioDao() {
    }

    
    public void crearTablaSiNoExiste() {
    
        try(Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
            Statement statement = conexionDB.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS usuario"+
                    "(id INTEGER auto_increment, " +
                    "nombre VARCHAR(25), " +
                    "contrasena VARCHAR(10), " +
                    "tipoUsuario VARCHAR(10))";
           statement.executeUpdate(sql);

        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    public void guardarUsuario (String nombre, String contraseña, String correo){
     
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
         Statement statement = conexionDB.createStatement();
         String sql = "INSERT INTO usuario (nombre, contrasena) VALUES ('" + nombre +"','" + contraseña +"')";
               
         statement.executeUpdate(sql);
            
        }catch(Exception e){
             throw new RuntimeException("Ocurrio un error al crear el usuario: " + e.getMessage());
        }
    } 
   
    public boolean comprobarUsuario (String nombre, String contrasena){
       
        List<Usuario> listaUsuarios = new ArrayList<Usuario>();
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
            Statement statement = conexionDB.createStatement();
            String sql = "SELECT * FROM usuario WHERE nombre ='" + nombre + "' AND contrasena='" + contrasena + "'";
            ResultSet rs = statement.executeQuery(sql);
     
        while(rs.next()){
             listaUsuarios .add(new Usuario(rs.getInt("id"), rs.getString("nombre"),rs.getString("contrasena"),rs.getString("tipoUsuario")));
         }

        } catch (SQLException ex) {
             Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
    
        }
     return !listaUsuarios.isEmpty();  
    }

    
}
