package ejercicio3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CuentaCaracteres {
	
	private final static String RUTA_FICHERO = "C:\\ficheros\\texto_largo.txt";

	public static void main(String[] args) {
		try {
			// El carácter a contar lo tomo de la línea de argumentos.
			// Me quedo con el primero de los argumentos, que debe ser el carácter a buscar.
			String caracter = args[0];
			
			// En numCaracteres llevaré la cuenta del carácter que se ha introducido por argumentos
			int numCaracteres = 0;
			
			// En caracterLeido guardaré cada carácter leído del texto
			String caracterLeido;
			
			// caracterNumero es el carácter leído en formato número
			int caracterNumero;
			
			// Abro para su lectura el fichero texto_largo.txt
			FileReader fr = new FileReader(RUTA_FICHERO);
			
			// Mientras siga habiendo caracteres para leer
			caracterNumero = fr.read();
			while (caracterNumero != -1) {
				// Paso todos los caracteres a minúscula para facilitar la comparación
				caracterLeido = String.valueOf((char) caracterNumero).toLowerCase();
				
				// Cada vez que encuentre al carácter sumo 1 al contador
				if(caracterLeido.equals(caracter)) {
					numCaracteres++;
				}
				caracterNumero = fr.read();
			}
			
			System.out.println("El carácter " + caracter + " aparece " + numCaracteres + " veces.");

			fr.close();
		} catch (FileNotFoundException e) {
			System.err.println("No se ha encontrado el fichero especificado");
		} catch (IOException e) {
			System.err.println("Se ha producido un error durante la lectura del fichero");
			e.printStackTrace();
		}
	}
}