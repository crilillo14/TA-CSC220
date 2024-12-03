package lab12;

public class PacmanTester {
	public static void main(String[] args) {
		String inputFileName = "mazes/bigMaze.txt";
		String outputFileName = "output/bigMazeDFS.txt";
		
		Pacman pacman = new Pacman(inputFileName, outputFileName);
		pacman.solveDFS();

		
	}
	

}
