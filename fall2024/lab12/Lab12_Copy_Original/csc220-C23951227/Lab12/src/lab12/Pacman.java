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
	private void backtrack(Node end){
		while (end != null && end.content != 'S'){
			if (end.content != 'G') {
				end.content = '.';
			}
			end = end.parent;
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
	    // TODO for lab12
	    try (PrintWriter output = new PrintWriter(new FileWriter(outputFileName))) {
	        output.write(toString());
	    } catch (IOException e) {
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
			BufferedReader input = new BufferedReader(new FileReader(inputFileName));
			// FILL IN
			String graphStats = input.readLine();
			String[] parts = graphStats.split(" ");
			int sizeX = Integer.parseInt(parts[1]);
			int sizeY = Integer.parseInt(parts[0]);
			height = sizeY;
			width = sizeX;
			maze = new Node[sizeY][sizeX];
			for (int y = 0; y < sizeY; y++) {
				for(int x = 0; x < sizeX; x++) {
					maze[y][x] = new Node(y,x,(char) input.read());
					if (maze[y][x].content == 'S') {
						startX = x;
						startY = y;
					}
				}
				input.read();
			}

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
		ArrayList<Node> neighbors = new ArrayList();
		// 1. Inspect the north neighbor
		int northRow = currentNode.row - 1;
		int northCol = currentNode.col;
		
		if (inMaze(northRow, height) && !maze[northRow][northCol].isWall() && !maze[northRow][northCol].isVisited()) {
		    north = maze[northRow][northCol];
		    neighbors.add(north); // Add the north neighbor to the list
		}
		// 2. Inspect the south neighbor
		int southRow = currentNode.row + 1;
		int southCol = currentNode.col;
		
		if (inMaze( southRow, height) && !maze[ southRow][ southCol].isWall() && !maze[ southRow][ southCol].isVisited()) {
		    south = maze[ southRow][ southCol];
		    neighbors.add( south); // Add the north neighbor to the list
		}
		// 3. Inspect the west neighbor
		int westRow = currentNode.row;
		int westCol = currentNode.col - 1;
		
		if (inMaze(westRow, height) && !maze[westRow][westCol].isWall() && !maze[westRow][westCol].isVisited()) {
		    west = maze[westRow][westCol];
		    neighbors.add( west); // Add the north neighbor to the list
		}
		// 4. Inspect the east neighbor
		int eastRow = currentNode.row;
		int eastCol = currentNode.col + 1;
		
		if (inMaze(eastRow, height) && !maze[eastRow][eastCol].isWall() && !maze[eastRow][eastCol].isVisited()) {
		    east = maze[eastRow][eastCol];
		    neighbors.add( east); // Add the north neighbor to the list
		}
		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		Queue<Node> queue = new LinkedList<>();
		Node start = maze[startY][startX];
		start.visited = true;
		queue.add(start);
		boolean solved = false;
		while (!queue.isEmpty()) {
			Node current = queue.poll();
			if (current.content == 'G') {
				solved = true;
				backtrack(current);
				break;
			}
			ArrayList<Node> neighbors = getNeighbors(current);
		    for (Node neighbor : neighbors) {
		        neighbor.visited = true;    
		        neighbor.parent = current;
		        queue.add(neighbor);         
		    }
		    if (queue.isEmpty() && solved) {
		    	System.out.println("no solution");
		    }
			
		}
		       
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		Stack<Node> stack = new Stack<>();
		Node start = maze[startY][startX];
		start.visited = true;
		stack.push(start);
		boolean solved = false;
		while (!stack.isEmpty()) {
			Node current = stack.pop();
			if (current.content == 'G') {
				solved = true;
				backtrack(current);
				break;
			}
			ArrayList<Node> neighbors = getNeighbors(current);
		    for (Node neighbor : neighbors) {
		        neighbor.visited = true;    
		        neighbor.parent = current;
		        stack.push(neighbor);         
		    }
		    if (stack.isEmpty() && solved) {
		    	System.out.println("no solution");
		    }
			
		}
	}

}
