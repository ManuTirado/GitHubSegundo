package EstudiantesYlibros;

public class Libreria {
    public static boolean libros[] = new boolean[9];



    public synchronized void cogerLibros(int libro1, int libro2) {
        if (libros[libro1] || libros[libro2]) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        libros[libro1] = true;
        libros[libro2] = true;
    }

    public synchronized void dejarLibros(int libro1, int libro2) {
        libros[libro1] = false;
        libros[libro2] = false;
        this.notifyAll();
    }
}
