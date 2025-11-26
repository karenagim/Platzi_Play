package platzi.play.util;

import platzi.play.contenido.Genero;

import java.util.Scanner;

public class ScannerUtils {

    public static Scanner scanner= new Scanner(System.in); //permite definir que este atributo no depende ningun objeto sino de la clase en general


    public static String capturarTexto (String mensaje){

        System.out.println(mensaje + ": ");
        return scanner.nextLine();
    }

    public static int capturarNumero (String mensaje){

         System.out.println(mensaje + ": ");

         while( !scanner.hasNextInt()){
             System.out.println("Dato no valido " + mensaje + " :");
             scanner.next();//descartamos esa entrada invalida
         }



         int dato = scanner.nextInt(); // capturo el dato
         scanner.nextLine(); // mientras lo capturo debo despreciar el enter para que solo tenga en cuenta el numero

        return dato;
    }

    public static double capturarDecimal (String mensaje){

        System.out.println(mensaje + ": ");
        while( !scanner.hasNextDouble()){
            System.out.println("Dato no valido " + mensaje + " :");
            scanner.next();//descartamos esa entrada invalida
        }

        double dato =  scanner.nextDouble() ; // capturo el dato
        scanner.nextLine(); // mientras lo capturo debo despreciar el enter para que solo tenga en cuenta el numero

        return dato;
    }


    public static Genero capturarGenero(String mensaje){
        while(true){

            System.out.println(mensaje + " ... Opciones: ");

            for(Genero genero : Genero.values()){
                System.out.println("-" + genero.name());
            }

            System.out.println("Cual quieres? ");
            //String entrada = capturarTexto(mensaje);
            String entrada = scanner.nextLine();


            try{
                return Genero.valueOf(entrada.toUpperCase());
            }catch(IllegalArgumentException e ){
                System.out.println("Genero no aceptado " + mensaje + ": ");
            }
        }
    }

}
