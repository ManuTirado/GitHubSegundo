import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Agenda")
public class AgendaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAgenda")
    private int idAgenda;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "tfno")
    private String tfno;

    public AgendaEntity() {

    }
    public AgendaEntity(String nombre, String tfno) {
        this.nombre = nombre;
        this.tfno = tfno;
    }

    //region Getters and Setters
    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTfno() {
        return tfno;
    }

    public void setTfno(String tfno) {
        this.tfno = tfno;
    }
    //endregion
}
