package Ejercicio1;

import java.io.IOException;
import java.util.Scanner;

public class Ej1Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcionSeleccionada, num1, num2;
        String comando = "java src\\Ejercicio1\\Calculadora.java";

        // Muestro el menú y leo la opción escogida
        mostrarMenu();
        opcionSeleccionada = validarOpcion(1, 4);
        comando += " " + opcionSeleccionada;    // Añado la opción seleccionada al primer argumento del comando

        // Leo los dos números con los que se operarán
        System.out.println();
        System.out.println("Introduzca el número 1:");
        num1 = sc.nextInt();
        System.out.println("Introduzca el número 2:");
        num2 = sc.nextInt();

        comando += " " + num1 + " " + num2;     // Añado los dos números a los argumentos del comando

        ProcessBuilder pb = new ProcessBuilder(comando.split(" "));
        pb.inheritIO();     // Heredamos la entrada estándar y la salida estándar del proceso padre.

        try {
            Process process = pb.start();

            int salidaProceso = process.waitFor();  // Recojo el valor devuelto por el proceso

            // Dependiendo de como termine el proceso escribo un mensaje
            if (salidaProceso == 0) {
                System.out.println("El proceso ha finalizado con éxito");
            } else {
                System.out.println("El proceso ha finalizado con el código de error: " + salidaProceso);
            }

        } catch (IOException e) {
            System.err.println("Error durante la ejecución del proceso");
            System.exit(1);     // Indicamos que la ejecución termina con error 1

        } catch (InterruptedException e) {
            System.err.println("Proceso interrumpido");
            System.exit(2);     // Indicamos que la ejecución termina con error 2
        }
    }

    /***
     * Imprime por consola el menú principal
     */
    private static void mostrarMenu() {
        System.out.println();
        System.out.println("-- -- -- -- -- --");
        System.out.println("1 - SUMA");
        System.out.println("2 - RESTA");
        System.out.println("3 - MULTIPLICACIÓN");
        System.out.println("4 - DIVISIÓN");
    }

    /***
     * Valida la entrada de un entero en un rango dado, ambos incluidos
     * @param min Valor mínimo, incluido
     * @param max Valor máximo, incluido
     * @return entero en el rango dado
     */
    private static int validarOpcion(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int opc;
        do {
            System.out.print("==> ");
            opc = sc.nextInt();
        } while (opc < min || opc > max);
        return opc;
    }
}
