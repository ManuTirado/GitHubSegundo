package Funcionalidad;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

public class cifrado {

    private static final String RUTA_FICHERO_RAW = "src\\Ficheros\\fichero.txt";
    private static final String RUTA_FICHERO_CIFRADO_PUBLICA = "src\\Ficheros\\msgCifradoPublica.txt";
    private static final String RUTA_FICHERO_CIFRADO_PRIVADA = "src\\Ficheros\\msgCifradoPrivada.txt";
    private static final String ALGORITHM = "RSA";

    public static void main(String[] args) {
        cifrarMensajesYGuardar();
    }

    public static void cifrarMensajesYGuardar() {
        String mensaje, mensajeCifradoPrivadaBase64, mensajeCifradoPublicaBase64;
        byte[] mensajeCifradoPrivada, mensajeCifradoPublica;

        mensaje = Utilidades.leerFichero(RUTA_FICHERO_RAW);
        System.out.println();
        System.out.println(Utilidades.ANSI_BLUE + "Mensaje original: " + Utilidades.ANSI_RESET);
        System.out.println(Utilidades.ANSI_GREEN + mensaje + Utilidades.ANSI_RESET);

        mensajeCifradoPrivada = cifrarMensaje(mensaje, manejadoraDeKeys.getClavePrivada());

        // Lo imprimimos por pantalla en Base64 y lo escribimos en un fichero
        System.out.println();
        System.out.println(Utilidades.ANSI_BLUE + "Mensaje cifrado con la clave privada (x64): " + Utilidades.ANSI_RESET);
        mensajeCifradoPrivadaBase64 = Base64.getEncoder().encodeToString(mensajeCifradoPrivada);
        Utilidades.escribirEnFichero(RUTA_FICHERO_CIFRADO_PRIVADA, mensajeCifradoPrivadaBase64);
        System.out.println(Utilidades.ANSI_PURPLE + mensajeCifradoPrivadaBase64 + Utilidades.ANSI_RESET);


        mensajeCifradoPublica = cifrarMensaje(mensaje, manejadoraDeKeys.getClavePublica());

        // Lo imprimimos por pantalla en Base64 y lo escribimos en un fichero
        System.out.println();
        System.out.println(Utilidades.ANSI_BLUE + "Mensaje cifrado con la clave pública (x64): " + Utilidades.ANSI_RESET);
        mensajeCifradoPublicaBase64 = Base64.getEncoder().encodeToString(mensajeCifradoPublica);
        Utilidades.escribirEnFichero(RUTA_FICHERO_CIFRADO_PUBLICA, mensajeCifradoPublicaBase64);
        System.out.println(Utilidades.ANSI_PURPLE + mensajeCifradoPublicaBase64 + Utilidades.ANSI_RESET);
    }

    private static byte[] cifrarMensaje(String mensaje, Key clave) {
        byte[] mensajeCifrado = null;

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, clave);

            byte[] mensajeBytes = mensaje.getBytes(StandardCharsets.UTF_8);
            // Se cifra el mensaje
            mensajeCifrado = cipher.doFinal(mensajeBytes);

        } catch (NoSuchAlgorithmException e) {
            System.err.println("El algoritmo seleccionado no existe");
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            System.err.println("No existe el padding seleccionado");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.err.println("La clave introducida no es válida");
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            System.err.println("El tamaño del bloque utilizado no es correcto");
            e.printStackTrace();
        } catch (BadPaddingException e) {
            System.err.println("El padding utilizado es erróneo");
            e.printStackTrace();
        }

        return mensajeCifrado;
    }
}
