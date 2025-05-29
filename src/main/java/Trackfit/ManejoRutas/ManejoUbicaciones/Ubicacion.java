package Trackfit.ManejoRutas.ManejoUbicaciones;

import jakarta.persistence.*;

import Trackfit.ManejoExcepciones.UbicacionExc;
import com.gluonhq.maps.MapPoint;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Scanner;


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
    private double latitud;

    @Column(name = "Longitud")
    private double longitud;

    @Transient
    private MapPoint coordenada;
    

    public Ubicacion(String nombre, String direccion) throws UbicacionExc {

        this.nombre = nombre;
        this.direccion = direccion;
        calcularCoordenadas();

    }

    public Ubicacion() {
        
    }

    public void calcularCoordenadas() throws UbicacionExc{
        try{
            String address=URLEncoder.encode(getDireccion(), "UTF-8").replace("+", "%20").replace("%3A", ":").replace("%2F", "/")+ "+Bogota+Colombia";
            
            System.out.println(address);
            System.out.println(address.substring(0, address.length() - 1));
            String Strurl = "https://maps.googleapis.com/maps/api/geocode/json?address="+address+"&key=AIzaSyDMKRQfPE2U1xZI0-e5pbHQ3n7yt0NJzEk";
            URL url = new URL(Strurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            
            int responseCode = conn.getResponseCode();
            if(responseCode != 200){
                throw new UbicacionExc("Error al ingresar al URL de Google: "+ responseCode);
            } else {
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                
                while(scanner.hasNext()){
                    informationString.append(scanner.nextLine());
                }
                scanner.close();
                System.out.println(informationString);

                JSONObject JSONlec = new JSONObject(String.valueOf(informationString));             
                JSONArray resultados = (JSONArray) JSONlec.get("results");
                JSONObject JSON = (JSONObject) resultados.get(0);
                JSONObject Geometry = (JSONObject) JSON.get("geometry");
                JSONObject location = (JSONObject) Geometry.get("location");
                
                BigDecimal latitud = (BigDecimal) location.get("lat");
                BigDecimal longitud = (BigDecimal) location.get("lng");
                
                setLatitud(latitud.doubleValue());
                setLongitud(longitud.doubleValue());
                setCoordenada(new MapPoint(latitud.doubleValue(),longitud.doubleValue())); 

                System.out.println("Latitud calculada: " + latitud);
                System.out.println("Longitud calculada: " + longitud);
                
            }
        }catch(IOException | RuntimeException e){
            e.printStackTrace();
        }
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

    public void setCoordenada(MapPoint coordenada){
        this.coordenada = coordenada; 
    }

    public MapPoint getCoordedana(){
        return coordenada;
    }

    public void setLatitud(double latitud){
        this.latitud = latitud;
    }

    public void setLongitud(double longitud){
        this.longitud = longitud;
    }

    public double getLatitud(){
        return latitud;
    }

    public double getLongitud(){
        return longitud;
    }

}
