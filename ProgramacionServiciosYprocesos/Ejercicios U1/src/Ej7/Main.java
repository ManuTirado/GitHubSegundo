package Ej7;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        File salida = new File("src\\Ej7\\salida.txt");

        ProcessBuilder pb1 = new ProcessBuilder("java", "src\\Ej7\\GenerarDireccionesIP.java");
        ProcessBuilder pb2 = new ProcessBuilder("java", "src\\Ej7\\FiltrarIPs.java");
        ProcessBuilder pb3 = new ProcessBuilder("java", "src\\Ej7\\LeerDireccionesYmostrarlas.java");

        pb1.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb2.redirectError(ProcessBuilder.Redirect.INHERIT);
        pb3.redirectError(ProcessBuilder.Redirect.INHERIT);

        pb3.redirectOutput(ProcessBuilder.Redirect.to(salida));

        List<ProcessBuilder> lpb = new ArrayList<>();
        lpb.add(pb1);
        lpb.add(pb2);
        lpb.add(pb3);

        try {
            ProcessBuilder.startPipeline(lpb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
