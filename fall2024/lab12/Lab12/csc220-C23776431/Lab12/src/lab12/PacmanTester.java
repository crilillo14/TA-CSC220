package lab12;

public class PacmanTester {
	
	
	public static void main(String[] args) {
		
		//	writeOutput	//
		Pacman pac1 = new Pacman("tinyMaze.txt", "solutions/tinyMaze.txt");
		pac1.writeOutput();
		
		
		// BFS //
		pac1.solveBFS();
		pac1.writeOutput();
		
		Pacman pac2 = new Pacman("bigMaze.txt", "solutions/bigMaze.txt");
		pac2.solveBFS();
		pac2.writeOutput();
		
		// DFS //
		Pacman pac3 = new Pacman("demoMaze.txt", "solutions/demoMaze.txt");
		pac3.solveDFS();
		pac3.writeOutput();
		
		Pacman pac4 = new Pacman("turn.txt", "solutions/turn.txt");
		pac4.solveDFS();
		pac4.writeOutput();
	}
}
