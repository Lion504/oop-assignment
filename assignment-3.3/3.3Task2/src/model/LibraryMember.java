package model;

import java.util.ArrayList;
import java.util.List;

public class LibraryMember {
    private String name;
    private int userId;
    private List<Book> bookBorrowed;
    private List<Book> bookReserved;

    public LibraryMember(String name, int userId) {
        this.name = name;
        this.userId = userId;
        this.bookBorrowed = new ArrayList<>();
        this.bookReserved = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public List<Book> getBookReserved() {
        return bookReserved;
    }

    public List<Book> getBookBorrowed() {
        return bookBorrowed;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void bookReserved(Book book) {
        this.bookReserved.add(book);
    }

    public void bookRmReserved(Book book) {
        this.bookReserved.remove(book);
    }
    public void borrowBook(Book book) {
        this.bookBorrowed.add(book);
    }

    public void returnBook(Book book) {
        this.bookBorrowed.remove(book);
    }
    @Override
    public String toString() {
        return " Name: " + getName()
                + "\nID: " + getUserId()
                + "\n Borowed books: " + getBookBorrowed();
    }

}
