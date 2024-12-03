package lab12;

public class PacmanTester {
	public static void main(String[] arg)
    {
    Pacman maze1 = new Pacman("bigMaze.txt", "bigMazeBFS_output.txt");
    maze1.solveBFS();
    maze1.writeOutput();
    System.out.println("DONE");
    }
}
