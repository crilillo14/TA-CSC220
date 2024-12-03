package lab04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Testing class for LibraryRef.
 * 
 * 
 */
public class CheckLab {

  public static void main(String[] args) {
    // test an empty library
	  
	  int []gradePoint = {0,0,0,0,0};
	  /**
	   * index 0: OrderByAuthor
	   * index 1: getOrderedbyAuthor
	   * index 2: OrderByDueDate
	   * index 3: getOverdueList
	   * index 4: orderByISBN
	   */
      int []givenPoint = {10,10,10,15,10};
      String output = "";
      try{
    	  	LibraryRef<String> libRef = new LibraryRef<String>();
    	  	
		    libRef.add(1, "Thomas L. Friedman", "The World is Flat");
		    libRef.add(99, "Jon Krakauer", "Into the Wild");
		    libRef.add(23, "David Baldacci", "Simple Genius");
		    libRef.add(77, "Test 1 ", "Test 1 2nd book");
		    libRef.add(44, "Test 2", "Test 2 2nd book");
		    libRef.add(66, "Test 3", "Test 3 2nd book");
		    libRef.add(7, "Test 1 ", "Test 1 1st book");
		    libRef.add(4, "Test 2", "Test 2 1st book");
		    libRef.add(6000, "Test 3", "Test 3 1st book");
		    libRef.add(111, "Test 3", "AAAAAAAAAA AAAAAAAAAAA");
		    
		    List<LibraryBook<String>> refISBN = libRef.getOrderedByISBNRef();
		    List<LibraryBook<String>> testISBN = libRef.getInventoryList();
		    
		    if(libRef.checkTwoLibrary(refISBN, testISBN)){
		    	gradePoint[4] = 100;
		    }else{
		    	output += "## OrderByISBN is incorrect and doesn't sort properly\n";
		    }
		    
		    List<LibraryBook<String>> refAuthorSort = libRef.getOrderedByAuthorRef();
		    List<LibraryBook<String>> refAutherOnlySort = libRef.getOrderedByAuthorOnlyRef();
		    List<LibraryBook<String>> testAuthorSort = libRef.getOrderedByAuthor();
		    
		    if(libRef.checkTwoLibrary(refAuthorSort, testAuthorSort)){
		    	gradePoint[0] = 100;
		    	gradePoint[1] = 100;
		    }else if (libRef.checkTwoLibrary(refAutherOnlySort, testAuthorSort)){
		    	output += "## getOrderedByAuthor() doesn't break ties with titles when author is same\n";
		    	gradePoint[0] = 50;
		    	gradePoint[1] = 100;
		    }else{
		    	if(refAuthorSort.size() == testAuthorSort.size()){
		    		output += "## getOrderedByAuthor() doesn't sort, but returns a valid list\n";
		    		gradePoint[1] = 25;
		    	}else{
		    		output += "## getOrderedByAuthor() doesn't sort and returned list is invalid\n";
		    	}
		    	
		    	testAuthorSort = libRef.sortByStuAuthor();
		    	
		    	if(libRef.checkTwoLibrary(refAuthorSort, testAuthorSort)){
		    		gradePoint[0] = 100;
		    	}else if (libRef.checkTwoLibrary(refAutherOnlySort, testAuthorSort)){
		    		output += "## OrderByAuthor compare() doesn't consider title when author is same\n";
		    		gradePoint[0] = 50;
		    	}else{
		    		output += "## OrderByAuthor compare() is incorrect\n";
		    	}
		    	
		    }
		    
		    libRef.checkoutRef(1, "Jane Doe", 3, 11, 2008);
		    libRef.checkoutRef(99, "Jane Doe", 5, 12, 2009);
		    libRef.checkoutRef(7, "Test1", 3, 5, 2007);
		    libRef.checkoutRef(66, "Test1", 7, 11, 2006);
		    libRef.checkoutRef(6000, "Jane Doe", 5, 2, 2019);
		    
		    
		    refAuthorSort = libRef.getOverdueListRef();
		    try{
		    	testAuthorSort = libRef.getOverdueList();
		   
			    if(libRef.checkTwoLibrary(refAuthorSort, testAuthorSort)){
			    	gradePoint[2] = 100;
			    	gradePoint[3] = 100;
			    }else{
			    	if(refAuthorSort.size() == testAuthorSort.size()){
			    		output += "## getOverdueList() doesn't sort, but returns a valid overdue book list\n";
			    		gradePoint[3] = 50;
			    	}else{
			    		output += "## getOverdueList() doesn't sort and returned list is invalid\n";
			    	}
			    	refAuthorSort = libRef.getOverdueListRef();
			    	testAuthorSort = libRef.sortByStuDueDate();
			    	
			    	if(libRef.checkTwoLibrary(refAuthorSort, testAuthorSort)){
			    		gradePoint[2] = 100;
			    	}else{
			    		output += "## OrderByDueDate compare() is incorrect\n";
			    	}
			    }
		    
		    }catch(Exception ex){
		    	output += "## getOverdueList() threw " + ex + "\n"; 
		    	
		    	refAuthorSort = libRef.getOverdueListRef();
		    	testAuthorSort = libRef.sortByStuDueDate();
		    	
		    	if(libRef.checkTwoLibrary(refAuthorSort, testAuthorSort)){
		    		gradePoint[2] = 100;
		    	}else{
		    		output += "## OrderByDueDate compare() is incorrect\n";
		    	}
		    }
		    
      }catch(Exception ex){
      	output = "## program threw " + ex + "\n";
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
  }
}

/**
 * Class representation of a library (a collection of library books).
 * 
 */
class LibraryRef<Type> extends Library<Type>{
	
	 protected static <ListType> void sort(List<ListType> list,
		      Comparator<ListType> c) {
		    for (int i = 0; i < list.size() - 1; i++) {
		      int j, minIndex;
		      for (j = i + 1, minIndex = i; j < list.size(); j++)
		        if (c.compare(list.get(j), list.get(minIndex)) < 0)
		          minIndex = j;
		      ListType temp = list.get(i);
		      list.set(i, list.get(minIndex));
		      list.set(minIndex, temp);
		    }
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
	  public boolean checkoutRef(long isbn, Type holder, int month, int day, int year) {
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
	   * Returns the list of library books, sorted by author
	   */
	  public List<LibraryBook<Type>> getOrderedByAuthorRef() {
	    // *FILL IN -- do not return null
	    //return null;
		  
		  List<LibraryBook<Type>> libraryCopy = new ArrayList<LibraryBook<Type>>();
		  libraryCopy.addAll(library);
		  
		  OrderByAuthorRef comparator = new OrderByAuthorRef();
		  
		  sort(libraryCopy, comparator);
		  
		  return libraryCopy;
	  }

	  public List<LibraryBook<Type>> getOrderedByAuthorOnlyRef() {
		    // *FILL IN -- do not return null
		    //return null;
			  
			  ArrayList<LibraryBook<Type>> libraryCopy = new ArrayList<LibraryBook<Type>>();
			  libraryCopy.addAll(library);
			  
			  OrderByOnlyAuthorRef comparator = new OrderByOnlyAuthorRef();
			  
			  sort(libraryCopy, comparator);
			  
			  return libraryCopy;
		  }
	  /**
	   * Returns the list of library books whose due date is older than the input
	   * date. The list is sorted by date (oldest first).
	   *
	   * If no library books are overdue, returns an empty list.
	   */
	  public List<LibraryBook<Type>> getOverdueListRef() {
		ArrayList<LibraryBook<Type>> libraryCopy = new ArrayList<LibraryBook<Type>>();
		GregorianCalendar today = new GregorianCalendar();
	
		// Check for overdue books based on the current date
		for (LibraryBook<Type> book : library) {
			if (book.getDueDate() != null && book.getDueDate().compareTo(today) < 0) {
				libraryCopy.add(book);
			}
		}
	
		OrderByDueDateRef comparator = new OrderByDueDateRef();
		sort(libraryCopy, comparator);
	
		return libraryCopy;
	}
	

	 /**
	   * Comparator that defines an ordering among library books using the author,  and book title as a tie-breaker.
	   */
	  protected class OrderByAuthorRef implements 
	Comparator<LibraryBook<Type>> {

	    // *FILL IN
		  public int compare(LibraryBook<Type> lhs, LibraryBook<Type> rhs){
			  if (lhs.getAuthor().equals(rhs.getAuthor()))
				  return lhs.getTitle().compareTo(rhs.getTitle());
			  else
				  return lhs.getAuthor().compareTo(rhs.getAuthor());
		  }
	  }

	  protected class OrderByOnlyAuthorRef implements 
		Comparator<LibraryBook<Type>> {

		    // *FILL IN
			  public int compare(LibraryBook<Type> lhs, LibraryBook<Type> rhs){
				 
				  return lhs.getAuthor().compareTo(rhs.getAuthor());
			  }
		  }
	  
	  /**
	   * Comparator that defines an ordering among library books using the due date.
	   */
	  protected class OrderByDueDateRef implements Comparator<LibraryBook<Type>> {

	    // *FILL IN
		  public int compare(LibraryBook<Type> lhs, LibraryBook<Type> rhs){
			  return lhs.getDueDate().compareTo(rhs.getDueDate());
		  }	  
	  }
	  
	  public boolean checkTwoLibrary(List<LibraryBook<Type>> ref1,
			  List<LibraryBook<Type>> test){
		  if(ref1.size() != test.size())
			  return false;
		  try{
			  for(int i=0;i<ref1.size();i++){
				  if(ref1.get(i).getIsbn() != test.get(i).getIsbn())
					  return false;
			  }
		  }catch(Exception ex){
			  return false;
		  }
		  
		  return true;
	  }
	  
	  public List<LibraryBook<Type>> getUnsortedList(){
		  ArrayList<LibraryBook<Type>> libraryCopy = new ArrayList<LibraryBook<Type>>();
		  libraryCopy.addAll(library);
		  return libraryCopy;
	  }
	  
	  public List<LibraryBook<Type>> getUnsortedDueList(){
		  ArrayList<LibraryBook<Type>> libraryCopy = new ArrayList<LibraryBook<Type>>();
		  for (int i = 0; i < library.size(); i++){
			  if (library.get(i).getDueDate() != null){
				 libraryCopy.add(library.get(i));
			  }
		  }
		  return libraryCopy;
	  }
	  
	  public List<LibraryBook<Type>> sortByStuAuthor(){
		  List<LibraryBook<Type>> unsortList = getUnsortedList();

		  AuthorComparator comparator = new AuthorComparator();
		  
	    	Collections.sort(unsortList, comparator);
	    	return unsortList;
	  }
	  
	  public List<LibraryBook<Type>> sortByStuDueDate(){
		  	List<LibraryBook<Type>> unsortList = getUnsortedDueList();
			DueDateComparator comparator = new DueDateComparator();
		  
	    	Collections.sort(unsortList, comparator);
	    	return unsortList;
	  }
	  
	  protected class OrderByIsbnRef implements Comparator<LibraryBook<Type>> {

		    /**
		     * Returns a negative value if lhs is smaller than rhs. Returns a positive
		     * value if lhs is larger than rhs. Returns 0 if lhs and rhs are equal.
		     */
		    public int compare(LibraryBook<Type> lhs,
		        LibraryBook<Type> rhs) {
		      return (int) (lhs.getIsbn() - rhs.getIsbn());
		    }
		    
		  }
	  
	  public List<LibraryBook<Type>> getOrderedByISBNRef() {
		    // *FILL IN -- do not return null
		    //return null;
			  
			  ArrayList<LibraryBook<Type>> libraryCopy = new ArrayList<LibraryBook<Type>>();
			  libraryCopy.addAll(library);
			  
			  OrderByIsbnRef comparator = new OrderByIsbnRef();
			  
			  sort(libraryCopy, comparator);
			  
			  return libraryCopy;
		  }
	  
}