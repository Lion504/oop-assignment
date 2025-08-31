package Task1;

import java.util.Scanner;

public class Books {

    private String bookName;
    private String bookAuthor;
    private int publicationTime;
    private double bookPrice;
    private double rating;
    private String review;

    public Books(String bookName, String bookAuthor, int publicationTime, double bookPrice) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.publicationTime = publicationTime;
        this.bookPrice = bookPrice;
        this.rating = 0.0;
        this.review = "";
    }

    //setter
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public void setPublicationTime(int publicationTime) {
        this.publicationTime = publicationTime;
    }

    public void setBookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public void setRating(double rating) {
        //Scanner scan = new Scanner(System.in);
        //System.out.printf("Please enter your rating for <%s> (0-5): ", bookName);
        //scan.nextLine();
        if (rating >= 0.0 && rating <= 5.0) {
            this.rating = rating;
            System.out.println("✅ Rate added successfully!");

        } else {
            System.out.println("⚠️ Rating must be between 0.0 and 5.0");
        }
    }

    public void addReview(String review) {
        if (review == null || review.isEmpty()) {
            System.out.println("⚠️ Review is empty!");
        } else {
            this.review = review;
            System.out.println("✅ Review added successfully!");
        }
    }

    //getter
    public String getBookName() {
        return bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public int getPublicationTime() {
        return publicationTime;
    }

    public double getBookPrice() {
        return bookPrice;
    }

    public double getRating() {
        return rating;
    }
    public String getReview() {
        return review;
    }
    @Override
    public String toString() {
        return String.format("<%s> by '%s' (%d) - €%.2f", bookName, bookAuthor, publicationTime, bookPrice);
    }

}