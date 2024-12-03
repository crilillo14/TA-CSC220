package lab12;

public class PacmanTester {
	public static void main (String[] args) {

			String inputFileName = "mazes/demoMaze.txt";
			String outputFileName = "mazes/outputs/demoMazeOutput.txt";
		
			Pacman pacman = new Pacman (inputFileName, outputFileName);
		
			pacman.solveBFS();
			pacman.writeOutput(); 
	
	}
}
