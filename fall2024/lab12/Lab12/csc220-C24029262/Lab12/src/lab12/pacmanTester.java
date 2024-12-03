package lab12;

public class pacmanTester {
public static void main(String[] args) {
	Pacman p = new Pacman("mediumMaze.txt","out.txt" );
	
	System.out.println(p);
	p.solveDFS();
	System.out.println(p);
	
	
	p.writeOutput();
	
}
}
