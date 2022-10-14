package Entidades;
public class Producto {
    private String descripcion;
    private double precio_unidad, unidades, descuento;

    public Producto(String descripcion, double precio_unidad, double unidades, double descuento) {
        this.descripcion = descripcion;
        this.precio_unidad = precio_unidad;
        this.unidades = unidades;
        this.descuento = descuento;
    }

    public Producto() {
        this.unidades = 1;
        this.descuento = 0;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio_unidad() {
        return precio_unidad;
    }

    public double getUnidades() {
        return unidades;
    }

    public double getDescuento() {
        return descuento;
    }
}
