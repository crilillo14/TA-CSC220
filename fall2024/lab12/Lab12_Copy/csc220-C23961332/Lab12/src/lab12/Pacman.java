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
			parent = null;
			this.row = x;
			this.col = y;
		}

		public boolean isWall() {
			return content == 'X';
		}

		public boolean isVisited() {
			return visited;
		}
	}

	/** constructor */
	public Pacman(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		buildGraph();
	}

	private boolean inMaze(int index, int bound) {
		return index < bound && index >= 0;
	}

	/**
	 * helper method to construct the solution path from S to G NOTE this path is
	 * built in reverse order, starting with the goal G.
	 */
	private void backtrack(Node end) {
		
		Node curr = end;
		
		while (curr.parent != null) {
			
			if (curr.content == ' ') {
				curr.content = '.';
			}
			
			curr = curr.parent;
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// First Line
			String header = this.height + " " + this.width;
			output.println(header);

			// Rest of the maze
			for (int i = 0; i < this.height; i++) {
				for (int j = 0; j < this.width; j++) {

					output.print(this.maze[i][j].content);

				}
				output.println();
			}

			// PrintWriter stuff...
			output.flush();
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		String s = "";
		s += height + " " + width + "\n";
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				s += maze[i][j].content + " ";
			}
			s += "\n";
		}
		return s;
	}

	/**
	 * helper method to construct a graph from a given input file; all member
	 * variables (e.g. maze, startX, startY) should be initialized by the end of
	 * this method
	 */
	private void buildGraph() {

		try {
			// don't forget to close input when you're done
			BufferedReader input = new BufferedReader(new FileReader(inputFileName));

			String specs = input.readLine();

			// We find the index of the space and get the number before it and after it
			int index_of_space = specs.indexOf((int) ' ');

			this.height = Integer.parseInt(specs.substring(0, index_of_space));
			this.width = Integer.parseInt(specs.substring(index_of_space + 1));

			this.maze = new Node[this.height][this.width];

			for (int i = 0; i < this.height; i++) {
				for (int j = 0; j < this.width; j++) {
					char cell = (char) input.read();

					this.maze[i][j] = new Node(i, j, cell);

					if (cell == 'S') {
						this.startX = i;
						this.startY = j;
					}
				}
				// Discard newline character
				input.read();
			}

			input.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * obtains all neighboring nodes that have *not* been visited yet from a given
	 * node when looking at the four cardinal directions: North, South, West, East
	 * (IN THIS ORDER!)
	 *
	 * @param currentNode the given node
	 * @return an ArrayList of the neighbors (i.e., successors)
	 */
	public ArrayList<Node> getNeighbors(Node currentNode) {

		Node north, south, east, west;
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> out = new ArrayList<Node>();

		// 1. Inspect the north neighbor
		north = this.maze[currentNode.row - 1][currentNode.col];

		if (north.content != 'X' && !north.visited) {
			out.add(north);
		}

		// 2. Inspect the south neighbor
		south = this.maze[currentNode.row + 1][currentNode.col];

		if (south.content != 'X' && !south.visited) {
			out.add(south);
		}

		// 3. Inspect the west neighbor
		west = this.maze[currentNode.row ][currentNode.col - 1];

		if (west.content != 'X' && !west.visited) {
			out.add(west);
		}

		// 4. Inspect the east neighbor
		east = this.maze[currentNode.row][currentNode.col + 1];

		if (east.content != 'X' && !east.visited) {
			out.add(east);
		}

		return out;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {

		LinkedList<Node> queue = new LinkedList<Node>();
		Node startNode = this.maze[this.startX][this.startY];
		
		startNode.visited = true;
		
		queue.add(startNode);
		
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			ArrayList<Node> neighbors = this.getNeighbors(current);
			
			for (int i = 0; i < neighbors.size(); i++) {
				Node neighbor = neighbors.get(i);
				neighbor.parent = current;
				neighbor.visited = true;
				if (neighbor.content == 'G') {
					this.backtrack(neighbor);
					return;
				}
				
				queue.add(neighbor);
			}
		}
		
		
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		
		Stack<Node> stack = new Stack<Node>();
		Node startNode = this.maze[this.startX][this.startY];
		
		startNode.visited = true;
		
		stack.push(startNode);
		
		while (!stack.isEmpty()) {
			Node current = stack.pop();
			ArrayList<Node> neighbors = this.getNeighbors(current);
			
			for (int i = 0; i < neighbors.size(); i++) {
				Node neighbor = neighbors.get(i);
				neighbor.parent = current;
				neighbor.visited = true;
				if (neighbor.content == 'G') {
					this.backtrack(neighbor);
					return;
				}
				
				stack.push(neighbor);
			}
		}
	}

}
