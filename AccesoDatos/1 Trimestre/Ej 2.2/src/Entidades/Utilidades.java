package Entidades;

import java.util.ArrayList;

public class Utilidades {
    /**
     * Función que devuelve un String con un resumen de una compra formateado como un ticket
     * @param compra
     * @return
     */
    public static String obtenerResumenCompra(Compra compra) {
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
    public static String obtenerResumenTotal(ArrayList<Compra> compras) {
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
