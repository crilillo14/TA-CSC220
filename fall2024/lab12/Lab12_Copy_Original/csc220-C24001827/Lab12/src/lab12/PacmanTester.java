package lab12;

public class PacmanTester {
    public static void main(String[] args) {
        try {
            
            String inputFileName = "demoMaze.txt";
            String outputFileNameBFS = "demoMazeBFSSol.txt";
            String outputFileNameDFS = "demoMazeDFSSol.txt";

           
            Pacman pacmanDFS = new Pacman(inputFileName, outputFileNameDFS, outputFileNameDFS);

            
            System.out.println("Input Maze:");
            System.out.println(pacmanDFS.toString());

            // Solve the maze using DFS
            if (pacmanDFS.solveDFS()) {
                System.out.println("Maze solved using DFS:");
                pacmanDFS.writeOutput(); 
                System.out.println(pacmanDFS.toString()); 
            } else {
                System.out.println("No solution found for the maze using DFS.");
            }

           
            Pacman pacmanBFS = new Pacman(inputFileName, outputFileNameBFS, outputFileNameDFS);

            // Solve the maze using BFS
            if (pacmanBFS.solveBFS()) {
                System.out.println("Maze solved using BFS:");
                pacmanBFS.writeOutput(); 
                System.out.println(pacmanBFS.toString()); 
            } else {
                System.out.println("No solution found for the maze using BFS.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
