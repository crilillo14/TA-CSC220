package lab12;

public class PacmanTester {

    public static void main(String[] args) {
        String inputFileName = "mazes/bigMaze.txt"; // Change this to your desired maze file
        String bfsOutputFileName = "mazes/bigMaze_BFSSol.txt";
        String dfsOutputFileName = "mazes/bigMaze_DFSSol.txt";

        // BFS Solution
        Pacman pacmanBFS = new Pacman(inputFileName, bfsOutputFileName);
        pacmanBFS.solveBFS();
        System.out.println("BFS Solution:");
        System.out.println(pacmanBFS);

        // DFS Solution
        Pacman pacmanDFS = new Pacman(inputFileName, dfsOutputFileName);
        pacmanDFS.solveDFS();
        System.out.println("DFS Solution:");
        System.out.println(pacmanDFS);
    }
}
