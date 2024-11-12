package lab08;

public class LinkedIntListTester 
{
	
	public static void main(String[] args)
	{
		LinkedIntList list = new LinkedIntList();

		// ********************* TESTS FOR LAB ****************************//
		
		// *** testing lastIndexof		
		if (list.lastIndexOf(3) != -1)
			System.err.println("TEST FAILED -- lastIndexOf 0");
		
		int[] arr1 = {1, 18, 2, 7, 18, 39, 18, 40};
		LinkedIntList list1 = new LinkedIntList(arr1);
		
		if (list1.lastIndexOf(18) != 6)
			System.err.println("TEST FAILED -- lastIndexOf 1");		
		
		int[] arr2 = {1, 18, 2, 7, 18, 18, 39, 18, 40, 18};
		LinkedIntList list2 = new LinkedIntList(arr2);
		
		if (list2.lastIndexOf(18) != 9)
			System.err.println("TEST FAILED -- lastIndexOf 2");
				
		if (list2.lastIndexOf(7) != 3)
			System.err.println("TEST FAILED -- lastIndexOf 3");		
				
		if (list2.lastIndexOf(36) != -1)
			System.err.println("TEST FAILED -- lastIndexOf 4");	
		
		// *** Additional Testing for lastIndexOf ***

		// Test for a list with a single element
		int[] arr15 = {5};
		LinkedIntList list15 = new LinkedIntList(arr15);
		if (list15.lastIndexOf(5) != 0)
		    System.err.println("TEST FAILED -- lastIndexOf 5");

		// Test for a list with repeated elements in consecutive positions
		int[] arr16 = {1, 2, 2, 2, 2, 3};
		LinkedIntList list16 = new LinkedIntList(arr16);
		if (list16.lastIndexOf(2) != 4)
		    System.err.println("TEST FAILED -- lastIndexOf 6");

		// Test for an empty list
		LinkedIntList list17 = new LinkedIntList();
		if (list17.lastIndexOf(10) != -1)
		    System.err.println("TEST FAILED -- lastIndexOf 7");
				
		// *** testing removeAll
		int[] arr3 = {9, 4, 2, 3, 8, 17, 4, 18};
		LinkedIntList list3 = new LinkedIntList(arr3);
		list3.removeAll(3);
		
		if (!list3.toString().equals("[9, 4, 2, 8, 17, 4, 18]"))
			System.err.println("TEST FAILED -- removeAll 1");
		
		int[] arr4 = {9, 4, 2, 3, 3, 8, 17, 4, 18};
		LinkedIntList list4 = new LinkedIntList(arr4);
		list4.removeAll(3);
		
		if (!list4.toString().equals("[9, 4, 2, 8, 17, 4, 18]"))
			System.err.println("TEST FAILED -- removeAll 2");		

		int[] arr5 = {3, 9, 4, 2, 3, 3, 8, 17, 4, 18};
		LinkedIntList list5 = new LinkedIntList(arr5);
		list5.removeAll(3);
		
		if (!list5.toString().equals("[9, 4, 2, 8, 17, 4, 18]"))
			System.err.println("TEST FAILED -- removeAll 3");				
		
		int[] arr6 = {3, 9, 4, 2, 3, 3, 8, 17, 4, 18, 3};
		LinkedIntList list6 = new LinkedIntList(arr6);
		list6.removeAll(3);
		
		// *** Additional Testing for removeAll ***

		// Test removing the first element in a list
		int[] arr18 = {3, 5, 9, 12};
		LinkedIntList list18 = new LinkedIntList(arr18);
		list18.removeAll(3);
		if (!list18.toString().equals("[5, 9, 12]"))
		    System.err.println("TEST FAILED -- removeAll 5");

		// Test removing the last element in a list
		int[] arr19 = {1, 2, 3, 4, 3};
		LinkedIntList list19 = new LinkedIntList(arr19);
		list19.removeAll(3);
		if (!list19.toString().equals("[1, 2, 4]"))
		    System.err.println("TEST FAILED -- removeAll 6");

		// Test removing all elements (list becomes empty)
		int[] arr20 = {3, 3, 3, 3};
		LinkedIntList list20 = new LinkedIntList(arr20);
		list20.removeAll(3);
		if (!list20.toString().equals("[]"))
		    System.err.println("TEST FAILED -- removeAll 7");

		// Test removing a non-existent element (list just remains unchanged)
		int[] arr21 = {10, 20, 30};
		LinkedIntList list21 = new LinkedIntList(arr21);
		list21.removeAll(5);
		if (!list21.toString().equals("[10, 20, 30]"))
		    System.err.println("TEST FAILED -- removeAll 8");
		
		if (!list6.toString().equals("[9, 4, 2, 8, 17, 4, 18]"))
			System.err.println("TEST FAILED -- removeAll 4");
		System.out.println("Lab Testing Done!!!");	
					
		// ********************* TESTS FOR ASSIGNMENT ****************************//
		
		// *** testing stutter
		int[] arr7 = {};
		LinkedIntList list7 = new LinkedIntList(arr7);
		list7.stutter();

		if (!list7.toString().equals("[]"))
			System.err.println("TEST FAILED -- stutter 1");						
		
		int[] arr8 = {1};
		LinkedIntList list8 = new LinkedIntList(arr8);
		list8.stutter();

		if (!list8.toString().equals("[1, 1]"))
			System.err.println("TEST FAILED -- stutter 2");								
		
		int[] arr9 = {1, 8};
		LinkedIntList list9 = new LinkedIntList(arr9);
		list9.stutter();

		if (!list9.toString().equals("[1, 1, 8, 8]"))
			System.err.println("TEST FAILED -- stutter 3");
		
		int[] arr10 = {1, 8, 19, 4, 17};
		LinkedIntList list10 = new LinkedIntList(arr10);
		list10.stutter();

		if (!list10.toString().equals("[1, 1, 8, 8, 19, 19, 4, 4, 17, 17]"))
			System.err.println("TEST FAILED -- stutter 4");	
		
		// *** Additional Testing for stutter ***

		// Test stuttering a list with all of the identical elements
		int[] arr22 = {7, 7, 7};
		LinkedIntList list22 = new LinkedIntList(arr22);
		list22.stutter();
		if (!list22.toString().equals("[7, 7, 7, 7, 7, 7]"))
		    System.err.println("TEST FAILED -- stutter 5");

		// Test stuttering a larger list
		int[] arr23 = {2, 4, 6, 8, 10};
		LinkedIntList list23 = new LinkedIntList(arr23);
		list23.stutter();
		if (!list23.toString().equals("[2, 2, 4, 4, 6, 6, 8, 8, 10, 10]"))
		    System.err.println("TEST FAILED -- stutter 6");
		
		// *** testing shift
		int[] arr11 = {};
		LinkedIntList list11 = new LinkedIntList(arr11);
		list11.shift();
		
		if (!list11.toString().equals("[]"))
			System.err.println("TEST FAILED -- shift 1");				
		
		int[] arr12 = {1, 2, 3};
		LinkedIntList list12 = new LinkedIntList(arr12);
		list12.shift();
		
		if (!list12.toString().equals("[1, 3, 2]"))
			System.err.println("TEST FAILED -- shift 2");	
		
		int[] arr13 = {1,2};
		LinkedIntList list13 = new LinkedIntList(arr13);
		list13.shift();
		
		if (!list13.toString().equals("[1, 2]"))
			System.err.println("TEST FAILED -- shift 3");
		
		int[] arr14 = {10, 31, 42, 23, 44, 75, 86};
		LinkedIntList list14 = new LinkedIntList(arr14);
		list14.shift();
		
		// *** Additional Testing for shift ***

		// Test shifting a list with an odd number of elements
		int[] arr24 = {1, 3, 5, 7, 9};
		LinkedIntList list24 = new LinkedIntList(arr24);
		list24.shift();
		if (!list24.toString().equals("[1, 5, 9, 3, 7]"))
		    System.err.println("TEST FAILED -- shift 5");

		// Test shifting a list with complete duplicate elements
		int[] arr25 = {4, 4, 4, 4};
		LinkedIntList list25 = new LinkedIntList(arr25);
		list25.shift();
		if (!list25.toString().equals("[4, 4, 4, 4]"))
		    System.err.println("TEST FAILED -- shift 6");

		// Test shifting a large list
		int[] arr26 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		LinkedIntList list26 = new LinkedIntList(arr26);
		list26.shift();
		if (!list26.toString().equals("[1, 3, 5, 7, 9, 2, 4, 6, 8, 10]"))
		    System.err.println("TEST FAILED -- shift 7");
		
		if (!list14.toString().equals("[10, 42, 44, 86, 31, 23, 75]"))
			System.err.println("TEST FAILED -- shift 4");
		System.out.println("Assignment Testing Done!!!");							
		
		
	}
}
