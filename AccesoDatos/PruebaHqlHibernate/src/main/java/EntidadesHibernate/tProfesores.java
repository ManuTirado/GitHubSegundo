package EntidadesHibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tProfesores")
@NamedQueries({
        @NamedQuery(name = "listaDocente", query = "from tProfesores"),
        @NamedQuery(name = "listaPorNombre", query = "from tProfesores p where p.nombre = :nombre")
})
public class tProfesores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProf")
    private int idProf;

    @Column(name = "ape1")
    private String ape1;

    @Column(name = "ape2")
    private String ape2;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAsig")
    private List<tAsignaturas> listaAsignaturas;

    @ManyToOne
    @JoinColumn(name = "idDepartamento")
    private tDepartamento departamento;

    public tProfesores() {
    }

    public tProfesores(String ape1, String ape2, String nombre) {
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.nombre = nombre;
    }

    public tProfesores(String ape1, String ape2, String nombre, List<tAsignaturas> asignaturas) {
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.nombre = nombre;
        this.listaAsignaturas = asignaturas;
    }

    public int getIdProf() {
        return idProf;
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

    public List<tAsignaturas> getListaAsignaturas() {
        return listaAsignaturas;
    }

    public void setListaAsignaturas(List<tAsignaturas> listaAsignaturas) {
        this.listaAsignaturas = listaAsignaturas;
    }

    public tDepartamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(tDepartamento departamento) {
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        return "tProfesores { " +
                "idProf=" + idProf +
                ", ape1='" + ape1 + '\'' +
                ", ape2='" + ape2 + '\'' +
                ", nombre='" + nombre + " }";
    }
}
