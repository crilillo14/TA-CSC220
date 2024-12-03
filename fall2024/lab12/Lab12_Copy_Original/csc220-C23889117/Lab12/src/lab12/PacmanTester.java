package lab12;
import java.io.IOException;

public class PacmanTester {
	 public static void main(String[] args) {
	        String inputFile = "src/bigMaze.txt";
	        String bfsOutputFile = "bigMazeBFSSol.txt";
	        String dfsOutputFile = "bigMazeDFSSol.txt";

	        try {
	            // Test BFS
	            System.out.println("Testing BFS on bigMaze:");
	            Pacman pacmanBFS = new Pacman(inputFile, bfsOutputFile);
	            pacmanBFS.solveBFS();
	            System.out.println("BFS solution written to " + bfsOutputFile);
	            System.out.println("BFS Solution Maze:");
	            System.out.println(pacmanBFS);

	            // Test DFS
	            System.out.println("\nTesting DFS on bigMaze:");
	            Pacman pacmanDFS = new Pacman(inputFile, dfsOutputFile);
	            pacmanDFS.solveDFS();
	            System.out.println("DFS solution written to " + dfsOutputFile);
	            System.out.println("DFS Solution Maze:");
	            System.out.println(pacmanDFS);

	        } catch (Exception e) {
	            System.out.println("An error occurred during testing: " + e.getMessage());
	        }
	    }
}
