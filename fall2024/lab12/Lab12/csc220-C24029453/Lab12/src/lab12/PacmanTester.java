package lab12;

public class PacmanTester {
	public static void main(String[] args) {
//		String inputFileName = "mazes/tinyMaze.txt";
//		String outputFileName = "mazes/tinyMazeOutput.txt"; 
//
//        Pacman pacman = new Pacman(inputFileName, outputFileName);
//
//        pacman.writeOutput(); 
//        
//
//        System.out.println("Maze written to " + outputFileName);
        
        String inputFileName = "mazes/tinyMaze.txt";
        String bfsOutputFileName = "mazes/tinyMazeBFSSol.txt";
        String dfsOutputFileName = "mazes/tinyMazeDFSSol.txt";

        Pacman pacmanBFS = new Pacman(inputFileName, bfsOutputFileName);
        pacmanBFS.solveBFS();
        pacmanBFS.writeOutput();
 
        Pacman pacmanDFS = new Pacman(inputFileName, dfsOutputFileName);
        pacmanDFS.solveDFS();
        pacmanDFS.writeOutput();

        System.out.println("BFS and DFS solutions written to files.");
    }
}
