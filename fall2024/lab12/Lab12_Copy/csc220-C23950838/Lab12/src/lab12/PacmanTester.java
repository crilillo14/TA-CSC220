package lab12;

public class PacmanTester {
	
	public static void main(String[] args) {
		
		Pacman p1 = new Pacman("turn.txt", "AAAAAAAAAAAAA.txt");
		//p1.solveBFS();
		p1.solveDFS();
		
		Pacman p2 = new Pacman("turnDFSSol.txt", "AAAAAAAAAAAAA.txt");
		
		if (p1.toString().equals(p2.toString())) {
			System.out.println("Success");
		}
		
		// Tests passed for all files
		
	}
}
