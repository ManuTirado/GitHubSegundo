import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashLogic {
    /**
     * Cifra un mensaje por HASH
     * @param mensaje mensaje a cifrar
     * @return resumen cifrado del mensaje
     */
    public static String getDigest(String mensaje) {
        String resumenString = "";
        byte[] resContrasena;
        try {
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
            algoritmo.reset();
            algoritmo.update(mensaje.getBytes("UTF-8")); // mensaje es un array de bytes
            resContrasena = algoritmo.digest();
            resumenString = String.format("%064x", new BigInteger(1, resContrasena));
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
        }
        return resumenString;
    }

    /**
     * Compara dos cadenas y devuelve true en caso de que sean iguales
     * @param res1 cadena 1
     * @param res2 cadena 2
     * @return true si son iguales
     */
    public static boolean compararResumenes(String res1, String res2) {
        boolean iguales = false;
        if (res1.equals(res2)) {
            iguales = true;
        }
        return iguales;
    }
}
