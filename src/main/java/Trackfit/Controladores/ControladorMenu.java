package Trackfit.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import Trackfit.TrackFit;

import java.io.IOException;

public class ControladorMenu {

    @FXML
    private AnchorPane idPantalla;

    @FXML
    private Button idConsultarRecorrido;

    @FXML
    private Button idConsultarRutas;

    @FXML
    private Button idCrearRecorrido;

    @FXML
    private Button idCrearRuta;

    @FXML
    private Button idVerReporte;

    protected static TrackFit sistema = new TrackFit();

    @FXML
    void onActionCrearRuta(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/CrearRuta.fxml"));
        idPantalla = loader.load();

        Scene secondScene = new Scene(idPantalla);

        Stage stage = (Stage) idConsultarRecorrido.getScene().getWindow();
        stage.setScene(secondScene);
        stage.show();

    }

    @FXML
    void onActionConsultarRecorrido(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/ConsultarRecorridos.fxml"));
        idPantalla = loader.load();

        Scene secondScene = new Scene(idPantalla);

        Stage stage = (Stage) idConsultarRecorrido.getScene().getWindow();
        stage.setScene(secondScene);
        stage.show();

    }

    @FXML
    void onActionConsultarRutas(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/ConsultarRuta.fxml"));
        idPantalla = loader.load();

        Scene secondScene = new Scene(idPantalla);

        Stage stage = (Stage) idConsultarRecorrido.getScene().getWindow();
        stage.setScene(secondScene);
        stage.show();

    }

    @FXML
    void onActionCrearRecorrido(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/CrearRecorrido.fxml"));
        idPantalla = loader.load();

        Scene secondScene = new Scene(idPantalla);

        Stage stage = (Stage) idConsultarRecorrido.getScene().getWindow();
        stage.setScene(secondScene);
        stage.show();

    }

    @FXML
    void onActionVerReportes(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/VerReportes.fxml"));
        idPantalla = loader.load();

        Scene secondScene = new Scene(idPantalla);

        Stage stage = (Stage) idConsultarRecorrido.getScene().getWindow();
        stage.setScene(secondScene);
        stage.show();


    }

}

