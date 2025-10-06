-- =============================================
-- Currency Converter Database Setup Script
-- Created: September 28, 2025
-- Purpose: Create database for currency converter application
-- =============================================

-- 1. Drop existing database if it exists
DROP DATABASE IF EXISTS currency_converter_db;

-- 2. Create the database
CREATE DATABASE currency_converter_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

-- Use the newly created database
USE currency_converter_db;

-- 3. Create table for storing Currency objects
CREATE TABLE currencies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    abbreviation VARCHAR(3) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    exchange_rate DECIMAL(12, 6) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    transactions VARCHAR(100) NOT NULL,

    -- Add indexes for better performance
    INDEX idx_abbreviation (abbreviation),
    INDEX idx_exchange_rate (exchange_rate)
);

-- 4. Populate the table with current exchange rates (as of September 2025)
-- USD is the base currency with rate 1.000000
INSERT INTO currencies (abbreviation, name, exchange_rate, transactions) VALUES
    ('USD', 'US Dollar', 1.000000, 'Transaction example'),
    ('EUR', 'Euro', 0.920000,'Transaction example'),
    ('GBP', 'British Pound Sterling', 0.810000,'Transaction example'),
    ('JPY', 'Japanese Yen', 149.500000,'Transaction example'),
    ('CAD', 'Canadian Dollar', 1.350000,'Transaction example'),
    ('AUD', 'Australian Dollar', 1.480000,'Transaction example'),
    ('CHF', 'Swiss Franc', 0.870000,'Transaction example'),
    ('CNY', 'Chinese Yuan', 7.250000,'Transaction example'),
    ('SEK', 'Swedish Krona', 10.450000,'Transaction example'),
    ('NOK', 'Norwegian Krone', 10.850000,'Transaction example');

-- 5. Drop existing user account if it exists
DROP USER IF EXISTS 'appuser'@'localhost';

-- 6. Create the user account appuser (MariaDB compatible syntax)
CREATE USER 'appuser'@'localhost' IDENTIFIED BY 'apppass123';

-- 7. Grant minimal required privileges to appuser
-- The application needs to read currency data and potentially update exchange rates
-- Grant permissions on ALL tables in the database (including transactions table created by JPA)
GRANT SELECT, INSERT, UPDATE, DELETE ON currency_converter_db.* TO 'appuser'@'localhost';

-- Flush privileges to ensure changes take effect
FLUSH PRIVILEGES;

-- Display success message and verification
SELECT 'Currency Converter Database Setup Complete!' AS Message;
SELECT COUNT(*) AS 'Total Currencies Inserted' FROM currencies;
SELECT * FROM currencies ORDER BY abbreviation;

CREATE TABLE IF NOT EXISTS transactions (
                                            transaction_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                            source_currency_id INT NOT NULL,
                                            target_currency_id INT NOT NULL,
                                            source_amount DOUBLE NOT NULL,
                                            target_amount DOUBLE NOT NULL,
                                            transaction_date DATETIME NOT NULL,
                                            FOREIGN KEY (source_currency_id) REFERENCES currencies(id),  -- Changed from currency_id to id
                                            FOREIGN KEY (target_currency_id) REFERENCES currencies(id),   -- Changed from currency_id to id
                                            INDEX idx_source_currency (source_currency_id),
                                            INDEX idx_target_currency (target_currency_id),
                                            INDEX idx_transaction_date (transaction_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;