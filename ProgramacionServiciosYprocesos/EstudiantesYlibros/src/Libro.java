public class Libro {
    public static boolean[] libros = new boolean[9];    // Lista que hace referencia a los libros

    /**
     * Obtiene la reserva de dos libros en cuanto alguno de ellos queda libre
     * @param libro1
     * @param libro2
     */
    public synchronized void reservaLibros(int libro1, int libro2) {
        while (libros[libro1] == true || libros[libro2] == true) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Reservo los libros
        libros[libro1] = true;
        libros[libro2] = true;
        mostrarLibros();
    }

    /**
     * Libera los libros pasados por parámetro y lo notifica a todos los hilos pausados
     *
     * @param libro1
     * @param libro2
     */
    public synchronized void liberaLibros(int libro1, int libro2) {
        libros[libro1] = false;
        libros[libro2] = false;
        mostrarLibros();
        this.notifyAll();
    }

    /**
     * Muestra por consola los nueve libros e indica si están ocupados por algún alumno o están libres
     */
    private static void mostrarLibros() {
        String ansiGreen = "\u001B[32m";
        String ansiRed = "\u001B[31m";
        String ansiReset = "\u001B[0m";
        StringBuilder sb = new StringBuilder();
        sb.append("Libros:    ");
        for (int i = 0; i < libros.length; i++) {
            sb.append((i + 1) + "->" + ((libros[i]) ? (ansiRed + "Ocupado" + ansiReset) : (ansiGreen + "Libre" + ansiReset)) + "    ");
        }
        System.out.println(sb);

    }
}
