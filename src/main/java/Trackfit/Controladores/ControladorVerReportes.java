package Trackfit.Controladores;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;

import Trackfit.ManejoRecorridos.Recorrido;
import Trackfit.ManejoRecorridos.Reporte;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.collections.FXCollections;



public class ControladorVerReportes extends ControladorMenu {

    @FXML
    private TextField idCalorias;

    @FXML
    private TextField idDistancia;

    @FXML
    private JFXComboBox<String> idIntervalo;

    @FXML
    private AnchorPane idPantalla;

    @FXML
    private JFXListView<Recorrido> idRecorridos;

    @FXML
    private Button idTrackFit;

    @FXML
    void initialize() {

        idRecorridos.setCellFactory(_ -> new JFXListCell<>() {
            @Override
            protected void updateItem(Recorrido item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Interfaces/ItemRecorrido.fxml"));
                        AnchorPane pane = loader.load();
                        ControladorItemRecorrido controller = loader.getController();
                        controller.setDatos(item);
                        setText(null);
                        setGraphic(pane);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        
        // Poner "Semanal", "Mensual", "Trimestral", "Semestral", "Anual" en el comboBox
        idIntervalo.setItems(javafx.collections.FXCollections.observableArrayList(
            "Semanal", "Mensual", "Anual"
        ));

    }


    @FXML
    public void onActionIntervalo(ActionEvent event) {

        // Aquí puedes manejar la acción del comboBox si es necesario
        String intervaloSeleccionado = idIntervalo.getValue();
        Reporte reporte = sistema.crearReporteRecorridos(intervaloSeleccionado);

        idCalorias.setText(String.valueOf(reporte.getCaloriasTotales()));
        idDistancia.setText(String.valueOf(reporte.getDistanciaTotal()));
        idRecorridos.setItems(FXCollections.observableArrayList(reporte.getLr()));


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
