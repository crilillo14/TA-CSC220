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
		while(current != null) {
			if (current.content != 'S' && current.content != 'G') {
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
			output.println(height + " " + width);
			for (int row = 0; row < height; row++) {
				for (int col = 0; col < width; col++) {
					output.print(maze[row][col].content);
				}
				output.println();
	            }
			output.close();
			// FILL IN
		} catch(IOException e) {
			System.out.println("We got an error");
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
			String[] dimensions = input.readLine().split(" ");
			height = Integer.parseInt(dimensions[0]);
			width = Integer.parseInt(dimensions[1]);
			maze = new Node[height][width];
			
			for (int row = 0; row < height; row++) {
				String line = input.readLine();
				for (int col = 0; col < width; col++) {
					char content = line.charAt(col);
					maze[row][col] = new Node(row,col,content);
					if (content == 'S') {
						startX = row;
						startY = col;
					}
				}
			}
      
			// FILL IN

		}
		catch (IOException e) {
			System.out.println("Error reading input file.");
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
		ArrayList<Node> neighbors = new ArrayList<>();
	    

	    // Check North
	    if (inMaze(currentNode.row - 1, height) && maze[currentNode.row - 1][currentNode.col].content != 'X' && !maze[currentNode.row - 1][currentNode.col].visited) {
	        neighbors.add(maze[currentNode.row - 1][currentNode.col]);
	        
	    }

	    // Check South  (Corrected bound check)
	    if (inMaze(currentNode.row + 1, height) && maze[currentNode.row + 1][currentNode.col].content != 'X' && !maze[currentNode.row + 1][currentNode.col].visited) {
	        neighbors.add(maze[currentNode.row + 1][currentNode.col]);
	        
	    }

	    // Check West
	    if (inMaze(currentNode.row, width) && maze[currentNode.row][currentNode.col - 1].content != 'X' && !maze[currentNode.row][currentNode.col - 1].visited) {
	        neighbors.add(maze[currentNode.row][currentNode.col - 1]);
	        
	    }

	    // Check East (Corrected bound check)
	    if (inMaze(currentNode.row, width) && maze[currentNode.row][currentNode.col + 1].content != 'X' && !maze[currentNode.row][currentNode.col + 1].visited) {
	        neighbors.add(maze[currentNode.row][currentNode.col + 1]);
	        
	    }

	    return neighbors;
		
		
		// 0. Initialize an ArrayList of nodes

		// 1. Inspect the north neighbor

		// 2. Inspect the south neighbor

		// 3. Inspect the west neighbor

		// 4. Inspect the east neighbor

	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		Queue<Node> queue = new LinkedList<>();
		Node startNode = maze[startX][startY];
		queue.add(startNode);
		startNode.visited = true;


		
		while(!queue.isEmpty()) {
			Node currentNode = queue.poll();
			
			if (currentNode.content == 'G') {
				backtrack(currentNode);
				writeOutput();
				return;
			}
			ArrayList<Node> neighbors = getNeighbors(currentNode);
			for(int i = 0; i < neighbors.size(); i++) {
				Node neighbor = neighbors.get(i);
				if (!neighbor.visited) {
					neighbor.visited = true;
					neighbor.parent = currentNode;
					queue.add(neighbor);
				}else{
					System.out.println("Already visited neighbor (" + neighbor.row + ", " + neighbor.col + ")");
				}
			}
		}
		System.out.println("Goal not found.");
		// TODO for assignment12
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		Stack<Node> stack = new Stack<>();
	    

	    Node startNode = maze[startX][startY];
	    stack.push(startNode);
	    startNode.visited = true;
	    

	    while (!stack.isEmpty()) {
	       
	        Node currentNode = stack.pop();
	        
	       
	        if (currentNode.content == 'G') {
	            backtrack(currentNode); 
	            writeOutput();
	           
	            return; 
	        }
	        
	        // Step 6: Get neighbors of the current node
	        ArrayList<Node> neighbors = getNeighbors(currentNode);
	        
	        // Step 7: For each neighbor that has not been visited, mark it as visited, set its parent, and add it to the stack
	        for (int i = 0; i < neighbors.size(); i++) {
	            Node neighbor = neighbors.get(i);
	            if (!neighbor.visited) {
	                neighbor.visited = true;
	                neighbor.parent = currentNode;  // Set the current node as the parent of this neighbor
	                stack.push(neighbor);
	                
                }
	            }
	        }
	    }
		
		// TODO for assignment12
	}

