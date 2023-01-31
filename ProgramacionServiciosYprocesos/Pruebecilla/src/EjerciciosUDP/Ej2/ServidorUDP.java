package EjerciciosUDP.Ej2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorUDP {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(41500);

            while (true) {
                System.out.println("Creación de los arrays de bytes");
                byte[] buffer1 = new byte[64];
                byte[] buffer2 = new byte[64];

                System.out.println("Creación de los datagramas");
                DatagramPacket packet1 = new DatagramPacket(buffer1, buffer1.length);
                DatagramPacket packet2 = new DatagramPacket(buffer2, buffer2.length);

                System.out.println("A la espera de recibir la cadena 1");
                socket.receive(packet1);
                System.out.println("Leemos la cadena 1");
                String mensaje1 = new String(packet1.getData());
                System.out.println("Primer mensaje recibido del cliente: " + mensaje1.trim());

                System.out.println("A la espera de recibir la cadena 2");
                socket.receive(packet2);
                System.out.println("Leemos la cadena 2");
                String mensaje2 = new String(packet2.getData());
                System.out.println("Primer mensaje recibido del cliente: " + mensaje2.trim());

                if (mensaje1.compareTo(mensaje2) < 0) {
                    enviarMensaje(socket,mensaje1,packet1.getAddress(), packet1.getPort());
                    enviarMensaje(socket,mensaje2,packet2.getAddress(), packet2.getPort());
                } else {
                    enviarMensaje(socket,mensaje2,packet2.getAddress(), packet2.getPort());
                    enviarMensaje(socket,mensaje1,packet1.getAddress(), packet1.getPort());
                }

                System.out.println();
                System.out.println("/  /  /  /  /  /  /  /  /  /  /  /  /  /  /  /  /  /");
            }

        } catch (SocketException e) {
            System.err.println("Error en la creación del socket");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("Error en la recuperación del paquete");
            throw new RuntimeException(e);
        }
    }

    private static void enviarMensaje(DatagramSocket socket, String mensaje, InetAddress address, int port) throws IOException {
        System.out.println("Preparamos el datagrama para el cliente y lo enviamos");
        byte[] bufferSalida = mensaje.getBytes();
        DatagramPacket packetSalida1 = new DatagramPacket(bufferSalida, bufferSalida.length, address, port);
        socket.send(packetSalida1);
    }
}
