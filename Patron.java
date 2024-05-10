import java.util.ArrayList;

public class Patron
{
	private int patronId;
	private double fineAmt;
	private ArrayList<Book> borrowedBookList = new ArrayList<>();
	
	//default constructor
	public Patron()
	{
		patronId = 0;
		fineAmt = 0.00;
		borrowedBookList = null;
	}
	
	//overloaded constructor
	public Patron(int patronId)
	{
		this.patronId = patronId;
	}
	
	public void setFineAmt(double fineAmt)
	{
		this.fineAmt = fineAmt;
	}
	
	public void addToFineAmt(double singleFineAmt)
	{
		fineAmt += singleFineAmt;
	}
	
	public void substractFineAmt(double singleFineAmt)
	{
		fineAmt -= singleFineAmt;
	}
	
	public int getPatronID()
	{
		return patronId;
	}
	
	public double getFineAmt()
	{
		return fineAmt;
	}
	
	public void displayPatronID()
	{
		System.out.println("Patron id: " +getPatronID());
	}
	
	private void issueReceipt(double prevFineAmt, double payAmt, double changeAmt)
	{
		System.out.println("Previous fine amount :" +prevFineAmt);
		System.out.println("Paid: " +payAmt);
		System.out.println("Change: " +String.format("%.2f", changeAmt));
		System.out.println("Current fine amount: "+fineAmt);
	}
	
	public void displayBorrowedBooks()
	{
		for(Book b:borrowedBookList)
		{
			System.out.println(b.getID());
		}
	}
	
	public void addBorrowedBook(Book bb)
	{
		borrowedBookList.add(bb);
	}
	
	public void removeBorrowedBook(Book bb)
	{
		borrowedBookList.remove(bb);
	}
	
	
}
