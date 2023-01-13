import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {

    private static final String RUTA_ENTRADA = "src\\input.txt";
    private static final String RUTA_SALIDA = "src\\output.txt";

    public static void main(String[] args) {
        RandomAccessFile rafEntrada;
        RandomAccessFile rafSalida;
        String letra;

        try {
            rafEntrada = new RandomAccessFile(new File(RUTA_ENTRADA), "r");
            rafSalida = new RandomAccessFile(new File(RUTA_SALIDA), "rw");

            rafEntrada.seek(0);
            letra = rafEntrada.readLine();
            while (letra != null) {
                escribirCaracter(rafSalida, letra);

                letra = rafEntrada.readLine();
            }

            rafEntrada.close();
            rafSalida.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void escribirCaracter(RandomAccessFile raf, String letra) {
        int pos = 0;
        String letraParaMover;
        try {
            raf.seek(pos);
            if (raf.readLine() != null) {
                for (int i = (int) raf.length(); i >= 2; i -= 2) {
                    raf.seek(i - 2);
                    letraParaMover = raf.readLine();
                    raf.seek(i);
                    raf.write(letraParaMover.getBytes());
                    raf.write("\n".getBytes());
                }
                raf.seek(0);
            }
            raf.write(letra.getBytes());
            raf.write("\n".getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}