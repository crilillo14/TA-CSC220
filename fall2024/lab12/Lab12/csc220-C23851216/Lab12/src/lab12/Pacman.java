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
	    public void setVisited(boolean visited) { this.visited = visited; }
        public void setParent(Node parent) { this.parent = parent; }
        public Node getParent() { return parent; }
        public void setContent(char content) { this.content = content; }
        public boolean isEndNode() { return content == 'G'; }
        public int getX() { return row; }
        public int getY() { return col; }
    

		
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

	    while (current != null) {
	        current.setContent('.'); 
	        current = current.getParent(); 
	    }
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			for (int i = 0; i < height; i++) {
	            StringBuilder row = new StringBuilder();
	            for (int j = 0; j < width; j++) {
	                row.append(maze[i][j].content);
	            }
	            output.println(row);
	        }
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
	public void buildGraph() {
		// TODO for lab12
		    try {
		    	String mazeFiles; 
		        BufferedReader input = new BufferedReader(new FileReader(inputFileName));
		        System.out.println("Reading file: " + inputFileName);
		        
		        String line = input.readLine();
		        if (line == null) {
		            System.err.println("Error: The file is empty or incorrectly formatted.");
		            return;
		        }

		        System.out.println("File dimensions: " + line); // Print the dimensions
		        String[] dimensions = line.split(" ");
		        int height = Integer.parseInt(dimensions[0]);
		        int width = Integer.parseInt(dimensions[1]);
		        maze = new Node[height][width];

		        // Now read each line of the maze
		        for (int i = 0; i < height; i++) {
		            line = input.readLine();
		            if (line == null) {
		                System.err.println("Error: Maze data is incomplete.");
		                return;
		            }
		            System.out.println(line);
		            for (int j = 0; j < width; j++) {
		                char content = line.charAt(j);
		                Node node = new Node(i, j, content);
		                maze[i][j] = node;

		                if (content == 'S') {
		                    startX = i;
		                    startY = j;
		                }
		            }
		        }
		        input.close();
		        System.out.println("Maze loaded successfully.");
		    } catch (IOException e) {
		        System.err.println("Error reading the input file: " + e.getMessage());
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
		 int x = currentNode.getX();
		 int y = currentNode.getY();
		// 1. Inspect the north neighbor
		 
		 if (x + 1 < height && !maze[x + 1][y].isWall() && !maze[x + 1][y].isVisited()) {
			 	north = maze[x - 1][y];
			 	maze[x - 1][y].setVisited(true);
		        maze[x - 1][y].setParent(currentNode);
		        neighbors.add(north);
		        }
		    
		// 2. Inspect the south neighbor
		 if (y - 1 >= 0 && !maze[x][y - 1].isWall() && !maze[x][y - 1].isVisited()) {
			 	south =  maze[x + 1][y];
			 	maze[x + 1][y].setVisited(true);
		        maze[x + 1][y].setParent(currentNode);
		        neighbors.add(south);
		        }
		    

		// 3. Inspect the west neighbor
		 if (y + 1 < width && !maze[x][y + 1].isWall() && !maze[x][y + 1].isVisited()) {
			 	west = maze[x][y - 1];
			 	maze[x][y - 1].setVisited(true);
		        maze[x][y - 1].setParent(currentNode);
		        neighbors.add(west);
		    
		 }

		// 4. Inspect the east neighbor
		 if (x >= 0 && x < height && y >= 0 && y < width && !maze[x][y].isWall()) {
			 	east = maze[x][y + 1];
			 	maze[x][y + 1].setVisited(true);
		        maze[x][y + 1].setParent(currentNode);
		        neighbors.add(east);
		    
		 }
		 
		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		 Queue<Node> queue = new LinkedList<>();
		    Node start = maze[startX][startY];
		    start.setVisited(true);
		    queue.add(start);

		    while (!queue.isEmpty()) {
		        Node current = queue.poll();

		        if (current.isEndNode()) {
		            backtrack(current); // Trace the path back to the start
		            writeOutput(); // Output the solution
		            return;
		        }

		        ArrayList<Node> neighbors = getNeighbors(current);
		        queue.addAll(neighbors); // Add neighbors to the queue
		    }
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		Stack<Node> stack = new Stack<>();
	    Node start = maze[startX][startY];
	    start.setVisited(true);
	    stack.push(start);

	    while (!stack.isEmpty()) {
	        Node current = stack.pop();

	        if (current.isEndNode()) {
	            backtrack(current); // Trace the path back to the start
	            writeOutput(); // Output the solution
	            return;
	        }

	        ArrayList<Node> neighbors = getNeighbors(current);
	        for (Node neighbor : neighbors) {
	            stack.push(neighbor); // Add neighbors to the stack
	        }
	    }
	}

}
