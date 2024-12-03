package lab12;

public class PacmanTester {
    
	
	public static void main(String[] args) {
        
        String inputFileName = "randomMaze.txt";
        String bfsOutputFileName = "testResultBFSSol.txt";
        String dfsOutputFileName = "testResultDFSSol.txt";

       
        Pacman bfsPacman = new Pacman(inputFileName, bfsOutputFileName);
        Pacman dfsPacman = new Pacman(inputFileName, dfsOutputFileName);

        System.out.println("Solving using BFS:");
        bfsPacman.solveBFS();
        System.out.println(bfsPacman.toString());

        
        System.out.println("Solving using DFS:");
        dfsPacman.solveDFS();
        System.out.println(dfsPacman.toString());
    } 
    
    /*
    public static void main(String[] args) {
        // Define the input maze file
        String inputFileName = "bigMaze.txt"; // Replace with your maze file path
        String dummyOutputFileName = "dummyOutput.txt"; // A placeholder output filename

        // Create a Pacman instance for solving the maze
        Pacman bfsPacman = new Pacman(inputFileName, dummyOutputFileName);
        Pacman dfsPacman = new Pacman(inputFileName, dummyOutputFileName);

        // Solve the maze using BFS and print the result
        System.out.println("Solving using BFS:");
        bfsPacman.solveBFS();
        System.out.println(bfsPacman.toString());

        // Solve the maze using DFS and print the result
        System.out.println("\nSolving using DFS:");
        dfsPacman.solveDFS();
        System.out.println(dfsPacman.toString());
    }*/
    
    
}