package Tasks;

import java.util.ArrayList;
import java.util.List;

public class Books {

    private String bookName;
    private String bookAuthor;
    private int publicationTime;
    private double bookPrice;
    private final List<String> reviews;
    private final List<Double> ratings;


    public Books(String bookName, String bookAuthor, int publicationTime, double bookPrice) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.publicationTime = publicationTime;
        this.bookPrice = bookPrice;
        this.ratings = new ArrayList<>();
        this.reviews = new ArrayList<>();
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

    //previous logic seems not correct, change add main method to library
    public void setRating(double rating) {
        ratings.add(rating);
    }

    //previous logic seems not correct, change add main method to library
    public void setReview(String review) {
        reviews.add(review);
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
    //
    public double getRating() {
        return getAverageRating();
    }

    //return list of reviews
    public List<String> getReview() {
        return new ArrayList<>(reviews);
    }

    //Average Rating for each book
    public double getAverageRating() {
        if (ratings.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (double rating : ratings) {
            sum += rating;
        }
        return sum / ratings.size();
    }


    public String getRateWithStar() {
        double averageRating  = getAverageRating();
        if (averageRating == 0.0) {
            return "No ratings available";
        }
        StringBuilder stars = new StringBuilder();
        int fullStars = (int) averageRating;

        // Add full stars
        for (int i = 0; i < fullStars; i++) {
            stars.append("★");
        }

        // Add half star if needed
        if (averageRating % 1 >= 0.5) {
            stars.append("☆");
        }

        // Add empty stars to make 5 total
        while (stars.length() < 5) {
            stars.append("☆");
        }

        return String.format("%.1f %s", averageRating, stars.toString());
    }

    @Override
    public String toString() {
        String reviewDisplay = !reviews.isEmpty() ? String.format(" | Reviews: %d", reviews.size()) : "";
        return String.format("<%s> by '%s' (%d) - €%.2f | %s%s",
                bookName,
                bookAuthor,
                publicationTime,
                bookPrice,
                getRateWithStar(),
                reviewDisplay);
    }

}