package model;

import jakarta.persistence.*;

/**
 * Currency class represents a currency with its code, name, and exchange rate
 * This class is now a JPA entity that maps to the currencies table
 */
@Entity
@Table(name = "currencies")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;           // Database ID (auto-generated)

    @Column(name = "abbreviation")
    private String code;      // Currency abbreviation (e.g., USD, EUR)

    @Column(name = "name")
    private String name;      // Full currency name (e.g., US Dollar, Euro)

    @Column(name = "exchange_rate")
    private double rate;      // Exchange rate to USD base

    // Flag emoji for the currency (not stored in database, set programmatically)
    @Transient
    private String flagEmoji;

    /**
     * Default constructor required by JPA
     */
    public Currency() {
    }

    /**
     * This method is called after JPA loads the entity from database
     * It ensures the flag emoji is set for database-loaded currencies
     */
    @PostLoad
    private void onLoad() {
        setFlagEmoji();
    }

    /**
     * Create a new Currency with all fields (for database loading)
     */
    public Currency(int id, String code, String name, double rate) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.rate = rate;
        setFlagEmoji(); // Set flag based on currency code
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
        setFlagEmoji(); // Set flag based on currency code
    }

    /**
     * Set flag icon URL based on currency code
     */
    private void setFlagEmoji() {
        if (code == null) {
            this.flagEmoji = "https://flagcdn.com/16x12/xx.png"; // Default unknown flag
            return;
        }

        // Map currency codes to country codes for flag icons
        String countryCode = getCountryCodeFromCurrency(code.toUpperCase());

        // Use flagcdn.com for high-quality flag icons (16x12 pixels, perfect for dropdowns)
        this.flagEmoji = "https://flagcdn.com/16x12/" + countryCode + ".png";
    }

    /**
     * Map currency codes to ISO country codes for flag icons
     * @param currencyCode Currency code (e.g., USD, EUR)
     * @return Country code for flag API (e.g., us, eu)
     */
    private String getCountryCodeFromCurrency(String currencyCode) {
        switch (currencyCode) {
            case "USD": return "us"; // United States
            case "EUR": return "eu"; // European Union
            case "GBP": return "gb"; // United Kingdom
            case "JPY": return "jp"; // Japan
            case "CAD": return "ca"; // Canada
            case "AUD": return "au"; // Australia
            case "CHF": return "ch"; // Switzerland
            case "CNY": return "cn"; // China
            case "SEK": return "se"; // Sweden
            case "NOK": return "no"; // Norway
            case "DKK": return "dk"; // Denmark
            case "PLN": return "pl"; // Poland
            case "CZK": return "cz"; // Czech Republic
            case "HUF": return "hu"; // Hungary
            case "RUB": return "ru"; // Russia
            case "INR": return "in"; // India
            case "KRW": return "kr"; // South Korea
            case "SGD": return "sg"; // Singapore
            case "HKD": return "hk"; // Hong Kong
            case "NZD": return "nz"; // New Zealand
            case "MXN": return "mx"; // Mexico
            case "BRL": return "br"; // Brazil
            case "ZAR": return "za"; // South Africa
            case "TRY": return "tr"; // Turkey
            default: return "xx";    // Unknown country
        }
    }

    /**
     * Get flag icon URL for this currency
     * @return URL string for the flag icon
     */
    public String getFlagIconUrl() {
        return flagEmoji; // Now contains URL instead of emoji
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
        setFlagEmoji(); // Ensure flag is updated when code changes
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

    public String getFlagEmoji() {
        return flagEmoji;
    }

    @Override
    public String toString() {
        // Don't include the URL in toString since the cell factory handles image display
        return code + (name.isEmpty() ? "" : " - " + name);
    }
}
