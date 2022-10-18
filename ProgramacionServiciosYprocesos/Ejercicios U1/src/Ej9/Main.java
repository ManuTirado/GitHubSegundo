package Ej9;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Main {
    public static void main(String[] args) {
        File input = new File("src\\Ej9\\input.txt");

        String[] comando = {"java","src\\Ej5\\PreguntaNombre.java"};

        try {
            Process p = Runtime.getRuntime().exec(comando);
            BufferedReader br = new BufferedReader(new FileReader(input));

            OutputStream os = p.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

            // Escribo en la entrada del proceso lo escrito en el fichero input.txt
            String line = br.readLine();
            while (line != null) {
                bw.write(line);
                bw.newLine();
                line = br.readLine();
            }

            bw.close();
            p.waitFor();
            InputStream is = p.getInputStream();
            BufferedReader br2 = new BufferedReader(new InputStreamReader(is));
            String line2 = br2.readLine();
            while (line2 != null) {
                System.out.println(line2);
                line2 = br2.readLine();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
