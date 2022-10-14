package SAX;

import Entidades.Compra;
import Entidades.Producto;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class ComprasHandler extends DefaultHandler {
    private ArrayList<Compra> compras;
    private Compra compra;
    private ArrayList<Producto> productos;
    private Producto producto;

    public void startElement (String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "Compras":
                compras = new ArrayList<>();
                break;
            case "compra":

                break;
            case "fecha":

                break;
            case "ticket":

                break;
            case "producto":

                break;
            case "descripcion":

                break;
            case "precio_unidad":

                break;
        }
    }
    public void characters(char ch[], int start, int length) {

    }
    public void endElement(String uri, String localName, String qName) {

    }

    public  ArrayList<Compra> obtenerCompras () {
        return compras;
    }
}
