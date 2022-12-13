package EstudiantesYlibros;

public class Estudiante extends Thread {
    public Libreria libreria;

    public Estudiante (Libreria libreria) {
        this.libreria = libreria;
    }

    @Override
    public void run() {
        int libro1 = (int) (Math.random()*9);
        int libro2 = (int) (Math.random()*9);
        while (libro1 == libro2) {
            libro2 = (int) (Math.random()*9);
        }

        libreria.cogerLibros(libro1,libro2);
        System.out.println("Estudiante " + Thread.currentThread().getName() + " leyendo los libros: " + libro1 + " y " + libro2);
        try {
            Thread.sleep((long) (Math.random()*4000 +4000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        libreria.dejarLibros(libro1,libro2);
        System.out.println("Estudiante " + Thread.currentThread().getName() + " ha dejado los libros: " + libro1 + " y " + libro2);
    }
}
