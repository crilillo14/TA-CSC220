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
		Node curNode = end;
		
		while (curNode != null && curNode.content != 'S') {
			
			if (curNode.content != 'G') {
				
				curNode.content = '.';
			}
			curNode = curNode.parent;
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			
			output.write(height + " " + width + "\n");
			
			for (int row = 0; row < height; row++) {
				
				for (int col = 0; col < width; col++) {
					
					output.write(maze[row][col].content);
				}
				output.write("\n");
			}
			
			output.close();
			// FILL IN
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
			
			String line = input.readLine();
			String[] nums = line.split(" ");
			
			height = Integer.parseInt(nums[0]);	
			width = Integer.parseInt(nums[1]);
			maze = new Node[height][width];
			
			for (int row = 0; row < height; row++) {
				
				for (int col = 0; col < width; col++) {
					
					char nextChar = (char) input.read();
					if (nextChar == 'S') {					
						
						startX = row;
						startY = col;
					}
					maze[row][col] = new Node(row, col, nextChar);
					
				}
				input.readLine();
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
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> neighbors = new ArrayList<Node>();
		
		int	curRow = currentNode.row;
		int curCol = currentNode.col;

		// 1. Inspect the north neighbor
		if (inMaze(curRow - 1, height)) {
	        Node north = maze[curRow - 1][curCol];
	        if (!north.isWall() && !north.isVisited()) {
	            north.visited = true;
	            north.parent = currentNode;
	            neighbors.add(north);
	        }
	    }

		// 2. Inspect the south neighbor
		if (inMaze(curRow + 1, height)) {
	        Node south = maze[curRow + 1][curCol];
	        if (!south.isWall() && !south.isVisited()) {
	            south.visited = true;
	            south.parent = currentNode;
	            neighbors.add(south);
	        }
	    }

		// 3. Inspect the west neighbor
		if (inMaze(curCol - 1, width)) {
	        Node west = maze[curRow][curCol - 1];
	        if (!west.isWall() && !west.isVisited()) {
	            west.visited = true;
	            west.parent = currentNode;
	            neighbors.add(west);
	        }
	    }

		// 4. Inspect the east neighbor
		if (inMaze(curCol + 1, width)) {
	        Node east = maze[curRow][curCol + 1];
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
		// TODO for assignment12
		Queue<Node> queue = new LinkedList<>();
		
		Node startNode = maze[startX][startY];
		startNode.visited = true;
		queue.add(startNode);
		
		while (!queue.isEmpty()) {
			
			Node curNode = queue.poll();
			
			if (curNode.content == 'G') {
				
				backtrack(curNode);
				writeOutput();
				return;
			}
		
			ArrayList<Node> neighbors = getNeighbors(curNode);
		
			for (Node neighbor : neighbors) {
			
				queue.add(neighbor);
			}
		}
		
		for (int row = 0; row < height; row++) {
	        for (int col = 0; col < width; col++) {
	            Node node = maze[row][col];
	            if (node.content == '.') {
	                node.content = ' ';
	            }
	        }
	    }
		
		writeOutput();
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		Stack<Node> stack = new Stack<>();
		
		Node startNode = maze[startX][startY];
	    startNode.visited = true;
	    stack.push(startNode);
	    
	    while (!stack.isEmpty()) {
	    	
	        Node currentNode = stack.pop();

	        if (currentNode.content == 'G') {
	        	
	            backtrack(currentNode);
	            writeOutput();
	            return;
	        }
	        
	        ArrayList<Node> neighbors = getNeighbors(currentNode);
	        
	        for (int i = neighbors.size() - 1; i >= 0; i--) {
	            stack.push(neighbors.get(i));
	        }
	    }
	    
	    for (int row = 0; row < height; row++) {
	        for (int col = 0; col < width; col++) {
	            Node node = maze[row][col];
	            if (node.content == '.') {
	                node.content = ' ';
	            }
	        }
	    }
		
		writeOutput();
	}
}
