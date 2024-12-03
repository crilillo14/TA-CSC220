package lab12;

//import java.io.File;

public class PacmanTester
{
	public static void main (String [] args)
	{
		/*// Define input and output files for testing
        String inputFile = "mazes/demoMaze.txt"; // Replace with the path to your maze file
        String bfsOutputFile = "output/demoMazeBFSSol.txt"; // Output for BFS
        String dfsOutputFile = "output/demoMazeDFSSol.txt"; // Output for DFS

        // Create output directory if it doesn't exist
        new File("output").mkdir();

        // Test the constructor and initial graph setup
        System.out.println("Testing maze initialization...");
        Pacman pacman = new Pacman(inputFile, bfsOutputFile);
        pacman.writeOutput();
        System.out.println("Maze structure written to " + bfsOutputFile + ". Compare it with the input file.");

        // Test BFS
        System.out.println("Testing BFS solution...");
        pacman.solveBFS();
        System.out.println("BFS solution written to " + bfsOutputFile + ". Check the output file for correctness.");

        // Test DFS
        System.out.println("Testing DFS solution...");
        Pacman pacmanDFS = new Pacman(inputFile, dfsOutputFile);
        pacmanDFS.solveDFS();
        System.out.println("DFS solution written to " + dfsOutputFile + ". Check the output file for correctness.");*/
		
		Pacman maze1 = new Pacman("/Users/hrishi.mucherla/eclipse-workspace/Lab12/src/lab12/mazes/tinyMaze.txt","out1.txt");
		//System.out.println(maze1);
		//maze1.solveBFS();
		maze1.solveDFS();
		System.out.println(maze1);

		
		Pacman maze2 = new Pacman("/Users/hrishi.mucherla/eclipse-workspace/Lab12/src/lab12/mazes/turn.txt","out2.txt");		
		//maze2.solveBFS();
    	maze2.solveDFS();
		System.out.println(maze2);
		
		Pacman maze3 = new Pacman("/Users/hrishi.mucherla/eclipse-workspace/Lab12/src/lab12/mazes/tinyopen.txt","out3.txt");
		//System.out.println(maze3);
		//maze3.solveBFS();
		maze3.solveDFS();
		System.out.println(maze3);
		
		Pacman maze4 = new Pacman("/Users/hrishi.mucherla/eclipse-workspace/Lab12/src/lab12/mazes/straight.txt","out4.txt");
		//System.out.println(maze4);
		//maze4.solveBFS();
		maze4.solveDFS();
		System.out.println(maze4);
		
		Pacman maze5 = new Pacman("/Users/hrishi.mucherla/eclipse-workspace/Lab12/src/lab12/mazes/randomMaze.txt","out5.txt");
		//System.out.println(maze5);
	    //maze5.solveBFS();
		maze5.solveDFS();
		System.out.println(maze5);
		
		Pacman maze6 = new Pacman("/Users/hrishi.mucherla/eclipse-workspace/Lab12/src/lab12/mazes/mediumMaze.txt","out6.txt");
		//System.out.println(maze6);
		//maze6.solveBFS();
		maze6.solveDFS();
		System.out.println(maze6);
		
		Pacman maze7 = new Pacman("/Users/hrishi.mucherla/eclipse-workspace/Lab12/src/lab12/mazes/demoMaze.txt","out7.txt");
		//System.out.println(maze7);
		//maze7.solveBFS();
		maze7.solveDFS();
		System.out.println(maze7);
		
	    Pacman maze8 = new Pacman("/Users/hrishi.mucherla/eclipse-workspace/Lab12/src/lab12/mazes/classic.txt","out8.txt");
		//System.out.println(maze8);
		//maze8.solveBFS();
		maze8.solveDFS();
		System.out.println(maze8);
		
     	Pacman maze9 = new Pacman("/Users/hrishi.mucherla/eclipse-workspace/Lab12/src/lab12/mazes/bigMaze.txt","out9.txt");
		//System.out.println(maze9);
		//maze9.solveBFS();
		maze9.solveDFS();
		System.out.println(maze9);
		
		Pacman unsolvable = new Pacman("/Users/hrishi.mucherla/eclipse-workspace/Lab12/src/lab12/mazes/unsolvable.txt","outUnsolvable.txt");
		//System.out.println(unsolvable);
    	//unsolvable.solveBFS();
		unsolvable.solveDFS();
		System.out.println(unsolvable);
		
		System.out.println("Testing Done!");
		
		// You can uncomment to test out the desired BFS and DFS iterations of it!
		
    }
}