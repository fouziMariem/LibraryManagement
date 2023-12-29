import java.util.ArrayList;
import java.util.List;

public class Book {
    private static List<Book> allBooks = new ArrayList<>();
    private int ISBN;
    private String name;
    private List<String> authors;
    private int quantity;
    private boolean premium;

    public Book(int ISBN, String name, List<String> authors,boolean premium,int quantity) {
        this.ISBN = ISBN;
        this.name = name;
        this.authors = authors;
        this.premium =premium;
        this.quantity=quantity;
        allBooks.add(this);
    }
    public void borrowBook(){
        if(quantity>0){
            System.out.println("Book borrowed successfully.");
            quantity--;
        }
        else{
            System.out.println("This book is not available.");
        }
    }
    public void returnBook(){
        quantity++;
        System.out.println("Book returned successfully.");
    }

    public static List<Book> getAllBooks() {

        return allBooks;
    }

    public int getISBN() {

        return ISBN;
    }

    public static Book findBook(int isbn){
        for(int i= 0 ;i<allBooks.size();i++){
            if(isbn==allBooks.get(i).getISBN()){
                return allBooks.get(i);
            }
        }
        return null;
    }
    public boolean isPremium(){

        return premium;
    }

    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }

    public int getQuantity() {
        return quantity;
    }
    public void displayBook(){
        System.out.println("ISBN : "+ISBN);
        System.out.println("Name : "+name);
        System.out.println("Authors : ");
        for (int i=0;i<authors.size();i++){
            System.out.println(authors.get(i));
        }
        if(quantity!=0){
            System.out.println("State : Available");
        }
        else{
            System.out.println("State : Not available");
        }

    }
    public static void diplayAllBooks(){
        System.out.println("Books : ");
        for(int i =0 ;i< allBooks.size();i++){
            Book book=allBooks.get(i);
            book.displayBook();
        }
    }
}
