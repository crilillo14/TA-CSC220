package lab12;

public class PacmanTester {
	public static void main(String[] args) {
	    // Test BFS
	    Pacman pacmanBFS = new Pacman("mazes/demoMaze.txt", "mazes/demoMazeBFSSol.txt");
	    pacmanBFS.solveBFS();

	    // Test DFS
	    Pacman pacmanDFS = new Pacman("mazes/demoMaze.txt", "mazes/demoMazeDFSSol.txt");
	    pacmanDFS.solveDFS();
	}
//        
//        String inputFileName = "mazes/classic.txt";  
//        
//        Pacman pacman = new Pacman(inputFileName);
//
//        // Write the output maze to a file
//        pacman.writeOutput();
//
//        // At this stage, the input and output files should be identical.
//        // You can use an online diff tool to verify if the files match.
//        System.out.println("Maze written to output file. Use a diff tool to verify the output.");
  
}