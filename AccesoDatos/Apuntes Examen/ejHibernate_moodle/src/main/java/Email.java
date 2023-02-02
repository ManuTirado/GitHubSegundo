import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Email")
public class Email implements Serializable {
    @Id
    @Column(name = "idEmail")
    private int idEmail;
    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "idAlum")
    private Alumnado alumnado;
    public Email(){}
    public Email(int idEmail,String email, Alumnado alumnado ){
        setIdEmail(idEmail);
        setEmail(email);
        setAlumnado(alumnado);
    }
    public int getIdEmail() {
        return idEmail;
    }

    public void setIdEmail(int idEmail) {
        this.idEmail = idEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Alumnado getAlumnado() {
        return alumnado;
    }

    public void setAlumnado(Alumnado alumnado) {
        this.alumnado = alumnado;
    }


}