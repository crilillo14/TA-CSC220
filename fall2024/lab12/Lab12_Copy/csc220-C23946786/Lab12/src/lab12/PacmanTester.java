package lab12;

public class PacmanTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Pacman pac1 = new Pacman("src/mazes/bigMaze.txt", "src/MyResults/bigMazeBFSSol.txt");
		pac1.solveBFS();
		pac1.writeOutput();
		pac1 = new Pacman("src/mazes/bigMaze.txt", "src/MyResults/bigMazeDFSSol.txt");
		pac1.solveDFS();
		pac1.writeOutput();
		
		pac1 = new Pacman("src/mazes/classic.txt", "src/MyResults/classicBFSSol.txt");
		pac1.solveBFS();
		pac1.writeOutput();
		pac1 = new Pacman("src/mazes/classic.txt", "src/MyResults/classicDFSSol.txt");
		pac1.solveDFS();
		pac1.writeOutput();
		
		pac1 = new Pacman("src/mazes/demoMaze.txt", "src/MyResults/demoMazeBFSSol.txt");
		pac1.solveBFS();
		pac1.writeOutput();
		pac1 = new Pacman("src/mazes/demoMaze.txt", "src/MyResults/demoMazeDFSSol.txt");
		pac1.solveDFS();
		pac1.writeOutput();
		
		pac1 = new Pacman("src/mazes/mediumMaze.txt", "src/MyResults/mediumMazeBFSSol.txt");
		pac1.solveBFS();
		pac1.writeOutput();
		pac1 = new Pacman("src/mazes/mediumMaze.txt", "src/MyResults/mediumMazeDFSSol.txt");
		pac1.solveDFS();
		pac1.writeOutput();
		
		pac1 = new Pacman("src/mazes/randomMaze.txt", "src/MyResults/randomMazeBFSSol.txt");
		pac1.solveBFS();
		pac1.writeOutput();
		pac1 = new Pacman("src/mazes/randomMaze.txt", "src/MyResults/randomMazeDFSSol.txt");
		pac1.solveDFS();
		pac1.writeOutput();
		
		pac1 = new Pacman("src/mazes/straight.txt", "src/MyResults/straightBFSSol.txt");
		pac1.solveBFS();
		pac1.writeOutput();
		pac1 = new Pacman("src/mazes/straight.txt", "src/MyResults/straightDFSSol.txt");
		pac1.solveDFS();
		pac1.writeOutput();
		
		pac1 = new Pacman("src/mazes/tinyMaze.txt", "src/MyResults/tinyMazeBFSSol.txt");
		pac1.solveBFS();
		pac1.writeOutput();
		pac1 = new Pacman("src/mazes/tinyMaze.txt", "src/MyResults/tinyMazeDFSSol.txt");
		pac1.solveDFS();
		pac1.writeOutput();
		
		pac1 = new Pacman("src/mazes/tinyOpen.txt", "src/MyResults/tinyOpenBFSSol.txt");
		pac1.solveBFS();
		pac1.writeOutput();
		pac1 = new Pacman("src/mazes/tinyOpen.txt", "src/MyResults/tinyOpenDFSSol.txt");
		pac1.solveDFS();
		pac1.writeOutput();
		
		pac1 = new Pacman("src/mazes/turn.txt", "src/MyResults/turnBFSSol.txt");
		pac1.solveBFS();
		pac1.writeOutput();
		pac1 = new Pacman("src/mazes/turn.txt", "src/MyResults/turnDFSSol.txt");
		pac1.solveDFS();
		pac1.writeOutput();
		
		pac1 = new Pacman("src/mazes/unsolvable.txt", "src/MyResults/unsolvableBFSSol.txt");
		pac1.solveBFS();
		pac1.writeOutput();
		pac1 = new Pacman("src/mazes/unsolvable.txt", "src/MyResults/unsolvableDFSSol.txt");
		pac1.solveDFS();
		pac1.writeOutput();

	}

}