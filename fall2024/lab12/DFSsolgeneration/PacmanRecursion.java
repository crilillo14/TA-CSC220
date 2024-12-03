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

public class PacmanRecursion {

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
		Node curr = end;
		while (curr != null && curr.content != 'S') {
			if (curr.content != 'G') {
				curr.content = '.';
			}
			curr = curr.parent;
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
//			this.solveBFS();
//			this.solveDFS();

			String mazeOutput = "";

			// add width and height
			mazeOutput += this.height + " " + this.width + "\n";

			// add each thing in maze
			for (int row = 0; row < this.height; row++) {
				for (int column = 0; column < this.width; column++) {
					mazeOutput += this.maze[row][column].content;
				}
				if (row != this.height - 1) {
					mazeOutput += "\n";
				}
			}

			output.write(mazeOutput);
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
			// dont worry i did!
			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));

			String line = input.readLine();

			// initializing the maze and shit
			this.height = Integer.parseInt(line.split(" ")[0]);
			this.width = Integer.parseInt(line.split(" ")[1]);

			this.maze = new Node[this.height][this.width];

			// time to loop through the ting big man
			line = input.readLine();

			int row = 0;

			while (line != null) {
				// now add stuff to maze
				for (int column = 0; column < line.length(); column++) {
					this.maze[row][column] = new Node(row, column, line.charAt(column));

					if (line.charAt(column) == 'S') {
						this.startX = row;
						this.startY = column;
					}
				}

				row += 1;
				line = input.readLine();
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
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> neighbors = new ArrayList<Node>();
		int x = currentNode.row;
		int y = currentNode.col;

		// 1. Inspect the north neighbor
		if (this.inMaze(x, this.height) && this.inMaze(y + 1, this.width)) {
			north = this.maze[x][y + 1];
		}
		else {
			north = null;
		}
		neighbors.add(north);

		// 2. Inspect the south neighbor
		if (this.inMaze(x, this.height) && this.inMaze(y - 1, this.width)) {
			south = this.maze[x][y - 1];
		}
		else {
			south = null;
		}
		neighbors.add(south);

		// 3. Inspect the west neighbor
		if (this.inMaze(x - 1, this.height) && this.inMaze(y, this.width)) {
			west = this.maze[x - 1][y];
		}
		else {
			west = null;
		}
		neighbors.add(west);

		// 4. Inspect the east neighbor
		if (this.inMaze(x + 1, this.height) && this.inMaze(y, this.width)) {
			east = this.maze[x + 1][y];
		}
		else {
			east = null;
		}
		neighbors.add(east);

		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12

		// get start
		Node currentNode = this.maze[this.startX][this.startY];

		BFS(currentNode);

	}

	public void BFS(Node curr) {
		curr.visited = true;

		LinkedList<Node> queue = new LinkedList<Node>();

		queue.add(curr);

		while (!queue.isEmpty() && curr.content != 'G') {
			curr = queue.poll();

			for (Node next : this.getNeighbors(curr)) {
				if (next != null && !next.isWall() && !next.isVisited()) {
					next.visited = true;
					next.parent = curr;
					queue.add(next);
				}
			}
		}

		// if theres no path
		if (queue.isEmpty() && curr.content != 'G') {
			this.writeOutput();
		}
		else { // there is path found
			this.backtrack(curr);
			this.writeOutput();
		}
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12

		// make start node
		Node currentNode = this.maze[this.startX][this.startY];

		DFS(currentNode);

		this.writeOutput();

	}

	public void DFS(Node curr) {

		curr.visited = true;
		if (curr.content == 'G') {
			this.backtrack(curr);
			return;
		}

		for (Node next : this.getNeighbors(curr)) {

			if (next != null && !next.isWall() && !next.isVisited()) {
				next.parent = curr;
				DFS(next);
			}

		}

	}

}

















