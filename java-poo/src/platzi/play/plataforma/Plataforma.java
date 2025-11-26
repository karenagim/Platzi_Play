package platzi.play.plataforma;

import platzi.play.contenido.Contenido;
import platzi.play.contenido.Genero;
import platzi.play.contenido.ResumenContenido;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.util.FileUtils;

import java.util.*;

public class Plataforma {

    private String nombre;

    private List<Contenido> contenido; // lista de Peliculass
    private Map<Contenido, Integer> visualizaciones;



    public Plataforma(String nomnbre) {
        this.nombre = nomnbre;
        this.contenido= new ArrayList<>(); //inicializado el array - clase hija de List
        this.visualizaciones = new HashMap<>(); // es una clase hija de Map
    }


    public void agregar(Contenido elemento){

        Contenido contenido = this.buscarPorTitulo(elemento.getTitulo());
        if(contenido != null ){
            throw new PeliculaExistenteException(elemento.getTitulo());
        }

        FileUtils.escribirContenido(elemento);
        this.contenido.add(elemento);
    }


    public void reproducir(Contenido contenido){

        //getOrDefault -> RECIBE EL KEY Y ME DA UN VALOR POR DEFECTO SI NO LO ENCUENTRA
        int conteoActual = visualizaciones.getOrDefault(contenido, 0 );
        System.out.println(contenido.getTitulo() + " ha sido reproducido" + conteoActual + "veces");
        //visualizaciones.put(contenido,conteoActual + 1); lo modularizo con ' contarVisualizacion

        this.contarVisualizacion(contenido);
        contenido.reproducir();
    }

    private void contarVisualizacion(Contenido contenido){

        int conteoActual = visualizaciones.getOrDefault(contenido, 0 );
        visualizaciones.put(contenido,conteoActual +1);
    }
    public List<String> getTitulos(){

        return contenido.stream()
                .map(Contenido:: getTitulo)//.map(contenido -> contenido.getTitulo())   -- METODO DE REFERENCIA QUE LLAMO DESDE LA CLASE
                .toList();  // lo retornamos en una lista de stings
    }

    public List<ResumenContenido> getResumenes(){
        return contenido.stream()
                .map(contenido -> new ResumenContenido(contenido.getTitulo(), contenido.getDuracion(),contenido.getGenero()))
                .toList();
    }

    public int getDuracionTotal(){

        return contenido.stream()
                .mapToInt(Contenido:: getDuracion)//.map(contenido -> contenido.getTitulo())   -- METODO DE REFERENCIA QUE LLAMO DESDE LA CLASE
                .sum();  // lo retornamos en una lista de stings
    }

    public List<Contenido> getPopulares(int cantidad){
          return contenido.stream()
                .sorted(Comparator.comparingDouble(Contenido:: getCalificacion).reversed())
                  .limit(cantidad)
                  .toList();

    }


    public void mostrarTitulos(){

        /* -----------------------recorrido con FOR --------------------------------------------------
        for (int i = 0; i < contenido.size(); i++) {{
            System.out.println(contenido.get(i).getTitulo());
        }------------------------------------ version con foreach -----------------------
        for(Contenido pelicula: contenido){
            System.out.println(pelicula.getTitulo());
        }
        */
        contenido.forEach(contenido -> System.out.println(contenido.getTitulo()));
    }


    public void eliminar(Contenido elemento){
        this.contenido.remove(elemento);
    }

    public Contenido buscarPorTitulo(String titulo){
       /* for(Contenido pelicula: contenido){
            if(pelicula.getTitulo().equalsIgnoreCase(titulo)){
                return pelicula;
            }
        }*/
        return contenido.stream().filter(contenido-> contenido.getTitulo().equalsIgnoreCase(titulo))
                .findFirst()
                .orElse(null); // permite rfecorrer una lista y aplicar acciones especifica sobre sus elementos

    }
    public List <Contenido> buscarPorGenero(Genero genero){
        return contenido.stream().filter(contenido -> contenido.getGenero().equals(genero))
                .toList();
    }
    public String getNombre() {
        return nombre;
    }

    public List<Contenido> getContenido() {
        return contenido;
    }
}
