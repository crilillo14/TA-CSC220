package lab12;

public class PacmanTester {

	public static void main(String[] args) {

		Pacman p1 = new Pacman("mazes/demoMaze.txt", "out.txt");
		
		p1.solveDFS();
		
		p1.writeOutput();
		
		System.out.println(p1);
	}

}
