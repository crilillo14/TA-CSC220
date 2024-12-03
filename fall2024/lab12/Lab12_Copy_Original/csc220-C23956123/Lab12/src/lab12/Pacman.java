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
		// TODO for assignment12 DONE
		
		Node parent = end.parent;
		
		while(parent.content != 'S') {
			parent.content = '.';
			parent = parent.parent;
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12 DONE
		
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			
			String s = "";
			s += height + " "+ width + "\n";
			for (int i = 0; i < height; i++){
				for (int j = 0; j < width; j++){
					s += maze[i][j].content;
				}
				s += "\n";
			}
			output.print(s);
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
		// TODO for lab12 DONE
		
		try {
			// don't forget to close input when you're done
			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));
			
			String[] line = input.readLine().split(" ");
			height = Integer.parseInt(line[0]);
			width = Integer.parseInt(line[1]);
			this.maze = new Node[this.height][this.width];
			
			for(int r = 0; r<height; r++) {
				String currentLine = input.readLine();
				for(int c = 0; c<width; c++) {
					char toAdd = currentLine.charAt(c);
					maze[r][c] = new Node(r, c, toAdd);
 					
					if(toAdd == 'S') {
						this.startX = c;
						this.startY = r;
					}
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
		// TODO for assignment12 DONE
		
		Node north, south, east, west;
		
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> neighbors = new ArrayList<Node>();	
	

		// 1. Inspect the north neighbor
	    if(inMaze(currentNode.col,width) && inMaze(currentNode.row-1,height)){
			if((!maze[currentNode.row-1][currentNode.col].visited) && 
					(maze[currentNode.row-1][currentNode.col].content != 'X') ) {
			maze[currentNode.row-1][currentNode.col].visited = true;
			maze[currentNode.row-1][currentNode.col].parent = currentNode;
			neighbors.add(maze[currentNode.row-1][currentNode.col]);
			}
		
	    }
	    
		// 2. Inspect the south neighbor
	    if(inMaze(currentNode.col,width) && inMaze(currentNode.row+1,height)){
			if((!maze[currentNode.row+1][currentNode.col].visited) && 
					(maze[currentNode.row+1][currentNode.col].content != 'X') ) {
			maze[currentNode.row+1][currentNode.col].visited = true;
			maze[currentNode.row+1][currentNode.col].parent = currentNode;
			neighbors.add(maze[currentNode.row+1][currentNode.col]);
				}
	        }	

		// 3. Inspect the west neighbor
	    if(inMaze(currentNode.col-1,width) && inMaze(currentNode.row,height)){
			if((!maze[currentNode.row][currentNode.col-1].visited) && 
					(maze[currentNode.row][currentNode.col-1].content != 'X') ) {
			maze[currentNode.row][currentNode.col-1].visited = true;
			maze[currentNode.row][currentNode.col-1].parent = currentNode;
			neighbors.add(maze[currentNode.row][currentNode.col-1]);
					}
	            }

		// 4. Inspect the east neighbor
	    if(inMaze(currentNode.col+1,width) && inMaze(currentNode.row,height)){
			if((!maze[currentNode.row][currentNode.col+1].visited) && 
					(maze[currentNode.row][currentNode.col+1].content != 'X') ) {
			maze[currentNode.row][currentNode.col+1].visited = true;
			maze[currentNode.row][currentNode.col+1].parent = currentNode;
			neighbors.add(maze[currentNode.row][currentNode.col+1]);
			}
		}
		
	                    return neighbors;
	            }
	        

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		
		// TODO for assignment12 DONE
		
		Node start = maze[startY][startX];
		start.visited = true;

		Queue<Node> sol = new LinkedList<Node>();
		sol.add(start);
		Node current;
		
		while(!sol.isEmpty()) {
			current = sol.poll();
			if(current.content == 'G') {
			backtrack(current);
			break;
			}
			
			ArrayList<Node> neighbors = getNeighbors(current);
			for(int i = 0; i< neighbors.size(); i++) {
				sol.add(neighbors.get(i));
			}

		}
		writeOutput();
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		
		// TODO for assignment12 DONE
		
		Node start = maze[startY][startX];	
		start.visited = true;
				
		Stack <Node> sol = new Stack<Node>();
		sol.push(start);
		Node current;
		
		while(!sol.isEmpty()) {
			current = sol.pop();
			if(current.content == 'G') {
				backtrack(current);
				writeOutput();
				return;
			}
			
			ArrayList<Node> neighbors = getNeighbors(current);
			for(int i = 0; i< neighbors.size(); i++) {
			sol.push(neighbors.get(i));
			}
			
			}
			
		writeOutput();
	}

    
} 
    


