package lab12;

public class Tester {
	
	public static void main(String[] args) {
		//Normal Tests
		
		Pacman p1 = new Pacman("tinyMaze.txt", "tinyMaze_output.txt");
		System.out.println(p1);
		p1.writeOutput();
		
        Pacman p2 = new Pacman("mediumMaze.txt", "mediumMaze_output.txt");        
        System.out.println(p2);
		p2.writeOutput();
		
		
		//BFS Tests
		
		Pacman p3 = new Pacman("classic.txt", "classicBFS_output.txt");
		p3.solveBFS();
		System.out.println(p3);
		p3.writeOutput();
		
		//DFS Tests
		
		Pacman p4 = new Pacman("classic.txt", "classicDFS_output.txt");
		p4.solveDFS();
		System.out.println(p4);
		p4.writeOutput();
				



        System.out.println("\nAll tests passed!");

		
		
	}//end of main method

}
