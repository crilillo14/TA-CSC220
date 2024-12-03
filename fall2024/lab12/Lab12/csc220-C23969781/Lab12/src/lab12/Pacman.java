package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;

//import lab10.MaxHeap;

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
		Node currentNode = end;
	    while (currentNode != null && currentNode.parent != null) {
	        if (currentNode.content != 'S' && currentNode.content != 'G') {
	            currentNode.content = '.';
	        }// end of if statement
	        currentNode = currentNode.parent;
	    }// end of while loop
		
		
	}// end of backtrack method

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			
			output.println(height + " " + width);
			
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
		// TODO for lab12
		try {
			// don't forget to close input when you're done
			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));
			// FILL IN
			
			String [] firstLine = input.readLine().split(" ");
			
			height = Integer.parseInt(firstLine[0]);
			width = Integer.parseInt(firstLine[1]);
			maze = new Node[height][width];
			
			for(int i = 0; i < height; i++) {
				String lineOfText = input.readLine();
				
				for(int j = 0; j < width; j++) {
					char character = lineOfText.charAt(j);
					maze[i][j] = new Node(i, j, character);
					
					if(maze[i][j].content == 'S') {
						startX = i;
						startY = j;
					}
					
				}// end of inner for loop
			
			}// end of outer for loop
			
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
		
		ArrayList<Node> neighborsList = new ArrayList<>();
	    int currentRow = currentNode.row;
	    int currentCol = currentNode.col;

	    // Inspect the north neighbor
	    if (inMaze(currentRow - 1, height)) {
	        Node northNeighbor = maze[currentRow - 1][currentCol];
	        if (!northNeighbor.isVisited() && !northNeighbor.isWall()) {
	            northNeighbor.visited = true;
	            northNeighbor.parent = currentNode;
	            neighborsList.add(northNeighbor);
	        }
	    }

	    // Inspect the south neighbor
	    if (inMaze(currentRow + 1, height)) {
	        Node southNeighbor = maze[currentRow + 1][currentCol];
	        if (!southNeighbor.isVisited() && !southNeighbor.isWall()) {
	        	southNeighbor.visited = true;
	            southNeighbor.parent = currentNode;	        	
	        	neighborsList.add(southNeighbor);
	        }
	    }

	    // Inspect the west neighbor
	    if (inMaze(currentCol - 1, width)) {
	        Node westNeighbor = maze[currentRow][currentCol - 1];
	        if (!westNeighbor.isVisited() && !westNeighbor.isWall()) {
	        	westNeighbor.visited = true;
	            westNeighbor.parent = currentNode;
	        	neighborsList.add(westNeighbor);
	        }
	    }

	    // Inspect the east neighbor
	    if (inMaze(currentCol + 1, width)) {
	        Node eastNeighbor = maze[currentRow][currentCol + 1];
	        if (!eastNeighbor.isVisited() && !eastNeighbor.isWall()) {
	        	eastNeighbor.visited = true;
	            eastNeighbor.parent = currentNode;
	        	neighborsList.add(eastNeighbor);
	        }
	    }

	    return neighborsList;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
	    Queue<Node> queue = new LinkedList<>(); 
	    Node startNode = maze[startX][startY];
	    Node goalNode = null;
	    
	    queue.add(startNode);
	    startNode.visited = true;
	    

	    while (!queue.isEmpty()) {
	        Node currentNode = queue.poll();
	        if (currentNode.content == 'G') { // Goal reached
	            //backtrack(current);
	            //return;
	        	goalNode = currentNode;
	        	break;
	        }// end of if
	        
	        ArrayList<Node> neighbors = getNeighbors(currentNode);
	        for (Node neighbor : neighbors) {
	        		queue.add(neighbor);
	        }// end of for
        }// end of while
	    
	    if(goalNode != null) {
	    	backtrack(goalNode);
	    }else {
	    	System.out.println("No solution");
	    }// end of if
		
	}// end of solveBFS method

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		
		Stack<Node> stack = new Stack<>();
	    Node startNode = maze[startX][startY];
	    startNode.visited = true;
	    stack.push(startNode);

	    while (!stack.isEmpty()) {
	        Node current = stack.pop();
	        if (current.content == 'G') { // Goal reached
	            backtrack(current);
	            return;
	        }// end of if

	        for (Node neighbor : getNeighbors(current)) {
	            neighbor.visited = true;
	            neighbor.parent = current;
	            stack.push(neighbor);
	        }// end of for loop
	    }// end of while loop
	}// end of solveDFS method

}//END
