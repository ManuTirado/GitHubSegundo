package EntidadesHibernate;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "Productos")
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProducto")
    private int idProducto;

    @Column(name = "Denominacion")
    private String Denominacion;

    @Column(name = "Precio")
    private BigDecimal Precio;
}
