package Funcionalidad;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;

public class descifrado {

    private static final String RUTA_FICHERO_CIFRADO_PUBLICA = "src\\Ficheros\\msgCifradoPublica.txt";
    private static final String RUTA_FICHERO_CIFRADO_PRIVADA = "src\\Ficheros\\msgCifradoPrivada.txt";
    private static final String ALGORITHM = "RSA";

    public static void main(String[] args) {
        descifrarMensajesYMostrar();
    }

    public static void descifrarMensajesYMostrar() {
        String mensajeCifradoPrivada, mensajeCifradoPublica;
        byte[] mensajeDescifradoPublica, mensajeDescifradoPrivada;

        mensajeCifradoPrivada = Utilidades.leerFichero(RUTA_FICHERO_CIFRADO_PRIVADA);
        mensajeDescifradoPublica = descifrar(mensajeCifradoPrivada, manejadoraDeKeys.getClavePublica());

        // Lo imprimimos por pantalla en Base64
        System.out.println();
        System.out.println(Utilidades.ANSI_BLUE + "Mensaje descifrado con la clave pública: " + Utilidades.ANSI_RESET);
        System.out.println(Utilidades.ANSI_PURPLE + new String(mensajeDescifradoPublica) + Utilidades.ANSI_RESET);


        mensajeCifradoPublica = Utilidades.leerFichero(RUTA_FICHERO_CIFRADO_PUBLICA);
        mensajeDescifradoPrivada = descifrar(mensajeCifradoPublica, manejadoraDeKeys.getClavePrivada());

        // Lo imprimimos por pantalla en Base64
        System.out.println();
        System.out.println(Utilidades.ANSI_BLUE + "Mensaje descifrado con la clave privada: " + Utilidades.ANSI_RESET);
        System.out.println(Utilidades.ANSI_PURPLE + new String(mensajeDescifradoPrivada) + Utilidades.ANSI_RESET);
    }

    private static byte[] descifrar(String mensajeCifrado, Key clave) {
        byte[] mensajeDescifrado = null;

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            // Desciframos con la clave privada
            cipher.init(Cipher.DECRYPT_MODE, clave);

            byte[] mensajeCifradoBytes = Base64.getDecoder().decode(mensajeCifrado);

            // Se obtiene el mensaje Funcionalidad.descifrado
            mensajeDescifrado = cipher.doFinal(mensajeCifradoBytes);

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

        return mensajeDescifrado;
    }
}
