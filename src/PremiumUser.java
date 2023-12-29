public class PremiumUser extends User{
    public PremiumUser(int CIN , String userName, String dateSubscription, String dateEndSubscription, String password) {
        super(CIN , userName, dateSubscription, dateEndSubscription, password);
    }
    public void borrowBook(int isbn){
        Book book=Book.findBook(isbn);
        if(book!=null){
            book.borrowBook();
            borrowedBooks.add(isbn);
            History.addBorrowingHistory(this.userId,isbn);
        }
        else{
            System.out.println("This book doesn't exist in the library");
        }
    }

}
