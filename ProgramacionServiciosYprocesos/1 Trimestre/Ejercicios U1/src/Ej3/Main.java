package Ej3;

import java.io.IOException;
import java.util.Scanner;

/***
 * Crea una clase Java que lance un proceso haciendo uso de la clase ProcessBuilder.
 * En vez de tomar el comando por los argumentos del main,
 * debe solicitarle al usuario por consola que introduzca el comando/programa a ejecutar.
 */
public class Main {
    public static void main(String[] args) {
        String[] comando;
        Scanner sc = new Scanner(System.in);

        System.out.print("Ingrese el comando que desea lanzar: ");

        comando = sc.nextLine().split(" ");
        ProcessBuilder pb = new ProcessBuilder(comando);
        pb.inheritIO();
        try {
            Process p = pb.start();
            p.waitFor();
            System.out.println("El proceso ha terminado");
        } catch (IOException e) {
            System.err.println("Error de escritura o lectura: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Proceso interrumpido: " + e.getMessage());
        }
    }
}
