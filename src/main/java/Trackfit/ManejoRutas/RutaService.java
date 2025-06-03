package Trackfit.ManejoRutas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RutaService {

    @Autowired
    private RepoRuta rutaRepository;

    @Autowired
    private RepoRutaXUbi rutaXUbiRepository;

    public void insertarRuta(Ruta ruta) {
        try {
            rutaRepository.save(ruta);
            System.out.println("Ruta guardada correctamente: " + ruta.getNombre());
        } catch (Exception e) {
            System.err.println("Error al guardar la ruta: " + e.getMessage());
        }
    }

    public Ruta buscarRutaPorNombre(String nombre) {
        return rutaRepository.findByNombre(nombre);
    }

    public List<Ruta> obtenerTodasLasRutas() {
        List<Ruta> rutas = rutaRepository.findAll();
        List<RutaXUbicacion> rutaXUbicaciones = rutaXUbiRepository.findAll();
        boolean agregado = false;

        for(RutaXUbicacion rutaXUbicacion : rutaXUbicaciones) {
            for(Ruta ruta: rutas){
                if (ruta.getId() == rutaXUbicacion.getRuta().getId()) {
                    agregado = true;
                    break;
                }
            }
            if(!agregado) rutas.add(rutaXUbicacion.getRuta());
        }

        return rutas;
    }

    public void eliminarRuta(long id) {
        try {
            rutaRepository.deleteById(id);
            System.out.println("Ruta eliminada correctamente.");
        } catch (Exception e) {
            System.err.println("Error al eliminar la ruta: " + e.getMessage());
        }
    }
}