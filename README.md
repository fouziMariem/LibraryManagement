# Java library managment system
___
This Java-based Library Management System allows admins to efficiently manage library resources, and customers to borrow books.
## Features
___
* User authentication System
* History management
* Book management
* Manage user actions : borrow and return 
* **Admins** :
    * Log in as an admin
    * Add an admin
    * Add a user
    * Add a book
    * Delete a book
    * View all history 
    * View history of a specific customer or admin
    * View history of a certain date
    * Log out 

* **Customers** :
    * Log in as a user
    * Borrow a book
    * Return a book
    * View all books
    * View the user’s history
    * Log out 
## Design Decisions
___
* We used the abstract class User as a parent class to the NormalUser class and PremiumUser class .
* The Admin class is used to manage the admins  .
* The Book class is used to handle the books (display the book find a book based on its isbn borrow and return it).
* The Library class is used to manage both books and users of the system (runs the system and displays the menus)  .
* The History class used to handle the history of both admins and users :with each action we add a history describing the action (user or admin/date/type of action).
* The NormalUser class extends the User class and limits the user meaning that they can’t borrow a premium book .
* The PremiumUser class extends the User class and allows the user to borrow premium books.
* To access all users we used a static array in which we store the users.
* To access all admins we used a static array in which we store the admins.
* To access all books we used a static array in which we store the books.

   

