package EntidadesHibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "IE_Parejas")
public class IE_Parejas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;

    @Column(name = "FechaInicio")
    private LocalDate FechaInicio;

    @Column(name = "FechaFin")
    private LocalDate FechaFin;

    @ManyToOne
    @JoinColumn(name = "idEst1")
    private IE_Estudiantes estudiante1;

    @ManyToOne
    @JoinColumn(name = "idEst2")
    private IE_Estudiantes estudiante2;



    public IE_Parejas(){}

    public IE_Parejas(LocalDate fechaInicio, LocalDate fechaFin, IE_Estudiantes estudiante1, IE_Estudiantes estudiante2) {
        FechaInicio = fechaInicio;
        FechaFin = fechaFin;
        this.estudiante1 = estudiante1;
        this.estudiante2 = estudiante2;
    }

    public int getID() {
        return ID;
    }

    public LocalDate getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        FechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        FechaFin = fechaFin;
    }

    public IE_Estudiantes getEstudiante1() {
        return estudiante1;
    }

    public void setEstudiante1(IE_Estudiantes estudiante1) {
        this.estudiante1 = estudiante1;
    }

    public IE_Estudiantes getEstudiante2() {
        return estudiante2;
    }

    public void setEstudiante2(IE_Estudiantes estudiante2) {
        this.estudiante2 = estudiante2;
    }

//    public List<IE_entrenamientos> getListaEntrenamientos() {
//        return listaEntrenamientos;
//    }
//
//    public void setListaEntrenamientos(List<IE_entrenamientos> listaEntrenamientos) {
//        this.listaEntrenamientos = listaEntrenamientos;
//    }
}
