package EntidadesHibernate;

import jakarta.persistence.*;

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
}
