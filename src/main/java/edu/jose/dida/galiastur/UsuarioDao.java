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
      // int idUsuario, String nombre, String apellidos, String correo, int edad, String telefono
        try(Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
            Statement statement = conexionDB.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS usuario"+
                    "(id INTEGER auto_increment, " +
                    "nombre VARCHAR(25), " +
                    "contrasena VARCHAR(10), " +
                    "tipoUsuario VARCHAR(10))";
           statement.executeUpdate(sql);
            System.err.println("la tabla existe");
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
  /*  public ArrayList<Usuario> cargarDatosTablaOla() throws SQLException{
        
       ArrayList<Usuario> listaOlas = new ArrayList<>();
       
       try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
         Statement statement = conexionDB.createStatement();
         String sql = "SELECT * FROM ola";
         ResultSet rs = statement.executeQuery(sql);
         
         while(rs.next()){
             listaOlas .add(new Usuario(rs.getInt("id"), rs.getString("nombre"),rs.getString("descripcion"),rs.getString("localidad"),
                     rs.getInt("fondoRocoso") == 0, rs.getString("locales")));
         }
         
        }catch(Exception e){
            e.printStackTrace();
        }
     return listaOlas;
    }
    
    public void eliminarDatosTabla(Ola ola)throws SQLException{
        
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
         Statement statement = conexionDB.createStatement();
         String sql = "DELETE  FROM ola WHERE id =" + ola.getId();
         statement.executeUpdate(sql);
        }catch(Exception e){
            e.printStackTrace();
        }
    }*/
    
    public void guardarUsuario (String nombre, String contraseña, String correo){
     
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
         Statement statement = conexionDB.createStatement();
         String sql = "INSERT INTO usuario (nombre, contrasena) VALUES ('" + nombre +"','" + contraseña +"')";
               
         statement.executeUpdate(sql);
            
        }catch(Exception e){
             throw new RuntimeException("Ocurrio un error al crear el usuario: " + e.getMessage());
        }
    } 
    /* crear metodo comprobarUsuario en Usuario dao : select * from usuario
                    where nombre usuario = getUsuario y contraseña =  getContraseña;
                    Truco : comprueba si resultSet viene vacío*/
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
   /* public void modificarOla(int id, String nombre, String descripcion, String localidad, int fondoRocoso, String locales)throws SQLException{
     
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
         Statement statement = conexionDB.createStatement();
         String sql = "UPDATE ola(nombre, descripcion, localidad, fondoRocoso, locales)"+
                 "SET ('" + nombre +"','" + descripcion +"','" + localidad+"'," + fondoRocoso +",'" + locales+ "')" +
                 "WHERE id =" + id;

        } catch (Exception e) {
            
        }
    }
     
    
    public ArrayList<Integer> encontrarIdDeOla(Ola ola) throws SQLException{
        int fondoRocoso = ola.isFondoRocoso()? 0 : 1;
        ArrayList<Integer> ids = new ArrayList<>();
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
            Statement statement = conexionDB.createStatement();
            String sql = "SELECT id FROM ola WHERE NOMBRE = '" + ola.getNombre() +
                 "' AND DESCRIPCION = '"+ ola.getDescripcion()+
                 "' AND LOCALIDAD = '" + ola.getLocalidad() + 
                 "' AND FONDO ROCOSO = " + fondoRocoso +
                 " AND LOCALES ='" + ola.getLocales() + "'";

            ResultSet rs = statement.executeQuery(sql);
            
            while(rs.next()){
              ids.add(rs.getInt("id"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("ids ====>)");
        return ids;
    }
    
}
       */

    
}
