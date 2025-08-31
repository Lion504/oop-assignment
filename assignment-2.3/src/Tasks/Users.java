package Tasks;

import java.util.ArrayList;
import java.util.List;

public class Users {
    private String name;
    private int age;
    private final List<Books> borrowedBooks;

    public Users(String name, int age) {
        this.name = name;
        this.age = age;
        this.borrowedBooks = new ArrayList<>();
    }
    //getter
    public String getName() { return name; }
    public int getAge() { return age; }

    //setter
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }

    public void borrowBook(Books book) {
        if (!borrowedBooks.contains(book)) {
            borrowedBooks.add(book);
            System.out.println("📚 " + name + " borrowed <" + book.getBookName() + ">");
        } else {
            System.out.println("📚 " + name + " already borrowed <" + book.getBookName() + "> you should return it first!");
        }
    }

    public boolean returnBook(Books book) {
        if (borrowedBooks.remove(book)) {
            System.out.println("📤 " + name + " returned <" + book.getBookName() + ">");
            return true;
        }
        return false;
    }

    public List<Books> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    public int getBorrowedBooksCount() {
        return borrowedBooks.size();
    }

    public boolean hasBorrowedBook(String bookName) {
        return borrowedBooks.stream()
                .anyMatch(book -> book.getBookName().equalsIgnoreCase(bookName));
    }

    @Override
    public String toString() {
        return String.format("%s (Age: %d) - Currently borrowed: %d books | Total read: %d books",
                name, age, borrowedBooks.size());
    }
}
