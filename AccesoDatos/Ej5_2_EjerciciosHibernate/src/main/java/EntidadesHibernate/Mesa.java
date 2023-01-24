package EntidadesHibernate;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Mesa")
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMesa")
    private int idMesa;

    @Column(name = "numComensales")
    private int numComensales;

    @Column(name = "reserva")
    private int reserva;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="idMesa")
    private List<Factura> listaFacturas;

    public Mesa() {
    }

    public Mesa(int numComensales, int reserva) {
        this.numComensales = numComensales;
        this.reserva = reserva;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public int getNumComensales() {
        return numComensales;
    }

    public void setNumComensales(int numComensales) {
        this.numComensales = numComensales;
    }

    public int getReserva() {
        return reserva;
    }

    public void setReserva(int reserva) {
        this.reserva = reserva;
    }

    public List<Factura> getListaFacturas() {
        return listaFacturas;
    }

    public void setListaFacturas(List<Factura> listaFacturas) {
        this.listaFacturas = listaFacturas;
    }

    @Override
    public String toString() {
        return idMesa +" -> NumComensales: " + numComensales +
                ", Reserva: " + reserva;
    }
}
