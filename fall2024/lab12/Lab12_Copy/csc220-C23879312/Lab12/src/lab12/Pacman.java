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
		// TODO for assignment12
		Node currentNode = end;
		while(currentNode != null && currentNode.content != 'S') {
			if (currentNode.content != 'G' && currentNode.content != 'X') {
				currentNode.content = '.'; // the dot is showing us the navigation of the maze
				
			}
			currentNode = currentNode.parent;
		}
//		if (end.content == 'S') {
//			return;
//			
//		}
//		
//		if(end.content != 'S' && end.content != 'G') {
//			end.content = '.';
//		}
//		
//		backtrack(end.parent);
		
//		Node parent = end.parent;
//		while(parent.content != 'S') {
//			parent.content = '.';
//			parent = parent.parent;
//		}
		
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
					output.print(maze[i][j].content);
				}
				output.print("\n");
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
			// FILL IN
			// for each char we read, set it up as a node?
			String widthHeight = input.readLine();
			String[] something = widthHeight.split(" ");
			height = Integer.parseInt(something[0]);
			width = Integer.parseInt(something[1]);
			
			maze = new Node[height][width];
			
			for (int i = 0; i < height; i++) {
				String currLine = input.readLine();
				char[] c = currLine.toCharArray();
				for (int j = 0; j < width; j++) {
					if (c[j] == 'S') {
						startX = j;
						startY = i;
					}
					maze[i][j] = new Node(i, j, c[j]);
				}
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
		// TODO for assignment12
		Node north, south, east, west;
		
		ArrayList<Node> neighbors = new ArrayList<>();
		int row = currentNode.row;
		int column = currentNode.col;
		// 0. Initialize an ArrayList of nodes

//		// 1. Inspect the north neighbor
		if(inMaze(row - 1, height) && !maze[row - 1][column].isWall() && !maze[row-1][column].isVisited()) {
			north = maze[row - 1][column];
			north.visited = true;
			north.parent = currentNode;
			neighbors.add(north);
		}

		// 2. Inspect the south neighbor
		
		if(inMaze(row + 1, height) && !maze[row + 1][column].isWall() && !maze[row + 1][column].isVisited()) {
			south = maze[row + 1][column];
			south.visited = true;
			south.parent = currentNode;
			neighbors.add(south);
		}

		// 3. Inspect the west neighbor
		
		if(inMaze(column - 1, width) && !maze[row][column - 1].isWall() && !maze[row][column - 1].isVisited()) {
			west = maze[row][column - 1];
			west.visited = true;
			west.parent = currentNode;
			neighbors.add(west);
		}

		// 4. Inspect the east neighbor
		if(inMaze(column + 1, width) && !maze[row][column + 1].isWall() && !maze[row][column + 1].isVisited()) {
			east = maze[row][column + 1];
			east.visited = true;
			east.parent = currentNode;
			neighbors.add(east);
		}
		

		return neighbors;
		
		//north
		
//		if(inMaze(currentNode.row - 1, height)) {
//			north = maze[currentNode.row - 1][currentNode.col];
//			if(!north.isVisited() && !north.isWall()) {
//				north.parent = currentNode;
//				neighbors.add(north);
//			}
//		}
//		
//		// south
//		
//		if(inMaze(currentNode.row + 1, height)) {
//			south = maze[currentNode.row + 1][currentNode.col];
//			if(!south.isVisited() && !south.isWall()) {
//				south.parent = currentNode;
//				neighbors.add(south);
//			}
//		}
//		
//		// west
//		if(inMaze(currentNode.col - 1, width)) {
//			west = maze[currentNode.row][currentNode.col - 1];
//			if(!west.isVisited() && !west.isWall()) {
//				west.parent = currentNode;
//				neighbors.add(west);
//			}
//		}
//		
//		//east
//		
//		if(inMaze(currentNode.col + 1, width)) {
//			east = maze[currentNode.row][currentNode.col + 1];
//			if(!east.isVisited() && !east.isWall()) {
//				east.parent = currentNode;
//				neighbors.add(east);
//			}
//		}
//		
//		return neighbors;
		
		
		
		
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		Queue<Node> queue = new LinkedList<>();
		Node start = maze[startY][startX];
		start.visited = true;
		queue.add(start);
		
		while (!queue.isEmpty()) {
			Node currentNode = queue.poll();
			if(currentNode.content == 'G') {
				backtrack(currentNode);
				break;
			}
			
			ArrayList<Node> neighbors = getNeighbors(currentNode);
			
//			for(Node neighbor : neighbors) {
//				neighbor.visited = true;
//				neighbor.parent = currentNode;
//				queue.add(neighbor);
//			}
			for(int i = 0; i< neighbors.size(); i++) {
				queue.add(neighbors.get(i));
			}
		}
		
		
		writeOutput();
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		Stack<Node> stack = new Stack<>();
		
		Node start = maze[startY][startX];
		start.visited = true;
		stack.push(start);
		
		while (!stack.isEmpty()) {
			Node currentNode = stack.pop();
			
			if(currentNode.content == 'G') {
				backtrack(currentNode);
				break;
			}
			
			ArrayList<Node> neighbors = getNeighbors(currentNode);
			for(int i = 0; i < neighbors.size(); i++) {
				stack.push(neighbors.get(i));
			}
//			for(Node neighbor : neighbors) {
//				neighbor.visited = true;
//				neighbor.parent = currentNode;
//				stack.push(neighbor);
//			}
		}
		
		
		writeOutput();
	}

}
