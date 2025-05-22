package Trackfit;

import Trackfit.ManejoRecorridos.RecorridoService;
import Trackfit.ManejoRutas.ManejoUbicaciones.UbiService;
import Trackfit.ManejoRutas.RutaService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TestTrackFit extends Application {

    private static ApplicationContext context;
    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader loader = new FXMLLoader(TestTrackFit.class.getResource("/Interfaces/Menu.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setTitle("TrackFit");
        stage.setScene(scene);
        stage.setOnCloseRequest(_ -> {
            System.exit(0);
        });
        stage.show();

    }

    public static void main(String[] args) {
        context = SpringApplication.run(TestTrackFit.class, args);

        UbiService ubiService = context.getBean(UbiService.class);
        RutaService rutaService = context.getBean(RutaService.class);
        RecorridoService recorridoService = context.getBean(RecorridoService.class);

        TrackFit.setUbiService(ubiService);
        TrackFit.setRutaService(rutaService);
        TrackFit.setRecorridoService(recorridoService);

        launch();
    }
}