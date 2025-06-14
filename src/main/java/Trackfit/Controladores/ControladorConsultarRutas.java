package Trackfit.Controladores;

import Trackfit.ManejoRutas.Ruta;
import Trackfit.ManejoRutas.ManejoUbicaciones.Ubicacion;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import com.gluonhq.maps.*;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import java.util.ArrayList;


import java.util.List;

import java.io.IOException;

public class ControladorConsultarRutas extends ControladorMenu {

    @FXML
    private VBox idMapa;
    
    @FXML
    private AnchorPane idPantalla;

    @FXML
    private JFXComboBox<String> idRutas;

    @FXML
    private Label idTitulo;

    @FXML
    private Button idTrackFit;

    @FXML
    private JFXListView<String> idUbicaciones;

    @FXML
    void initialize() {

        idUbicaciones.setCellFactory(_ -> {
            ListCell<String> cell = new ListCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item);
                    }
                }
            };

            // Aquí evitamos que responda al mouse
            cell.setMouseTransparent(true);
            cell.setFocusTraversable(false);
            return cell;
        });

        idRutas.setItems(FXCollections.observableArrayList(sistema.getNombresRutas()));

    }

    void actualizarMapa(Ruta ruta) {
        MapView mapView = createMapView(ruta);
        idMapa.getChildren().clear();
        idMapa.getChildren().add(mapView);
        VBox.setVgrow(mapView, Priority.ALWAYS);
    }

    private MapView createMapView(Ruta ruta) {
        MapView mapView = new MapView();
        mapView.setPrefSize(500,400);
        mapView.addLayer(new ControladorConsultarRutas.CustomMapLayer(ruta));
        mapView.setZoom(11);
        mapView.flyTo(0,new MapPoint(4.649923,-74.103937), 0.1);

        return mapView;
    }

    private class CustomMapLayer extends MapLayer{
        private final List<Node> markers = new ArrayList<>();
        private final Ruta ruta;
        
        public CustomMapLayer(Ruta ruta){
            this.ruta = ruta;
            int i = 0;
            for(Ubicacion _: ruta.getUbicaciones()){
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
    void onActionRutas(ActionEvent event) {

        idTitulo.setVisible(true);
        idUbicaciones.getItems().clear();

        

        Ruta ruta = sistema.buscarRuta(idRutas.getValue());
        List<Ubicacion> listaUbicaciones = ruta.getUbicaciones();

        actualizarMapa(ruta);

        ObservableList<String> ubicaciones = idUbicaciones.getItems();

        for(Ubicacion u: listaUbicaciones)
            ubicaciones.add(u.getNombre() + " - Dirección: " + u.getDireccion());

    }

    @FXML
    void onActionTrackFit(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/Menu.fxml"));
        idPantalla = loader.load();

        Scene secondScene = new Scene(idPantalla);
        Stage stage = (Stage) idTrackFit.getScene().getWindow();
        stage.setScene(secondScene);
        stage.show();


    }

}

