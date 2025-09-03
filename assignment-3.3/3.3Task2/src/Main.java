import model.Book;
import model.LibraryMember;
import system.Library;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Java Programming", "John Doe", 2019, 15.99);
        Book book2 = new Book("Data Structures", "Jane Smith", 2019, 18.99);
        library.addBook(book1);
        library.addBook(book2);
        Book book3 = new Book("Data Structures", "Jane Smith", 1999, 15);
        Book book4 = new Book("To Kill a Mockingbird", "Harper Lee", 1960, 13.99);
        Book book5 = new Book("1984", "George Orwell", 1949, 12.50);
        Book book6 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 10.75);
        Book book7 = new Book("Pride and Prejudice", "Jane Austen", 1813, 9.99);
        Book book8 = new Book("The Catcher in the Rye", "J.D. Salinger", 1951, 14.50);
        Book book9 = new Book("Go Set a Watchman", "Harper Lee", 2015, 18.00);
        Book book10 = new Book("Animal Farm", "George Orwell", 1945, 11.25);

        library.displayBooks();
        // Create members
        LibraryMember member1 = new LibraryMember("Alice", 1001);
        LibraryMember member2 = new LibraryMember("Bob", 1002);

        // Add to library
        library.addLibraryMember(member1);
        library.addLibraryMember(member2);

        // operations
        library.borrowBook(member1, book1);
        library.borrowBook(member2, book2);
        library.returnBook(member1, book1);
        library.reserveBook(member1, book1);
        library.displayReservedBooks();
        library.cancelReservation(member1, book1);

    }
}
