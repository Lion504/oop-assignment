package dao;

import model.Currency;
import model.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * CurrencyDAO handles database operations for Currency objects
 * only essential methods for currency converter
 */
public class CurrencyDAO {

    /**
     * Get all currencies from the database
     * This is the main method needed for the currency converter
     * @return List of all currencies
     */
    public List<Currency> getAllCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        Connection connection = null;

        try {
            connection = DataSource.getInstance().getConnection();
            String sql = "SELECT id, abbreviation, name, exchange_rate FROM currencies ORDER BY abbreviation";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Currency currency = mapResultSetToCurrency(rs);
                currencies.add(currency);
            }

            System.out.println("Successfully loaded " + currencies.size() + " currencies from database");

        } catch (SQLException e) {
            System.err.println("Error loading currencies from database: " + e.getMessage());
            // Return empty list instead of null to prevent NullPointerException
        } finally {
            DataSource.getInstance().closeConnection(connection);
        }

        return currencies;
    }

    /**
     * Helper method to convert ResultSet row to Currency object
     * @param rs ResultSet containing currency data
     * @return Currency object
     * @throws SQLException if data access fails
     */
    private Currency mapResultSetToCurrency(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String abbreviation = rs.getString("abbreviation");
        String name = rs.getString("name");
        double exchangeRate = rs.getDouble("exchange_rate");

        return new Currency(id, abbreviation, name, exchangeRate);
    }

    /**
     * Test database connectivity by trying to load currencies
     * @return true if database is accessible, false otherwise
     */
    public boolean testDatabaseConnection() {
        try {
            List<Currency> currencies = getAllCurrencies();
            return !currencies.isEmpty();
        } catch (Exception e) {
            System.err.println("Database connection test failed: " + e.getMessage());
            return false;
        }
    }
}
