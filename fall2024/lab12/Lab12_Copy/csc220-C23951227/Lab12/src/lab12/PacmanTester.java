package lab12;

public class PacmanTester {
	public static void main(String[] args) {
		Pacman p1 = new Pacman("mazes/tinyMaze.txt", "mazes/tinyMazeTest.txt");
		System.out.println(p1);
		p1.solveBFS();
		System.out.println(p1);
		p1.writeOutput();
		
		Pacman p2 = new Pacman("mazes/classic.txt", "mazes/classicTest.txt");
		System.out.println(p2);
		p2.solveDFS();
		System.out.println(p2);
		p2.writeOutput();
		
		
		Pacman p3 = new Pacman("mazes/randomMaze.txt", "mazes/randomMazeTest.txt");
		System.out.println(p3);
		p3.solveDFS();
		System.out.println(p3);
		p3.solveBFS();
		System.out.println(p3);
		p3.writeOutput();
		
		Pacman p4 = new Pacman("mazes/unsolvable.txt", "mazes/unsolvableTest.txt");
		System.out.println(p4);
		p4.solveDFS();
		System.out.println(p4);
		p4.solveBFS();
		System.out.println(p4);
		p4.writeOutput();
	}

}
