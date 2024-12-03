package lab12;

public class PacmanTester {
	
    public static void main(String[] args) {
        // Test Case 1: BFS
        System.out.println("Running BFS Test...");
        Pacman bfsSolver = new Pacman("mazes/tinyMaze.txt", "mazes/tinyMazeBFSSol.txt");
        bfsSolver.solveBFS();
        bfsSolver.writeOutput();
        System.out.println("BFS Solution written to tinyMazeBFSSol.");

        // Test Case 2: DFS
        System.out.println("Running DFS Test...");
        Pacman dfsSolver = new Pacman("mazes/tinyMaze.txt", "mazes/tinyMazeDFSSol.txt");
        dfsSolver.solveDFS();
        dfsSolver.writeOutput();
        System.out.println("DFS Solution written to tinyMazeDFSSol.");

        // Test Case 3: Unsolvable Maze
        System.out.println("Running Unsolvable Maze Test...");
        Pacman unsolvableSolver = new Pacman("mazes/unsolvable.txt", "mazes/unsolvableSol.txt");
        unsolvableSolver.solveBFS();
        unsolvableSolver.writeOutput();
        System.out.println("Unsolvable Maze Solution written to unsolvableSol.");
    }

}
