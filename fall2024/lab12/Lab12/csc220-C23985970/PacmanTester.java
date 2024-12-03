package lab12;

public class PacmanTester {
	public static void main(String[]args) {
//		Pacman p1 = new Pacman("tinyMaze.txt", "tinyMaze_output.txt");
//		System.out.println(p1);
//		p1.writeOutput();

		
		Pacman p2 = new Pacman("mediumMaze.txt", "BFS_output.txt");
		Pacman p3 = new Pacman("mediumMaze.txt", "DFS_output.txt");
		

		p2.solveBFS();
		System.out.println("Solved Maze BFS:");
		
		p2.writeOutput();
	   
		
		
		
		p3.solveDFS();
	   System.out.println("Solved Maze DFS:");
	   p3.writeOutput();
	  


        
	}

}
