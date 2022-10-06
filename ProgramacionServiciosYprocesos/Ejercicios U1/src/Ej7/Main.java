package Ej7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ProcessBuilder pb1 = new ProcessBuilder("");
        ProcessBuilder pb2 = new ProcessBuilder("");
        ProcessBuilder pb3 = new ProcessBuilder("");
        ProcessBuilder pb4 = new ProcessBuilder("");

        List<ProcessBuilder> lpb = new ArrayList<>();
        lpb.add(pb1);
        lpb.add(pb2);
        lpb.add(pb3);
        lpb.add(pb4);

        try {
            ProcessBuilder.startPipeline(lpb);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
