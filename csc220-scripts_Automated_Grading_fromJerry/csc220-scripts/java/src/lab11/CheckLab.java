package lab11;

public class CheckLab {
	public static void main(String[] args){
		
		double []gradePoint = {0,100,0,0,0,0};
	    double []givenPoint = {7,10,12,12,12,12};
	    String output = "" ;
		try{
			
			int dIns = 6;
	    	int dHash = 2;
	    	int dRmv = 1;
	    	int dFin = 3;
			
	    	QuadraticProbingHashTable h1 = new QuadraticProbingHashTable(10);
	    	
	    	if (!h1.toString().equals("eF eF eF eF eF eF eF eF eF eF "))
	    		output+="TEST FAILED: constructor ( 0 )\n";
	    	else
	    		gradePoint[0]+=100.0;
	    	    	
	    	h1.insert(89);
	    	h1.insert(58);
	    	h1.insert(6);
	    	
	    	if (!h1.toString().equals("eF eF eF eF eF eF 6T eF 58T 89T "))
	    		output+="TEST FAILED: insert ( 1 )\n";
	    	else{
	    		gradePoint[2]+=100.0/dIns;
	    	}
	    	    	
	    	h1.insert(16);
	    	
	    	if (!h1.toString().equals("eF eF eF eF eF eF 6T 16T 58T 89T "))
	    			output+="TEST FAILED: insert ( 2 )\n";
	    	else{
	    		gradePoint[2]+=100.0/dIns;
	    	}
	    	
	    	h1.insert(9);
	    	if (!h1.toString().equals("9T eF eF eF eF eF 6T 16T 58T 89T "))
				output+="TEST FAILED: insert ( 3 )\n";
	    	else{
	    		gradePoint[2]+=100.0/dIns;
	    	}
	    	
	    	QuadraticProbingHashTable h2 = new QuadraticProbingHashTable(7);
	    	
	    	h2.insert(0);
	    	h2.insert(1);
	    	h2.insert(2);
	    	h2.insert(3);
	    	h2.insert(4);
	    	h2.insert(5);
	    	
	    	if (!h2.toString().equals("0T 1T 2T 3T 4T 5T eF eF eF eF eF eF eF eF "))
				output+="TEST FAILED: insert ( 4 ); incorrect rehash()\n"; 
	    	else{
	    		gradePoint[3]+=100.0/dHash;
	    	}
	    	
	    	
	    	
	    	// ********************* TESTS FOR ASSIGNMENT ****************************//
	    	
	    	QuadraticProbingHashTable h3 = new QuadraticProbingHashTable(11);
	    	
	    	if (!h3.toString().equals("eF eF eF eF eF eF eF eF eF eF eF "))
				output+="TEST FAILED: insert ( 5 )\n";
	    	else{
	    		gradePoint[2]+=100.0/dIns;
	    	}
	    	
	    	h3.insert(44);    	
	    	h3.insert(4);
	    	h3.remove(44);
	    	
	    	if (!h3.toString().equals("44F eF eF eF 4T eF eF eF eF eF eF "))
				output+="TEST FAILED: remove ( 6 )\n";
	    	else{
	    		gradePoint[4]+=100.0/dRmv;
	    	}
	    	
	    	h3.insert(77);
	    	
	    	if (!h3.toString().equals("77T eF eF eF 4T eF eF eF eF eF eF "))
				output+="TEST FAILED: insert ( 7 )\n";
	    	else{
	    		gradePoint[2]+=100.0/dIns;
	    	}
	    	
	    	h3.insert(16);    	
	    	h3.insert(28);
	    	h3.insert(21);    	
	    	h3.insert(11);    	
	    	h3.insert(22);
	    	h3.insert(33);  
	    	
	    	if (!h3.toString().equals("77T 11T eF 33T 4T 16T 28T eF eF 22T 21T "))
				output+="TEST FAILED: insert ( 8 )\n";
	    	else{
	    		gradePoint[2]+=100.0/dIns;
	    	}

	    	h3.insert(55);
	    	
	    	if (!h3.toString().equals("22T eF eF eF 4T eF 28T eF eF eF eF 77T 11T eF eF 33T 16T eF eF eF 55T 21T "))
				output+="TEST FAILED: insert ( 9 ); rehash () incorrect.\n";
	    	else{
	    		gradePoint[3]+=100.0/dHash;
	    	}
	    	
	    	if (h3.find(4) != 4)
	    		output+="TEST FAILED: find ( 10 )\n";
	    	else{
	    		gradePoint[5]+=100.0/dFin;
	    	}
	    	
	    	if (h3.find(44) != -1)
	    		output+="TEST FAILED: find ( 11 )\n";
	    	else{
	    		gradePoint[5]+=100.0/dFin;
	    	}
	    	
	    	if (h3.find(77) != 11)
	    		output+="TEST FAILED: find ( 12 )\n";
	    	else{
	    		gradePoint[5]+=100.0/dFin;
	    	}
	
		}catch(Exception ex){
			int endLen = ex.toString().length()<50?ex.toString().length():50;
			output+="program threw " + ex + "\n";
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