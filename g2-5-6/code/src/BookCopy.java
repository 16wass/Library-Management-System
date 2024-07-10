public class BookCopy {
    private String title;
    private String author;
    private int year;
    private int id;
    private boolean borrowed;
    private int customerId;
    private String publisher;

    public BookCopy(String title, String author, int year, int id, boolean borrowed,int customerId, String publisher) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.id = id;
        this.borrowed = borrowed;
        this.customerId = customerId;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getId() {
        return id;
    }

    public boolean isBorrowed() {
        return borrowed;
    }

    public void setBorrowed(boolean borrowed) {
        this.borrowed = borrowed;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}