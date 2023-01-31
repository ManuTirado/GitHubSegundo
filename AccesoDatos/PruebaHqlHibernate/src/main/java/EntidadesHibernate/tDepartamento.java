package EntidadesHibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tDepartamento")
public class tDepartamento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDepartamento")
    private int idDepartamento;

    @Column(name = "nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idDepartamento")
    private List<tProfesores> listaProfesores;

    public tDepartamento() {
    }

    public tDepartamento(String nombre) {
        this.nombre = nombre;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<tProfesores> getListaProfesores() {
        return listaProfesores;
    }

    public void setListaProfesores(List<tProfesores> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    @Override
    public String toString() {
        return "tDepartamento{" +
                "idDepartamento=" + idDepartamento +
                ", nombre='" + nombre + '\'' +
                ", listaProfesores=" + listaProfesores +
                '}';
    }
}