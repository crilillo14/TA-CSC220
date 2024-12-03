package lab12;

public class PacmanTester {
	public static void main(String[] args) {
		
	Pacman maze1 = new Pacman("mazes/bigMaze.txt", "out.txt");
		maze1.solveDFS();
	System.out.print(maze1);
		
		Pacman maze2 = new Pacman("mazes/bigMaze.txt", "out.txt");
		maze2.solveBFS();
		System.out.print(maze2);
		
		Pacman maze3 = new Pacman("mazes/turn.txt", "out.txt");
		maze3.solveDFS();
		System.out.print(maze3);
		
		Pacman maze4 = new Pacman("mazes/classic.txt", "out.txt");
		maze4.solveBFS();
		System.out.print(maze4);
		
		Pacman maze5 = new Pacman("mazes/demoMaze.txt", "out.txt");
		maze5.solveDFS();
		System.out.print(maze5);
		
		Pacman maze6 = new Pacman("mazes/demoMaze.txt", "out.txt");
		maze6.solveBFS();
		System.out.print(maze6);
		
		Pacman maze7 = new Pacman("mazes/tinyOpen.txt", "out.txt");
		maze7.solveBFS();
		System.out.print(maze7);
		
		
		Pacman maze8 = new Pacman("mazes/tinyOpenBFSSol.txt", "out.txt");
		maze8.solveBFS();
		System.out.print(maze8);
		
		
		Pacman maze9 = new Pacman("mazes/tinyOpenDFSSol.txt", "out.txt");
		maze9.solveBFS();
		System.out.print(maze9);
		
	
        
    } 

}
