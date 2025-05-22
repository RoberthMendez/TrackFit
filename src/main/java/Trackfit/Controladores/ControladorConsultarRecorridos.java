package Trackfit.Controladores;

import Trackfit.ManejoRecorridos.Recorrido;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorConsultarRecorridos extends ControladorMenu {

    @FXML
    private TextField idCalorias;

    @FXML
    private TextField idDistancia;

    @FXML
    private TextField idHoraFin;

    @FXML
    private TextField idHoraInicio;

    @FXML
    private TextField idRuta;

    @FXML
    private AnchorPane idPantalla;

    @FXML
    private JFXComboBox<String> idRecorridos;

    @FXML
    private Button idTrackFit;

    @FXML
    void initialize() {

        idRecorridos.setItems(FXCollections.observableArrayList(sistema.getNombresRecorridos()));

    }

    @FXML
    void onActionRecorridos(ActionEvent event) {

        Recorrido recorrido = sistema.buscarRecorrido(idRecorridos.getValue());
        idHoraInicio.setText(recorrido.getHoraInicio().toString());
        idHoraFin.setText(recorrido.getHoraFin().toString());
        idDistancia.setText(String.valueOf(recorrido.getDistancia()));
        idCalorias.setText(String.valueOf(recorrido.getCalorias()));
        idRuta.setText(recorrido.getRuta().getNombre());

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
