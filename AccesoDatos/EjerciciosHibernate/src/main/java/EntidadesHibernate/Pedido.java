package EntidadesHibernate;

import javax.persistence.*;

@Entity
@Table(name = "Pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedido")
    private int idPedido;

    @ManyToOne
    @JoinColumn(name = "idFactura")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "idProducto")
    private Productos producto;

    @Column(name = "cantidad")
    private int cantidad;

    public Pedido() {
    }

    public Pedido(Factura factura, Productos producto, int cantidad) {
        this.factura = factura;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return idPedido + " -> idFactura: " + factura.getIdFactura() +
                ", idProducto: " + producto.getIdProducto() +
                ", Cantidad: " + cantidad;
    }
}
