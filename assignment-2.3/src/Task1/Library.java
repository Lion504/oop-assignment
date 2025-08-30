package Task1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Library {
    //<Books> corresponded with Books Objects which have setter and getter
    ArrayList<Books> booksList = new ArrayList<Books>();

    //add
    /*public void addBook(String bookName, String bookAuthor, int publicationTime, double price) {
        //need create instance new Books to call Books class
        if (bookName != null && bookAuthor != null) {
            for (int i = 0; i <= booksList.size(); i++ ) {
                Books book = booksList.get(i);
                if (book.getBookName().equalsIgnoreCase(bookName)) {
                    booksList.add(new Books(bookName, bookAuthor, publicationTime, price));
                    System.out.println(bookName + " added successfully!");
                } else {
                    System.out.println(bookName + "already exists");
                }
            }
        } else  {
            System.out.println("Book Name or Author is null");
        }
    }*/
    public void addBook(Books book) {
        if (book == null || book.getBookName() == null || book.getBookAuthor() == null) {
            System.out.println("⚠️" + "Book Name or Author is required");
            return;
        }
        //seems unnecessary it's compare hashcode, keep it first
        if (booksList.contains(book)) {
            System.out.println("⚠️" + book.getBookName() + "already exists");
            return;
        }
        for (Books libBooks : booksList) {
            if (libBooks.getBookName().equalsIgnoreCase(book.getBookName())) {
                System.out.println("⚠️" + book.getBookName() + " is already in Library!");
                return;
            }
        }
        booksList.add(book);
        System.out.println("✅ <" + book.getBookName() + "> added successfully!");
    }


    //remove by book name
    public boolean removeBook (String bookName) {
        System.out.println(bookName + " Removing... ");
        Iterator<Books> itrbooks = booksList.iterator();
        while (itrbooks.hasNext()) {
            Books book = itrbooks.next();
            if (book.getBookName().equals(bookName)) {
                booksList.remove(book);
                System.out.println("🆗 " + bookName + " removed successfully!");
                return true;
            }
        }
        System.out.println("⚠️ " +bookName + " not found!");
        return false;
    }

    //display
    public void displayBooks() {
        if  (booksList.isEmpty()) {
            System.out.println("⚠️ " +"Library is empty!");
        }
        System.out.println("\n📃 Library List: ");
        Iterator<Books> listBooks = booksList.iterator();
        int counter = 1;
        while (listBooks.hasNext()) {
            Books book = listBooks.next();
            //System.out.printf("%s.  " + counter + " . " + book.getBookName() + ": " + book.getBookAuthor() + book.getPublicationTime() + book.getBookPrice());
            System.out.printf("  %s. <%s>: '%s', Year: %s, - €%s.\n",
                    counter,
                    book.getBookName(),
                    book.getBookAuthor(),
                    book.getPublicationTime(),
                    book.getBookPrice()
            );
            //System.out.printf("%s. %s\n",counter,book);
            counter++;
        }
    }

    //find book by author
    public void findBookByAuthor(String author) {
    //use stream
        List<Books> booksFind = booksList.stream()
                .filter(book -> book.getBookAuthor().equalsIgnoreCase(author))
                .toList();
        if (booksFind.isEmpty()) {
            System.out.println("⚠️ " +"Books not found!");
        }  else {
            System.out.printf("\n🔍 " +"Find books by '%s': %n", author);
            AtomicInteger counter = new AtomicInteger(1);
            booksFind.forEach(book ->
                    System.out.printf("--%s. %s\n",
                            counter.getAndIncrement(),
                            book)
            );
        }
    }

    //borrow
    public void borrowBookByName(String bookName) {

    }

}
