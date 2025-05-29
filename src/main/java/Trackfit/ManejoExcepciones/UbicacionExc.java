package Trackfit.ManejoExcepciones;

public class UbicacionExc extends Exception {

    private String detalle;

    /**
     * @param detalle
     */
    public UbicacionExc(String detalle) {
        this.detalle = detalle;
    }

    public String getDetalle() { return detalle; }

    @Override
    public String toString() {
        return "UbicacionExc [Detalle = " + detalle + "]";
    }

}