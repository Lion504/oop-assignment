package system;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Book;
import model.LibraryMember;

public class Library {
    private final List<Book> books;
    private final List<LibraryMember> members;
    private final List<Book> bookReserved;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.bookReserved = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.books.add(book);
        System.out.printf("🆗 <%s> add into Library\n", book.getTitle());
    }

    public void addLibraryMember(LibraryMember member) {
        members.add(member);
        System.out.printf("✅ '%S' join into Library Member\n", member.getName());
    }

    public void reserveBook(LibraryMember member, Book book) {
        if (book.isAvailable() && book.isNotReserved()) {
            book.setAvailable(false);//since reserved
            book.setNotReserved(false);//reserved
            bookReserved.add(book);
            member.bookReserved(book);
            System.out.printf("⚠️ <%s> remove from Library, reserved by %s\n", book.getTitle(), member.getName());
            return;
        }
        System.out.printf("⚠️ <%s> can not reserved in '%s'!\n", book.getTitle(), member.getName());
    }

    public void cancelReservation(LibraryMember member, Book book) {
        if (member.getBookReserved().contains(book)) {
            member.bookRmReserved(book);
            bookReserved.remove(book);
            book.setNotReserved(true);
            book.setAvailable(true);
            System.out.printf("⚠️ <%s> remove from reserved add into library!\n", book.getTitle());
            return;
        }
        System.out.printf("⚠️ <%s> not reserved '%s'!\n", book.getTitle(), member.getName());
    }


    public void borrowBook(LibraryMember member, Book book) {
        if (!book.isAvailable()) {
            System.out.printf("⚠️ <%s> not find in Library!\n", book.getTitle());
            return;
        }
        if (!book.isNotReserved() && !member.getBookReserved().contains(book)) {
            System.out.printf("⚠️ <%s> is reserved by others!\n", book.getTitle());
            return;
        }

        if (!book.isNotReserved()) {
            books.remove(book);
        }

        book.setAvailable(false);
        book.setNotReserved(true);
        System.out.printf("⚠️ <%s> remove from Library!\n", book.getTitle());
        member.borrowBook(book);
        System.out.printf("🆗 '%s' borrow <%s>successfully!\n", member.getName(), book.getTitle());

    }

    public void returnBook(LibraryMember member, Book book) {
        if (member.getBookBorrowed().contains(book)) {
            member.getBookBorrowed().remove(book);
            member.returnBook(book);
            books.add(book);
            book.setAvailable(true);
            System.out.printf("⚠️ '%s' return <%s>!\n", member.getName(), book.getTitle());
            return;
        }
        System.out.printf("⚠️ <%s> not reserved '%s'!\n", book.getTitle(), member.getName());
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("⚠️ Library is empty!");
        }
        System.out.println("\n📃 Library List: ");
        Iterator<Book> listBooks = books.iterator();
        int counter = 1;
        while (listBooks.hasNext()) {
            Book book = listBooks.next();
            System.out.printf("  %s. %s\n",
                    counter,
                    book
            );
            counter++;
        }
    }

    public void displayReservedBooks() {
        if (bookReserved.isEmpty()) {
            System.out.println("no book is reserved!");
            return;
        }
        System.out.println("\n📃 Reserved List: ");
        //Iterator<Book> listReserved = bookReserved.iterator();
        int counter = 1;
        for (Book book : bookReserved) {
            System.out.printf("  %s. %s\n",
                    counter,
                    book.getTitle()
            );
            counter++;
        }
    }
}
