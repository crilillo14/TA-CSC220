package lab12;

public class PacmanTester {
	public static void main(String[] args) {
		String inputFolder = "mazes/";
		String outputFolder = "solutions/";
		
		String[] testFiles = {
			"bigMaze.txt",
			"classic.txt",
			"demoMaze.txt",
			"mediumMaze.txt"
			
		};
		
		for (String testFile : testFiles) {
            String inputFileName = inputFolder + testFile;
            String outputFileNameBFS = outputFolder + "solved_BFS_" + testFile;
            String outputFileNameDFS = outputFolder + "solved_DFS_" + testFile;
            
            System.out.println("Running tests for: " + testFile);

            // Create a Pacman object
            Pacman pacman = new Pacman(inputFileName, outputFileNameBFS);

            // Test the initial output (should match input)
            pacman.writeOutput();
            System.out.println("Initial maze output written to: " + outputFileNameBFS);

            // Test BFS solution
            pacman.solveBFS();
            pacman.writeOutput();
            System.out.println("BFS solution written to: " + outputFileNameBFS);

            // Recreate the Pacman object for DFS (to reset the maze state)
            pacman = new Pacman(inputFileName, outputFileNameDFS);

            // Test DFS solution
            pacman.solveDFS();
            pacman.writeOutput();
            System.out.println("DFS solution written to: " + outputFileNameDFS);   
	}
		
		System.out.println("All tests completed.");

}
}
