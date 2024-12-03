
package lab12;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PacmanTester {

    public static void main(String[] args) {
        File outputDir = new File("output");
        if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
        
        printOutput("mazes/tinyOpen.txt", "output/tinyOpen_output.txt");

        testMaze("mazes/turn.txt", "output/turn_output.txt");
        testMaze("mazes/randomMaze.txt", "output/randomMaze_output.txt");
        testMaze("mazes/unsolvable.txt", "output/unsolvable_output.txt");
        testMaze("mazes/straight.txt", "output/straight_output.txt");
        testMaze("mazes/tinyOpen.txt", "output/tinyOpen_output.txt");
        testMaze("mazes/bigMaze.txt", "output/bigMaze_output.txt");
        testMaze("mazes/classic.txt", "output/classic_output.txt");
        testMaze("mazes/demoMaze.txt", "output/demoMaze_output.txt");
        testMaze("mazes/mediumMaze.txt", "output/mediumMaze_output.txt");
        testMaze("mazes/tinyMaze.txt", "output/tinyMaze_output.txt");

        testMazeAssignment("mazes/turn.txt", "output/turnBFSSol.txt", "output/turnDFSSol.txt", "mazes/turnBFSSol.txt", "mazes/turnDFSSol.txt");
        testMazeAssignment("mazes/randomMaze.txt", "output/randomMazeBFSSol.txt", "output/randomMazeDFSSol.txt", "mazes/randomMazeBFSSol.txt", "mazes/randomMazeDFSSol.txt");
        testMazeAssignment("mazes/straight.txt", "output/straightBFSSol.txt", "output/straightDFSSol.txt", "mazes/straightBFSSol.txt", "mazes/straightDFSSol.txt");
        testMazeAssignment("mazes/tinyOpen.txt", "output/tinyOpenBFSSol.txt", "output/tinyOpenDFSSol.txt", "mazes/tinyOpenBFSSol.txt", "mazes/tinyOpenDFSSol.txt");
        testMazeAssignment("mazes/bigMaze.txt", "output/bigMazeBFSSol.txt", "output/bigMazeDFSSol.txt", "mazes/bigMazeBFSSol.txt", "mazes/bigMazeDFSSol.txt");
        testMazeAssignment("mazes/classic.txt", "output/classicBFSSol.txt", "output/classicDFSSol.txt", "mazes/classicBFSSol.txt", "mazes/classicDFSSol.txt");
        testMazeAssignment("mazes/demoMaze.txt", "output/demoMazeBFSSol.txt", "output/demoMazeDFSSol.txt", "mazes/demoMazeBFSSol.txt", "mazes/demoMazeDFSSol.txt");
        testMazeAssignment("mazes/mediumMaze.txt", "output/mediumMazeBFSSol.txt", "output/mediumMazeDFSSol.txt", "mazes/mediumMazeBFSSol.txt", "mazes/mediumMazeDFSSol.txt");
        testMazeAssignment("mazes/tinyMaze.txt", "output/tinyMazeBFSSol.txt", "output/tinyMazeDFSSol.txt", "mazes/tinyMazeBFSSol.txt", "mazes/tinyMazeDFSSol.txt");

        testAndPrintMaze("mazes/turn.txt", "output/turnBFSSol.txt", "output/turnDFSSol.txt", "mazes/turnBFSSol.txt", "mazes/turnDFSSol.txt");
        testAndPrintMaze("mazes/randomMaze.txt", "output/randomMazeBFSSol.txt", "output/randomMazeDFSSol.txt", "mazes/randomMazeBFSSol.txt", "mazes/randomMazeDFSSol.txt");
        testAndPrintMaze("mazes/straight.txt", "output/straightBFSSol.txt", "output/straightDFSSol.txt", "mazes/straightBFSSol.txt", "mazes/straightDFSSol.txt");
        testAndPrintMaze("mazes/tinyOpen.txt", "output/tinyOpenBFSSol.txt", "output/tinyOpenDFSSol.txt", "mazes/tinyOpenBFSSol.txt", "mazes/tinyOpenDFSSol.txt");
        testAndPrintMaze("mazes/bigMaze.txt", "output/bigMazeBFSSol.txt", "output/bigMazeDFSSol.txt", "mazes/bigMazeBFSSol.txt", "mazes/bigMazeDFSSol.txt");
        testAndPrintMaze("mazes/classic.txt", "output/classicBFSSol.txt", "output/classicDFSSol.txt", "mazes/classicBFSSol.txt", "mazes/classicDFSSol.txt");
        testAndPrintMaze("mazes/demoMaze.txt", "output/demoMazeBFSSol.txt", "output/demoMazeDFSSol.txt", "mazes/demoMazeBFSSol.txt", "mazes/demoMazeDFSSol.txt");
        testAndPrintMaze("mazes/mediumMaze.txt", "output/mediumMazeBFSSol.txt", "output/mediumMazeDFSSol.txt", "mazes/mediumMazeBFSSol.txt", "mazes/mediumMazeDFSSol.txt");
        testAndPrintMaze("mazes/tinyMaze.txt", "output/tinyMazeBFSSol.txt", "output/tinyMazeDFSSol.txt", "mazes/tinyMazeBFSSol.txt", "mazes/tinyMazeDFSSol.txt");
    }

    private static void testMaze(String inputFileName, String outputFileName) {
        Pacman pacman = new Pacman(inputFileName, outputFileName);
        pacman.writeOutput();

        try {
            List<String> expectedLines = Files.readAllLines(Paths.get(inputFileName));
            List<String> actualLines = Files.readAllLines(Paths.get(outputFileName));

            boolean passed = true;
            for (int i = 0; i < expectedLines.size(); i++) {
                if (!expectedLines.get(i).equals(actualLines.get(i))) {
                    passed = false;
                    System.out.println("Difference at line " + (i + 1) + ":");
                    System.out.println("Expected: " + expectedLines.get(i));
                    System.out.println("Actual:   " + actualLines.get(i));
                }
            }

            if (passed) {
                System.out.println("Test passed for: " + inputFileName);
            } else {
                System.out.println("Test failed for: " + inputFileName);
            }

            if (inputFileName.equals("mazes/demoMaze.txt") || inputFileName.equals("mazes/mediumMaze.txt")) {
                System.out.println("Actual Output for " + inputFileName + ":\n" + String.join("\n", actualLines));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printOutput(String inputFileName, String outputFileName) {
        Pacman pacman = new Pacman(inputFileName, outputFileName);
        pacman.writeOutput();

        try {
            List<String> actualLines = Files.readAllLines(Paths.get(outputFileName));
            System.out.println("Actual Output for " + inputFileName + ":\n" + String.join("\n", actualLines));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void testMazeAssignment(String inputFileName, String bfsOutputFileName, String dfsOutputFileName, String expectedBFSFileName, String expectedDFSFileName) {
        Pacman pacmanBFS = new Pacman(inputFileName, bfsOutputFileName);
        pacmanBFS.solveBFS();

        Pacman pacmanDFS = new Pacman(inputFileName, dfsOutputFileName);
        pacmanDFS.solveDFS();

        compareFiles(bfsOutputFileName, expectedBFSFileName, "BFS");
        compareFiles(dfsOutputFileName, expectedDFSFileName, "DFS");
    }

    private static void compareFiles(String outputFileName, String expectedFileName, String searchType) {
        try {
            List<String> expectedLines = Files.readAllLines(Paths.get(expectedFileName));
            List<String> actualLines = Files.readAllLines(Paths.get(outputFileName));

            boolean passed = true;
            for (int i = 0; i < expectedLines.size(); i++) {
                if (!expectedLines.get(i).equals(actualLines.get(i))) {
                    passed = false;
                    System.out.println(searchType + " Difference at line " + (i + 1) + ":");
                    System.out.println("Expected: " + expectedLines.get(i));
                    System.out.println("Actual:   " + actualLines.get(i));
                }
            }

            if (passed) {
                System.out.println(searchType + " Test passed for: " + outputFileName);
            } else {
                System.out.println(searchType + " Test failed for: " + outputFileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static void testAndPrintMaze(String inputFileName, String bfsOutputFileName, String dfsOutputFileName, String expectedBFSFileName, String expectedDFSFileName) {
        Pacman pacmanBFS = new Pacman(inputFileName, bfsOutputFileName);
        pacmanBFS.solveBFS();
        printMaze(bfsOutputFileName, "BFS Output");

        Pacman pacmanDFS = new Pacman(inputFileName, dfsOutputFileName);
        pacmanDFS.solveDFS();
        printMaze(dfsOutputFileName, "DFS Output");

        printMaze(expectedBFSFileName, "Expected BFS Solution");
        printMaze(expectedDFSFileName, "Expected DFS Solution");
        printMaze(inputFileName, "Original Maze");
    }

    private static void printMaze(String fileName, String description) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            System.out.println(description + " for " + fileName + ":");
            for (String line : lines) {
                System.out.println(line);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
