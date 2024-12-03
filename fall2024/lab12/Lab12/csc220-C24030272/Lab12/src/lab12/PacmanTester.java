package lab12;

public class PacmanTester
{
	public static void main(String args[])
	{
		Pacman p1 = new Pacman("tinyMaze.txt", "tinyMaze_Output.txt" );	//constructer works
		System.out.println("Unsolved Maze:");
		System.out.println(p1.toString());
		p1.solveDFS();
		System.out.println("Solved Maze:");
		System.out.println(p1.toString()); //DFS solver works, files match
		
		Pacman p2 = new Pacman("bigMaze.txt", "bigMaze_Output.txt");
		System.out.println("Unsolved Maze:");
		System.out.println(p2.toString());
		p2.solveBFS();
		System.out.println("Solved Maze:");
		System.out.println(p2.toString()); //BFS solver works, files match
	
		Pacman p3 = new Pacman("classic.txt", "classic_Output.txt");
		System.out.println("Unsolved Maze:");
		System.out.println(p3.toString());
		p3.solveDFS();
		System.out.println("Solved Maze:");
		System.out.println(p3.toString()); //DFS solver works, files match

		Pacman p4 = new Pacman("mediumMaze.txt", "mediumMaze_Output.txt");
		System.out.println("Unsolved Maze:");
		System.out.println(p4.toString());
		p4.solveBFS();
		System.out.println("Solved Maze:");
		System.out.println(p4.toString()); //BFS solver works, files match

		Pacman p5 = new Pacman("randomMaze.txt", "randomMaze_Output.txt");
		System.out.println("Unsolved Maze:");
		System.out.println(p5.toString());
		p5.solveDFS();
		System.out.println("Solved Maze:");
		System.out.println(p5.toString()); //DFS solver works, files match

		Pacman p6 = new Pacman("tinyOpen.txt", "tinyOpen_Output.txt");
		System.out.println("Unsolved Maze:");
		System.out.println(p6.toString());
		p6.solveBFS();
		System.out.println("Solved Maze:");
		System.out.println(p6.toString()); //BFS solver works, files match

		Pacman p7 = new Pacman("turn.txt", "turn_Output.txt");
		System.out.println("Unsolved Maze:");
		System.out.println(p7.toString());
		p7.solveDFS();
		System.out.println("Solved Maze:");
		System.out.println(p7.toString()); //DFS solver works, files match

		Pacman p8 = new Pacman("unsolvable.txt", "unsolvable_Output.txt");
		System.out.println("Unsolved Maze:");
		System.out.println(p8.toString());
		p8.solveBFS();
		System.out.println("Solved Maze:");
		System.out.println(p8.toString()); //BFS solver works- states that maze is unsolvable and prints unsolved maze

		Pacman p9 = new Pacman("straight.txt", "straight_Output.txt");
		System.out.println("Unsolved Maze:");
		System.out.println(p9.toString());
		p9.solveBFS();
		System.out.println("Solved Maze:");
		System.out.println(p9.toString()); //BFS solver works, files match
		

	}
}
