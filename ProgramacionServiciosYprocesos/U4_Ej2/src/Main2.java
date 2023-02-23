import java.io.*;
import java.security.Key;
import java.util.Scanner;

public class Main2 {

    private static final String RUTA_ARCHIVO = "src\\mensajesCifrados.txt";

    public static void main(String[] args) {
        Key clave = generarClave();

        leerFicheroYdesCifrar(clave);
    }

    /**
     * Lee el fichero de mensajes cifrados y los descifra para imprimirlos por consola.
     * Si el fichero no existe, se muestra un mensaje de error.
     * Si hay algún error de entrada/salida, se muestra un mensaje de error.
     * @param clave clave para descifrar los mensajes
     */
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

    /**
     * Genera una clave a partir de una contraseña introducida por el usuario.
     *  La contraseña debe tener entre 1 y 16 caracteres.
     *  Si la contraseña es demasiado larga o demasiado corta, se volverá a pedir.
     * @return clave generada a partir de la contraseña
     */
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
