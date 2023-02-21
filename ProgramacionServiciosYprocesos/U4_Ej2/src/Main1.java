import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.util.Base64;
import java.util.Scanner;

public class Main1 {

    private static final String RUTA_ARCHIVO = "src\\mensajesCifrados.txt";

    public static void main(String[] args) {
        Key clave = generarClave();

        String mensaje = leerMensajes();

        byte[] mensajeCifrado = cifrarMensaje(mensaje, clave);

        guardarMensajeFichero(mensajeCifrado);
    }

    private static Key generarClave() {
        Scanner sc = new Scanner(System.in);
        String pass;
        do {
            System.out.println("Ingrese la contraseña para cifrar");
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

    private static String leerMensajes() {
        Scanner sc = new Scanner(System.in);
        StringBuffer sb = new StringBuffer();
        String mensaje;
        boolean parar;
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /  /  /  /  /");
        System.out.println("Pulse enter sin ningún mensaje para terminar");
        System.out.println("/  /  /  /  /  /  /  /  /  /  /  /  /  /  /  /");
        parar = false;
        do {
            System.out.println("Ingrese un mensaje para cifrar:");
            System.out.print("==> ");
            mensaje = sc.nextLine();
            if (mensaje.isBlank()) {
                parar = true;
            } else {
                sb.append(mensaje + System.lineSeparator());
            }
        } while (!parar);
        return sb.toString();
    }

    private static byte[] cifrarMensaje(String mensaje, Key clave) {
        return Cifrado_Logic.cifrar(mensaje, clave);
    }

    private static void guardarMensajeFichero(byte[] mensajeCifrado) {
        File file = new File(RUTA_ARCHIVO);
        String mensajeCifradox64;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
            mensajeCifradox64 = Base64.getEncoder().encodeToString(mensajeCifrado);
            bw.write(mensajeCifradox64);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            System.err.println("No existe el archivo de escritura");
        }
    }
}
