package lab12;

public class PacmanTester {
    public static void main(String[] args) {
        // Create a Pacman object with input and output file names
        // Use the relative path for the files, since they are in src/lab12/lab12.mazes
        Pacman pacman = new Pacman("src/lab12/mazes/demoMaze.txt", "src/lab12/mazes/demooutput.txt");
        
        // Solve the maze using BFS
        System.out.println("Solving the maze using BFS...");
        pacman.solveBFS();

        // Solve the maze using DFS
        System.out.println("Solving the maze using DFS...");
        pacman.solveDFS();
    }
}
