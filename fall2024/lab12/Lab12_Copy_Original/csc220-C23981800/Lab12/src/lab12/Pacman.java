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

	/**
	 * Helper method to reconstruct the solution path from S to G.
	 * This path is built in reverse order, starting with the goal G.
	 * Each node on the path will have its content set to '.'.
	 * @param end the end node (goal) where the path terminates
	 */
	private void backtrack(Node end) {
	    Node current = end;
	    while (current != null) {
	        // Set the path character, but keep the start and goal as 'S' and 'G'
	        if (current.content != 'S' && current.content != 'G') {
	            current.content = '.';
	        }
	        // Move to the parent node, tracing the path backwards
	        current = current.parent;
	    }
	}

	/** writes the solution to file */
	/** writes the solution to file */
	/** writes the solution to file */
	public void writeOutput() {
	    try (PrintWriter output = new PrintWriter(new FileWriter(outputFileName))) {
	        output.println(height + " " + width);

	        for (int i = 0; i < height; i++) {
	            for (int j = 0; j < width; j++) {
	                if (maze[i][j] != null) {
	                    output.print(maze[i][j].content);
	                } else {
	                    output.print('X'); // Fallback character if node is null
	                }
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
	/** helper method to construct a graph from a given input file;
	 *  all member variables (e.g., maze, startX, startY) should be
	 *  initialized by the end of this method
	 */
	private void buildGraph() {
	    try (BufferedReader input = new BufferedReader(new FileReader(inputFileName))) {
	        String line = input.readLine();
	        String[] dimensions = line.split(" ");
	        height = Integer.parseInt(dimensions[0]);
	        width = Integer.parseInt(dimensions[1]);

	        maze = new Node[height][width];

	        for (int i = 0; i < height; i++) {
	            line = input.readLine();
	            if (line == null) {
	                throw new IllegalArgumentException("Not enough lines in the input file.");
	            }
	            line = line.replaceAll(" ", "");  // Remove spaces

	            for (int j = 0; j < width; j++) {
	                char c = (j < line.length()) ? line.charAt(j) : 'X'; // Default to 'X' if line is too short
	                maze[i][j] = new Node(i, j, c);
	                if (c == 'S') {
	                    startX = i;
	                    startY = j;
	                }
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (IllegalArgumentException e) {
	        System.err.println("Error in input file format: " + e.getMessage());
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
	    ArrayList<Node> neighbors = new ArrayList<>();

	    // North Neighbor
	    if (currentNode.row > 0) { // Ensure it's not the top row
	        Node north = maze[currentNode.row - 1][currentNode.col];
	        if (!north.isVisited() && !north.isWall()) {
	            north.visited = true;
	            north.parent = currentNode;
	            neighbors.add(north);
	        }
	    }

	    // South Neighbor
	    if (currentNode.row < height - 1) { // Ensure it's not the bottom row
	        Node south = maze[currentNode.row + 1][currentNode.col];
	        if (!south.isVisited() && !south.isWall()) {
	            south.visited = true;
	            south.parent = currentNode;
	            neighbors.add(south);
	        }
	    }

	    // West Neighbor
	    if (currentNode.col > 0) { // Ensure it's not the leftmost column
	        Node west = maze[currentNode.row][currentNode.col - 1];
	        if (!west.isVisited() && !west.isWall()) {
	            west.visited = true;
	            west.parent = currentNode;
	            neighbors.add(west);
	        }
	    }

	    // East Neighbor
	    if (currentNode.col < width - 1) { // Ensure it's not the rightmost column
	        Node east = maze[currentNode.row][currentNode.col + 1];
	        if (!east.isVisited() && !east.isWall()) {
	            east.visited = true;
	            east.parent = currentNode;
	            neighbors.add(east);
	        }
	    }

	    return neighbors;
	}

	/**
	 * Pacman uses BFS strategy to solve the maze.
	 */
	public void solveBFS() {
	    Queue<Node> queue = new LinkedList<>();
	    Node startNode = maze[startX][startY];
	    startNode.visited = true;
	    queue.add(startNode);

	    boolean found = false;

	    while (!queue.isEmpty()) {
	        Node currentNode = queue.poll();
	        System.out.println("Visiting Node: " + currentNode.row + ", " + currentNode.col); // Debug output

	        if (currentNode.content == 'G') {
	            System.out.println("Goal found!");
	            backtrack(currentNode);
	            found = true;
	            break;
	        }

	        ArrayList<Node> neighbors = getNeighbors(currentNode);
	        for (Node neighbor : neighbors) {
	            if (!neighbor.isVisited()) {
	                neighbor.visited = true;
	                neighbor.parent = currentNode;
	                queue.add(neighbor);
	            }
	        }
	    }

	    if (found) {
	        writeOutput();
	    } else {
	        System.out.println("No solution found for maze starting at " + startX + ", " + startY);
	    }
	}

	/**
	 * Pacman uses DFS strategy to solve the maze.
	 */
	public void solveDFS() {
	    // Initialize the stack with the starting node
	    Stack<Node> stack = new Stack<>();
	    Node startNode = maze[startX][startY];
	    startNode.visited = true;
	    stack.push(startNode);

	    boolean found = false;
	    Node currentNode;

	    // Perform DFS until the stack is empty or the goal is found
	    while (!stack.isEmpty() && !found) {
	        currentNode = stack.pop();

	        // Check if we have reached the goal
	        if (currentNode.content == 'G') {
	            backtrack(currentNode);
	            found = true;
	            break;
	        }

	        // Push all unvisited neighbors onto the stack
	        ArrayList<Node> neighbors = getNeighbors(currentNode);
	        for (Node neighbor : neighbors) {
	            if (!neighbor.isVisited()) {
	                neighbor.visited = true;
	                neighbor.parent = currentNode;
	                stack.push(neighbor);
	            }
	        }
	    }

	    // Output the solution to the file
	    if (found) {
	        writeOutput();
	    } else {
	        System.out.println("No solution found.");
	    }
	}

}
