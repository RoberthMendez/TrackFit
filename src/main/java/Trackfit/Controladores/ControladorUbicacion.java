package Trackfit.Controladores;

import Trackfit.ManejoExcepciones.RutaExc;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import com.gluonhq.maps.*;
import javafx.scene.Node;

import java.util.ArrayList; 
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.geometry.Point2D;
import Trackfit.ManejoRutas.ManejoUbicaciones.Ubicacion;

import java.io.IOException;

public class ControladorUbicacion extends ControladorCrearRuta {

    @FXML
    private VBox idMapa;
    
    @FXML
    private AnchorPane idPantalla;

    @FXML
    private TextField idErrorNombre;

    @FXML
    private Button idAtras;

    @FXML
    private TextField idDireccion;

    @FXML
    private TextField idErrorDireccion;

    @FXML
    private Button idFinalizar;

    @FXML
    private TextField idNombre;

    @FXML
    private Button idTrackFit;

    @FXML
    private TextField idAgregarUbicacion;

    private int ubicaciones = 0;

    @FXML
    void initialize() {
        Platform.runLater(() -> idPantalla.requestFocus());
        actualizarMapa();   
    }

    void actualizarMapa() {
        MapView mapView = createMapView();
        idMapa.getChildren().clear();
        idMapa.getChildren().add(mapView);
        VBox.setVgrow(mapView, Priority.ALWAYS);
    }

    private MapView createMapView() {
        MapView mapView = new MapView();
        mapView.setPrefSize(500,400);
        mapView.addLayer(new ControladorUbicacion.CustomMapLayer());
        mapView.setZoom(11);
        mapView.flyTo(0,new MapPoint(4.649923,-74.103937), 0.1);

        return mapView;
    }

    private class CustomMapLayer extends MapLayer{
        private final List<Node> markers = new ArrayList<>();;
        
        public CustomMapLayer(){
            int i=0;
            for(Ubicacion U: ruta.getUbicaciones()){
                markers.add(new Circle(5, Color.RED));
                getChildren().add(markers.get(i));
                i++;
            }
        }
        
        @Override
        protected void layoutLayer(){
            int i=0;
            for(Ubicacion U: ruta.getUbicaciones()){
                Point2D point = getMapPoint(U.getCoordenada().getLatitude(),U.getCoordenada().getLongitude());
                markers.get(i).setTranslateX(point.getX());
                markers.get(i).setTranslateY(point.getY());
                i++;
            }
        }
    }

    @FXML
    void onMouseClickedNombre() {
        idAgregarUbicacion.clear();
    }

    @FXML
    void onMouseClickedLatitud() {
        idAgregarUbicacion.clear();
    }

    @FXML
    void onMouseClickedLongitud() {
        idAgregarUbicacion.clear();
    }

    @FXML
    void onActionAgregar() {

        if(idNombre.getText().isBlank()){
            idErrorNombre.setText("El Nombre de la Ubicacion es obligatorio");
            return;
        }
        idErrorNombre.clear();

        if(idDireccion.getText().isBlank()){
            idDireccion.setText("La Direccion es obligatoria");
            return;
        }
        idErrorDireccion.clear();

        //Agregar la ubicación a la ruta
        try {
            sistema.agregarUbicacionARuta(ruta, idNombre.getText(), idDireccion.getText(), ubicaciones+1);
        } catch (RutaExc e) {
            idErrorNombre.clear();
            idErrorDireccion.setText("Ya existe una ubicación con esa dirección");
            idDireccion.clear();
            idDireccion.requestFocus();
            return;
        } catch (Exception e){
            idErrorNombre.setText(e.getMessage());
            return;
        }

        actualizarMapa();
        idAgregarUbicacion.setText("Se agregó una nueva ubicación");
        idNombre.clear();
        idDireccion.clear();

        ubicaciones++;
        if(ubicaciones > 1){
            idFinalizar.setDisable(false);
            idFinalizar.setOpacity(1);
            idFinalizar.setCursor(javafx.scene.Cursor.HAND);
        }

    }

    @FXML
    void onActionFinalizar(ActionEvent event) throws IOException {

        sistema.crearRuta(ruta);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/PopUpRuta.fxml"));
        AnchorPane ap = loader.load();

        Stage popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.initOwner(((Node) event.getSource()).getScene().getWindow());
        popUp.setScene(new Scene(ap));

        popUp.setTitle("Ruta Creada");
        popUp.showAndWait();

        loader = new FXMLLoader(getClass().getResource("/Interfaces/Menu.fxml"));
        idPantalla = loader.load();

        Scene secondScene = new Scene(idPantalla);

        Stage stage = (Stage) idFinalizar.getScene().getWindow();
        stage.setScene(secondScene);
        stage.show();

    }

    @FXML
    void onActionAtras() throws IOException {

        //Borrar Todas las ubicaciones
        //Borrar el nombre de la ruta
        ruta = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/CrearRuta.fxml"));
        idPantalla = loader.load();

        Scene secondScene = new Scene(idPantalla);

        Stage stage = (Stage) idAtras.getScene().getWindow();
        stage.setScene(secondScene);
        stage.show();

    }


    @FXML
    void onActionTrackFit() throws IOException {

        //Borrar Todas las ubicaciones
        //Borrar el nombre de la ruta
        ruta = null;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/Menu.fxml"));
        idPantalla = loader.load();

        Scene secondScene = new Scene(idPantalla);

        Stage stage = (Stage) idTrackFit.getScene().getWindow();
        stage.setScene(secondScene);
        stage.show();

    }

}
