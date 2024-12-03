package lab12;

public class PacmanTester {
	public static void main(String[] args) {
		
        Pacman tinyMazeBFS = new Pacman("tinyMaze.txt", "tinyMaze_output_BFS.txt");
        System.out.println("Tiny Maze (Starting Configuration):");
        System.out.println(tinyMazeBFS);
        tinyMazeBFS.solveBFS();
        System.out.println("Tiny Maze (Solved using BFS):");
        System.out.println(tinyMazeBFS);
        tinyMazeBFS.writeOutput();
        System.out.println("Results saved to tinyMaze_output_BFS.txt");

        Pacman tinyMazeDFS = new Pacman("tinyMaze.txt", "tinyMaze_output_DFS.txt");
        tinyMazeDFS.solveDFS();
        System.out.println("Tiny Maze (Solved using DFS):");
        System.out.println(tinyMazeDFS);
        tinyMazeDFS.writeOutput();
        System.out.println("Results saved to tinyMaze_output_DFS.txt");

        Pacman classicMazeBFS = new Pacman("classic.txt", "classic_output_BFS.txt");
        System.out.println("Classic Maze (Starting Configuration):");
        System.out.println(classicMazeBFS);
        classicMazeBFS.solveBFS();
        System.out.println("Classic Maze (Solved using BFS):");
        System.out.println(classicMazeBFS);
        classicMazeBFS.writeOutput();
        System.out.println("Results saved to classic_output_BFS.txt");

        Pacman classicMazeDFS = new Pacman("classic.txt", "classic_output_DFS.txt");
        classicMazeDFS.solveDFS();
        System.out.println("Classic Maze (Solved using DFS):");
        System.out.println(classicMazeDFS);
        classicMazeDFS.writeOutput();
        System.out.println("Results saved to classic_output_DFS.txt");
        
	}

}
