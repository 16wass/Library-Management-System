public class Book {
    private final String title;
    private final String author;
    private final int year;
    private final int isbn;
    private final String publisher;


    public Book(String title, String author,int year, int isbn, String publisher) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.isbn = isbn;
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    public String getPublisher() {
        return publisher;
    }
}
