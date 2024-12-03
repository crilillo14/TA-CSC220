package lab12;

public class PacManTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// testing output writing
		Pacman p1 = new Pacman("tinyMaze.txt", "tinyMaze_output.txt");
		//System.out.println(p1);
		//p1.writeOutput();
		
		System.out.println("Beginning Maze: ");
		System.out.println(p1);
		
		System.out.println("Solved Maze BFS:");
		p1.solveBFS();
		System.out.println(p1);
		
		Pacman p1_1 = new Pacman("tinyMaze.txt", "tinyMaze_output2.txt");
		System.out.println("Solved Maze DFS:");
		p1_1.solveDFS();
		System.out.println(p1_1);
		
		
		
		
		
		Pacman p2 = new Pacman("bigMaze.txt", "bigMaze_output.txt");
		System.out.println("Beginning Maze: ");
		System.out.println(p2);
		
		System.out.println("Solved Maze BFS:");
		p2.solveBFS();
		System.out.println(p2);
		
		
		Pacman p2_2 = new Pacman("bigMaze.txt", "bigMaze_outputDFS.txt");
		System.out.println("Solved Maze BFS:");
		p2_2.solveBFS();
		System.out.println(p2_2);
		
		
		Pacman rand1 = new Pacman("randomMaze.txt", "rand_DFSout.txt");
		Pacman rand2 = new Pacman("randomMaze.txt", "rand_BFSout.txt");
		rand1.solveDFS();
		rand2.solveBFS();
		
		
		Pacman turnt1 = new Pacman("turn.txt", "turn_BFSout.txt");
		Pacman turnt2 = new Pacman("turn.txt", "turn_DFS.txt");
		turnt1.solveBFS();
		turnt2.solveDFS();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

}
