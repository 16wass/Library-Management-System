import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        CommandParser commandParser = new CommandParser();
        commandParser.displayMenu();
        commandParser.run();


        List<Book> books = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();
        List<BookCopy> bookCopies = new ArrayList<>();
        BookCopy bookCopy = new BookCopy("title", "author", 2002, 11, true, 15, "AdventurePublishers Inc.");
        BookCopy bookCopy2 = new BookCopy("title1", "author", 2002, 11, true, 15, "AdventurePublishers Inc.");
        BookCopy bookCopy3 = new BookCopy("title2", "author", 2002, 11, true, 15, "AdventurePublishers Inc.");
        BookCopy bookCopy4 = new BookCopy("title3", "author", 2002, 11, true, 15, "Penguin Publishing");
        BookCopy bookCopy5 = new BookCopy("title4", "author", 2002, 11, true, 15, "Penguin Publishing");
        bookCopies.add(bookCopy);
        bookCopies.add(bookCopy2);
        bookCopies.add(bookCopy3);
        bookCopies.add(bookCopy4);
        bookCopies.add(bookCopy5);
        Library library = new Library((ArrayList<Book>) books, (ArrayList<Customer>) customers, (ArrayList<BookCopy>) bookCopies);
        System.out.println(library.getBookCopies());
        library.outputNumberBookCopiesPerPublisher();
    }
}
