package model;

import java.util.List;

/**
 * CurrencyConverter handles the logic for converting between different currencies
 * Now works with currencies loaded from the database
 */
public class CurrencyConverter {
    private List<Currency> currencies;

    /**
     * Create a converter with a list of available currencies
     * @param currencies List of currencies (usually loaded from database)
     */
    public CurrencyConverter(List<Currency> currencies) {
        this.currencies = currencies;
    }

    /**
     * Default constructor for backward compatibility
     */
    public CurrencyConverter() {
        // Empty constructor - currencies will be set later if needed
    }

    /**
     * Convert an amount from one currency to another
     *
     * @param amount The amount of money to convert
     * @param from The currency we're converting from
     * @param to The currency we're converting to
     * @return The converted amount
     */
    public double convert(double amount, Currency from, Currency to) {
        // Convert from 'from' currency to USD (base), then to 'to' currency
        // Formula: amount * (to_rate / from_rate)
        return amount * (to.getRate() / from.getRate());
    }

    /**
     * Get the list of available currencies
     * @return List of currencies
     */
    public List<Currency> getCurrencies() {
        return currencies;
    }

    /**
     * Set the list of available currencies
     * @param currencies List of currencies
     */
    public void setCurrencies(List<Currency> currencies) {
        this.currencies = currencies;
    }
}
