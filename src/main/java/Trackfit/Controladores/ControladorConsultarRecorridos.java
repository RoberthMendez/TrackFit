package Trackfit.Controladores;

import java.io.IOException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXListCell;
import com.jfoenix.controls.JFXListView;

import Trackfit.ManejoRecorridos.Recorrido;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import java.net.URL;

import javafx.scene.Scene;
import javafx.scene.control.Button;

public class ControladorConsultarRecorridos implements Initializable {

    @FXML
    private JFXListView<Recorrido> idRecorridos;

    @FXML
    private Button idTrackFit;

    @FXML
    private AnchorPane idPantalla;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Supongamos que tienes una lista de recorridos:
        ObservableList<Recorrido> recorridos = FXCollections.observableArrayList(
            new Recorrido("Recorrido 1", null, null, null, 500, 10),
            new Recorrido("Recorrido 2", null, null, null, 600, 12),
            new Recorrido("Recorrido 3", null, null, null, 700, 15)
            // ... otros recorridos
        );

        // Renderizar con celdas personalizadas
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

        idRecorridos.setItems(recorridos);
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


