package Task1;

public class Main {
    public static void main(String[] args) {
        //create instance
        Library library =  new Library();
        Books book = new Books();

        //add book examples
        Book.addBook("To Kill a Mockingbird","Harper Lee",1960,15.99);
        Book.addBook("1984","George Orwell",1949,12.50);
        Book.addBook("The Great Gatsby","F. Scott Fitzgerald",1925,10.75);
        Book.addBook("Pride and Prejudice","Jane Austen",1813,9.99);
        Book.addBook("The Catcher in the Rye","J.D. Salinger",1951,14.50);
        Book.addBook("Go Set a Watchman","Harper Lee",2015,18.00);
        Book.addBook("Animal Farm","George Orwell",1945,11.25);
        Book.addBook("The Great Gatsby","F. Scott Fitzgerald",1925,10.75);

    }
}
