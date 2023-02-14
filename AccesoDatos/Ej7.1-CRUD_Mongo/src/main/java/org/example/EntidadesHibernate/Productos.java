package org.example.EntidadesHibernate;

import java.math.BigDecimal;
import java.util.List;

public class Productos {
    private int idProducto;
    private String Denominacion;
    private BigDecimal Precio;

    public Productos(){}

    public Productos(String denominacion, BigDecimal precio) {
        Denominacion = denominacion;
        Precio = precio;
    }
    public Productos(int idProducto, String denominacion, BigDecimal precio) {
        this.idProducto = idProducto;
        Denominacion = denominacion;
        Precio = precio;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getDenominacion() {
        return Denominacion;
    }

    public void setDenominacion(String denominacion) {
        Denominacion = denominacion;
    }

    public BigDecimal getPrecio() {
        return Precio;
    }

    public void setPrecio(BigDecimal precio) {
        Precio = precio;
    }

    @Override
    public String toString() {
        return idProducto + " -> Denominación: '" + Denominacion + '\'' +
                ", Precio: " + Precio + "€";
    }
}
