package DOM;

import Entidades.Compra;
import Entidades.Producto;
import Entidades.Utilidades;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;

public class MainDOM {

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

    /**
     * Función que devuelve un arraylist con instanciaciones de la clase Compra realizadas en el documento xml.
     * Utiliza la librería DOM para la lectura del xml.
     * @param entrada
     * @return
     */
    private static ArrayList<Compra> leerCompras(File entrada) {
        ArrayList<Compra> comprasLeidas = new ArrayList<>();
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(entrada);

            doc.getDocumentElement().normalize(); // Elimina nodos vacíos y combina adyacentes en caso de que los hubiera

            NodeList compras = doc.getElementsByTagName("compra");
            for (int i = 0; i < compras.getLength(); i++) {     // Recorro cada compra del xml
                comprasLeidas.add(leerCompra(compras, i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return comprasLeidas;
    }

    /**
     * Devuelve una instanciación de la clase Compra, resultado de leer el índice del NodeList que las contiene.
     * @param compras
     * @param i índice
     * @return compra
     * @throws ParseException
     */
    private static Compra leerCompra(NodeList compras, int i) throws ParseException {
        Compra compraLeida;
        Node nNode = compras.item(i);
        String fecha;
        ArrayList<Producto> productosLeidos = new ArrayList<Producto>();

        if(nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element compra = (Element) nNode;

            fecha = compra.getElementsByTagName("fecha").item(0).getTextContent();

            NodeList productos = compra.getElementsByTagName("producto");
            for (int j = 0; j < productos.getLength(); j++) {       // Recorro cada producto del ticket de cada compra
                productosLeidos.add(leerProducto(productos, j));
            }

            compraLeida = new Compra(fecha, productosLeidos);
        } else {
            compraLeida = new Compra(null, null);
        }
        return compraLeida;
    }

    /**
     * Devuelve una instanciación de la clase Producto, resultado de leer el índice del NodeList que los contiene.
     * @param productos
     * @param i
     * @return producto
     * @throws ParseException
     */
    private static Producto leerProducto(NodeList productos, int i) throws ParseException {
        Producto productoLeido;
        Node node = productos.item(i);
        String descripcion;
        double precio_ud, uds = 1, descuento = 0;

        if(node.getNodeType() == Node.ELEMENT_NODE) {
            Element producto = (Element) node;

            descripcion = producto.getElementsByTagName("descripcion").item(0).getTextContent();
            precio_ud = DecimalFormat.getNumberInstance().parse(producto.getElementsByTagName("precio_unidad").item(0).getTextContent()).doubleValue();
            if (producto.getElementsByTagName("unidades").getLength() != 0) {
                uds = DecimalFormat.getNumberInstance().parse(producto.getElementsByTagName("unidades").item(0).getTextContent()).doubleValue();
            }
            if (producto.getElementsByTagName("descuento").getLength() != 0) {
                descuento = DecimalFormat.getNumberInstance().parse(producto.getElementsByTagName("descuento").item(0).getTextContent()).doubleValue();
            }

            productoLeido = new Producto(descripcion, precio_ud, uds, descuento);
        } else {
            productoLeido = new Producto(null, 0, 0, 0);
        }
        return productoLeido;
    }
}