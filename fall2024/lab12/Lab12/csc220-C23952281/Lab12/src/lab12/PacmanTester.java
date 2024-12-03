package lab12;

public class PacmanTester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pacman p1 = new Pacman("classic.txt", "classic_BFSoutput.txt");
		p1.solveBFS();		
		
		Pacman p2 = new Pacman("classic.txt", "classic_DFSoutput.txt");
		p2.solveDFS();	
		
		Pacman p3 = new Pacman("bigMaze.txt", "bigMaze_BFSoutput.txt");
		p3.solveBFS();	
		
		Pacman p4 = new Pacman("bigMaze.txt", "bigMaze_DFSoutput.txt");
		p4.solveDFS();	
		
		Pacman p5 = new Pacman("demoMaze.txt", "demoMaze_BFSoutput.txt");
		p5.solveBFS();	
		
		Pacman p6 = new Pacman("demoMaze.txt", "demoMaze_DFSoutput.txt");
		p6.solveDFS();	
		
		Pacman p7 = new Pacman("mediumMaze.txt", "mediumMaze_BFSoutput.txt");
		p7.solveBFS();	
		
		Pacman p8 = new Pacman("mediumMaze.txt", "mediumMaze_DFSoutput.txt");
		p8.solveDFS();	
		
		Pacman p9 = new Pacman("randomMaze.txt", "randomMaze_BFSoutput.txt");
		p9.solveBFS();	
		
		Pacman p10 = new Pacman("randomMaze.txt", "randomMaze_DFSoutput.txt");
		p10.solveDFS();	
		
		Pacman p11 = new Pacman("straight.txt", "straight_BFSoutput.txt");
		p11.solveBFS();	
		
		Pacman p12 = new Pacman("straight.txt", "straight_DFSoutput.txt");
		p12.solveDFS();	
		
		Pacman p13 = new Pacman("tinyMaze.txt", "tinyMaze_BFSoutput.txt");
		p13.solveBFS();	
		
		Pacman p14 = new Pacman("tinyMaze.txt", "tinyMaze_DFSoutput.txt");
		p14.solveDFS();	
		
		Pacman p15 = new Pacman("tinyOpen.txt", "tinyOpen_BFSoutput.txt");
		p15.solveBFS();	
		
		Pacman p16 = new Pacman("tinyOpen.txt", "tinyOpen_DFSoutput.txt");
		p16.solveDFS();	
		
		Pacman p17 = new Pacman("turn.txt", "turn_BFSoutput.txt");
		p17.solveBFS();	
		
		Pacman p18 = new Pacman("turn.txt", "turn_DFSoutput.txt");
		p18.solveDFS();	
		
		Pacman p19 = new Pacman("unsolvable.txt", "unsolvable_BFSoutput.txt");
		p19.solveBFS();	
		
		Pacman p20 = new Pacman("unsolvable.txt", "unsolvable_DFSoutput.txt");
		p20.solveDFS();	
//		Pacman p2 = new Pacman("bigMaze.txt", "bigMaze_output.txt");
//		p2.writeOutput();
//		System.out.println(p2.toString());
		
		

	}

}
