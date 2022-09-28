import java.io.*;

public class Main {

    private static final String inputPath = "src\\input";
    private static final String outputPath = "src";

    public static void main(String[] args) {

        try {
            BufferedReader bufferedReaderReader = new BufferedReader(new FileReader(new File(inputPath)));
            String line = bufferedReaderReader.readLine();
            while (line != null) {
                File f = new File(outputPath, line);
                if (!f.exists()) {
                    f.mkdir();
                }
                line = bufferedReaderReader.readLine();
            }

        } catch (Exception e) {
            System.out.println("Algo salió mal ;(");
        }

    }
}