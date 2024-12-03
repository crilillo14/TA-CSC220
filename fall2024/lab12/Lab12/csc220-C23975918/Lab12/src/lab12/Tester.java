package lab12;

public class Tester{
	
	public static void main(String[] args) {
		
		System.out.println("Testing DFS on bigMaze:");
        Pacman dfsBigMaze = new Pacman("bigMaze.txt", "bigMazeDFSSol_test.txt");
        dfsBigMaze.solveDFS();
        System.out.println(dfsBigMaze); 
        
        System.out.println("Testing BFS on bigMaze:");
        Pacman bfsBigMaze = new Pacman("bigMaze.txt", "bigMazeBFSSol_test.txt");
        bfsBigMaze.solveDFS();
        System.out.println(bfsBigMaze);
        
        System.out.println("Testing DFS on classic:");
        Pacman dfsClassic = new Pacman("classic.txt", "classicDFSSol_test.txt");
        dfsClassic.solveDFS();
        System.out.println(dfsClassic); 
        
        System.out.println("Testing BFS on classic:");
        Pacman bfsClassic = new Pacman("classic.txt", "classicBFSSol_test.txt");
        bfsClassic.solveBFS();
        System.out.println(dfsClassic); 
        
        System.out.println("Testing DFS on demoMaze:");
        Pacman dfsDemoMaze = new Pacman("demoMaze.txt", "demoMazeDFSSol_test.txt");
        dfsDemoMaze.solveDFS();
        System.out.println(dfsDemoMaze);
        
        System.out.println("Testing BFS on demoMaze:");
        Pacman bfsDemoMaze = new Pacman("demoMaze.txt", "demoMazeBFSSol_test.txt");
        bfsDemoMaze.solveDFS();
        System.out.println(bfsDemoMaze);
        
        System.out.println("Testing BFS on mediumMaze:");
        Pacman bfsMediumMaze = new Pacman("mediumMaze.txt", "mediumMazeBFSSol_test.txt");
        bfsMediumMaze.solveBFS();
        System.out.println(bfsMediumMaze); 
        
        System.out.println("Testing DFS on mediumMaze:");
        Pacman dfsMediumMaze = new Pacman("mediumMaze.txt", "mediumMazeDFSSol_test.txt");
        dfsMediumMaze.solveDFS();
        System.out.println(dfsMediumMaze); 
        
        System.out.println("Testing BFS on randomMaze:");
        Pacman bfsRandomMaze = new Pacman("randomMaze.txt", "randomMazeBFSSol_test.txt");
        bfsRandomMaze.solveBFS();
        System.out.println(bfsRandomMaze); 
        
        System.out.println("Testing DFS on randomMaze:");
        Pacman dfsRandomMaze = new Pacman("randomMaze.txt", "randomMazeDFSSol_test.txt");
        dfsRandomMaze.solveBFS();
        System.out.println(dfsRandomMaze);
        
        System.out.println("Testing BFS on straight:");
        Pacman bfsStraight = new Pacman("straight.txt", "straightBFSSol_test.txt");
        bfsStraight.solveBFS();
        System.out.println(bfsStraight); 
        
        System.out.println("Testing DFS on straight:");
        Pacman dfsStraight = new Pacman("straight.txt", "straightDFSSol_test.txt");
        dfsStraight.solveDFS();
        System.out.println(dfsStraight); 
        
        System.out.println("Testing BFS on tinyMaze:");
        Pacman bfsTinyMaze = new Pacman("tinyMaze.txt", "tinyMazeBFSSol_test.txt");
        bfsTinyMaze.solveBFS();
        System.out.println(bfsTinyMaze); 

        System.out.println("\nTesting DFS on tinyMaze:");
        Pacman dfsTinyMaze = new Pacman("tinyMaze.txt", "tinyMazeDFSSol_test.txt");
        dfsTinyMaze.solveDFS();
        System.out.println(dfsTinyMaze); 
        
        System.out.println("Testing BFS on tinyOpen:");
        Pacman bfsTinyOpen = new Pacman("tinyOpen.txt", "tinyOpenBFSSol_test.txt");
        bfsTinyOpen.solveBFS();
        System.out.println(bfsTinyOpen); 

        System.out.println("Testing DFS on tinyOpen:");
        Pacman dfsTinyOpen = new Pacman("tinyOpen.txt", "tinyOpenDFSSol_test.txt");
        dfsTinyOpen.solveDFS();
        System.out.println(dfsTinyOpen); 
        
        System.out.println("Testing BFS on turn:");
        Pacman bfsTurn = new Pacman("turn.txt", "turnBFSSol_test.txt");
        bfsTurn.solveBFS();
        System.out.println(bfsTurn); 
        
        System.out.println("Testing DFS on turn:");
        Pacman dfsTurn = new Pacman("turn.txt", "turnDFSSol_test.txt");
        dfsTurn.solveBFS();
        System.out.println(dfsTurn); 
        
	}
}

