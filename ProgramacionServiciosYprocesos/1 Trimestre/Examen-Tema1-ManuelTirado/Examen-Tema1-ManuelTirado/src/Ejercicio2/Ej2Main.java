package Ejercicio2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ej2Main {

    public static void main(String[] args) {
        File alumnosInput = new File("src\\Ejercicio2\\alumnos.txt");
        File alumnosOutput = new File("src\\Ejercicio2\\alumnos_mayores.txt");

        String comando1 = "java src\\Ejercicio2\\MayoresEdad.java";
        String comando2 = "java src\\Ejercicio2\\OrdenaNombres.java";

        ProcessBuilder pb1 = new ProcessBuilder(comando1.split(" "));
        ProcessBuilder pb2 = new ProcessBuilder(comando2.split(" "));

        pb1.redirectInput(alumnosInput);    // Redirijo la entrada del proceso 1 al archivo alumnos.txt
        pb2.redirectOutput(alumnosOutput);      // Redirijo la salida del proceso 2 al archivo alumnos_mayores.txt

        // Redirijo la salida de error a la del padre (consola)
        pb1.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb2.redirectError(ProcessBuilder.Redirect.INHERIT);

        // Creo una lista con los process builder
        List<ProcessBuilder> lpb = new ArrayList<>();
        lpb.add(pb1);
        lpb.add(pb2);

        try {

            List<Process> processes = ProcessBuilder.startPipeline(lpb);   // Inicio la tubería de procesos, la cual enlaza sus entradas y salidas entre sí

            int salidaProceso1 = processes.get(0).waitFor();  // Recojo el valor devuelto por el proceso 1
            int salidaProceso2 = processes.get(1).waitFor();  // Recojo el valor devuelto por el proceso 2

            // Compruebo la salida de los procesos e imprimo un mensaje en consecuencia
            if (salidaProceso1 == 0) {
                System.out.println("El proceso 1 ha finalizado con éxito");
            } else {
                System.out.println("El proceso 1 ha finalizado con el código de error: " + salidaProceso1);
            }

            if (salidaProceso2 == 0) {
                System.out.println("El proceso 2 ha finalizado con éxito");
            } else {
                System.out.println("El proceso 2 ha finalizado con el código de error: " + salidaProceso2);
            }

        } catch (IOException e) {
            System.err.println("Error durante la ejecución del proceso");
            System.exit(1);     // Indicamos que la ejecución termina con error 1
        } catch (InterruptedException e) {
            System.err.println("Proceso interrumpido");
            System.exit(2);     // Indicamos que la ejecución termina con error 2
        }
    }
}
