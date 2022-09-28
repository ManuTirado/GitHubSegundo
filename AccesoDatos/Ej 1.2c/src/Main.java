import java.io.*;
import java.util.Arrays;


public class Main {

    private static String RUTA_INPUT = "src\\input.txt";
    private static String RUTA_OUTPUT = "src\\palabrasOrdenadas.txt";

    public static void main(String[] args) {

        File ficheroEntrada = new File(RUTA_INPUT);
        File ficheroSalida = new File(RUTA_OUTPUT);

        try {
            RandomAccessFile raf = new RandomAccessFile(ficheroEntrada,"r");
            System.out.println(raf.readLine());
        } catch (Exception e) {
            System.out.println("XDn't");
        }



        // Creo el fichero de salida, borrandolo si existe para hacer una creación limpia
        if (ficheroSalida.exists()) { ficheroSalida.delete(); }
        try {
            ficheroSalida.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}