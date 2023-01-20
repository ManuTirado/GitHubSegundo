package EntidadesHibernate;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "Factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFactura")
    private int idFactura;

    @ManyToOne
    @JoinColumn(name = "idMesa")
    private Mesa mesa;

    @Column(name = "tipoPago")
    private String tipoPago;

    @Column(name = "Importe")
    private BigDecimal Importe;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="idFactura")
    private List<Pedido> listaPedidos;

    public Factura() {
    }

    public Factura(Mesa mesa, String tipoPago, BigDecimal importe) {
        this.mesa = mesa;
        this.tipoPago = tipoPago;
        Importe = importe;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public BigDecimal getImporte() {
        return Importe;
    }

    public void setImporte(BigDecimal importe) {
        Importe = importe;
    }
}
