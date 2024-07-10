import java.util.ArrayList;
import java.util.Scanner;

public class CommandParser {
    private Scanner scanner;
    private Library library;
    private Customer customer;

    public CommandParser() {
        this.scanner = new Scanner(System.in);
        this.library = new Library(new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        // Initialize with a placeholder, to be replaced when importing customers
        this.customer = null;
    }

    public void run() {
        int userSelection = 0;
        while (userSelection != 7) {
            userSelection = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (userSelection) {
                case 1:
                    importBooks();
                    break;
                case 2:
                    importBookCopies();
                    break;
                case 3:
                    importCustomers();
                    break;
                case 4:
                    searchBookCopies();
                    break;
                case 5:
                    borrowBook();
                    break;
                case 6:
                    returnBook();
                    break;
                case 7:
                    exit();
                    break;
                default:
                    System.out.println("Invalid selection. Please select again...");
            }
        }
    }

    public void displayMenu() {
        System.out.println("=== Main Menu ===");
        System.out.println("1. Import books from CSV");
        System.out.println("2. Import book copies from CSV");
        System.out.println("3. Import customers from CSV");
        System.out.println("4. Search book copies");
        System.out.println("5. Borrow book");
        System.out.println("6. Return book");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private void importBooks() {
        System.out.print("Enter path to books CSV file: ");
        String filePath = scanner.nextLine();
        library.importingCsvFileBooks(filePath);
    }

    private void importBookCopies() {
        System.out.print("Enter path to book copies CSV file: ");
        String filePath = scanner.nextLine();
        library.importingCsvFileBookcopies(filePath);
    }

    private void importCustomers() {
        System.out.print("Enter path to customers CSV file: ");
        String filePath = scanner.nextLine();
        library.importingCsvFileCustomers(filePath);

        if (!library.getCustomers().isEmpty()) {
            this.customer = library.getCustomers().get(0);
        } else {
            System.out.println("No customers found in the file.");
        }
    }

    private void searchBookCopies() {
        System.out.print("Enter search criteria (ISBN, title, author): ");
        String criteria = scanner.nextLine();
        library.searchingBookcopiesViaIsbnAuthorTitle(criteria);
    }

    private void borrowBook() {
        if (customer == null) {
            System.out.println("No customer is currently selected. Please import customers first.");
            return;
        }
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter book copy ID: ");
        int bookCopyId = scanner.nextInt();
        customer.borrowBook(customerId, bookCopyId);
    }

    private void returnBook() {
        if (customer == null) {
            System.out.println("No customer is currently selected. Please import customers first.");
            return;
        }
        System.out.print("Enter customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter book copy ID: ");
        int bookCopyId = scanner.nextInt();
        customer.returnBook(customerId, bookCopyId);
    }

    private void exit() {
        System.out.println("Exiting...");
    }
}