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
import java.util.InputMismatchException;

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
		// TODO for assignment12
		
		while (end.content != 'S') {
			end.content = '.';
			end = end.parent;
		}
		
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try (PrintWriter output = new PrintWriter(new FileWriter(outputFileName))) {
			
			// FILL IN
			
			output.write(height + " " + width + "\n");
			
			int row;
			int column;
			
			for (row = 0; row < maze.length; ++row) {
				for (column = 0; column < maze[row].length; ++ column) {
					output.write(maze[row][column].content);
				}
				output.write("\n"); // Don't forget the newline
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
	private void buildGraph() {
		// TODO for lab12
		try (BufferedReader input = new BufferedReader(new FileReader(inputFileName))) {
			// don't forget to close input when you're done
			
			// FILL IN
			
			String[] dimensions = input.readLine().split(" ");
			height = Integer.parseInt(dimensions[0]);
			width = Integer.parseInt(dimensions[1]);
			
			maze = new Node[height][width];
			
			int row;
			int column;
			
			for (row = 0; row < maze.length; ++row) {
				for (column = 0; column < maze[row].length; ++column) {
					char theContent = (char) input.read();
					maze[row][column] = new Node(row, column, theContent);
					
					if (theContent == 'S') {
						startX = row;
						startY = column;
					}	
				}
				
				input.read(); // Discard the newline character
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InputMismatchException e) {
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
		ArrayList<Node> neighborList = new ArrayList<Node>();
		int currRow = currentNode.row;
		int currColumn = currentNode.col;

		// 1. Inspect the north neighbor
		
		north = maze[currRow - 1][currColumn];
		if (!north.isWall() && !north.isVisited())
			neighborList.add(north);

		// 2. Inspect the south neighbor
		south = maze[currRow + 1][currColumn];
		if (!south.isWall() && !south.isVisited())
			neighborList.add(south);

		// 3. Inspect the west neighbor
		west = maze[currRow][currColumn - 1];
		if (!west.isWall() && !west.isVisited())
			neighborList.add(west);

		// 4. Inspect the east neighbor
		east = maze[currRow][currColumn + 1];
		if (!east.isWall() && !east.isVisited())
			neighborList.add(east);

		return neighborList;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		
		Queue<Node> queue = new LinkedList<Node>();
		Node currentNode = maze[startX][startY];
		queue.offer(currentNode);
		currentNode.visited = true;
		
//		While the data structure has data
		
		while(!queue.isEmpty()) {
			
//			Remove node from data structure
			
			currentNode = queue.poll();
			
//			Find all valid neighbors
			
			ArrayList<Node> neighborList = getNeighbors(currentNode);
			
//			For each neighbor
			
			for (Node neighbor : neighborList) {
				
//				If it is the goal, return
				
				if (neighbor.content == 'G') {
					backtrack(currentNode);
					return;
				}
				
//				Set parent
				neighbor.parent = currentNode;
				
//				Set visited
				neighbor.visited = true;
				
//				Add to data structure	
				queue.offer(neighbor);
				
			}
			
		}
					
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		
		Stack<Node> stack = new Stack<Node>();
		Node currentNode = maze[startX][startY];
		stack.push(currentNode);
		currentNode.visited = true;
		
//		While the data structure has data
		
		while(!stack.isEmpty()) {
			
//			Remove node from data structure
			
			currentNode = stack.pop();
			
//			Find all valid neighbors
			
			ArrayList<Node> neighborList = getNeighbors(currentNode);
			
//			For each neighbor
			
			for (Node neighbor : neighborList) {
				
//				If it is the goal, return
				
				if (neighbor.content == 'G') {
					backtrack(currentNode);
					return;
				}
				
//				Set parent
				neighbor.parent = currentNode;
				
//				Set visited
				neighbor.visited = true;
				
//				Add to data structure	
				stack.push(neighbor);
				
			}
			
		}
		
	}

}
