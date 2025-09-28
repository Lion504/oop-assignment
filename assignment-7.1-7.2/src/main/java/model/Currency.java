package model;

/**
 * Currency class represents a currency with its code, name, and exchange rate
 * This class will be used to store currency data from the database
 */
public class Currency {
    private int id;           // Database ID (auto-generated)
    private String code;      // Currency abbreviation (e.g., USD, EUR)
    private String name;      // Full currency name (e.g., US Dollar, Euro)
    private double rate;      // Exchange rate to USD base

    /**
     * Create a new Currency with all fields (for database loading)
     */
    public Currency(int id, String code, String name, double rate) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.rate = rate;
    }

    /**
     * Create a new Currency with code and rate only (for backward compatibility)
     * This constructor is used when we don't have database ID and full name
     */
    public Currency(String code, double rate) {
        this.id = 0; // Default ID for non-database currencies
        this.code = code;
        this.name = ""; // Empty name, will be filled from database if needed
        this.rate = rate;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return code + (name.isEmpty() ? "" : " - " + name);
    }
}
