package lab03;

import java.util.GregorianCalendar;

public class LibraryBook extends Book {
	String holder;
	GregorianCalendar dueDate;
	
	public LibraryBook(long isbn, String author, String title){
		super(isbn, author, title);
	}

	public String getHolder(){
		return holder;
	}
	
	public GregorianCalendar getDueDate(){
		return dueDate;
	}
	
	public void checkin(){
		// no late late comparison required
		// If a library book is checked in, its holder and due date should be set to null.		
		this.holder = null;
		this.dueDate = null;
	}
	
	public void checkout(String holder, GregorianCalendar dueDate){
		this.holder = new String(holder);
		this.dueDate = new GregorianCalendar(
				dueDate.get(GregorianCalendar.YEAR), 
				dueDate.get(GregorianCalendar.MONTH), 
				dueDate.get(GregorianCalendar.DATE));
		
	}	
	// Do not override the equals method in Book

}