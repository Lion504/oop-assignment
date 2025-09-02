package system;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Book;
import model.LibraryMember;

public class Library {
    private final List<Book> books;
    private final List<LibraryMember> members;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }

    public void addBook(Book book) {
        this.books.add(book);
        System.out.printf("🆗 <%s> add into Library\n", book.getTitle());
    }

    public void addLibraryMember(LibraryMember member) {
        members.add(member);
        System.out.printf("✅ '%S' join into Library Member\n", member.getName());
    }

    public void borrowBook(LibraryMember member, Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            books.remove(book);
            System.out.printf("⚠️ <%s> remove from Library!\n", book.getTitle());
            member.borrowBook(book);
            System.out.printf("🆗 '%s' borrow <%s>successfully!\n", member.getName(),book.getTitle());
        }
        System.out.printf("⚠️ <%s> not find in Library!\n", book.getTitle());
    }

    public void returnBook(LibraryMember member, Book book) {
        if (member.getBookBorrowed().contains(book)) {
            member.getBookBorrowed().remove(book);
            books.add(book);
            book.setAvailable(true);
            System.out.printf("⚠️ '%s' return <%s>!\n", member.getName(), book.getTitle());
        }
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

}
