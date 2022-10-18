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

    private boolean fecha, descripcion, precio_unidad, unidades, descuento;

    public void startElement (String uri, String localName, String qName, Attributes attributes) {
        switch (qName) {
            case "Compras" -> compras = new ArrayList<>();
            case "compra" -> compra = new Compra();
            case "fecha" -> fecha = true;
            case "ticket" -> productos = new ArrayList<>();
            case "producto" -> producto = new Producto();
            case "descripcion" -> descripcion = true;
            case "precio_unidad" -> precio_unidad = true;
            case "unidades" -> unidades = true;
            case "descuento" -> descuento = true;
        }
    }
    public void characters(char[] ch, int start, int length) {
        if (fecha) {
            compra.setFecha(new String(ch, start, length));
            fecha = false;
        }
        if (descripcion) {
            producto.setDescripcion(new String(ch, start, length));
            descripcion = false;
        }
        if (precio_unidad) {
            producto.setPrecio_unidad(Double.parseDouble(new String(ch, start, length).replaceAll(",",".")));
            precio_unidad = false;
        }
        if (unidades) {
            producto.setUnidades(Double.parseDouble(new String(ch, start, length).replaceAll(",",".")));
            unidades = false;
        }
        if (descuento) {
            producto.setDescuento(Double.parseDouble(new String(ch, start, length).replaceAll(",",".")));
            descuento = false;
        }
    }
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case "compra" -> compras.add(compra);
            case "ticket" -> compra.setTicket(productos);
            case "producto" -> productos.add(producto);
        }
    }

    public  ArrayList<Compra> obtenerCompras () {
        return compras;
    }
}
