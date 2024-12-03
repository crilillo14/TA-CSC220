package lab12;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PacmanTester {

    public static void main(String[] args) {
        // Test all mazes
        testMaze("tinyMaze.txt", "tinyMazeBFSSol.txt", "tinyMazeDFSSol.txt");
        testMaze("tinyOpen.txt", "tinyOpenBFSSol.txt", "tinyOpenDFSSol.txt");
        testMaze("straight.txt", "straightBFSSol.txt", "straightDFSSol.txt");
        testMaze("turn.txt", "turnBFSSol.txt", "turnDFSSol.txt");
        testMaze("randomMaze.txt", "randomMazeBFSSol.txt", "randomMazeDFSSol.txt");
        testMaze("mediumMaze.txt", "mediumMazeBFSSol.txt", "mediumMazeDFSSol.txt");
        testMaze("bigMaze.txt", "bigMazeBFSSol.txt", "bigMazeDFSSol.txt");
        testMaze("classic.txt", "classicBFSSol.txt", "classicDFSSol.txt");
        testMaze("demoMaze.txt", "demoMazeBFSSol.txt", "demoMazeDFSSol.txt");
        testMaze("unsolvable.txt", null, null); // No solution for unsolvable
    }

    private static void testMaze(String inputFileName, String bfsSolutionFile, String dfsSolutionFile) {
        System.out.println("\nTesting Maze: " + inputFileName);

        String bfsOutputFile = "output_bfs_" + inputFileName;
        String dfsOutputFile = "output_dfs_" + inputFileName;

        // Test BFS
        if (bfsSolutionFile != null) {
            Pacman pacmanBFS = new Pacman(inputFileName, bfsOutputFile);
            pacmanBFS.solveBFS();
            pacmanBFS.writeOutput();
            compareFiles(bfsOutputFile, bfsSolutionFile, "BFS");
        }

        // Test DFS
        if (dfsSolutionFile != null) {
            Pacman pacmanDFS = new Pacman(inputFileName, dfsOutputFile);
            pacmanDFS.solveDFS();
            pacmanDFS.writeOutput();
            compareFiles(dfsOutputFile, dfsSolutionFile, "DFS");
        }
    }

    private static void compareFiles(String outputFile, String solutionFile, String method) {
        try {
            boolean isSame = Files.readAllLines(Paths.get(outputFile))
                    .equals(Files.readAllLines(Paths.get(solutionFile)));
            if (isSame) {
                System.out.println(method + " Solution matches for " + solutionFile);
            } else {
                System.out.println(method + " Solution does NOT match for " + solutionFile);
            }
        } catch (Exception e) {
            System.err.println("Error comparing files: " + outputFile + " and " + solutionFile);
            e.printStackTrace();
        }
    }
}
