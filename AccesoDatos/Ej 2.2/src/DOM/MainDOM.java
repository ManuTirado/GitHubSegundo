package DOM;

import Entidades.Compra;
import Entidades.Producto;
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
            System.out.println(obtenerResumenCompra(c));
        }

        System.out.println(obtenerResumenTotal(comprasLeidas));
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

    /**
     * Función que devuelve un String con un resumen de una compra formateado como un ticket
     * @param compra
     * @return
     */
    private static String obtenerResumenCompra(Compra compra) {
        StringBuilder sb = new StringBuilder();

        sb.append("=== === === === === === COMPRA === === === === === ===\n");
        sb.append("Fecha => " + compra.getFecha() + System.lineSeparator());
        sb.append("Se compraron " + compra.getProductosVendidos() + " productos\n");
        sb.append("Ticket:\n");
        for (Producto p :
                compra.getTicket()) {
            sb.append("    Descripción: " + p.getDescripcion() + System.lineSeparator());
            sb.append("    Precio ud: " + obt2Decimales(p.getPrecio_unidad()) + "€\n");
            sb.append("    Unidades: " + p.getUnidades() + System.lineSeparator());
            if (p.getDescuento() != 0) {
                sb.append("    Descuento: " + obt2Decimales(p.getDescuento()) + "€\n\n");
            } else {
                sb.append(System.lineSeparator());
            }
        }
        sb.append("--- --- --- --- --- --- --- --- --- --- --- --- --- ---\n");
        sb.append("Precio => " + obt2Decimales(compra.getPrecioCompra()) +"€\n");
        sb.append("Descuento => " + obt2Decimales(compra.getDescuentoCompra()) + "€\n");
        sb.append("Precio Final => " + obt2Decimales(compra.getPrecioCompraFinal()) +"€\n");
        sb.append("=== === === === === === === === === === === === === ===\n");

        return sb.toString();
    }

    /**
     * Función que devuelve un String con un resumen de la suma de todas las compras
     * @param compras
     * @return
     */
    private static String obtenerResumenTotal(ArrayList<Compra> compras) {
        StringBuilder sb = new StringBuilder();

        sb.append("/  /  /  /  /  /  /  / RESUMEN TOTAL /  /  /  /  /  /  /  /\n");
        sb.append("Productos comprados => " + obtenerTotalProductosComprados(compras) + System.lineSeparator());
        sb.append("Precio Total => " + obt2Decimales(obtenerPrecioTotal(compras)) + "€\n");
        sb.append("Descuento Total => " + obt2Decimales(obtenerDescuentoTotal(compras)) + "€\n");
        sb.append("Precio Final Total => " + obt2Decimales(obtenerPrecioFinalTotal(compras)) + "€\n");
        sb.append("/ / / / / / / / / / / / / / / / / / / / / / / / / / / / / /");
        return sb.toString();
    }

    /**
     * Función que suma los productos comprados en todas las compras y lo devuelve como entero.
     * @param compras
     * @return Total productos comprados
     */
    private static int obtenerTotalProductosComprados(ArrayList<Compra> compras) {
        int totalProductos = 0;
        for (Compra c:
                compras) {
            totalProductos += c.getProductosVendidos();
        }
        return totalProductos;
    }

    /**
     * Función que suma los precios de todas las compras y la devuelve como double.
     * @param compras
     * @return Precio de todas las compras juntas
     */
    private static double obtenerPrecioTotal(ArrayList<Compra> compras) {
        double precioTotal = 0;
        for (Compra c :
                compras) {
            precioTotal += c.getPrecioCompra();
        }
        return precioTotal;
    }

    /**
     * Función que suma los descuentos de todas las compras y lo devuelve como double.
     * @param compras
     * @return Descuento total
     */
    private static double obtenerDescuentoTotal(ArrayList<Compra> compras) {
        double descuentoTotal = 0;
        for (Compra c:
                compras) {
            descuentoTotal += c.getDescuentoCompra();
        }
        return descuentoTotal;
    }

    /**
     * Función que suma los precios finales (restado el descuento) de todas lascomprasy lo devuelve como double.
     * @param compras
     * @return Precio final total
     */
    private static double obtenerPrecioFinalTotal(ArrayList<Compra> compras) {
        double precioFinal = 0;
        for (Compra c :
                compras) {
            precioFinal += c.getPrecioCompraFinal();
        }
        return precioFinal;
    }

    /**
     * Función que devuelve un String con el número pasado por parámetro formateado para tener 2 decimales
     * @param numero
     * @return Número con 2 decimales
     */
    private static String obt2Decimales(double numero) {
        return String.format("%.2f", numero);
    }
}