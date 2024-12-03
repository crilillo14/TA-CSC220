package lab12;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PacmanTester {
	public static void main(String[] args) {
    
        testMaze("mazes/bigMaze.txt");
    }

    private static void testMaze(String inputFileName) {
        System.out.println("Testing maze: " + inputFileName);
        
        String outputFileName = "output_" + new File(inputFileName).getName();
        String bfsOutputFileName = "output_BFS_" + new File(inputFileName).getName();
        String dfsOutputFileName = "output_DFS_" + new File(inputFileName).getName();
        
      
        Pacman pacman = new Pacman(inputFileName, outputFileName);
        System.out.println("Maze structure:");
        System.out.println(pacman.toString());
        pacman.writeOutput();
        compareFiles(inputFileName, outputFileName, "Initial maze");

    
        Pacman pacmanBFS = new Pacman(inputFileName, bfsOutputFileName);
        pacmanBFS.solveBFS();
        System.out.println("BFS solution:");
        System.out.println(pacmanBFS.toString());
        compareFiles(inputFileName.replace(".txt", "BFSSol.txt"), bfsOutputFileName, "BFS solution");

      
        Pacman pacmanDFS = new Pacman(inputFileName, dfsOutputFileName);
        pacmanDFS.solveDFS();
        System.out.println("DFS solution:");
        System.out.println(pacmanDFS.toString());
        compareFiles(inputFileName.replace(".txt", "DFSSol.txt"), dfsOutputFileName, "DFS solution");

        System.out.println("--------------------");
    }

    private static void compareFiles(String expectedFileName, String actualFileName, String testName) {
        try {
            String expected = new String(Files.readAllBytes(Paths.get(expectedFileName)));
            String actual = new String(Files.readAllBytes(Paths.get(actualFileName)));

            if (expected.equals(actual)) {
                System.out.println(testName + ": PASS - Files match!");
            } else {
                System.out.println(testName + ": FAIL - Files do not match.");
                System.out.println("You may want to use a diff tool to check the differences.");
            }
        } catch (IOException e) {
            System.out.println("Error reading files: " + e.getMessage());
        }
    }
	
	

}
