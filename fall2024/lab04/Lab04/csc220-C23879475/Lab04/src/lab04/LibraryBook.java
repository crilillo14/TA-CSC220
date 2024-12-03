package lab04;

import java.util.GregorianCalendar;

public class LibraryBook<Type> extends Book 
{
	Type holder;
	GregorianCalendar dueDate;
	
	public LibraryBook(long isbn, String author, String title){
		super(isbn, author, title);
	}

	public Type getHolder()
	{
		return holder;
	}
	
	public GregorianCalendar getDueDate()
	{
		return dueDate;
	}
	
	public void checkin()
	{
		this.dueDate = null;	
		this.holder = null;
	}
	
	public void checkout(Type holder, GregorianCalendar dueDate)
	{
		this.dueDate = new GregorianCalendar(
				dueDate.get(GregorianCalendar.YEAR), 
				dueDate.get(GregorianCalendar.MONTH), 
				dueDate.get(GregorianCalendar.DATE));
		this.holder = holder;
		
	}	

}
