package Trackfit.ManejoRutas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepoRutaXUbi extends JpaRepository<RutaXUbicacion, Long> {
    List<RutaXUbicacion> findByRutaIdOrderByOrdenAsc(Long rutaId);
}