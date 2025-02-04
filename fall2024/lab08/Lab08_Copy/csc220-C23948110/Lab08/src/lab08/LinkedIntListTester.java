package lab08;

public class LinkedIntListTester {
	
	public static void main(String[] args){
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
		System.out.println(list5);
		list5.removeAll(3);
		System.out.println(list5);
		
		if (!list5.toString().equals("[9, 4, 2, 8, 17, 4, 18]"))
			System.err.println("TEST FAILED -- removeAll 3");				
		
		int[] arr6 = {3, 9, 4, 2, 3, 3, 8, 17, 4, 18, 3};
		LinkedIntList list6 = new LinkedIntList(arr6);
		System.out.println("List 6 before: " + list6);
		list6.removeAll(3);
		System.out.println("List 6 after: " + list6);
		
		if (!list6.toString().equals("[9, 4, 2, 8, 17, 4, 18]"))
			System.err.println("TEST FAILED -- removeAll 4");
		System.out.println("Testing Done!!!");			

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

		//System.out.println("List 9: " + list9);
		if (!list9.toString().equals("[1, 1, 8, 8]"))
			System.err.println("TEST FAILED -- stutter 3");
		
		int[] arr10 = {1, 8, 19, 4, 17};
		LinkedIntList list10 = new LinkedIntList(arr10);
		list10.stutter();

		//System.out.println("List 10: " + list10);
		if (!list10.toString().equals("[1, 1, 8, 8, 19, 19, 4, 4, 17, 17]"))
			System.err.println("TEST FAILED -- stutter 4");		
		
		// *** testing shift
		int[] arr11 = {};
		LinkedIntList list11 = new LinkedIntList(arr11);
		list11.shift();
		
		System.out.println("List 11: " + list11);
		if (!list11.toString().equals("[]"))
			System.err.println("TEST FAILED -- shift 1");				
		
		int[] arr12 = {1, 2, 3};
		LinkedIntList list12 = new LinkedIntList(arr12);
		list12.shift();
		
		System.out.println("List 12: " + list12);
		if (!list12.toString().equals("[1, 3, 2]"))
			System.err.println("TEST FAILED -- shift 2");	
		
		int[] arr13 = {1,2};
		LinkedIntList list13 = new LinkedIntList(arr13);
		list13.shift();
		
		System.out.println("List 13: " + list13);
		if (!list13.toString().equals("[1, 2]"))
			System.err.println("TEST FAILED -- shift 3");
		
		int[] arr14 = {10, 31, 42, 23, 44, 75, 86};
		LinkedIntList list14 = new LinkedIntList(arr14);
		list14.shift();
		
		System.out.println("List 14: " + list14);
		if (!list14.toString().equals("[10, 42, 44, 86, 31, 23, 75]"))
			System.err.println("TEST FAILED -- shift 4");
		
		int[] arr15 = {10, 31, 42, 23, 44, 75, 86, 87};
		LinkedIntList list15 = new LinkedIntList(arr15);
		list15.shift();
		
		System.out.println("List 15: " + list15);
		if (!list15.toString().equals("[10, 42, 44, 86, 31, 23, 75, 87]"))
			System.err.println("TEST FAILED -- shift 5");
		System.out.println("Testing Done!!!");							
		
		
	}
}
