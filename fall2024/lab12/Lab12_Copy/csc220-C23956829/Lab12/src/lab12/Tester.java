package lab12;

public class Tester {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
	// Lab Tests
	Pacman p1 = new Pacman("tinyMaze.txt", "tinyMaze_output.txt");
	System.out.println(p1);
	p1.writeOutput();
	
	Pacman p2 = new Pacman("tinyOpen.txt", "tinyOpen_output.txt");
	System.out.println(p2);
	p2.writeOutput();
	
	Pacman p3 = new Pacman("classic.txt", "classic_output.txt");
	System.out.println(p3);
	p3.writeOutput();
	
	//Assignment Tests
	Pacman p4 = new Pacman("tinyMaze.txt", "tinyMaze_output.txt");
	//Tests for BFS
	p4.solveBFS();
	System.out.println(p4);
	p4.writeOutput();
	
	
	
	Pacman p5 = new Pacman("tinyMaze.txt", "tinyMaze_output.txt");
	//Test for DFS
	p5.solveDFS();
	System.out.println(p5);
	p5.writeOutput();
	
	
	}

}
