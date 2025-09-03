package model;

public class Book {
    private String title;
    private String author;
    private int publishedYear;
    private double price;
    private boolean available = true;
    private boolean notReserved = true;

    public Book(String title, String author, int publishedYear, double price) {
        this.title = title;
        this.author = author;
        this.publishedYear = publishedYear;
        this.price = price;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public int getPublishedYear() {
        return this.publishedYear;
    }

    public double getPrice() {
        return this.price;
    }

    public boolean isAvailable() {
        return this.available;
    }

    public boolean isNotReserved() {
        return this.notReserved;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setNotReserved(boolean notReserved) {
        this.notReserved = notReserved;
    }

    @Override
    public String toString() {
        return getTitle() + " by " + getAuthor() + " --" + getPublishedYear() + " (price: " + getPrice() + " euros)";
    }

}
