package lab12;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Pacman p1 = new Pacman("turnBFSSol.txt", "turnBFSSol_output.txt");
        System.out.println(p1);
        p1.writeOutput();
	}

}
