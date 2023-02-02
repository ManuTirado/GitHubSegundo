package EntidadesHibernate;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "VQ_Naves")
public class VQ_Naves {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ganadero")
    private String ganadero;

    @Column(name = "ubicacion")
    private String ubicacion;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name="id")
    private List<VQ_Ganado> listaGanado;

    public VQ_Naves(){}

    public VQ_Naves(String ganadero, String ubicacion) {
        this.ganadero = ganadero;
        this.ubicacion = ubicacion;
    }

    public VQ_Naves(int id, String ganadero, String ubicacion) {
        this.id = id;
        this.ganadero = ganadero;
        this.ubicacion = ubicacion;
    }

    public int getId() {
        return id;
    }

    public String getGanadero() {
        return ganadero;
    }

    public void setGanadero(String ganadero) {
        this.ganadero = ganadero;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<VQ_Ganado> getListaGanado() {
        return listaGanado;
    }

    public void setListaGanado(List<VQ_Ganado> listaGanado) {
        this.listaGanado = listaGanado;
    }
}
