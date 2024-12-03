package lab12;

public class PacmanTester {
	public static void main(String[] args) {
		System.out.println("Hello world!");
		Pacman maze1 = new Pacman("tinyMaze.txt", "tinyMaze_Output.txt");
		maze1.writeOutput();
		//maze1.solveBFS();
	}

}
