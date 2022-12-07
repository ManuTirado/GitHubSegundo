package Entidades;

import java.util.Objects;

public class clsEquipo {
    private String idEquipo;
    private int ganados;
    private int empatados;
    private int perdidos;
    private int golesMarcados;
    private int golesRecibidos;

    public clsEquipo() {
    }

    public clsEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    @Override
    public String toString() {
        return "clsEquipo{" +
                "idEquipo='" + idEquipo + '\'' +
                ", ganados=" + ganados +
                ", empatados=" + empatados +
                ", perdidos=" + perdidos +
                ", golesMarcados=" + golesMarcados +
                ", golesRecibidos=" + golesRecibidos +
                '}';
    }

    public String getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(String idEquipo) {
        this.idEquipo = idEquipo;
    }

    public int getGanados() {
        return ganados;
    }

    public void setGanados(int ganados) {
        this.ganados = ganados;
    }

    public int getEmpatados() {
        return empatados;
    }

    public void setEmpatados(int empatados) {
        this.empatados = empatados;
    }

    public int getPerdidos() {
        return perdidos;
    }

    public void setPerdidos(int perdidos) {
        this.perdidos = perdidos;
    }

    public int getGolesMarcados() {
        return golesMarcados;
    }

    public void setGolesMarcados(int golesMarcados) {
        this.golesMarcados = golesMarcados;
    }

    public int getGolesRecibidos() {
        return golesRecibidos;
    }

    public void setGolesRecibidos(int golesRecibidos) {
        this.golesRecibidos = golesRecibidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        clsEquipo equipo = (clsEquipo) o;
        return Objects.equals(idEquipo, equipo.idEquipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEquipo);
    }
}
