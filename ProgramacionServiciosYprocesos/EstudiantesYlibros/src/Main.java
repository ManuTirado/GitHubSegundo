import java.util.Random;

public class Main implements Runnable {

    public Libro libro;

    public Main(Libro l) {
        this.libro = l;
    }

    @Override
    public void run() {
        try {
            while (true) {  // Bucle infinito
                // Genero dos números aleatorios entre el 0 y el 8, que hacen referencia a
                // dos libros de los 9 que hay
                int libro1 = new Random().nextInt(9);
                int libro2 = new Random().nextInt(9);
                while (libro1 == libro2) {  // Si se repiten los número obtenemos otro aleatorio hasta que no sean iguales
                    libro2 = new Random().nextInt(9);
                }

                libro.reservaLibros(libro1, libro2);    //Reservo los dos libros
                System.out.println(Thread.currentThread().getName() + " tiene reservados los libros " + (libro1 + 1) + " y " + (libro2 + 1) + System.lineSeparator());

                Thread.sleep((new Random().nextInt(3000, 5001)));    // Espero entre 3 y 5 segundos

                libro.liberaLibros(libro1, libro2);     // Libero los dos libros
                System.out.println(Thread.currentThread().getName() + " ha terminado de leer." + System.lineSeparator());

                Thread.sleep(1000);
            }
        } catch (

                InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        Main e = new Main(new Libro());
        for (int i = 1; i <= 4; i++) {      // Creo un hilo por cada estudiante [1-4]
            Thread hilo = new Thread(e);
            hilo.setName("Estudiante " + i);
            hilo.setPriority(i * 2);
            hilo.start();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}