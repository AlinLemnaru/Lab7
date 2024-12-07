import java.util.Objects;

public class Chitara extends InstrumentMuzical {
    private TipChitara tip_chitara;
    private int nr_corzi;

    //Constructors

    public Chitara() {
    }

    public Chitara(String producator, int pret, TipChitara tip_chitara, int nr_corzi) {
        super(producator, pret);
        this.tip_chitara = tip_chitara;
        this.nr_corzi = nr_corzi;
    }

    //getter and setter

    public TipChitara getTip_chitara() {
        return tip_chitara;
    }

    public void setTip_chitara(TipChitara tip_chitara) {
        this.tip_chitara = tip_chitara;
    }

    public int getNr_corzi() {
        return nr_corzi;
    }

    public void setNr_corzi(int nr_corzi) {
        this.nr_corzi = nr_corzi;
    }

    //Metode

    @Override
    public String toString() {
        return super.toString() + ", " + tip_chitara.toString() + ", " + nr_corzi;
    }

    @Override
    public void descriereInstrument() {
        System.out.println("Chitara " + tip_chitara + ":\n" + nr_corzi + " corzi,\n"
                + "produsa de " + producator + ",\npret " + pret + " RON\n----------");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chitara chitara = (Chitara) o;
        return pret == chitara.pret &&
                nr_corzi == chitara.nr_corzi &&
                producator.equals(chitara.producator) &&
                tip_chitara == chitara.tip_chitara;
    }

    @Override
    public int hashCode() {
        return Objects.hash(producator, pret, tip_chitara, nr_corzi);
    }
}
