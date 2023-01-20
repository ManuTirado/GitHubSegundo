package EntidadesHibernate;

import jakarta.persistence.*;

@Entity
@Table(name = "Pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedido")
    private int idPedido;

    @Column(name = "idFactura")
    private int idFactura;

    @Column(name = "idProducto")
    private int idProducto;

    @Column(name = "cantidad")
    private int cantidad;
}
