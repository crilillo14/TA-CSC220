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
		
		
		if (end.content != 'S') {
			if (end.content != 'G') {
				end.content = '.';
			}
			backtrack(end.parent);		
		}

		// TODO for assignment12

	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12

		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			
			output.print(height);
			output.print(" ");
			output.print(width);
			output.println();
			
			
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					output.print(maze[i][j].content);
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
		// TODO for lab12
		try {
			// don't forget to close input when you're done
			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));
			
			
			String startEntry = input.readLine();
			int spaceIndex = startEntry.indexOf(' ');
			for (int i = 0; i < spaceIndex; i++) {
				height = (height*10) + Character.getNumericValue(startEntry.charAt(i));
			}
			for (int i = spaceIndex + 1; i < startEntry.length(); i++) {
				width = (width*10) + Character.getNumericValue(startEntry.charAt(i));
			}
			
			maze = new Node[height][width];
			
			for (int i = 0; i < height; i++) {
				
				String lineEntry = input.readLine();
				for (int j = 0; j < width; j++) {
					
					if (lineEntry.charAt(j) == 'S')	{
						startX = i;
						startY = j;
					}
					
					Node entry = new Node(i, j, lineEntry.charAt(j));
					maze[i][j] = entry;
					
				}
			}
			
			input.close();
			
			// FILL IN

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
		
		ArrayList<Node> neighborList = new ArrayList<Node>();

		// 1. Inspect the north neighbor
		
		if (this.inMaze(currentNode.row  - 1, height)) {
			Node northNeighbor = maze[currentNode.row - 1][currentNode.col];
			if (!northNeighbor.isWall() && !northNeighbor.isVisited()) {
				neighborList.add(northNeighbor);
				northNeighbor.visited = true;
				northNeighbor.parent = currentNode;
			}
		}
		// 2. Inspect the south neighbor
		
		if (this.inMaze(currentNode.row + 1, height)) {
			Node southNeighbor = maze[currentNode.row + 1][currentNode.col];
			if (!southNeighbor.isWall() && !southNeighbor.isVisited()) {
				neighborList.add(southNeighbor);
				southNeighbor.visited = true;
				southNeighbor.parent = currentNode;
			}
		}

		// 3. Inspect the west neighbor
		
		if (this.inMaze(currentNode.col - 1, width)) {
			Node westNeighbor = maze[currentNode.row][currentNode.col - 1];
			if (!westNeighbor.isWall() && !westNeighbor.isVisited()) {
				neighborList.add(westNeighbor);
				westNeighbor.visited = true;
				westNeighbor.parent = currentNode;
			}
		}

		// 4. Inspect the east neighbor
		
		if (this.inMaze(currentNode.col + 1, width)) {
			Node eastNeighbor = maze[currentNode.row][currentNode.col + 1];
			if (!eastNeighbor.isWall() && !eastNeighbor.isVisited()) {
				neighborList.add(eastNeighbor);
				eastNeighbor.visited = true;
				eastNeighbor.parent = currentNode;
			}
		}

		return neighborList;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		
		
		Queue<Node> queue = new LinkedList<Node>();
		
		maze[startX][startY].visited = true;
		queue.offer(maze[startX][startY]);
		
		while (!queue.isEmpty()) {
			
			Node curr = queue.poll();
			
			if (curr.content == ('G')){
				backtrack(curr);
				this.writeOutput();
				break;
			}
			
			
			ArrayList<Node> neighbors = getNeighbors(curr);
			for (int i = 0; i < neighbors.size(); i++) {
				queue.add(neighbors.get(i));
			}
			
		
		}
		

		// TODO for assignment12
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		
		
		
		Stack<Node> stack = new Stack<Node>();
		
		maze[startX][startY].visited = true;
		stack.push(maze[startX][startY]);
		
		while (!stack.isEmpty()) {
			
			Node curr = stack.pop();
			
			if (curr.content == ('G')){
				backtrack(curr);
				this.writeOutput();
				break;
			}
			
			
			ArrayList<Node> neighbors = getNeighbors(curr);
			for (int i = 0; i < neighbors.size(); i++) {
				stack.push(neighbors.get(i));
			}
			
		
		}
		
		
		
		
		
		
		// TODO for assignment12
	}

}
