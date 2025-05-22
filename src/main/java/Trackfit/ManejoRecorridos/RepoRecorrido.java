package Trackfit.ManejoRecorridos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepoRecorrido extends JpaRepository<Recorrido, Long> {
    Recorrido findByNombre(String nombre);
}
