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
	/** starting (X,Y) position of  */
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
		
		if(current!=null && current.content!='S') {
			current.content='*';
			current = current.parent;
		
	}
}

	public void writeOutput() {
		try (PrintWriter output = new PrintWriter(new FileWriter(outputFileName))) {
		    for (int row = 0; row < height; row++) {
		        for (int col = 0; col < width; col++) {
		            System.out.print(maze[row][col].content);
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
		try {
		    BufferedReader input = new BufferedReader(new FileReader(inputFileName));
		    ArrayList<String> lines = new ArrayList<>();
		    String line;

		    // Read all lines from the file
		    while ((line = input.readLine()) != null) {
		        line = line.trim(); // Trim whitespace
		        if (!line.isEmpty()) { // Skip empty lines
		            lines.add(line);
		        }
		    }
		    input.close();

		    // Ensure all rows have the same length
		    int maxWidth = 0;
		    for (String row : lines) {
		        maxWidth = Math.max(maxWidth, row.length());
		    }
		    for (int i = 0; i < lines.size(); i++) {
		        String row = lines.get(i);
		        if (row.length() < maxWidth) {
		            row = String.format("%-" + maxWidth + "s", row); // Pad with spaces
		            lines.set(i, row);
		        }
		    }

		    // Proceed to build the graph using the normalized `lines` list
		    height = lines.size();
		    width = maxWidth;

		    maze = new Node[height][width];
		    for (int row = 0; row < height; row++) {
		        line = lines.get(row);
		        for (int col = 0; col < width; col++) {
		            char charAt = line.charAt(col);
		            maze[row][col] = new Node(row, col, charAt);

		            if (charAt == 'S') {
		                startX = row;
		                startY = col;
		            }
		        }
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
		int row = currentNode.row;
		int col = currentNode.col;
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> someNode = new ArrayList<Node>();
		if(inMaze(row-1,height)==true){
			north = maze[row-1][col];
			if(!north.isVisited()&&!north.isWall()) {
			someNode.add(north);
		}
			}
		if(inMaze((row+1),height)==true) {
			south = maze[row+1][col];
			if(!south.isVisited()&&!south.isWall()) {
			someNode.add(south);
			}
		}
		if(inMaze((col+1),width)==true) {
			east = maze[row][col+1];
			if(!east.isWall() && !east.isVisited())
			someNode.add(east);
		}	
		if(inMaze((col-1) , width)==true){
			west = maze[row][col-1];
			if(!west.isWall() && !west.isVisited()) {
			someNode.add(west);
		}
	}
		
		// 1. Inspect the north neighbor
		// 2. Inspect the south neighbor

		// 3. Inspect the west neighbor

		// 4. Inspect the east neighbor

		return someNode;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		Queue<Node> queue = new LinkedList<Node>();
		
		Node startNode = maze[startX][startY];
		
		startNode.visited=true;
		
		queue.add(startNode);
			
			
			while(!queue.isEmpty()) {
								
				Node NeighborNode = queue.poll();
				
				for (Node neighbor : getNeighbors(NeighborNode)) {
		            if (!neighbor.visited) {
		                neighbor.visited = true;
		                neighbor.parent = NeighborNode;
		                queue.add(neighbor);
		            } else if(NeighborNode.content=='G') {
					this.backtrack(NeighborNode);
					this.writeOutput();
					return;
			} else {
				writeOutput();
				return;
			}
		}
		}}
	


	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		Stack<Node> queue = new Stack<Node>();
		
		Node startNode = maze[startX][startY];
		
		startNode.visited=true;
		
		queue.add(startNode);
		
		while(!queue.isEmpty()) {
						
			Node current = 	queue.pop();
			
			  for(Node neighbor : getNeighbors(current)) {
					
				  if(!neighbor.isWall() || !neighbor.isVisited()) {
					  
					  neighbor.visited=true;
					  
					  neighbor.parent = current;
					  
					  queue.add(neighbor);
					  
				  } else if (neighbor.content=='G') {
					  
					 this.backtrack(neighbor);
					  
					  this.writeOutput();
					  return;
					  
				  } else {
					  this.writeOutput();
					  return;
				  }

			  }
			}
		}
	}

