/*
 * Here is a starting point for your Matrix tester. You will have to fill in the rest of "main" with
 * more code to sufficiently test your Matrix class. We will be using our own MatrixTester for grading. 
*/
package lab02;

public class CheckLab {
	public static void main(String[] args) {

		MatrixRef MR1 = new MatrixRef(new int[][] { { 7, 2, 3 }, { 4, 9, 6 } });

		MatrixRef MRR1 = new MatrixRef(new int[][] { { 3, 2, 11 }, { 5, 22, 11 } });

		MatrixRef MR2 = new MatrixRef(new int[][] { { 7, 8, 6 }, { 11, -3, 12 } });

		MatrixRef MR3 = new MatrixRef(new int[][] { { 7, 8 }, { 9, 10 }, { 11, 12 } });

		MatrixRef MR4 = new MatrixRef(new int[][] { { 7, 8 }, { 9, 10 }, { 11, 12 }, { 110, 120 } });
		
		MatrixRef MR1Transpose = MR1.transpose();
		MatrixRef MR4Transpose = MR4.transpose();

		Matrix M1 = new Matrix(MR1.data);
		Matrix MM1 = new Matrix(MRR1.data);

		Matrix M2 = new Matrix(MR2.data);

		Matrix M3 = new Matrix(MR3.data);

		Matrix M4 = new Matrix(MR4.data);

		/*
		 * Note that none of the tests below will be correct until you have
		 * implemented all methods. This is just one example of a test, you must
		 * write more tests and cover all cases.
		 */

		MatrixRef RFAdd = MR1.plus(MR2);
		MatrixRef RFMulti = MR1.times(MR3);

		// checking equals
		String checkingResult = "$$";
		try {
			Matrix MAdd_equals = new Matrix(MR4.data);
			if (M4.equals(MAdd_equals) && !M1.equals(MM1)) {
				checkingResult += "1$$";
			} else {
				checkingResult += "0$$";
			}
		} catch (Exception ex) {
			checkingResult += "2$$";
		}

		try {
			Matrix MAdd_equals = new Matrix(MR3.data);
			if (M4.equals(MAdd_equals)) {
				checkingResult += "0$$";
			} else {
				checkingResult += "1$$";
			}
		} catch (Exception ex) {
			checkingResult += "2$$";
		}

		// checking addition
		try {
			Matrix MAdd_val = M1.plus(M2);
			MatrixRef MRAdd_Val = new MatrixRef(MAdd_val.data);
			if (MRAdd_Val.equals(RFAdd)) {
				checkingResult += "1$$";
			} else {
				checkingResult += "0$$";
			}
		} catch (Exception ex) {
			checkingResult += "2$$";
		}

		try {
			Matrix MAdd_val = M1.plus(M4);
			if (MAdd_val == null) {
				checkingResult += "1$$";
			} else {
				checkingResult += "0$$";
			}
		} catch (Exception ex) {
			checkingResult += "2$$";
		}

		// checking times
		try {
			Matrix MAdd_val = M1.times(M3);
			MatrixRef MRAdd_Val = new MatrixRef(MAdd_val.data);
			if (MRAdd_Val.equals(RFMulti)) {
				checkingResult += "1$$";
			} else {
				checkingResult += "0$$";
			}
		} catch (Exception ex) {
			checkingResult += "2$$";
		}

		try {
			Matrix MAdd_val = M1.times(M4);
			if (MAdd_val == null) {
				checkingResult += "1$$";
			} else {
				checkingResult += "0$$";
			}
		} catch (Exception ex) {
			checkingResult += "2$$";
		}
		
		// test transpose 
		
		try {
			Matrix M1Transpose = M1.transpose();
			MatrixRef M1TransposeToRef = new MatrixRef(M1Transpose.data);
			if (M1TransposeToRef.equals(MR1Transpose)) {
				checkingResult += "1$$";
			} else {
				checkingResult += "0$$";
			}
			
		} catch (Exception ex) {
			checkingResult += "2$$";
		}
		
		try {
			Matrix M4Transpose = M4.transpose();
			MatrixRef M4TransposeToRef = new MatrixRef(M4Transpose.data);
			if (M4TransposeToRef.equals(MR4Transpose)) {
				checkingResult += "1$$";
			} else {
				checkingResult += "0$$";
			}
			
		} catch (Exception ex) {
			checkingResult += "2$$";
		}

		// print value
		System.out.println(checkingResult);

	}
}