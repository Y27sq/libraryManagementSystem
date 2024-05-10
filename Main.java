import java.util.*;
import java.time.LocalDate;

public class Main {
    static int choice = 0;
    static Scanner sc = new Scanner(System.in);
    static Book b = new Book();
    static BookLoan bl = new BookLoan();
    static Book[] bookArray = new Book[100];
    
    public static void main(String[] args) {
        b.addBook(); //adding the books from the catalogue to the ArrayList
           
    //add books to the bookArray
    	for(int i = 0;i<b.getLength();i++)
		{
			bookArray[i] = new Book(i);
		}
	//hard coding pre-borrowed book
	BookLoan.addNewLoan(p, bookArray[4]); //borrowed book (id 5)
	bl.setDetailsWithID(0);
	bl.setBorrowDate(LocalDate.now().minusDays(14)); //it was borrowed 14 days before
        manageTask();
    }
    
    public static void manageTask(){
        int patreonId;
        System.out.println("=======Welcome admin, what would you like to do today=======");
    do{ System.out.println("0. Exit application");      
        System.out.println("1. Manage book");
        System.out.println("2. Manage user");
    try {
        choice = sc.nextInt();
    }catch(InputMismatchException e){
	       choice = 3;
	        sc.nextLine();
    }   
        if (choice == 0 ) {
            System.out.println("Goodbye");
            System.exit (0);
        } else if (choice == 1) {
            manageBook();
        } else if (choice == 2) {
            manageUser();
        } else if (choice < 0 | choice > 2) {
        System.out.println("Please input a valid number");
        }
        
        
    } while (choice != 1 && choice != 2 ) ;
    }

public static void manageBook() {

    System.out.println("=======Currently managing book=======");
    do {
        System.out.println("0. Return");
        System.out.println("1. Search Book.");
        System.out.println("2. Add Book.");
        System.out.println("3. Archive Book.");
        System.out.println("4. Edit Book.");
        System.out.println("5. View entire catalogue.");
        
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            choice = 5;
            sc.nextLine(); 
        }

        if (choice == 0) {
            manageTask();
        } else if (choice == 1) {
            searchBook();
        } else if (choice == 2) {
            
            System.out.println("=======Currently adding book=======");
            b.addBooks();
            System.out.println("Succesfully added books. Returning to managing books...");
            manageBook();
            
        } else if (choice == 3) {
            
            System.out.println("=======Currently archiving book=======");
            System.out.println("Enter the id of book you want to archive :");
            int bookToArchive;
            try {
            bookToArchive = sc.nextInt();
            } catch (InputMismatchException e) {
            bookToArchive = 0; 
            sc.nextLine();
            }
            b.setId (bookToArchive);
            b.archiveBook();
            System.out.println("Returning to managing books...");
            manageBook();
            
        }else if (choice == 4) {
           editBook();
   
        }else if (choice == 5) {
          System.out.println("=======Displaying entire catalogue=======");
          b.displayAll();
          System.out.println("Returning to managing books...");
          manageBook();
           
        }else if (choice < 0 || choice > 5) {
            System.out.println("Please input a valid number");
        }

    } while (choice != 0 && choice != 1 && choice != 2 && choice != 3 && choice != 4  && choice != 5);
}
    public static void editBook() {
        int editBookChoice = 0;
        int bookIdToEdit = 0 ;
        String name= null,passAvailability = null; 
        int availability = 0;
        
        System.out.println("=======Currently editing book=======");
            do {
                System.out.println("0. Return");
                System.out.println("1. Edit book title");
                System.out.println("2. Edit book availability");

                try {
                    editBookChoice = sc.nextInt();
                } catch (InputMismatchException e) {
                    editBookChoice = 3;
                    sc.nextLine(); 
                }
                
               
                if (editBookChoice == 0) {
                    manageBook();
                } else if (editBookChoice == 1) {
                    do {
                    System.out.println("Enter book id to edit:");
                    // Check if the next input is an integer
                    if (sc.hasNextInt()) {
                        bookIdToEdit = sc.nextInt();
                        if (bookIdToEdit >= b.getLength() || bookIdToEdit < 0) {
                            System.out.println("This book does not exist.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        sc.nextLine();
                        bookIdToEdit = -1; // Set a default value to avoid using an undefined value
                    }
                } while (bookIdToEdit >= b.getLength() || bookIdToEdit < 0);
                    
                    b.setId(bookIdToEdit); 
                    sc.nextLine(); //you can't skip this 
                    System.out.println("Enter a new title:"); 
                
                    name= sc.nextLine(); 
                    b.editBookTitle(name); 
                    b.searchById(); 
                    
                    System.out.println("Returning to edit books...");
                    editBook();
                } else if(editBookChoice==2){
                     do {
                    System.out.println("Enter book id to edit:");
                     if (sc.hasNextInt()) {
                        bookIdToEdit = sc.nextInt();
                        if (bookIdToEdit >= b.getLength() || bookIdToEdit < 0) {
                            System.out.println("This book does not exist.");
                        }
                    } else {
                        System.out.println("Invalid input. Please enter a valid integer.");
                        sc.nextLine();
                        bookIdToEdit = -1; // Set a default value to avoid using an undefined value
                    }
                } while (bookIdToEdit >= b.getLength() || bookIdToEdit < 0);
                    
                    b.setId(bookIdToEdit); 
                    sc.nextLine(); //you can't skip this 
                do{
                    System.out.println("Enter a new status(1 for available/ 2 for Unavailable:"); 
                    try {
                        availability= sc.nextInt(); 
                    } catch (InputMismatchException e) {
                        availability = 3;
                        sc.nextLine(); 
                    }
                    
                    if(availability == 1)
                    {
                        passAvailability = "Available";
                    }else if (availability == 2){
                        
                        passAvailability = "Unavailable";
                    } else {
                        System.out.println("Invalid input, try again.");
                    }
                }while (availability != 1 && availability != 2)  ;
                
                    b.editBookAvai(passAvailability); 
                    b.searchById(); 
                    System.out.println("Returning to edit books...");
                    editBook();
                }else if(editBookChoice < 0 || editBookChoice > 4 ){
                    System.out.println("Please input a valid number");
                }
                
            } while (editBookChoice != 0 && editBookChoice != 1 && editBookChoice != 2 );
    }
    
    public static void searchBook(){
        int searchBookChoice = 0;

        
        System.out.println("=======Currently searching for book=======");
            do {
                System.out.println("0. Return");
                System.out.println("1. Search by book id.");
                System.out.println("2. Search by book title.");
                System.out.println("3. Search by book author.");
                 
                try {
                    searchBookChoice = sc.nextInt();
                } catch (InputMismatchException e) {
                    searchBookChoice = 4;
                    sc.nextLine(); 
                }

                if (searchBookChoice == 0) {
                    manageBook();
                } else if (searchBookChoice == 1) {
                    int bookIdToSearch = 100 ;
                    System.out.println("Enter book id to search:");
                    try {
                        bookIdToSearch = sc.nextInt();
                    } catch (InputMismatchException e) {
                        bookIdToSearch = 0;
                        sc.nextLine(); 
                    }
                
                    b.setId (bookIdToSearch);
                    b.searchById();
                    System.out.println("Returning to searching for books...");
                    searchBook();
                    
                } else if (searchBookChoice == 2) { 

                    System.out.println("Enter book title to search:");
                    String titleToSearch = sc.next();
                    b.searchByTitle(titleToSearch);
                    System.out.println("Returning to searching for books...");
                    searchBook();
                    
                } else if (searchBookChoice == 3) { 

                    System.out.println("Enter book author to search:");
                    String authorToSearch = sc.next();
                    b.searchByAuthor(authorToSearch);
                    System.out.println("Returning to searching for books...");
                    searchBook();
                    
                } else if (searchBookChoice < 0 || searchBookChoice > 3) {
                    System.out.println("Please input a valid number");
                }
                
            } while (searchBookChoice != 0 && searchBookChoice != 1 && searchBookChoice != 2 && searchBookChoice != 3);
    }
    
    static Patron p = new Patron(555);
    static int userToManage;
    public static void manageUser() {
        do {
            System.out.println("Enter the patreon id you want to manage:");
            try {
                   userToManage = sc.nextInt();
                } catch (InputMismatchException e) {
                 
                    userToManage = 0;
                    sc.nextLine(); 
                }
            if (userToManage==555){
                manageUserOption();
            } else{
            System.out.println("User does not exist,try again.")  ;
            }
        }while(userToManage != 555);
    }
    

    public static void manageUserOption(){
    final int borrowLimit = 3;
    int amountToBorrow = -1 ,bookToBorrow = -1 ,loanToReturn = -1,bookToPayFine= -1;
    boolean bookLoanExists = false;
    boolean returned ;
    boolean paid ,bookSuccess = false;
    
      System.out.println("=======Managing patron "+ userToManage+"=======");
        do{ 
        System.out.println("0. Return to main menu");
        System.out.println("1. Borrow Book ");
        System.out.println("2. Return Book.");
        System.out.println("3. Pay fine ");
        
            try {
            choice = sc.nextInt();
            }catch(InputMismatchException e){
    	       choice = 4;
    	       sc.nextLine();
            }
            
            if (choice == 0) {
                
                manageTask();
                
            } else if (choice == 1) {
        	for(int i = 0;i<b.getLength();i++)
    		{
    			bookArray[i] = new Book(i);
    		}
            
                System.out.println("=======Borrowing book=======");
            do {
                System.out.println("How many books to borrow?");
                try {
                    amountToBorrow = sc.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid borrow amount, try again ");
                    bookSuccess = false;
                    sc.nextLine();
                    continue; //loops
                }
            
                if (amountToBorrow <= 0) {
                    System.out.println("Invalid borrow amount, try again ");
                    sc.nextLine();
                    bookSuccess = false;
                    continue; //loops
                }
            
                if (amountToBorrow > borrowLimit) {
                    System.out.println("Borrow limit exceeded");
                    System.out.println("Returning to managing patron...");
                    manageUserOption();
                    break;
                } else {
                    int remainingBorrowChances, currentLoanQuant = 0;
                    for (int i = 0; i < BookLoan.getLoanCount(); i++) {
                        bl.setDetailsWithID(i);
                        if (bl.getLoanerID() == p.getPatronID() && !bl.getReturnStatus()) {
                            currentLoanQuant++;
                        }
            
                        if (currentLoanQuant == borrowLimit) {
                            System.out.println("Borrow limit reached.");
                            System.out.println("Returning to managing patron...");
                            manageUserOption();
                            bookSuccess = false;
                            break;
                        }
                    }
                    remainingBorrowChances = borrowLimit - currentLoanQuant;
            
                    if (amountToBorrow > remainingBorrowChances) {
                        System.out.println("You're currently borrowing " + currentLoanQuant + " book.");
                        System.out.println("You can only borrow at most " + remainingBorrowChances + " books, try again ");
                        bookSuccess = false;
                        continue;
                    }
                }
            
                for (int i = 0; i < amountToBorrow; i++) {
                    boolean validInput = false;
            
                    while (!validInput) {
                        System.out.println("Enter the ID of the book to Borrow:");
                        bookToBorrow = sc.nextInt();
            
                        if (bookToBorrow >= 0 && bookToBorrow < b.getLength()) {
                            validInput = true;
                        } else {
                            System.out.println("Book ID does not exist. Please enter a valid Book ID.");
                        }
                    }
            
                    if (bookArray[bookToBorrow].getAvai().equals("Unavailable")) {
                        System.out.println("Book is already borrowed,try again");
                        sc.nextLine();
                        break;
                        
                    } else {
                        BookLoan.addNewLoan(p, bookArray[bookToBorrow]);
                    }
                }
            
                // Display book loans and return to managing patron
                System.out.println("Now displaying all current existing book loan.");
                for (int i = 0; i < BookLoan.getLoanCount(); i++) {
                    bl.setDetailsWithID(i);
                    bl.displayLoan();
                    System.out.println(" ");
                }
            
                System.out.println("Returning to managing patron...");
                manageUserOption();
            
            } while (!bookSuccess);

            
            } else if (choice == 2) {
                System.out.println("=======Returning book======="); 
                System.out.println("Now displaying all current existing book loan:"); 
                
        //         for(int i = 0;i<b.getLength();i++)
        // 		{
        // 			System.out.println(bookArray[i]);
        			
        // 		}
                //display all bookloan
                for (int i = 0; i < BookLoan.getLoanCount(); i++) {
                    bl.setDetailsWithID(i);
                    bl.displayLoan();
                    System.out.println(" ");
                }
            
        		
        		do {
                System.out.println("Now returning book. Enter loan id :");
            
                try {
                    loanToReturn = sc.nextInt();
                } catch (InputMismatchException e) {
                    bookLoanExists = false; //jump tp line 411
                    sc.nextLine();
                    
                   
                } catch (ArrayIndexOutOfBoundsException e) {
                    bookLoanExists = false; //jump tp line 411
                    sc.nextLine();
                   
                }
            
                // Making sure the index is within bounds before accessing the ArrayList
                if (loanToReturn >= 0 && loanToReturn < BookLoan.loanIDs.size()) {
                    bookLoanExists = BookLoan.loanIDs.contains(loanToReturn); //jump to line 415
                } else {
                    bookLoanExists = false;  //jump tp line 411
                }
            
                if (!bookLoanExists) { //error handle invalid inputs
                    System.out.println("Book loan does not exist. Please enter a valid book loan id.");
                    continue;
                } else {
                   	bl.setDetailsWithID(loanToReturn);
                }
                
                 if (bl.getReturnStatus()) { //chcck if book is returned
                    System.out.println("Book returned, you can't return a returned book.");
                    bookLoanExists = false; //goes to end
                }else{
                
        		    returned = true;
                    bookLoanExists = BookLoan.loanIDs.contains(loanToReturn);
                    
                    System.out.println("Will the patreon be paying the fine? (y/n)");
                    String inputYN ;
                    do {
                         try {
                     inputYN = sc.nextLine().toLowerCase();
                        } catch (InputMismatchException e) {
                            paid = false;
                            sc.nextLine();
                            continue;
                        }
                    
                        if (inputYN.equals("y")) {
                            paid = true;
                            break;
                        } else if (inputYN.equals("n")) {
                            paid = false;
                            break;
                        } else {
                            System.out.println("Invalid input. Please enter 'y' or 'n'.");
                        }
                    
                    } while (true);
                    
            		bl.updateLoan(returned, paid);
            		System.out.println("\nDisplaying the returned book loan details:");
            		bl.displayLoan();

            	  System.out.println("Returning to managing patron...");	
                  manageUserOption();
                    
                }
            
            } while (!bookLoanExists);
        		
        		
            }else if (choice == 3) {
                
          System.out.println("=======Pay fine=======");
            do {
                System.out.println("Enter the book loan id to pay fine:");
            
                try {
                    bookToPayFine = sc.nextInt();
                } catch (InputMismatchException e) {
                    bookLoanExists = false;
                    sc.nextLine();
                   
                } catch (ArrayIndexOutOfBoundsException e) {
                    bookLoanExists = false;
                    sc.nextLine();
                   
                }
            
                // Making sure the index is within bounds before accessing the ArrayList
                if (bookToPayFine >= 0 && bookToPayFine < BookLoan.loanIDs.size()) {
                    bookLoanExists = BookLoan.loanIDs.contains(bookToPayFine);
                   
                } else {
                    bookLoanExists = false;
                }
            
                if (!bookLoanExists) {
                    System.out.println("Book loan does not exist. Please enter a valid book loan id.");
                } else {
                    // Perform actions related to bookToPayFine immediately after validating it
                    bl.setDetailsWithID(bookToPayFine);
                   // bookLoanExists = BookLoan.loanIDs.contains(bookToPayFine);
                
                
                    if (!bl.getReturnStatus()) {
                        System.out.println("Book not returned, please return before paying fine.");
                        System.out.println("Returning to managing patron...");
                        manageUserOption();
                    }else{
            
                    bl.setFineToPaid();
                    System.out.println("Fine paid, displaying loan status:");
                    bl.displayLoan();
                    System.out.println("Returning to managing patron...");
                    manageUserOption();
                    }
                }
            
            } while (!bookLoanExists);

		
            }else if (choice < 0 | choice > 3) {
            System.out.println("Please input a valid number");
            }
       
      } while (choice != 0 && choice != 1 && choice != 2 && choice != 3);

     }
}




