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
		Node current = end;
		
		while(current.parent!=null) {
			if(current.content == ' ') {
				current.content = '.';
			}
			current = current.parent;
		}
		
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			
			output.println(height+" "+ width);
			for (int i =0;i<height;i++) {
				for(int j= 0; j<width;j++) {
					output.print(this.maze[i][j].content);
				}
				output.println();
			}
			
			output.flush();
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
			String sizeLine = input.readLine();
			int place = sizeLine.indexOf(" ");
			
			String p1 = sizeLine.substring(0,place);
			String p2 = sizeLine.substring(place+1, sizeLine.length());
			this.height = Integer.parseInt(p1);
			this.width = Integer.parseInt(p2);
			
			this.maze = new Node[height][width];
			for(int i = 0; i<height; i++) {
				String line = input.readLine();
				for(int j= 0; j<width;j++) {
					char c = line.charAt(j);
					this.maze[i][j] = new Node(i,j,c);
					if(c == 'S') {
						this.startX = i;
						this.startY = j;
					}
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
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> direction = new ArrayList<Node>();
		// 1. Inspect the north neighbor
		if(this.maze[currentNode.row-1][currentNode.col].content != 'X'&& !this.maze[currentNode.row-1][currentNode.col].visited) {
			direction.add(this.maze[currentNode.row-1][currentNode.col]);
		}
			
		// 2. Inspect the south neighbor
		if(this.maze[currentNode.row+1][currentNode.col].content != 'X'&& !this.maze[currentNode.row+1][currentNode.col].visited) {
			direction.add(this.maze[currentNode.row+1][currentNode.col]);
		}
		// 3. Inspect the west neighbor
		if(this.maze[currentNode.row][currentNode.col-1].content != 'X'&& !this.maze[currentNode.row][currentNode.col-1].visited) {
			direction.add(this.maze[currentNode.row][currentNode.col-1]);
		}
		// 4. Inspect the east neighbor
		if(this.maze[currentNode.row][currentNode.col+1].content != 'X'&& !this.maze[currentNode.row][currentNode.col+1].visited) {
			direction.add(this.maze[currentNode.row][currentNode.col+1]);
		}
		return direction;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		LinkedList <Node> queue = new LinkedList<Node>();
		queue.add(maze[startX][startY]);
		maze[startX][startY].visited = true;
		while(!queue.isEmpty()) {
			Node n = queue.poll();
			ArrayList<Node> valid = this.getNeighbors(n);
			for(int i = 0;i<valid.size();i++) {
				if(valid.get(i).content== 'G') {
					valid.get(i).parent = n;
					this.backtrack(valid.get(i));
					return;
				}
				valid.get(i).parent = n;
				valid.get(i).visited = true;
				queue.add(valid.get(i));
			}
		}
		
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		Stack<Node> stack = new Stack<Node>();
		
		stack.push(maze[startX][startY]);
		maze[startX][startY].visited = true;
		while(!stack.isEmpty()) {
			Node n = stack.pop();
			ArrayList<Node> valid = this.getNeighbors(n);
			for(int i = 0;i<valid.size();i++) {
				if(valid.get(i).content== 'G') {
					valid.get(i).parent = n;
					valid.get(i).visited = true;
					this.backtrack(valid.get(i));
					return;
				}
				valid.get(i).parent = n;
				valid.get(i).visited = true;
				stack.push(valid.get(i));
			}
		
		}
	}
}
