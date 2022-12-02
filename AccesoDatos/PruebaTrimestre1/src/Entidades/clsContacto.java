package Entidades;

public class clsContacto {
    private int idAgenda;
    private String nombre;
    private String tfno;

    public clsContacto() {
    }

    public clsContacto(int idAgenda, String nombre, String tfno) {
        this.idAgenda = idAgenda;
        this.nombre = nombre;
        this.tfno = tfno;
    }
    public clsContacto(String nombre, String tfno) {
        this.nombre = nombre;
        this.tfno = tfno;
    }

    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTfno() {
        return tfno;
    }

    public void setTfno(String tfno) {
        this.tfno = tfno;
    }
}
