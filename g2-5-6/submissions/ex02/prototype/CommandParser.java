import java.util.Scanner;
import commands.Command1;
import commands.Command2;
import commands.Command3;

public class CommandParser {
    private Scanner scanner;

    public CommandParser() {
        this.scanner = new Scanner(System.in);
    }

    public void run(){
        int userSelection;
        while (userSelection != 3){
            userSelection = scanner.nextInt();
            switch (userSelection){
                case 1: option1(); break;
                case 2: option2(); break;
                case 3: option3(); break;
                default: "Invalid selection. Please select again..."
            }
        }
    }

    private void displayMenu() {
        System.out.println("=== Main Menu ===");
        System.out.println("1. Add book");
        System.out.println("2. Delete book");
        System.out.println("3. Exit the page");
        System.out.print("Enter your choice: ");
    }

    public void option1(){
        System.out.println("Executing command 1...");
        Command1 command1 = new Command1();
        command1.execute();
    }

    public void option2(){
        System.out.println("Executing command 2...");
        Command2 command2 = new Command2();
        command2.execute();
    }

    public void option3(){
        System.out.println("Executing command 3...");
        Command3 command3 = new Command3();
        command3.execute();
    }
}