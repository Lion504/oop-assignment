package Task1;

import java.util.ArrayList;

public class Library {
    ArrayList<Books.Item> books = new ArrayList<Books.Item>();

    //add
    public void addBook(String bookName, String bookAuthor, String publicationTime, double price) {
        //for static inner class can direct access no need create instance first
        Books.Item
        books.add(new Books.Item(bookName, bookAuthor, publicationTime, price));
    }

    //remove by name
    public void removeBook (String bookName) {
        System.out.println(bookName + " Removing... ");
        for (Books.Item book : books){
            if (books.().equals(book)){
                books.remove(book);
            }
        }

        if (books.contains(bookName)) {
            books.remove(bookName);
            System.out.println("Book " + bookName + " Removed Successfully");
        } else {
            System.out.println("Book " + bookName + " Not Found!");
        }
        System.out.println("After remove: " + books);
    }

    //display

    //



}
