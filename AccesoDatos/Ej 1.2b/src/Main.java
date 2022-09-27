import java.io.*;
import java.util.Arrays;


public class Main {

    private static String RUTA_INPUT = "src\\input.txt";
    private static String RUTA_OUTPUT = "src\\output.txt";

    public static void main(String[] args) {

        File ficheroEntrada = new File(RUTA_INPUT);
        File ficheroSalida = new File(RUTA_OUTPUT);
        String input;
        String[] output;

        // Leo el archivo y separo las palabras, para después meterlas en un array de String
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(ficheroEntrada));
            input = bufferedReader.readLine();
            bufferedReader.close();
            output = input.split("(?=\\p{Upper})"); // coincide con una secuencia vacía seguida de una letra mayúscula, y split la usa como delimitador.
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Creo el fichero de salida, borrandolo si existe para hacer una creación limpia
        if (ficheroSalida.exists()) { ficheroSalida.delete(); }
        try {
            ficheroSalida.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Escribo el array de strings en el archivo de texto
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ficheroSalida));
            for (int i=0; i<output.length; i++) {
                bufferedWriter.write(output[i]);
                if (i != output.length-1) {
                    bufferedWriter.newLine();
                }
            }
            bufferedWriter.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}