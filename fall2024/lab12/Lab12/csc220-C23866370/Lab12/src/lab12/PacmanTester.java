package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PacmanTester {

    public static void main(String[] args) {
        // Test mazes
        String[] testMazes = {
            "tinyMaze.txt",
            "demoMaze.txt",
            "mediumMaze.txt",
            "bigMaze.txt"
        };

        // Test BFS and DFS for each maze
        for (String mazeFile : testMazes) {
            String baseName = mazeFile.replace(".txt", "");
            String inputFileName = "mazes/" + mazeFile;
            String bfsOutputFileName = "mazes/" + baseName + "_BFS_Output.txt";
            String bfsSolutionFileName = "mazes/" + baseName + "BFSSol.txt";
            String dfsOutputFileName = "mazes/" + baseName + "_DFS_Output.txt";
            String dfsSolutionFileName = "mazes/" + baseName + "DFSSol.txt";

            System.out.println("Testing maze: " + mazeFile);

            // BFS Test
            System.out.println("Running BFS...");
            Pacman pacmanBFS = new Pacman(inputFileName, bfsOutputFileName);
            pacmanBFS.solveBFS();
            System.out.println("Comparing BFS output...");
            compareFiles(bfsOutputFileName, bfsSolutionFileName);

            // DFS Test
            System.out.println("Running DFS...");
            Pacman pacmanDFS = new Pacman(inputFileName, dfsOutputFileName);
            pacmanDFS.solveDFS();
            System.out.println("Comparing DFS output...");
            compareFiles(dfsOutputFileName, dfsSolutionFileName);

            System.out.println("--------------------------------------");
        }
    }

    // Utility method to compare two files
    private static void compareFiles(String file1, String file2) {
        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1));
             BufferedReader reader2 = new BufferedReader(new FileReader(file2))) {

            String line1, line2;
            int lineNumber = 1;
            boolean filesAreIdentical = true;

            while ((line1 = reader1.readLine()) != null && (line2 = reader2.readLine()) != null) {
                if (!line1.equals(line2)) {
                    System.out.println("Difference found on line " + lineNumber + ":");
                    System.out.println("File 1: " + line1);
                    System.out.println("File 2: " + line2);
                    filesAreIdentical = false;
                }
                lineNumber++;
            }

            if ((line1 = reader1.readLine()) != null || (line2 = reader2.readLine()) != null) {
                System.out.println("Files have different lengths!");
                filesAreIdentical = false;
            }

            if (filesAreIdentical) {
                System.out.println("Files are identical.");
            }
        } catch (IOException e) {
            System.out.println("Error comparing files: " + e.getMessage());
        }
    }
}
