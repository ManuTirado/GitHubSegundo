package EntidadesHibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "IE_Estudiantes")
@NamedQueries({
        @NamedQuery(name = "mejoresEstudiantes", query = "select est FROM IE_Estudiantes as est join IE_entrenamientos ent on est.ID = ent.bestEst.ID group by est.ID order by count(*) desc")
})
public class IE_Estudiantes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;

    @Column(name = "Nombre")
    private String Nombre;

    @Column(name = "Apellidos")
    private String Apellidos;

    @Column(name = "Curso")
    private int Curso;

    @Column(name = "ExpAcumulada")
    private int ExpAcumulada;

    public IE_Estudiantes() {
    }

    public IE_Estudiantes(String nombre, String apellidos, int curso, int expAcumulada) {
        Nombre = nombre;
        Apellidos = apellidos;
        Curso = curso;
        ExpAcumulada = expAcumulada;
    }

    public int getID() {
        return ID;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }

    public int getCurso() {
        return Curso;
    }

    public void setCurso(int curso) {
        Curso = curso;
    }

    public int getExpAcumulada() {
        return ExpAcumulada;
    }

    public void setExpAcumulada(int expAcumulada) {
        ExpAcumulada = expAcumulada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IE_Estudiantes that = (IE_Estudiantes) o;
        return ID == that.ID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
