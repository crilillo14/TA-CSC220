package lab01;

public class SumExperiment {
	
	public static int check_sum(int[] array){
		// This function will inspect the input to find any pair of values that add up to 20
		// if it find such a pair, it will return the *index* of the smallest value
		// if it does not find such as pair, it will return -1;
		int i, j; 
		i = 0;
		j = array.length - 1; 

		while (i < j) {
			int sum = array[i] + array[j];
			if (sum == 20) {
				return Math.min(i, j); 
			} else if (sum < 20) {
				i++; 
			} else {
				j--; 
			}

		} 

		// remove the following line after you are done writing the function
		return -1;
	}
	
	public static void main(String[] args) {
		int[] array1 = new int[]{5, 7, 8, 9, 10, 15, 16};
		if (check_sum(array1) != 0)
			System.err.println("TEST1 FAILED");
		
		int[] array2 = new int[]{3, 5, 8, 9, 10, 15, 16};
		if (check_sum(array2) != 1)
			System.err.println("TEST2 FAILED");

		
		int[] array3 = new int[]{3, 4, 6, 9, 10, 14, 15};
		if (check_sum(array3) != 2)
			System.err.println("TEST3 FAILED");
		
		int[] array4 = new int[]{6, 7, 8, 9, 10, 15, 16};
		if (check_sum(array4) != -1)
			System.err.println("TEST4 FAILED");
		
		System.out.println("Done!!!");
	}
}