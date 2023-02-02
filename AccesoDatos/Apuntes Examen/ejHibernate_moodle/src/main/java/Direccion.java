import java.io.Serializable;import javax.persistence.*;
@Entity
@Table(name="Direccion")
public class Direccion implements Serializable {
    @Id
    @Column(name = "idAlum")
    private int idAlum;
    @Column(name = "calle")
    private String calle;
    @Column(name = "numero")
    private int numero;
    @Column(name = "poblacion")
    private String poblacion;
    @Column(name = "provincia")
    private String provincia;

    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Alumnado alumnado;
public Direccion(int idAlum,String calle,int numero,String poblacion,String provincia){
    setIdAlum(idAlum);
    setCalle(calle);
    setNumero(numero);
    setPoblacion(poblacion);
    setProvincia(provincia);
}

    public Direccion() {

    }

    public int getIdAlum() {
        return idAlum;
    }

    public void setIdAlum(int idAlum) {
        this.idAlum = idAlum;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setAlumnado(Alumnado alumnado) {
        this.alumnado=alumnado;
    }
}