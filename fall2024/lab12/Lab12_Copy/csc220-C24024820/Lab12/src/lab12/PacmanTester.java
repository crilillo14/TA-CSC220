package lab12;

public class PacmanTester{
	public static void main(String[] args) {
//		Pacman p1=new Pacman("tinyMaze.txt","tinyMaze_output.txt");
//		p1.solveBFS();
//		Pacman p2=new Pacman("bigMaze.txt","bigMaze_output.txt");
//		System.out.println(p2.toString());
//		p2.solveBFS();
//		Pacman p3=new Pacman("classic.txt","classic_output.txt");
//		p3.writeOutput();
//		Pacman p4=new Pacman("demoMaze.txt","demoMaze_output.txt");
//		p4.writeOutput();
//		Pacman p5=new Pacman("mediumMaze.txt","mediumMaze_output.txt");
//		p5.writeOutput();
//		Pacman p6=new Pacman("randomMaze.txt","randomMaze_output.txt");
//		p6.writeOutput();
//		Pacman p7=new Pacman("straight.txt","straight_output.txt");
//		p7.writeOutput();
//		Pacman p8=new Pacman("tinyOpen.txt","tinyOpen_output.txt");
//		p8.writeOutput();
//		Pacman p9=new Pacman("turn.txt","turn_output.txt");
//		p9.writeOutput();
//		Pacman p10=new Pacman("unsolvable.txt","bigMaze_output.txt");
//		p10.writeOutput();
		
//		Pacman p1=new Pacman("tinyMaze.txt","tinyMaze_sol.txt");	
//		p1.solveBFS();
//		Pacman p2=new Pacman("bigMaze.txt","bigMaze_sol.txt");
//		p2.solveBFS();
//		Pacman p3=new Pacman("classic.txt","classic_sol.txt");
//		p3.solveBFS();
//		Pacman p4=new Pacman("demoMaze.txt","demoMaze_sol.txt");
//		p4.solveBFS();
//		Pacman p5=new Pacman("mediumMaze.txt","mediumMaze_sol.txt");
//		p5.solveBFS();
//		Pacman p6=new Pacman("randomMaze.txt","randomMaze_sol.txt");
//		p6.solveBFS();
//		Pacman p7=new Pacman("straight.txt","straight_sol.txt");
//		p7.solveBFS();
//		Pacman p8=new Pacman("tinyOpen.txt","tinyOpen_sol.txt");
//		p8.solveBFS();
//		Pacman p9=new Pacman("turn.txt","turn_sol.txt");
//		p9.solveBFS();
//		Pacman p10=new Pacman("unsolvable.txt","bigMaze_sol.txt");
//		p10.solveBFS();
		
		Pacman p1=new Pacman("tinyMaze.txt","tinyMaze_DFSsol.txt");	
		p1.solveDFS();
		Pacman p2=new Pacman("bigMaze.txt","bigMaze_DFSsol.txt");
		p2.solveDFS();
		Pacman p3=new Pacman("classic.txt","classic_DFSsol.txt");
		p3.solveDFS();
		Pacman p4=new Pacman("demoMaze.txt","demoMaze_DFSsol.txt");
		p4.solveDFS();
		Pacman p5=new Pacman("mediumMaze.txt","mediumMaze_DFSsol.txt");
		p5.solveDFS();
		Pacman p6=new Pacman("randomMaze.txt","randomMaze_DFSsol.txt");
		p6.solveDFS();
		Pacman p7=new Pacman("straight.txt","straight_DFSsol.txt");
		p7.solveDFS();
		Pacman p8=new Pacman("tinyOpen.txt","tinyOpen_DFSsol.txt");
		p8.solveDFS();
		Pacman p9=new Pacman("turn.txt","turn_DFSsol.txt");
		p9.solveDFS();
		Pacman p10=new Pacman("unsolvable.txt","bigMaze_DFSsol.txt");
		p10.solveDFS();
	}
}