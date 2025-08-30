package Task1;

public class Books {

    private String bookName;
    private String bookAuthor;
    private int publicationTime;
    private double bookPrice;

    public Books(String bookName, String bookAuthor, int publicationTime, double bookPrice) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.publicationTime = publicationTime;
        this.bookPrice = bookPrice;
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

    @Override
    public String toString() {
        return String.format("<%s> by '%s' (%d) - €%.2f", bookName, bookAuthor, publicationTime, bookPrice);
    }

}