package lab03;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class CheckLab {
	
	public static void main(String[] args) {
	    // test an empty library
		  
		  int []gradePoint = {0,0,0};
		  /**
		   *  15 points - checkin(isbn) 
		   *  20 points - checkin(holder)
		   *  20 points - lookup(holder)
		   */
	      int []givenPoint = {15,20,20};
	      String output = "";
	      try{
				  LibraryRef libRef = new LibraryRef();
				  Library lib = new Library();
				  
				// checkin(isbn) 
				// 25% points for case when book is not in library 
				  
			    try{
				    if(lib.checkin(978037429279L) == libRef.checkin(978037429279L)){
				    	gradePoint[0]+=25;
				    }else{
				    	gradePoint[0]+=0;
				    	output+="## checkin(isbn) returned true when library is empty\n";
				    }
			    }catch(Exception ex){
			    	gradePoint[0]+=0;
			    	output+="## checkin(isbn) threw an exception when library is empty\n";
			    }
			    
			    //checkin(holder) 
			    // 25% points for case when no books with specified holder is found 
			    
			    try{
				    if(lib.checkin("Jane Doe") == libRef.checkin("Jane Doe")){
				    	gradePoint[1]+=25;
				    }else{
				    	gradePoint[1]+=0;
				    	output+="## checkin(holder) returned true when library is empty\n";
				    }
			    }catch(Exception ex){
			    	gradePoint[1]+=0;
			    	output+="## checkin(holder) threw an exception when library is empty\n";
			    }
			    
			    //lookup(holder)
			    // 25% points for case when no books can be found for a specified holder   
			    
			    try{
				    if(lib.lookup("Jane Doe").size() == libRef.lookup("Jane Doe").size()){
				    	gradePoint[2]+=25;
				    }else{
				    	gradePoint[2]+=0;
				    	output+="## lookup(holder) on empty library has size : " + lib.lookup("Jane Doe").size() + " ; should be 0\n";
				    }
			    }catch(Exception ex){
			    	gradePoint[2]+=0;
			    	output+="## lookup(holder) on empty library threw an exception\n";
			    }
			    
			    // now fill the library with some books 
			    
			    lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
			    lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
			    lib.add(9780446580342L, "David Baldacci", "Simple Genius");
			    lib.add(9780374292719L, "F. Scott Fitzgerald", "The Great Gatsby");
			    lib.add(9780330351620L, "Nathaniel Hawthorne", "The Scarlet Letter");
			    lib.add(9780446580332L, "Test 3", "Test 3 book");
			    
			    libRef.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
			    libRef.add(9780330351690L, "Jon Krakauer", "Into the Wild");
			    libRef.add(9780446580342L, "David Baldacci", "Simple Genius");
			    libRef.add(9780374292719L, "F. Scott Fitzgerald", "The Great Gatsby");
			    libRef.add(9780330351620L, "Nathaniel Hawthorne", "The Scarlet Letter");
			    libRef.add(9780446580332L, "Test 3", "Test 3 book");
			    
			    // checkin(isbn) 
			    // 25% points for case when book is already checked in (should return false) 
			    // total % of points for checkin(isbn): 50% now 
			    
			    try{
				    if(lib.checkin(9780374292799L) == libRef.checkin(9780374292799L)){
				    	gradePoint[0]+=25;
				    }else{
				    	gradePoint[0]+=0;
				    	output+="## checkin(isbn) returned true on library with books, no checkouts\n";
				    }
			    }catch(Exception ex){
			    	gradePoint[0]+=0;
			    	output+="## checkin(isbn) threw an exception on library with books, no checkouts\n";
			    }
			    
			    // lookup(holder)
			    // 25% points for case when no books have been checked-out by a holder (should have size 0)
			    // total % of points for lookup(holder): 50% now 
			    
			    try{
				    if(lib.lookup("Jane Doe").size() == libRef.lookup("Jane Doe").size()){
				    	gradePoint[2]+=25;
				    }else{
				    	gradePoint[2]+=0;
				    	output+= "## lookup(holder) on library with books, no checkouts has size : " + lib.lookup("Jane Doe").size() + " ; should be 0\n";
				    }
			    }catch(Exception ex){
			    	gradePoint[2]+=0;
			    	output+= "## lookup(holder) throws an exception on library with books, no checkouts\n";
			    }
			    
			    // try some checkouts 
			    
			    lib.checkout(9780374292799L, "Jane Doe", 1, 1, 2008);
			    lib.checkout(9780374292719L, "Jane Doe", 1, 2, 2009);
			    lib.checkout(9780446580342L, "Test1", 1, 3, 2010);
			    
			    libRef.checkout(9780374292799L, "Jane Doe", 1, 1, 2008);
			    libRef.checkout(9780374292719L, "Jane Doe", 1, 2, 2009);
			    libRef.checkout(9780446580342L, "Test1", 1, 3, 2010);
			    
			    
			    // lookup(holder) 
			    // 50% points for case where holder checks out 2 books 
			    // total % of points for lookup(holder): 100% now
			    
			    try{
				    if(lib.lookup("Jane Doe").size() == libRef.lookup("Jane Doe").size()){
				    	gradePoint[2]+=50;
				    }else{
				    	gradePoint[2]+=0;
				    	output+="## lookup(holder) incorrect; should get all books checked out by a holder\n";
				    }
			    }catch(Exception ex){
			    	gradePoint[2]+=0;
			    	output+="## lookup(holder) throws an exception when checking out\n";
			    }
			    
			    // checkin(isbn) 
			    // 50% points for check-in of a checked-out book (should return true)
			    // total % of points for checkin(isbn): 100% now 
			    
			    try{
				    if(lib.checkin(9780446580342L) == libRef.checkin(9780446580342L)){
				    	gradePoint[0]+=50;
				    }else{
				    	gradePoint[0]+=0;
				    	output+="## checkin(isbn) returned false on valid checkin\n";
				    }
			    }catch(Exception ex){
			    	gradePoint[0]+=0;
			    	output+="## checkin(isbn) threw exception on valid checkin\n";
			    }
			    
			    
			    // checkin(holder)
			    // 25% points for check-in of all checked out books by holder
			    // (should return true)
			    // total % of points for checkin(holder): 50% now 
			    
			    try{
				    if(lib.checkin("Jane Doe") == libRef.checkin("Jane Doe")){
				    	gradePoint[1]+=25;
				    }else{
				    	gradePoint[1]+=0;
				    	output+="## checkin(holder) returned false on valid checkin\n";
				    }
			    }catch(Exception ex){
			    	gradePoint[1]+=0;
			    	output+="## checkin(holder) threw exception on valid checkin\n";
			    }
			    
			    // checkin(holder)
			    // 50% points for check-in by a holder; should return false now 
			    // since all books have been checked in. 
			    // total % of points for checkin(holder): 100%
			    
			    try{
				    if(lib.checkin("Jane Doe") == libRef.checkin("Jane Doe")){
				    	gradePoint[1]+=50;
				    }else{
				    	gradePoint[1]+=0;
				    	output+="## checkin(holder) incorrect; when multiple books are checked-out by a holder, all should be removed when checked-in.\n";
				    }
			    }catch(Exception ex){
			    	gradePoint[1]+=0;
			    	output+="## checkin(holder) threw exception on valid checkin\n";
			    }
			    
			    
	      }catch(Exception ex){
	      	output += "## program threw " + ex + "\n";
	      }
	    arrayPrint(gradePoint,givenPoint);
	    System.out.println(output);
	  }
	  
	  public static void arrayPrint(int []arr,int []givenpoint){
		  System.out.print("$$");
		  int i=0;
		  for(int r: arr){
			  //System.out.println(givenpoint[i]);
			  System.out.print((r*givenpoint[i])/100);
			  System.out.print("$$");
			  i++;
		  }
		  //System.out.println();
	  }
	}

	/**
	 * Class representation of a library (a collection of library books).
	 * 
	 */
	class LibraryRef {

	  private ArrayList<LibraryBookRef> library;

	  public LibraryRef() {
	    library = new ArrayList<LibraryBookRef>();
	  }

	  /**
	   * Add the specified book to the library, assume no duplicates.
	   * 
	   * @param isbn --
	   *          ISBN of the book to be added
	   * @param author --
	   *          author of the book to be added
	   * @param title --
	   *          title of the book to be added
	   */
	  public void add(long isbn, String author, String title) {
	    library.add(new LibraryBookRef(isbn, author, title));
	  }
	  

	  /**
	   * Add the list of library books to the library, assume no duplicates.
	   * 
	   * @param list --
	   *          list of library books to be added
	   */
	  public void addAll(ArrayList<LibraryBookRef> list) {
	    library.addAll(list);
	  }

	  /**
	   * Add books specified by the input file. One book per line with ISBN, author,
	   * and title separated by tabs.
	   * 
	   * If file does not exist or format is violated, do nothing.
	   * 
	   * @param filename
	   */
	  public void addAll(String filename) {
	    ArrayList<LibraryBookRef> toBeAdded = new ArrayList<LibraryBookRef>();

	    try {
	      Scanner fileIn = new Scanner(new File(filename));
	      int lineNum = 1;

	      while (fileIn.hasNextLine()) {
	        String line = fileIn.nextLine();

	        Scanner lineIn = new Scanner(line);
	        lineIn.useDelimiter("\\t");

	        if (!lineIn.hasNextLong())
	          throw new ParseException("ISBN", lineNum);
	        long isbn = lineIn.nextLong();

	        if (!lineIn.hasNext())
	          throw new ParseException("Author", lineNum);
	        String author = lineIn.next();

	        if (!lineIn.hasNext())
	          throw new ParseException("Title", lineNum);
	        String title = lineIn.next();

	        toBeAdded.add(new LibraryBookRef(isbn, author, title));

	        lineNum++;
	      }
	    } catch (FileNotFoundException e) {
	      System.err.println(e.getMessage() + " Nothing added to the library.");
	      return;
	    } catch (ParseException e) {
	      System.err.println(e.getLocalizedMessage()
	          + " formatted incorrectly at line " + e.getErrorOffset()
	          + ". Nothing added to the library.");
	      return;
	    }

	    library.addAll(toBeAdded);
	  }

	  /**
	   * Returns the holder of the library book with the specified ISBN.
	   * If no book with the specified ISBN is in the library, returns null.
	   * 
	   * @param isbn --
	   *          ISBN of the book to be looked up
	   */
	  public String lookup(long isbn) {
	    // *FILL IN -- do not return null unless appropriate
		for (int i = 0; i < library.size(); i++){
			if (library.get(i).getIsbn() == isbn){
				return library.get(i).getHolder();
			}
		}
	    return null;
	  }

	  /**
	   * Returns the list of library books checked out to the specified holder.
	   * 
	   * If the specified holder has no books checked out, returns an empty list.
	   * 
	   * @param holder --
	   *          holder whose checked out books are returned
	   */
	  public ArrayList<LibraryBookRef> lookup(String holder) {
	    // *FILL IN -- do not return null
		  ArrayList<LibraryBookRef>  CheckedOutList = new ArrayList<LibraryBookRef>();
		  if (library.size() == 0)
			  return CheckedOutList;
		  for (int i = 0; i < library.size(); i++){
			  String BookHolder = library.get(i).getHolder();
			  if (BookHolder != null && holder.equals(BookHolder)){
				  CheckedOutList.add(library.get(i));
			  }
		  }
	    
		 return CheckedOutList;
	  }

	  /**
	   * Sets the holder and due date of the library book with the specified ISBN.
	   * If no book with the specified ISBN is in the library, returns false.
	   * If the book with the specified ISBN is already checked out, returns false.
	   * Otherwise, returns true.
	   * 
	   * @param isbn --
	   *          ISBN of the library book to be checked out
	   * @param holder --
	   *          new holder of the library book
	   * @param month --
	   *          month of the new due date of the library book
	   * @param day --
	   *          day of the new due date of the library book
	   * @param year --
	   *          year of the new due date of the library book
	   */
	  public boolean checkout(long isbn, String holder, int month, int day, int year) {
	    // *FILL IN -- do not return false unless appropriate
		for (int i = 0; i < library.size(); i++){
			if (library.get(i).getIsbn() == isbn){
				if (library.get(i).getHolder() != null){
					return false;
				}else{
					GregorianCalendar dueDate = new GregorianCalendar(year, month, day);
					 
					library.get(i).checkout(holder, dueDate);
					return true;
				}
			}
		}
	    return false;
	  }

	  /**
	   * Unsets the holder and due date of the library book.
	   * If no book with the specified ISBN is in the library, returns false.
	   * If the book with the specified ISBN is already checked in, returns false.
	   * Otherwise, returns true.
	   * 
	   * @param isbn --
	   *          ISBN of the library book to be checked in
	   */
	  public boolean checkin(long isbn) {
	    // *FILL IN -- do not return false unless appropriate
		  for (int i = 0; i < library.size(); i++){
			  if (library.get(i).getIsbn() == isbn){
				  if (library.get(i).getHolder() == null){
					  return false;
				  }else{
					  library.get(i).checkin();
					  return true;
				  }
			  }
		  }
	    return false;
	  }

	  /**
	   * Unsets the holder and due date for all library books checked out by the
	   * specified holder.
	   * If no books with the specified holder are in the library, returns false;
	   * Otherwise, returns true.
	   * 
	   * @param holder --
	   *          holder of the library books to be checked in
	   */
	  public boolean checkin(String holder) {
		    // *FILL IN -- do not return false unless appropriate	  
		int counter = 0;
		for (int i = 0; i < library.size(); i++){
			if (library.get(i).getHolder()!= null && holder.equals(library.get(i).getHolder())){
				counter++;
				library.get(i).checkin();
			}
		}
		
		if (counter > 0)
			return true;
		
	    return false;
	  }
	}

	class BookRef {

		  private long isbn;
		  private String author;
		  private String title;

		  public BookRef(long isbn, String author, String title) {
		    this.isbn = isbn;
		    this.author = author;
		    this.title = title;
		  }

		  // return the author
		  public String getAuthor() {
		    return this.author;
		  }

		  // return the ISBN
		  public long getIsbn() {
		    return this.isbn;
		  }

		  // return the title
		  public String getTitle() {
		    return this.title;
		  }

		  /**
		   * Two books are considered equal if they have the same ISBN, author, and
		   * title.
		   * 
		   * @param other --
		   *          the object begin compared with "this"
		   * @return true if "other" is a BookRef and is equal to "this", false otherwise
		   */
		  
		  public boolean equals(Object other) {
		    // TODO -- do not return false unless appropriate
				if(!(other instanceof BookRef)) // make sure the Object we're comparing to is a Matrix
					return false;
				BookRef b = (BookRef)other; // if the above was not true, we know it's safe to treat 'o' as a Matrix
			  
				if (this.isbn != b.isbn || !this.author.equals(b.author) || !this.title.equals(b.title))
					return false;
				
				return true;
		  }
		  
		  /**
		   * Returns a string representation of the book.
		   */
		  public String toString() {
		    return isbn + ", " + author + ", \"" + title + "\"";
		  }
		}


	class LibraryBookRef extends BookRef {
		String holder;
		GregorianCalendar dueDate;
		
		public LibraryBookRef(long isbn, String author, String title){
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
