import java.io.*;
public class VerInf {
    public static void main(String[] args) {
        System.out.println("INFORMACIÓN SOBRE EL FICHERO:");
        File f = new File("C:\\Users\\mtirado\\Desktop\\Segundo DAM\\1er Trimestre\\Acceso a datos\\VerDir\\VerDir\\src\\VerInf.java");
        if(f.exists()){
            System.out.println("Nombre del fichero  : "+f.getName());
            System.out.println("Ruta                : "+f.getPath());
            System.out.println("Ruta absoluta       : "+f.getAbsolutePath());
            System.out.println("Se puede leer       : "+f.canRead());
            System.out.println("Se puede escribir   : "+f.canWrite());
            System.out.println("Tamaño              : "+f.length());
            System.out.println("Es un directorio    : "+f.isDirectory());
            System.out.println("Es un fichero       : "+f.isFile());
            System.out.println("Nombre del directorio padre: "+f.getParent());
        }
    }
}