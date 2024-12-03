package lab12;


public class PacmanTester {
    public static void main(String[] args) {
        String[] mazeFiles = {"demoMaze.txt", "tinyMaze.txt"};
        String baseDir = "/Users/joseardila/Downloads/Lab12-Assignment12/mazes/"; // Base directory for maze files

        for (String file : mazeFiles) {
            String inputFilePath = baseDir + file;
            String bfsOutputFilePath = baseDir + file.replace(".txt", "BFSSol.txt");
            String dfsOutputFilePath = baseDir + file.replace(".txt", "DFSSol.txt");

            // Test BFS
            Pacman pacmanBFS = new Pacman(inputFilePath, bfsOutputFilePath);
            pacmanBFS.solveBFS();

            // Test DFS
            Pacman pacmanDFS = new Pacman(inputFilePath, dfsOutputFilePath);
            pacmanDFS.solveDFS();
        }
    }
}