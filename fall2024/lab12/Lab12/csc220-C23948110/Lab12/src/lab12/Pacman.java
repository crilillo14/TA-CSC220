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
	private void backtrack(Node end){ //after you know your graph, you backtrack to draw your dots
		// TODO for assignment12
		//modifies maze
		//Node currentNode = end.parent;
		Node currentNode = end;
		while ( currentNode != null && currentNode.content != 'S') {
			if (currentNode.content != 'G') {
				currentNode.content = '.';
			}
			
			currentNode = currentNode.parent;
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			//output.println(height + " " + width);
			output.write(height + " " + width + "\n");
			
			//height + " " + width
			// maze array
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j ++) {
					output.print(maze[i][j].content);
					//output.write(maze[i][j].content);
				}
				output.println();
				//output.write("\n");
			}
			output.close();
			//System.out.println("Output has been succesfully saved!" + " to " + outputFileName);
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
			String currentLine = input.readLine();
			//System.out.println(currentLine);
			String[] dimensions = currentLine.split(" ");
			//System.out.println("This graph will be " + dimensions[0] + " x " + dimensions[1]);
			height = Integer.parseInt(dimensions[0]);
			width = Integer.parseInt(dimensions[1]);
			maze = new Node[height][width];
			
			for (int i = 0; i < height; i++) {
				currentLine = input.readLine();
				char[] splitLine = currentLine.toCharArray();
				for (int j = 0; j < splitLine.length; j ++) {
					if (splitLine[j] == 'S') {
						startX = i;
						startY = j;
					}
					maze[i][j] = new Node(i, j, splitLine[j]);
					
					//System.out.print(splitLine[j]);
				}
				//System.out.println();
			}
			//System.out.println("Start is at (" + startX + ", " + startY + ")");
			//String.toCharArray
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
		//Node north, south, east, west;
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> neighbors = new ArrayList<>();
		
		int row = currentNode.row;
		int col = currentNode.col;

		// 1. Inspect the north neighbor
		if (inMaze(row - 1, height)) {
			Node north = maze[row - 1][col];//new Node(row, col, maze[row-1][col].content);
			if (!north.isVisited() && !north.isWall()) {
				neighbors.add(north);
				north.visited = true;
				north.parent = currentNode;
			}
		}
			
//		if ((row - 1) >= 0) {
//			north = maze[row-1][col];//new Node(row, col, maze[row-1][col].content);
//			if (!north.isVisited() && !north.isWall()) {
//				neighbors.add(north);
//			}
//		}

		// 2. Inspect the south neighbor
		if (inMaze(row + 1, height)) {
			Node south = maze[row + 1][col];//new Node(row, col, maze[row-1][col].content);
			if (!south.isVisited() && !south.isWall()) {
				neighbors.add(south);
				south.visited = true;
				south.parent = currentNode;
			}
		}
//		if ((row + 1) < height) {
//			south = maze[row+1][col];
//			if (!south.isVisited() && !south.isWall()) {
////				south.visited = true;
////				south.parent = currentNode;
//				//if (south.content != 'X')
//				neighbors.add(south);
//			}
//		}

		// 3. Inspect the west neighbor
		if (inMaze(col - 1, width)) {
			Node west = maze[row][col - 1];//new Node(row, col, maze[row-1][col].content);
			if (!west.isVisited() && !west.isWall()) {
				neighbors.add(west);
				west.visited = true;
				west.parent = currentNode;
			}
		}
//		if ((col - 1) >= 0) {
//			west = maze[row][col-1];
//			if (!west.isVisited() && !west.isWall()) {
////				west.visited = true;
////				west.parent = currentNode;
//				neighbors.add(west);
//			}
//		}

		// 4. Inspect the east neighbor
		if (inMaze(col + 1, width)) {
			Node east = maze[row][col + 1];//new Node(row, col, maze[row-1][col].content);
			if (!east.isVisited() && !east.isWall()) {
				neighbors.add(east);
				east.visited = true;
				east.parent = currentNode;
			}
		}
//		if ((col + 1) < width) {
//			east = maze[row][col+1];
//			if (!east.isVisited() && !east.isWall()) {
////				east.visited = true;
////				east.parent = currentNode;
//				neighbors.add(east);
//			}
//		}

		//System.out.println("Neighbors: " + neighbors.size());
//		for (int i = 0; i < neighbors.size(); i++) {
//			System.out.println();
//		}
		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() { //uses a queue ...explore all paths at once until you find the Goal (guarantees the shortest path)
		// TODO for assignment12
		//System.out.println("BFS?");
		Node currentNode;
		maze[startX][startY].visited = true;
		
		LinkedList<Node> queue = new LinkedList<>();
		queue.offer(maze[startX][startY]);
		
		while (!queue.isEmpty()) {
			currentNode = queue.poll();
			
			if(currentNode.content == 'G') {
				backtrack(currentNode);
				writeOutput();
				return;
			}
			ArrayList<Node> neighbors = getNeighbors(currentNode);
			for (int i = 0; i < neighbors.size(); i++) {
				queue.offer(neighbors.get(i));
			}
		}
//		
//		backtrack(currentNode);
//		writeOutput();
		
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() { //uses a stack ...explore each path until a dead end is found (or stack is empty) 
		// TODO for assignment12
		Stack<Node> stack = new Stack<>();
		Node currentNode;
	    maze[startX][startY].visited = true;

	    stack.push(maze[startX][startY]);

	    while (!stack.isEmpty()) {
	        currentNode = stack.pop();

	        if (currentNode.content == 'G') {
	            backtrack(currentNode);
	            writeOutput();
	            return;
	        }

	        ArrayList<Node> neighbors = getNeighbors(currentNode);
	        for (int i = 0; i < neighbors.size(); i++) {
	            stack.push(neighbors.get(i));
	        }
	    }
	}

}
