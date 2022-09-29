import java.io.File;
import java.util.Map;
import java.util.Set;

public class crearProceso {

    public static void main(String[] args) {
        ProcessBuilder pb = new ProcessBuilder("java", "Avanzado.java");
        pb.directory(new File("src"));
        pb.inheritIO();

        Map<String, String> mapa = pb.environment();
        Set<String> claves = mapa.keySet();
        for (String clave : claves) {
            String valor = mapa.get(clave);
            System.out.println("[ " + clave + " = " + valor + " ]");
        }

        try {
            System.out.println("Voy a lanzar el proceso");
            pb.start();
        } catch (Exception e) {
            System.out.println("Algo salió mal ;(");
        }
    }
}
