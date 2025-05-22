package Trackfit.Controladores;

import Trackfit.ManejoExcepciones.TrackFitExc;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ControladorCrearRecorrido extends ControladorMenu {

    @FXML
    private AnchorPane idPantalla;

    @FXML
    private TextField idCalorias;

    @FXML
    private Button idCrear;

    @FXML
    private TextField idDistancia;

    @FXML
    private TextField idErrorCalorias;

    @FXML
    private TextField idErrorDistancia;

    @FXML
    private TextField idErrorHoraFin;

    @FXML
    private TextField idErrorHoraInicio;

    @FXML
    private TextField idErrorNombre;

    @FXML
    private TextField idErrorRuta;

    @FXML
    private TextField idHoraFin;

    @FXML
    private TextField idHoraInicio;

    @FXML
    private TextField idNombre;

    @FXML
    private Button idTrackFit;

    @FXML
    private JFXComboBox<String> idRutas;

    @FXML
    void initialize() {

        idRutas.setItems(FXCollections.observableArrayList(sistema.getNombresRutas()));

    }

    @FXML
    void onActionCrear(ActionEvent event) throws IOException {

        if(idRutas.getValue() == null){
            idErrorRuta.setText("Debe seleccionar una ruta");
            return;
        }
        idErrorRuta.clear();

        if(idNombre.getText().isBlank()){
            idErrorNombre.setText("El Nombre del Recorrido es obligatorio");
            return;
        }
        idErrorNombre.clear();

        if(idHoraInicio.getText().isBlank()){
            idErrorHoraInicio.setText("La Hora de Inicio es obligatoria");
            return;
        }
        idErrorHoraInicio.clear();

        if(idHoraFin.getText().isBlank()){
            idErrorHoraFin.setText("La Hora de Fin es obligatoria");
            return;
        }
        idErrorHoraFin.clear();

        if(idDistancia.getText().isBlank()){
            idErrorDistancia.setText("La Distancia es obligatoria");
            return;
        }

        if(idCalorias.getText().isBlank()){
            idErrorCalorias.setText("Las Calorias son obligatorias");
            return;
        }

        if(!idHoraInicio.getText().matches("\\d+:\\d+")){
            idErrorHoraInicio.setText("La Hora de Inicio debe estar en formato HH:mm");
            return;
        }

        if(!idHoraFin.getText().matches("\\d+:\\d+")){
            idErrorHoraFin.setText("La Hora de Fin debe estar en formato HH:mm");
            return;
        }
        idErrorHoraFin.clear();

        if(!idDistancia.getText().matches("\\d+\\.?\\d*")){
            idErrorDistancia.setText("La Distancia debe ser un numero");
            return;
        }
        idErrorDistancia.clear();

        if(!idCalorias.getText().matches("\\d+\\.?\\d*")){
            idErrorCalorias.setText("Las Calorias deben ser un numero");
            return;
        }
        idErrorCalorias.clear();

        //Crear LocalDateTime de las horas de inicio y fin
        LocalDate fecha = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        LocalDateTime horaInicio = LocalDateTime.of(fecha,LocalTime.parse(idHoraInicio.getText(), formatter));
        LocalDateTime horaFin = LocalDateTime.of(fecha,LocalTime.parse(idHoraFin.getText(), formatter));

        if(horaInicio.isAfter(horaFin)){
            idErrorHoraInicio.setText("La Hora de Inicio debe estar antes de la Hora de Fin");
            return;
        }
        idErrorHoraInicio.clear();

        try {
            sistema.crearRecorrido(idNombre.getText(), idRutas.getValue(), horaInicio, horaFin, Integer.parseInt(idCalorias.getText()), Integer.parseInt(idDistancia.getText()));
        } catch (TrackFitExc e) {
            idErrorNombre.setText(e.getDetalle());
            return;
        } catch (Exception e){
            idErrorNombre.setText(e.getMessage());
            return;
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/PopUpRecorrido.fxml"));
        AnchorPane ap = loader.load();

        Stage popUp = new Stage();
        popUp.initModality(Modality.APPLICATION_MODAL);
        popUp.initOwner(((Node) event.getSource()).getScene().getWindow());
        popUp.setScene(new Scene(ap));

        popUp.setTitle("Recorrido Creado");
        popUp.showAndWait();

        loader = new FXMLLoader(getClass().getResource("/Interfaces/Menu.fxml"));
        idPantalla = loader.load();

        Scene secondScene = new Scene(idPantalla);

        Stage stage = (Stage) idCrear.getScene().getWindow();
        stage.setScene(secondScene);
        stage.show();

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
