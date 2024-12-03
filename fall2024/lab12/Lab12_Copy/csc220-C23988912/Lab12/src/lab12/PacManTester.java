package lab12;

public class PacManTester {

public static void main(String[] args) {

	
	
	
	// Test case 1: Tiny Maze
    Pacman test1 = new Pacman("mazes/tinyMaze.txt", "output/tinyMazeBFS.txt");
    test1.solveBFS();
    Pacman test1DFS = new Pacman("mazes/tinyMaze.txt", "output/tinyMazeDFS.txt");
    test1DFS.solveDFS();

    // Test case 2: Big Maze
    Pacman test2 = new Pacman("mazes/bigMaze.txt", "output/bigMazeBFS.txt");
    test2.solveBFS();
    Pacman test2DFS = new Pacman("mazes/bigMaze.txt", "output/bigMazeDFS.txt");
    test2DFS.solveDFS();

    // Test case 3: Classic Maze
    Pacman test3 = new Pacman("mazes/classic.txt", "output/classicMazeBFS.txt");
    test3.solveBFS();
    Pacman test3DFS = new Pacman("mazes/classic.txt", "output/classicMazeDFS.txt");
    test3DFS.solveDFS();

    // Test case 4: Demo Maze
    Pacman test4 = new Pacman("mazes/demoMaze.txt", "output/demoMazeBFS.txt");
    test4.solveBFS();
    Pacman test4DFS = new Pacman("mazes/demoMaze.txt", "output/demoMazeDFS.txt");
    test4DFS.solveDFS();

    // Test case 5: Medium Maze
    Pacman test5 = new Pacman("mazes/mediumMaze.txt", "output/mediumMazeBFS.txt");
    test5.solveBFS();
    Pacman test5DFS = new Pacman("mazes/mediumMaze.txt", "output/mediumMazeDFS.txt");
    test5DFS.solveDFS();

    // Test case 6: Random Maze
    Pacman test6 = new Pacman("mazes/randomMaze.txt", "output/randomMazeBFS.txt");
    test6.solveBFS();
    Pacman test6DFS = new Pacman("mazes/randomMaze.txt", "output/randomMazeDFS.txt");
    test6DFS.solveDFS();

    // Test case 7: Straight Maze
    Pacman test7 = new Pacman("mazes/straight.txt", "output/straightMazeBFS.txt");
    test7.solveBFS();
    Pacman test7DFS = new Pacman("mazes/straight.txt", "output/straightMazeDFS.txt");
    test7DFS.solveDFS();

    // Test case 8: Unsolvable Maze
    Pacman test8 = new Pacman("mazes/unsolvable.txt", "output/unsolvableMazeBFS.txt");
    test8.solveBFS();
    Pacman test8DFS = new Pacman("mazes/unsolvable.txt", "output/unsolvableMazeDFS.txt");
    test8DFS.solveDFS();
	
}
}
