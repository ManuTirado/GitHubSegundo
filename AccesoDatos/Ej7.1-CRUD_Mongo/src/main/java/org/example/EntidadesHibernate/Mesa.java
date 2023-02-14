package org.example.EntidadesHibernate;

import java.util.List;

public class Mesa {
    private int idMesa;
    private int numComensales;
    private int reserva;

    public Mesa() {
    }

    public Mesa(int numComensales, int reserva) {
        this.numComensales = numComensales;
        this.reserva = reserva;
    }

    public Mesa(int idMesa, int numComensales, int reserva) {
        this.idMesa = idMesa;
        this.numComensales = numComensales;
        this.reserva = reserva;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public int getNumComensales() {
        return numComensales;
    }

    public void setNumComensales(int numComensales) {
        this.numComensales = numComensales;
    }

    public int getReserva() {
        return reserva;
    }

    public void setReserva(int reserva) {
        this.reserva = reserva;
    }

    @Override
    public String toString() {
        return idMesa + " -> NumComensales: " + numComensales +
                ", Reserva: " + reserva;
    }
}
