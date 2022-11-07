package Ej11;

import java.io.*;

public class LeerFichero {

    private static final String RUTA_FICHERO = "C:\\pruebas\\carpeta1\\fichero2.txt";

    public static void main(String[] args) {
        File fichero = new File(RUTA_FICHERO);
        try {
            BufferedReader br = new BufferedReader(new FileReader(fichero));
            String line = br.readLine();
            while (line != null) {
                System.out.println(line);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
