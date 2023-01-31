package EjerciciosUDP.Ej3;

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
                System.out.println("A la espera de recibir el datagrama");
                socket.receive(packet);

                System.out.println("Leemos el mensaje");
                String mensaje = new String(packet.getData());
                System.out.println("Mensaje enviado por el cliente: " + mensaje.trim());

                int sumaValoresAscii = 0;
                for (char c:
                        mensaje.trim().toCharArray()){
                    sumaValoresAscii += (int) c;
                }

                System.out.println("Preparamos el datagrama para el cliente y lo enviamos");
                String mensajeSalida = "Suma valores ASCII: " + String.valueOf(sumaValoresAscii);
                byte[] bufferSalida = mensajeSalida.getBytes();
                DatagramPacket packetSalida = new DatagramPacket(bufferSalida, bufferSalida.length, packet.getAddress(), packet.getPort());
                socket.send(packetSalida);
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
}
