package lab04;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

import java.util.Comparator;


/**
 * Class representation of a library (a collection of library books).
 * 
 */
public class Library<Type> 
{

  protected ArrayList<LibraryBook<Type>> library;

  public Library() 
  {
    library = new ArrayList<LibraryBook<Type>>();
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
  public void add(long isbn, String author, String title) 
  {
    library.add(new LibraryBook(isbn, author, title));
  }
 void addAll(ArrayList<LibraryBook<Type>> list)
  {
    library.addAll(list);
  }

  
  public void addAll(String filename)
  {
    ArrayList<LibraryBook<Type>> toBeAdded = new ArrayList<LibraryBook<Type>>();

    try
    {
      Scanner fileIn = new Scanner(new File(filename)); 
      int lineNum = 1;

      	while (fileIn.hasNextLine()) 
      	{
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
        	toBeAdded.add(new LibraryBook(isbn, author, title));
   
        lineNum++;
      }
    } catch (FileNotFoundException e) 
    {
      System.err.println(e.getMessage() + "Nothing added to the library.");
      return;
    } catch (ParseException e)
    {
      System.err.println(e.getLocalizedMessage()
          + " formatted incorrectly at line " + e.getErrorOffset()
          + ". Nothing added to the library.");
      return;
    }

    library.addAll(toBeAdded);
  }

   
  public Type lookup(long isbn)
  {
    // *FILL IN -- do not return null unless appropriate
	for (int i = 0; i < library.size(); i++)
	{
		if (library.get(i).getIsbn() == isbn)
		{
			return library.get(i).getHolder();
		}
	}
    return null;
  }

  
  public ArrayList<LibraryBook<Type>> lookup(Type holder) 
  {
    // *FILL IN -- do not return null
	  ArrayList<LibraryBook<Type>>  CheckedOutList = new ArrayList<LibraryBook<Type>>();
	  if (library.size() == 0)
		  return CheckedOutList;
	  for (int i = 0; i < library.size(); i++)
	  {
		  Type BookHolder = library.get(i).getHolder();
		  if (holder.equals(BookHolder))
		  {
			  CheckedOutList.add(library.get(i));
		  }
	  }
	      
	 return CheckedOutList;
  }

  public boolean checkout(long isbn, Type holder, int month, int day, int year) 
  {
	for (int i = 0; i < library.size(); i++)
	{
		if (library.get(i).getIsbn() == isbn)
		{
			if (library.get(i).getHolder() != null)
			{
					return false;
			}else
			{
				GregorianCalendar dueDate = new GregorianCalendar(year, month, day); 
				library.get(i).checkout(holder, dueDate);
				return true;
			}
		}
	}
    return false;
  }

  
  public boolean checkin(long isbn) 
  {
    // *FILL IN -- do not return false unless appropriate
	  for (int i = 0; i < library.size(); i++)
	  {
		  if (library.get(i).getIsbn() == isbn)
		  {
			  if (library.get(i).getHolder() == null)
			  {
				  return false;
			  }else
			  {
				  library.get(i).checkin();
				  return true;
			  }
		  }
	  }
    return false;
  }

  public boolean checkin(Type holder) 
  {
	    // *FILL IN -- do not return false unless appropriate	  
	int counter = 0;
	for (int i = 0; i < library.size(); i++)
	{
		if (holder.equals(library.get(i).getHolder()))
		{
			counter++;
			library.get(i).checkin();
		} 
	}
	
	if (counter > 0)
		return true;
    return false;
  }
  
  
  
  public ArrayList<LibraryBook<Type>> getInventoryList() 
  {
    ArrayList<LibraryBook<Type>> copy = new ArrayList<LibraryBook<Type>>();
    copy.addAll(library);
    IsbnComparator comparator = new IsbnComparator();
    sort(copy, comparator);
    return copy;
  }
  
  protected static <ListType> void sort(ArrayList<ListType> list,
      Comparator<ListType> c) 
  {
    for (int i = 0; i < list.size() - 1; i++) 
    {
      int j, minIndex;
      for (j = i + 1, minIndex = i; j < list.size(); j++)
        if (c.compare(list.get(j), list.get(minIndex)) < 0)
          minIndex = j;
      ListType temp = list.get(i);
      list.set(i, list.get(minIndex));
      list.set(minIndex, temp);
    }
  }

  
  protected class IsbnComparator implements Comparator<LibraryBook<Type>>
  {
	  public int compare(LibraryBook<Type> lhs, LibraryBook<Type> rhs)
	  {
	    	
	    	int result = (int) (lhs.getIsbn() - rhs.getIsbn());
	    	return result;	
	  
	    }
  }

 
  public ArrayList<LibraryBook<Type>> getOrderedByAuthor() 
  {
    // FILL IN -- do not return null
	  	
	  	ArrayList<LibraryBook<Type>> copy = new ArrayList<LibraryBook<Type>>();
	  	
	  	copy.addAll(library);

	    AuthorComparator comparator = new AuthorComparator();

	    sort(copy, comparator);

	    return copy;
  }
  

 
  public ArrayList<LibraryBook<Type>> getOverdueList() 
  {
	  	ArrayList<LibraryBook<Type>> copy = new ArrayList<LibraryBook<Type>>();
	  	GregorianCalendar dueDate = new GregorianCalendar();
	  	for (int i=0; i< library.size(); i++) 
	  	{
	  		if ((library.get(i).getDueDate() != null) && (dueDate.compareTo(library.get(i).getDueDate())> 0))
	  		{
	  			copy.add(library.get(i));
	  		}
	  	}
	  	
  		DueDateComparator comparator = new DueDateComparator();
  		
  		sort(copy, comparator);

  		return copy;
  }
  
  
  protected class AuthorComparator implements 
Comparator<LibraryBook<Type>> 
  {

    public int compare(LibraryBook<Type> lhs, LibraryBook<Type> rhs) 
    {
    	
    	int result = lhs.getAuthor().compareTo(rhs.getAuthor());
    	if ( result != 0 )
    		return result;
    	else
    		return lhs.getTitle().compareTo(rhs.getTitle());
    }
  }
  
  
  protected class DueDateComparator implements Comparator<LibraryBook<Type>> 
  {
	  	
	  public int compare(LibraryBook<Type> lhs, LibraryBook<Type> rhs) {
	    	
	    	return(lhs.getAuthor().compareTo(rhs.getAuthor()));
	  
	  }
   
   	  
  }
  
}







