import java.io.*;

public class Main2 {

    private static final String inputPath = "src\\input";

    public static void main(String[] args) {

        try {
            BufferedReader bufferedReaderReader = new BufferedReader(new FileReader(new File(inputPath)));
            String line = bufferedReaderReader.readLine();
            while (line != null) {
                crearHtml(new File("src",line));
                line = bufferedReaderReader.readLine();
            }
        } catch (Exception e) {
            System.out.println("Algo salió mal ;(");
        }
    }

    private static void crearHtml (File archivo) {
        File f = new File(archivo, "index.html");
        if (f.exists()) {
            f.delete();
        }
        try {
            f.createNewFile();
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f));
            String str = "<html><head><title> " + archivo.getName() + " </title></head><body><h1>" + archivo.getPath() + "</h1><h3> Autor: Manuel Tirado García </h3></body></html>";
            bufferedWriter.write(str);
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.printf("Algo salió mal en la creación del html: '%s'%n", f.getAbsolutePath());
        }
    }
}
