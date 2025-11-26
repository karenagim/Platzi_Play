package platzi.play;

import platzi.play.contenido.*;
import platzi.play.excepcion.PeliculaExistenteException;
import platzi.play.plataforma.Plataforma;
import platzi.play.util.ScannerUtils;
import platzi.play.util.FileUtils;


import java.util.List;

public class main {

    public static final String VERSION = "1.0.0"; //constante----------
    public static final String NOMBRE_PLATAFORMA = "Platzi Play ";

    public static final int AGREGAR = 1;
    public static final int MOSTRAR_TODO = 2;
    public static final int BUSCAR_POR_TITULO = 3;
    public static final int BUSCAR_POR_GENERO = 4;
    public static final int VER_POPULARES = 5;
    public static final int REPRODUCIR = 6;
    public static final int ELIMINAR = 8;
    public static final int SALIR = 9;

    public static void main(String[] args) {


        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + "v" + VERSION);

        cargarPeliculas(plataforma);

        System.out.println("Mas de  "+ plataforma.getDuracionTotal() + "duracion de contenido ");

        while (true) {
            int opcionElegida = ScannerUtils.capturarNumero("""
                                        
                  1. Agregar contenido                         
                  2. Mostrar Todo
                  3. Buscar por titulo
                  4. Buscar por genero
                  5. Ver populares
                  6. Reproducir
                  8. Eliminar
                  98. salir
                            """);


            System.out.println("OPCION ELEGIDA : " + opcionElegida);


            switch (opcionElegida) {
                case AGREGAR -> {

                    int tipoDeContenido = ScannerUtils.capturarNumero("Que tipo de contenido quieres agregar? 1. Pelicula \n 2. Documental");
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                   // Genero genero = Genero.valueOf(ScannerUtils.capturarTexto("genero del contenido"));
                    Genero genero = ScannerUtils.capturarGenero("genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificacion del contenido");


                    try{

                        if(tipoDeContenido ==1){
                            /*
                            Contenido pelicula = new Contenido(nombre,duracion,genero,calificacion); // instancio pelicula
                            plataforma.agregar(pelicula);*/
                            // Version corta de lo de arriba pelicula
                            plataforma.agregar(new Pelicula(nombre, duracion, genero, calificacion));
                        }else{
                            String narrador = ScannerUtils.capturarTexto("Narrador del documental ");
                            plataforma.agregar(new Documental(nombre, duracion, genero, calificacion,narrador));
                        }


                    }catch(PeliculaExistenteException e ){
                        System.out.println(e.getMessage());
                    }

                }

                case MOSTRAR_TODO -> //plataforma.mostrarTitulos(); //al ser una sola linea uso lambda directo
               /* {                 version antigua, programacion funcional, ahora lo transformo con RECORDS
                    List<String> titulos = plataforma.getTitulos();
                    titulos.forEach(System.out::println); // funcion de referencia
                }*/

                {
                    List<ResumenContenido> contenidosResumidos = plataforma.getResumenes();
                    contenidosResumidos.forEach(resumen-> System.out.println(resumen.toString()));
                    contenidosResumidos.forEach(resumen-> System.out.println(resumen.titulo().toString()));
                }


                case BUSCAR_POR_TITULO -> {
                    String nombreBuscado = ScannerUtils.capturarTexto("Nombre del contenido a buscar");

                    Contenido contenido = plataforma.buscarPorTitulo(nombreBuscado);

                    if (contenido != null) {
                        System.out.println(contenido.obtenerFichaTecnica());
                    } else {
                        System.out.println(nombreBuscado + "No existe dentro de " + plataforma.getNombre());
                    }
                }

                case BUSCAR_POR_GENERO -> {
                //conviernte un string a un genero
                    Genero generoBuscado = ScannerUtils.capturarGenero("genero del contenido");
                            //Genero.valueOf(ScannerUtils.capturarTexto("genero del contenido a buscar"));

                    List<Contenido> contenidoPorGenero= plataforma.buscarPorGenero(generoBuscado);
                    System.out.println(contenidoPorGenero.size() + " encontrados para el genero " + generoBuscado);
                     contenidoPorGenero.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));

                }

                case VER_POPULARES -> {
                    List<Contenido> contenidoPopulares = plataforma.getPopulares(3);
                    contenidoPopulares.forEach(contenido -> System.out.println(contenido.obtenerFichaTecnica() + "\n"));

                }

                case REPRODUCIR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido a reproducir");
                    Contenido contenido = plataforma.buscarPorTitulo(nombre);

                    if(contenido != null){
                        plataforma.reproducir(contenido);

                    }else{
                        System.out.println(nombre + "no existe dentro de  " + plataforma.getNombre());
                    }

                }



                case ELIMINAR -> {

                    String nombreAElminar = ScannerUtils.capturarTexto("Nombre del contenido a eliminar");
                    Contenido contenido = plataforma.buscarPorTitulo(nombreAElminar);

                    if(contenido != null){
                        plataforma.eliminar(contenido);
                        System.out.println(nombreAElminar + " eliminado !!!");
                    }else{
                        System.out.println(nombreAElminar + "no existe dentro de  " + plataforma.getNombre());
                    }

                }

                case SALIR -> System.exit(0);
            }
        }
    }

    private static void cargarPeliculas(Plataforma plataforma) {
plataforma.getContenido().addAll(FileUtils.leerContenido())
;    }
}


