package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * JPAUtil replaces DataSource for JPA-based database operations
 * Uses Singleton pattern to manage EntityManagerFactory
 */
public class JPAUtil {
    private static EntityManagerFactory emf;

    /**
     * Initialize EntityManagerFactory with database properties
     */
    public static void initEntityManagerFactory() {
        try {
            // Load database properties from file
            Properties dbProps = loadDatabaseProperties();

            // Validate that required properties are present
            validateRequiredProperties(dbProps);

            // Override persistence.xml properties with values from database.properties
            Map<String, String> props = new HashMap<>();
            props.put("jakarta.persistence.jdbc.user", dbProps.getProperty("db.username"));
            props.put("jakarta.persistence.jdbc.password", dbProps.getProperty("db.password"));
            props.put("jakarta.persistence.jdbc.url", dbProps.getProperty("db.url"));

            // Create EntityManagerFactory
            emf = Persistence.createEntityManagerFactory("currencyPU", props);
            System.out.println("JPA EntityManagerFactory initialized successfully");

        } catch (Exception e) {
            System.err.println("Error initializing JPA: " + e.getMessage());
            throw new RuntimeException("Failed to initialize JPA", e);
        }
    }

    /**
     * Load database properties from configuration file
     * @return Properties object with database configuration
     */
    private static Properties loadDatabaseProperties() {
        Properties props = new Properties();

        try (InputStream input = JPAUtil.class.getClassLoader()
                .getResourceAsStream("database.properties")) {

            if (input == null) {
                throw new RuntimeException("database.properties file not found! Please ensure the file exists in src/main/resources/");
            }

            props.load(input);
            System.out.println("Database configuration loaded successfully from properties file");

        } catch (IOException e) {
            throw new RuntimeException("Error loading database properties: " + e.getMessage());
        }

        return props;
    }

    /**
     * Validate that all required database properties are present
     * @param props Properties to validate
     */
    private static void validateRequiredProperties(Properties props) {
        String[] requiredProps = {"db.url", "db.username", "db.password"};

        for (String prop : requiredProps) {
            String value = props.getProperty(prop);
            if (value == null || value.trim().isEmpty()) {
                throw new RuntimeException("Required property '" + prop + "' is missing or empty in database.properties");
            }
        }
    }

    /**
     * Get an EntityManager instance
     * @return EntityManager for database operations
     */
    public static EntityManager getEntityManager() {
        if (emf == null) {
            initEntityManagerFactory();
        }
        return emf.createEntityManager();
    }

    /**
     * Close the EntityManagerFactory
     */
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("JPA EntityManagerFactory closed successfully");
        }
    }
}
