package lab04;

import java.util.GregorianCalendar;
// Lab Part 2.2 What imports do you need to include? Put them here.

/**
 * A LibraryBook is a subclass of Book that contains a holder (a String) and a due date
 * represented by a GregorianCalendar.
 */
public class LibraryBook<Type> extends Book{
	
	/* ADD SOMETHING HERE */ { // Lab Part 2.1
}

    // A LibraryBook contains a holder (a String) and due date represented by
    // a GregorianCalendar
	private Type holder;
    private GregorianCalendar dueDate;
    
    /**
     * Constructs a LibraryBook with the specified ISBN, author, and title.
     *
     * @param isbn   The ISBN number of the book.
     * @param author The author of the book.
     * @param title  The title of the book.
     */
    
	public LibraryBook(long isbn, String author, String title) {
		 super(isbn, author, title);
	     this.holder = null;
	     this.dueDate = null; 

        //Lab Part 2.3.1
    }

    /**
     * Returns the holder of the library book.
     *
     * @return A String representing the holder of the book, or null if the book is not checked out.
     */
    public Type getHolder() {
        //Lab Part 2.3.2
        return this.holder; // placeholder
    }
    public void setHolder(Type holder) {
        this.holder = holder;
    }
    /**
     * Returns the due date of the library book.
     *
     * @return A GregorianCalendar object representing the due date of the book, or null if the book is not checked out.
     */
    public GregorianCalendar getDueDate() {
        //Lab Part 2.3.3
    	//System.out.println(this.dueDate);
    	//System.out.println(new GregorianCalendar(2008, 1, 1));
    	return this.dueDate; // placeholder
    }
    
    /**
     * Checks in the library book by setting the holder and due date to null.
     */
    public void checkin() {
        //Lab Part 2.3.4
    	this.holder = null;
        this.dueDate = null;
    }
    
    /**
     * Checks out the library book by setting the holder and due date to the specified values.
     *
     * @param holder   The name of the person who is checking out the book.
     * @param dueDate  The due date for the book's return.
     */
    public void checkout(Type holder, GregorianCalendar dueDate){
        //Lab Part 2.3.5
    	this.holder = holder;
        this.dueDate = dueDate;
    }	

    // Do not override the equals method in Book

}