package Trackfit.ManejoRecorridos;


import java.util.List;

public class Reporte {

    private int distanciaTotal;
    private int caloriasTotales;
    private List<Recorrido> lr;

    public Reporte(int totalDistancia, int caloriasTotales, List<Recorrido> lr) {

        this.distanciaTotal = totalDistancia;
        this.caloriasTotales = caloriasTotales;
        this.lr = lr;
    }

    public int getDistanciaTotal() {
        return distanciaTotal;
    }

    public void setDistanciaTotal(int distanciaTotal) {
        this.distanciaTotal = distanciaTotal;
    }

    public int getCaloriasTotales() {
        return caloriasTotales;
    }

    public void setCaloriasTotales(int caloriasTotales) {
        this.caloriasTotales = caloriasTotales;
    }

    public List<Recorrido> getLr() {
        return lr;
    }

    public void setLr(List<Recorrido> lr) {
        this.lr = lr;
    }

}
