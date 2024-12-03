package lab12;

public class tester {
	
	public static void main(String[] args) {
		Pacman p1 = new Pacman("tinyMaze.txt", "tinyMazeSOLUTION_bfs.txt");
//		
//		p1.solveBFS();
//		System.out.println(p1);
//		
//		Pacman p2 = new Pacman("classic.txt", "classicSOLUTION_bfs.txt");
//		p2.solveBFS();
//		System.out.println(p2);
//		
//		
//		
//		Pacman p3 = new Pacman("tinyMaze.txt", "tinyMazeSOLUTION_bfs.txt");
//		p3.solveDFS();
//		System.out.println(p3);
//		Pacman p4 = new Pacman("unsolvable.txt", "unsolvableMazeSOLUTION_bfs.txt");
//		p4.solveDFS();
//		System.out.println(p4);
		
		
		Pacman p5 = new Pacman("turn.txt", "turnSOLUTION_bfs.txt");
		p5.solveBFS();
		System.out.println(p5);

		
	}// end of main method

}
