package lab12;

public class Tester {
	
	public static void main(String[] args) {
		Pacman p1 = new Pacman("tinyMaze.txt", "tinyMaze_output.txt"); //check done
//		System.out.println(p1); 
//		p1.solveBFS();
//		System.out.println("BFS results");
//		System.out.println(p1);
		
//		p1.solveDFS();
//		System.out.println("DFS results");
//		System.out.println(p1);
		
		Pacman p2 = new Pacman("mediumMaze.txt", "mediumMaze_output.txt"); //check done
//		System.out.println(p2);
//		p2.solveBFS();
//		System.out.println("BFS results");
//		System.out.println(p2);
		
//		p2.solveDFS();
//		System.out.println("DFS results");
//		System.out.println(p2);
		
		Pacman p3 = new Pacman("bigMaze.txt", "bigMaze_output.txt"); //check done
//		System.out.println(p3);
//		p3.solveBFS();
//		System.out.println("BFS results");
//		System.out.println(p3);
	
//		p3.solveDFS();
//		System.out.println("DFS results");
//		System.out.println(p3);
		
		Pacman p4 = new Pacman("randomMaze.txt", "randomMaze_output.txt"); //check done
//		System.out.println(p4);
//		p4.solveBFS();
//		System.out.println("BFS results");
//		System.out.println(p4);
	
//		p4.solveDFS();
//		System.out.println("DFS results");
//		System.out.println(p4);
		
		Pacman p5 = new Pacman("unsolvable.txt", "unsolvable_output.txt"); //check done
//		System.out.println(p5);
//		p5.solveBFS();
//		System.out.println("BFS results");
//		System.out.println(p5);
	
		Pacman p6 = new Pacman("demoMaze.txt", "demoMaze_output.txt"); //check done
//		System.out.println(p6);
//		p6.solveBFS();
//		System.out.println("BFS results");
//		System.out.println(p6);
	
		p6.solveDFS();
		System.out.println("DFS results");
		System.out.println(p6);
		
	}
	
	

}
