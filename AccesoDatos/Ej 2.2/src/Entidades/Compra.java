package Entidades;

import java.util.ArrayList;

public class Compra {
    private String fecha;
    private ArrayList<Producto> ticket;
    private int productosVendidos;
    private double precioCompra, descuentoCompra, precioCompraFinal;

    public Compra(String fecha, ArrayList<Producto> ticket) {
        setFecha(fecha);
        setTicket(ticket);
        setProductosVendidos();
        setPrecioCompra();
        setDescuentoCompra();
        setPrecioCompraFinal();
    }

    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Producto> getTicket() {
        return ticket;
    }
    public void setTicket(ArrayList<Producto> ticket) {
        this.ticket = ticket;
    }

    public int getProductosVendidos() {
        return productosVendidos;
    }
    public void setProductosVendidos() {
        int productosVendidos=0;
        for (Producto p :
                this.ticket) {
            productosVendidos += p.getUnidades();
        }
        this.productosVendidos = productosVendidos;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }
    public void setPrecioCompra() {
        double precioCompra = 0;
        for (Producto p :
                this.ticket) {
            precioCompra += (p.getPrecio_unidad() * p.getUnidades());
        }
        this.precioCompra = precioCompra;
    }

    public double getDescuentoCompra() {
        return descuentoCompra;
    }
    public void setDescuentoCompra() {
        double descuentoCompra = 0;
        for (Producto p :
                this.ticket) {
            descuentoCompra += p.getDescuento();
        }
        this.descuentoCompra = descuentoCompra;
    }

    public double getPrecioCompraFinal() {
        return precioCompraFinal;
    }
    public void setPrecioCompraFinal() {
        this.precioCompraFinal = this.precioCompra-this.descuentoCompra;
    }
}
