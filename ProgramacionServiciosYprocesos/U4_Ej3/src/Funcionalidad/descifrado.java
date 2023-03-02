package Funcionalidad;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

public class descifrado {

    private static final String EMISOR = "src\\Keys\\emisor\\";
    private static final String RECEPTOR = "src\\Keys\\receptor\\";
    private static final String RUTA_FICHERO_CIFRADO = "src\\Ficheros\\msgCifrado.txt";
    private static final String ALGORITHM = "RSA";

    public static void main(String[] args) {
        descifrarMensajesYMostrar();
    }

    public static void descifrarMensajesYMostrar() {
        String mensajeCifrado;
        byte[] mensajeDescifrado;

        mensajeCifrado = Utilidades.leerFichero(RUTA_FICHERO_CIFRADO);
        mensajeDescifrado = descifrar(Base64.getDecoder().decode(mensajeCifrado), manejadoraDeKeys.getClavePrivada(RECEPTOR));
        mensajeDescifrado = descifrar(mensajeDescifrado, manejadoraDeKeys.getClavePublica(EMISOR));


        // Lo imprimimos por pantalla en Base64
        System.out.println();
        System.out.println(Utilidades.ANSI_BLUE + "Mensaje descifrado con la clave privada del receptor y la pública del emisor: " + Utilidades.ANSI_RESET);
        System.out.println(Utilidades.ANSI_PURPLE + new String(mensajeDescifrado) + Utilidades.ANSI_RESET);
    }

    private static byte[] descifrar(byte[] mensajeCifrado, Key clave) {
        // Inicializar buffer de salida
        ByteArrayOutputStream bufferSalida = new ByteArrayOutputStream();

        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            // Desciframos con la clave privada
            cipher.init(Cipher.DECRYPT_MODE, clave);

            // Calcular tamaño del bloque
            int tamanoBloque;
            try{
                tamanoBloque = (((RSAPublicKey)clave).getModulus().bitLength() + 7) / 8 - 11;
            } catch (ClassCastException e){
                tamanoBloque = (((RSAPrivateKey)clave).getModulus().bitLength() + 7) / 8 - 11;
            }

            // Cifrar el contenido en bloques
            int offset = 0;
            while (offset < mensajeCifrado.length) {
                int tamanoBloqueActual = Math.min(tamanoBloque, mensajeCifrado.length - offset);
                byte[] bloqueCifrado = cipher.doFinal(mensajeCifrado, offset, tamanoBloqueActual);
                bufferSalida.write(bloqueCifrado);
                offset += tamanoBloqueActual;
            }

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
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Devolver contenido cifrado completo
        return bufferSalida.toByteArray();
    }
}
