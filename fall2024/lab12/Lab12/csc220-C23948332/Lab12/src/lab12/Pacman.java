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
		ArrayList<Node> solutionPath = new ArrayList<>();
		Node currentNode = end;
	
		while(currentNode != null) {
			solutionPath.add(currentNode);
			currentNode = currentNode.parent;
		}
		
		for (Node pathNode : solutionPath) {
	        if (pathNode.content != 'S' && pathNode.content != 'G') { 
	            pathNode.content = '.';
	        }
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			//output.print(maze[i][j].content + " ");
			output.print(height + " " + width);
			
			for (int i = 0; i < height; i++){
				for(int j = 0; j < width; j++) {
					output.print(maze[i][j].content);
					
				}
				output.println();
				
			}
			
			
		 
			output.close();
			
			
		}catch(IOException e) {
			e.printStackTrace();	
	
		}
	}

	public String toString() {
		String s = "";
		s += height + " " + width + "\n";
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				s += maze[i][j].content + "";
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
			//reading first line, read the height and width 
//			height = input.read();
//			width = input.read();
			
			String [] firstLine = input.readLine().split(" ");
			height = Integer.parseInt(firstLine[0]);
			width = Integer.parseInt(firstLine[1]);
			maze = new Node[height][width];
			
		
			
			//reading each line and getting each node and putting into our array 
			for(int i = 0; i < height; i++) {
				String readTextOfLine = input.readLine();
				//String [] valuesOfArray = readTextOfLine.split("");
				for(int j = 0; j < width; j++) {
					char character = readTextOfLine.charAt(j);
					maze[i][j] = new Node(i ,j, character);
					
		
					
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
		ArrayList<Node> neighbors = new ArrayList<>();
		int row = currentNode.row;
	    int collumn = currentNode.col;

		

		// 1. Inspect the north neighbor
	    if (inMaze(row - 1, height) && !maze[row - 1][collumn].isWall() && !maze[row - 1][collumn].isVisited()) {
	        north = maze[row - 1][collumn];
	        north.visited = true; // Mark as visited
	        north.parent = currentNode; // Set parent
	        neighbors.add(north);
	    }
		

		// 2. Inspect the south neighbor
	    if (inMaze(row + 1, height) && !maze[row + 1][collumn].isWall() && !maze[row + 1][collumn].isVisited()) {
	        south = maze[row + 1][collumn];
	        south.visited = true;
	        south.parent = currentNode;
	        neighbors.add(south);
	    }
	    

		// 3. Inspect the west neighbor
	    if (inMaze(collumn - 1, width) && !maze[row][collumn - 1].isWall() && !maze[row][collumn - 1].isVisited()) {
	        west = maze[row][collumn - 1];
	        west.visited = true;
	        west.parent = currentNode;
	        neighbors.add(west);
	    }

		// 4. Inspect the east neighbor
	    
	    if (inMaze(collumn + 1, width) && !maze[row][collumn + 1].isWall() && !maze[row][collumn + 1].isVisited()) {
	        east = maze[row][collumn + 1];
	        east.visited = true;
	        east.parent = currentNode;
	        neighbors.add(east);
	    }


		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		 Queue<Node> queue = new LinkedList<>();
		    
		    // Find the start node and enqueue it
		    Node startNode = null;
		    Node goalNode = null;
		    for (int i = 0; i < height; i++) {
		        for (int j = 0; j < width; j++) {
		            if (maze[i][j].content == 'S') {
		                startNode = maze[i][j];
		                break;
		            }
		        }
		        
		        //if (startNode != null) break;
		    }
//		    if (startNode == null) {
//		        System.out.println("Error: No start node found!");
//		        return;
//		    }

		    queue.add(startNode);
		    startNode.visited = true;

		    // BFS loop
		    while (!queue.isEmpty()) {
		        Node currentNode = queue.poll();

		        // Check if we've reached the goal
		        if (currentNode.content == 'G') {
		            goalNode = currentNode;
		            break;
		        }

		        // Get neighbors and process them
		        ArrayList<Node> neighbors = getNeighbors(currentNode);
		        for (Node neighbor : neighbors) {
		            queue.add(neighbor);
		        }
		    }

		    // If a goal was found, backtrack to find the solution path
		    if (goalNode != null) {
		        backtrack(goalNode);
		    } else {
		        System.out.println("No solution found!");
		    }
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		  Stack<Node> stack = new Stack<>();

		    // Find the start node and push it onto the stack
		    Node startNode = null;
		    Node goalNode = null;
		    for (int i = 0; i < height; i++) {
		        for (int j = 0; j < width; j++) {
		            if (maze[i][j].content == 'S') {
		                startNode = maze[i][j];
		                break;
		            }
		        }
		        if (startNode != null) break;
		    }
		    if (startNode == null) {
		        System.out.println("Error: No start node found!");
		        return;
		    }

		    stack.push(startNode);
		    startNode.visited = true;

		    // DFS loop
		    while (!stack.isEmpty()) {
		        Node currentNode = stack.pop();

		        // Check if we've reached the goal
		        if (currentNode.content == 'G') {
		            goalNode = currentNode;
		            break;
		        }

		        // Get neighbors and process them
		        ArrayList<Node> neighbors = getNeighbors(currentNode);
		        for (Node neighbor : neighbors) {
		            stack.push(neighbor);
		        }
		    }

		    // If a goal was found, backtrack to find the solution path
		    if (goalNode != null) {
		        backtrack(goalNode);
		    } 
//		    else {
//		        System.out.println("No solution found!");
//		    }
			
		}

	}

