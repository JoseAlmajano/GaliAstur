/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.jose.dida.galiastur;
import java.net.URL;
import java.util.Map;
import java.util.ResourceBundle;
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

    
    @FXML
    private PieChart chart;

    private OlasDao olasDao = new OlasDao();
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarDatosEnElChart();

    }

    @FXML
    public void cargarDatosEnElChart() {
       Map<String, Integer> olasPorLocalidad = olasDao.contarOlasPorLocalidad();
      
       ObservableList<PieChart.Data> datosParaElChart = FXCollections.observableArrayList();
       olasPorLocalidad.forEach((nombreOla, cantidad)-> {
           PieChart.Data data = new PieChart.Data(nombreOla, cantidad);
           datosParaElChart.add(data);
       });
       chart.setData(datosParaElChart);
    }
    
}