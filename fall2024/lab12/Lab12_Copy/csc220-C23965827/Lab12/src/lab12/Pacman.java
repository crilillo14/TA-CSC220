package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	}

	private boolean inMaze(int index, int bound){
		return index < bound && index >= 0;
	}

	/** helper method to construct the solution path from S to G
	 *  NOTE this path is built in reverse order, starting with the goal G.
	*/
	private void backtrack(Node end){
		// TODO for assignment12
		Node current = end; 
		while(current != null) {
			if(current.content != 'S' && current.content != 'G') {
				current.content = '.'; 
			}
			current = current.parent; 
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			output.println(height + " " + width);
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					output.print(maze[i][j].content);
				}
				output.println();
			}
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
			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));
			// FILL IN
			String[] dimensions = input.readLine().split(" ");
			height = Integer.parseInt(dimensions[0]);
			width = Integer.parseInt(dimensions[1]);
			maze = new Node[height][width]; 
			for(int i = 0; i < height; i++) {
				String line = input.readLine();
				for(int j = 0; j < width; j++) {
					char content = line.charAt(j); 
					maze[i][j] = new Node(i, j, content); 
					if(content == 'S') {
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
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> neighbors = new ArrayList<>(); 
		int row = currentNode.row; 
		int col = currentNode.col; 

		// 1. Inspect the north neighbor
		if (inMaze(row - 1, height) && !maze[row - 1][col].isWall() && !maze[row - 1][col].isVisited()) {
			north = maze[row - 1][col];
			north.parent = currentNode;
			neighbors.add(north); 
		}

		// 2. Inspect the south neighbor
		if (inMaze(row + 1, height) && !maze[row + 1][col].isWall() && !maze[row + 1][col].isVisited()) {
			south = maze[row + 1][col];
			south.parent = currentNode;
			neighbors.add(south); 
		}

		// 3. Inspect the west neighbor
		if (inMaze(col - 1, width) && !maze[row][col - 1].isWall() && !maze[row][col - 1].isVisited()) {
			west = maze[row][col - 1];
			west.parent = currentNode;
			neighbors.add(west); 
		}

		// 4. Inspect the east neighbor
		if (inMaze(col + 1, width) && !maze[row][col + 1].isWall() && !maze[row][col + 1].isVisited()) {
			east = maze[row][col + 1];
			east.parent = currentNode;
			neighbors.add(east); 
		}

		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		Queue<Node> queue = new LinkedList<>();
		Node start = maze[startX][startY];
		start.visited = true; 
		queue.add(start); 
		while(!queue.isEmpty()) {
			Node current = queue.poll(); 
			if(current.content == 'G') {
				backtrack(current); 
				writeOutput(); 
				return; 
			}
			for(Node neighbor : getNeighbors(current)) {
				neighbor.visited = true; 
				queue.add(neighbor); 
			}
		}
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		Stack<Node> stack = new Stack<>();
		Node start = maze[startX][startY];
		start.visited = true; 
		stack.push(start);
		while(!stack.isEmpty()) {
			Node current = stack.pop(); 
			if(current.content == 'G') {
				backtrack(current);
				writeOutput(); 
				return; 
			}
			for(Node neighbor : getNeighbors(current)) {
				neighbor.visited = true; 
				stack.push(neighbor); 
					
			}
		}
	}

}
