import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "Personas")
public class EntidadPersona implements Serializable {
@Id
@GeneratedValue(strategy= GenerationType.IDENTITY) //La opción más usada con MySQL
@Column(name="idPersona")
private int idPersona;

@Column(name = "nombre")
    private String nombre;
@Column(name = "saldo")
    private double saldo;

    public EntidadPersona(String nombre, double saldo) {
        setNombre(nombre);
        setSaldo(saldo);
    }

    public EntidadPersona() {

    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

