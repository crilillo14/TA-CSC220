package lab12;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PacmanTester {

    public static void main(String[] args) {
        String[] testFiles = {
            "lab12/tinyMaze.txt",
            "lab12/classic.txt",
            "lab12/randomMazeBFSSol.txt",
            "lab12/randomMazeDFSSol.txt"
        };

        // Test BFS and DFS on all files
        for (String file : testFiles) {
            System.out.println("Testing file: " + file);
            testMaze(file);
            System.out.println();
        }
    }

    private static void testMaze(String inputFile) {
        String bfsOutputFile = inputFile.replace(".txt", "_BFS_Solution.txt");
        String dfsOutputFile = inputFile.replace(".txt", "_DFS_Solution.txt");

        System.out.println("Testing BFS...");
        try {
            // Initialize Pacman with BFS
            Pacman bfsSolver = new Pacman(inputFile, bfsOutputFile);
            bfsSolver.solveBFS();
            bfsSolver.writeOutput();

            // Verify BFS solution
            if (verifySolution(bfsOutputFile, inputFile.replace(".txt", "_ExpectedBFS.txt"))) {
                System.out.println("BFS solution matches expected output!");
            } else {
                System.out.println("BFS solution does NOT match expected output.");
            }
        } catch (Exception e) {
            System.err.println("BFS Test failed: " + e.getMessage());
        }

        System.out.println("Testing DFS...");
        try {
            // Initialize Pacman with DFS
            Pacman dfsSolver = new Pacman(inputFile, dfsOutputFile);
            dfsSolver.solveDFS();
            dfsSolver.writeOutput();

            // Verify DFS solution
            if (verifySolution(dfsOutputFile, inputFile.replace(".txt", "_ExpectedDFS.txt"))) {
                System.out.println("DFS solution matches expected output!");
            } else {
                System.out.println("DFS solution does NOT match expected output.");
            }
        } catch (Exception e) {
            System.err.println("DFS Test failed: " + e.getMessage());
        }
    }

    /**
     * Verifies that the generated solution matches the expected solution.
     *
     * @param generatedFile The file generated by BFS or DFS solving.
     * @param expectedFile  The expected solution file.
     * @return true if the files match, false otherwise.
     */
    private static boolean verifySolution(String generatedFile, String expectedFile) {
        try (BufferedReader generatedReader = new BufferedReader(new FileReader(generatedFile));
             BufferedReader expectedReader = new BufferedReader(new FileReader(expectedFile))) {

            String generatedLine;
            String expectedLine;

            while ((generatedLine = generatedReader.readLine()) != null &&
                    (expectedLine = expectedReader.readLine()) != null) {
                if (!generatedLine.equals(expectedLine)) {
                    return false; // Lines differ
                }
            }
            return generatedReader.readLine() == null && expectedReader.readLine() == null;

        } catch (IOException e) {
            System.err.println("Error comparing files: " + e.getMessage());
            return false;
        }
    }
}