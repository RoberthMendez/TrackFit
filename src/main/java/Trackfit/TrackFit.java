package Trackfit;

import Trackfit.ManejoExcepciones.RutaExc;
import Trackfit.ManejoExcepciones.TrackFitExc;
import Trackfit.ManejoRecorridos.Recorrido;
import Trackfit.ManejoRecorridos.RecorridoService;
import Trackfit.ManejoRutas.ManejoUbicaciones.UbiService;
import Trackfit.ManejoRutas.ManejoUbicaciones.Ubicacion;
import Trackfit.ManejoRutas.Ruta;
import Trackfit.ManejoRutas.RutaService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



public class TrackFit {

    private static List<Ruta> rutas;
    private static List<Recorrido> recorridos;
    private static RutaService rutaService;
    private static RecorridoService recorridoService;
    private static UbiService ubiService;

    public TrackFit() {
        rutas = new ArrayList<>();
        recorridos = new ArrayList<>();
        cargarDatos();
    }

    public static void cargarDatos()  {
        // Obtener todas las rutas
        rutas = rutaService.obtenerTodasLasRutas();
        System.out.println("Rutas cargadas: ");
        for (Ruta ruta : rutas) {
            System.out.println(ruta.getNombre());
        }

        // Obtener todos los recorridos
        recorridos = recorridoService.obtenerTodosLosRecorridos();
        System.out.println("Recorridos cargados: ");
        for (Recorrido recorrido : recorridos) {
            System.out.println(recorrido.getNombre());
        }


    }

    public List<String> getNombresRutas() {

        List<String> nombres = new ArrayList<>();

        for(Ruta r: rutas)
            nombres.add(r.getNombre());

        return nombres;

    }

    public List<String> getNombresRecorridos() {

        List<String> nombres = new ArrayList<>();

        for(Recorrido r: recorridos)
            nombres.add(r.getNombre());

        return nombres;

    }


    public void crearRuta(Ruta ruta) {
        if (ruta != null){
            rutas.add(ruta);
            guardarRutaEnBd(ruta);
        }
    }

    public void agregarUbicacionARuta(Ruta r, String nomUbicacion, String direccion, int orden) throws RutaExc {

        Ubicacion ubicacion =  new Ubicacion(nomUbicacion, direccion); 

        

        r.agregarUbicacion(ubicacion, orden);
        guardarUbicacionEnBD(ubicacion);

    }

    public void crearRecorrido(String nomRecorrido, String nomRuta, LocalDateTime horaInicio, LocalDateTime horaFin, int calorias, int distancia) throws TrackFitExc{

        if (horaFin.isBefore(horaInicio))
            throw new TrackFitExc("No se encontró la ruta");

        Ruta ruta = buscarRuta(nomRuta);
        Recorrido recorrido = buscarRecorrido(nomRecorrido);

        if (recorrido == null){
            Recorrido r = new Recorrido(nomRecorrido, ruta, horaInicio, horaFin, calorias, distancia);
            recorridos.add(r);
            guardarRecorridoEnBd(r);
        }

        else
            throw new TrackFitExc("Ya existe un recorrido con este nombre");

    }

    public Ruta buscarRuta(String nombre) {

        for (Ruta r: rutas)
            if (r != null)
                if (r.getNombre().equalsIgnoreCase(nombre))
                    return r;

        return null;

    }

    public Recorrido buscarRecorrido(String nombre) {

        for (Recorrido r: recorridos)
            if (r != null)
                if (r.getNombre().equalsIgnoreCase(nombre))
                    return r;

        return null;

    }


    public List<Ruta> getRutas() {
        return rutas;
    }

    public void setRutas(List<Ruta> rutas) {
        TrackFit.rutas = rutas;
    }

    public List<Recorrido> getRecorridos() {
        return recorridos;
    }

    public void setRecorridos(List<Recorrido> recorridos) {
        TrackFit.recorridos = recorridos;
    }

    public static void setRutaService(RutaService service) {
        rutaService = service;
    }
    public static void setUbiService(UbiService service) {
        ubiService = service;
    }
    public static void setRecorridoService(RecorridoService service) {
        recorridoService = service;
    }

    public void guardarRutaEnBd(Ruta ruta) {
        if (rutaService != null) {
            rutaService.insertarRuta(ruta);
        } else {
            System.err.println("Servicio de Ruta no inicializado.");
        }
    }
    public void guardarRecorridoEnBd(Recorrido recorrido) {
        if (recorridoService != null) {
            recorridoService.insertarRecorrido(recorrido);
        } else {
            System.err.println("Servicio de Recorrido no inicializado.");
        }
    }
    public void guardarUbicacionEnBD(Ubicacion ubicacion){
        if(ubiService != null){
            ubiService.insertarUbicacion(ubicacion);
        } else {
            System.err.println("Servicio de Ubicacion no inicializado.");
        }
    }

}
