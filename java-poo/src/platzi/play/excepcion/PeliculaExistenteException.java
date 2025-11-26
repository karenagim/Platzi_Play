package platzi.play.excepcion;

//permite representar errores en tiempo de ejecucion
public class PeliculaExistenteException extends RuntimeException{

    public PeliculaExistenteException(String titulo){
        super("La pelicula " + titulo + "ya existe");
    }
}
