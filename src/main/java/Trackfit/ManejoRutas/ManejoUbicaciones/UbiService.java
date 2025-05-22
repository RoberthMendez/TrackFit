package Trackfit.ManejoRutas.ManejoUbicaciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbiService {

    @Autowired
    private RepoUbi ubicacionRepository;

    public void insertarUbicacion(Ubicacion ubicacion) {

        try {
            ubicacionRepository.save(ubicacion);
            System.out.println("Ubicación guardada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al guardar la ubicación: " + e.getMessage());
        }
    }

    public List<Ubicacion> obtenerTodasLasUbicaciones() {
        return ubicacionRepository.findAll();
    }
}
