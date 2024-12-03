package lab12;

public class PacmanTester {

    public static void main(String[] args) {
        runTestDFS("tinyMaze.txt", "output/tinyMaze_DFS_output.txt");
        runTestBFS("tinyMaze.txt", "output/tinyMaze_BFS_output.txt");

        runTestDFS("bigMaze.txt", "output/bigMaze_DFS_output.txt");
        runTestBFS("bigMaze.txt", "output/bigMaze_BFS_output.txt");

        runTestDFS("mediumMaze.txt", "output/mediumMaze_DFS_output.txt");
        runTestBFS("mediumMaze.txt", "output/mediumMaze_BFS_output.txt");

        runTestDFS("demoMaze.txt", "output/demoMaze_DFS_output.txt");
        runTestBFS("demoMaze.txt", "output/demoMaze_BFS_output.txt");
    }


    private static void runTestDFS(String inputFileName, String outputFileName) {
        System.out.println("DFS Test: ");
        System.out.println("Input: " + inputFileName);


        Pacman pacman = new Pacman(inputFileName, outputFileName);

        pacman.solveDFS();

        System.out.println("DFS Solution:");
        System.out.println(pacman.toString());

        System.out.println("Output written to: " + outputFileName);
        System.out.println();
    }

    private static void runTestBFS(String inputFileName, String outputFileName) {
        System.out.println("BFS Test: ");
        System.out.println("Input: " + inputFileName);

        Pacman pacman = new Pacman(inputFileName, outputFileName);

        pacman.solveBFS();

        System.out.println("BFS Solution:");
        System.out.println(pacman.toString());

        System.out.println("Output written to: " + outputFileName);
        System.out.println();
    }
}
