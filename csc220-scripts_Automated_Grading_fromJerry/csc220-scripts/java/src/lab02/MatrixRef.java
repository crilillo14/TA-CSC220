package lab02;

public class MatrixRef {
	int numRows;
	int numColumns;
	int data[][];
	
	// default constructor
	public MatrixRef(){}
	
	// constructor 1 - Constructor for new zero matrix
	public MatrixRef(int rowDim, int colDim){
		/*
		* TODO: write a constructor that would create a matrix
		* of correct size and initialize it to 0. 
		*/
		numRows = rowDim;
		numColumns = colDim;
		data =new int[numRows][numColumns];
		
		for (int row = 0; row < numRows; row++){
			for (int col = 0; col < numColumns; col++){
				data[row][col] = 0;
			}
		}
	}
	
	
	// constructor 2 - Constructor with data for new matrix (automatically determines dimensions)
	public MatrixRef(int d[][])
	{
		/* 1) put the numRows to be the number of 1D arrays in the 2D array
		*  2) specify the numColumns and set it
		*  3) be careful of special cases you are supposed to handle them properly
		*  4) create a new matrix to hold the data
		*  5) copy the data over
		*/
	
		numRows = d.length; // d.length is the number of 1D arrays in the 2D array
		if(numRows == 0)
			numColumns = 0;
		else
			numColumns = d[0].length; // d[0] is the first 1D array
		data = new int[numRows][numColumns]; // create a new matrix to hold the data
		// copy the data over
		for(int i=0; i < numRows; i++) 
			for(int j=0; j < numColumns; j++)
				data[i][j] = d[i][j];
	}	
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public String toString()
	{
		
		/*
		 * TODO: replace the below return statement with the correct code, you must return a String that represents this 
		 * 			matrix, as specified in the instruction for M1 - anything else IS NOT acceptable
		 */
		//return ""; // placeholder		
		
		String s = "\n";
		for (int row = 0; row < numRows; row++){
			for (int col = 0; col < numColumns; col++){
				s += (data[row][col] + " ");
			}
			s += "\n";
		}
		return s; 
	}
	
	// *** you will implement the rest of the methods for your assignment
	// *** don't touch them before finishing the lab
	
	@Override // instruct the compiler that we do indeed intend for this method to override the superclass' (Object) version
	public boolean equals(Object o)
	{
		if(!(o instanceof MatrixRef)) // make sure the Object we're comparing to is a Matrix
			return false;
		MatrixRef m = (MatrixRef)o; // if the above was not true, we know it's safe to treat 'o' as a Matrix
		
		/*
		 * TODO: replace the below return statement with the correct code, you must return the correct value
		 * 			after determining if this matrix is equal to the input matrix
		 */
		//return false; // placeholder
		if (!(this.numRows == m.numRows && this.numColumns == m.numColumns)){
			return false;
		}
		
		for (int row = 0; row < m.numRows; row++){
			for (int col = 0; col < m.numColumns; col++){
				if (data[row][col] != m.data[row][col])
					return false;
			}
		}
		return true;
	}

	public MatrixRef times(MatrixRef m)
	{
		/*
		 * TODO: replace the below return statement with the correct code, 
		 *  This function must check if the two matrices are compatible for multiplication, if not, return null.
		 *  If they are compatible, determine the dimensions of the resulting matrix, and fill it in with
		 *  the correct values for matrix multiplication
		 */
		//return null; // placeholder
		
		if (numColumns != m.numRows)
			return null;
		
		MatrixRef toReturn = new MatrixRef(numRows, m.numColumns);
		
		for (int row = 0; row < numRows; row++){
			for (int col = 0; col < m.numColumns; col++){
				for (int k = 0; k < numColumns; k++)
					toReturn.data[row][col] += data[row][k]*m.data[k][col];
			}
		}
		return toReturn;
	}
	
	public MatrixRef plus(MatrixRef m)
	{
		/*
		 * TODO: replace the below return statement with the correct code, 
		 *  This function must check if the two matrices are compatible for addition, if not, return null.
		 *  If they are compatible, create a new matrix and fill it in with
		 *  the correct values for matrix addition
		 */
		//return null; // placeholder
		if (!(this.numRows == m.numRows && this.numColumns == m.numColumns)){
			return null;
		}
		
		MatrixRef toReturn = new MatrixRef(m.numRows, m.numColumns);
		
		for (int row = 0; row < m.numRows; row++){
			for (int col = 0; col < m.numColumns; col++){
				toReturn.data[row][col] = data[row][col] + m.data[row][col];
			}
		}
		return toReturn;
	}
	
	public MatrixRef transpose()
    {
        /*
         * TODO: replace the below return statement with the correct code,
         */
		 int temp[][] = new int[numColumns][numRows]; 

	        for (int i = 0; i < numColumns; i++) {
	        	for (int j = 0; j < numRows; j++) {
	        		temp[i][j] = data[j][i]; 
	        	}
	        }

	     return new MatrixRef(temp); 
    }
	
}
