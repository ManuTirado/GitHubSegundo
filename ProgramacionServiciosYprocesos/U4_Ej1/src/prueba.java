import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class prueba {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String mensaje;
        byte[] mensajeBytes;
        byte[] resumen = null;
        String resumenHexadecimal;

        System.out.println("Introduce el mensaje a cifrar:");
        mensaje = sc.nextLine();

        try {
            // Convierto el mensaje introducido por el usuario en un array de bytes
            mensajeBytes = mensaje.getBytes("UTF-8");

            // Creo una instancia de MessageDigest con el algoritmo SHA-256
            MessageDigest algoritmo = MessageDigest.getInstance("SHA-256");

            // Reiniciamos el objeto por si contiene datos
            algoritmo.reset();

            // Añado el mensaje del cual quiero calcular su hash
            algoritmo.update(mensajeBytes);

            // Generamos el resumen
            resumen = algoritmo.digest();

            resumenHexadecimal = String.format("%064x", new BigInteger(1, resumen));
            System.out.println(resumenHexadecimal);

        } catch (NoSuchAlgorithmException e) {
            System.err.println("El algoritmo seleccionado no existe");
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            System.err.println("No se conoce la codificación especificada");
            e.printStackTrace();
        }
    }
}
