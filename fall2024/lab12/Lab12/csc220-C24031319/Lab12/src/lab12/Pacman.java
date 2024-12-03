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
	public Node[][] maze; //made public for testing *switch before submitting*
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
		Node n = end.parent;
		while(n != maze[startX][startY]) {
			n.content = '.';
			n = n.parent;
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			//print the dimensions (1st row)
			String h = Integer.toString(height);
			String w = Integer.toString(width);
			output.write(h);
			output.write(" ");
			output.write(w);
			output.write('\n');
			
			//iterate through the maze[][]
			for (int row = 0; row < this.height;row++) {
				for (int col = 0; col < this.width; col++) {
					output.write(this.maze[row][col].content);
				}
				output.write('\n');
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
			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));
			// FILL IN
			//find dimensions (1st line - 3 characters)
			String ln = input.readLine();
			String[] row1 = ln.split(" ");
			this.height = Integer.parseInt(row1[0]);
			this.width = Integer.parseInt(row1[1]);
			this.maze = new Node[height][width];
			
			//iterate through the rest of the textfile;
			for (int row = 0; row < this.height;row++) { //rows
				String line = input.readLine();
				for (int col = 0; col < line.length(); col++) { //col
					Node newNode = new Node(row, col, line.charAt(col));
					this.maze[row][col] = newNode;
					if (line.charAt(col) == 'S') { 
						this.startX = row;
						this.startY = col;
					}
				}
			}
			input.close();
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
		int currC = currentNode.col;
		int currR = currentNode.row;
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> neighbors = new ArrayList<Node>();
		// 1. Inspect the north neighbor -- same column, row - 1
		north = maze[currR-1][currC];
		if (north.content != 'X' && !north.visited) {
			neighbors.add(north);
		}
		// 2. Inspect the south neighbor -- same column, row + 1
		south = maze[currR+1][currC];
		if (south.content != 'X' && !south.visited) {
			neighbors.add(south);
		}
		// 3. Inspect the west neighbor -- column - 1, same row
		west = maze[currR][currC-1];
		if (west.content != 'X' && !west.visited) {
			neighbors.add(west);
		}
		// 4. Inspect the east neighbor -- column + 1, same row
		east = maze[currR][currC+1];
		if (east.content != 'X' && !east.visited) {
			neighbors.add(east);
		}
		
		

		return neighbors;
	}
	

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		Queue<Node> q = new LinkedList<>();
		Node start = maze[startX][startY];
		start.visited = true;
		q.add(start);
		Node curr;
		while(!q.isEmpty()) {
			
			curr = q.remove();
			
			if (curr.content == 'G') {
				backtrack(curr);
				return;
			}
			for (Node i : getNeighbors(curr)) {
				if (!i.visited) {
					i.visited = true;
					i.parent = curr;
					q.add(i);
				}	
			}
		}
	}

	/** Pacman uses DFS strategy to solve the maze */
	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
	    //System.out.println("Starting DFS from: " + startX + ", " + startY);
	    DFS(maze[startX][startY]);
	}

	private void DFS(Node curr) {
	    //System.out.println("Visiting Node: (" + curr.row + ", " + curr.col + ")");

	    // Mark the current node as visited
	    curr.visited = true;

	    // Check if the goal is found
	    if (curr.content == 'G') {
	        //System.out.println("Goal found at: (" + curr.row + ", " + curr.col + ")");
	        backtrack(curr);
	        return;
	    }

	    // Iterate through neighbors
	    for (Node neighbor : getNeighbors(curr)) {
	        //System.out.println("Checking neighbor: (" + neighbor.row + ", " + neighbor.col + ")");
            neighbor.parent = curr;
            DFS(neighbor);

	    }
	}


}
