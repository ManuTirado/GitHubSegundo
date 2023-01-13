package EntidadesHibernate;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Alumnado")
public class Alumnado implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlum")
    private int idAlum;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "ape1")
    private String ape1;
    @Column(name = "ape2")
    private String ape2;
    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Direccion direccion;

    public Alumnado(){}

    public Alumnado(String nombre, String ape1, String ape2) {
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
    }

    public int getIdAlum() {
        return idAlum;
    }

    public void setIdAlum(int idAlum) {
        this.idAlum = idAlum;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe1() {
        return ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
}
