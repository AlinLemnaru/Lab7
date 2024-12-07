import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public abstract class InstrumentMuzical {
    protected String producator;
    protected int pret;

    //Constructors

    public InstrumentMuzical() {
    }

    public InstrumentMuzical(String producator, int pret) {
        this.producator = producator;
        this.pret = pret;
    }

    //getter and setter

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    //Metode

    @Override
    public String toString() {
        return producator + ", " + pret;
    }

    public abstract void descriereInstrument();
}
