import java.util.List;

public class Customer {
    private String name;
    private int id;
    private String lastName;
    private String paymentStatus;
    private int numberOfBooksCurrentlyBorrowed;
    private List<BookCopy> bookCopyList;

    public Customer(int id, String name, String lastName, String paymentStatus, int numberOfBooksCurrentlyBorrowed, List<BookCopy> bookList) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.paymentStatus = paymentStatus;
        this.numberOfBooksCurrentlyBorrowed = numberOfBooksCurrentlyBorrowed;
        this.bookCopyList = bookList;
    }

    public void returnBook(int customerId, int bookCopyId) {
        if (id == customerId) {
            for (BookCopy copy : bookCopyList) {
                if (copy.getId() == bookCopyId && copy.isBorrowed()) {
                    copy.setBorrowed(false);
                    bookCopyList.removeIf(bookCopy -> bookCopy.getId() == bookCopyId);
                    System.out.println("Book returned successfully.");
                    return;
                }
            }
        }
        System.out.println("Returning failed.");
    }

    public void borrowBook(int customerId, int bookCopyId) {
        for (BookCopy copy : bookCopyList) {
            if (copy.getId() == bookCopyId && customerId == id && !copy.isBorrowed()) {
                copy.setBorrowed(true);
                setNumberOfBooksCurrentlyBorrowed(getNumberOfBooksCurrentlyBorrowed() + 1);
                bookCopyList.add(new BookCopy(copy.getTitle(), copy.getAuthor(), copy.getYear(), copy.getId(), copy.isBorrowed(), copy.getCustomerId(), copy.getPublisher()));
                System.out.println("Book borrowed successfully.");
                return;
            }
            System.out.println("Borrowing failed.");
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getNumberOfBooksCurrentlyBorrowed() {
        return numberOfBooksCurrentlyBorrowed;
    }

    public void setNumberOfBooksCurrentlyBorrowed(int numberOfBooksCurrentlyBorrowed) {
        this.numberOfBooksCurrentlyBorrowed = numberOfBooksCurrentlyBorrowed;
    }

    public List<BookCopy> getBookCopyList() {
        return bookCopyList;
    }

    public void setBookcopiesList(List<BookCopy> bookCopyList) {
        this.bookCopyList = bookCopyList;
    }
}