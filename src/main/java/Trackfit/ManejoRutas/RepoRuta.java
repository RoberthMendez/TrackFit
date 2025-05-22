package Trackfit.ManejoRutas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoRuta extends JpaRepository<Ruta, Long> {
    Ruta findByNombre(String nombre);
}