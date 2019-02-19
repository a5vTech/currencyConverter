package dk.tangsolutions.currencyconverter;
public class Rates{
    private String id;
    private Double rate;


    public Rates() {
    }

    public Rates(String id, Double rate) {
        this.id = id;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}