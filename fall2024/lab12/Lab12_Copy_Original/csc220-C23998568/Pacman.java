package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class Pacman {

	/** representation of the graph as a 2D array */
	private Node[][] maze;
	/** input file name */
	private String inputFileName;
	/** output file name */
	private String outputFileName;
	/** height and width of the maze */
	private int height;
	private int width;
	/** starting (X,Y) position of Pacman */
	private int startX;
	private int startY;

	/** A Node is the building block for a Graph. */
	private static class Node {
		/** the content at this location */
	    private char content;
	    /** the row where this location occurs */
	    private int row;
	    /** the column where this location occurs */
	    private int col;
		private boolean visited;
		private Node parent;

	    public Node(int x, int y, char c) {
	        visited = false;
	        content = c;
	        parent =  null;
	        this.row = x;
	        this.col = y;
	    }

	    public boolean isWall() { return content == 'X'; }
	    public boolean isVisited() { return visited; }
	}

	/** constructor */
	public Pacman(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		buildGraph();
		
		
		//This test the neighbors returned array
		/*
		ArrayList<Node> test = new ArrayList<Node>();
		test = getNeighbors(maze[startX][startY]);
		for(int i = 0; i < 4; i++) {
			System.out.print(test.get(i).content + ",");
			
		}
		System.out.println(maze[startX - 1][startY].visited);
		*/
	}
	

	private boolean inMaze(int index, int bound){
		return index < bound && index >= 0;
	}

	/** helper method to construct the solution path from S to G
	 *  NOTE this path is built in reverse order, starting with the goal G.
	*/
	private void backtrack(Node end){
		Node end1 = end.parent;
		
		while(end1.row != startX || end1.col != startY) {
			end1.content = '.';
			end1 = end1.parent;
		}
			
	}
	

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			output.println(height + " " + width);
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					output.print(maze[i][j].content);	
				}
				output.println();
			}
			output.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		String s = "";
		s += height + " " + width + "\n";
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				s += maze[i][j].content + " ";
			}
			s += "\n";
		}
		return s;
	}

	/** helper method to construct a graph from a given input file;
	 *  all member variables (e.g. maze, startX, startY) should be
	 *  initialized by the end of this method
	 */
	private void buildGraph() {
		// TODO for lab12
		try {
			// don't forget to close input when you're done
			BufferedReader input = new BufferedReader(new FileReader(inputFileName));
			// FILL IN
			String row1 = input.readLine();
			String[] row1Array = row1.split(" ");
			height = Integer.parseInt(row1Array[0]);
			width = Integer.parseInt(row1Array[1]);
			
			maze = new Node[height][width];
			
			
			for (int i = 0; i < height; i++) {
				String rowRead = input.readLine();
				for (int j = 0; j < width; j++) {
					char x =  rowRead.charAt(j);
					maze[i][j] = new Node(i, j, x);
					
					if(x == 'S') {
						startX = i; 
						startY = j;
					}
					
				}
			}
		
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** obtains all neighboring nodes that have *not* been
	 *  visited yet from a given node when looking at the four
	 *  cardinal directions: North, South, West, East (IN THIS ORDER!)
	 *
	 * @param currentNode the given node
	 * @return an ArrayList of the neighbors (i.e., successors)
	 */
	public ArrayList<Node> getNeighbors(Node currentNode) {
		// TODO for assignment12
		Node north, south, east, west;
		int x = currentNode.row;
		int y = currentNode.col;
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> neighbors = new ArrayList<Node>();
		// 1. Inspect the north neighbor
		if(x != 0 && !(maze[x -1][y].visited)) {
			maze[x -1][y].visited = true;
			maze[x -1][y].parent = currentNode;
			north = maze[x -1][y];
			neighbors.add(north);
		}
		// 2. Inspect the south neighbor
		if(x < height && !(maze[x + 1][y].visited)) {
			maze[x + 1][y].visited = true;
			maze[x + 1][y].parent = currentNode;
			south = maze[x + 1][y];
			neighbors.add(south);
		}
		// 3. Inspect the west neighbor	
		if(y != 0 && !(maze[x][y -1].visited)) {
			maze[x][y -1].visited = true;
			maze[x][y -1].parent = currentNode;
			west = maze[x][y -1];
			neighbors.add(west);
		}
		// 4. Inspect the east neighbor
		if(y < width && !(maze[x][y + 1].visited)) {
			maze[x][y +1].visited = true;
			maze[x][y +1].parent = currentNode;
			east = maze[x][y +1];
			neighbors.add(east);
		}
		
		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		Queue<Node> nodeQueue = new LinkedList<>();
		helperBFS(maze[startX][startY],nodeQueue);
		
	}
	
	public void helperBFS(Node currentNode, Queue<Node> nodeQueue) {
		if(currentNode.content == 'G') {
			backtrack(currentNode);
			return;
		}
	
		ArrayList<Node> test = getNeighbors(currentNode);
	
		for(int i = 0; i < test.size(); i++) {
			if(test.get(i).content != 'X') {
				nodeQueue.add(test.get(i));
			}
		}
		
		helperBFS(nodeQueue.poll(), nodeQueue);
		
	}
	

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		Stack<Node> nodeStack = new Stack<>();
		helperDFS(maze[startX][startY],nodeStack);
	}



public void helperDFS(Node currentNode, Stack<Node> nodeStack) {
	if(currentNode.content == 'G') {
		backtrack(currentNode);
		return;
	}

	ArrayList<Node> test = getNeighbors(currentNode);

	for(int i = 0; i < test.size(); i++) {
		if(test.get(i).content != 'X') {
			nodeStack.add(test.get(i));
		}
	}
	
	helperDFS(nodeStack.pop(), nodeStack);
	
}
}




