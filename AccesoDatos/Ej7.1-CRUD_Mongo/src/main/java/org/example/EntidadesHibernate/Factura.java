package org.example.EntidadesHibernate;

import java.math.BigDecimal;
import java.util.List;

public class Factura {
    private int idFactura;
    private int idMesa;
    private String tipoPago;
    private BigDecimal Importe;

    public Factura() {
    }

    public Factura(String tipoPago, BigDecimal importe) {
        this.tipoPago = tipoPago;
        this.Importe = importe;
    }
    public Factura(int idMesa, String tipoPago, BigDecimal importe) {
        this.idMesa = idMesa;
        this.tipoPago = tipoPago;
        this.Importe = importe;
    }

    public int getIdFactura() {
        return idFactura;
    }

    public int getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(int idMesa) {
        this.idMesa = idMesa;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public BigDecimal getImporte() {
        return Importe;
    }

    public void setImporte(BigDecimal importe) {
        Importe = importe;
    }

    @Override
    public String toString() {
        return idFactura + " -> idMesa: " + idMesa +
                ", TipoPago: '" + tipoPago + '\'' +
                ", Importe: " + Importe + "€";
    }
}
