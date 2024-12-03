package lab12;

public class Tester {

	public static void main(String[] args) {

		Pacman p1 = new Pacman("mazes/tinyMaze.txt", "output/tinyMazeBFS_output.txt");
		p1.solveBFS();
		System.out.println(p1);

		Pacman p2 = new Pacman("mazes/tinyMaze.txt", "output/tinyMazeDFS_output.txt");
		p2.solveDFS();
		System.out.println(p2);

		Pacman p3 = new Pacman("mazes/bigMaze.txt", "output/bigMazeBFS_output.txt");
		p3.solveBFS();
		System.out.println(p3);

		Pacman p4 = new Pacman("mazes/bigMaze.txt", "output/bigMazeDFS_output.txt");
		p4.solveDFS();
		System.out.println(p4);

		Pacman p5 = new Pacman("mazes/unsolvable.txt", "output/unsolvableBFS_output.txt");
		p5.solveBFS();
		System.out.println(p5);

		Pacman p6 = new Pacman("mazes/unsolvable.txt", "output/unsolvableDFS_output.txt");
		p6.solveDFS();
		System.out.println(p6);

		System.out.println("Testing done!");

	}

}
