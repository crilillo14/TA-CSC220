package lab08;

public class LinkedIntListTester {
	
	public static void main(String[] args){
		LinkedIntList list = new LinkedIntList();

		// ********************* TESTS FOR LAB ****************************//

		// *** testing lastIndexof	
	//	System.out.println("** log 01 ** check list1 wether or not lastIndexof 3 equal -1 **");
	//	if (list != null) {
	//		System.out.println("** log 01  print list ** " + list.toString() + " ");	
	//		System.out.println(" ");
	//	}
		if (list.lastIndexOf(3) != -1)
			System.err.println("TEST FAILED -- lastIndexOf 0");
		
		int[] arr1 = {1, 18, 2, 7, 18, 39, 18, 40};
		LinkedIntList list1 = new LinkedIntList(arr1);
		
	//	System.out.println(" ");
	//	System.out.println("** log 02 ** check list1 wether or not lastIndexof 18 equal 6 **");
	//	if (list1 != null) {
	//			System.out.println("** log 02 ** print list1 ** " + list1.toString() + " ");
	//			System.out.println(" ");
	//	}
		if (list1.lastIndexOf(18) != 6)
			System.err.println("TEST FAILED -- lastIndexOf 1");		
		
		int[] arr2 = {1, 18, 2, 7, 18, 18, 39, 18, 40, 18};
		LinkedIntList list2 = new LinkedIntList(arr2);
		
	//	System.out.println(" ");
	//	System.out.println("** log 03 ** check list2 wether or not lastIndexof 18 equal 9 **");
	//	if (list2 != null) {
	//		System.out.println("** log 03 ** print list2 ** " + list2.toString() + " ");
	//		System.out.println(" ");
	//	}
		if (list2.lastIndexOf(18) != 9)
			System.err.println("TEST FAILED -- lastIndexOf 2");
		
	//	System.out.println(" ");
	//	System.out.println("** log 04 ** check list2 wether or not lastIndexof 7 equal 3 **");
	//	if (list2 != null) {
	//		System.out.println("** log 04 ** print list2 ** " + list2.toString() + " ");
	//		System.out.println(" ");
	//	}
		if (list2.lastIndexOf(7) != 3)
			System.err.println("TEST FAILED -- lastIndexOf 3");		
		
	//	System.out.println(" ");
	//	System.out.println("** log 05 ** check list2 wether or not lastIndexof 36 equal -1 **");
	//	if (list2 != null) {
	//		System.out.print(list2.toString() + " ");
	//		System.out.println("** log 05 ** print list2 ** " + list2.toString() + " ");
	//		System.out.println(" ");
	//	}
		if (list2.lastIndexOf(36) != -1)
			System.err.println("TEST FAILED -- lastIndexOf 4");	
		
		
		// *** testing removeAll
		// ** list3
		int[] arr3 = {9, 4, 2, 3, 8, 17, 4, 18};
		LinkedIntList list3 = new LinkedIntList(arr3);
		
	//	if (list3 != null) {
	//		System.out.println(" ");
	//		System.out.println("** log 21 ** print list3 before rewmoved all 3 ** " + list3.toString() + " ");
	//		System.out.println(" ");
	//	}
		list3.removeAll(3);
		
	//	if (list3 != null) {
	//		System.out.println(" ");
	//		System.out.println("** log 22 ** print list3 after removed all 3 ** " + list3.toString() + " ");
	//		System.out.println(" ");
	//	}
	
		if (!list3.toString().equals("[9, 4, 2, 8, 17, 4, 18]"))
			System.err.println("TEST FAILED -- removeAll 1");
		
		
		// ** list4
		int[] arr4 = {9, 4, 2, 3, 3, 8, 17, 4, 18};
		LinkedIntList list4 = new LinkedIntList(arr4);
		
	//	if (list4 != null) {
	//		System.out.println(" ");
	//		System.out.println("** log 23 ** print list4 before removed all 3 ** " + list4.toString() + " ");
	//		System.out.println(" ");
	//	}
		
		list4.removeAll(3);
		
	//	if (list4 != null) {
	//		System.out.println(" ");
	//		System.out.println("** log 24 ** print list4 after removed all 3 ** " + list4.toString() + " ");
	//		System.out.println(" ");
	//	}
			
		if (!list4.toString().equals("[9, 4, 2, 8, 17, 4, 18]"))
			System.err.println("TEST FAILED -- removeAll 2");	
		
		

		// list5
		int[] arr5 = {3, 9, 4, 2, 3, 3, 8, 17, 4, 18};
		LinkedIntList list5 = new LinkedIntList(arr5);
		
	//	if (list5 != null) {
	//		System.out.println(" ");
	//		System.out.println("** log 25 ** print list5 before removed ** " + list5.toString() + " ");
	//		System.out.println(" ");
	//	}
		
		list5.removeAll(3);
		
	//	if (list5 != null) {
	//		System.out.println(" ");
	//		System.out.println("** log 26 ** print list5 after removed ** " + list5.toString() + " ");
	//		System.out.println(" ");
	//	}
		
		if (!list5.toString().equals("[9, 4, 2, 8, 17, 4, 18]"))
			System.err.println("TEST FAILED -- removeAll 3");				
		
		
		
		// list6
		int[] arr6 = {3, 9, 4, 2, 3, 3, 8, 17, 4, 18, 3};
		LinkedIntList list6 = new LinkedIntList(arr6);
		
	//	if (list6 != null) {
	//		System.out.println(" ");
	//		System.out.println("** log 27 ** print list6 before removed ** " + list6.toString() + " ");
	//		System.out.println(" ");
	//	}
		
		list6.removeAll(3);
		
	//	if (list6 != null) {
	//		System.out.println(" ");
	//		System.out.println("** log 28 ** print list6 after removed all 3 ** " + list6.toString() + " ");
	//		System.out.println(" ");
	//	}
		
		if (!list6.toString().equals("[9, 4, 2, 8, 17, 4, 18]"))
			System.err.println("TEST FAILED -- removeAll 4");
		
		System.out.println("Testing Done!!!");	
					
			

		// ********************* TESTS FOR ASSIGNMENT ****************************//
		/*
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
		
		if (!list14.toString().equals("[10, 42, 44, 86, 31, 23, 75]"))
			System.err.println("TEST FAILED -- shift 4");
		System.out.println("Testing Done!!!");							
		*/
		
	}
}
