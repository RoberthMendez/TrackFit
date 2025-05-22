package Trackfit.ManejoRutas.ManejoUbicaciones;

import jakarta.persistence.*;

@Entity
@Table(name = "Ubicacion")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Latitud")
    private double latitud;

    @Column(name = "Longitud")
    private double longitud;

    public Ubicacion(String nombre, double latitud, double longitud) {

        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Ubicacion() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

}
