package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PacmanTester {

    public static void main(String[] args) {
        
            Pacman p1= new Pacman("mazes/demoMaze.txt", "mazes/lenaSol.txt");
            System.out.println(p1);
            p1.solveBFS();
            System.out.println(p1);
            p1.writeOutput();
            // Solve using BFS
        }
}