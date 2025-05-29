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

    @Column(name = "Direccion")
    private String direccion;

    @Column(name = "Latitud")
    private int latitud;

    @Column(name = "Longitud")
    private int longitud;
    

    public Ubicacion(String nombre, String direccion) {

        this.nombre = nombre;
        this.direccion = direccion;

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
