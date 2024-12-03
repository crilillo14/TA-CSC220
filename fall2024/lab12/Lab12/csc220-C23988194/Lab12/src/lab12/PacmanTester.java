package lab12; 

import java.io.File;

public class PacmanTester {
    public static void main(String[] args) {
        String inputFileName = "classic.txt";
        String outputFileName = "outputClassic.txt";

        File inputFile = new File(inputFileName);
        if (!inputFile.exists()) {
            System.out.println("Input file not found: " + inputFileName);
            return;
        }
        
        Pacman pacman = new Pacman(inputFileName, outputFileName);
        pacman.writeOutput();

        System.out.println("Maze:");
        System.out.println(pacman);
        
        String bfsfile = "classicBFSSol.txt";
        String dfsfile = "classicDFSSol.txt";

        
        // Assignment
        File inputFile1 = new File(inputFileName);
        if (!inputFile1.exists()) {
            System.out.println("Input file not found: " + inputFileName);
            return;
        }
        
        System.out.println("Solving with BFS");
        Pacman pacmanBFS = new Pacman(inputFileName, bfsfile);
        pacmanBFS.solveBFS();
        System.out.println("BFS: " + bfsfile + "\n");


        System.out.println("Solving with DFS");
        Pacman pacmanDFS = new Pacman(inputFileName, dfsfile);
        pacmanDFS.solveDFS();
        System.out.println("DFS: " + dfsfile + "\n");
    }
}
