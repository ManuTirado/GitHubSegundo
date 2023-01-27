package EntidadesHibernate;

import javax.persistence.*;

@Entity
@Table(name = "tMatricula")
public class tMatricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMat")
    private int idMat;

    @ManyToOne
    @JoinColumn(name = "idAlum")
    private tAlumnado alumnado;

    @ManyToOne
    @JoinColumn(name = "idAsig")
    private tAsignaturas asignatura;

    public tMatricula(){}

    public tMatricula(tAlumnado alumnado, tAsignaturas asignatura) {
        this.alumnado = alumnado;
        this.asignatura = asignatura;
    }

    public int getIdMat() {
        return idMat;
    }

    public tAlumnado getAlumnado() {
        return alumnado;
    }

    public void setAlumnado(tAlumnado alumnado) {
        this.alumnado = alumnado;
    }

    public tAsignaturas getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(tAsignaturas asignatura) {
        this.asignatura = asignatura;
    }
}
