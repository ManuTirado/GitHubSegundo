package EntidadesHibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tAlumnado")
@NamedQuery(name = "listaAlumnosMatriculados", query = "select alumnado from tAlumnado as alumnado inner join alumnado.listaMatriculas as ot")
public class tAlumnado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlum")
    private int idAlum;

    @Column(name = "ape1")
    private String ape1;

    @Column(name = "ape2")
    private String ape2;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="idAlum")
    private List<tMatricula> listaMatriculas;

    public tAlumnado(){}

    public tAlumnado(String ape1, String ape2, String nombre) {
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.nombre = nombre;
    }

    public tAlumnado(String ape1, String ape2, String nombre, List<tMatricula> listaMatriculas) {
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.nombre = nombre;
        this.listaMatriculas = listaMatriculas;
    }

    public int getIdAlum() {
        return idAlum;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<tMatricula> getListaMatriculas() {
        return listaMatriculas;
    }

    public void setListaMatriculas(List<tMatricula> listaMatriculas) {
        this.listaMatriculas = listaMatriculas;
    }

    @Override
    public String toString() {
        return "tAlumnado{" +
                "idAlum=" + idAlum +
                ", ape1='" + ape1 + '\'' +
                ", ape2='" + ape2 + '\'' +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
