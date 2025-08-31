package Task1;

public class Main {
    public static void main(String[] args) {
        //instance for book,
        Books[] book = {
                new Books("To Kill a Mockingbird", "Harper Lee", 1960, 15.99),
                new Books("1984", "George Orwell", 1949, 12.50),
                new Books("The Great Gatsby", "F. Scott Fitzgerald", 1925, 10.75),
                new Books("Pride and Prejudice", "Jane Austen", 1813, 9.99),
                new Books("The Catcher in the Rye", "J.D. Salinger", 1951, 14.50),
                new Books("Go Set a Watchman", "Harper Lee", 2015, 18.00),
                new Books("Animal Farm", "George Orwell", 1945, 11.25),//seems last one can have ,
        };
        //instance for Library, add book
        Library library = new Library();
        for (Books b : book) {
            library.addBook(b);
        }

        //instance test duplicated
        Books bookTest = new Books("The Great Gatsby", "F. Scott Fitzgerald", 1925, 10.75);
        library.addBook(bookTest); // expected output already in Library
        //instance test findBookByAuthor
        String author = "George Orwell";
        String bookTest1 = "Animal Farm";
        String bookTest2 = "Pride and Prejudice";

        //call displayBooks
        library.displayBooks();
        //call findBookByAuthor
        library.findBookByAuthor(author);
        //call borrowBookByName
        library.borrowBookByName(bookTest1);
        //call borrowBookByName
        library.borrowBookByName(bookTest2);
        //call displayBorrowBooks
        library.displayBorrowBooks();
        //call returnBooks
        library.returnBooks(bookTest1);
        library.returnBooks(bookTest2);
        //call isBooksAvailable
        library.isBooksAvailable(bookTest1);
    }
}
