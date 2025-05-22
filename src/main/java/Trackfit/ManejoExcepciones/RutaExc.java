package Trackfit.ManejoExcepciones;

public class RutaExc extends Exception {

    private String detalle;

    /**
     * @param detalle
     */
    public RutaExc(String detalle) {
        this.detalle = detalle;
    }

    public String getDetalle() { return detalle; }

    @Override
    public String toString() {
        return "RutaExc [Detalle = " + detalle + "]";
    }

}
