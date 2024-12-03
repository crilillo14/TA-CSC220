package lab12;

public class Tester {
	public static void main(String[] args) {
		//TODO Auto generated method stub
        // Test tiny maze
        Pacman p1 = new Pacman("tinyMaze.txt", "tinyMaze_output_BFS.txt");
        System.out.println("Tiny Maze (Initial State):");
        System.out.println(p1);
        p1.solveBFS();
        System.out.println("Tiny Maze (Solved with BFS):");
        System.out.println(p1);
        p1.writeOutput();
        System.out.println("Output written to tinyMaze_output_BFS.txt");

        Pacman p1DFS = new Pacman("tinyMaze.txt", "tinyMaze_output_DFS.txt");
        p1DFS.solveDFS();
        System.out.println("Tiny Maze (Solved with DFS):");
        System.out.println(p1DFS);
        p1DFS.writeOutput();
        System.out.println("Output written to tinyMaze_output_DFS.txt");

        // Test classic maze
        Pacman p2 = new Pacman("classic.txt", "classic_output.txt");
        System.out.println("Classic Maze (Initial State):");
        System.out.println(p2);
        p2.solveBFS();
        System.out.println("Classic Maze (Solved with BFS):");
        System.out.println(p2);
        p2.writeOutput();
        System.out.println("Output written to classic_output.txt");

        Pacman p2DFS = new Pacman("classic.txt", "classic_output_DFS.txt");
        p2DFS.solveDFS();
        System.out.println("Classic Maze (Solved with DFS):");
        System.out.println(p2DFS);
        p2DFS.writeOutput();
        System.out.println("Output written to classic_output_DFS.txt");
        
        
		}
	
	
		
		

}
