package Entidades;

import java.sql.Timestamp;

public class clsRegistro {
    private int idRegistro;
    private int idAgenda;
    private Timestamp fechaHoraInicio;
    private Timestamp fechaHoraFin;
    private char tipo;

    public clsRegistro() {
    }

    public clsRegistro(int idRegistro, int idAgenda, Timestamp fechaHoraInicio, Timestamp fechaHoraFin, char tipo) {
        this.idRegistro = idRegistro;
        this.idAgenda = idAgenda;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.tipo = tipo;
    }

    public clsRegistro(int idAgenda, Timestamp fechaHoraInicio, Timestamp fechaHoraFin, char tipo) {
        this.idAgenda = idAgenda;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.tipo = tipo;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }

    public Timestamp getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Timestamp fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Timestamp getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Timestamp fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "clsRegistro{" +
                "idRegistro=" + idRegistro +
                ", idAgenda=" + idAgenda +
                ", fechaHoraInicio=" + fechaHoraInicio +
                ", fechaHoraFin=" + fechaHoraFin +
                ", tipo=" + tipo +
                '}';
    }
}
