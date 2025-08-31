package Tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Library {
    //<Books> corresponded with Books Objects which have setter and getter
    ArrayList<Books> booksList = new ArrayList<>();
    ArrayList<Books> borrowBooksList = new ArrayList<>();
    ArrayList<Users> users = new ArrayList<>();

    //add for another way
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

    //add books
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
        Iterator<Books> itrBooks = booksList.iterator();
        while (itrBooks.hasNext()) {
            Books book = itrBooks.next();
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
            //just another way
            //System.out.printf("%s.  " + counter + " . " + book.getBookName() + ": " + book.getBookAuthor() + book.getPublicationTime() + book.getBookPrice());
            System.out.printf("  %s. <%s>: '%s', Year: %s, - €%s Rate: %s⭐ \n  Review: %s.\n",
                    counter,
                    book.getBookName(),
                    book.getBookAuthor(),
                    book.getPublicationTime(),
                    book.getBookPrice(),
                    book.getAverageRating(),// or getRatingWithStars()
                    book.getReview()
            );
            //just another way
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
    public boolean borrowBookByName(String userName, String bookName) {
        //find user
        Users user = findUser(userName);
        if (user == null) {
            System.out.println("❌ User '" + userName + "' not found!");
            return false;
        }
        if (user.hasBorrowedBook(bookName)) {
            System.out.println("⚠️ " + userName + " already has <" + bookName + "> book!");
            return false;
        }
        //find book
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
            System.out.println("Borrow for user: " + userName);
            booksList.remove(bookBorrow);
            borrowBooksList.add(bookBorrow);
            System.out.println("🆗 " + userName + " borrowed <" + bookName + "> successfully!");
            return true;
            }
        }

    //return book
    public boolean returnBooks(String userName, String bookName) {
        Users user = findUser(userName);
        if (user == null) {
            System.out.println("❌ User '" + userName + "' not found!");
            return false;
        }

        // Find book in user borrowed list
        Books bookReturn = null;
        for (Books book : user.getBorrowedBooks()) {
            if (book.getBookName().equalsIgnoreCase(bookName)) {
                bookReturn = book;
                break;
            }
        }

        if (bookReturn == null) {
            System.out.printf("⚠️ %s doesn't have <%s> to return!%n", userName, bookName);
            return false;
        }

        // Return the book
        user.returnBook(bookReturn);
        borrowBooksList.remove(bookReturn);
        booksList.add(bookReturn);
        System.out.println("🆗 " + userName + " returned <" + bookName + "> successfully!");
        return true;
    }

    //display borrowed book
    public void displayBorrowBooksByUser() {
        if (borrowBooksList.isEmpty()) {
            System.out.println("⚠️ You don't have any books to return!");
        } else {
            System.out.println("\n📋 All Borrowed Books:");
            for (Users user : users) {
                if (user.getBorrowedBooksCount() > 0) {
                    System.out.println("\n👾 " + user.getName() + ":");
                    List<Books> userBooks = user.getBorrowedBooks();
                    for (int i = 0; i < userBooks.size(); i++) {
                        System.out.println("   " + (i + 1) + ". " + userBooks.get(i));
                    }
                }
            }
        }
    }

    //set rate for book
    public void setRateByBook(String bookName, double rating) {
        if (bookName == null) {
            System.out.println("⚠️ Please give a book name!");
            return;
        }
        if (rating < 0.0 || rating > 5.0) {
            System.out.println("⚠️ Rating must be between 0.0 and 5.0 ⭐");
            return;
        }

        for (Books book : booksList) {
            if (book.getBookName().equalsIgnoreCase(bookName)) {
                book.setRating(rating);
                System.out.println("\n✅ Rate added successfully for <" + bookName + ">!");
                return;
            }
        }
        System.out.println("❌ Book <" + bookName + "> not found in library!");
    }

    //check rate
    public boolean bookRateCheck(String bookName) {
        List<Books> rateCheckByName = booksList.stream()
                .filter(book -> book.getBookName().equalsIgnoreCase(bookName))
                .toList();
        if (rateCheckByName.isEmpty()) {
            System.out.println("⚠️ Book is not available!");
            return false;
        } else {
            System.out.printf("\n🔍 " + "Current '%s' in Library info: %n", bookName);
            rateCheckByName.forEach(book -> System.out.printf("--%s.", book));
            return true;
        }
    }

    //set review
    public void setReviewByBook(String bookName, String review) {
        if (bookName == null || review == null) {
            System.out.println("⚠️ Please give a book name or add review!");
            return;
        }
        for (Books book : booksList) {
            if (book.getBookName().equalsIgnoreCase(bookName)) {
                book.setReview(review);
                System.out.println("\n✅ Review added for <" + bookName + ">!");
                return;
            }
        }
        System.out.println("❌ Book <" + bookName + "> not found in library!");
    }

    //Average Book Rating already handled in books class
    public void getAverageBookRating(String bookName) {
        bookRateCheck(bookName);
    }

    //Most Reviewed Book
    public void getMostReviewedBook() {
        if  (booksList.isEmpty()) {
            System.out.println("\n⚠️ no books in Library!");
        }
        Books mostReviewedBook = null;
        int maxReviews = 0;
        Iterator<Books> listBooksReview = booksList.iterator();
        while (listBooksReview.hasNext()) {
            Books book = listBooksReview.next();
            int reviewCount = book.getReview().size();
            if (reviewCount > maxReviews) {
                maxReviews = reviewCount;
                mostReviewedBook = book;
            }
        }

        if  (mostReviewedBook == null) {
            System.out.println("⚠️ no books has been reviewed!");
        } else {
            System.out.printf("\n🏆 Most Reviewed Book: <%s> has %d review(s)%n",
                    mostReviewedBook.getBookName(), maxReviews);
            System.out.println("📖 Book Details: " + mostReviewedBook);

            // Display all reviews
            System.out.println("📝 Reviews:");
            List<String> reviews = mostReviewedBook.getReview();
            for (int i = 0; i < reviews.size(); i++) {
                System.out.printf("   %d. %s%n", i + 1, reviews.get(i));
            }
        }
    }
    //add users
    public void registerUser(String name, int age) {
        for (Users user : users) {
            if (user.getName().equalsIgnoreCase(name)) {
                System.out.println("⚠️ User '" + name + "' already registered!");
                return;
            }
        }
        Users newUser = new Users(name, age);
        users.add(newUser);
        System.out.println("✅ User '" + name + "' registered successfully!");
    }
    //find user
    private Users findUser(String name) {
        return users.stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
    //displayUsers
    public void displayUsers() {
        if (users.isEmpty()) {
            System.out.println("⚠️ No users registered!");
            return;
        }
        System.out.println("\n📃 Registered Users:");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i));
        }
    }

}