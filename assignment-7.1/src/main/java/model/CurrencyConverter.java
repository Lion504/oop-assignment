package model;
public class CurrencyConverter {

    /**
     * Convert an amount from one currency to another
     *
     * @param amount The amount of money to convert
     * @param from The currency we're converting from
     * @param to The currency we're converting to
     * @return The converted amount
     */
    public double convert(double amount, Currency from, Currency to) {

        return amount * (to.getRate() / from.getRate());

        // For example:
        // If we convert 100 USD to EUR:
        // USD rate = 1.0, EUR rate = 0.91
        // 100 * (0.91 / 1.0) = 91 EUR
    }

}
