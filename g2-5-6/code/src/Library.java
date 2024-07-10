import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Library {
    private ArrayList<Book> books;
    private ArrayList<Customer> customers;
    private ArrayList<BookCopy> bookCopies;

    public Library(ArrayList<Book> books, ArrayList<Customer> customers, ArrayList<BookCopy> bookCopies) {
        this.books = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.bookCopies = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addBookCopy(BookCopy bookCopy) {
        bookCopies.add(bookCopy);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void deleteBook(int ISBN) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn() == ISBN) {
                books.remove(books.get(i));
                break;
            }
        }
    }

    public void deleteBookCopy(int ID) {
        for (int i = 0; i < bookCopies.size(); i++) {
            if (bookCopies.get(i).getId() == ID) {
                bookCopies.remove(bookCopies.get(i));
                break;
            }
        }
    }

    public void deleteCustomer(int ID) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == ID) {
                customers.remove(customers.get(i));
                break;
            }
        }
    }

    //    public void importingCsvFileBooks(String filePath) {
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                //books.add(new Book(values[0], values[1], Integer.parseInt(values[2]), Integer.parseInt(values[3])));
//                String[] values = line.split(",");
//                String title = values[0];
//                String author = values[1];
//                int year = Integer.parseInt(values[2]);
//                int isbn = Integer.parseInt(values[3]);
//                books.add(new Book(title, author, year, isbn));
//
//            }
//            for (int i = 0 ; i < this.bookCopies.size();i++){
//                System.out.println(bookCopies.get(i).getTitle() + ", " + bookCopies.get(i).getAuthor()+ ", " +bookCopies.get(i).getYear()+ ", "
//                        +bookCopies.get(i).getId()+ ", " +bookCopies.get(i).isBorrowed()+ ", " +bookCopies.get(i).getCustomerId()+ ", " +bookCopies.get(i).getPublisher());
//            }
//            System.out.println("Books imported successfully.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public void importingCsvFileBooks(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                // Split the line into values using ","
                String[] values = line.split(",");

                // Remove leading and trailing whitespace and remove quotes from strings
                String title = values[0].trim().replaceAll("^\"|\"$", ""); // Remove surrounding quotes
                String author = values[1].trim().replaceAll("^\"|\"$", ""); // Remove surrounding quotes
                int year = Integer.parseInt(values[2].trim());
                int isbn = Integer.parseInt(values[3].trim());
                String publisher = values[4].trim().replaceAll("^\"|\"$", "");

                // Create a new Book object and add it to the books list
                Book book = new Book(title, author, year, isbn,publisher);
                books.add(book);
            }

            // Print imported books
            for (Book book : books) {
                System.out.println(book.getTitle() + ", " + book.getAuthor() + ", " + book.getYear() + ", " + book.getIsbn());
            }

            System.out.println("Books imported successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void importingCsvFileBookcopies(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String title = values[0];
                String author = values[1];
                int year = Integer.parseInt(values[2]);
                int id = Integer.parseInt(values[3]);
                boolean borrowed = Boolean.parseBoolean(values[4]);
                Integer customerId = values[5].isEmpty() ? null : Integer.parseInt(values[5]);
                String publisher = values[6];
                bookCopies.add(new BookCopy(title, author, year, id, borrowed, customerId,publisher));
            }
            for (int i = 0 ; i < this.bookCopies.size();i++){
                System.out.println(bookCopies.get(i).getTitle() + ", " + bookCopies.get(i).getAuthor()+ ", " +bookCopies.get(i).getYear()+ ", "
                        +bookCopies.get(i).getId()+ ", " +bookCopies.get(i).isBorrowed()+ ", " +bookCopies.get(i).getCustomerId()+ ", " +bookCopies.get(i).getPublisher());
            }
            System.out.println("Book copies imported successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // helper method for importingCsvFileBookcopies
    private boolean isValidCustomerId(Integer customerId) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                return true;
            }
        }
        return false;
    }
    public void importingCsvFileCustomers(String filePath) {
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Read the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int id = Integer.parseInt(values[0]);
                String name = values[1];
                String lastName = values[2];
                String paymentStatus = values[3];
                int numberOfBooksCurrentlyBorrowed = Integer.parseInt(values[4]);

                // Create a new Customer object
                Customer customer = new Customer(id, name, lastName, paymentStatus, numberOfBooksCurrentlyBorrowed, new ArrayList<>());

                // Add the Customer object to the customers list
                customers.add(customer);
            }
            for (int i = 0; i < customers.size(); i++) {
                Customer customer = customers.get(i);
                System.out.println(customer.getId() + ", " + customer.getName() + ", " + customer.getLastName() + ", " +
                        customer.getPaymentStatus() + ", " + customer.getNumberOfBooksCurrentlyBorrowed());
            }

            System.out.println("Customers imported successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<BookCopy> searchingBookcopiesViaIsbnAuthorTitle(String criteria) {
        for (BookCopy copy : bookCopies) {
            if (copy.getTitle().contains(criteria) || copy.getAuthor().contains(criteria) || String.valueOf(copy.getId()).contains(criteria)) {
                System.out.printf("Title: %s, Author: %s, ISBN: %d, ID: %d, Borrowed: %b%n",
                        copy.getTitle(), copy.getAuthor(), copy.getId(), copy.getId(), copy.isBorrowed());
            }
        }
        return bookCopies;
    }

    public String outputAllBooks(String title, String author, int year, int isbn) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle() == title && books.get(i).getAuthor() == author && books.get(i).getIsbn() == isbn && books.get(i).getYear() == year) {
                System.out.println(books.get(i));
            }
        }
        String y = String.valueOf(year);
        String s = String.valueOf(isbn);
        String output = new String( title + author + y + s);
        return output;
    }

    public String outputOfAllBorrowedBookCopies(int id,String title, String author, int year, int bookCopyId) {
        for (int i = 0; i < bookCopies.size(); i++) {
            if (customers.get(i).getId() == id && bookCopies.get(i).getTitle() == title && bookCopies.get(i).getAuthor() == author && bookCopies.get(i).getYear() == year && books.get(i).getYear() == year && bookCopies.get(i).getId() == bookCopyId) {
                System.out.println(books.get(i));
            }
        }
        String y = String.valueOf(year);
        String s = String.valueOf(id);
        String d = String.valueOf(bookCopyId);
        String output = new String( s + title + author + y + d);
        return output;
    }

    public String outputOfNonBorrowedBookCopies(String title, String author, int year, int isbn) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle() == title && books.get(i).getAuthor() == author && books.get(i).getIsbn() == isbn && books.get(i).getYear() == year) {
                System.out.println(books.get(i));
            }
        }
        String y = String.valueOf(year);
        String s = String.valueOf(isbn);
        String output = new String( title + author + y + s);
        return output;
    }

    public String outputOfAllCustomers(int id, String name, String firstName,String paymentStatus, int booksBorrowed) {
        for (Customer customer : customers) {
            if (customer.getId() == id && Objects.equals(customer.getName(), name) && customer.getName() == firstName && customer.getPaymentStatus() == paymentStatus && customer.getNumberOfBooksCurrentlyBorrowed() == booksBorrowed) {
                System.out.println(customer);
            }
        }
        String y = String.valueOf(booksBorrowed);
        String i = String.valueOf(id);
        String output = new String(i + name + firstName+ paymentStatus +y);
        return output;
    }

    public String outputOfAllBorrowedBookCopiesOfCustomer(int customerId, String title, String author, int year, int id) {
        for (Customer customer : customers) {
            if (customer.getId() == customerId) {
                for (BookCopy bookCopy : bookCopies) {
                    if (bookCopy.isBorrowed() && Objects.equals(bookCopy.getTitle(), title) && Objects.equals(bookCopy.getAuthor(), author) && bookCopy.getId() == id && bookCopy.getYear() == year) {
                        System.out.println(bookCopy);
                    }
                }
            }
        }
        String cid = String.valueOf(customerId);
        String y = String.valueOf(year);
        String i = String.valueOf(id);
        String output = new String(cid + title + author + y + i);
        return output;
    }


    public Map<String, Integer> outputNumberBookCopiesPerPublisher() {
        Map<String, Integer> nrBookPerPublisher = new HashMap<>();
        bookCopies.stream().peek(bookCopy -> {
            String publisher = bookCopy.getPublisher();
            nrBookPerPublisher.put(publisher, nrBookPerPublisher.getOrDefault(publisher, 0) + 1);
        });
        return nrBookPerPublisher;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    public ArrayList<BookCopy> getBookCopies() {
        return bookCopies;
    }

    public void setBookCopies(ArrayList<BookCopy> bookCopies) {
        this.bookCopies = bookCopies;
    }
}