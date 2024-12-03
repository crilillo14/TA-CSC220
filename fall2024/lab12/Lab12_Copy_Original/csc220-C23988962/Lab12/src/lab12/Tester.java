package lab12;

public class Tester {
	
	public static void main(String[] args) {
		Pacman p1 = new Pacman("tinyMaze.txt", "tinyMaze_output.txt");
		System.out.println(p1);
		p1.writeOutput();
		
        Pacman dfsBigMaze = new Pacman("bigMaze.txt", "bigMazeDFSSol_test.txt");
        dfsBigMaze.solveDFS();
        System.out.println(dfsBigMaze); 
        
        Pacman bfsBigMaze = new Pacman("bigMaze.txt", "bigMazeBFSSol_test.txt");
        bfsBigMaze.solveDFS();
        System.out.println(bfsBigMaze);
        
        Pacman dfsClassic = new Pacman("classic.txt", "classicDFSSol_test.txt");
        dfsClassic.solveDFS();
        System.out.println(dfsClassic); 
        
        Pacman bfsClassic = new Pacman("classic.txt", "classicBFSSol_test.txt");
        bfsClassic.solveBFS();
        System.out.println(dfsClassic); 
        
        Pacman dfsDemoMaze = new Pacman("demoMaze.txt", "demoMazeDFSSol_test.txt");
        dfsDemoMaze.solveDFS();
        System.out.println(dfsDemoMaze);
        
        Pacman bfsDemoMaze = new Pacman("demoMaze.txt", "demoMazeBFSSol_test.txt");
        bfsDemoMaze.solveDFS();
        System.out.println(bfsDemoMaze);
        
        Pacman bfsMediumMaze = new Pacman("mediumMaze.txt", "mediumMazeBFSSol_test.txt");
        bfsMediumMaze.solveBFS();
        System.out.println(bfsMediumMaze); 
        
        Pacman dfsMediumMaze = new Pacman("mediumMaze.txt", "mediumMazeDFSSol_test.txt");
        dfsMediumMaze.solveDFS();
        System.out.println(dfsMediumMaze); 

        Pacman bfsRandomMaze = new Pacman("randomMaze.txt", "randomMazeBFSSol_test.txt");
        bfsRandomMaze.solveBFS();
        System.out.println(bfsRandomMaze); 
        
        Pacman dfsRandomMaze = new Pacman("randomMaze.txt", "randomMazeDFSSol_test.txt");
        dfsRandomMaze.solveBFS();
        System.out.println(dfsRandomMaze);
        
        Pacman bfsStraight = new Pacman("straight.txt", "straightBFSSol_test.txt");
        bfsStraight.solveBFS();
        System.out.println(bfsStraight); 
        
        Pacman dfsStraight = new Pacman("straight.txt", "straightDFSSol_test.txt");
        dfsStraight.solveDFS();
        System.out.println(dfsStraight); 

        Pacman bfsTinyMaze = new Pacman("tinyMaze.txt", "tinyMazeBFSSol_test.txt");
        bfsTinyMaze.solveBFS();
        System.out.println(bfsTinyMaze); 

        Pacman dfsTinyMaze = new Pacman("tinyMaze.txt", "tinyMazeDFSSol_test.txt");
        dfsTinyMaze.solveDFS();
        System.out.println(dfsTinyMaze); 
        
        Pacman bfsTinyOpen = new Pacman("tinyOpen.txt", "tinyOpenBFSSol_test.txt");
        bfsTinyOpen.solveBFS();
        System.out.println(bfsTinyOpen); 

        Pacman dfsTinyOpen = new Pacman("tinyOpen.txt", "tinyOpenDFSSol_test.txt");
        dfsTinyOpen.solveDFS();
        System.out.println(dfsTinyOpen);
        
        Pacman bfsTurn = new Pacman("turn.txt", "turnBFSSol_test.txt");
        bfsTurn.solveBFS();
        System.out.println(bfsTurn); 
        
        Pacman dfsTurn = new Pacman("turn.txt", "turnDFSSol_test.txt");
        dfsTurn.solveBFS();
        System.out.println(dfsTurn);
        
	}
	
}
