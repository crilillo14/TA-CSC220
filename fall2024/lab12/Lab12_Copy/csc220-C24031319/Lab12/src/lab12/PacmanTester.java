package lab12;

import java.util.ArrayList;

public class PacmanTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ********Testing For Lab********
		Pacman maze1 = new Pacman("tinyMaze.txt", "tinyMaze_output.txt");
		
		System.out.println(maze1);
		
//		maze1.solveBFS();
//		maze1.writeOutput();
		
		maze1.solveDFS();
		maze1.writeOutput();
		
		Pacman maze2 = new Pacman("classic.txt", "classsic_output.txt");

		maze2.solveDFS();
		maze2.writeOutput();
		
		
		Pacman maze3 = new Pacman("unsolvable.txt", "unsolvable_output.txt");
		maze3.solveBFS();
		maze3.writeOutput();
	}

}
