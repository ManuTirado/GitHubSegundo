package Entidades;

public class clsPartido {
    private String equipoA;
    private String equipoB;
    private int golesA;
    private int golesB;

    public clsPartido() {}

    public clsPartido(String equipoA, String equipoB, int golesA, int golesB) {
        this.equipoA = equipoA;
        this.equipoB = equipoB;
        this.golesA = golesA;
        this.golesB = golesB;
    }

    public String getEquipoA() {
        return equipoA;
    }

    public void setEquipoA(String equipoA) {
        this.equipoA = equipoA;
    }

    public String getEquipoB() {
        return equipoB;
    }

    public void setEquipoB(String equipoB) {
        this.equipoB = equipoB;
    }

    public int getGolesA() {
        return golesA;
    }

    public void setGolesA(int golesA) {
        this.golesA = golesA;
    }

    public int getGolesB() {
        return golesB;
    }

    public void setGolesB(int golesB) {
        this.golesB = golesB;
    }
}
