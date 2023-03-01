package Funcionalidad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class manejadoraDeKeys {

    private static final String PUBLIC_KEY_FILE_ROUTE = "src\\Keys\\public_key.key";
    private static final String PRIVATE_KEY_FILE_ROUTE = "src\\Keys\\private_key.key";
    private static final String ALGORITHM = "RSA";
    private static final int KEY_SIZE = 2048;

    public static KeyPair generarClaves() {
        KeyPairGenerator generador;
        KeyPair claves = null;
        try {
            generador = KeyPairGenerator.getInstance(ALGORITHM);
            generador.initialize(KEY_SIZE);
            claves = generador.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No existe el algoritmo especificado");
            e.printStackTrace();
        }
        return claves;
    }

    public static void guardarClaves(KeyPair claves) {
        PrivateKey clavePrivada = claves.getPrivate();
        PublicKey clavePublica = claves.getPublic();
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(PUBLIC_KEY_FILE_ROUTE);
            fos.write(clavePublica.getEncoded());
            fos.close();

            fos = new FileOutputStream(PRIVATE_KEY_FILE_ROUTE);
            fos.write(clavePrivada.getEncoded());
            fos.close();
        } catch (FileNotFoundException e) {
            System.err.println("No se encuentra el fichero.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Se ha producido un error durante la escritura en el fichero.");
            e.printStackTrace();
        }

    }

    public static PublicKey getClavePublica() {
        File ficheroClavePublica = new File(PUBLIC_KEY_FILE_ROUTE);
        PublicKey clavePublica = null;
        try {
            byte[] bytesClavePublica = Files.readAllBytes(ficheroClavePublica.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(bytesClavePublica);
            clavePublica = keyFactory.generatePublic(publicKeySpec);
        } catch (IOException e) {
            System.err.println("Se ha producido en la lectura del fichero");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No existe el algoritmo especificado");
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            System.err.println("La clave indicada no es válida");
            e.printStackTrace();
        }
        return clavePublica;
    }

    public static PrivateKey getClavePrivada() {
        File ficheroClavePrivada = new File(PRIVATE_KEY_FILE_ROUTE);
        PrivateKey clavePrivada = null;
        try {

            byte[] bytesClavePrivada = Files.readAllBytes(ficheroClavePrivada.toPath());
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(bytesClavePrivada);
            clavePrivada = keyFactory.generatePrivate(privateKeySpec);

        } catch (IOException e) {
            System.err.println("Se ha producido en la lectura del fichero");
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No existe el algoritmo especificado");
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            System.err.println("La clave indicada no es válida");
            e.printStackTrace();
        }
        return clavePrivada;
    }

    public static void main(String[] args) {
        KeyPair claves = generarClaves();
        guardarClaves(claves);
        System.out.println("Claves generadas y guardadas correctamente");
    }
}
