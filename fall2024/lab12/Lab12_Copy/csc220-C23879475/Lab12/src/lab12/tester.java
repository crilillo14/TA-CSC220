package lab12;

public class tester {
	
	public static void main(String[] args) {
		
		Pacman maze1 = new Pacman("mazes/tinymaze.txt", "tinyMaze_output.txt");
		

//		maze1.writeOutput();
		maze1.solveDFS(); // same graphs
		maze1.solveBFS(); // same graphs
		
	}

}
