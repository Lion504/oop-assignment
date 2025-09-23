package model;

public class Currency {
    private String code;
    private double rate;

    /**
     * Create a new Currency with a code and rate
     */
    public Currency(String code, double rate) {
        this.code = code;
        this.rate = rate;
    }

    public String getCode() {
        return code;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return code;
    }
}
