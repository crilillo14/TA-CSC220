package lab04;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 * Testing class for Library.
 */
public class LibraryTest 
{

  public static void main(String[] args) 
  {
    // test empty library
 
	  Library lib = new Library();

   
    if (booksCheckedOut == null || booksCheckedOut.size() != 0)
      System.err.println("Test Failed: lookup(holder)");
    
    if (lib.lookup(978037429279L) != null)
        System.err.println("Test Failed: lookup(isbn)");
      
    ArrayList<LibraryBook> booksCheckedOut = lib.lookup("Kris Murray");
      
    if (lib.checkout(978037429279L, "Kris Murray", 4, 8, 2009))
      System.err.println("Test Failed: checkout");
    
    if (lib.checkin(978037429279L))
      System.err.println("Test Failed: checkin(isbn)");
    
    if (lib.checkin("Jane Doe"))
      System.err.println("Test Failed: checkin(holder)");

    
    lib.add(9780374292799L, "Thomas L. Friedman", "The World is Flat");
    lib.add(9780330351690L, "Jon Krakauer", "Into the Wild");
    lib.add(9780446580342L, "David Baldacci", "Simple Genius");

    if (lib.lookup(9780330351690L) != null)
      System.err.println("Test Failed: lookup(isbn)");    
    
    if (!lib.checkout(9780330351690L, "Kris Murray", 4, 8, 2009))
      System.err.println("Test Failed: checkout");
    
    booksCheckedOut = lib.lookup("Kris Murray");
        
    if (booksCheckedOut == null
        || booksCheckedOut.size() != 1|| !booksCheckedOut.get(0).equals(new Book(9780330351690L, "Jon Krakauer", "Into the Wild"))|| !booksCheckedOut.get(0).getHolder().equals("KrisDoe")
        || !booksCheckedOut.get(0).getDueDate().equals(
           new GregorianCalendar(2008, 1, 1)))
    {
      System.err.println("Test Failed: lookup(holder)");
    }
    if (!lib.checkin(9780330351690L))
      System.err.println("Test Failed: checkin(isbn)");
    if (lib.checkin("Kris Murray"))
      System.err.println("Test Failed: checkin(holder)");
    System.out.println("Testing done.");
  }


}
