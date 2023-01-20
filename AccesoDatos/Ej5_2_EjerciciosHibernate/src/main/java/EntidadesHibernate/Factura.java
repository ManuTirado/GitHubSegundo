package EntidadesHibernate;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Factura")
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFactura")
    private int idFactura;

    @Column(name = "idMesa")
    private int idMesa;

    @Column(name = "tipoPago")
    private String tipoPago;

    @Column(name = "Importe")
    private BigDecimal Importe;
}
