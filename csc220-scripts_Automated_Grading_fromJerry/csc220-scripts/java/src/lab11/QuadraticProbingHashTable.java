package lab11;


public class QuadraticProbingHashTable
{
	
    public HashEntry [ ] HashTable;   // The array that holds the hash table
    public int currentSize;       // The number of occupied cells	

	// constructor to create the HashTable of initial size = size
    // and sets all of its elements to null.
    public QuadraticProbingHashTable( int size )
    {
    	// FILL IN
    	HashTable = new HashEntry[ size ];
        currentSize = 0;
        for( int i = 0; i < HashTable.length; i++ )
        	HashTable[ i ] = null;    	
    }
 
    // insert into the hash table
    // if the item is already present, do nothing and simply return
    // be careful you might need to rehash - reshape when the load factor is .75
    // Hint: first check the load factor after add - if the size is beyond rehash first!
    public void insert( int x )
    {
    	// FILL IN
    	if ((double)(currentSize+1)/HashTable.length > .75)
    		rehash();

    	int currentPos = findPos( x );
        if( isActive( currentPos ) )
            return;

        HashTable[currentPos] = new HashEntry( x, true );
        
        currentSize++;
        

    }

    // this function will increase the size of the hash table by a factor of two
    // and do the rehash of the current elements inside the hash table
    public void rehash( )
    {
    	// FILL IN
        HashEntry [ ] oldArray = HashTable;

         // Create a new double-sized, empty table
        HashTable = new HashEntry[ 2 * oldArray.length ];
        currentSize = 0;

        // Copy table over
        for( int i = 0; i < oldArray.length; i++ )
            if( oldArray[ i ] != null && oldArray[ i ].isActive )
                insert( oldArray[ i ].element );

        return;
    }
    
    // a simple hash function for int values
    // the hash value should be a number between 0 and tableSize-1
    // use the mod operator as suggested in class
    public int hash(int value, int tableSize )
    {
    	// FILL IN
        if( value < 0 )
            return -value % tableSize;
        else
            return value % tableSize;
    }  
    
    // helper function for insert
    public int findPos( int x )
    {
    	int collisionNum = 0;
    	int currentPos =hash(x, HashTable.length );
    	int initialPos = currentPos;

    	while (HashTable[currentPos] != null && (HashTable[currentPos].isActive && HashTable[currentPos].element != x)){
    		currentPos  = (initialPos + (collisionNum * collisionNum++)) % HashTable.length;
    	}
    	return currentPos;
    } 

    // this function will remove an element from the hash table
    // remember you are not going to remove the element from the hash table (physcially)
    // instead you are supposed to make it inactive
    public void remove( int x )
    {
    	// FILL IN
        int currentPos = findPos( x );
        if( isActive( currentPos ) )
        	HashTable[ currentPos ].isActive = false;
        currentSize--;
    }

    // this function finds an element in the hash table
    // x is the value we are looking for
    // This function returns the index in which the value resides
    // if not in the hashtable return -1
    public int find( int x )
    {
    	// FILL IN
        int currentPos = findPos( x );
        return isActive( currentPos ) ? currentPos : -1;
    }

    public boolean isActive( int currentPos )
    {
        return HashTable[ currentPos ] != null && HashTable[ currentPos ].isActive;
    }
    
    
    // DO NOT CHNAGE THIS FUNCTION!
    public String toString(){
    	String toReturn = "";
    	for (int i = 0; i < HashTable.length; i++){
    		if (HashTable[i] == null){
    				toReturn += ("eF ");
    		}else{
    			if (HashTable[i].isActive)
    				toReturn += (HashTable[i].element + "T ");
    			else
    				toReturn += (HashTable[i].element + "F ");
    		}
    	}
    	return toReturn;
    }
    
    
    public static void main(String[] args){
    	
    	
    	// ********************* TESTS FOR LAB ****************************//
    	
    	QuadraticProbingHashTable h1 = new QuadraticProbingHashTable(10);
    	
    	if (!h1.toString().equals("eF eF eF eF eF eF eF eF eF eF "))
    		System.err.print("TEST FAILED: constructor ( 0 )");
    	    	
    	h1.insert(89);
    	h1.insert(58);
    	h1.insert(6);
    	
    	if (!h1.toString().equals("eF eF eF eF eF eF 6T eF 58T 89T "))
    		System.err.println("TEST FAILED: insert ( 1 )");
    	    	
    	h1.insert(16);
    	
    	if (!h1.toString().equals("eF eF eF eF eF eF 6T 16T 58T 89T "))
    			System.err.println("TEST FAILED: insert ( 2 )");
    	
    	h1.insert(9);
    	if (!h1.toString().equals("9T eF eF eF eF eF 6T 16T 58T 89T "))
			System.err.println("TEST FAILED: insert ( 3 )");   
    	
    	QuadraticProbingHashTable h2 = new QuadraticProbingHashTable(7);
    	
    	h2.insert(0);
    	h2.insert(1);
    	h2.insert(2);
    	h2.insert(3);
    	h2.insert(4);
    	h2.insert(5);
    	
    	if (!h2.toString().equals("0T 1T 2T 3T 4T 5T eF eF eF eF eF eF eF eF "))
			System.err.println("TEST FAILED: insert ( 4 )"); 
    	
    	System.out.println("Lab Testing Done!!!");
    	
    	
    }

}