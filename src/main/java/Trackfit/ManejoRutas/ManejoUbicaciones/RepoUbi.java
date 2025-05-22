package Trackfit.ManejoRutas.ManejoUbicaciones;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoUbi extends JpaRepository<Ubicacion, Long> {
}
