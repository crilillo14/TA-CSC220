package lab01;

public class CheckLab {

	public static void main(String[] args) {
		double []gradePoint = {0,0,0,0,0,0};
	    double []givenPoint = {5, 5, 12.5, 12.5, 12.5, 12.5};
	    int totalValue = 0;
	    String output  = "";
		for(int i=0;i<10000;i++){
			totalValue += CoinFlipExperiment.coinFlipExperiment();
		}
		if(totalValue > 0){
			gradePoint[0] += 100;
		}else{
			output += "CoinFlipExperiment is incorrect.\n";
		}
		try{
			int[] array1 = new int[]{5, 7, 8, 9, 10, 15, 16};
			int answer = SumExperiment.check_sum(array1);
			if (answer == 0){
				gradePoint[2] += 100;
			}else{
				output += "Test 1 FAILED: sum is 0; your answer " + answer + "\n";
			}
			
			int[] array2 = new int[]{3, 5, 8, 9, 10, 15, 16};
			int answer2 = SumExperiment.check_sum(array2);
			if (answer2 == 1){
				gradePoint[3] = 100;
			}else{
				output += "Test 2 FAILED: sum is 1; your answer " + answer2 + "\n";
			}
			
			
			int[] array3 = new int[]{3, 4, 6, 9, 10, 14, 15};
			int answer3 = SumExperiment.check_sum(array3);
			if (answer3 == 2){
				gradePoint[4] = 100;
			}else{
				output += "Test 3 FAILED: sum is 2; your answer " + answer3 + "\n";
			}
			
			int[] array4 = new int[]{6, 7, 8, 9, 10, 15, 16};
			int answer4 = SumExperiment.check_sum(array4);
			if (answer4 == -1){
				gradePoint[5] = 100;
			}else{
				output += "Test 4 FAILED: sum is -1; your answer " + answer4 + "\n";
			}
			
			gradePoint[1] = 100;
		}catch(Exception ex){
			output += "Your check_sum threw an exception!\n";
		}
		
		arrayPrint(gradePoint, givenPoint);
		System.out.println(output);
	}
		
		  public static void arrayPrint(double []arr, double []givenpoint){
			  System.out.print("$$");
			  int i=0;
			  for(double r: arr){
				  //System.out.println(givenpoint[i]);
				  System.out.print((r*givenpoint[i])/100.0);
				  System.out.print("$$");
				  i++;
			  }
			  System.out.println(); //delete if causes problems 
		  }
}
