/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package edu.jose.dida.galiastur;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import org.h2.util.Utils;

/**
 * FXML Controller class
 *
 * @author jose
 */
public class ListaOlasController extends ControladorConNavegabilidad implements Initializable{
    OlasDao olasDao = new OlasDao();
     
    @FXML
    private Button botonRetroceder;
    @FXML
    private TableView<Ola> tableView;
    @FXML
    private TableColumn<Ola,String> nombre;
    @FXML
    private TableColumn<Ola,String> descripcion;
    @FXML
    private TableColumn<Ola,Integer> idLocalidad;
    @FXML
    private TableColumn<Ola,Boolean> fondoRocoso;
    @FXML
    private TableColumn<Ola,String> locales;
     @FXML
    private TableColumn<Ola,String> opciones;

    private ObservableList<Ola> elementosTabla = FXCollections.observableArrayList();
    private LocalidadDao localidadDao;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            localidadDao = new LocalidadDao();
            configurarTabla();
            olasDao.cargarDatosTablaOla().forEach(ola -> System.out.println("ola localidad = " + ola.getIdLocalidad()));
            meterDatosEnTabla(olasDao.cargarDatosTablaOla());
        } catch (SQLException ex) {
            Logger.getLogger(ListaOlasController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
 
    private void configurarTabla() {
        nombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        nombre.setCellFactory(new Callback<TableColumn<Ola, String>, TableCell<Ola, String>>() {
            @Override
            public TableCell<Ola, String> call(TableColumn<Ola, String> columnaNombre) {
                return new TableCell<Ola, String>(){
                    
                    @Override public void updateItem(String nombre,boolean empty){
                        super.updateItem(nombre,empty);
                        if(nombre != null && !empty){
                            HBox contenedorCelda = new HBox(5);
                            Button botonEliminar = new Button("X");
                            Button botonModificar = new Button("M");
                            Label texto = new Label(nombre);
                            botonEliminar.setOnAction(new EventHandler<ActionEvent>() {
                                @Override public void handle(ActionEvent e) {
                                    try {
                                        eliminarOla();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ListaOlasController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            botonModificar.setOnAction(new EventHandler<ActionEvent>() {
                                @Override public void handle(ActionEvent e) {
                                    try {
                                        modificarOla();
                                    } catch (SQLException ex) {
                                        Logger.getLogger(ListaOlasController.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IOException ex) {
                                        Logger.getLogger(ListaOlasController.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            });
                            contenedorCelda.getChildren().addAll(texto, botonModificar, botonEliminar);
                     
                            
                            /*this.getTableRow()*/
                            
                            setText("");
                            setGraphic(contenedorCelda);
                        }else {
                            setText("");
                            setGraphic(null);
                        }
                    }
                };

            }
        });
        
        
        descripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        idLocalidad.setCellValueFactory(new PropertyValueFactory("idLocalidad"));
        idLocalidad.setCellFactory(new Callback<TableColumn<Ola, Integer>, TableCell<Ola, Integer>>(){
            @Override
            public TableCell<Ola, Integer> call(TableColumn<Ola, Integer> columnaLocalidad) {
                return new TableCell<Ola, Integer>(){
                    
                    @Override public void updateItem(Integer idLocalidad ,boolean empty){
                        super.updateItem(idLocalidad, empty);
                        
                        if(idLocalidad != null && !empty){
                            
                            String localidad = "";
                            try {
                                localidad = new LocalidadDao().encontrarLocalidad(idLocalidad);
                            } catch (SQLException ex) {
                                Logger.getLogger(ListaOlasController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            setText(localidad);
                            setGraphic(null);
                        }else {
                            setText("");
                            setGraphic(null);
                        }
                    }
                };
            }
        });
        
        fondoRocoso.setCellValueFactory(new PropertyValueFactory("fondoRocoso"));
        locales.setCellValueFactory(new PropertyValueFactory("locales"));
       
    }
    
    private void eliminarOla() throws SQLException {
        Ola ola = tableView.getSelectionModel().getSelectedItem();
        System.out.println("ID OLA ===> " + ola.getId());
        olasDao.eliminarDatosTabla(ola);
       
        meterDatosEnTabla(olasDao.cargarDatosTablaOla()); 
    }
    
    private void modificarOla() throws SQLException, IOException{
        Ola ola = tableView.getSelectionModel().getSelectedItem();
        navegarEdicion(ola);
       /* olasDao.modificarOla(ola.getId(), ola.getNombre(), ola.getDescripcion(),
       ola.getLocalidad(), ola.isFondoRocoso() ? 0:1, ola.getLocales());*/
       
       
        
        /*tableView.getItems().clear();
        meterDatosEnTabla(olasDao.cargarDatosTablaOla());*/ 
    }
    
    public void meterDatosEnTabla(ArrayList<Ola> listaOlas) throws SQLException {
        tableView.getItems().clear();
        elementosTabla.addAll(listaOlas);
        tableView.setItems(elementosTabla);
    }
    
    private void navegarEdicion(Ola ola) throws IOException {
        FormularioOlaController controladorFormulario =  (FormularioOlaController) this.layout.getController("formularioOla");
        controladorFormulario.prepararModificarOla(ola);
        this.layout.cargarPantallaActual("formularioOla");
    }
    
}
