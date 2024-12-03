package lab12;

import java.nio.file.Files;
import java.nio.file.Paths;

public class PacmanTester {
    public static void main(String[] args) {
    	 
    	try {
    		
        // Lab tests
    	
    	String inputFile1 = "/Users/matthewbernard/eclipse-workspace/Lab12/src/lab12/mazes/tinyMaze.txt";
        String outputFile = "/Users/matthewbernard/eclipse-workspace/Lab12/src/lab12/testOutput.txt";
      
        Pacman pacmanTest = new Pacman(inputFile1, outputFile);
        pacmanTest.writeOutput();
        
        String inputFile2 = "/Users/matthewbernard/eclipse-workspace/Lab12/src/lab12/mazes/bigMaze.txt";
      
        
        Pacman pacmanTest2 = new Pacman(inputFile2, outputFile);
        pacmanTest2.writeOutput();
        
     
        // Assignment Tests
       
    	String inputFileName = "/Users/matthewbernard/eclipse-workspace/Lab12/src/lab12/mazes/bigMaze.txt";
    	String outputFileBFS = "/Users/matthewbernard/eclipse-workspace/Lab12/src/lab12/testOutputBFS.txt";
    	String outputFileDFS = "/Users/matthewbernard/eclipse-workspace/Lab12/src/lab12/testOutputDFS.txt";
        
        
        Pacman pacman3 = new Pacman(inputFileName, outputFileBFS);
        pacman3.solveBFS();
        pacman3.writeOutput();
        
        //check that outputFile is the same as the Solution - used text Compare website
        
        Pacman pacman4 = new Pacman(inputFileName, outputFileDFS);
        pacman4.solveDFS();
        pacman4.writeOutput();
       
        //check that this is the same as solution - used text compare website
        
        
        System.out.println("Testing Passed!")  ;  
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
