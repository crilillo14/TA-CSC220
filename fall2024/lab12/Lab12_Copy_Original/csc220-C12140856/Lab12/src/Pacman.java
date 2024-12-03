package lab12.code;

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
    private void backtrack(Node end) {
        Node currentNode = end;
        while (currentNode != null && currentNode.parent != null) {
            if (currentNode.content != 'S' && currentNode.content != 'G') {
                currentNode.content = '.';
            }
            currentNode = currentNode.parent;
        }
    }

	/** writes the solution to file */
    public void writeOutput() {
        try {
            PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
            output.println(height + " " + width);
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    output.print(maze[i][j].content + " ");
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
	        BufferedReader input = new BufferedReader(new FileReader(inputFileName));
	        // Read the first line to get height and width
	        String line = input.readLine();
	        System.out.println("First line read: '" + line + "'"); // Debugging statement

	        // Check if the line is null or empty
	        if (line == null || line.trim().isEmpty()) {
	            System.err.println("Error: The first line of the maze file is empty or null.");
	            input.close();
	            return;
	        }

	        String[] dimensions = line.trim().split("\\s+");
	        System.out.println("Dimensions array length: " + dimensions.length); // Debugging

	        // Ensure that dimensions array has at least two elements
	        if (dimensions.length < 2) {
	            System.err.println("Error: The dimensions line does not contain both height and width.");
	            input.close();
	            return;
	        }

	        try {
	            height = Integer.parseInt(dimensions[0]);
	            width = Integer.parseInt(dimensions[1]);
	            System.out.println("Parsed height: " + height + ", width: " + width); // Debugging
	        } catch (NumberFormatException e) {
	            System.err.println("Error parsing dimensions: " + e.getMessage());
	            input.close();
	            return;
	        }

	        // Initialize the maze array
	        maze = new Node[height][width];

	        // Read the rest of the maze
	        for (int i = 0; i < height; i++) {
	            line = input.readLine();
	            if (line == null || line.trim().isEmpty()) {
	                System.err.println("Error: Maze file has fewer rows than specified height.");
	                input.close();
	                return;
	            }

	            // Remove leading and trailing whitespace
	            line = line.trim();

	            // Get the characters as an array
	            char[] chars = line.toCharArray();

	            System.out.println("Processed line " + i + " (columns " + chars.length + "): " + line);

	            if (chars.length != width) {
	                System.err.println("Error: Maze row " + i + " does not contain the expected number of columns.");
	                System.err.println("Expected " + width + " columns but found " + chars.length);
	                input.close();
	                return;
	            }

	            for (int j = 0; j < width; j++) {
	                char c = chars[j];
	                maze[i][j] = new Node(i, j, c);
	                if (c == 'S') {
	                    startX = i;
	                    startY = j;
	                }
	            }
	        }

	        // Check if start position was found
	        if (maze[startX][startY] == null || maze[startX][startY].content != 'S') {
	            System.err.println("Error: Start position 'S' not found in the maze.");
	            input.close();
	            return;
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
	        ArrayList<Node> neighbors = new ArrayList<>();
	        int row = currentNode.row;
	        int col = currentNode.col;

	        // North
	        if (inMaze(row - 1, height)) {
	            Node north = maze[row - 1][col];
	            if (!north.isWall() && !north.isVisited()) {
	                north.visited = true;
	                north.parent = currentNode;
	                neighbors.add(north);
	            }
	        }
	        // South
	        if (inMaze(row + 1, height)) {
	            Node south = maze[row + 1][col];
	            if (!south.isWall() && !south.isVisited()) {
	                south.visited = true;
	                south.parent = currentNode;
	                neighbors.add(south);
	            }
	        }
	        // West
	        if (inMaze(col - 1, width)) {
	            Node west = maze[row][col - 1];
	            if (!west.isWall() && !west.isVisited()) {
	                west.visited = true;
	                west.parent = currentNode;
	                neighbors.add(west);
	            }
	        }
	        // East
	        if (inMaze(col + 1, width)) {
	            Node east = maze[row][col + 1];
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
	        Queue<Node> queue = new LinkedList<>();
	        Node startNode = maze[startX][startY];
	        startNode.visited = true;
	        queue.add(startNode);

	        boolean found = false;
	        Node endNode = null;

	        while (!queue.isEmpty()) {
	            Node currentNode = queue.poll();
	            if (currentNode.content == 'G') {
	                found = true;
	                endNode = currentNode;
	                break;
	            }
	            ArrayList<Node> neighbors = getNeighbors(currentNode);
	            for (Node neighbor : neighbors) {
	                queue.add(neighbor);
	            }
	        }

	        if (found) {
	            backtrack(endNode);
	        }
	        // After solving, write the output
	        writeOutput();
	    }

	    /** Pacman uses DFS strategy to solve the maze */
	    public void solveDFS() {
	        Stack<Node> stack = new Stack<>();
	        Node startNode = maze[startX][startY];
	        startNode.visited = true;
	        stack.push(startNode);

	        boolean found = false;
	        Node endNode = null;

	        while (!stack.isEmpty()) {
	            Node currentNode = stack.pop();
	            if (currentNode.content == 'G') {
	                found = true;
	                endNode = currentNode;
	                break;
	            }
	            ArrayList<Node> neighbors = getNeighbors(currentNode);
	            for (Node neighbor : neighbors) {
	                stack.push(neighbor);
	            }
	        }

	        if (found) {
	            backtrack(endNode);
	        }
	        // After solving, write the output
	        writeOutput();
	    }

}
