//package foopAss;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
//libraries used to calculate fine amount everyday:
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BookLoan
{
	private static int loanCount = 0;
	static ArrayList<Integer> loanIDs = new ArrayList<>();
	private static ArrayList<Book> loanedBooks = new ArrayList<>();
	private static ArrayList<Patron> loaners = new ArrayList<>();
	private static ArrayList<Boolean> returnStatuses = new ArrayList<>();
	private static ArrayList<Double> bookFineAmts = new ArrayList<>();
	private static ArrayList<LocalDate> borrowDates = new ArrayList<>();
	private static ArrayList<LocalDate> returnDates = new ArrayList<>();
	private static ArrayList<LocalDate> returnDueDates = new ArrayList<>();
	private int loanID;
	private Book loanedB;
	private Patron loaner;
	private boolean returnStatus;
	private double bookFineAmt;
	private LocalDate borrowDate, returnDate, returnDueDate;
	
	//constructor
	public BookLoan() //new added 18nov
	{
		loanID = 0;
		loanedB = null;
		loaner = null;
		returnStatus = false;
		bookFineAmt = 0.00;
		borrowDate = null;
		returnDate = null;
		returnDueDate = null;
	}
	
	
	public void setDetailsWithID(int loanID) //added new 18nov
	{
		this.loanID = loanID;
		loanedB = loanedBooks.get(loanID);
		loaner = loaners.get(loanID);
		returnStatus = returnStatuses.get(loanID);
		borrowDate = borrowDates.get(loanID);
		returnDate = returnDates.get(loanID);
		returnDueDate = returnDueDates.get(loanID);
		bookFineAmt = bookFineAmts.get(loanID);
		//to update the fine amount everyday
		ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(this::calFineAmt, 0, 1, TimeUnit.SECONDS);
        //realistically, time unit need should be DAYS but I put SECONDS just for demonstration
	}
	
	
	public void setBorrowDate(LocalDate newBorrowDate)
	{
		borrowDate = newBorrowDate;
		returnDueDate = newBorrowDate.plusDays(7);
		borrowDates.set(loanID, newBorrowDate);
		returnDueDates.set(loanID, newBorrowDate.plusDays(7));
	}
	
	public void setReturnStatus(boolean returned)
	{
		returnStatus = returned;
		returnStatuses.set(loanID, returned);
	}
	
	public void setFineToPaid()
	{
		loaner.substractFineAmt(bookFineAmt);
		bookFineAmt = 0.00;
		bookFineAmts.set(loanID, bookFineAmt);
	}
	
	//get methods
	public static int getLoanCount() //new added 18nov
	{
		return loanCount;
	}
	
	public int getLoanID()
	{
		return loanID;
	}
	
	public int getLoanedBookID()
	{
		return loanedB.getID();
	}
	
	public int getLoanerID()
	{
		return loaner.getPatronID();
	}
	
	public boolean getReturnStatus()
	{
		return returnStatus;
	}
	
	public double getBookFineAmt()
	{
		return bookFineAmt;
	}
	
	public LocalDate getBorrowDate()
	{
		return borrowDate;
	}
	
	public LocalDate getReturnDate()
	{
		return returnDate;
	}
	
	public LocalDate getReturnDueDate()
	{
		return returnDueDate;
	}
	
	//adding new loan
	public static void addNewLoan(Patron p, Book b)
	{
		loanIDs.add(loanCount);
		loanedBooks.add(b);
		loaners.add(p);
		returnStatuses.add(false);
		bookFineAmts.add(0.00);
		borrowDates.add(LocalDate.now());
		returnDates.add(null);
		returnDueDates.add(LocalDate.now().plusDays(7));
		loanCount++;
		p.addBorrowedBook(b);
		b.setAvailability("Unavailable");
	}
	
	//update loan's return status and fine amount
	public void updateLoan(boolean hasReturned, boolean hasPaidFine)
	{
		setReturnStatus(hasReturned);
		if(returnStatus)
		{
			returnDate = LocalDate.now();
			returnDates.set(loanID, returnDate);//update array list
			loaner.removeBorrowedBook(loanedB);
			loanedB.setAvailability("Available");
		}
		if(hasPaidFine) //beta
		{
			setFineToPaid();
		}
	}
	
	public void calFineAmt()
	{
		if(LocalDate.now().isAfter(returnDueDate))
		{
			long diffInDays = ChronoUnit.DAYS.between(returnDueDate, LocalDate.now());
			bookFineAmt = diffInDays * 3;
			bookFineAmts.set(loanID, bookFineAmt); //update in the arrayList
		}
		updatePatronFineAmt(loaner);

	}
	

	
	public static void updatePatronFineAmt(Patron p)
	{
		int pID = p.getPatronID();
		p.setFineAmt(0); //reset
		for(int i = 0; i < loaners.size(); i++)
		{
			if(loaners.get(i).getPatronID()==pID)
			{
				p.addToFineAmt(bookFineAmts.get(i)); //update again
			}
		}
		
	}
	
	//display the loan details
	public void displayLoan()
	{
		String rs;
		if (returnStatus)
		{
			rs = "Returned";
		}
		else
		{
			rs = "Borrowing";
		}
		System.out.println("Loan id: " +getLoanID());
		System.out.println("Loaned book ID: " +getLoanedBookID());
		System.out.println("Loaner's patron ID: " +getLoanerID());
		System.out.println("Return status: " +rs);
		System.out.println("Fine amount: RM" +String.format("%.2f", getBookFineAmt()));
		System.out.println("Borrow date: " +getBorrowDate());
		if(returnDate != null)
		{
			System.out.println("Return date: " +getReturnDate());
		}
		else
		{
			System.out.println("Return date: (not applicable)");
		}
		System.out.println("Return due date: " +getReturnDueDate());
	}
	

}
