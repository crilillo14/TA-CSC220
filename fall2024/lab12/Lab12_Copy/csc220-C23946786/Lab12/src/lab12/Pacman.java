package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
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

		public boolean isStart() {
			return content == 'S';
		}

		public boolean isGoal() {
			return content == 'G';
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

	private Node getStart() {
		return maze[startX][startY];
	}

	/**
	 * helper method to construct the solution path from S to G NOTE this path is
	 * built in reverse order, starting with the goal G.
	 */
	private void backtrack(Node end) {
		// TODO for assignment12

		Node currNode = end.parent;
		while (currNode != null && !currNode.isStart()) {
			currNode.content = '.';
			currNode = currNode.parent;
		}

	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN

			String s = "";
			s += height + " " + width + "\n";
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					s += maze[i][j].content;
				}
				s += "\n";
			}

			output.write(s);
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
		// TODO for lab12
		try {
			// don't forget to close input when you're done
			BufferedReader input = new BufferedReader(new FileReader(inputFileName));
			// FILL IN=
			String userInput = input.readLine();
			String[] dimensions = userInput.split(" ");
			height = Integer.parseInt(dimensions[0]);
			width = Integer.parseInt(dimensions[1]);
		
			maze = new Node[height][width];
			for (int i = 0; i < height; i++) {
				String currLine = input.readLine();
				for (int j = 0; j < width; j++) {
					char content = currLine.charAt(j);
					Node newNode = new Node(i, j, content);
					maze[i][j] = newNode;
					if (content == 'S') {
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

	/**
	 * obtains all neighboring nodes that have *not* been visited yet from a given
	 * node when looking at the four cardinal directions: North, South, West, East
	 * (IN THIS ORDER!)
	 *
	 * @param currentNode the given node
	 * @return an ArrayList of the neighbors (i.e., successors)
	 */
	public ArrayList<Node> getNeighbors(Node currentNode) {
		// TODO for assignment12
		Node north, south, east, west;
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> neighbors = new ArrayList<>();

		// 1. Inspect the north neighbor
		if (inMaze(currentNode.row - 1, height)) {

			north = maze[currentNode.row - 1][currentNode.col];
			if (!north.isWall() && !north.isVisited()) {
				north.visited = true;
				north.parent = currentNode;
				neighbors.add(north);
			}
		}
		// 2. Inspect the south neighbor
		if (inMaze(currentNode.row + 1, height)) {
			south = maze[currentNode.row + 1][currentNode.col];
			if (!south.isWall() && !south.isVisited()) {
				south.visited = true;
				south.parent = currentNode;
				neighbors.add(south);
			}
		}

		// 3. Inspect the west neighbor
		if (inMaze(currentNode.col - 1, width)) {
			west = maze[currentNode.row][currentNode.col - 1];
			if (!west.isWall() && !west.isVisited()) {
				west.visited = true;
				west.parent = currentNode;
				neighbors.add(west);
			}
		}

		// 4. Inspect the east neighbor
		if (inMaze(currentNode.col + 1, width)) {
			east = maze[currentNode.row][currentNode.col + 1];
			if (!east.isWall() && !east.isVisited()) {
				east.visited = true;
				east.parent = currentNode;
				neighbors.add(east);
			}
		}

		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12

		LinkedList<Node> Q = new LinkedList<>();

		getStart().visited = true;
		Q.add(getStart());
		
		while (!Q.isEmpty()) {
			Node curr = Q.remove();
			if (curr.isGoal()) {
				backtrack(curr);
				return;
			}

			ArrayList<Node> neighbors = getNeighbors(curr);
			for (int i = 0; i < neighbors.size(); i++) {
				Node next = neighbors.get(i);
					Q.add(next);
			}
		}
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		LinkedList<Node> Stack = new LinkedList<>();

		getStart().visited = true;
		Stack.add(getStart());
		
		while (!Stack.isEmpty()) {
			Node curr = Stack.remove();
			if (curr.isGoal()) {
				backtrack(curr);
				return;
			}

			ArrayList<Node> neighbors = getNeighbors(curr);
			for (int i = 0; i < neighbors.size(); i++) {
				Node next = neighbors.get(i);
					Stack.add(0, next);
			}
		}
		
	}
}
