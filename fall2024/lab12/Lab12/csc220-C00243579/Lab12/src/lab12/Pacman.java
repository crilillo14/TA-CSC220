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
		 Node current = end;

		 // Traverse back from the end node to the start node using the parent pointers
		 while (current != null) {
		     // Avoid overwriting the start ('S') and goal ('G')
	         if (current.content != 'S' && current.content != 'G') {
	             current.content = '.';
	         }
		     // Move to the parent node
		     current = current.parent;
		 }
	}

	/** writes the solution to file */
	public void writeOutput() {
	          // TODO for lab12
	      try {
		        PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
		        // FILL IN
		        // Write the height and width as the first line
                output.println(height + " " + width);
 
                // Loop through the maze to write each row
           		for (int i = 0; i < height; i++) {
                	 StringBuilder line = new StringBuilder();
                  	 for (int j = 0; j < width; j++) {
                          char content = maze[i][j].content;
                          // Replace spaces with dots
                          if (content == ' ') {
                              line.append('.');
                          } else {
                              line.append(content);
                          }
                     }
                     // Print the constructed line for the current row
                     output.println(line.toString());
                 }

                 // Close the PrintWriter to write the contents to the file
                 output.close();
	        } catch(IOException e) {
		             e.printStackTrace();  // Handle any potential IOException
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
			
			int i = 0;
			int j = 0;
			String input_str = input.readLine();
			String [] str_array = input_str.split(" ");
			height = Integer.parseInt((str_array[0]));
			width = Integer.parseInt((str_array[1]));
			maze = new Node[height][width];
			for (i= 0; i < height; i++) {
				input_str = input.readLine();
				for (j = 0; j < width; j++)	 {	
					maze[i][j] = new Node(i, j, input_str.charAt(j));
					if (input_str.charAt(j) == 'S') {
						startX = i;
						startY = j;
					}
				}
			}	
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	//	
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
		// 1. Inspect the north neighbor   startY - 1
		// 2. Inspect the south neighbor   startY + 1
		// 3. Inspect the west neighbor    startX + 1
		// 4. Inspect the east neighbor    startX - 1
       
		ArrayList<Node> neighbors = new ArrayList<>();

	    int row = currentNode.row;
	    int col = currentNode.col;
	    
	    /****
	    north = maze[row - 1][col];
	    south = maze[row + 1][col];
	    west = maze[row][col - 1];
	    east = maze[row][col + 1];
	    *****/

	    // Check the north neighbor
	    if (inMaze(row - 1, height)) {	    	
	    	north = maze[row - 1][col];
	        if (!north.isWall() && !north.isVisited()) {
	            neighbors.add(north);
	        }
	        /*******
	        else if (north.isVisited()) {
	        	north.parent = currentNode;
  	            north.visited = true;
	        }
	        *******/
	    }
	    
	    // Check the south neighbor
	    if (inMaze(row + 1, height)) { 
	    	south = maze[row + 1][col];
	        if (!south.isWall() && !south.isVisited()) {
	            neighbors.add(south);
	        }
	        /*******
	        else if (south.isVisited()) {
	        	south.parent = currentNode;
  	            south.visited = true;
	        }
	        *******/
	    }

	    // Check the west neighbor
	    if (inMaze(col - 1, width)) { 
	    	west = maze[row][col - 1];
	    	if (!west.isWall() && !west.isVisited()) {
	    		neighbors.add(west);
	    	}
	    	/*****
	    	else if (west.isVisited()) {
	    		west.parent = currentNode;
	    		west.visited = true;
	        }
	        *****/
	    }

	    // Check the east neighbor
	    if (inMaze(col + 1, width)) {
	    	east = maze[row][col + 1];
	        if (!east.isWall() && !east.isVisited()) {
	        	neighbors.add(east);
	        }
	        /*****
	        else if (east.isVisited()) {
	        	east.parent = currentNode;
	        	east.visited = true;
	        }
	        *****/
	    }
	    return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		// Create a queue to hold nodes for BFS
        Queue<Node> queue = new LinkedList<>();

        // Get the start node and mark it as visited
        Node startNode = maze[startX][startY];
        startNode.visited = true;
        queue.add(startNode);

        // Perform BFS
        while (!queue.isEmpty()) {
            // Dequeue the next node
            Node currentNode = queue.poll();

            // Check if we reached the goal
            if (currentNode.content == 'G') {
                 backtrack(currentNode); // Trace the path back to the start
                 return; // Exit as we found the goal
            }

            // Get the current node's neighbors
            ArrayList<Node> neighbors = getNeighbors(currentNode);
        
            // Add unvisited neighbors to the queue
            for (Node neighbor : neighbors) {
                 if (!neighbor.visited && !neighbor.isWall()) {
                     neighbor.visited = true;
                     neighbor.parent = currentNode; //Set parent for path tracking
                     queue.add(neighbor);
                 }
            }
	    }
        // If we exit the loop without finding the goal
        System.out.println("No path found to the goal.");
    }

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		Stack<Node> stack = new Stack<>(); // Stack for DFS
        Node startNode = maze[startX][startY]; // Get the start node
        stack.push(startNode); // Push start node onto the stack

        while (!stack.isEmpty()) { // Continue until the stack is empty
              Node current = stack.pop(); // Get the top node from the stack

              if (current.content == 'G') { // If the goal node is reached
                    backtrack(current); // Mark the path using backtracking
                    writeOutput(); // Write the solution to the output file
                    return; // Exit the method
              }

              ArrayList<Node> neighbors = getNeighbors(current); // Get neighbors
              for (Node neighbor : neighbors) {
            	  if (!neighbor.isVisited()) { // If the node hasn't been visited
            		  current.visited = true; // Mark it as visited
                   //for (Node neighbor : neighbors) { // For each neighbor
            		  neighbor.parent = current; // Set the current node as parent
            		  stack.push(neighbor); // Push the neighbor onto the stack
                   }
              }
         }
         System.out.println("No solution found for the maze.");   //indicate no solution
	}

    public static void main(String[] args) {
		// TODO Auto-generated method stub
		Pacman p01 = new Pacman("tinyMaze.txt", "tinyMaze_Output.txt");
		System.out.println(p01);
		p01.writeOutput();		
		System.out.println(p01);
		
		// Use a node from the maze, for example, the starting position
	    Node start = p01.maze[p01.startX][p01.startY];
	    
	    // Test getNeighbors method
	    ArrayList<Node> nbors = p01.getNeighbors(start);
	 
	    // Output the results
	    System.out.println("Neighbors of the p01 starting node: " + p01.startX + ", " + p01.startY);
	    for (Node nbor : nbors) {
	        System.out.println("Neighbor at row " + 
	            nbor.row + ", col " + nbor.col);
        }
	    
	    // Backtrack from the goal
	    Pacman p02 = new Pacman("tinyMaze.txt", "tinyMaze_Output.txt");
	    Node goal = p02.maze[5][1];
	    System.out.println(" ");
	    p02.backtrack(goal);

	    // Write output
	    p02.writeOutput();
	    System.out.println("Backtrack p02 from the goal & Write the output maze to a file");
	    System.out.println(p02);
	   
	    // Solve the tiny maze using BFS
	    Pacman p03_tiny = new Pacman("tinyMaze.txt", "tinyMaze_Output.txt");
	    System.out.println("original tinyMaze");
	    System.out.println(p03_tiny);
	    p03_tiny.solveBFS();

	    // Write the output tiny maze to a file
	    p03_tiny.writeOutput();
	    System.out.println("Solve the tiny maze using BFS & Write the output tiny maze to a file");
	    System.out.println(p03_tiny);
	    
	 // Solve the tiny maze using DFS
	    Pacman p04_tiny = new Pacman("tinyMaze.txt", "tinyMaze_Output.txt");
	    System.out.println("original tinyMaze");
	    System.out.println(p04_tiny);
	    p04_tiny.solveDFS();

	    // Write the output tiny maze to a file
	    p04_tiny.writeOutput();
	    System.out.println("Solve the tiny maze using DFS & Write the output tiny maze to a file");
	    System.out.println(p04_tiny);

	    /****************************
	    // Solve the classic maze using BFS
	    Pacman p05_classic = new Pacman("classic.txt", "classic_Output.txt");
	    System.out.println("original classicMaze");
	    System.out.println(p05_classic);
	    p05_classic.solveBFS();

	    // Write the output classic maze to a file
	    p05_classic.writeOutput();
	    System.out.println("Solve the classic maze using BFS & Write the output classic maze to a file");
	    System.out.println(p05_classic);
	    
	    // Solve the classic maze using DFS
	    Pacman p06_classic = new Pacman("classic.txt", "classic_Output.txt");
	    System.out.println("original classicMaze");
	    System.out.println(p06_classic);
	    p06_classic.solveDFS();

	    // Write the output classic maze to a file
	    p06_classic.writeOutput();
	    System.out.println("Solve the classic maze using DFS & Write the output classic maze to a file");
	    System.out.println(p06_classic);
	
	    // Solve the medium maze using BFS
	    Pacman p07_medium = new Pacman("mediumMaze.txt", "mediumMaze_Output.txt");
	    System.out.println("original mediumMaze");
	    System.out.println(p07_medium);
	    p07_medium.solveBFS();

	    // Write the output medium maze to a file
	    p07_medium.writeOutput();
	    System.out.println("Solve the medium maze using BFS & Write the output medium maze to a file");
	    System.out.println(p07_medium);
	    
	    // Solve the medium maze using DFS
	    Pacman p08_medium = new Pacman("mediumMaze.txt", "mediumMaze_Output.txt");
	    System.out.println("original mediumMaze");
	    System.out.println(p08_medium);
	    p08_medium.solveDFS();
	    
	    // Write the output medium maze to a file
	    p08_medium.writeOutput();
	    System.out.println("Solve the medium maze using DFS & Write the output medium maze to a file");
	    System.out.println(p08_medium);
	    
	    // Solve the big maze using BFS
	    Pacman p09_big = new Pacman("bigMaze.txt", "bigMaze_Output.txt");
	    System.out.println("original bigMaze");
	    System.out.println(p09_big);
	    p09_big.solveBFS();
	    
	    // Write the output big maze to a file
	    p09_big.writeOutput();
	    System.out.println("Solve the big maze using BFS & Write the output big maze to a file");
	    System.out.println(p09_big);
	    
	    // Solve the big maze using DFS
	    Pacman p10_big = new Pacman("bigMaze.txt", "bigMaze_Output.txt");
	    System.out.println("original bigMaze");
	    System.out.println(p10_big);
	    p10_big.solveDFS();
	    
	    // Write the output big maze to a file
	    p10_big.writeOutput();
	    System.out.println("Solve the big maze using DFS & Write the output big maze to a file");
	    System.out.println(p10_big);
	    
	    // Solve the demo maze using BFS
	    Pacman p11_demo = new Pacman("demoMaze.txt", "demoMaze_Output.txt");
	    System.out.println("original demoMaze");   
	    System.out.println(p11_demo);
	    p11_demo.solveBFS();
	    
	    // Write the output demo maze to a file
	    p11_demo.writeOutput();
	    System.out.println("Solve the demo maze using BFS & Write the output demo maze to a file");
	    System.out.println(p11_demo);
	    
	    // Solve the demo maze using DFS
	    Pacman p12_demo = new Pacman("demoMaze.txt", "demoMaze_Output.txt");
	    System.out.println("original demoMaze");
	    System.out.println(p12_demo);
	    p12_demo.solveDFS();
	    
	    // Write the output demo maze to a file
	    p12_demo.writeOutput();
	    System.out.println("Solve the demo maze using DFS & Write the output demo maze to a file");
	    System.out.println(p12_demo);
	    ************************/
	    
	    // Solve the unsolvable maze using BFS
	    Pacman p13_unsolve = new Pacman("unsolvable.txt", "unsolvable_Output.txt");
	    System.out.println("original unsolvable maze");
	    System.out.println(p13_unsolve);
	    p13_unsolve.solveBFS();
	    
	    // Write the output unsolvable maze to a file
	    p13_unsolve.writeOutput();
	    System.out.println("Solve the unsolvable maze using BFS & Write the output unsolvable maze to a file");
	    System.out.println(p13_unsolve);
    }
}
