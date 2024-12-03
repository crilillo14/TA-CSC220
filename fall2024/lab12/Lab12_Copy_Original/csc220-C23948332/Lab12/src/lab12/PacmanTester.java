package lab12;

public class PacmanTester {
	
	public static void main(String[] args) {
		// TO DO auto generated methis stub
		
		Pacman p1 = new Pacman("tinyMaze.txt" , "tinyMaze_output.txt") ; 
		System.out.println(p1);
		p1.writeOutput();
			

	
		Pacman p2 = new Pacman("classicBFSSol.txt" , "classicBFSSol_output.txt") ; 
		System.out.println(p2);
		p2.writeOutput();
		
		Pacman p3 = new Pacman("classicDFSSol.txt" , "classicDFSSol_output.txt") ; 
		System.out.println(p3);
		p3.writeOutput();
		
		Pacman p4 = new Pacman("demoMaze.txt" , "demoMaze_output.txt") ; 
		System.out.println(p4);
		p4.writeOutput();
		
		
		
		
}
	
	
}

	

