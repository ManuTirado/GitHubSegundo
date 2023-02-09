package Ej1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class GestorProcesosUDP extends Thread {
    private static final String MENSAJE_ACERTADO = "Correcto";
    DatagramSocket socket;
    DatagramPacket datagramaEntrada;
    int numSecreto;

    public GestorProcesosUDP(DatagramSocket socket, DatagramPacket datagramaEntrada, int numSecreto) {
        this.socket = socket;
        this.datagramaEntrada = datagramaEntrada;
        this.numSecreto = numSecreto;
    }

    @Override
    public void run() {
        realizarProceso();
    }

    private void realizarProceso() {
        // Recepción de mensaje del cliente
        String mensajeRecibido = new String(datagramaEntrada.getData()).trim();
        int numRecibido = Integer.parseInt(mensajeRecibido);
        System.out.println("Mensaje Recibido: " + mensajeRecibido);
        // Envío de la respuesta
        String respuesta;
        if (numRecibido == numSecreto) {
            respuesta = MENSAJE_ACERTADO;
            System.out.println("El cliente con ip: " + datagramaEntrada.getAddress() + " ha adivinado el número");
        } else if (numRecibido < numSecreto) {
            respuesta = "El número enviado es menor que el número secreto";
        } else if (numRecibido > numSecreto) {
            respuesta = "El número enviado es mayor que el número secreto";
        } else {
            respuesta = "Algo no salió bien";
        }
        try {
            enviarMensaje(socket,respuesta,datagramaEntrada.getAddress(), datagramaEntrada.getPort());
        } catch (IOException e) {
            System.err.println("Error al enviar el mensaje al cliente");
        }
    }

    private static void enviarMensaje(DatagramSocket socket, String mensaje, InetAddress address, int port) throws IOException {
        byte[] bufferSalida = mensaje.getBytes();
        DatagramPacket packetSalida1 = new DatagramPacket(bufferSalida, bufferSalida.length, address, port);
        socket.send(packetSalida1);
    }
}
