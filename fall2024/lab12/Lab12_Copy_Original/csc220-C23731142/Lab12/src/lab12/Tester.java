package lab12;

public class Tester {

	public static void main(String[] args) {
		Pacman maze1 = new Pacman("/Users/gahmlielartison/Downloads/Lab12-Assignment12/mazes/bigMaze.txt", "out1.txt");
		System.out.println(maze1);
		maze1.solveDFS();
		
		Pacman maze2 = new Pacman("/Users/gahmlielartison/Downloads/Lab12-Assignment12/mazes/bigMazeBFSSol.txt", "out2.txt");
		System.out.println(maze2);
		maze2.solveDFS();
		
		Pacman maze3 = new Pacman("/Users/gahmlielartison/Downloads/Lab12-Assignment12/mazes/bigMazeDFSSol.txt", "out3.txt");
		System.out.println(maze3);
		maze3.solveDFS();
		
		Pacman maze4 = new Pacman("mazes/straight.txt", "out4.txt");
		System.out.println(maze4);
		maze4.solveDFS();
		
		Pacman maze5 = new Pacman("mazes/randomMaze.txt", "out5.txt");
		System.out.println(maze5);
		maze5.solveDFS();
		
		Pacman maze6 = new Pacman("mazes/mediumMaze.txt", "out6.txt");
		System.out.println(maze6);
		maze6.solveDFS();
		
		Pacman maze7 = new Pacman("mazes/demoMaze.txt", "out7.txt");
		System.out.println(maze7);
		maze7.solveDFS();
		
		Pacman maze8 = new Pacman("mazes/classic.txt", "out8.txt");
		System.out.println(maze8);
		maze8.solveDFS();
		
		Pacman maze9 = new Pacman("mazes/bigMaze.txt", "out9.txt");
		System.out.println(maze9);
		maze9.solveDFS();
		
		Pacman unsolvable = new Pacman("mazes/unsolvable.txt", "outUnsolvable.txt");
		System.out.println(unsolvable);
		unsolvable.solveDFS();
	}
}
