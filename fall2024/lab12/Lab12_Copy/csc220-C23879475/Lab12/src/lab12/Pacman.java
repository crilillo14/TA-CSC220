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
		Node node = end;
		while(node.parent != null) {
			node = node.parent;
			node.content = '.';
			if(node.parent.content == 'S') {
				break;
			}
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
	
			
			String s = "";
			s += height + " " + width + "\n";
			for (int i = 0; i < height; i++){
				for (int j = 0; j < width; j++){
					s += maze[i][j].content;
				}
				s += "\n";
			}
			output.print(s);
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


	private void buildGraph() {
		try {

			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));
			String[] fline = input.readLine().split(" ");
			this.height = Integer.parseInt(fline[0]);
			this.width = Integer.parseInt(fline[1]);
			
			this.maze = new Node[this.height][this.width];
			
			for (int row = 0; row < this.height; row++) {
				String curLine = input.readLine();
				for (int col = 0; col < this.width; col++) {
					maze[row][col] = new Node(row, col, curLine.charAt(col));
					if (curLine.charAt(col) == 'S') {
						this.startX = row;
						this.startY = col;
					}
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
	public ArrayList<Node> getNeighbors(Node currentNode) {
		Node north, south, east, west;
		// 0. Initialize an ArrayList of nodes
		
		ArrayList<Node> neighbors = new ArrayList<Node>();

		// 1. Inspect the north neighbor
		north = maze[(currentNode.row)- 1][currentNode.col];
		if(!north.isWall() && !north.isVisited()) {
			neighbors.add(north);
			north.visited = true;
			north.parent = currentNode;
			
		}

		// 2. Inspect the south neighbor
		
		south = maze[(currentNode.row) + 1][currentNode.col];
		if(!south.isWall() && !south.isVisited()) {
			neighbors.add(south);
			south.visited = true;
			south.parent = currentNode;
		}

		// 3. Inspect the west neighbor
		
		west = maze[(currentNode.row)][currentNode.col-1];
		if(!west.isWall() && !west.isVisited()) {
			neighbors.add(west);
			west.visited = true;
			west.parent = currentNode;
		}

		// 4. Inspect the east neighbor
		
		east = maze[(currentNode.row)][currentNode.col+1];
		if(!east.isWall() && !east.isVisited()) {
			neighbors.add(east);
			east.visited = true;
			east.parent = currentNode;
		}

		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		Node curr_node;
		LinkedList<Node> q = new LinkedList<Node>();
		q.add(maze[startX][startY]);
		
		while(q.size() != 0) {
			curr_node = q.remove();
			ArrayList<Node> neighbors = getNeighbors(curr_node);
			for(int i = 0; i < neighbors.size(); i++) {
				q.add(neighbors.get(i));
			}
			
			if(curr_node.content == 'G') {
				backtrack(curr_node);
				break;
			}
		}
		
		writeOutput();
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		Node curr_node;
		Stack<Node> stack = new Stack<Node>();
		stack.push(maze[startX][startY]);
		
		while(!stack.empty()) {
			curr_node = stack.pop();
			ArrayList<Node> neighbors = getNeighbors(curr_node);
			for(int i = 0; i < neighbors.size(); i++) {
				stack.push(neighbors.get(i));
			}
			
			if(curr_node.content == 'G') {
				backtrack(curr_node);
				break;
			}
		}
		
		writeOutput();
	}

}
