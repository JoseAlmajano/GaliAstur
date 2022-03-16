
package edu.jose.dida.galiastur;

import static edu.jose.dida.galiastur.OlasDao.PASSWORD_BBDD;
import static edu.jose.dida.galiastur.OlasDao.URL_CONEXION;
import static edu.jose.dida.galiastur.OlasDao.USUARIO_BBDD;
import edu.jose.dida.galiastur.Localidad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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

    public Map<String, Integer> contarOlasPorLocalidad() throws SQLException {
//       List<Localidad> localidades = buscarTodos();
       Map<String, Integer> olasPorLocalidad = new HashMap<>();
       
      
        try(Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
            Statement statement = conexionDB.createStatement();
            String sql = "SELECT l.nombre, count(1) as cantidad FROM"
                    + " ola  o inner join localidad l where o.idLocalidad=o.idLocalidad GROUP BY l.nombre";
            ResultSet resultSet = statement.executeQuery(sql);
            System.out.println("SQL = " + sql);
            while(resultSet.next()){
                String nombre = resultSet.getString("nombre");
                int cantidadOlas = resultSet.getInt("cantidad");
                olasPorLocalidad.put(nombre, cantidadOlas);
                
//                for(Localidad localidad: localidades){
//                    if(localidad.getIdLocalidad() == idLocalidad){
//                        olasPorDepartamento.put(localidad.getIdLocalidad(), localidad.getNombre());
//                        cantidadOlas++;
//                        break;
//                    }
//                }
            }
    }catch (Exception e){
        throw new RuntimeException("Ocurrió un error al consultar la información: " + e.getMessage());
    }
        return olasPorLocalidad;
        
   
}
    public void guardarOActualizar(Localidad localidad) throws SQLException{
        if(localidad.getIdLocalidad() == 0){
            guardar(localidad);
        }else{
            actualizar(localidad);
        }
    }
    
    public void guardar(Localidad localidad) throws SQLException{
          try(Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
            Statement statement = conexionDB.createStatement();
            String sql ="INSERT INTO localidad(nombre)"
                    + "VALUES('"+ localidad.getNombre()+"')";
            statement.executeUpdate(sql);    
    }catch (Exception e){
        throw new RuntimeException("Ocurrió un error al guardar la información" + e.getMessage());
    }}
          
    public void actualizar(Localidad localidad){
         try(Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
            Statement statement = conexionDB.createStatement();
            String sql ="UPDATE localidad set nombre='" + localidad.getNombre()
                    +"' WHERE idLocalidad= " + localidad.getIdLocalidad();
            statement.executeUpdate(sql);
     }catch (Exception e){
        throw new RuntimeException("Ocurrió un error al guardar la información" + e.getMessage());
    }
    }
       
    

    public List<Localidad> buscarTodos(){
       List<Localidad> localidades = new ArrayList<>();
        try(Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
            Statement statement = conexionDB.createStatement();
            String sql ="SELECT * FROM localidad ORDER BY idLocalidad";
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Localidad localidad = new Localidad();
                localidad.setIdLocalidad(resultSet.getInt("idLocalidad"));
                localidad.setNombre(resultSet.getString("nombre"));
                localidades.add(localidad);
            }
        }catch(Exception e){
            throw new RuntimeException("Ocurrió un error al consultar la iinformación: " + e.getMessage());
        }
    return localidades;
}
    public void eliminar(Localidad localidad){
          try(Connection conexionDB = DriverManager.getConnection(URL_CONEXION,USUARIO_BBDD,PASSWORD_BBDD)){
            Statement statement = conexionDB.createStatement();
            String sql ="DELETE FROM localidad WHERE idLocalidad= " +localidad.getIdLocalidad();
            statement.executeUpdate(sql);
    } catch(Exception e){
            throw new RuntimeException("Ocurrió un error al consultar la iinformación: " + e.getMessage());
        }
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