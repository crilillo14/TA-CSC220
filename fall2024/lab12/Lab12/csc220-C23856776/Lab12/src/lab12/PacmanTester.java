package lab12;

import java.io.File;

public class PacmanTester {

    public static void main(String[] args) {
    	
        // Input and output files (will find under src for lab12)
        String inputFileName = "demoMaze.txt";
        
        String outputFileNameBFS = "demoMazeeBFSSol.txt";
        
        String outputFileNameDFS = "demoMazeDFSSol.txt";
        

        // Test 1: Gathering Input File
        File mazeFile = new File(inputFileName);
        
        if (!mazeFile.exists()) {
        	
            System.err.println("Error: Input maze file is not found at " + mazeFile.getAbsolutePath());
            return;
            
        }

        try {
        	
        	// Test Number 2: Pacman object
        	
            System.out.println("Starting Testing Maze...");
            
            Pacman pacman = new Pacman(inputFileName, outputFileNameBFS);
            
            System.out.println("Maze successfully loaded and ready:");
            
            System.out.println(pacman.toString());
            
            
     

            // Test Number 3: Write an output file that will match the input file in this test
            
            System.out.println("Testing the writeOutput() method...");
            
            pacman.writeOutput();
            
            System.out.println("Output written to: " + outputFileNameBFS);

            // completed the testing through text-compare.com

            // Testing Number 4: Solving maze using BFS and check the output
            System.out.println("\nTesting the BFS Solution...");
            
            Pacman pacmanBFS = new Pacman(inputFileName, outputFileNameBFS);
            
            pacmanBFS.solveBFS(); // Solve the maze using BFS
            
            pacmanBFS.writeOutput(); // Write the solved maze to the output file
            
            System.out.println("BFS solution written to: " + outputFileNameBFS);
            
            

            // Testing Number 5: Solving maze using DFS
            System.out.println("\nTesting the DFS Solution...");
            
            Pacman pacmanDFS = new Pacman(inputFileName, outputFileNameDFS);
            
            pacmanDFS.solveDFS(); 
            
            pacmanDFS.writeOutput(); 
            
            System.out.println("DFS solution written to: " + outputFileNameDFS);
            
            

            // Test 6: Output
            System.out.println("\nYou can now compare the following files:");
            
            System.out.println("1. Input file: " + inputFileName);
            
            System.out.println("2. BFS solution: " + outputFileNameBFS);
            
            System.out.println("3. DFS solution: " + outputFileNameDFS);
            
            System.out.println("Use an online diff tool or visual inspection to verify correctness.");
        
        } catch (Exception e) { // catching exceptions method 
        	
            e.printStackTrace();
        }
    }
}
