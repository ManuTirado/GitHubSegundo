package Ejercicio3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Ej3Main {

    public static void main(String[] args) {
        String comandoNumerosAleatorios = "java src\\Ejercicio3\\NumerosAleatorios.java";
        String comandoSumaNumeros = "java src\\Ejercicio3\\SumaNumeros.java";
        String comandoMediaNumeros = "java src\\Ejercicio3\\MediaNumeros.java";

        ArrayList<Process> procesosNumerosAleatorios = new ArrayList<>();
        ArrayList<Process> procesosMediaNumeros = new ArrayList<>();
        ArrayList<Process> procesosSumaNumeros = new ArrayList<>();

        try {
            File carpetaSalida = new File("src\\Ejercicio3\\SalidaNumerosAleatorios");     // Creo la carpeta de salida
            if (!carpetaSalida.exists()) {
                carpetaSalida.mkdirs();
            }
            long inicio = System.currentTimeMillis();   // Inicio Números Aleatorios
            // Lanzo 10 veces la clase NumerosAleatorios
            for (int i = 0; i < 10; i++) {
                File salida = new File("src\\Ejercicio3\\SalidaNumerosAleatorios\\"+i+".txt");     // Creo el fichero de salida
                ProcessBuilder pb = new ProcessBuilder(comandoNumerosAleatorios.split(" "));
                pb.redirectError(ProcessBuilder.Redirect.INHERIT);      // Redirecciono la salida de error al padre
                pb.redirectOutput(salida);      // Redirijo la salida al archivo salida
                procesosNumerosAleatorios.add(pb.start());      // Añado el proceso a la lista iniciándolo
            }
            esperarEjecucionProcesos(procesosNumerosAleatorios);
            long fin = System.currentTimeMillis();
            System.out.println("La creación de los números aleatorios ha tardado en ejecutarse: " + (fin-inicio) + " milisegundos.");

            File salidaSumas = new File("src\\Ejercicio3\\sumas.txt");      // Creo el fichero de salida de SumaNumeros
            File salidaMedias = new File("src\\Ejercicio3\\medias.txt");    // Creo el fichero de salida de MediaNumeros
            salidaSumas.delete(); salidaMedias.delete();    // Borro los archivos para que estén vacíos cuando escribamos
            inicio = System.currentTimeMillis();   // Inicio Números Aleatorios
            // Lanzo SumaNumeros y MediaNumeros
            for (int i = 0; i < 10; i++) {
                File entrada = new File("src\\Ejercicio3\\SalidaNumerosAleatorios\\"+i+".txt");    // Creo el fichero de entrada
                ProcessBuilder pbSumas = new ProcessBuilder((comandoSumaNumeros + " " + entrada.getName()).split(" "));
                ProcessBuilder pbMedias = new ProcessBuilder((comandoMediaNumeros + " " + entrada.getName()).split(" "));
                // Redirecciono la salida de error al padre de ambos procesos
                pbSumas.redirectError(ProcessBuilder.Redirect.INHERIT);
                pbMedias.redirectError(ProcessBuilder.Redirect.INHERIT);
                // Redirecciono la entrada al archivo de entrada de ambos procesos
                pbSumas.redirectInput(entrada);
                pbMedias.redirectInput(entrada);
                // Redirecciono la salida de cada proceso con su archivo correspondiente
                pbSumas.redirectOutput(ProcessBuilder.Redirect.appendTo(salidaSumas));
                pbMedias.redirectOutput(ProcessBuilder.Redirect.appendTo(salidaMedias));
                // Añado los procesos a sus respectivas listas iniciándolos
                procesosSumaNumeros.add(pbSumas.start());
                procesosMediaNumeros.add(pbMedias.start());
            }
            esperarEjecucionProcesos(procesosSumaNumeros);
            esperarEjecucionProcesos(procesosMediaNumeros);
            fin = System.currentTimeMillis();
            System.out.println("La creación de las sumas y las medias ha tardado en ejecutarse: " + (fin-inicio) + " milisegundos.");

            // Una vez que sé que todos los procesos han terminado, entonces miro el código con el que han terminado
            System.out.println("-- Procesos Números Aleatorios:");
            comprobarSalidaProcesos(procesosNumerosAleatorios);
            System.out.println();
            System.out.println("-- Procesos Suma Números:");
            comprobarSalidaProcesos(procesosSumaNumeros);
            System.out.println();
            System.out.println("-- Procesos Media Números:");
            comprobarSalidaProcesos(procesosMediaNumeros);

        } catch (IOException e) {
            System.err.println("Error durante ejecución del proceso");
            System.exit(1);
        }
    }

    /***
     * Imprime por pantalla si cada proceso de un arraylist de procesos ha finalizado correctamente o no,
     * en caso negativo, muestra el código de error correspondiente
     * @param listaProcesos arraylist de procesos
     */
    private static void comprobarSalidaProcesos (ArrayList<Process> listaProcesos) {
        for(Process proceso : listaProcesos) {
            int retorno = proceso.exitValue();
            // Compruebo cómo ha terminado el proceso y escribo un mensaje en consecuencia
            if (retorno == 0) {
                System.out.println("    El proceso ha finalizado correctamente");
            } else {
                System.out.println("    El proceso ha terminado con el siguiente código de error: " + retorno);
            }
        }
    }

    /***
     * Permanece en el bucle hasta que todos los procesos del arraylist han finalizado
     * @param procesos arraylist de procesos
     */
    private static void esperarEjecucionProcesos (ArrayList<Process> procesos) {
        int procesosVivos = procesos.size();
        // Mientras haya algún proceso vivo vuelvo a comenzar la cuenta
        while (procesosVivos > 0) {
            procesosVivos = procesos.size();
            // Recorro la lista de procesos para ver si siguen vivos
            for (Process proceso : procesos) {
                // Si alguno de los procesos ya ha terminado, entonces resto 1 a procesosVivos
                if(!proceso.isAlive()) {
                    procesosVivos--;
                }
            }
        }
    }
}
