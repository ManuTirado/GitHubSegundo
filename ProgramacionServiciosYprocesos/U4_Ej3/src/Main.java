import Funcionalidad.cifrado;
import Funcionalidad.descifrado;
import Funcionalidad.manejadoraDeKeys;

public class Main {

    public static void main(String[] args) {
        manejadoraDeKeys.guardarClaves(manejadoraDeKeys.generarClaves(), "src\\Keys\\emisor\\");
        manejadoraDeKeys.guardarClaves(manejadoraDeKeys.generarClaves(), "src\\Keys\\receptor\\");
        System.out.println("Claves generadas y guardadas correctamente");


        cifrado.cifrarMensajesYGuardar();

        descifrado.descifrarMensajesYMostrar();
    }
}
