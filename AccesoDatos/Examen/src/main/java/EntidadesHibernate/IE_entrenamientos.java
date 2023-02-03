package EntidadesHibernate;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "IE_entrenamientos")
public class IE_entrenamientos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;

    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "idAct")
    private IE_Actividades Actividad;

    @ManyToOne
    @JoinColumn(name = "theBest")
    private IE_Estudiantes bestEst;

    @ManyToOne
    @JoinColumn(name = "idPar")
    private IE_Parejas pareja;

    public IE_entrenamientos(){}

    public IE_entrenamientos(LocalDate fecha, IE_Actividades actividad, IE_Estudiantes bestEst, IE_Parejas pareja) {
        this.fecha = fecha;
        Actividad = actividad;
        this.bestEst = bestEst;
        this.pareja = pareja;
    }

    public int getID() {
        return ID;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public IE_Actividades getActividad() {
        return Actividad;
    }

    public void setActividad(IE_Actividades actividad) {
        Actividad = actividad;
    }

    public IE_Estudiantes getBestEst() {
        return bestEst;
    }

    public void setBestEst(IE_Estudiantes bestEst) {
        this.bestEst = bestEst;
    }

    public IE_Parejas getPareja() {
        return pareja;
    }

    public void setPareja(IE_Parejas pareja) {
        this.pareja = pareja;
    }
}
