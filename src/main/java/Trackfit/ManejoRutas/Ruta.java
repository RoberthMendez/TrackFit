package Trackfit.ManejoRutas;


import Trackfit.ManejoExcepciones.RutaExc;

import java.util.ArrayList;
import java.util.List;

import Trackfit.ManejoRutas.ManejoUbicaciones.Ubicacion;
import jakarta.persistence.*;

@Entity
@Table(name="Ruta")
public class Ruta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "Nombre")
    private String nombre;

    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RutaXUbicacion> lu;


    public Ruta(String nombre) {

        this.nombre = nombre;
        lu = new ArrayList<>();

    }

    public Ruta() {

    }

    public void agregarUbicacion(Ubicacion ubicacion, int orden) throws RutaExc{
        RutaXUbicacion u = buscarUbicacion(ubicacion);
        if (u == null){
            u = new RutaXUbicacion(this, ubicacion, orden);
            lu.add(u);
        }
        else
            throw new RutaExc("La ubicacion ya se agregó");
    }

    public RutaXUbicacion buscarUbicacion(Ubicacion ubicacion) throws RutaExc {
        Ubicacion ubi;
        for (RutaXUbicacion u: lu) {
            if (u != null){
                ubi = u.getUbicacion();
                if (ubi.getDireccion() == ubicacion.getDireccion())
                    return u;
            }

        }
        return null;
    }



    public List<Ubicacion> getUbicaciones(){
        List<Ubicacion> ubis = new ArrayList<>();
        for(RutaXUbicacion u: lu)
            ubis.add(u.getUbicacion());
        return ubis;
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

    public List<RutaXUbicacion> getLu() {
        return lu;
    }

    public void setLu(List<RutaXUbicacion> lu) {
        this.lu = lu;
    }

}
