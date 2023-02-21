import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Scanner;

public class Main1 {

    private static final String RUTA_FICHERO = "src\\credenciales.cre";

    public static void main(String[] args) {
        realizarRegistroUsuario();
    }

    private static void realizarRegistroUsuario() {
        Scanner sc = new Scanner(System.in);
        String usuario, contrasena;
        System.out.println("/ / / / / REGISTRO / / / / /");
        System.out.print("Usuario => ");
        usuario = sc.nextLine();
        System.out.print("Contraseña => ");
        contrasena = sc.nextLine();
        System.out.println("/ / / / / / / / / / / / / /");

        String resumenContrasena = HashLogic.getDigest(contrasena);
        guardarRegistroEnFichero(usuario, resumenContrasena);
        sc.close();
    }

    /**
     * Escribe al usuario y al resumen de su contraseña en el documento especificado
     * @param usuario nombre del usuario
     * @param resumenContrasena resumen de la contraseña
     */
    private static void guardarRegistroEnFichero(String usuario, String resumenContrasena) {
        File ficheroCredenciales = new File(RUTA_FICHERO);
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ficheroCredenciales, true));
            bw.write(usuario);
            bw.newLine();
            bw.write(resumenContrasena);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}