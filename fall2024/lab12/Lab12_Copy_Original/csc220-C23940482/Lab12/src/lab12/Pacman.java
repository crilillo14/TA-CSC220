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
		private boolean visited; // Indicates if the node has been visited
		private Node parent; // Reference to the parent node for backtracking

	    public Node(int x, int y, char c) {
	        visited = false;
	        content = c;
	        parent = null;
	        this.row = x;
	        this.col = y;
	    }

	    // Checks if the node is a wall
	    public boolean isWall() { return content == 'X'; }
	    // Checks if the node has been visited
	    public boolean isVisited() { return visited; }
	}

	/** constructor */
	public Pacman(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		buildGraph(); // Builds the maze (graph) based on input
	}

	// Helper method to check if an index is within maze bounds
	private boolean inMaze(int index, int bound){
		return index < bound && index >= 0;
	}

	/** Helper method to construct the solution path from S to G.
	 *  This path is built in reverse order, starting with the goal node.
	 */
	private void backtrack(Node end){
		// Start from the goal node and traverse backwards using parent references
		Node curr = end;
		
		while (curr.parent != null) {
			// Mark the path with '.'
			if (curr.content == ' ') {
				curr.content = '.';
			}
			
			// Move to the parent node
			curr = curr.parent;
		}
	}

	/** Writes the solved maze with the path to an output file */
	public void writeOutput() {
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// Write maze dimensions as the first line
			String top = this.height + " " + this.width;
			output.println(top);
			
			// Write each row of the maze
			for (int i = 0; i < this.height; i++) {
				for (int j = 0; j < this.width; j++) {
					output.print(this.maze[i][j].content);
				}
				output.println(); // Move to the next line
			}
			
			output.flush();
			output.close(); // Close the writer
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	// Provides a string representation of the maze
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

	/** Constructs the maze (graph) from the input file.
	 *  Initializes the maze and other member variables.
	 */
	private void buildGraph() {
		try {
			// Read input file
			BufferedReader input = new BufferedReader(new FileReader(inputFileName));
			
			String s = input.readLine();
			
			// Parse the dimensions of the maze
			int space_idx = s.indexOf((int)' ');
			this.height = Integer.parseInt(s.substring(0, space_idx));
			this.width = Integer.parseInt(s.substring(space_idx + 1));
			
			// Initialize the maze array
			this.maze = new Node[this.height][this.width];
			
			for (int i = 0; i < this.height; i++) {
				for (int j = 0; j < this.width; j++) {
					char cell = (char) input.read(); // Read cell character
					
					// Create a new node for each cell
					this.maze[i][j] = new Node(i, j, cell);
					
					// Track the start position
					if (cell == 'S') {
						this.startX = i;
						this.startY = j;
					}
				}
				input.read(); // Consume newline
			}
			
			input.close(); // Close the file
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Gets all unvisited neighbors of a node in the four cardinal directions:
	 *  North, South, West, and East (in that order).
	 */
	public ArrayList<Node> getNeighbors(Node currentNode) {
		ArrayList<Node> out = new ArrayList<Node>();
		
		// Inspect the north neighbor
		if (currentNode.row > 0) {
			Node north = this.maze[currentNode.row - 1][currentNode.col];
			if (!north.isWall() && !north.visited) out.add(north);
		}
		
		// Inspect the south neighbor
		if (currentNode.row < this.height - 1) {
			Node south = this.maze[currentNode.row + 1][currentNode.col];
			if (!south.isWall() && !south.visited) out.add(south);
		}
		
		// Inspect the west neighbor
		if (currentNode.col > 0) {
			Node west = this.maze[currentNode.row][currentNode.col - 1];
			if (!west.isWall() && !west.visited) out.add(west);
		}
		
		// Inspect the east neighbor
		if (currentNode.col < this.width - 1) {
			Node east = this.maze[currentNode.row][currentNode.col + 1];
			if (!east.isWall() && !east.visited) out.add(east);
		}
		return out;
	}

	/** Solves the maze using BFS (Breadth-First Search) */
	public void solveBFS() {
		LinkedList<Node> q = new LinkedList<Node>();
		Node start = this.maze[this.startX][this.startY];
		
		start.visited = true; // Mark start node as visited
		q.add(start); // Enqueue the start node
		
		while (!q.isEmpty()) {
			Node curr = q.poll(); // Dequeue the front node
			ArrayList<Node> neighbors = this.getNeighbors(curr);
			
			// Visit each neighbor
			for (Node neighbor : neighbors) {
				neighbor.parent = curr; // Set parent for backtracking
				neighbor.visited = true;
				
				// If goal is reached, backtrack to mark the path
				if (neighbor.content == 'G') {
					this.backtrack(neighbor);
					return;
				}
				
				q.add(neighbor); // Enqueue the neighbor
			}
		}
	}

	/** Solves the maze using DFS (Depth-First Search) */
	public void solveDFS() {
		Stack<Node> stack = new Stack<Node>();
		Node start = this.maze[this.startX][this.startY];
		
		start.visited = true; // Mark start node as visited
		stack.push(start); // Push start node onto the stack
		
		while (!stack.isEmpty()) {
			Node curr = stack.pop(); // Pop the top node
			ArrayList<Node> neighbors = this.getNeighbors(curr);
			
			// Visit each neighbor
			for (Node neighbor : neighbors) {
				neighbor.parent = curr; // Set parent for backtracking
				neighbor.visited = true;
				
				// If goal is reached, backtrack to mark the path
				if (neighbor.content == 'G') {
					this.backtrack(neighbor);
					return;
				}
				
				stack.push(neighbor); // Push the neighbor onto the stack
			}
		}
	}
}
