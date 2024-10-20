package lab09;

public class CheckLab {
	public static void main(String[] args){
		
		double []gradePoint = {0,0,0,0};
	    double []givenPoint = {16,16,16,16};
	    String output = "";
		try{
		
			// Example trees
			// Use PrintSideways to get a sense of the tree structures (and consult the instruction)
			IntTree empty_tree = new IntTree(0);
			IntTree tree1 = new IntTree(1);
			IntTree tree2 = new IntTree(2);
			IntTree tree3 = new IntTree(3);
			IntTree tree4 = new IntTree(4);
			IntTree tree6 = new IntTree(6);
			
			
			int[] arr1 = {3, 5, 2, 1, -1, 4, 6};
			IntTree tree_ref1 = new IntTree(arr1);
			
			//System.out.println(tree_ref1.getLevel(4));
			int[] arr2 = {2, 8, 1, 0, -1, 7, 6, -1, -1, -1, -1, 4, -1, -1, 9};
			IntTree tree_ref2 = new IntTree(arr2);
			//tree_ref2.printSideways();
			// ********************* TESTS FOR LAB ****************************//
			
			// *** countEmpty() tests
			double divby = 8.0;
			
			try {
				int n1 = tree1.countEmpty();
				if (n1 != 2)
					output+="TEST FAILED: countEmpty() with tree1\n";
				else
					gradePoint[0]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: countEmpty() threw " + e + "\n";
			}
			
			try {
				int n2 = tree2.countEmpty();
				if (n2 != 3)
					output+="TEST FAILED: countEmpty() with tree2\n";
				else
					gradePoint[0]+=100.0/divby;	
			} catch (Exception e) {
				output+="TEST FAILED: countEmpty() threw " + e + "\n";
			}
		
			try {
				int n3 = tree3.countEmpty();
				if (n3 != 4)
					output+="TEST FAILED: countEmpty() with tree3\n";
				else
					gradePoint[0]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: countEmpty() threw " + e + "\n";
			}
			
			try {
				int n4 = tree4.countEmpty();
				if (n4 != 5)
					output+="TEST FAILED: countEmpty() with tree4\n";
				else
					gradePoint[0]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: countEmpty() threw " + e + "\n";
			}
		
			try {
				int n6 = tree6.countEmpty();
				if (n6 != 7)
					output+="TEST FAILED: countEmpty() with tree6\n";
				else
					gradePoint[0]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: countEmpty() threw " + e + "\n";
			}
		
			try {
				int n_ref1 = tree_ref1.countEmpty();
				if (n_ref1 != 7)
					output+="TEST FAILED: countEmpty() with tree_ref1\n";
				else
					gradePoint[0]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: countEmpty() threw " + e + "\n";
			}
			
			try {
				int n_ref2 = tree_ref2.countEmpty();
				if (n_ref2 != 9)
					output+="TEST FAILED: countEmpty() with tree_ref2\n";
				else
					gradePoint[0]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: countEmpty() threw " + e + "\n";
			}
				
			try {
				int n0 = empty_tree.countEmpty();
				if (n0 != 1)
					output+="TEST FAILED: countEmpty() with empty tree\n";
				else
					gradePoint[0]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: countEmpty() threw " + e + "\n";
			}
			
			// *** printLevel tests
			divby = 6;
			
			try {
				if (!tree_ref2.getLevel(1).equals("2\n"))
					output+="TEST FAILED: getLevel() with tree_ref2\n";
				else
					gradePoint[1]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: getLevel() threw " + e + "\n";
			}
			
			try {
				if (!tree_ref2.getLevel(2).equals("8\n1\n"))
					output+="TEST FAILED: getLevel() with tree_ref2\n";		
				else
					gradePoint[1]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: getLevel() threw " + e + "\n";
			}
				
			try {
				if (!tree_ref2.getLevel(3).equals("0\n7\n6\n"))
					output+="TEST FAILED: getLevel() with tree_ref2\n";
				else
					gradePoint[1]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: getLevel() threw " + e + "\n";
			}
			
			try {
				if (!tree_ref2.getLevel(4).equals("4\n9\n"))
					output+="TEST FAILED: getLevel() with tree_ref2\n";
				else
					gradePoint[1]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: getLevel() threw " + e + "\n";
			}
			
			try {
				if (!tree_ref2.getLevel(5).equals(""))
					output+="TEST FAILED: getLevel() with tree_ref2\n";
				else
					gradePoint[1]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: getLevel() threw " + e + "\n";
			}
			
			try {
				if (!empty_tree.getLevel(1).equals(""))
					output+="TEST FAILED: getLevel() with empty tree\n";
				else
					gradePoint[1]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: getLevel() threw " + e + "\n";
			}
			
			
			// ********************* TESTS FOR ASSIGNMENT ****************************//
			
			// *** toString() tests
			divby =6;
			
			try {	
				if (!tree3.toString().equals("(1, 2, 3)"))
					output+="TEST FAILED: toString() with tree3\n";
				else
					gradePoint[2]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: toString() threw " + e + "\n";
			}
			
			try {
				if (!tree4.toString().equals("(1, (2, 4, empty), 3)"))
					output+="TEST FAILED: toString() with tree4\n";
				else
					gradePoint[2]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: toString() threw " + e + "\n";
			}
			
			try {
				if (!tree6.toString().equals("(1, (2, 4, 5), (3, 6, empty))"))
					output+="TEST FAILED: toString() with tree6\n";
				else
					gradePoint[2]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: toString() threw " + e + "\n";
			}
				
			try {
				if (!tree_ref2.toString().equals("(2, (8, 0, empty), (1, (7, 4, empty), (6, empty, 9)))"))
					output+="TEST FAILED: toString() with tree_ref2\n";
				else
					gradePoint[2]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: toString() threw " + e + "\n";
			}
			
			try {
				if (!empty_tree.toString().equals(""))
					output+="TEST FAILED: toString() with empty tree\n";
				else
					gradePoint[2]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: toString() threw " + e + "\n";
			}
			
			try {
				if (!tree2.toString().equals("(1, 2, empty)"))
					output+="TEST FAILED: toString() with tree2\n";
				else
					gradePoint[2]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: toString() threw " + e + "\n";
			}
			
				
			// *** makePerfect() tests
			divby = 8;
			try {
				tree2.makePerfect();
				if (!tree2.getInorder().equals(" 2  1  0 "))
					output+="TEST FAILED: makePerfect() with tree2\n";
				else
					gradePoint[3]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: makePerfect() threw " + e + "\n";
			}
		
			try {
				tree3.makePerfect();
				if (!tree3.getInorder().equals(" 2  1  3 "))
					output+="TEST FAILED: makePerfect() with tree3\n";		
				else
					gradePoint[3]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: makePerfect() threw " + e + "\n";
			}
				
			try {
				tree4.makePerfect();
				if (!tree4.getInorder().equals(" 4  2  0  1  0  3  0 "))
					output+="TEST FAILED: makePerfect() with tree4\n";				
				else
					gradePoint[3]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: makePerfect() threw " + e + "\n";
			}
				
			try {
				tree6.makePerfect();
				if (!tree6.getInorder().equals(" 4  2  5  1  6  3  0 "))
					output+="TEST FAILED: makePerfect() with tree6\n";						
				else
					gradePoint[3]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: makePerfect() threw " + e + "\n";
			}
	
			try {
				tree_ref1.makePerfect();
				if (!tree_ref1.getInorder().equals(" 1  5  0  3  4  2  6 "))
					output+="TEST FAILED: makePerfect() with tree_ref1\n";						
				else
					gradePoint[3]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: makePerfect() threw " + e + "\n";
			}
	
			try {
				tree_ref2.makePerfect();
				if (!tree_ref2.getInorder().equals(" 0  0  0  8  0  0  0  2  4  7  0  1  0  6  9 "))
					output+="TEST FAILED: makePerfect() with tree_ref2\n";
				else
					gradePoint[3]+=100.0/divby;
			} catch (Exception e) {
				output+="TEST FAILED: makePerfect() threw " + e + "\n";
			}
	
			
		    try {    
				empty_tree.makePerfect();
				if (!empty_tree.getInorder().equals(""))
					output+="TEST FAILED: makePerfect() with empty tree\n";
				else
					gradePoint[3]+=100.0/divby;
		    } catch (Exception e) {
		    	output+="TEST FAILED: makePerfect() threw " + e + "\n";
		    }
	
		    try {
				tree1.makePerfect();
				if (!tree1.getInorder().equals(" 1 "))
					output+="TEST FAILED: makePerfect() with tree1\n";
				else
					gradePoint[3]+=100.0/divby;
		    } catch (Exception e) {
		    	output+="TEST FAILED: makePerfect() threw " + e + "\n";
		    }

	
		}catch(Exception ex){
			int endLen = ex.toString().length()<50?ex.toString().length():50;
			output+="## program threw " + ex.toString().substring(0, endLen) + "\n";
		}
		
		arrayTestPrint(gradePoint,givenPoint);
	    System.out.println(output);
	}
	
	public static void arrayTestPrint(double []arr,double []givenpoint){
		  System.out.print("$$" ) ;
		  int i=0;
		  for(double r: arr){
			  //System.out.println(givenpoint[i]);
			  System.out.print(Math.round((r*givenpoint[i])/100.0));
			  System.out.print("$$" ) ;
			  i++;
		  }
	  }
}