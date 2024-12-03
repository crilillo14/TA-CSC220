package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.stream.Stream;
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
		while (end.parent != null) {
			if (end.content != 'G')
				end.content = '.';
			end = end.parent;
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			
			String line = height + " " + width;
			output.println(line);
			
			for (int row = 0; row < maze.length; row++) {
				line = "";
				for (int col = 0; col < maze[row].length; col++) {
					line += maze[row][col].content;
				}
				
				output.println(line);
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
		try {
			// don't forget to close input when you're done
			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));
			

			String line;
			
			// Handle dimensions
			line = input.readLine();
			if (line == null) {
				input.close();
				return;
			}
				
			String[] dimensions = line.split(" ");
			height = Integer.parseInt(dimensions[0]);
			width = Integer.parseInt(dimensions[1]);
			maze = new Node[height][width];
			
			int row = 0;
			while ((line = input.readLine()) != null) {
				
				for (int col = 0; col < line.length(); col++) {
					maze[row][col] = new Node(row, col, line.charAt(col));
					
					if (line.charAt(col) == 'S') {
						startX = row;
						startY = col;
					}
				}
				
				row++;
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
		Node north, south, east, west;
		
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> unvisitedNodes = new ArrayList<Node>();
		
		// 1. Inspect the north neighbor
		north = maze[currentNode.row - 1][currentNode.col];
		if (!north.isWall() && !north.isVisited())
			unvisitedNodes.add(north);

		// 2. Inspect the south neighbor
		south = maze[currentNode.row + 1][currentNode.col];
		if (!south.isWall() && !south.isVisited())
			unvisitedNodes.add(south);

		// 3. Inspect the west neighbor
		west = maze[currentNode.row][currentNode.col - 1];
		if (!west.isWall() && !west.isVisited())
			unvisitedNodes.add(west);

		// 4. Inspect the east neighbor
		east = maze[currentNode.row][currentNode.col + 1];
		if (!east.isWall() && !east.isVisited())
			unvisitedNodes.add(east);

		return unvisitedNodes;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		
		Queue<Node> nodeQueue = new LinkedList<Node>();
		
		Node start = maze[startX][startY];
		
		start.visited = true;
		nodeQueue.add(start);
		
		while (!nodeQueue.isEmpty()) {
			Node curr = nodeQueue.poll();
			if (curr.content == 'G')
				backtrack(curr);
			
			for (Node next : getNeighbors(curr)) {
				if (!next.visited) {
					next.visited = true;
					next.parent = curr;
					nodeQueue.add(next);
				}
			}
		}
		
	}
	
	private void rDFS(Node curr) {
		curr.visited = true;
		
		if (curr.content == 'G')
			backtrack(curr);
		
		for (Node next : getNeighbors(curr)) {
			if(!next.visited) {
				next.parent = curr;
				rDFS(next);
			}
		}
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		Node start = maze[startX][startY];
		rDFS(start);
	}

}
