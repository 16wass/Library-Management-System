import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.AssertJUnit.*;

public class CLITest {

    // Delete a book (via ISBN)
    //Delete a book copy (via ID)
    //Delete a customer (via ID)


    ArrayList<Book> bookList = new ArrayList<>();
    ArrayList<BookCopy> bookCopyList = new ArrayList<>();
    ArrayList<Customer> customerList = new ArrayList<>();
    Library library = new Library(bookList, customerList, bookCopyList);
    Book book = new Book("Title1", "Author1", 1997, 1234, "java");
    BookCopy bookCopy = new BookCopy("Title2", "Author2", 2000, 12345, false, 111,"java");
    Customer customer = new Customer(11,"Name1","lastname","payment", 1, bookCopyList);

    @Test
    public void deleteBookTestSuccessful() {
        library.addBook(book);
        library.deleteBook(book.getIsbn());
        assertEquals(0, library.getBooks().size());
    }
    @Test
    public void deleteBookTestUnsuccessful() {
        library.addBook(book);
        int falseIsbn = 67;
        library.deleteBook(falseIsbn);
        assertEquals(1, library.getBooks().size());
    }
    @Test
    public void deleteBookCopySuccessful() {
        library.addBookCopy(bookCopy);
        bookCopy.setBorrowed(false);
        if(!bookCopy.isBorrowed()) {
            library.deleteBookCopy(bookCopy.getId());
            assertEquals(0, library.getBookCopies().size());
        }
    }
    @Test
    public void deleteBookCopyUnsuccessful() {
        library.addBookCopy(bookCopy);
        bookCopy.setBorrowed(true);
        if(bookCopy.isBorrowed()) {
            library.deleteBookCopy(bookCopy.getId());
            assertEquals(0, library.getBookCopies().size());
        }
    }

    @Test
    public void deleteCustomerSuccessful() {
        library.addCustomer(customer);
        library.deleteCustomer(customer.getId());
        assertEquals(0, library.getCustomers().size());
    }
    @Test
    public void deleteCustomerUnsuccessful() {
        library.addCustomer(customer);
        int falseId = 67;
        library.deleteCustomer(falseId);
        assertEquals(1, library.getCustomers().size());
    }

    @Test
    public void testOutputNumberBookCopiesPerPublisher() {
        bookCopyList.add(new BookCopy("title", "author", 2002, 11, true, 15, "AdventurePublishers Inc."));
        bookCopyList.add(new BookCopy("title1", "author", 2002, 11, true, 15, "AdventurePublishers Inc."));
        bookCopyList.add(new BookCopy("title2", "author", 2002, 11, true, 15, "AdventurePublishers Inc."));
        bookCopyList.add(new BookCopy("title3", "author", 2002, 11, true, 15, "AdventurePublishers Inc."));
        bookCopyList.add(new BookCopy("title", "author", 2002, 11, true, 15, "O'Reilly Media"));
        bookCopyList.add(new BookCopy("title1", "author", 2002, 11, true, 15, "O'Reilly Media"));
        bookCopyList.add(new BookCopy("title", "author", 2002, 11, true, 15, "Penguin Publishing"));
        bookCopyList.add(new BookCopy("title", "author", 2002, 11, true, 15, "Penguin Publishing"));
        bookCopyList.add(new BookCopy("title", "author", 2002, 11, true, 15, "Penguin Publishing"));
        bookCopyList.add(new BookCopy("title", "author", 2002, 11, true, 15, "Xaver Books"));

        library.setBookCopies(bookCopyList);

        // Call the method to test
        Map<String, Integer> result = library.outputNumberBookCopiesPerPublisher();

        // Verify the results
        assertEquals("O'Reilly Media: 2 book copies (20%)\n" + "Penguin Publishing: 3 book copies (30%)\n" +
                "AdventurePublishers Inc.: 4 book copies (40%)\n" + "Xaver Books: 1 book copies (10%)",
                result.get("AdventurePublishers Inc.\n" + "O'Reilly Media\n" + "Penguin Publishing\n" + "Xaver Books"));
        assertFalse(result.containsKey("Rheinwerk Computing"));
    }
}