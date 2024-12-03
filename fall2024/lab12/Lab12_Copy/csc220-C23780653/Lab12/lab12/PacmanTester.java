package lab12;

public class PacmanTester{
	
	public static void main(String[] args) {
	      // Test BFS
        System.out.println("Testing BFS solution:");
        Pacman pacmanBFS = new Pacman("classic.txt", "classicBFSSol.txt");
        pacmanBFS.solveBFS();
        pacmanBFS.writeOutput();
        
        System.out.println(pacmanBFS.toString());
        
        
        //Test DFS
        System.out.println("Testing DFS solution:");
        Pacman pacmanDFS = new Pacman("classic.txt", "classicDFSSol.txt");
        pacmanDFS.solveDFS();
        pacmanDFS.writeOutput();
        
        System.out.println(pacmanDFS.toString());
		
        
		
	}
	
}