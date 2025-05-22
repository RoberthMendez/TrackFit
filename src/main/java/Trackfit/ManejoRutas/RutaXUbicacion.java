package Trackfit.ManejoRutas;

import Trackfit.ManejoRutas.ManejoUbicaciones.Ubicacion;
import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "RutaXUbicacion")
public class RutaXUbicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "ruta_id")
    private Ruta ruta;

    @ManyToOne
    @JoinColumn(name = "ubicacion_id")
    private Ubicacion ubicacion;

    @Column(name = "orden")
    private int orden;

    public RutaXUbicacion() {
    }

    public RutaXUbicacion(Ruta ruta, Ubicacion ubicacion, int orden) {
        this.ruta = ruta;
        this.ubicacion = ubicacion;
        this.orden = orden;
    }

    public long getId() {
        return id;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }
}
