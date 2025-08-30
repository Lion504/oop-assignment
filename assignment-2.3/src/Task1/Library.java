package Task1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Library {
    //<Books> corresponded with Books Objects which have setter and getter
    ArrayList<Books> booksList = new ArrayList<Books>();

    //add
    public void addBook(String bookName, String bookAuthor, int publicationTime, double price) {
        //need create instance new Books to call Books class
        if (bookName != null && bookAuthor != null) {
            for (int i = 0; i <= booksList.size(); i++ ) {
                Books book = booksList.get(i);
                if (book.getBookName().equals(bookName)) {
                    System.out.println(bookName + "already exists");
                } else {
                    booksList.add(new Books(bookName, bookAuthor, publicationTime, price));
                    System.out.println(bookName + " added successfully!");
                }
            }
        } else  {
            System.out.println("Book Name or Author or Publication Time is null");
        }
    }

    //remove by book name
    public boolean removeBook (String bookName) {
        System.out.println(bookName + " Removing... ");
        Iterator<Books> itrbooks = booksList.iterator();
        while (itrbooks.hasNext()) {
            Books book = itrbooks.next();
            if (book.getBookName().equals(bookName)) {
                booksList.remove(book);
                System.out.println(bookName + " removed successfully!");
                return true;
            }
        }
        System.out.println(bookName + " not found!");
        return false;
    }

    //display
    public void displayBooks() {
        if  (booksList.isEmpty()) {
            System.out.println("Library is empty!");
        }
        System.out.println("Library List: ");
        Iterator<Books> listBooks = booksList.iterator();
        int counter = 1;
        while (listBooks.hasNext()) {
            Books book = listBooks.next();
            System.out.println(counter + " . " + book);
            counter++;
        }
    }

    //find book by author
    public void findBookByAuthor(String author) {
    //use stream
        List<Books> booksFind = booksList.stream()
                .filter(book -> book.getBookAuthor().equalsIgnoreCase(author))
                .collect(Collectors.toList());
        if (booksFind.isEmpty()) {
            System.out.println("Books not found!");
        }  else {
            System.out.printf("Find books by %s: %s", author);
            booksFind.forEach(book -> System.out.println("-- " + book));
        }
    }

}
