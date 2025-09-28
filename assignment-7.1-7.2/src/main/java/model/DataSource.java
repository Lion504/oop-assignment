package model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DataSource class handles database connections
 * Uses Singleton pattern to ensure only one instance manages connections
 * Now reads credentials from properties file for security
 */
public class DataSource {
    // Database connection details - loaded from properties file
    private final String DB_URL;
    private final String USER;
    private final String PASSWORD;

    // Singleton instance
    private static DataSource instance;

    /**
     * Private constructor to prevent direct instantiation
     * Loads database configuration from properties file
     */
    private DataSource() {
        Properties props = loadDatabaseProperties();

        // Load configuration from properties file
        DB_URL = props.getProperty("db.url");
        USER = props.getProperty("db.username");
        PASSWORD = props.getProperty("db.password");

        // Load MariaDB JDBC driver
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MariaDB JDBC Driver not found: " + e.getMessage());
        }
    }

    /**
     * Load database properties from configuration file
     * @return Properties object with database configuration
     */
    private Properties loadDatabaseProperties() {
        Properties props = new Properties();

        try (InputStream input = getClass().getClassLoader()
                .getResourceAsStream("database.properties")) {
            props.load(input);
            System.out.println("Database configuration loaded successfully from properties file");

        } catch (IOException e) {
            System.err.println("Error loading database properties: " + e.getMessage());
            System.err.println("Using default database configuration");
        }

        return props;
    }

    /**
     * Get the singleton instance of DataSource
     * @return DataSource instance
     */
    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    /**
     * Get a database connection
     * @return Connection to the database
     * @throws SQLException if connection fails
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    /**
     * Close a database connection safely
     * @param connection Connection to close
     */
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed successfully");
            } catch (SQLException e) {
                System.err.println("Error closing database connection: " + e.getMessage());
            }
        }
    }

    /**
     * Test the database connection
     * @return true if connection is successful, false otherwise
     */
    public boolean testConnection() {
        try (Connection connection = getConnection()) {
            System.out.println("Database connection test successful!");
            return true;
        } catch (SQLException e) {
            System.err.println("Database connection test failed: " + e.getMessage());
            return false;
        }
    }
}
