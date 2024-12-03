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

	/** Maze representation and configuration */
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

	/** Node class represents a single cell in the maze */
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

	/** Checks if given index is within maze bounds */
	private boolean inMaze(int index, int bound) {
		return index < bound && index >= 0;
	}

	/** Traces back from goal to start, marking the solution path with '.' */
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

	/** Reads maze configuration from input file and builds graph representation */
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

	/** Returns unvisited neighbors in order: North, South, West, East */
	public ArrayList<Node> getNeighbors(Node currentNode) {
		// Add bounds checking to prevent array index out of bounds
		ArrayList<Node> out = new ArrayList<Node>();
		int row = currentNode.row;
		int col = currentNode.col;

		// Check each direction only if within bounds
		if (inMaze(row - 1, height)) {  // North
			Node north = maze[row - 1][col];
			if (!north.isWall() && !north.visited) out.add(north);
		}
		if (inMaze(row + 1, height)) {  // South
			Node south = maze[row + 1][col];
			if (!south.isWall() && !south.visited) out.add(south);
		}
		if (inMaze(col - 1, width)) {   // West
			Node west = maze[row][col - 1];
			if (!west.isWall() && !west.visited) out.add(west);
		}
		if (inMaze(col + 1, width)) {   // East
			Node east = maze[row][col + 1];
			if (!east.isWall() && !east.visited) out.add(east);
		}

		return out;
	}

	/** Solves maze using Breadth-First Search (finds shortest path) */
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

	/** Solves maze using Depth-First Search */
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
