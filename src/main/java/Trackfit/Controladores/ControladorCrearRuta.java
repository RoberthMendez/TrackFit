package Trackfit.Controladores;

import Trackfit.ManejoRutas.Ruta;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorCrearRuta extends ControladorMenu {

    @FXML
    private AnchorPane idPantalla;

    @FXML
    private TextField idNombreRuta;

    @FXML
    private TextField idErrorNombre;

    @FXML
    private Button idTrackFit;

    protected static Ruta ruta;

    @FXML
    void initialize() {
        Platform.runLater(() -> idPantalla.requestFocus());
    }

    @FXML
    void onActionContinuar() throws IOException {

        if(idNombreRuta.getText().isBlank()){
            idErrorNombre.setText("El Nombre de la Ruta es obligatorio");
            idNombreRuta.requestFocus();
            return;
        }

        if(sistema.buscarRuta(idNombreRuta.getText()) != null){
            idErrorNombre.setText("Ya existe una ruta con ese nombre");
            idNombreRuta.requestFocus();
            return;
        }

        idErrorNombre.setText("");
        ruta = new Ruta(idNombreRuta.getText());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/AgregarUbicacion.fxml"));
        idPantalla = loader.load();

        Scene secondScene = new Scene(idPantalla);
        Stage stage = (Stage) idTrackFit.getScene().getWindow();
        stage.setScene(secondScene);
        stage.show();

    }

    @FXML
    void onActionTrackFit() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/Menu.fxml"));
        idPantalla = loader.load();

        Scene secondScene = new Scene(idPantalla);
        Stage stage = (Stage) idTrackFit.getScene().getWindow();
        stage.setScene(secondScene);
        stage.show();

    }

}
