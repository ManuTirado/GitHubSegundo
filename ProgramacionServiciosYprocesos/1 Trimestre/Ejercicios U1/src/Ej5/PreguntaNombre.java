package Ej5;

import java.util.Scanner;

/***
 * Crea una clase Java llamada PreguntaNombre.java.
 * Esta clase debe preguntarle al usuario su nombre, el cual debe introducirlo por la entrada estándar,
 * e imprimir en la salida estándar “¡Hola nombre!”, donde nombre es el nombre introducido por teclado.
 * Crea otra clase que se encargue de lanzar PreguntaNombre.java como un proceso.
 * Además, redirecciona la entrada estándar para que se lea desde un fichero.
 * La salida del proceso debe ser la estándar, es decir, la pantalla.
 */
public class PreguntaNombre {
    public static void main(String[] args) {
        String nombre;
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese su nombre: ");
        nombre = sc.nextLine();

        System.out.printf("\n¡Hola %s!",nombre);

        sc.close();
    }
}
