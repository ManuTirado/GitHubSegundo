import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteUDP {
    private static final String MENSAJE_ACERTADO = "Correcto";

    public static void main(String[] args) {
        try {
            //Obtener dirección IP local
            InetAddress direccion = InetAddress.getLocalHost();
            int port = 50000;
            int num;
            String respuesta;
            boolean adivinado = false;

            // Creación del socket
            System.out.println("Creación del socket");
            DatagramSocket socket = new DatagramSocket();

            do {
                System.out.println("Ingrese un número");
                num = validarEntero();

                System.out.println("Inicio de envío de datos");
                enviarMensaje(socket, String.valueOf(num), direccion, port);
                System.out.println("Fin de envío de datos");
                System.out.println("Esperando respuesta...");
                respuesta = leerMensaje(socket);
                System.out.println(respuesta);
                if (respuesta.equals(MENSAJE_ACERTADO)) {
                    adivinado = true;
                }
            } while (!adivinado);
            System.out.println("Cierre de conexiones");
            socket.close();
        } catch (IOException e) {
            System.err.println("Error al enviar el paquete");
            throw new RuntimeException(e);
        }
    }

    private static void enviarMensaje(DatagramSocket socket, String mensaje, InetAddress address, int port) throws IOException {
        byte[] bufferSalida = mensaje.getBytes();
        DatagramPacket packetSalida1 = new DatagramPacket(bufferSalida, bufferSalida.length, address, port);
        socket.send(packetSalida1);
    }

    private static String leerMensaje(DatagramSocket socket) throws IOException {
        String mensaje = "";
        byte[] bufferEntrada = new byte[64];

        DatagramPacket packetEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
        socket.receive(packetEntrada);

        mensaje = new String(packetEntrada.getData());
        return mensaje.trim();
    }

    private static int validarEntero() {
        Scanner sc = new Scanner(System.in);
        int num = -1;
        boolean correcto = false;
        do {
            try {
                System.out.print("==> ");
                num = sc.nextInt();
                correcto = true;
            } catch (Exception e) {
                System.out.println("Valor no válido");
                sc.next();
            }
        } while (!correcto);
        return num;
    }
}
