import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Cifrado_Logic {

    private static final int LONGITUD_BLOQUE = 16;
    private static final String ALGORITMO = "AES/ECB/PKCS5Padding";

    /**
     * Genera una clave a partir de una contraseña introducida por el usuario.
     * Si la contraseña es demasiado larga o demasiado corta, se ajustará a la longitud requerida.
     * @return clave generada a partir de la contraseña
     */
    public static Key obtenerClave(String pass) {
        StringBuffer sb = new StringBuffer(pass);
        sb.setLength(LONGITUD_BLOQUE);
        Key clave = new SecretKeySpec(sb.toString().getBytes(), 0, LONGITUD_BLOQUE, "AES");
        return clave;
    }

    /**
     * Cifra un mensaje con una clave.
     *  El mensaje se cifra con el algoritmo AES/ECB/PKCS5Padding.
     * @param mensaje mensaje a cifrar
     * @param clave clave con la que cifrar el mensaje
     * @return mensaje cifrado
     */
    public static byte[] cifrar(String mensaje, Key clave) {
        byte[] mensajeCifrado;
        try {

            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.ENCRYPT_MODE, clave);    // Iniciar el cifrado con la clave
            mensajeCifrado = cipher.doFinal(mensaje.getBytes());    // Llevar a cabo el cifrado

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }

        return mensajeCifrado;
    }

    /**
     * Descifra un mensaje con una clave.
     * El mensaje se descifra con el algoritmo AES/ECB/PKCS5Padding.
     * @param mensajeCifrado mensaje cifrado
     * @param clave clave con la que descifrar el mensaje
     * @return mensaje descifrado
     */
    public static String descifrar(String mensajeCifrado, Key clave) {
        String mensajeDescifrado = "";
        try {
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, clave);    // Iniciar el descifrado con la clave
            byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(mensajeCifrado));   // Llevar a cabo el descifrado
            mensajeDescifrado = new String(plainText);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (IllegalBlockSizeException e) {
            throw new RuntimeException(e);
        } catch (BadPaddingException e) {
            System.err.println("Contraseña Incorrecta");
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
        return mensajeDescifrado;
    }
}
