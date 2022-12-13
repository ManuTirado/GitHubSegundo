package EstudiantesYlibros;

public class Main {
    public static void main(String[] args) {
        Libreria libreria = new Libreria();

        for (int i = 0; i < 9; i++) {
            new Estudiante(libreria).start();
        }
    }
}
