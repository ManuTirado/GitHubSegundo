package EntidadesHibernate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "IE_Actividades")
public class IE_Actividades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;

    @Column(name = "Descripcion")
    private String Descripcion;

    @Column(name = "FechaInicio")
    private LocalDate FechaInicio;

    @Column(name = "FechaFin")
    private LocalDate FechaFin;

    @Column(name = "Experiencia")
    private int Experiencia;



    public IE_Actividades(){}

    public IE_Actividades(String descripcion, LocalDate fechaInicio, LocalDate fechaFin, int experiencia) {
        Descripcion = descripcion;
        FechaInicio = fechaInicio;
        FechaFin = fechaFin;
        Experiencia = experiencia;
    }

    public int getID() {
        return ID;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
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

    public int getExperiencia() {
        return Experiencia;
    }

    public void setExperiencia(int experiencia) {
        Experiencia = experiencia;
    }

//    public List<IE_entrenamientos> getListaEntrenamientos() {
//        return listaEntrenamientos;
//    }
//
//    public void setListaEntrenamientos(List<IE_entrenamientos> listaEntrenamientos) {
//        this.listaEntrenamientos = listaEntrenamientos;
//    }
}
