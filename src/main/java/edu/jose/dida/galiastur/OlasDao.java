/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jose.dida.galiastur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class OlasDao {
    
 public static final String URL_CONEXION = "jdbc:h2:C:/Users/jose/Documents/NetBeansProjects/olas";
 public static final String USUARIO_BBDD  = "root";
 public static final String PASSWORD_BBDD="";

    
    public void crearTablaSiNoExiste() throws SQLException {
       
        try(Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
            Statement statement = conexionDB.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS ola"+
                    "(id INTEGER auto_increment PRIMARY KEY, " +
                    "nombre VARCHAR(255), " +
                    "descripcion VARCHAR(255), " +
                    "idLocalidad INTEGER, " +
                    "fondoRocoso INTEGER , " +
                    "locales VARCHAR (255)," + 
                    " FOREIGN KEY (idLocalidad) REFERENCES localidad (idLocalidad))";
           statement.executeUpdate(sql);
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public ArrayList<Ola> cargarDatosTablaOla(){
        
       ArrayList<Ola> listaOlas = new ArrayList<>();
       
       try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
         Statement statement = conexionDB.createStatement();
         String sql = "SELECT * FROM ola";
         ResultSet rs = statement.executeQuery(sql);
         
         while(rs.next()){
            
             listaOlas .add(new Ola(rs.getInt("id"), rs.getString("nombre"), rs.getString("descripcion"), rs.getInt("idLocalidad"),
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
    }
    
    public void añadirOla(String nombre, String descripcion, int idLocalidad, int fondoRocoso, String locales)throws SQLException{
     
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
         Statement statement = conexionDB.createStatement();
         String sql = "INSERT INTO ola (nombre, descripcion, idlocalidad, fondoRocoso, locales) "+
                 "VALUES ('" + nombre +"','" + descripcion +"'," + idLocalidad +"," + fondoRocoso +",'" + locales+ "')";                
            statement.executeUpdate(sql);
            
        }catch(Exception e){
             throw new RuntimeException("Ocurrio un error al actualizar la informacion: " + e.getMessage());
        }
    } 
    
    public void modificarOla(int id, String nombre, String descripcion, int idLocalidad, int fondoRocoso, String locales)throws SQLException{
     
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
         Statement statement = conexionDB.createStatement();
         String sql = "UPDATE ola SET nombre ='" + nombre +"', descripcion = '" + descripcion + 
                 "', idlocalidad = " + idLocalidad + ", fondoRocoso = " + fondoRocoso + ", locales ='" + locales +
                 "' WHERE id =" + id;
          
         statement.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public ArrayList<Integer> encontrarIdDeOla(Ola ola) throws SQLException{
        int fondoRocoso = ola.isFondoRocoso()? 0 : 1;
        ArrayList<Integer> ids = new ArrayList<>();
        try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
            Statement statement = conexionDB.createStatement();
            String sql = "SELECT id FROM ola WHERE NOMBRE = '" + ola.getNombre() +
                 "' AND DESCRIPCION = '"+ ola.getDescripcion()+
                 "' AND LOCALIDAD = '" + ola.getIdLocalidad() + 
                 "' AND FONDO ROCOSO = " + fondoRocoso +
                 " AND LOCALES ='" + ola.getLocales() + "'";

            ResultSet rs = statement.executeQuery(sql);
            
            while(rs.next()){
              ids.add(rs.getInt("id"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
      
        return ids;
    }
   

    public Map<String, Integer> contarOlasPorLocalidad()  {
  
       Map<String, Integer> olasPorLocalidad = new HashMap<>();
       
      
        try(Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
            Statement statement = conexionDB.createStatement();
            String sql = "select l.nombre as nombre, count(1) as cantidad " +
                        "from ola o " +
                        "inner join localidad l " +
                        "where o.idLocalidad = l.idLocalidad " +
                        "group by l.nombre;";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                String localidad = resultSet.getString("nombre");
                int cantidadOlas = resultSet.getInt("cantidad");
                olasPorLocalidad.put(localidad, cantidadOlas);
            }
    }catch (Exception e){
        throw new RuntimeException("Ocurrió un error al contar el número de olas por localidad: " + e.getMessage());
    }
        return olasPorLocalidad;
        
   
}
       
}