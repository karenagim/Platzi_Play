package platzi.play.plataforma;
import platzi.play.contenido.Contenido;

import java.time.LocalDateTime;


public class Usuario {
    public String nombre;
    public String email;
    public LocalDateTime fechaRegistro;


    public Usuario(String nombre, String email ) {
        this.nombre = nombre;
        this.email = email;
        this.fechaRegistro = LocalDateTime.now();
    }

    public void ver(Contenido contenido){
        System.out.println(nombre + " esta viendo ..." + contenido.getTitulo());
        contenido.reproducir();
    }
}
