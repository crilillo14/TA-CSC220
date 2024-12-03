package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PacmanTester {
    public static void main(String[] args) {
    	//lab test
        String inputFileName = "tinyMaze.txt";
        String outputFileName = "tinyMazeOutput.txt";

        Pacman pacman = new Pacman(inputFileName, outputFileName);
        pacman.writeOutput();

        try {
            BufferedReader inputReader = new BufferedReader(new FileReader(inputFileName));
            BufferedReader outputReader = new BufferedReader(new FileReader(outputFileName));

            String inputLine;
            String outputLine;
            boolean passed = true;

            while ((inputLine = inputReader.readLine()) != null) {
                outputLine = outputReader.readLine();

                if (outputLine == null || !inputLine.equals(outputLine)) {
                    passed = false;
                    break;
                }
            }

        
            if (outputReader.readLine() != null) {
                passed = false;
            }

            inputReader.close();
            outputReader.close();

            if (passed) {
                System.out.println("Test passed: Output matches the input file.");
            } else {
                System.out.println("Test failed: Output does not match the input file.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        //assignment test
    	// Test BFS with tinyMaze.txt
        Pacman bfsTest = new Pacman("tinyMaze.txt", "tinyMazeBFSTestOutput.txt");
        bfsTest.solveBFS();
        System.out.println("BFS solution written to tinyMazeBFSTestOutput.txt");
        // Print the BFS solution to the console
        System.out.println("BFS Solution:");
        System.out.println(bfsTest);

        // Test DFS with tinyMaze.txt
        Pacman dfsTest = new Pacman("tinyMaze.txt", "tinyMazeDFSTestOutput.txt");
        dfsTest.solveDFS();
        System.out.println("DFS solution written to tinyMazeDFSTestOutput.txt");
        // Print the DFS solution to the console
        System.out.println("DFS Solution:");
        System.out.println(dfsTest);
    }
}


