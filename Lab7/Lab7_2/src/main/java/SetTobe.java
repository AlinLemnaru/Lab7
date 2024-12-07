import java.util.Objects;

public class SetTobe extends InstrumentMuzical {
    private TipTobe tip_tobe;
    private int nr_tobe;
    private int nr_cinele;

    //Constructors

    public SetTobe() {
    }

    public SetTobe(String producator, int pret, TipTobe tip_tobe, int nr_tobe, int nr_cinele) {
        super(producator, pret);
        this.tip_tobe = tip_tobe;
        this.nr_tobe = nr_tobe;
        this.nr_cinele = nr_cinele;
    }

    //getter and setter

    public TipTobe getTip_tobe() {
        return tip_tobe;
    }

    public void setTip_tobe(TipTobe tip_tobe) {
        this.tip_tobe = tip_tobe;
    }

    public int getNr_tobe() {
        return nr_tobe;
    }

    public void setNr_tobe(int nr_tobe) {
        this.nr_tobe = nr_tobe;
    }

    public int getNr_cinele() {
        return nr_cinele;
    }

    public void setNr_cinele(int nr_cinele) {
        this.nr_cinele = nr_cinele;
    }

    //Metode

    @Override
    public String toString() {
        return super.toString() + ", " + tip_tobe.toString() + ", " + nr_tobe + ", " + nr_cinele;
    }

    @Override
    public void descriereInstrument() {
        System.out.println("Tobe " + tip_tobe + ":\n" + nr_tobe + " tobe,\n" + nr_cinele + " cinele,\n"
                + "produsa de " + producator + ",\npret " + pret + " RON\n----------");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SetTobe setTobe = (SetTobe) o;
        return pret == setTobe.pret &&
                nr_tobe == setTobe.nr_tobe &&
                nr_cinele == setTobe.nr_cinele &&
                producator.equals(setTobe.producator) &&
                tip_tobe == setTobe.tip_tobe;
    }

    @Override
    public int hashCode() {
        return Objects.hash(producator, pret, tip_tobe, nr_tobe, nr_cinele);
    }
}
