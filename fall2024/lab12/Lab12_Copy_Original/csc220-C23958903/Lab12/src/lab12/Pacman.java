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
	private void backtrack(Node end, Node[][] mazeCopy){
		Node current = end;
		while (current != null && current.content != 'S') {
			if (current.content == ' ') {
				mazeCopy[current.row][current.col].content = '.';
			}
			current = current.parent;
		}
	}

	/** writes the solution to file */
	public void writeOutput(Node[][] mazeToWrite, String outputFileName) {
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			output.println(height + " " + width);
			for (int i = 0; i < height; i++){
				for (int j = 0; j < width; j++){
					output.print(mazeToWrite[i][j].content);
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
				s += maze[i][j].content;
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
		try {
			BufferedReader input = new BufferedReader(new FileReader(inputFileName));
			String[] dimensions = input.readLine().split(" ");
			height = Integer.parseInt(dimensions[0]);
			width = Integer.parseInt(dimensions[1]);
			maze = new Node[height][width];

			for (int i = 0; i < height; i++) {
				String line = input.readLine();
				for (int j = 0; j < width; j++) {
					maze[i][j] = new Node(i, j, line.charAt(j));
					if (line.charAt(j) == 'S') {
						startX = i;
						startY = j;
					}
				}
			}
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** helper method to make a deep copy of the maze */
	private Node[][] copyMaze() {
		Node[][] copy = new Node[height][width];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Node original = maze[i][j];
				copy[i][j] = new Node(original.row, original.col, original.content);
				copy[i][j].visited = original.visited;
				copy[i][j].parent = original.parent;
			}
		}
		return copy;
	}

	/** obtains all neighboring nodes that have *not* been
	 *  visited yet from a given node when looking at the four
	 *  cardinal directions: North, South, West, East (IN THIS ORDER!)
	 *
	 * @param currentNode the given node
	 * @param mazeCopy the maze copy used for this particular run
	 * @return an ArrayList of the neighbors (i.e., successors)
	 */
	public ArrayList<Node> getNeighbors(Node currentNode, Node[][] mazeCopy) {
		ArrayList<Node> neighbors = new ArrayList<>();
		int row = currentNode.row;
		int col = currentNode.col;

		// North
		if (inMaze(row - 1, height) && !mazeCopy[row - 1][col].isWall() && !mazeCopy[row - 1][col].isVisited()) {
			neighbors.add(mazeCopy[row - 1][col]);
		}
		// South
		if (inMaze(row + 1, height) && !mazeCopy[row + 1][col].isWall() && !mazeCopy[row + 1][col].isVisited()) {
			neighbors.add(mazeCopy[row + 1][col]);
		}
		// West
		if (inMaze(col - 1, width) && !mazeCopy[row][col - 1].isWall() && !mazeCopy[row][col - 1].isVisited()) {
			neighbors.add(mazeCopy[row][col - 1]);
		}
		// East
		if (inMaze(col + 1, width) && !mazeCopy[row][col + 1].isWall() && !mazeCopy[row][col + 1].isVisited()) {
			neighbors.add(mazeCopy[row][col + 1]);
		}

		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		Node[][] mazeCopy = copyMaze(); // Use a fresh copy of the maze
		resetVisited(mazeCopy);
		Queue<Node> queue = new LinkedList<>();
		Node start = mazeCopy[startX][startY];
		start.visited = true;
		queue.add(start);

		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (current.content == 'G') {
				backtrack(current, mazeCopy);
				writeOutput(mazeCopy, outputFileName.replace(".txt", "BFSOutput.txt"));
				return;
			}
			for (Node neighbor : getNeighbors(current, mazeCopy)) {
				neighbor.visited = true;
				neighbor.parent = current;
				queue.add(neighbor);
			}
		}
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		Node[][] mazeCopy = copyMaze(); // Use a fresh copy of the maze
		resetVisited(mazeCopy);
		Stack<Node> stack = new Stack<>();
		Node start = mazeCopy[startX][startY];
		start.visited = true;
		stack.push(start);

		while (!stack.isEmpty()) {
			Node current = stack.pop();
			if (current.content == 'G') {
				backtrack(current, mazeCopy);
				writeOutput(mazeCopy, outputFileName.replace(".txt", "DFSOutput.txt"));
				return;
			}
			for (Node neighbor : getNeighbors(current, mazeCopy)) {
				neighbor.visited = true;
				neighbor.parent = current;
				stack.push(neighbor);
			}
		}
	}

	/** Helper method to reset the visited state of all nodes */
	private void resetVisited(Node[][] mazeToReset) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				mazeToReset[i][j].visited = false;
				mazeToReset[i][j].parent = null;
			}
		}
	}
}
