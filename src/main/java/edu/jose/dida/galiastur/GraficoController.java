/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jose.dida.galiastur;
import edu.jose.dida.galiastur.LocalidadDao;
import edu.jose.dida.galiastur.ControladorConNavegabilidad;
import java.net.URL;
import java.util.*;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

/**
 *
 * @author jose
 */
public class GraficoController extends ControladorConNavegabilidad implements Initializable{

    LocalidadDao localidadDao;
    
    @FXML
    private PieChart chart;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            localidadDao = new LocalidadDao();
//            cargarDatosEnElChart();
//        } catch (SQLException ex) {
//            Logger.getLogger(GraficoController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public void cargarDatosEnElChart() throws SQLException {
//       Map<String, Integer> olasPorLocalidad = localidadDao.contarOlasPorLocalidad();
//       
//       ObservableList<PieChart.Data> datosParaElChart = FXCollections.observableArrayList();
//       olasPorLocalidad.forEach((localidad, cantidad)-> {
//           PieChart.Data data = new PieChart.Data(localidad, cantidad);
//           datosParaElChart.add(data);
//       });
//       chart.setData(datosParaElChart);
    }
    
}
