package PruebaUDP;

import java.io.IOException;
import java.net.*;

public class ClienteUDP {

    public static void main(String[] args) {
        try {
            //Obtener dirección IP local
            InetAddress direccion = InetAddress.getLocalHost();

            // Creación del socket
            System.out.println("Creación del socket");
            DatagramSocket socket = new DatagramSocket();

            // Creación del mensaje
            String mensaje = "Hola colombi";
            byte[] buffer = mensaje.getBytes();

            System.out.println("Creamos el datagrama");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccion, 41500);

            System.out.println("Enviamos el datagrama");
            socket.send(packet);

            System.out.println("Cierre de conexiones");
            socket.close();
        } catch (UnknownHostException e) {
            System.err.println("Error al obtener la dirección IP local");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("Error al enviar el paquete");
            throw new RuntimeException(e);
        }
    }
}
