import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

public class Main2 {

    private static final String RUTA_FICHERO = "src\\credenciales.cre";

    public static void main(String[] args) {
        validarRegistroUsuario();
    }

    private static void validarRegistroUsuario() {
        Scanner sc = new Scanner(System.in);
        String usuario, contrasena;
        System.out.println("/ / / / / LOGIN / / / / /");
        System.out.print("Usuario => ");
        usuario = sc.nextLine();
        System.out.print("Contraseña => ");
        contrasena = sc.nextLine();
        System.out.println("/ / / / / / / / / / / / / /");

        String resumenContrasena = HashLogic.getDigest(contrasena);
        comprobarRegistroEnFichero(usuario, resumenContrasena);
        sc.close();
    }

    /**
     * Comprueba si el usuario y el resumen de la contraseña pasadas coincide con los guardados en el archivo.
     * @param usuario nombre del usuario
     * @param resumenContrasena resumen de la contraseña
     */
    private static void comprobarRegistroEnFichero(String usuario, String resumenContrasena) {
        File ficheroCredenciales = new File(RUTA_FICHERO);
        BufferedReader br;
        boolean encontrado;
        String line;
        try {
            br = new BufferedReader(new FileReader(ficheroCredenciales));
            encontrado = false;
            line = br.readLine();
            while (line != null && !encontrado) {
                if (line.equals(usuario)) {
                    String resContra = br.readLine();
                    if (HashLogic.compararResumenes(resContra, resumenContrasena)) {
                        System.out.println("Usuario y contraseña válidos :)");
                        System.out.println("Acceso permitido a la aplicación");
                    } else {
                        System.out.println("Contraseña incorrecta");
                    }
                    encontrado = true;
                }
                line = br.readLine();
            }
            if (!encontrado) {
                System.out.println("Usuario no encontrado ;(");
            }
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
