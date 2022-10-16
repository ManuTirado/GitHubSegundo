package SAX;

import Entidades.Compra;
import Entidades.Producto;
import Entidades.Utilidades;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;

public class MainSAX {

    private static final String RUTA_FICHERO_ENTRADA = "src\\compras.xml";

    public static void main(String[] args) {
        File entrada = new File(RUTA_FICHERO_ENTRADA);
        ArrayList<Compra> comprasLeidas;

        comprasLeidas = leerCompras(entrada);

        for (Compra c :
                comprasLeidas) {
            System.out.println(Utilidades.obtenerResumenCompra(c));
        }

        System.out.println(Utilidades.obtenerResumenTotal(comprasLeidas));
    }

    private static ArrayList<Compra> leerCompras(File entrada) {
        ArrayList<Compra> compras = new ArrayList<>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            ComprasHandler handler = new ComprasHandler();

            saxParser.parse(entrada, handler);

            compras = handler.obtenerCompras();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return compras;
    }
}