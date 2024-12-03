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
		//Base case
		if (end.content == 'S') {
	        return;
	    }
		//Not start or end node
	    if (end.content != 'S' && end.content != 'G') {
	        end.content = '.';
	    }
	    //recusion
	    
	    backtrack(end.parent);
	}
		// TODO for assignment12
	

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
			for(int i = 0; i < height; i++) {
				for(int j = 0; j < width; j++) {
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
	    try {
	        // Open the input file
	        BufferedReader input = new BufferedReader(new FileReader(inputFileName));

	        //height and width of the maze
	        String bounds = input.readLine();
	        String[] array = bounds.split("\\s+");
	        height = Integer.parseInt(array[0]);
	        width = Integer.parseInt(array[1]);

	        // Initialize the maze
	        maze = new Node[height][width];

	        // Read the maze content
	        for (int i = 0; i < height; i++) {
	            String newLine = input.readLine().toUpperCase();
	            char[] charArray = newLine.toCharArray();
	            for (int j = 0; j < width; j++) {
	                Node index = new Node(i, j, charArray[j]);
	                if (index.content == 'S') {
	                    startX = i;
	                    startY = j;
	                }
	                maze[i][j] = index;
	            }
	        }

	        // Close the input stream after processing all lines
	        input.close();
	        System.out.println("Maze dimensions: " + height + "x" + width);
	        for (int i = 0; i < height; i++) {
	            for (int j = 0; j < width; j++) {
	                System.out.print(maze[i][j].content);
	            }
	            System.out.println();
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
		ArrayList<Node> neighbors = new ArrayList<Node>();
		// 1.0 Initialize the north node
		if (inMaze(currentNode.row - 1, height)) {
	        north = maze[currentNode.row - 1][currentNode.col];
	        if (!north.isVisited() && !north.isWall()) {
	            
	            north.parent = currentNode; // Set parent
	            neighbors.add(north);
	        }
	    }

	    // 2.0 Initialize the south node
	    if (inMaze(currentNode.row + 1, height)) {
	        south = maze[currentNode.row + 1][currentNode.col];
	        if (!south.isVisited() && !south.isWall()) {
	            
	            south.parent = currentNode; // Set parent
	            neighbors.add(south);
	        }
	    }

	    // 3.0 Initialize the west node
	    if (inMaze(currentNode.col - 1, width)) {
	        west = maze[currentNode.row][currentNode.col - 1];
	        if (!west.isVisited() && !west.isWall()) {
	            
	            west.parent = currentNode; // Set parent
	            neighbors.add(west);
	        }
	    }

	    // 4.0 Initialize the east node
	    if (inMaze(currentNode.col + 1, width)) {
	        east = maze[currentNode.row][currentNode.col + 1];
	        if (!east.isVisited() && !east.isWall()) {
	            
	            east.parent = currentNode; // Set parent
	            neighbors.add(east);
	        }
	    }
	    return neighbors;//returns da array list #freak
	}
	

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		//Queue
			Queue<Node> queue = new LinkedList();
			Node current = maze[startX][startY];
			current.parent = null;
			//Gets the neighbor
			ArrayList<Node> neighbors; 
					
			queue.add(current);
			while(!queue.isEmpty()) {
				current = queue.poll();
				if(current.content == 'G') {
					System.out.println(current.row + " " + current.col);
					backtrack(current);
					break;
				}else {
					neighbors = getNeighbors(current);
					for(int i = 0; i < neighbors.size(); i++) {
						queue.add(neighbors.get(i));
						neighbors.get(i).visited = true;
						
					}
					
				}
			}
		
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		//stack
		Stack<Node> stack = new Stack();
		
		Node current = maze[startX][startY];
		current.parent = null;
		//Gets the neighbor
		ArrayList<Node> neighbors; 
				
		stack.push(current);
		while(!stack.isEmpty()) {
			current = stack.pop();
			if(current.content == 'G') {
				System.out.println(current.row + " " + current.col);
				backtrack(current);
				break;
			}else {
				neighbors = getNeighbors(current);
				for(int i = 0; i < neighbors.size(); i++) {
					stack.push(neighbors.get(i));
					neighbors.get(i).visited = true;
					
				}
				
			}
		}
	
	}
		
}


