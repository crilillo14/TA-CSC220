package lab12;

public class PacmanTester {
	
	public static void main(String[] args) {
		
		Pacman test1 = new Pacman("mediumMaze.txt", "outputtext.txt");
		System.out.println("INPUT:\n" + test1 + "\n");
		
		// NOTE: Choose which to uncomment
		
		test1.solveBFS();
		//test1.solveDFS();
		
		test1.writeOutput();
		
		// NOTE: Check outputtext.txt file to see result
		
	} // End of main method

} // End of tester class