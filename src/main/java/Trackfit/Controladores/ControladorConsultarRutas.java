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
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.util.List;

import java.io.IOException;

public class ControladorConsultarRutas extends ControladorMenu {

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

    @FXML
    void onActionRutas(ActionEvent event) {

        idTitulo.setVisible(true);
        idUbicaciones.getItems().clear();

        Ruta ruta = sistema.buscarRuta(idRutas.getValue());
        List<Ubicacion> listaUbicaciones = ruta.getUbicaciones();

        ObservableList<String> ubicaciones = idUbicaciones.getItems();

        for(Ubicacion u: listaUbicaciones)
            ubicaciones.add(u.getNombre() + " - Latitud: " + u.getLatitud() + "° - Longitud: " + u.getLongitud() + "°");

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

