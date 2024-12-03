package lab12.code;

import java.io.File;

public class PacmanTester {

    public static void main(String[] args) {
        // Print the current working directory for debugging
        System.out.println("Current working directory: " + System.getProperty("user.dir"));

        // Use File.separator to ensure compatibility across operating systems
        String separator = File.separator;

        // Adjust the file paths relative to the working directory
        String inputFileName = "mazes" + separator + "randomMaze.txt";
        String bfsOutputFileName = "mazes" + separator + "randomMazeBFSSol.txt";
        String dfsOutputFileName = "mazes" + separator + "randomMazeDFSSol.txt";

        // Create Pacman object for BFS solution
        System.out.println("Solving maze using BFS...");
        Pacman pacmanBFS = new Pacman(inputFileName, bfsOutputFileName);
        pacmanBFS.solveBFS(); // Solve the maze using BFS

        // Create a new Pacman object for DFS solution
        System.out.println("Solving maze using DFS...");
        Pacman pacmanDFS = new Pacman(inputFileName, dfsOutputFileName);
        pacmanDFS.solveDFS(); // Solve the maze using DFS

        // Optional: Print the results to the console (if needed)
        System.out.println("Maze after BFS solution:");
        System.out.println(pacmanBFS.toString());
        System.out.println("Maze after DFS solution:");
        System.out.println(pacmanDFS.toString());

        // Confirmation of file output
        System.out.println("Solutions have been written to the output files:");
        System.out.println("BFS: " + bfsOutputFileName);
        System.out.println("DFS: " + dfsOutputFileName);
    }
}
