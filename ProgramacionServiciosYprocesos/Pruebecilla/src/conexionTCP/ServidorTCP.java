package conexionTCP;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
    public static void main(String[] args) {
        try {
            // 1 - Creación del socket servidor
            System.out.println("(Servidor): Abriendo conexión...");
            ServerSocket socketServidor = new ServerSocket(50000);

            // 2 - Espera y acepta conexiones
            System.out.println("(Servidor): Aceptando conexión...");
            Socket socketCliente = socketServidor.accept();

            // 3 - Flujos de entrada y salida
            System.out.println("(Servidor): Abriendo flujos de entrada y de salida...");
            InputStream is = socketCliente.getInputStream();
            OutputStream os = socketCliente.getOutputStream();

            // 4 - Intercambiar datos con el cliente
            System.out.println("(Servidor): Leo mensaje del cliente...");
            System.out.println("Mensaje del cliente: " + is.read());

            System.out.println("(Servidor): Envío mensaje al cliente con 7...");
            os.write(7);

            // 5 - Cerrar flujos de lectura y escritura
            System.out.println("(Servidor): Cerramos flujo de lectura y escritura...");
            is.close();
            os.close();

            // 6 - Cerrar la conexión
            socketServidor.close();
            socketCliente.close();

        } catch (IOException e) {
            System.err.println("ERROR: Error al crear el socket en el puerto 50000");
            throw new RuntimeException(e);
        }
    }
}
