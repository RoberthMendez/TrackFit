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
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ControladorUbicacion extends ControladorCrearRuta {

    @FXML
    private AnchorPane idPantalla;

    @FXML
    private TextField idErrorNombre;

    @FXML
    private Button idAtras;

    @FXML
    private TextField idErrorLatitud;

    @FXML
    private TextField idErrorLongitud;

    @FXML
    private Button idFinalizar;

    @FXML
    private TextField idLatitud;

    @FXML
    private TextField idLongitud;

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

        if(idLatitud.getText().isBlank()){
            idErrorLatitud.setText("La Latitud es obligatoria");
            return;
        }
        idErrorLatitud.clear();

        if(idLongitud.getText().isBlank()){
            idErrorLongitud.setText("La Longitud es obligatoria");
            return;
        }
        idErrorLongitud.clear();

        if(!idLatitud.getText().matches("\\d+\\.?\\d*")){
            idErrorLatitud.setText("La Latitud debe ser un numero");
            return;
        }
        idErrorLatitud.clear();

        if(!idLongitud.getText().matches("\\d+\\.?\\d*")){
            idErrorLongitud.setText("La Longitud debe ser un numero");
            return;
        }
        idErrorLongitud.clear();

        //Agregar la ubicación a la ruta
        try {
            sistema.agregarUbicacionARuta(ruta, idNombre.getText(), Double.parseDouble(idLatitud.getText()), Double.parseDouble(idLongitud.getText()), ubicaciones+1);
        } catch (RutaExc e) {
            idErrorNombre.clear();
            idErrorLatitud.setText("Ya existe una ubicación con esta latitud");
            idErrorLongitud.setText("Ya existe una ubicación con esta longitud");
            idLatitud.clear();
            idLongitud.clear();
            idLatitud.requestFocus();
            return;
        } catch (Exception e){
            idErrorNombre.setText(e.getMessage());
            return;
        }

        idAgregarUbicacion.setText("Se agregó una nueva ubicación");
        idNombre.clear();
        idLatitud.clear();
        idLongitud.clear();

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
