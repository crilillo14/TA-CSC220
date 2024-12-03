package lab12;

public class PacmanTester 
{
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		Pacman p1 = new Pacman("mazes/tinyMaze.txt", "tinyMaze_output.txt");

	    System.out.println("Og Maze:");
	    System.out.println(p1 + "\n");

	    // //create file
	    // p1.writeOutput();

	    //test BFS
	    p1.solveBFS();
	    p1.writeOutput();

	    Pacman p2 = new Pacman("mazes/tinyOpen.txt", "tinyOpen_output.txt");
	    System.out.println("Og Maze:");
	    System.out.println(p2 + "\n");

	    // //create file
	    // p2.writeOutput();

	    //test DFS
	    p2.solveDFS();
	    p2.writeOutput();
	}
	
}
