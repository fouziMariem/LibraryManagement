// Import necessary Java libraries
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

// Define an abstract class named User
public abstract class User {

    // Class-level variables to keep track of all users, the number of users, and borrowed books
    protected static List<User> allUsers = new ArrayList<>();
    protected static int numberOfUser = 0;
    protected List<Integer> borrowedBooks;

    // Instance variables for user details
    protected int userId;
    protected int CIN;
    protected String userName;
    protected String dateSubscription;
    protected String dateEndSubscription;
    protected String password;

    // Constructor to initialize user details
    public User(int CIN, String userName, String dateSubscription, String dateEndSubscription, String password) {
        numberOfUser++;
        this.userId = numberOfUser;
        this.CIN = CIN;
        this.userName = userName;
        this.dateSubscription = dateSubscription;
        this.dateEndSubscription = dateEndSubscription;
        this.password = password;

        // Add the current user to the list of all users
        allUsers.add(this);

        // Initialize the list of borrowed books for the current user
        borrowedBooks = new ArrayList<>();
    }

    // Getter methods for various user details
    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getDateSubscription() {
        return dateSubscription;
    }

    public String getDateEndSubscription() {
        return dateEndSubscription;
    }

    public void setDateEndSubscription(String dateEndSubscription) {
        this.dateEndSubscription = dateEndSubscription;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    // Static methods to get the number of users and the list of all users
    public static int getNumberOfUser() {
        return numberOfUser;
    }

    public static List<User> getAllUsers() {
        return allUsers;
    }

    // Static method to find a user by their user ID
    public static User findUser(int userId) {
        for (int i = 0; i < allUsers.size(); i++) {
            if (userId == allUsers.get(i).getUserId()) {
                return allUsers.get(i);
            }
        }
        return null;
    }

    // Getter method for CIN (Customer Identification Number)
    public int getCIN() {
        return CIN;
    }

    // Abstract method to be implemented by subclasses for borrowing a book
    public abstract void borrowBook(int isbn);

    // Method to return a borrowed book
    public void returnBook(int isbn) {
        Book book = Book.findBook(isbn);
        if (book != null) {
            if (borrowedBooks.contains(isbn)) {
                book.returnBook();
                borrowedBooks.remove(isbn);
                History.addReturningHistory(this.userId, isbn);
            }
        } else {
            System.out.println("This book doesn't exist in the library");
        }
    }

    // Method to display the borrowing history of the user
    public void displayHistory() {
        for (int i = 0; i < History.getHistory().size(); i++) {
            if (History.getHistory().get(i).getId() == userId & Objects.equals(History.getHistory().get(i).getUserType(), "user")) {
                History.getHistory().get(i).displayHistory();
            }
        }
    }

    // Method to perform various user operations based on the provided operation code
    public void runUser(int operation) {
        Scanner scanner = new Scanner(System.in);
        int isbn;
        switch (operation) {
            case 1:
                System.out.println("Enter ISBN of the book you want to borrow");
                isbn = Integer.parseInt(scanner.nextLine());
                borrowBook(isbn);
                break;
            case 2:
                System.out.println("Enter ISBN of the book you want to return");
                isbn = Integer.parseInt(scanner.nextLine());
                returnBook(isbn);
                break;
            case 3:
                Book.diplayAllBooks();
                break;
            case 4:
                this.displayHistory();
                break;
        }
    }
}
