import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Admin {
    private String adminName;
    protected static List<Admin> allAdmins=new ArrayList<>();
    private static int numberOfAdmin=0;
    private int adminId;
    private String password;

    public Admin(String adminName, String password) {
        this.adminName = adminName;
        numberOfAdmin++;
        this.adminId = numberOfAdmin;
        this.password = password;
        allAdmins.add(this);

    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public static int getNumberOfAdmin() {
        return numberOfAdmin;
    }

    public static void setNumberOfAdmin(int numberOfAdmin) {
        Admin.numberOfAdmin = numberOfAdmin;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Admin addAdmin(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Admin name :");
        String name= scanner.nextLine();
        System.out.println("Password :");
        String password= scanner.nextLine();
        Admin newAdmin =new Admin(name,password);
        return newAdmin;
    }
    public User addUser(){
        User newUser;
        Scanner scanner = new Scanner(System.in);
        int userType=0;
        while(true){
            System.out.println("Choose subscription :");
            System.out.println("1--> Normal ");
            System.out.println("2--> Premium ");
            userType= Integer.parseInt(scanner.nextLine());
            if(userType==1 | userType==2 ){
                break;
            }
            System.out.println("not an option retry");
        }

        System.out.println("CIN :");
        int CIN= Integer.parseInt(scanner.nextLine());

        System.out.println("User name :");
        String name= scanner.nextLine();

        System.out.println("Date of subscription :");
        String dateSubscription= scanner.nextLine();

        System.out.println("Date of end of subscription :");
        String dateEndSubscription= scanner.nextLine();

        System.out.println("Password :");
        String password= scanner.nextLine();
        switch (userType){
            case 1:
                newUser =new NormalUser(CIN,name,dateSubscription,dateEndSubscription,password);
                return newUser;
            case 2:
                newUser =new PremiumUser(CIN,name,dateSubscription,dateEndSubscription,password);
                return newUser;
            default:
                return null;
        }


    }

    public static List<Admin> getAllAdmins() {
        return allAdmins;
    }
    public void addBook(){
        Scanner scanner = new Scanner(System.in);
        boolean test=false;
        System.out.println("Adding a book");
        System.out.println("ISBN : ");
        int ISBN= Integer.parseInt(scanner.nextLine());
        System.out.println("quantity : ");
        int quantity= Integer.parseInt(scanner.nextLine());
        if(Book.findBook(ISBN)==null){
            System.out.println("name : ");
            String name= scanner.nextLine();
            System.out.println("number of authors : ");
            int nbAuthors= Integer.parseInt(scanner.nextLine());
            List<String> authors=new ArrayList<>();
            for (int i= 0 ; i<nbAuthors;i++){
                System.out.println("author : ");
                String author= scanner.nextLine();
                authors.add(author);
            }

            while(true) {
                System.out.println("is it premium ? Yes:1 , No:0 ");
                int premium = Integer.parseInt(scanner.nextLine());
                switch (premium) {
                    case 0:
                        test = false;
                        break;
                    case 1:
                        test = true;
                        break;
                }
                if (premium == 0 | premium == 1) {
                    break;
                } else {
                    System.out.println("Retry");
                }
            }
            Book book=new Book(ISBN,name,authors,test,quantity);

        }
        else{
            Book book =Book.findBook(ISBN);
            book.setQuantity(quantity);
        }

        History.addBookHistory(adminId,ISBN,quantity);
    }
    public void deleteBook(){
        Scanner scanner = new Scanner(System.in);
        boolean test=false;
        System.out.println("deleting a book");
        System.out.println("ISBN : ");
        int ISBN= Integer.parseInt(scanner.nextLine());
        System.out.println("quantity : ");
        int quantity= Integer.parseInt(scanner.nextLine());
        if(Book.findBook(ISBN)!=null){
            Book book=Book.findBook(ISBN);
            if(quantity>=book.getQuantity()){
                Book.getAllBooks().remove(book);
            }
            else{
                book.setQuantity(-quantity);
            }
            History.deleteBookHistory(adminId,ISBN,quantity);

        }
    }
    public void displayHistory(){
        for (int i=0; i<History.getHistory().size();i++){
            if(History.getHistory().get(i).getId()==adminId & Objects.equals(History.getHistory().get(i).getUserType(), "admin")){
                History.getHistory().get(i).displayHistory();
            }
        }
    }

    public static Admin findAdmin(int adminId){
        for(int i=0 ;i<allAdmins.size();i++){
            if(adminId==allAdmins.get(i).getAdminId()){
                return allAdmins.get(i);
            }
        }
        return null;
    }
    public void runAdmin(int operation){
        Scanner scanner = new Scanner(System.in);
        switch(operation){
            case 1:
                addBook();
                break;
            case 2:
                deleteBook();
                break;
            case 3:
                Book.diplayAllBooks();
                break;
            case 4:
                History.displayHistoryList();
                break;
            case 5:
                System.out.println("Enter the Id of the user");
                int userId=Integer.parseInt(scanner.nextLine());
                if(User.findUser(userId)!=null){
                    User.findUser(userId).displayHistory();
                }
                else{
                    System.out.println("this user was not found");
                }
                break;
            case 6:
                System.out.println("Enter the Id of the admin");
                int adminId=Integer.parseInt(scanner.nextLine());
                if(Admin.findAdmin(adminId)!=null){
                    Admin.findAdmin(adminId).displayHistory();
                }
                else{
                    System.out.println("this admin was not found");
                }
                break;
            case 7:
                System.out.println("Enter the date");
                String date=scanner.nextLine();
                if(History.findDate(date)){
                    for(int i =0;i<History.getHistory().size();i++){
                        if(Objects.equals(date, History.getHistory().get(i).getDate())){
                            History.getHistory().get(i).displayHistory();
                        }
                    }
                }
                else{
                    System.out.println("no date was found");
                }
                break;
            case 8:
                addAdmin();
                break;
            case 9:
                addUser();
                break;
        }
    }
}
