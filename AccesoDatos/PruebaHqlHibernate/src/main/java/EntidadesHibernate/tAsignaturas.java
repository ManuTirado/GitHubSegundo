package EntidadesHibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tAsignaturas")
public class tAsignaturas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAsig")
    private int idAsig;

    @Column(name = "curso")
    private int curso;

    @Column(name = "horas")
    private int horas;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idProf")
    private tProfesores profesor;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="idAsig")
    private List<tMatricula> listaMatriculas;

    public tAsignaturas() {}

    public tAsignaturas(int idAsig, int curso, int horas, String nombre, tProfesores profesor) {
        this.idAsig = idAsig;
        this.curso = curso;
        this.horas = horas;
        this.nombre = nombre;
        this.profesor = profesor;
    }

    public tAsignaturas(int idAsig, int curso, int horas, String nombre, tProfesores profesor, List<tMatricula> matriculas) {
        this.idAsig = idAsig;
        this.curso = curso;
        this.horas = horas;
        this.nombre = nombre;
        this.profesor = profesor;
        this.listaMatriculas = matriculas;
    }

    public int getIdAsig() {
        return idAsig;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public tProfesores getProfesor() {
        return profesor;
    }

    public void setProfesor(tProfesores profesor) {
        this.profesor = profesor;
    }

    public List<tMatricula> getListaMatriculas() {
        return listaMatriculas;
    }

    public void setListaMatriculas(List<tMatricula> listaMatriculas) {
        this.listaMatriculas = listaMatriculas;
    }
}
