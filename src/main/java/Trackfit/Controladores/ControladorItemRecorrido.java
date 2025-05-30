package Trackfit.Controladores;

import Trackfit.ManejoRecorridos.Recorrido;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ControladorItemRecorrido {

    @FXML
    private Text idCalorías;

    @FXML
    private Text idDistancia;

    @FXML
    private Text idHoraFin;

    @FXML
    private Text idHoraInicio;

    @FXML
    private Text idRecorrido;

    @FXML
    private Text idRuta;

    public void setDatos(Recorrido recorrido) {

        idRecorrido.setText(recorrido.getNombre());
        if (recorrido.getRuta() != null) {
            idRuta.setText(recorrido.getRuta().getNombre());
        } else {
            idRuta.setText("RutaX");
        }
        idHoraInicio.setText("Hora Inicio: " + recorrido.getHoraInicio());
        idHoraFin.setText("Hora Fin: " + recorrido.getHoraFin());
        idDistancia.setText("Distancia: " + recorrido.getDistancia() + " km");
        idCalorías.setText("Calorías: " + recorrido.getCalorias() + " kcal");

    }

}