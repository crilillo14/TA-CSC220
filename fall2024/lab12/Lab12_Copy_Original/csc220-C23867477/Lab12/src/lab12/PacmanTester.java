package lab12;

public class PacmanTester {
    public static void main(String[] args) {
        testMazeConstruction();
        testMazeOutput();
        testBFS();
        testDFS();
        compareSearchMethods();
    }
    
    private static void testBFS() {
        System.out.println("Testing BFS Solutions:");
        
        // Test BFS on straight maze
        System.out.println("\nStraight Maze - BFS:");
        Pacman straightMaze = new Pacman("mazes/straight.txt", "mazes/straightBFSSol.txt");
        System.out.println("Original maze:");
        System.out.println(straightMaze.toString());
        straightMaze.solveBFS();
        System.out.println("BFS solution:");
        System.out.println(straightMaze.toString());
        
        // Test BFS on classic maze
        System.out.println("\nClassic Maze - BFS:");
        Pacman classicMaze = new Pacman("mazes/classic.txt", "mazes/classicBFSSol.txt");
        System.out.println("Original maze:");
        System.out.println(classicMaze.toString());
        classicMaze.solveBFS();
        System.out.println("BFS solution:");
        System.out.println(classicMaze.toString());
        
        // Test BFS on big maze
        System.out.println("\nBig Maze - BFS:");
        Pacman bigMaze = new Pacman("mazes/bigMaze.txt", "mazes/bigMazeBFSSol.txt");
        System.out.println("Original maze:");
        System.out.println(bigMaze.toString());
        bigMaze.solveBFS();
        System.out.println("BFS solution:");
        System.out.println(bigMaze.toString());
    }
    
    private static void testDFS() {
        System.out.println("\nTesting DFS Solutions:");
        
        // Test DFS on straight maze
        System.out.println("\nStraight Maze - DFS:");
        Pacman straightMaze = new Pacman("mazes/straight.txt", "mazes/straightDFSSol.txt");
        System.out.println("Original maze:");
        System.out.println(straightMaze.toString());
        straightMaze.solveDFS();
        System.out.println("DFS solution:");
        System.out.println(straightMaze.toString());
        
        // Test DFS on classic maze
        System.out.println("\nClassic Maze - DFS:");
        Pacman classicMaze = new Pacman("mazes/classic.txt", "mazes/classicDFSSol.txt");
        System.out.println("Original maze:");
        System.out.println(classicMaze.toString());
        classicMaze.solveDFS();
        System.out.println("DFS solution:");
        System.out.println(classicMaze.toString());
        
        // Test DFS on big maze
        System.out.println("\nBig Maze - DFS:");
        Pacman bigMaze = new Pacman("mazes/bigMaze.txt", "mazes/bigMazeDFSSol.txt");
        System.out.println("Original maze:");
        System.out.println(bigMaze.toString());
        bigMaze.solveDFS();
        System.out.println("DFS solution:");
        System.out.println(bigMaze.toString());
    }
    
    private static void compareSearchMethods() {
        System.out.println("\nComparing BFS and DFS Solutions:");
        
        // Compare solutions for each maze
        String[] mazeFiles = {"straight.txt", "classic.txt", "bigMaze.txt"};
        
        for (String mazeFile : mazeFiles) {
            System.out.println("\nTesting " + mazeFile + ":");
            
            // Get base filename without extension
            String baseName = mazeFile.substring(0, mazeFile.lastIndexOf('.'));
            
            // BFS Solution
            Pacman bfsMaze = new Pacman("mazes/" + mazeFile, "mazes/" + baseName + "BFSSol.txt");
            bfsMaze.solveBFS();
            System.out.println("BFS Solution:");
            System.out.println(bfsMaze.toString());
            
            // DFS Solution
            Pacman dfsMaze = new Pacman("mazes/" + mazeFile, "mazes/" + baseName + "DFSSol.txt");
            dfsMaze.solveDFS();
            System.out.println("DFS Solution:");
            System.out.println(dfsMaze.toString());
        }
    }
    
    private static void testMazeConstruction() {
        Pacman bigMaze = new Pacman("mazes/bigMaze.txt", "mazes/bigMazeOutput.txt");
        System.out.println("Testing Big Maze:");
        System.out.println(bigMaze.toString());
        bigMaze.writeOutput();
        
        Pacman classicMaze = new Pacman("mazes/classic.txt", "mazes/classicOutput.txt");
        System.out.println("\nTesting Classic Maze:");
        System.out.println(classicMaze.toString());
        classicMaze.writeOutput();
        
        Pacman straightMaze = new Pacman("mazes/straight.txt", "mazes/straightOutput.txt");
        System.out.println("\nTesting Straight Maze:");
        System.out.println(straightMaze.toString());
        straightMaze.writeOutput();
    }
    
    private static void testMazeOutput() {
        System.out.println("\nVerifying maze output:");
        Pacman maze = new Pacman("mazes/classic.txt", "mazes/classicOutput.txt");
        String mazeString = maze.toString();
        System.out.println("Maze successfully loaded and converted to string");
        System.out.println(mazeString);
    }
}