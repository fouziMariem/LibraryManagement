import java.util.Objects;
import java.util.Scanner;

public class Library {
    private final String name;

    // Constructor to create a Library object with a given name
    public Library(String name){
        this.name = name;

        // Create the first admin when the Library is initialized
        Admin firstAdmin = new Admin("first admin", "0000");
    }

    // Method for user login
    public User loginUser(){
        boolean test = false;
        int userId = -1;
        String password;
        int retry = -1;

        // Continuously prompt for user login until successful or user chooses not to retry
        while(!test){
            retry = -1;
            Scanner scanner = new Scanner(System.in);
            System.out.println("User Id:");
            userId = scanner.nextInt();
            System.out.println("Password:");
            password = scanner.next();

            // Check if the entered user ID is valid
            if(userId <= User.getNumberOfUser()){
                test = Objects.equals(User.getAllUsers().get(userId - 1).getPassword(), password);
                if(test){
                    System.out.println(User.getAllUsers().get(userId - 1).getUserName() + " successfully connected");
                    return User.getAllUsers().get(userId - 1);
                }
            }

            // Prompt for retry if login fails
            System.out.println("You may have entered a wrong password or ID. Try again?");
            System.out.println("Enter 0 if no / Enter 1 if yes");
            retry = scanner.nextInt();
            if(retry == 0){
                return null;
            }
        }
        return null;
    }

    // Method for admin login
    public Admin loginAdmin(){
        boolean test = false;
        int adminId = -1;
        String password;
        int retry = -1;

        // Continuously prompt for admin login until successful or admin chooses not to retry
        while(!test){
            retry = -1;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Admin Id:");
            adminId = scanner.nextInt();
            System.out.println("Password:");
            password = scanner.next();

            // Check if the entered admin ID is valid
            if(adminId <= Admin.getNumberOfAdmin()){
                test = Objects.equals(Admin.getAllAdmins().get(adminId - 1).getPassword(), password);
                if(test){
                    System.out.println(Admin.getAllAdmins().get(adminId - 1).getAdminName() + " successfully connected");
                    return Admin.getAllAdmins().get(adminId - 1);
                }
            }

            // Prompt for retry if login fails
            System.out.println("You may have entered a wrong password or ID. Try again?");
            System.out.println("Enter 0 if no / Enter 1 if yes");
            retry = scanner.nextInt();
            if(retry == 0){
                return null;
            }
        }
        return null;
    }

    // Method to run the library system
    public void runSystem(){
        while(true){
            Scanner scanner = new Scanner(System.in);
            User currentUser = null;
            Admin currentAdmin = null;
            int loginType = 1;

            // Continuously prompt for login type until a valid option is chosen
            while (true){
                System.out.println("--MENU--");
                System.out.println("1-->Login user");
                System.out.println("2-->Login Admin");
                loginType = Integer.parseInt(scanner.nextLine());
                if(loginType == 1 || loginType == 2){
                    break;
                } else {
                    System.out.println("Retry");
                }
            }

            // Display a message if attempting to login as a user when no users are available
            if(loginType == 1 && User.getAllUsers().isEmpty()){
                System.out.println("There is no user.");
            } else if(loginType == 1){
                // User login and operations
                currentUser = loginUser();
                int operation;
                while(true){
                    System.out.println("1-->Borrow book");
                    System.out.println("2-->Return book");
                    System.out.println("3-->View books");
                    System.out.println("4-->View user history");
                    System.out.println("5-->Logout");
                    operation = Integer.parseInt(scanner.nextLine());
                    if(operation == 1 || operation == 2 || operation == 3 || operation == 4){
                        currentUser.runUser(operation);
                    } else if(operation == 5){
                        currentUser = null;
                        break;
                    }
                }
            } else if(loginType == 2){
                // Admin login and operations
                currentAdmin = loginAdmin();
                int operation;
                while(true){
                    System.out.println("1-->Add book");
                    System.out.println("2-->Delete book");
                    System.out.println("3-->View books");
                    System.out.println("4-->View all history");
                    System.out.println("5-->View history of a specific user");
                    System.out.println("6-->View history of a specific admin");
                    System.out.println("7-->View history of a certain date");
                    System.out.println("8-->Add admin");
                    System.out.println("9-->Add user");
                    System.out.println("10-->Logout");
                    operation = Integer.parseInt(scanner.nextLine());
                    if(operation == 1 || operation == 2 || operation == 3 || operation == 4 || operation == 5 || operation == 6 || operation == 7 || operation == 8 || operation == 9){
                        currentAdmin.runAdmin(operation);
                    } else if(operation == 10){
                        currentAdmin = null;
                        break;
                    }
                }
            }

            // Prompt to go back to the menu or leave the system
            int leave = 1;
            while(true){
                System.out.println("Go back to menu? Yes:1 / Leave:2");
                leave = Integer.parseInt(scanner.nextLine());
                if(leave == 2 || leave == 1){
                    break;
                } else {
                    System.out.println("Retry");
                }
            }
            if(leave == 2){
                break;
            }
        }
    }
}
