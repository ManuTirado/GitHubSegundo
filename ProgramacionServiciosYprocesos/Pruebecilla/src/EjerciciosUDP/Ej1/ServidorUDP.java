package EjerciciosUDP.Ej1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServidorUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(41500);

            while (true) {
                System.out.println("Creación del array de bytes");
                byte[] buffer = new byte[64];

                System.out.println("Creación del datagrama");
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                System.out.println("A la espera de recibir el nombre");
                socket.receive(packet);

                System.out.println("Leemos el nombre");
                String mensaje = new String(packet.getData());
                System.out.println("Mensaje enviado por el cliente: " + mensaje.trim());

                System.out.println("Hola " + mensaje.trim());
            }

        } catch (SocketException e) {
            System.err.println("Error en la creación del socket");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("Error en la recuperación del paquete");
            throw new RuntimeException(e);
        }
    }
}
