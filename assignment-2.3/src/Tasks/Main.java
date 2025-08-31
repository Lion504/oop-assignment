package Tasks;

public class Main {
    public static void main(String[] args) {

        System.out.println("\n🏦  SETUP: Creating Library and Books Collection");
        System.out.println("-".repeat(50));
        //instance for book,
        Library library = new Library();
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
        for (Books b : book) {
            library.addBook(b);
        }

        //instance test duplicated
        System.out.println("\n🔍 Testing duplicate book detection:");
        Books bookTest1 = new Books("The Great Gatsby", "F. Scott Fitzgerald", 1925, 10.75);
        library.addBook(bookTest1); // expected output already in Library

        // Register users
        System.out.println("\n👥 USER MANAGEMENT: Registering Users");
        System.out.println("-".repeat(50));
        library.registerUser("Johnson", 28);
        library.registerUser("Smith", 35);
        library.registerUser("Davis", 22);
        // Display users
        library.displayUsers();

        System.out.println("\n🔍 SEARCH FUNCTIONALITY: Finding Books");
        System.out.println("-".repeat(50));
        //instance test findBookByAuthor
        String author = "George Orwell";
        String bookTest3 = "Animal Farm";
        String bookTest4 = "Pride and Prejudice";

        //call displayBooks
        library.displayBooks();
        //call findBookByAuthor
        System.out.println("\n📖 Searching for books by: " + author);
        library.findBookByAuthor(author);

        System.out.println("\n📚 BORROWING SYSTEM: Testing Book Loans");
        System.out.println("-".repeat(50));
        System.out.println("🔄 Borrowing books:");
        //call borrowBookByName
        library.borrowBookByName("Smith", bookTest3);
        //call borrowBookByName
        library.borrowBookByName("Davis", bookTest4);
        //call displayBorrowBooks
        library.displayBorrowBooksByUser();

        System.out.println("\n📤 RETURN SYSTEM: Testing Book Returns");
        System.out.println("-".repeat(50));
        System.out.println("🔄 Returning books:");
        //call returnBooks
        library.returnBooks("Smith",bookTest3);
        library.returnBooks("Davis",bookTest4);
        System.out.println("\n✅ Checking availability after returns:");
        //call isBooksAvailable
        library.isBooksAvailable(bookTest3);

        System.out.println("\n⭐ RATING SYSTEM: Testing Multiple Ratings");
        System.out.println("-".repeat(50));
        //call setRateByBook
        System.out.println("📊 Adding ratings to " + bookTest3 + ":");
        library.setRateByBook(bookTest3, 4.0);
        library.setRateByBook(bookTest3, 3.5);
        library.setRateByBook(bookTest3, 4.5);
        System.out.println("📊 Adding ratings to " + bookTest4 + ":");
        library.setRateByBook(bookTest4, 4.9);
        library.setRateByBook(bookTest4, 3.5);
        library.setRateByBook(bookTest4, 4.5);

        System.out.println("\n📈 Rating Check:");
        //call bookRateCheck
        library.bookRateCheck(bookTest3);

        System.out.println("\n📝 REVIEW SYSTEM: Testing Book Reviews");
        System.out.println("-".repeat(50));
        //call setReviewByBook
        System.out.println("✍️  Adding reviews to " + bookTest4 + ":");
        library.setReviewByBook(bookTest4, "test review, this book is great!!😂");
        library.setReviewByBook(bookTest4, "review, this book is great!!😂");
        library.setReviewByBook(bookTest4, "this book is great!!😂");
        library.setReviewByBook(bookTest4, "book is great!!😂");

        System.out.println("✍️  Adding reviews to " + bookTest3 + ":");
        library.setReviewByBook(bookTest3, "qqq this book is great!!😂");
        library.setReviewByBook(bookTest3, "qqq book is great!!😂");

        System.out.println("\n📖 Book Reviews:");
        //call bookRateCheck
        library.bookRateCheck(bookTest3);
        library.bookRateCheck(bookTest4);
        System.out.println("\n📈 Individual Book Ratings check from library:");
        //call getAverageBookRating
        library.getAverageBookRating(bookTest3);
        library.getAverageBookRating(bookTest4);

        System.out.println("\n📊 check Most Reviewed Book:");
        System.out.println("-".repeat(50));
        //call getMostReviewedBook
        library.getMostReviewedBook();

        System.out.println("\n📋 Complete Library Overview");
        System.out.println("-".repeat(50));
        library.displayBooks();
    }
}
