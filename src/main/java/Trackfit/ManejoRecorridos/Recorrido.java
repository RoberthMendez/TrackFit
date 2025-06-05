package Trackfit.ManejoRecorridos;

import Trackfit.ManejoRutas.Ruta;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "Recorrido")
public class Recorrido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "Nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "ruta_id")
    private Ruta ruta;

    @Column(name = "HoraInicio")
    private LocalDateTime horaInicio;
    @Column(name = "HoraFin")
    private LocalDateTime horaFin;
    @Column(name = "Calorias")
    private int calorias;
    @Column(name = "Distancia")
    private int distancia;

    public Recorrido(String nombre, Ruta ruta, LocalDateTime horaInicio, LocalDateTime horaFin, int calorias, int distancia) {

        this.nombre = nombre;
        this.ruta = ruta;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.calorias = calorias;
        this.distancia = distancia;

    }

    public Recorrido() {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

}
