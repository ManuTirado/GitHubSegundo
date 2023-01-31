package PruebasTCP.conexionTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class ClienteTCP {
    public static void main(String[] args) {
        try {
            // 1 - Creación de socket de tipo cliente
            System.out.println("(Cliente): Creación de socket...");
            Socket socketCliente = new Socket(InetAddress.getLocalHost(), 50000);

            // 2 - Abrir los flujos de lectura y escritura de datos
            System.out.println("(Cliente): Apertura de flujos de entrada y salida...");
            InputStream is = socketCliente.getInputStream();
            OutputStream os = socketCliente.getOutputStream();

            // 3 - Intercambio datos con el servidor
            System.out.println("(Cliente): Envía mensaje al servidor con 14...");
            os.write(14);

            System.out.println("(Cliente): Lectura del mensaje del servidor...");
            System.out.println("Mensaje recibido por servidor: " + is.read());

            // 4 - Cerrar los flujos de lectura y escritura
            System.out.println("(Cliente): Cerramos flujo de lectura y escritura...");
            os.close();
            is.close();

            // 5 - Cerrar la conexión
            socketCliente.close();

        } catch (IOException e) {
            System.err.println("Error: Problema con la conexión");
            throw new RuntimeException(e);
        }
    }
}
