package lab12;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pacman p1 = new Pacman("tinyMaze.txt", "tinyMaze_output.txt");
		System.out.println(p1);
		p1.writeOutput();

		Pacman p2 = new Pacman("classicBFSSol.txt", "classicBFSSol_output.txt");
		System.out.println(p2);
		p2.writeOutput();
		
		Pacman p3 = new Pacman("classicDFSSol.txt", "classicDFSSol_output.txt");
		System.out.println(p3);
		p3.writeOutput();
	}

}
