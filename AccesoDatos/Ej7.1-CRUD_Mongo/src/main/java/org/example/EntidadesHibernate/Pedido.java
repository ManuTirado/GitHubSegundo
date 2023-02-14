package org.example.EntidadesHibernate;

public class Pedido {
    private int idPedido;
    private int idFactura;
    private int idProducto;
    private int cantidad;

    public Pedido() {
    }

    public Pedido(int idFactura, int idProducto, int cantidad) {
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public Pedido(int idPedido, int idFactura, int idProducto, int cantidad) {
        this.idPedido = idPedido;
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return idPedido + " -> idFactura: " + idFactura +
                ", idProducto: " + idProducto +
                ", Cantidad: " + cantidad;
    }
}
