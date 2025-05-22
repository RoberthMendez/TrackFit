package Trackfit.ManejoRecorridos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecorridoService {

    @Autowired
    private RepoRecorrido recorridoRepository;

    public void insertarRecorrido(Recorrido recorrido) {
        try {
            recorridoRepository.save(recorrido);
            System.out.println("Recorrido guardado correctamente: " + recorrido.getNombre());
        } catch (Exception e) {
            System.err.println("Error al guardar el recorrido: " + e.getMessage());
        }
    }

    public Recorrido buscarRecorridoPorNombre(String nombre) {
        return recorridoRepository.findByNombre(nombre);
    }

    public List<Recorrido> obtenerTodosLosRecorridos() {
        return recorridoRepository.findAll();
    }

    public void eliminarRecorrido(long id) {
        try {
            recorridoRepository.deleteById(id);
            System.out.println("Recorrido eliminado correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar el recorrido: " + e.getMessage());
        }
    }
}