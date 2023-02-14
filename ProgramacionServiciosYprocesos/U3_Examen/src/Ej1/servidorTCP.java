package Ej1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class servidorTCP {

    private static final int PORT = 4000;

    public static void main(String[] args) {
        try {
            //Creación del socket servidor
            System.out.println("(Servidor): Abriendo conexión");
            ServerSocket socketServidor = new ServerSocket(PORT);   // Puerto de escucha del servidor
            while (true) {
                //Esperando de la aceptación de las peticiones
                System.out.println("(Servidor): Esperando peticiones");
                Socket socketCliente = socketServidor.accept();

                new GestorProcesosTCP(socketCliente).start();   // Lanzo el hilo con el cocket del cliente para que se comuniquen
            }
            //Cerrar la conexión
            /*
            System.out.println("Cerramos la conexión");
            socketServidor.close();
            socketCliente.close();
             */
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
