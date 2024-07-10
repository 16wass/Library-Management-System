import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testng.AssertJUnit.*;

public class TDD {
    ArrayList<Book> books = new ArrayList<>();
    ArrayList<BookCopy> bookCopies = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();
    Library library = new Library(books, customers, bookCopies);
    Book book = new Book("title", "author",2000, 1234, "java");
    BookCopy bookCopy = new BookCopy("title", "author",1999, 4321, false, 1000, "java");
    List<BookCopy> bookCopyList = List.of();
    Customer customer = new Customer(11, "firstName", "lastName", "payment", 1, bookCopyList);

    @Test
    public void importingCsvFileBooksTest() {
        String csvData = "Title: title, Author: author, ISBN: 1234\n";
        String filePath = new String("");
        library.importingCsvFileBooks(filePath);
        Book bookTest = library.getBooks().get(0);
        assertEquals(1, library.getBooks().size());
        // check if the first book is imported correctly
        assertEquals("title", bookTest.getTitle());
        // check if the first book's author is imported correctly
        assertEquals("author", bookTest.getAuthor());
        assertEquals(1234, bookTest.getIsbn());
    }

    @Test
    public void importingCsvFileBookCopiesTest() {
        //To show the content of the Csv data
        String csvData = "Title: title, Author: author, Year: 2001, ID: 1234, Borrowed: No, CustomerID: 11, Publisher: java\n";
        String filePath = "/path/to/bookcopies.csv";
        library.importingCsvFileBooks(filePath);
        assertEquals(1, library.getBooks().size());
        BookCopy bookCopyTest = library.getBookCopies().get(0);
        assertEquals("title", bookCopyTest.getTitle());
        assertEquals("author", bookCopyTest.getAuthor());
        assertEquals(1234, bookCopyTest.getId());
        assertEquals(11,bookCopyTest.getCustomerId());
        assertEquals("java", bookCopyTest.getPublisher());
    }

    @Test
    public void importingCsvFileCustomersTest() {
        String csvData = "ID: 11, fist name: firstName, Last name: lastName, Payment status: payment, Books currently borrowed: 1\n";
        String filePath = new String("");
        library.importingCsvFileCustomers(filePath);
        Customer customerTest = library.getCustomers().get(0);
        assertEquals(1, library.getCustomers().size());
        assertEquals("customer", customerTest.getName());
        assertEquals(11, customerTest.getId());
        assertEquals(bookCopyList, customerTest.getBookCopyList());
        assertEquals("lastName", customerTest.getLastName());
        assertEquals(1, customerTest.getNumberOfBooksCurrentlyBorrowed());
        assertEquals("payment", customerTest.getPaymentStatus());
    }

    //output for this test must be in the form;
    //(Title, Authors, ISBN, ID, Shelf Location, Borrowing Status, Borrow Date)
    @Test
    public void searchingBookCopiesViaIsbnAuthorTitleTest() {
        library.getBookCopies().add(bookCopy);
        List<BookCopy> copiesByIsbn = library.searchingBookcopiesViaIsbnAuthorTitle("4321");
        List<BookCopy> copiesByTitle = library.searchingBookcopiesViaIsbnAuthorTitle("title");
        List<BookCopy> copiesByAuthor = library.searchingBookcopiesViaIsbnAuthorTitle("author");
        assertEquals(1, copiesByIsbn.size());
        assertEquals(1, copiesByTitle.size());
        assertEquals(1, copiesByAuthor.size());
        //Output: Title, Authors, ISBN, ID, Shelf Location, Borrowing Status, Borrow Date)
        String expectedOutput = "title, author, 4321, " + bookCopy.getId() + ", shelfLocation, false, null";
        assertEquals(expectedOutput, copiesByIsbn.get(0).toString());
        assertEquals(expectedOutput, copiesByTitle.get(0).toString());
        assertEquals(expectedOutput, copiesByAuthor.get(0).toString());
    }

    //This method need Customer ID and Book Copy ID
    @Test
    public void borrowingBookCopyTest() {
        customer.borrowBook(customer.getId(), bookCopy.getId());
        assertEquals(1, customer.getBookCopyList());
        BookCopy customerBook = customer.getBookCopyList().get(0);
        assertTrue(bookCopy.isBorrowed());
        assertEquals("title", customerBook.getTitle());
        assertEquals("author", customerBook.getAuthor());
        assertEquals(1234, customerBook.getId());
    }

    //This method need Customer ID and Book Copy ID
    @Test
    public void returningBookcopyTest() {
        customer.returnBook(customer.getId(), bookCopy.getId());
        assertEquals(0, customer.getBookCopyList());
        assertFalse(bookCopy.isBorrowed());
        assertEquals(bookCopy, library.getBookCopies().get(0));
    }

    //From now on other tests will be about the reporting part

    //output for this test must be in the form;
    //(Title, Authors, Year, ISBN)
    @Test
    public void outputAllBooksTest() {
        library.addBook(book);
        assertEquals(1, library.getBooks().size());
        String expectedOutput = "title, author, 2000, 1234";
        assertEquals(expectedOutput, library.outputAllBooks(book.getTitle(), book.getAuthor(), book.getYear(), book.getIsbn()));
    }

    //output is the same with the method outputAllBooksTest()
    @Test
    public void outputAllBorrowedBookCopiesTest() {
        bookCopy.setBorrowed(true);
        library.addBookCopy(bookCopy);
        assertEquals(1, library.getBookCopies().size());
        List<BookCopy> borrowedBookCopies = new ArrayList<>(List.of());
        for (BookCopy bookCopy1 : library.getBookCopies()) {
            if (bookCopy1.isBorrowed()) {
                borrowedBookCopies.add(bookCopy1);
            }
        }
        assertEquals(1, borrowedBookCopies.size());
        String expectedOutput = "11, title, author, 1999, 4321";
        assertEquals(expectedOutput, library.outputOfAllBorrowedBookCopies(customer.getId(), bookCopy.getTitle(),
                bookCopy.getAuthor(), bookCopy.getYear(), bookCopy.getId()));


    }

    //output is the same with the method outputAllBooksTest()
    @Test
    public void outputAllNonBorrowedBookCopiesTest() {
        bookCopy.setBorrowed(false);
        library.addBookCopy(bookCopy);
        assertEquals(1, library.getBookCopies().size());
        List<BookCopy> nonBorrowedBookCopies = new ArrayList<>(List.of());
        for (BookCopy bookCopy1 : library.getBookCopies()) {
            if (!bookCopy1.isBorrowed()) {
                nonBorrowedBookCopies.add(bookCopy1);
            }
        }
        assertEquals(1, nonBorrowedBookCopies.size());
        String expectedOutput = "title, author, 1999, 4321";
        assertEquals(expectedOutput, library.outputOfNonBorrowedBookCopies(bookCopy.getTitle(), bookCopy.getAuthor(), bookCopy.getYear(), bookCopy.getId()));
    }

    //output for this test must be in the form;
    //(ID,Name,FirstName,PaymentStatus,NumberofBooksCurrently Borrowed)
    @Test
    public void outputAllCustomersTest() {
        customer.getBookCopyList().add(bookCopy);
        library.getCustomers().add(customer);
        String output = library.outputOfAllCustomers(customer.getId(), customer.getName(), customer.getLastName(), customer.getPaymentStatus(), customer.getNumberOfBooksCurrentlyBorrowed());
        String expectedOutput = "11, firstName, lastName, payment, 1";
        assertEquals(expectedOutput, output);
    }

    //output is the same with the method outputAllBooksTest()
    @Test
    public void outputAllBorrowedBookCopiesOfCustomerTest() {
        customer.getBookCopyList().add(bookCopy);
        bookCopy.setBorrowed(true);
        assertEquals(1, customer.getBookCopyList().size());
        String expectedOutput = "title, author, 1999, 4321";
        assertEquals(expectedOutput, library.outputOfAllBorrowedBookCopiesOfCustomer(customer.getId(), bookCopy.getTitle(), bookCopy.getAuthor(), bookCopy.getYear(), bookCopy.getId()));
    }

    @Test
    public void outputNumberBookCopiesPerPublisherTest() {
        library.addBookCopy(bookCopy);
        Map<String, Integer> result = library.outputNumberBookCopiesPerPublisher();
        Map<String, Integer> expected = Map.of("java", 2, "python", 1);
        assertEquals(expected, result);
    }
}
