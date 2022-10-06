package SAX;

import Entidades.Compra;
import Entidades.Producto;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class ComprasHandler extends DefaultHandler {



    private ArrayList<Compra> compras = new ArrayList<>();
    private ArrayList<Producto> productosActuales = new ArrayList<>();
    private Producto productoActual = new Producto();

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

    }

    @Override
    public void characters(char[] arg0, int start, int length) throws SAXException {
        //nos pasa el contenido de un elemento
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        //avisa cuando acaba un elemento y nos pasa la info
    }


}
