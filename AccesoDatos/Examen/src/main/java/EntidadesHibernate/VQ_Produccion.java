package EntidadesHibernate;

import javax.persistence.*;

@Entity
@Table(name = "VQ_Produccion")
public class VQ_Produccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProduccion")
    private int idProduccion;

    @Column(name = "mes_produccion")
    private int mes_produccion;

    @Column(name = "litros")
    private int litros;

    @Column(name = "year_produccion")
    private int year_produccion;

    @ManyToOne
    @JoinColumn(name = "idVaca")
    private VQ_Ganado vaca;

    public VQ_Produccion(){}

    public VQ_Produccion(int mes_produccion, int litros, int year_produccion, VQ_Ganado vaca) {
        this.mes_produccion = mes_produccion;
        this.litros = litros;
        this.year_produccion = year_produccion;
        this.vaca = vaca;
    }

    public VQ_Produccion(int idProduccion, int mes_produccion, int litros, int year_produccion, VQ_Ganado vaca) {
        this.idProduccion = idProduccion;
        this.mes_produccion = mes_produccion;
        this.litros = litros;
        this.year_produccion = year_produccion;
        this.vaca = vaca;
    }

    public int getIdProduccion() {
        return idProduccion;
    }

    public int getMes_produccion() {
        return mes_produccion;
    }

    public void setMes_produccion(int mes_produccion) {
        this.mes_produccion = mes_produccion;
    }

    public int getLitros() {
        return litros;
    }

    public void setLitros(int litros) {
        this.litros = litros;
    }

    public int getYear_produccion() {
        return year_produccion;
    }

    public void setYear_produccion(int year_produccion) {
        this.year_produccion = year_produccion;
    }

    public VQ_Ganado getVaca() {
        return vaca;
    }

    public void setVaca(VQ_Ganado vaca) {
        this.vaca = vaca;
    }
}
