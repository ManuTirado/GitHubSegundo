import java.io.*;
import java.util.Arrays;


/**
 * Este programa recibe un archivo binario con nombres de 5 caracteres seguidos sin espacios y los ordena de manera ascendente
 * mediante el método de la burbuja en el mismo fichero, para finalmente escribir el resultado ordenado en un txt legible.
 */
public class Main {

    private static String RUTA_INPUT = "src\\input.bin";
    private static String RUTA_OUTPUT = "src\\palabrasOrdenadas.txt";

    public static void main(String[] args) {

        File ficheroEntrada = new File(RUTA_INPUT);
        File ficheroSalida = new File(RUTA_OUTPUT);

        // Creo el fichero de salida, borrandolo si existe para hacer una creación limpia
        if (ficheroSalida.exists()) {
            ficheroSalida.delete();
        }
        try {
            ficheroSalida.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            RandomAccessFile raf = new RandomAccessFile(ficheroEntrada, "rw");
            raf.seek(0);
            String nombre1 = "";
            String nombre2 = "";
            // Ordeno el archivo binario de entrada mediante el método de la burbuja
            for (int i = 0; i < raf.length(); i+=10) {  // Recorro todos los nombres del archivo de entrada (Cada nombre son 5 caracteres y cada carácter 2 bytes por lo que cada nombre son 10 bytes)
                for (int j = 0; j < raf.length()-10; j+=10) {
                    raf.seek(j);
                    for (int k = 0; k < 5; k++) {   // Cada nombre son 5 caracteres
                        nombre1 += raf.readChar();
                    }
                    raf.seek(j+10);
                    for (int l = 0; l < 5; l++) {   // Cada nombre son 5 caracteres
                        nombre2 += raf.readChar();
                    }
                    if (nombre1.compareTo(nombre2) > 0) {
                        raf.seek(j);                // Intercambio la posición de los nombres
                        raf.writeChars(nombre2);
                        raf.writeChars(nombre1);
                    }
                    nombre1 = "";
                    nombre2 = "";
                }
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroSalida));
            String nombre;
            raf.seek(0);
            while (raf.getFilePointer() < raf.length()) {   //Mientras quede fichero
                nombre = "";
                for (int i = 0; i < 5; i++) {   // Leo un nombre
                    nombre += raf.readChar();
                }
                bw.write(nombre);   // Lo escribo en el txt de salida
                bw.newLine();
            }
            bw.close();
            raf.close();
        } catch (Exception e) {
            System.out.println("XDn't");
        }
    }
}