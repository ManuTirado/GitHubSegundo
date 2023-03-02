package Funcionalidad;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

public class cifrado {

    private static final String EMISOR = "src\\Keys\\emisor\\";
    private static final String RECEPTOR = "src\\Keys\\receptor\\";
    private static final String RUTA_FICHERO_RAW = "src\\Ficheros\\fichero.txt";
    private static final String RUTA_FICHERO_CIFRADO = "src\\Ficheros\\msgCifrado.txt";
    private static final String ALGORITHM = "RSA";

    public static void main(String[] args) {
        cifrarMensajesYGuardar();
    }

    public static void cifrarMensajesYGuardar() {
        String mensaje, mensajeCifradoBase64;
        byte[] mensajeCifrado;

        mensaje = Utilidades.leerFichero(RUTA_FICHERO_RAW);
        System.out.println();
        System.out.println(Utilidades.ANSI_BLUE + "Mensaje original: " + Utilidades.ANSI_RESET);
        System.out.println(Utilidades.ANSI_GREEN + mensaje + Utilidades.ANSI_RESET);

        mensajeCifrado = cifrarMensaje(mensaje.getBytes(StandardCharsets.UTF_8), manejadoraDeKeys.getClavePrivada(EMISOR));

        mensajeCifrado = cifrarMensaje(mensajeCifrado, manejadoraDeKeys.getClavePublica(RECEPTOR));

        // Lo imprimimos por pantalla en Base64 y lo escribimos en un fichero
        System.out.println();
        System.out.println(Utilidades.ANSI_BLUE + "Mensaje cifrado con la clave privada del emisor y la pública del receptor (x64): " + Utilidades.ANSI_RESET);
        mensajeCifradoBase64 = Base64.getEncoder().encodeToString(mensajeCifrado);
        Utilidades.escribirEnFichero(RUTA_FICHERO_CIFRADO, mensajeCifradoBase64);
        System.out.println(Utilidades.ANSI_PURPLE + mensajeCifradoBase64 + Utilidades.ANSI_RESET);
    }

    private static byte[] cifrarMensaje(byte[] mensaje, Key clave) {
        // Inicializar buffer de salida
        ByteArrayOutputStream bufferSalida = new ByteArrayOutputStream();
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, clave);
            // Calcular tamaño del bloque
            int tamanoBloque;
            try{
                tamanoBloque = (((RSAPublicKey)clave).getModulus().bitLength() + 7) / 8 - 11;
            } catch (ClassCastException e){
                tamanoBloque = (((RSAPrivateKey)clave).getModulus().bitLength() + 7) / 8 - 11;
            }
            // Cifrar el contenido en bloques
            int offset = 0;
            while (offset < mensaje.length) {
                int tamanoBloqueActual = Math.min(tamanoBloque, mensaje.length - offset);
                byte[] bloqueCifrado = cipher.doFinal(mensaje, offset, tamanoBloqueActual);
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
