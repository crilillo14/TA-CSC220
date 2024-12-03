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
		while (currentNode.content != 'S') {
			if (currentNode.content == ' ') {
				currentNode.content = '.';
			}
			currentNode = currentNode.parent;
		}
	} // End of backtrack() method

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			
			output.print(height + " " + width);
			output.println();
			
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					output.print(maze[i][j].content);
				}
				output.println();
			}
			
			output.print(" ");
			//output.println();
			
			output.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	} // END OF WRITEOUTPUT() METHOD

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
			
			String[] dimensions = input.readLine().split(" ");
			try {
				height = Integer.parseInt( dimensions[0] );
				width = Integer.parseInt( dimensions[1] );
			}
			catch(Exception e) {
				System.err.println("No height and/or width at top of file");
			}
			
			maze = new Node[height][width]; // height -> # of rows, width -> # of columns
			for (int i = 0; i < height; i++) {
				String oneLine = input.readLine();
				char[] lineCharList = null;
				if (oneLine != null) {
					lineCharList = oneLine.toCharArray();
				}
				if (lineCharList != null) {
					for (int j = 0; j < width; j++) {
							char nextChar = lineCharList[j];
							switch(nextChar) {
								case 'S':
									maze[i][j] = new Node(i, j, 'S');
									startX = i;
									startY = j;
									break;
								case 'G':
									maze[i][j] = new Node(i, j, 'G');
									break;
								case ' ':
									maze[i][j] = new Node(i, j, ' ');
									break;
								default:
									maze[i][j] = new Node(i, j, 'X');
									break;
							} // End of switch case
					} // End of column for loop
				} // End of if statement
			} // End of row for loop
			input.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	} // END OF BUILDGRAPH() METHOD

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
		ArrayList<Node> neighbors = new ArrayList<Node>();

		// 1. Inspect the north neighbor
		if ( !(maze[currentNode.row - 1][currentNode.col].isWall()) &&
				!(maze[currentNode.row - 1][currentNode.col].isVisited()) ) {
			north = maze[currentNode.row - 1][currentNode.col];
			neighbors.add(north);
		}

		// 2. Inspect the south neighbor
		if ( !(maze[currentNode.row + 1][currentNode.col].isWall()) &&
				!(maze[currentNode.row + 1][currentNode.col].isVisited()) ) {
			south = maze[currentNode.row + 1][currentNode.col];
			neighbors.add(south);
		}

		// 3. Inspect the west neighbor
		if ( !(maze[currentNode.row][currentNode.col - 1].isWall()) &&
				!(maze[currentNode.row][currentNode.col - 1].isVisited()) ) {
			west = maze[currentNode.row][currentNode.col - 1];
			neighbors.add(west);
		}

		// 4. Inspect the east neighbor
		if ( !(maze[currentNode.row][currentNode.col + 1].isWall()) &&
				!(maze[currentNode.row][currentNode.col + 1].isVisited()) ) {
			east = maze[currentNode.row][currentNode.col + 1];
			neighbors.add(east);
		}

		return neighbors;
	} // End of getNeighbors() method

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		Node current = maze[startX][startY];
		ArrayList<Node> queue = new ArrayList<Node>();
		queue.add(current);
		maze[startX][startY].visited = true;
		while (current.content != 'G') {
			current = queue.getFirst();
			queue.removeFirst();
			ArrayList<Node> neighbors = getNeighbors(current);
			for (Node next : neighbors) {
				queue.add(next);
				maze[next.row][next.col].parent = current;
				maze[next.row][next.col].visited = true;
			}
		}
		backtrack(maze[current.row][current.col]);
	} // End of solveBFS method

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		DFSHelper(maze[startX][startY]);
		backtrack(maze[targetRow][targetCol]);
	} // End of solveDFS() method
	
	int targetRow = startX;
	int targetCol = startY;
	private void DFSHelper(Node current) {
		maze[current.row][current.col].visited = true;
		if (current.content == 'G') {
			targetRow = current.row;
			targetCol = current.col;
			return;
		}
		for (Node next : getNeighbors(current)) {
			if (!next.isVisited()) {
				maze[next.row][next.col].parent = current;
				DFSHelper(next);
			}
		}
	} // End of DFSHelper method

}
