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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;


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
            
            localidad.setCellFactory(cell -> new ListCell<Localidad>(){
            
               @Override
               protected void updateItem(Localidad localidad, boolean empty){
                super.updateItem(localidad, empty);
                     if(localidad != null && !empty){
                         
                         /*   setText(elementoSeleccionado.getNombre());
            String nombreImagen = "";
           
            String option = elementoSeleccionado.getNombre();
            
            switch(option) {
                case "Coruña" : 
                      nombreImagen = "banderaGalicia.jpg";
                    break;
                    
                case "Lugo" : 
                      nombreImagen = "banderaGalicia.jpg";
                    break;
                    
                case "Aturias Occidental" : 
                    nombreImagen = "banderaAsturiana.jpg";
                    break; 
                    
                case "Asturias Oriental" : 
                    nombreImagen = "banderaAsturiana.jpg";
                    break;    
            }
       
            Image imagen = new Image(getClass().getResourceAsStream(nombreImagen));
            ImageView imageView = new ImageView(imagen);
            Label labelConImagen = new Label();
            labelConImagen.setGraphic(imageView);
            setGraphic(labelConImagen); */
                        }else {
                            setText("");
                            setGraphic(null);
                        }
                
               }
                    
            });
      
            
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
       }

      ListaOlasController listaOlasControler = (ListaOlasController) this.layout.getController("listaOlas");
      listaOlasControler.meterDatosEnTabla(olasDao.cargarDatosTablaOla());
      this.layout.cargarPantallaActual("listaOlas");
    }

    @FXML
    private void volver() throws SQLException {
     this.layout.cargarPantallaActual("listaOlas"); 
     Map<String, Integer> olasPorLocalidad = new LocalidadDao().contarOlasPorLocalidad();
       olasPorLocalidad.entrySet().forEach(e -> System.out.println("Localidad = "  + e.getKey() + " num olas = " + e.getValue()));
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
         System.out.println("localidades --->" + localidades);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
