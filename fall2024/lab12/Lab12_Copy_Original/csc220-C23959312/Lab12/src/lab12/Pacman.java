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
		
		Node current = end;
		
		while (current != null && current.content != 'S') {
			
			 if (current.content != 'G') {
	                current.content = '.';
			 }
			
			current = current.parent;
		}
		
		
		// TODO for assignment12
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			
			output.write(height + " " + width + "\n");
			
			for (int i = 0; i < maze.length; i ++) {
				for (int j = 0; j < maze[i].length; j++) {
					output.write(maze[i][j].content);
				}
				output.write("\n");
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
			
		
			String height_width  = input.readLine();
			
            String[] values  = height_width.split(" ");	
            
            height = Integer.parseInt(values[0]);
            
            width = Integer.parseInt(values[1]);
                        
            maze = new Node[height][width];
            
        	int row = 0;
            
        	String newLine = input.readLine();

			
            while (newLine != null) {
         
            	
            	for (int col = 0; col < newLine.length(); col++) {
            		if (newLine.charAt(col) == 'S'){
            			startX= row;
            			startY = col;
            			
            		}
            		maze[row][col]= new Node (row,col, newLine.charAt(col));
            		
            	}
                newLine = input.readLine();

            	
            	row++;
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
		
		ArrayList<Node> neighbors = new ArrayList<Node>();

		
		if (inMaze(currentNode.row - 1, height)) {
             north = maze[currentNode.row - 1][currentNode.col];
            if (!north.isVisited() && !north.isWall()) {
                neighbors.add(north);
            }
        }
        
        if (inMaze(currentNode.row + 1, height)) {
             south = maze[currentNode.row + 1][currentNode.col];
            if (!south.isVisited() && !south.isWall()) {
                neighbors.add(south);
            }
        }
        
        if (inMaze(currentNode.col - 1, width)) {
             west = maze[currentNode.row][currentNode.col - 1];
            if (!west.isVisited() && !west.isWall()) {
                neighbors.add(west);
            }
        }
        
        if (inMaze(currentNode.col + 1, width)) {
             east = maze[currentNode.row][currentNode.col + 1];
            if (!east.isVisited() && !east.isWall()) {
                neighbors.add(east);
            }
        }

		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {		
		
		// TODO for assignment12
		
		Queue<Node> newQueue = new LinkedList<>();
		
		Node startNode = maze[startX][startY];
		
		startNode.visited = true;
		
		newQueue.offer(startNode);
		
		
		Node goal = null;
		
		while(!newQueue.isEmpty() && goal == null) {
			Node current = newQueue.poll();
			
			if (current.content == 'G') {
				goal = current;
				break;
			}
			
			ArrayList<Node> neighbors = getNeighbors(current);
			
			for (Node neighbor : neighbors) {
				neighbor.visited = true;
				neighbor.parent = current;
				newQueue.offer(neighbor);
			}
			
			
			
			}
		
		if (goal != null) {
			backtrack(goal);
		}
		
		writeOutput();
		
		
		
		

	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		
		Stack<Node> newStack = new Stack<>();
		
		Node startNode = maze[startX][startY];
		
		startNode.visited = true;
		
		newStack.push(startNode);
			
		Node goal = null;
		
		while(!newStack.isEmpty() && goal == null) {
			Node current = newStack.pop();
			
			if (current.content == 'G') {
				goal = current;
				break;
			}
			
		ArrayList<Node> neighbors = getNeighbors(current);
		
		for (Node neighbor : neighbors) {
			neighbor.visited = true;
			neighbor.parent = current;
			newStack.push(neighbor);
		}
			
			
		}
		
		if (goal != null) {
			backtrack(goal);
		}
		
		
		
		
	}

}
