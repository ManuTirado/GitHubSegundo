import java.io.*;
import java.security.Key;
import java.util.Scanner;

public class Main2 {

    private static final String RUTA_ARCHIVO = "src\\mensajesCifrados.txt";

    public static void main(String[] args) {
        Key clave = generarClave();

        leerFicheroYdesCifrar(clave);
    }

    private static void leerFicheroYdesCifrar(Key clave) {
        File file = new File(RUTA_ARCHIVO);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                String mensajeDescifrado = Cifrado_Logic.descifrar(line, clave);
                System.out.println(mensajeDescifrado);
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {
            System.err.println("No existe el archivo de lectura");
        } catch (IOException e) {
            System.err.println("Error de entrada/salida al leer el archivo");
        }
    }

    private static Key generarClave() {
        Scanner sc = new Scanner(System.in);
        String pass;
        do {
            System.out.println("Ingrese la contraseña para descifrar");
            System.out.print("==> ");
            pass = sc.nextLine();
            if (pass.length() > 16) {
                System.out.println("Contraseña demasiado larga (máx 16)");
            }
            if (pass.length() == 0) {
                System.out.println("Contraseña demasiado corta (mín 1)");
            }
        } while (pass.length() > 16 || pass.length() == 0);
        return Cifrado_Logic.obtenerClave(pass);
    }
}
