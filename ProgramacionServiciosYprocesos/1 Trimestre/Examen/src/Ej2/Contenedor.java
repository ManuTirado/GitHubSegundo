package Ej2;

public class Contenedor {
    private Integer dato = 0;
    private Integer colectaTotal = 0;

    synchronized public Integer get(Integer cantidad) {
        this.dato -= cantidad;
        System.out.println("Colecta: " + this.dato + "€");
        return cantidad;
    }

    synchronized public Integer consult() {
        Integer result = this.dato;
        return result;
    }

    synchronized public void add(Integer valor) {
        this.dato += valor;
        this.colectaTotal += valor;
        System.out.println("Colecta: " + this.dato + "€");
    }

    synchronized public boolean finColecta() {
        if (colectaTotal < 2000) {
            return false;
        } else {
            return true;
        }
    }

    synchronized public Integer consultColectaTotal() {
        Integer result = this.colectaTotal;
        return result;
    }
}
