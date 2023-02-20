import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashLogic {
    public static String getDigest(String mensaje) {
        String resumenHexadecimal = "";
        byte[] resContrasena;
        try {
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");
            algoritmo.reset();
            algoritmo.update(mensaje.getBytes("UTF-8")); // mensaje es un array de bytes
            resContrasena = algoritmo.digest();
            resumenHexadecimal = String.format("%064x", new BigInteger(1, resContrasena));
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
        }
        return resumenHexadecimal;
    }

    public static boolean compararResumenes(String res1, String res2) {
        boolean iguales = false;
        if (res1.equals(res2)) {
            iguales = true;
        }
        return iguales;
    }
}
