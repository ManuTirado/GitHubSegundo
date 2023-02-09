package Ej1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServidorUDP {


    public static void main(String[] args) {
        try {
            System.out.println("Creación del socket");
            DatagramSocket socket = new DatagramSocket(50000);

            int numSecreto = (int) (Math.random()*101);
            System.out.println("El número secreto es: " + numSecreto);

            while (true) {
                System.out.println("Esperando mensaje...");
                DatagramPacket datagramaEntrada = leerMensaje(socket);
                new GestorProcesosUDP(socket, datagramaEntrada, numSecreto).start();
            }

        } catch (SocketException e) {
            System.err.println("Error en la creación del socket");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("Error en la recuperación del paquete");
            throw new RuntimeException(e);
        }
    }

    private static DatagramPacket leerMensaje(DatagramSocket socket) throws IOException {
        String mensaje = "";
        byte[] bufferEntrada = new byte[64];

        DatagramPacket packetEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
        socket.receive(packetEntrada);

        return packetEntrada;
    }

}
