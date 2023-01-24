package EntidadesHibernate;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

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

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="idProducto")
    private List<Pedido> listaPedidos;

    public Productos(){}

    public Productos(String denominacion, BigDecimal precio) {
        Denominacion = denominacion;
        Precio = precio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getDenominacion() {
        return Denominacion;
    }

    public void setDenominacion(String denominacion) {
        Denominacion = denominacion;
    }

    public BigDecimal getPrecio() {
        return Precio;
    }

    public void setPrecio(BigDecimal precio) {
        Precio = precio;
    }

    @Override
    public String toString() {
        return idProducto + " -> Denominación: '" + Denominacion + '\'' +
                ", Precio: " + Precio + "€";
    }
}
