package lab12;

public class Tester {
	public static void main (String[] args) {
		Pacman p1 = new Pacman("tinyMaze.txt" , "tinyMaze_output.txt");
		System.out.println(p1);			
		p1.writeOutput();
		
		 Pacman p2 = new Pacman("bigMaze.txt", "bigMazeouputBFS.txt");
	     p2.solveBFS();
	     System.out.println(p2);
	     
	     Pacman p3 = new Pacman("bigMaze.txt", "bigMazeouputDFS.txt");
	     p3.solveDFS();
	     System.out.println(p3);

	}
}
