package SAX;

import Entidades.Compra;
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
        leerCompras(entrada);
    }

    private static void leerCompras(File entrada) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            ComprasHandler handler = new ComprasHandler();

            saxParser.parse(entrada, handler);

            ArrayList<Compra> compras = handler.obtenerCompras();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}