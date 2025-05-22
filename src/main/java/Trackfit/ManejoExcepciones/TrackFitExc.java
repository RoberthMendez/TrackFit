package Trackfit.ManejoExcepciones;

public class TrackFitExc extends Exception {

    private String detalle;

    /**
     * @param detalle
     */
    public TrackFitExc(String detalle) {
        this.detalle = detalle;
    }

    public String getDetalle() { return detalle; }

    @Override
    public String toString() {
        return "TrackFitExc [Detalle = " + detalle + "]";
    }

}
