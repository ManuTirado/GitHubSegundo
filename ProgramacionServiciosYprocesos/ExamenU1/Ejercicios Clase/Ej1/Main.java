package Ej1;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/***
 * Muestra el entorno de ejecución de un proceso con Map<String, String> environment() de ProcessBuilder.
 * Hay que iterar sobre el Map<String, String> devuelto para mostrar cada entrada.
 */
public class Main {
    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("java", "src\\Ej1\\HolaMundo.java");
        System.out.println("Voy a lanzar el proceso");
        Map<String, String> mapa = pb.environment();
        Set<String> claves = mapa.keySet();
        for (String clave: claves) {
            String valor = mapa.get(clave);
            System.out.println(clave + " = " + valor);
        }
        try {
            pb.inheritIO();
            Process process = pb.start();
        } catch (IOException ioException) {
            System.err.println("Error: " + ioException);
        }
    }
}