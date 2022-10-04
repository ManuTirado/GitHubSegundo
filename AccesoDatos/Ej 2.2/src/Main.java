import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.text.html.parser.Parser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.DecimalFormat;

public class Main {

    private static final String RUTA_FICHERO_ENTRADA = "src\\compras.xml";

    public static void main(String[] args) {

        File entrada = new File(RUTA_FICHERO_ENTRADA);

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(entrada);
            double totalProductos=0, precioTotalCompras=0, totalDescuento=0;

            doc.getDocumentElement().normalize(); // Elimina nodos vacíos y combina adyacentes en caso de que los hubiera
            NodeList compras = doc.getElementsByTagName("compra");
            for (int i = 0; i < compras.getLength(); i++) {     // Recorro cada compra del xml
                Node nNode = compras.item(i);
                double descuentoAcumulado = 0;
                double totalApagar = 0;

                if(nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element compra = (Element) nNode;

                    System.out.println("\nFecha: " + compra.getElementsByTagName("fecha").item(0).getTextContent());


                    NodeList productos = compra.getElementsByTagName("producto");
                    System.out.println("Se compraron " + productos.getLength() + " productos ese día");
                    totalProductos += productos.getLength();
                    System.out.println("Ticket: ");
                    for (int j = 0; j < productos.getLength(); j++) {       // Recorro cada producto del ticket de cada compra
                        Node node = productos.item(j);
                        double descuento, precio;
                        int unidades = 1;

                        if(node.getNodeType() == Node.ELEMENT_NODE) {
                            Element producto = (Element) node;

                            System.out.println("    Descripción: " + producto.getElementsByTagName("descripcion").item(0).getTextContent());
                            precio = DecimalFormat.getNumberInstance().parse(producto.getElementsByTagName("precio_unidad").item(0).getTextContent()).doubleValue();
                            System.out.println("    Precio ud: " + precio + "€");
                            if (producto.getElementsByTagName("unidades").getLength() != 0) {
                                unidades = DecimalFormat.getNumberInstance().parse(producto.getElementsByTagName("unidades").item(0).getTextContent()).intValue();
                                System.out.println("    Unidades: " + unidades);
                            }
                            totalApagar += precio * unidades;
                            if (producto.getElementsByTagName("descuento").getLength() != 0) {
                                descuento = DecimalFormat.getNumberInstance().parse(producto.getElementsByTagName("descuento").item(0).getTextContent()).doubleValue();
                                System.out.println("    Descuento: " + descuento + "€");
                                descuentoAcumulado += descuento;

                            }
                            System.out.println();
                        }
                    }
                    System.out.println("- - - - - - - - - - - - - - - - - - -");
                    System.out.println("Total a pagar: " + String.format("%.2f", totalApagar) + "€");
                    if (descuentoAcumulado != 0) {
                        System.out.println("Suma de descuentos aplicados: " + String.format("%.2f", descuentoAcumulado) + "€");
                        System.out.println("Precio final: " + String.format("%.2f", (totalApagar-descuentoAcumulado)) + "€");
                    }
                    System.out.println("- - - - - - - - - - - - - - - - - - -");
                    precioTotalCompras += totalApagar;
                    totalDescuento += descuentoAcumulado;
                }
                System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -");
            }
            System.out.println();
            System.out.println("Resumen total de compra:");
            System.out.println("    Productos comprados: " + totalProductos);
            System.out.println("    Precio: " + String.format("%.2f", precioTotalCompras) + "€");
            System.out.println("    Descuento: " + String.format("%.2f", totalDescuento) + "€");
            System.out.println();
            System.out.println("    Precio Final: " + String.format("%.2f", (precioTotalCompras-totalDescuento)) + "€");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}