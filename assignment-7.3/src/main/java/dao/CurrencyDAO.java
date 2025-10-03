package dao;

import model.Currency;
import util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * CurrencyDAO handles database operations for Currency objects
 * Now uses JPA instead of JDBC for database operations
 */
public class CurrencyDAO {

    /**
     * Get all currencies from the database using JPA
     * This is the main method needed for the currency converter
     * @return List of all currencies
     */
    public List<Currency> getAllCurrencies() {
        List<Currency> currencies = new ArrayList<>();
        EntityManager em = null;

        try {
            em = JPAUtil.getEntityManager();
            currencies = em.createQuery("SELECT c FROM Currency c ORDER BY c.code", Currency.class)
                          .getResultList();

            System.out.println("Successfully loaded " + currencies.size() + " currencies from database using JPA");

        } catch (Exception e) {
            System.err.println("Error loading currencies from database: " + e.getMessage());
            // Return empty list instead of null to prevent NullPointerException
        } finally {
            // Close EntityManager to free resources
            if (em != null) {
                em.close();
            }
        }

        return currencies;
    }

    /**
     * Insert a new currency into the database using JPA
     * @param currency Currency object to insert
     * @throws RuntimeException if insertion fails
     */
    public void insertCurrency(Currency currency) {
        EntityManager em = null;
        EntityTransaction tx = null;

        try {
            em = JPAUtil.getEntityManager();
            tx = em.getTransaction();

            tx.begin();
            em.persist(currency);
            tx.commit();

            System.out.println("Successfully inserted currency: " + currency.getCode() + " - " + currency.getName());

        } catch (Exception e) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
                System.err.println("Transaction rolled back due to error");
            }
            System.err.println("Error inserting currency: " + e.getMessage());
            throw new RuntimeException("Failed to insert currency: " + e.getMessage(), e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Test database connectivity by trying to load currencies using JPA
     * @return true if database is accessible, false otherwise
     */
    public boolean testDatabaseConnection() {
        try {
            List<Currency> currencies = getAllCurrencies();
            return !currencies.isEmpty();
        } catch (Exception e) {
            System.err.println("JPA database connection test failed: " + e.getMessage());
            return false;
        }
    }
}
