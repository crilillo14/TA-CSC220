package lab12;

public class PacmanTester {
    public static void main(String[] args) {
        String inputFileName = "bigMaze.txt";
        String bfsOutputFileName = "bigMazeBFSSol.txt";
        String dfsOutputFileName = "bigMazeDFSSol.txt";

        Pacman pacmanBFS = new Pacman(inputFileName, bfsOutputFileName);
        pacmanBFS.solveBFS();

        Pacman pacmanDFS = new Pacman(inputFileName, dfsOutputFileName);
        pacmanDFS.solveDFS();

        System.out.println("BFS and DFS solutions written to files!");
    }
}
    