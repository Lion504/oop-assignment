-- =============================================
-- Currency Converter Database Test Queries
-- Created: September 28, 2025
-- Purpose: Test queries for the currency converter database
-- =============================================

-- Use the currency converter database
USE currency_converter_db;

-- Query 1: Retrieve all currencies from the database
SELECT
    id,
    abbreviation,
    name,
    exchange_rate,
    created_at
FROM currencies
ORDER BY abbreviation;

-- Query 2: Retrieve the currency with abbreviation EUR
SELECT
    id,
    abbreviation,
    name,
    exchange_rate,
    created_at
FROM currencies
WHERE abbreviation = 'EUR';

-- Query 3: Retrieve the number of currencies in the database
SELECT COUNT(*) AS total_currencies
FROM currencies;

-- Query 4: Retrieve the currency with the highest exchange rate
SELECT
    id,
    abbreviation,
    name,
    exchange_rate,
    created_at
FROM currencies
WHERE exchange_rate = (SELECT MAX(exchange_rate) FROM currencies);

-- Additional helpful queries for testing:

-- Show all currencies sorted by exchange rate (highest to lowest)
SELECT
    abbreviation,
    name,
    exchange_rate
FROM currencies
ORDER BY exchange_rate DESC;

-- Show currencies with exchange rate greater than 1 (more expensive than USD)
SELECT
    abbreviation,
    name,
    exchange_rate
FROM currencies
WHERE exchange_rate > 1.0
ORDER BY exchange_rate DESC;
