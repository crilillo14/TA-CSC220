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
	private void backtrack(Node end) {
	    Node current = end;

	   
	    while (current != null && current.content != 'S') {
	        
	        if (current.content != 'G') {
	            current.content = '.'; 
	        }

	        
	        current = current.parent;
	    }
	}
	

	/** writes the solution to file */
	public void writeOutput() {
	    try (PrintWriter output = new PrintWriter(new FileWriter(outputFileName))) {
	        output.println(height + " " + width);
	        for (int i = 0; i < height; i++) {
	            for (int j = 0; j < width; j++) {
	                output.print(maze[i][j].content);
	            }
	            output.println();
	        }
	    } catch (IOException e) {
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
			
			BufferedReader input = new BufferedReader(new FileReader(inputFileName));
			
			// FILL IN
			
			String[] dimensions = input.readLine().split(" ");
			
			this.height = Integer.parseInt(dimensions[0]);
			this.width = Integer.parseInt(dimensions[1]);
			
			maze = new Node[height][width];
			
		    
	        for (int i = 0; i < height; i++) {
	        	
	            String line = input.readLine();
	            
	            for (int j = 0; j < width; j++) {
	            	
	                char cell = line.charAt(j);
	                
	               
	                maze[i][j] = new Node(i, j, cell);
	                
	                
	                if (cell == 'S') {
	                    startX = i;
	                    startY = j;
	                }
	                

	            } 
	        } 

	        
	        input.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	} // end of buildGraph

	/** obtains all neighboring nodes that have *not* been
	 *  visited yet from a given node when looking at the four
	 *  cardinal directions: North, South, West, East (IN THIS ORDER!)
	 *
	 * @param currentNode the given node
	 * @return an ArrayList of the neighbors (i.e., successors)
	 */
	
	public ArrayList<Node> getNeighbors(Node currentNode) {
	    // 0. Initialize an ArrayList of nodes
	    ArrayList<Node> neighbors = new ArrayList<>();

	    int x = currentNode.row;
	    int y = currentNode.col;

	    int northX = x - 1, northY = y; // North neighbor
	    int southX = x + 1, southY = y; // South neighbor
	    int westX = x, westY = y - 1;   // West neighbor
	    int eastX = x, eastY = y + 1;   // East neighbor

	    // 1. Inspect the north neighbor
	    if (inMaze(northX, height) && inMaze(northY, width)) {
	        Node northNeighbor = maze[northX][northY];
	        if (!northNeighbor.isWall() && !northNeighbor.isVisited()) {
	            neighbors.add(northNeighbor);
	        }
	    }

	    // 2. Inspect the south neighbor
	    if (inMaze(southX, height) && inMaze(southY, width)) {
	        Node southNeighbor = maze[southX][southY];
	        if (!southNeighbor.isWall() && !southNeighbor.isVisited()) {
	            neighbors.add(southNeighbor);
	        }
	    }

	    // 3. Inspect the west neighbor
	    if (inMaze(westX, height) && inMaze(westY, width)) {
	        Node westNeighbor = maze[westX][westY];
	        if (!westNeighbor.isWall() && !westNeighbor.isVisited()) {
	            neighbors.add(westNeighbor);
	        }
	    }

	    // 4. Inspect the east neighbor
	    if (inMaze(eastX, height) && inMaze(eastY, width)) {
	        Node eastNeighbor = maze[eastX][eastY];
	        if (!eastNeighbor.isWall() && !eastNeighbor.isVisited()) {
	            neighbors.add(eastNeighbor);
	        }
	    }

	    return neighbors;
	}


	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		Queue<Node> queue = new LinkedList<>();
	    Node start = maze[startX][startY];
	    start.visited = true;
	    queue.add(start);

	    while (!queue.isEmpty()) {
	        Node current = queue.poll();

	        if (current.content == 'G') { 
	            backtrack(current);
	            return;
	        }

	        for (Node neighbor : getNeighbors(current)) {
	            neighbor.visited = true;
	            neighbor.parent = current;
	            queue.add(neighbor);
	        }
	    }
	    
	    
	} // end of bfs




	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
	    Stack<Node> stack = new Stack<>();
	    Node start = maze[startX][startY];
	    start.visited = true;
	    stack.push(start);

	    while (!stack.isEmpty()) {
	        Node current = stack.pop();

	        if (current.content == 'G') { 
	            backtrack(current);
	            return;
	        }

	        for (Node neighbor : getNeighbors(current)) {
	            neighbor.visited = true;
	            neighbor.parent = current;
	            stack.push(neighbor);
	        }
	    }
	    
	} // end of dfs

}
