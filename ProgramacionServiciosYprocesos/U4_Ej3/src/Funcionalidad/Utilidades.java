package Funcionalidad;

import java.io.*;

public class Utilidades {

    public static final String ANSI_RESET = "\u001B[0m", ANSI_GREEN = "\u001B[32m", ANSI_BLUE = "\u001B[34m", ANSI_PURPLE = "\u001B[35m";

    public static String leerFichero(String rutaFichero) {
        StringBuilder mensaje = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaFichero));
            String linea;
            while ((linea = br.readLine()) != null) {
                mensaje.append(linea + System.lineSeparator());
            }
        } catch (FileNotFoundException e) {
            System.err.println("No se encuentra el fichero.");
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return mensaje.toString().trim();
    }

    public static void escribirEnFichero(String rutaFichero, String mensaje) {
        try {
            File fichero = new File(rutaFichero);
            BufferedWriter bw = new BufferedWriter(new FileWriter(fichero, false));
            bw.write(mensaje);
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
