package lab12;

public class pacmanTester {

	public static void main(String[] args) {
		Pacman p1 = new Pacman("bigMaze.txt", "bigMaze_output.txt");
		p1.solveBFS();
		p1.writeOutput();
		System.out.println(p1);
		


	}
}
