package lab12;

public class PacmanTester {
    public static void main(String[] args) {
        // Test with one of the mazes in the "mazes/" directory
        String inputFile = "demoMaze.txt"; // Update with the actual path
        String outputFileBFS = "outputBFS.txt"; // This will hold the BFS result
        String outputFileDFS = "outputDFS.txt"; // This will hold the DFS result

        try {
            // Create Pacman instance with the input file and BFS output file
            Pacman pacmanBFS = new Pacman(inputFile, outputFileBFS);
            System.out.println("Starting BFS to solve the maze...");
            pacmanBFS.solveBFS();

            System.out.println("Maze solved using BFS. Output written to " + outputFileBFS);

            // Create Pacman instance with the input file and DFS output file
            Pacman pacmanDFS = new Pacman(inputFile, outputFileDFS);
            System.out.println("Starting DFS to solve the maze...");
            pacmanDFS.solveDFS(); // Solve using DFS
            System.out.println("Maze solved using DFS. Output written to " + outputFileDFS);

        } catch (Exception e) {
            System.out.println("An error occurred while processing the maze: " + e.getMessage());
            e.printStackTrace();
        }
    }
}







