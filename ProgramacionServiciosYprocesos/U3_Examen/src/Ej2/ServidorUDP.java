package Ej2;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServidorUDP {

    private static final String RUTA_FICHERO = "src\\Ej2\\Alumnos.txt";
    private static final File FICHERO = new File(RUTA_FICHERO);
    private static final int PORT = 50000;  // Puerto de escucha del servidor

    public static void main(String[] args) {
        try {
            // Creamos el socket
            System.out.println("Creación del socket");
            DatagramSocket socket = new DatagramSocket(PORT);

            while (true) {
                // Esperamos un mensaje de un cliente
                System.out.println("Esperando mensaje...");
                DatagramPacket datagramaEntrada = leerMensaje(socket);
                // Recepción del mensaje del cliente
                String mensajeRecibido = new String(datagramaEntrada.getData()).trim();
                System.out.println("Mensaje Recibido: " + mensajeRecibido);
                // Envío de la respuesta
                String respuesta;
                String[] msgRecibidoPartes = mensajeRecibido.split(" ");
                switch (msgRecibidoPartes[0].toUpperCase()) {   // Según la primera palabra del mensaje recibido en mayúsculas
                    case "CREATE" -> respuesta = createAlumno(msgRecibidoPartes);
                    case "SELECT" -> respuesta = selectAlumno();
                    default ->  // Si no coincide con ninguna la respuesta será un mensaje de error
                            respuesta = "ERROR, operación '" + mensajeRecibido + "' inválida (válidos 'SELECT' o 'CREATE')";
                }
                try {   // Envío el mensaje al cliente
                    enviarMensaje(socket, respuesta, datagramaEntrada.getAddress(), datagramaEntrada.getPort());
                } catch (IOException e) {
                    System.err.println("Error al enviar el mensaje al cliente");
                }
            }
        } catch (SocketException e) {
            System.err.println("Error en la creación del socket");
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.err.println("Error en la recuperación del paquete");
            throw new RuntimeException(e);
        }
    }

    /**
     * Queda a la espera de recibir un mensaje por el socket pasado.
     * Una vez lo recibe lo mete en un datagrama y lo devuelve.
     *
     * @param socket Socket del lado servidor
     * @return packetEntrada recibido del cliente
     * @throws IOException En caso de que ocurra un error al recibir el datagrama
     */
    private static DatagramPacket leerMensaje(DatagramSocket socket) throws IOException {
        byte[] bufferEntrada = new byte[64];

        DatagramPacket packetEntrada = new DatagramPacket(bufferEntrada, bufferEntrada.length);
        socket.receive(packetEntrada);

        return packetEntrada;
    }

    /**
     * Envía un mensaje al socket pasado mediante su dirección y puerto de escucha.
     * Para ello crea un buffer de salida de bytes del mensaje, lo mete en un datagrama para enviarlo por el socket.
     *
     * @param socket  Socket del lado servidor
     * @param mensaje Mensaje que queremos enviar
     * @param address Dirección del cliente
     * @param port    Puerto de escucha del cliente
     * @throws IOException En caso de que ocurra un error al enviar el datagrama
     */
    private static void enviarMensaje(DatagramSocket socket, String mensaje, InetAddress address, int port) throws IOException {
        byte[] bufferSalida = mensaje.getBytes();
        DatagramPacket packetSalida1 = new DatagramPacket(bufferSalida, bufferSalida.length, address, port);
        socket.send(packetSalida1);
    }

    /**
     * Lee el archivo de alumnos y devuelve una cadena con el contenido del mismo.
     *
     * @return contenido del fichero, o en caso de que ocurra un error un mensaje de error.
     */
    private static String selectAlumno() {
        String respuesta;
        try {
            BufferedReader br = new BufferedReader(new FileReader(FICHERO));
            respuesta = "";
            String line = br.readLine();
            while (line != null) {
                respuesta += line + System.lineSeparator();
                line = br.readLine();
            }
        } catch (FileNotFoundException e) {     // No existe el fichero
            respuesta = "ERROR al realizar la operación";
            System.err.println("Error, no se ha encontrado el fichero");
        } catch (IOException e) {   // Error de entrada/salida al leer el fichero
            respuesta = "ERROR al realizar la operación";
            System.err.println("Error, de entrada y salida al leer el fichero");
        }
        return respuesta;
    }

    /**
     * Escribe un alumno en el fichero de alumnos.
     * @param msgRecibidoPartes array de string con la estructura 'CREATE', 'código', 'nombreAlumno'
     * @return Mensaje de inserción correcta o mensaje de error si ocurrió algún problema
     */
    private static String createAlumno(String[] msgRecibidoPartes) {
        String respuesta = "ERROR al realizar la operación";
        int codAlumno;
        String nombreAlumno;
        try {
            codAlumno = Integer.parseInt(msgRecibidoPartes[1]);
            nombreAlumno = msgRecibidoPartes[2];
            respuesta = escribirAlumno(codAlumno, nombreAlumno);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Error en el formato recibido");
        } catch (NumberFormatException e) {
            System.err.println("Error en el formato del código del alumno");
        } catch (IOException e) {
            System.err.println("Error al intentar escribir el alumno en el fichero");
        }
        return respuesta;
    }

    /**
     * Escribe un alumno en el fichero de alumnos mediante un bufferedWriter,
     * escribe su código y nombre en una línea separados por un espacio.
     * @param codigo código del alumno
     * @param nombre nombre del alumno
     * @return Mensaje de escritura correcta si no ocurre ningún error
     * @throws IOException En caso de que ocurra un error al escribir en el archivo
     */
    private static String escribirAlumno(int codigo, String nombre) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO, true));
        bw.write(String.valueOf(codigo));
        bw.write(" ");
        bw.write(nombre);
        bw.newLine();
        bw.close();
        return "Alumno introducido correctamente";
    }
}
