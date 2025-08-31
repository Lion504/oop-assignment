package Task1;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Library {
    //<Books> corresponded with Books Objects which have setter and getter
    ArrayList<Books> booksList = new ArrayList<>();
    ArrayList<Books> borrowBooksList = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

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
            System.out.println("⚠️ Book Name or Author is required");
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
    public boolean removeBook(String bookName) {
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
        System.out.println("⚠️ " + bookName + " not found!");
        return false;
    }

    //display
    public void displayBooks() {
        if (booksList.isEmpty()) {
            System.out.println("⚠️ Library is empty!");
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
        List<Books> authorFind = booksList.stream()
                .filter(book -> book.getBookAuthor().equalsIgnoreCase(author))
                .toList();
        if (authorFind.isEmpty()) {
            System.out.println("⚠️ Books not found!");
        } else {
            System.out.printf("\n🔍 " + "Find books by '%s': %n", author);
            AtomicInteger counter = new AtomicInteger(1);
            authorFind.forEach(book ->
                    System.out.printf("--%s. %s\n",
                            counter.getAndIncrement(),
                            book)
            );
        }
    }

    //check book Available
    public boolean isBooksAvailable(String bookName) {
        List<Books> bookFindByName = booksList.stream()
                .filter(book -> book.getBookName().equalsIgnoreCase(bookName))
                .toList();
        if (bookFindByName.isEmpty()) {
            System.out.println("⚠️ Book is not available!");
            return false;
        } else {
            System.out.printf("\n🔍 " + "Find books '%s' in Library: %n", bookName);
            bookFindByName.forEach(book -> System.out.printf("--%s.", book));
            return true;
        }

    }

    //borrow
    public boolean borrowBookByName(String bookName) {
        List<Books> bookFind = booksList.stream()
                .filter(book -> book.getBookName().equalsIgnoreCase(bookName))
                .toList();
        if (bookFind.isEmpty()) {
            System.out.println("⚠️ " + bookName + " not in Library, can't  borrow!");
            return false;
        } else {
            System.out.printf("\n🔍 " + "Borrow system search book '%s' in Library: %n", bookName);
            Books bookBorrow = bookFind.get(0);//get(0) means just get first match value
            System.out.printf("-- %s\n", bookBorrow);
            System.out.println("Do you want borrow this book? (y/n)");
            String borrowCondition = sc.nextLine();
            if (borrowCondition != null && borrowCondition.equalsIgnoreCase("y")) {
                booksList.remove(bookBorrow);
                borrowBooksList.add(bookBorrow);
                System.out.println("🆗 You borrowed <" + bookName + "> successfully!");
                return true;
            } else {
                System.out.println("⚠️ <" + bookName + "> borrow refused!");
                return false;
            }
        }
    }

    //return book
    public boolean returnBooks(String bookName) {
        List<Books> borrowBookFind = borrowBooksList.stream()
                .filter(book -> book.getBookName().equalsIgnoreCase(bookName))
                .toList();
        if (borrowBookFind.isEmpty()) {
            System.out.printf("⚠️ You don't have <%s> books to return!", bookName);
            return false;
        } else {
            Books bookReturn = borrowBookFind.get(0);
            borrowBooksList.remove(bookReturn);
            booksList.add(bookReturn);
            System.out.println("\n🆗 You returned <" + bookName + "> successfully!");
            return true;
        }
    }

    //display borrowed book
    public void displayBorrowBooks() {
        if (borrowBooksList.isEmpty()) {
            System.out.println("⚠️ You don't have any books to return!");
        } else {
            System.out.println("\nBooks You Borrowed: ");
            borrowBooksList.forEach(book -> System.out.println("-- " + book));


        }
    }

}
