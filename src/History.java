import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class History {

    // Class-level variable to store the history records
    private static List<History> historyList = new ArrayList<>();

    // Instance variables for a history record
    private int Id;
    private String date;
    private int isbn;
    private String description;
    private String userType;

    // Constructor to create a history record
    public History(int userId, String userType, String date, int isbn, String description) {
        this.Id = userId;
        this.userType = userType;
        this.date = date;
        this.isbn = isbn;
        this.description = description;
        historyList.add(this);
    }

    // Getter method for the date of a history record
    public String getDate() {
        return date;
    }

    // Getter method for the type of user (admin or user) associated with a history record
    public String getUserType() {
        return userType;
    }

    // Static method to get the actual date from the user
    public static String getActualDate() {
        System.out.println("Enter date:");
        Scanner scanner = new Scanner(System.in);
        String date = scanner.nextLine();
        return date;
    }

    // Static method to add a borrowing history record
    public static void addBorrowingHistory(int userId, int isbn) {
        String date = getActualDate();
        History history = new History(userId, "user", date, isbn, "borrow");
    }

    // Static method to add a returning history record
    public static void addReturningHistory(int userId, int isbn) {
        String date = getActualDate();
        History history = new History(userId, "user", date, isbn, "return");
    }

    // Static method to add a book addition history record
    public static void addBookHistory(int adminId, int isbn, int quantity) {
        String date = getActualDate();
        History history = new History(adminId, "admin", date, isbn, "add " + quantity + " book");
    }

    // Static method to add a book deletion history record
    public static void deleteBookHistory(int adminId, int isbn, int quantity) {
        String date = getActualDate();
        History history = new History(adminId, "admin", date, isbn, "delete " + quantity + " book");
    }

    // Static method to get the list of all history records
    public static List<History> getHistory() {
        return historyList;
    }

    // Method to display details of a history record
    public void displayHistory() {
        System.out.println("Date: " + date);
        System.out.println("ID: " + Id);
        System.out.println("Type of user (admin or user): " + userType);
        System.out.println("Book ISBN: " + isbn);
        System.out.println("Description: " + description);
    }

    // Static method to display the details of all history records
    public static void displayHistoryList() {
        for (int i = 0; i < historyList.size(); i++) {
            historyList.get(i).displayHistory();
        }
    }

    // Getter method for the ID of a history record
    public int getId() {
        return Id;
    }

    // Static method to find if a history record with a given date exists
    public static boolean findDate(String date1) {
        for (int i = 0; i < historyList.size(); i++) {
            if (Objects.equals(historyList.get(i).date, date1)) {
                return true;
            }
        }
        return false;
    }
}
