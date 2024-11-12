package lab08;

public class CheckLab {
	
	public static void main(String[] args){
		

		// ********************* TESTS FOR LAB ****************************//
		
		double []gradePoint = {0,0,0,0};
	    double []givenPoint = {16,16,16,16};
	    String output = "";
		try{
			
					
			// *** testing lastIndexof	
			try {
				int[] arr1 = {1, 18, 2, 7, 18, 39, 18, 40};
				LinkedIntList list1 = new LinkedIntList(arr1);
				
				if (list1.lastIndexOf(18) != 6)
					output+="TEST FAILED -- lastIndexOf() when 18 is before the last value\n";	
				else
					gradePoint[0]+=100.0/5.0;
			} catch (Exception e) {
				output +="TEST FAILED -- lastIndexOf() threw " + e +"\n";
			}
				
				int[] arr2 = {1, 18, 2, 7, 18, 18, 39, 18, 40, 18};
				LinkedIntList list2 = new LinkedIntList(arr2);
				
			try {	
				if (list2.lastIndexOf(18) != 9)
					output+="TEST FAILED -- lastIndexOf() when 18 is last value\n";
				else
					gradePoint[0]+=100.0/5.0;
			} catch (Exception e) {
				output +="TEST FAILED -- lastIndexOf() threw " + e +"\n";
			}
			
			try {
				if (list2.lastIndexOf(7) != 3)
					output+="TEST FAILED -- lastIndexOf() when 7 is the only value present in list\n";
				else
					gradePoint[0]+=100.0/5.0;
			} catch (Exception e) {
				output +="TEST FAILED -- lastIndexOf() threw " + e +"\n";
			}
			
			try {	
				if (list2.lastIndexOf(36) != -1)
					output+="TEST FAILED -- lastIndexOf() when 36 doesn't belongs to the list\n";
				else
					gradePoint[0]+=100.0/5.0;
			} catch (Exception e) {
				output +="TEST FAILED -- lastIndexOf() threw " + e +"\n";
			}
				
			try {
				LinkedIntList list = new LinkedIntList();
					
				if (list.lastIndexOf(3) != -1)
					output+="TEST FAILED -- lastIndexOf() with empty list\n";
				else
					gradePoint[0]+=100.0/5.0;
			} catch (Exception e) {
				output +="TEST FAILED -- lastIndexOf() threw " + e +"\n";
			}
				
				
			// remove all testing
			try {	
				
				int[] arr3 = {9, 4, 2, 3, 8, 17, 4, 18};
				LinkedIntList list3 = new LinkedIntList(arr3);
				list3.removeAll(3);
				
				if (!list3.toString().equals("[9, 4, 2, 8, 17, 4, 18]"))
					output+="TEST FAILED -- removeAll() when tried to remove only one 3.\n";
				else
					gradePoint[1]+=100.0/4.0;
				
			} catch (Exception e) {
				output +="TEST FAILED -- removeAll() threw " + e +"\n";
			}
			
			try {
				
				int[] arr4 = {9, 4, 2, 3, 3, 8, 17, 4, 18};
				LinkedIntList list4 = new LinkedIntList(arr4);
				list4.removeAll(3);
				
				if (!list4.toString().equals("[9, 4, 2, 8, 17, 4, 18]"))
					output+="TEST FAILED -- removeAll() when tried remove 2 consecutive 3's.\n";
				else
					gradePoint[1]+=100.0/4.0;
				
			} catch (Exception e) {
				output +="TEST FAILED -- removeAll() threw " + e +"\n";
			}
			
			try {
		
				int[] arr5 = {3, 9, 4, 2, 3, 3, 8, 17, 4, 18};
				LinkedIntList list5 = new LinkedIntList(arr5);
				list5.removeAll(3);
				
				if (!list5.toString().equals("[9, 4, 2, 8, 17, 4, 18]"))
					output+="TEST FAILED -- removeAll() when 3 is present in the first location and middle.\n";
				else
					gradePoint[1]+=100.0/4.0;
				
			} catch (Exception e) {
				output +="TEST FAILED -- removeAll() threw " + e +"\n";
			}
			
			try {
				
				int[] arr6 = {3, 9, 4, 2, 3, 3, 8, 17, 4, 18, 3};
				LinkedIntList list6 = new LinkedIntList(arr6);
				list6.removeAll(3);
				
				if (!list6.toString().equals("[9, 4, 2, 8, 17, 4, 18]"))
					output+="TEST FAILED -- removeAll() when 3 is in first, last, and in middle locations.\n";
				else
					gradePoint[1]+=100.0/4.0;
				
			} catch (Exception e) {
				output +="TEST FAILED -- removeAll() threw " + e +"\n";
			}
				
				
			// ********************* TESTS FOR ASSIGNMENT ****************************//
			
			// *** testing stutter
				
			try {	
				int[] arr8 = {1};
				LinkedIntList list8 = new LinkedIntList(arr8);
				list8.stutter();
		
				if (!list8.toString().equals("[1, 1]"))
					output+="TEST FAILED -- stutter() when only 1 value present before the call.\n";
				else
					gradePoint[2]+=100.0/4.0;
			} catch (Exception e) {
				output +="TEST FAILED -- stutter() threw " + e +"\n";
			}
			
			try {
				int[] arr9 = {1, 8};
				LinkedIntList list9 = new LinkedIntList(arr9);
				list9.stutter();
		
				if (!list9.toString().equals("[1, 1, 8, 8]"))
					output+="TEST FAILED -- stutter() when 2 values present before the call\n";
				else
					gradePoint[2]+=100.0/4.0;
			} catch (Exception e) {
				output +="TEST FAILED -- stutter() threw " + e +"\n";
			}
				
			
			try {
				int[] arr10 = {1, 8, 19, 4, 17};
				LinkedIntList list10 = new LinkedIntList(arr10);
				list10.stutter();
		
				if (!list10.toString().equals("[1, 1, 8, 8, 19, 19, 4, 4, 17, 17]"))
					output+="TEST FAILED -- stutter() when 5 values present before the call\n";
				else
					gradePoint[2]+=100.0/4.0;
			} catch (Exception e) {
				output +="TEST FAILED -- stutter() threw " + e +"\n";
			}
				
			try {
				int[] arr7 = {};
				LinkedIntList list7 = new LinkedIntList(arr7);
				list7.stutter();
		
				if (!list7.toString().equals("[]"))
					output+="TEST FAILED -- stutter() with empty list\n";
				else
					gradePoint[2]+=100.0/4.0;
			} catch (Exception e) {
				output +="TEST FAILED -- stutter() threw " + e +"\n";
			}
				
				
			// *** testing shift
						
			try {	
				int[] arr12 = {1, 2, 3};
				LinkedIntList list12 = new LinkedIntList(arr12);
				list12.shift();
				
				if (!list12.toString().equals("[1, 3, 2]"))
					output+="TEST FAILED -- shift() with 3 elements in the list\n";
				else
					gradePoint[3]+=100.0/4.0;
			} catch (Exception e) {
				output +="TEST FAILED -- shift() threw " + e +"\n";
			}
			
			try {
				int[] arr13 = {1,2};
				LinkedIntList list13 = new LinkedIntList(arr13);
				list13.shift();
				
				if (!list13.toString().equals("[1, 2]"))
					output+="TEST FAILED -- shift() with 2 elements in the list\n";
				else
					gradePoint[3]+=100.0/4.0;
			} catch (Exception e) {
				output +="TEST FAILED -- shift() threw " + e +"\n";
			}
			
			
			try {
				int[] arr14 = {10, 31, 42, 23, 44, 75, 86};
				LinkedIntList list14 = new LinkedIntList(arr14);
				list14.shift();
				
				if (!list14.toString().equals("[10, 42, 44, 86, 31, 23, 75]"))
					output+="TEST FAILED -- shift() with 7 elements in the list\n";
				else
					gradePoint[3]+=100.0/4.0;
				
			} catch (Exception e) {
				output +="TEST FAILED -- shift() threw " + e +"\n";
			}
				
			try {	
				int[] arr11 = {};
				LinkedIntList list11 = new LinkedIntList(arr11);
				list11.shift();
				
				if (!list11.toString().equals("[]"))
					output+="TEST FAILED -- shift() when list is empty\n";	
				else
					gradePoint[3]+=100.0/4.0;
			} catch (Exception e) {
				output +="TEST FAILED -- shift() threw " + e +"\n";
			}
		
		}catch(Exception ex){
			int endLen = ex.toString().length()<50?ex.toString().length():50;
			output+="## program threw " + ex.toString().substring(0, endLen) + "\n";
		}
		
		arrayTestPrint(gradePoint,givenPoint);
	    System.out.println(output);
	}
	
	public static void arrayTestPrint(double []arr,double []givenpoint){
		  System.out.print("$$");
		  int i=0;
		  for(double r: arr){
			  //System.out.println(givenpoint[i]);
			  System.out.print(Math.round((r*givenpoint[i])/100.0));
			  System.out.print("$$");
			  i++;
		  }
	  }
}