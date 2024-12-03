package lab12;

public class PacmanTester {

	public static void main(String[] args) {
		
		Pacman pacman = new Pacman("src/lab12/mazes/mediumMaze.txt", "straightSolved.txt");
		System.out.println(pacman);
		pacman.solveDFS();
	}
}
