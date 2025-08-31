package Tasks;

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
        Books bookTest1 = new Books("The Great Gatsby", "F. Scott Fitzgerald", 1925, 10.75);
        library.addBook(bookTest1); // expected output already in Library
        /*//instance test rate, not effective way
        Books bookTest2 = new Books("The Great Gatsby test", "F. Scott Fitzgerald", 1925, 10.75);
        library.addBook(bookTest2); // expected output already in Library
        bookTest2.setRating(2.6);
        bookTest2.setReview("this is a test review, the book is great!");
        library.displayBooks();*/


        //instance test findBookByAuthor
        String author = "George Orwell";
        String bookTest3 = "Animal Farm";
        String bookTest4 = "Pride and Prejudice";

        //call displayBooks
        library.displayBooks();
        //call findBookByAuthor
        library.findBookByAuthor(author);
        //call borrowBookByName
        library.borrowBookByName(bookTest3);
        //call borrowBookByName
        library.borrowBookByName(bookTest4);
        //call displayBorrowBooks
        library.displayBorrowBooks();
        //call returnBooks
        library.returnBooks(bookTest3);
        library.returnBooks(bookTest4);
        //call isBooksAvailable
        library.isBooksAvailable(bookTest3);
        //call setRateByBook
        library.setRateByBook(bookTest3, 3.5);
        library.setRateByBook(bookTest3, 3.5);
        //call bookRateCheck
        library.bookRateCheck(bookTest3);
        //call setReviewByBook
        library.setReviewByBook(bookTest4, "test review, this book is great!!😂");
        library.setReviewByBook(bookTest4, "review, this book is great!!😂");
        library.setReviewByBook(bookTest4, "this book is great!!😂");
        library.setReviewByBook(bookTest4, "book is great!!😂");
        //call bookRateCheck
        library.bookRateCheck(bookTest4);
        library.setReviewByBook(bookTest3, "qqq this book is great!!😂");
        library.setReviewByBook(bookTest3, "qqq book is great!!😂");
        //call bookRateCheck
        library.bookRateCheck(bookTest3);
        //call getMostReviewedBook
        library.getMostReviewedBook();
        //call getAverageBookRating
        library.getAverageBookRating(bookTest3);

    }
}
