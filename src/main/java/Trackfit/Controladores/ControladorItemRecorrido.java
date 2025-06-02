package Trackfit.Controladores;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

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

        // Asegurarse de que el recorrido no sea nulo
        if (recorrido == null) {
            throw new IllegalArgumentException("El recorrido no puede ser nulo");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d 'de' MMMM 'a las' HH:mm", Locale.forLanguageTag("es-ES"));
        
        idRecorrido.setText(recorrido.getNombre().toUpperCase());
        idRuta.setText("Ruta: " + recorrido.getRuta().getNombre());
        idHoraInicio.setText("Inicio: " + recorrido.getHoraInicio().format(formatter));
        idHoraFin.setText("Fin: " + recorrido.getHoraFin().format(formatter));
        idDistancia.setText("Distancia: " + recorrido.getDistancia() + " km");
        idCalorías.setText("Calorías: " + recorrido.getCalorias() + " kcal");

    }

}