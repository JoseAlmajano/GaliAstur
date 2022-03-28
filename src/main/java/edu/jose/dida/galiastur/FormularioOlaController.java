/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.jose.dida.galiastur;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Callback;



/**
 * FXML Controller class
 *
 * @author jose
 */
public class FormularioOlaController extends ControladorConNavegabilidad implements Callback, Initializable  { 
    @FXML
    private VBox formularioOla;
    @FXML
    private Label cabeceraFormulario;
    @FXML
    private TextField nombreOla;
    @FXML
    private ComboBox<Localidad> localidad;
    @FXML
    private TextField descripcion;
    @FXML
    private CheckBox siCheck;
    @FXML
    private CheckBox noCheck;
    
    
    @FXML
    private ComboBox<String> locales;
    @FXML
    private Button aceptar;
    @FXML
    private Button volver;
    
    private int idOla = 0;
    private OlasDao olasDao = new OlasDao();
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            configurarChecks();
            configurarComboBoxLocalidad();
            configurarComboBoxLocales();
            
            localidad.setCellFactory(c -> new ImagenListCell());
            localidad.setButtonCell(new ImagenListCell() );
        } catch (SQLException ex) {
            Logger.getLogger(FormularioOlaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
     

    @FXML
    private void aceptar(ActionEvent event) throws SQLException, IOException {
       
       if(idOla == 0) {
           olasDao.añadirOla(nombreOla.getText(), descripcion.getText(), 
                localidad.getSelectionModel().getSelectedItem().getIdLocalidad(),
                siCheck.isSelected() ? 0:1, locales.getSelectionModel().getSelectedItem());
       } else {
           olasDao.modificarOla(idOla, nombreOla.getText(), descripcion.getText(),
                   localidad.getSelectionModel().getSelectedItem().getIdLocalidad(),
                   siCheck.isSelected() ? 0:1, locales.getSelectionModel().getSelectedItem());
           idOla = 0;
       }

      ListaOlasController listaOlasControler = (ListaOlasController) this.layout.getController("listaOlas");
      GraficoController graficoControler = (GraficoController) this.layout.getController("grafico");
      listaOlasControler.meterDatosEnTabla(olasDao.cargarDatosTablaOla());
      graficoControler.cargarDatosEnElChart();
      this.layout.cargarPantallaActual("listaOlas");
    }

    @FXML
    private void volver() throws SQLException {
        this.layout.cargarPantallaActual("listaOlas"); 
         Map<String, Integer> olasPorLocalidad = new OlasDao().contarOlasPorLocalidad();
    }
   
    
    private void configurarChecks() {
   
        siCheck.selectedProperty().addListener((obs, oldV, newV) -> {
            if(newV) {
               noCheck.setSelected(false);
            }
        });
        noCheck.selectedProperty().addListener((obs, oldV, newV) -> {
            if(newV) {
               siCheck.setSelected(false);
            }
        });
    }
     private void configurarComboBoxLocalidad() throws SQLException{
        ObservableList<Localidad> localidades = FXCollections.observableArrayList();  
        localidades.addAll(new LocalidadDao().getLocalidades());
        localidad.getItems().addAll(localidades);
        localidad.setConverter(new ConvertidorLocalidad());
    }
    
    private void configurarComboBoxLocales(){
          locales.getItems().addAll("Pacíficos", "Conflictivos", "Peligrosos");
    }
     
    public void prepararModificarOla(Ola ola){
        nombreOla.setText(ola.getNombre());
        descripcion.setText(ola.getDescripcion());
        Localidad localidadSeleccionada =  localidad.getItems().stream()
                .filter(item -> item.getIdLocalidad() == ola.getIdLocalidad())
                .findFirst().get();
        
        localidad.getSelectionModel().select(localidadSeleccionada);
        locales.getSelectionModel().select(ola.getLocales());
        
        if(ola.isFondoRocoso()) {
            siCheck.setSelected(true);
        } else {
            noCheck.setSelected(true);
        }
        
        idOla = ola.getId();
        
        
    }

    @Override
    public Object call(Object p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
