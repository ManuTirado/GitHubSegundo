import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ServidorUDP {

    private static final String RUTA_ARCHIVO_SALIDA = "src\\output.txt";

    public static void main(String[] args) {
        try {
            File file = new File(RUTA_ARCHIVO_SALIDA);
            if (!file.exists()) {
                file.createNewFile();
            }

            System.out.println("Creación del socket");
            DatagramSocket socket = new DatagramSocket(50000);

            while (true) {
                boolean fin = false;
                BufferedWriter bw = new BufferedWriter(new FileWriter(file,true));
                System.out.println("-  -  - Inicio Escritura -  -  -");
                while (!fin) {
                    String mensaje = leerMensaje(socket);
                    escribirMensajeEnArchivo(bw, mensaje);
                    System.out.println(mensaje);
                    if (mensaje.equals("FIN")) {
                        fin = true;
                        System.out.println("-  -  - Fin Escritura -  -  -");
                    }
                }
                bw.close();
            }

        } catch (SocketException e) {
            System.err.println("Error en la creación del socket");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("Error en la recuperación del paquete");
            throw new RuntimeException(e);
        }
    }

    private static String leerMensaje(DatagramSocket socket) throws IOException {
        String mensaje = "";
        byte[] bufferEntrada = new byte[64];

        DatagramPacket packetEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
        socket.receive(packetEntrada);

        mensaje = new String(packetEntrada.getData());
        return mensaje.trim();
    }

    private static void escribirMensajeEnArchivo(BufferedWriter bw, String mensaje) {
        try {
            bw.write(mensaje);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al escribir el mensaje '" + mensaje + "'");
        }

    }
}
