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

    -- Add indexes for better performance
    INDEX idx_abbreviation (abbreviation),
    INDEX idx_exchange_rate (exchange_rate)
);

-- 4. Populate the table with current exchange rates (as of September 2025)
-- USD is the base currency with rate 1.000000
INSERT INTO currencies (abbreviation, name, exchange_rate) VALUES
    ('USD', 'US Dollar', 1.000000),
    ('EUR', 'Euro', 0.920000),
    ('GBP', 'British Pound Sterling', 0.810000),
    ('JPY', 'Japanese Yen', 149.500000),
    ('CAD', 'Canadian Dollar', 1.350000),
    ('AUD', 'Australian Dollar', 1.480000),
    ('CHF', 'Swiss Franc', 0.870000),
    ('CNY', 'Chinese Yuan', 7.250000),
    ('SEK', 'Swedish Krona', 10.450000),
    ('NOK', 'Norwegian Krone', 10.850000);

-- 5. Drop existing user account if it exists
DROP USER IF EXISTS 'appuser'@'localhost';

-- 6. Create the user account appuser (MariaDB compatible syntax)
CREATE USER 'appuser'@'localhost' IDENTIFIED BY 'apppass123';

-- 7. Grant minimal required privileges to appuser
-- The application needs to read currency data and potentially update exchange rates
GRANT SELECT ON currency_converter_db.currencies TO 'appuser'@'localhost';
GRANT INSERT ON currency_converter_db.currencies TO 'appuser'@'localhost';
GRANT UPDATE ON currency_converter_db.currencies TO 'appuser'@'localhost';
GRANT DELETE ON currency_converter_db.currencies TO 'appuser'@'localhost';

-- Flush privileges to ensure changes take effect
FLUSH PRIVILEGES;

-- Display success message and verification
SELECT 'Currency Converter Database Setup Complete!' AS Message;
SELECT COUNT(*) AS 'Total Currencies Inserted' FROM currencies;
SELECT * FROM currencies ORDER BY abbreviation;
