public class NormalUser extends User{
    public NormalUser(int CIN , String userName, String dateSubscription, String dateEndSubscription, String password) {
        super(CIN, userName, dateSubscription, dateEndSubscription, password);
    }
    public void borrowBook(int isbn){
        Book book=Book.findBook(isbn);
        if(book==null) {
            System.out.println("This book doesn't exist in the library");
        }
        else if(book!=null & !book.isPremium()){
            book.borrowBook();
            borrowedBooks.add(isbn);
            History.addBorrowingHistory(this.userId,isbn);
        }

        else {
            System.out.println("available only for premium user");
        }

    }

}
