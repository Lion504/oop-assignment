package Task1;

public class Books {

    private String bookName;
    private String bookAuthor;
    private String publicationTime;
    private double bookPrice;

    public void Item(String bookName, String bookAuthor, String publicationTime, double bookPrice) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.publicationTime = publicationTime;
        this.bookPrice = bookPrice;
    }

    //setter
    public void setBookName(String bookName) {this.bookName = bookName;}
    public void setBookAuthor(String bookAuthor) {this.bookAuthor = bookAuthor;}
    public void setPublicationTime(String publicationTime) {this.publicationTime = publicationTime;}
    public void setBookPrice(double bookPrice) {this.bookPrice = bookPrice;}

    //getter
    public String getBookName() {return bookName;}
    public String getBookAuthor() {return bookAuthor;}
    public String getPublicationTime() {return publicationTime;}
    public double getBookPrice() {return bookPrice;}

}