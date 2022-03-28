
package edu.jose.dida.galiastur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author jose
 */
public class LocalidadDao {
    
 public static final String URL_CONEXION = "jdbc:h2:C:/Users/jose/Documents/NetBeansProjects/olas";
 public static final String USUARIO_BBDD  = "root";
 public static final String PASSWORD_BBDD="";
 
 Localidad localidades;
 
    public LocalidadDao() throws SQLException{
        crearTablaSiNoExiste();
    }
    
    public void crearTablaSiNoExiste() throws SQLException {
       
        try(Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
            Statement statement = conexionDB.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS localidad "+
                    "(idLocalidad INTEGER auto_increment PRIMARY KEY, " +
                    "nombre VARCHAR(50))";
           statement.executeUpdate(sql);
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    
     public ArrayList<Localidad> getLocalidades() throws SQLException{
        
       ArrayList<Localidad> localidades = new ArrayList<>();
       
       try (Connection conexionDB = DriverManager.getConnection(URL_CONEXION, USUARIO_BBDD, PASSWORD_BBDD)){
         Statement statement = conexionDB.createStatement();
         String sql = "SELECT * FROM localidad";
         ResultSet rs = statement.executeQuery(sql);
         
         while(rs.next()){
             localidades .add(new Localidad(rs.getInt("idLocalidad"), rs.getString("nombre")));
         }
         
        }catch(Exception e){
            e.printStackTrace();
        }
     return localidades;
    }

    public String encontrarLocalidad(int idLocalidad) throws SQLException {
        
         List<String> nombresLocalidades = new ArrayList<>();
        
        try(Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
            Statement statement = conexionDB.createStatement();
            String sql = "SELECT nombre FROM localidad where idLocalidad =" + idLocalidad;
        //Consulta que me haga select nombre where idLocalidad = ? al valor pasado por parametro
        
            ResultSet resultSet = statement.executeQuery(sql);
            
           
            while(resultSet.next()){
                nombresLocalidades.add(resultSet.getString("nombre"));
            }
        } catch(Exception e){
            throw new RuntimeException("Ocurrió un error al consultar la iinformación: " + e.getMessage());
        }
        return !nombresLocalidades.isEmpty()? nombresLocalidades.get(0):"";
    }
}