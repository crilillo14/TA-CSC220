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
		while(end != null && end.content != 'S') {
			if(end.content != 'G') {
				end.content = '.';
			}
			end = end.parent;
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			 output.write(toString());
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
			
			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));
			String[] firstLine = input.readLine().split(" ");
			height = Integer.parseInt(firstLine[0]); // height
	        width = Integer.parseInt(firstLine[1]); // width
	        maze = new Node[height][width]; // creat maze
			// FILL IN
	        for (int i = 0; i < height; i++) {
	            String line = input.readLine();
	            for (int j = 0; j < width; j++) {
	                char c = line.charAt(j);
	                maze[i][j] = new Node(i, j, c);
	                
	                if (c == 'S') {
	                    startX = i;
	                    startY = j;
	                } // look for start spot
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
		
		ArrayList<Node> neighbors = new ArrayList<Node>();

	    int row = currentNode.row;
	    int col = currentNode.col;

		// 1. Inspect the north neighbor
	    if (inMaze(row - 1, height) && !maze[row - 1][col].isWall() && !maze[row - 1][col].isVisited()) {
	        north = maze[row - 1][col];
	        north.visited = true; // Mark as visited
	        north.parent = currentNode; // Set the parent to currentNode
	        neighbors.add(north); // Add to the list of neighbors
	    }
		// 2. Inspect the south neighbor
	    if (inMaze(row + 1, height) && !maze[row + 1][col].isWall() && !maze[row + 1][col].isVisited()) {
	        south = maze[row + 1][col];
	        south.visited = true; // Mark as visited
	        south.parent = currentNode; // Set the parent to currentNode
	        neighbors.add(south); // Add to the list of neighbors
	    }
		// 3. Inspect the west neighbor
	    if (inMaze(col - 1, width) && !maze[row][col - 1].isWall() && !maze[row][col - 1].isVisited()) {
	        west = maze[row][col - 1];
	        west.visited = true; // Mark as visited
	        west.parent = currentNode; // Set the parent to currentNode
	        neighbors.add(west); // Add to the list of neighbors
	    }
		// 4. Inspect the east neighbor
	    if (inMaze(col + 1, width) && !maze[row][col + 1].isWall() && !maze[row][col + 1].isVisited()) {
	        east = maze[row][col + 1];
	        east.visited = true; // Mark as visited
	        east.parent = currentNode; // Set the parent to currentNode
	        neighbors.add(east); // Add to the list of neighbors
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
		    boolean solved = false;
		    while (!queue.isEmpty()) {
		        Node currentNode = queue.poll();
		        if (currentNode.content == 'G') {
		        	solved = true;
		            backtrack(currentNode);
		            break; 
		        }
		        
		        ArrayList<Node> neighbors = getNeighbors(currentNode);
		        for (Node neighbor : neighbors) {
		        	neighbor.visited = true; 
		        	neighbor.parent = currentNode;
		        	queue.add(neighbor);
		        }
		        if(queue.isEmpty() && solved) {
		        	System.out.println("no solution");
		        }
		    }
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {	
		Stack<Node> stack = new Stack<>();
		Node startNode = maze[startX][startY];
	    startNode.visited = true; 
	    stack.push(startNode); 
	    boolean solved = false;
	    while (!stack.isEmpty()) {
	        Node currentNode = stack.pop();
	    if (currentNode.content == 'G') {
	    	solved = true;
	        backtrack(currentNode); 
	        break; 
	    }
	    ArrayList<Node> neighbors = getNeighbors(currentNode);
        for (Node neighbor : neighbors) {
                neighbor.visited = true; // Mark the neighbor as visited
                neighbor.parent = currentNode; // Set the parent of the neighbor to currentNode
                stack.push(neighbor); // Push the neighbor onto the stack for further exploration
            }
        if(stack.isEmpty() && solved) {
        	System.out.println("no solution");
        }
	  }
	}
	
}
