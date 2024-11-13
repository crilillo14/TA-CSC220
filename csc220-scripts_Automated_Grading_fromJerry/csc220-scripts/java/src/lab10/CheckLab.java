package lab10;

public class CheckLab {
	public static void main(String[] args){
		
		double []gradePoint = {0,0,0,0,0};
	    double []givenPoint = {9,14,14,14,14};
	    String output = "" ;
		try{
			
			double const2=4, insert=11, remove=3, sort=3;
			MaxHeap maxHeap = new MaxHeap(15);
			
			try{
			if(maxHeap.heap.length == 15){
				gradePoint[0] = 100;
			}else{
				output+="TEST FAILED: constructor(int) failed to create heap array\n";
			}
			}catch(Exception ex){}
	        
	        int[] arr1 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	        if (!maxHeap.IsEqual(arr1))
	        	output+="TEST FAILED: insert 0\n";
	        else
	        	gradePoint[1] += 100/insert;
	        
	        maxHeap.insert(5);
	        
	        int[] arr2 = {5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	        if (!maxHeap.IsEqual(arr2))
	        	output+="TEST FAILED: insert 1\n";
	        else
	        	gradePoint[1] += 100/insert;
	            
	        maxHeap.insert(3);
	        
	        int[] arr3 = {5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	        if (!maxHeap.IsEqual(arr3))
	        	output+="TEST FAILED: insert 2\n";
	        else
	        	gradePoint[1] += 100/insert;
	                
	        maxHeap.insert(17);
	        
	        int[] arr4 = {17, 3, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	        if (!maxHeap.IsEqual(arr4))
	        	output+="TEST FAILED: insert 3\n";
	        else
	        	gradePoint[1] += 100/insert;
	                
	        maxHeap.insert(10);
	        int[] arr5 = {17, 10, 5, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	        if (!maxHeap.IsEqual(arr5))
	        	output+="TEST FAILED: insert 4\n";
	        else
	        	gradePoint[1] += 100/insert;
	        
	        maxHeap.insert(84);
	        int[] arr6 = {84, 17, 5, 3, 10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	        if (!maxHeap.IsEqual(arr6))
	        	output+="TEST FAILED: insert 5\n";
	        else
	        	gradePoint[1] += 100/insert;
	      
	        maxHeap.insert(19);
	        int[] arr7 = {84, 17, 19, 3, 10, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	        if (!maxHeap.IsEqual(arr7))
	        	output+="TEST FAILED: insert 6\n";
	        else
	        	gradePoint[1] += 100/insert;
	                
	        maxHeap.insert(6);
	        int[] arr8 = {84, 17, 19, 3, 10, 5, 6, 0, 0, 0, 0, 0, 0, 0, 0};
	        if (!maxHeap.IsEqual(arr8))
	        	output+="TEST FAILED: insert 7\n";
	        else
	        	gradePoint[1] += 100/insert;
	                
	        maxHeap.insert(22);
	        int[] arr9 = {84, 22, 19, 17, 10, 5, 6, 3, 0, 0, 0, 0, 0, 0, 0};
	        if (!maxHeap.IsEqual(arr9))
	        	output+="TEST FAILED: insert 8\n";
	        else
	        	gradePoint[1] += 100/insert;
	                
	        maxHeap.insert(9);
	        int[] arr10 = {84, 22, 19, 17, 10, 5, 6, 3, 9, 0, 0, 0, 0, 0, 0};
	        if (!maxHeap.IsEqual(arr10))
	        	output+="TEST FAILED: insert 9\n";
	        else
	        	gradePoint[1] += 100/insert;
	        
	        MaxHeap maxHeap1 = new MaxHeap(15);
	        
	        maxHeap1.insert(97);
	        maxHeap1.insert(93);
	        maxHeap1.insert(87);
	        maxHeap1.insert(90);
	        maxHeap1.insert(89);
	        maxHeap1.insert(83);    
	        
	        int[] arr11 = {97, 93, 87, 90, 89, 83, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	        if (!maxHeap1.IsEqual(arr11))
	        	output+="TEST FAILED: insert 10\n";
	        else
	        	gradePoint[1] += 100/insert;
	        
	        // remove tests        
	        int maxVal1 = maxHeap1.removemax();
	        
	        int[] arr12 = {93, 90, 87, 83, 89, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	        if (maxVal1 != 97 || !maxHeap1.IsEqual(arr12))
	        	output+="TEST FAILED: remove 1\n";
	        else
	        	gradePoint[2] += 100/remove;
	                
	        int maxVal2 = maxHeap1.removemax();
	        
	        int[] arr13 = {90, 89, 87, 83, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	        if (maxVal2 != 93 || !maxHeap1.IsEqual(arr13))
	        	output+="TEST FAILED: remove 2\n";
	        else
	        	gradePoint[2] += 100/remove;
	        
	        MaxHeap maxHeap2 = new MaxHeap(15);
	        if (maxHeap2.removemax() != -1)
	        	output+="TEST FAILED: remove 0\n"; 
	        else
	        	gradePoint[2] += 100/remove;
	        
	        // write tests for sort
	        int[] arr15 = {3};
	        int[] arr14 = {};
	        int[] arr16 = {3, 22};//{22, 3};
	        int[] arr16_2 = {3, 22};
	        int[] arr16_2_1 = {22, 3};
	        int[] arr17_sort = {3, 5, 6, 9, 10, 17, 19, 22, 84};//{84, 22, 19, 17, 10, 9, 6, 5, 3}; //
	        int[] arr17 = {84, 22, 19, 17, 10, 5, 6, 3, 9};
	         int[] arr17_2 = {5, 3, 17, 10, 84, 19, 6, 22, 9};
	        MaxHeap maxHeap3 = new MaxHeap(arr14);
	        
	        if (!maxHeap3.IsEqual(arr14)){
	        	output+="TEST FAILED: constructor 0\n";
	        }
	        else{
	        	gradePoint[3] += 100/const2;
	        	//gradePoint[4] += 100/sort;
	        }
	        
        	MaxHeap maxHeap7 = new MaxHeap(0);
	        maxHeap7.heap = arr15;
	        try { 
	        	maxHeap7.sort();
		        if (!maxHeap7.IsEqual(arr15))
		        	output+="TEST FAILED: sort 1\n";
		        else{
		        	gradePoint[4] += 100/sort;
		        }
	        } catch (Exception e) {
	        	output+="TEST FAILED: sort 1 threw " + e + "\n";
	        }

	        
	        MaxHeap maxHeap4 = new MaxHeap(arr15);
	        if (!maxHeap4.IsEqual(arr15)){
	        	output+="TEST FAILED: constructor 1\n";
	        }
	        else{
	        	gradePoint[3] += 100/const2;
	        }
	        
	        
	        MaxHeap maxHeap5 = new MaxHeap(arr16_2);
	        if (!maxHeap5.IsEqual(arr16_2_1)){
	        	output+="TEST FAILED: constructor 2\n";
	        }
	        else{
	        	gradePoint[3] += 100/const2;
	        	//gradePoint[4] += 100/sort;
	        }
	        
	        MaxHeap maxHeap8 = new MaxHeap(2);
	        maxHeap8.heap = arr16_2_1;
	        maxHeap8.size = arr16_2_1.length - 1;
	        try { 
		        maxHeap8.sort();
		        if (!maxHeap8.IsEqual(arr16_2))
		        	output+="TEST FAILED: sort 2\n";
		        else{
		        	gradePoint[4] += 100/sort;
		        }
	        } catch (Exception e) {
	        	output+="TEST FAILED: sort 2 threw " + e + "\n";
	        }
	                
	       
	        MaxHeap maxHeap6 = new MaxHeap(arr17_2);
	        if (!maxHeap6.IsEqual(arr17)){
	        	output+="TEST FAILED: constructor 3\n";
	        }
	        else{
	        	gradePoint[3] += 100/const2;
	        	//gradePoint[4] += 100/sort;
	        }
	        
        	MaxHeap maxHeap9 = new MaxHeap(9);
	        maxHeap9.heap = arr17_2;
	        maxHeap9.size = arr17_2.length - 1;
	        try { 
		        maxHeap9.sort();
		        if (!maxHeap9.IsEqual(arr17_sort))
		        	output+="TEST FAILED: sort 3\n";
		        else{
		        	gradePoint[4] += 100/sort;
		        }
	        } catch (Exception e) {
	        	output+="TEST FAILED: sort 3 threw " + e + "\n";
	        }
	        
		}catch(Exception ex){
			int endLen = ex.toString().length()<50?ex.toString().length():50;
			output+="## program threw "+ex.toString().substring(0, endLen) + "\n";
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