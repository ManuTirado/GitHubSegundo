package EntidadesHibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "VQ_Ganado")
public class VQ_Ganado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int ID;

    @Column(name = "fechaEntrada")
    private Date fechaEntrada;

    @Column(name = "fechaSacrificio")
    private Date fechaSacrificio;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "idNave")
    private VQ_Naves nave;

    @ManyToOne
    @JoinColumn(name = "id_madre")
    private VQ_Ganado madre;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="ID")
    private List<VQ_Ganado> listaHijos;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="ID")
    private List<VQ_Produccion> listaProduccion;

    public VQ_Ganado(){}

    public VQ_Ganado(Date fechaEntrada, Date fechaSacrificio, String nombre, VQ_Naves nave, VQ_Ganado madre) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSacrificio = fechaSacrificio;
        this.nombre = nombre;
        this.nave = nave;
        this.madre = madre;
    }

    public VQ_Ganado(int ID, Date fechaEntrada, Date fechaSacrificio, String nombre, VQ_Naves nave, VQ_Ganado madre) {
        this.ID = ID;
        this.fechaEntrada = fechaEntrada;
        this.fechaSacrificio = fechaSacrificio;
        this.nombre = nombre;
        this.nave = nave;
        this.madre = madre;
    }

    public int getID() {
        return ID;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSacrificio() {
        return fechaSacrificio;
    }

    public void setFechaSacrificio(Date fechaSacrificio) {
        this.fechaSacrificio = fechaSacrificio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public VQ_Naves getNave() {
        return nave;
    }

    public void setNave(VQ_Naves nave) {
        this.nave = nave;
    }

    public VQ_Ganado getMadre() {
        return madre;
    }

    public void setMadre(VQ_Ganado madre) {
        this.madre = madre;
    }

    public List<VQ_Ganado> getListaHijos() {
        return listaHijos;
    }

    public void setListaHijos(List<VQ_Ganado> listaHijos) {
        this.listaHijos = listaHijos;
    }

    public List<VQ_Produccion> getListaProduccion() {
        return listaProduccion;
    }

    public void setListaProduccion(List<VQ_Produccion> listaProduccion) {
        this.listaProduccion = listaProduccion;
    }
}
