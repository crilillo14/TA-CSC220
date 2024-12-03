package lab12;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
//		if (end.parent == null || end.content == 'S') {
//			return;
//		}
//		if (end.content == 'G') {
//			backtrack(end.parent);
//		}
//		end.content = '.';
//		backtrack(end.parent);
		Node curr = end;
		while(curr != null && curr.content!= 'S') {
			if(curr.content != 'G') {
				curr.content = '.';
			}
			curr = curr.parent;
		}
		
		
		
		
		// TODO for assignment12
	}

	/** writes the solution to file */
	public void writeOutput() {
		
		
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			//this.solveBFS
			//this.solveDFS
			String s = "";
			
			for (int i = 0; i < height; i++){
				for (int j = 0; j < width; j++){
					s += maze[i][j].content + "";
				}
				s += "\n";
			}
			output.println(this.height + " " + this.width);
			output.println(s);
			

			
			
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
		int down = 0;
		int right = 0;
		try {
			// don't forget to close input when you're done
			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));
			// FILL IN
			int count = 0;
			String oneLine = input.readLine();
			
			while(oneLine != null) {
			if (count ==0) {
				
				
				String[] temp = oneLine.split(" ");
				this.height = Integer.parseInt(temp[0]);
				this.width = Integer.parseInt(temp[1]);
				this.maze = new Node[this.height][this.width];
				count++;
				continue;
				
			}
				
			 oneLine = input.readLine();
			 if (oneLine == null) {
			        break; // Exit the loop if the file ends
			    }
			 for (int index = 0; index < oneLine.length(); index++) {
				 char c = oneLine.charAt(index);
				 if (c == 'S') {
					 this.startX = down;
					 this.startY = index;
				 }
				 
				 this.maze[down][index] = new Node(down, index, c);
			
				 
			 }// end of for loop
			 down++;
			
				
				
			}// end of while
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
		
		
		int currX = currentNode.row;
		int currY = currentNode.col;
		ArrayList<Node> nextToCurr = new ArrayList<Node>();
		
		
		
		int tryNorth = currY - 1;
		if (tryNorth < this.height && tryNorth >= 0 && currX < this.width && currX > 0) {
			north = this.maze[currX][tryNorth];
			if (north.isVisited() == false && !north.isWall()) {
				nextToCurr.add(north);
			}else {
				north = null;
			}
			
		}
		// 1. Inspect the north neighbor
		

		// 2. Inspect the south neighbor
		int trySouth = currY + 1;
		if (trySouth < this.height && trySouth >= 0 && currX < this.width && currX > 0) {
			south = this.maze[currX][trySouth];
			if (south.isVisited() == false && !south.isWall()) {
				nextToCurr.add(south);
			}else {
				south = null;
			}
			
		}

		// 3. Inspect the west neighbor
		int tryWest = currX - 1;
		if (tryWest < this.height && tryWest >= 0 && currY < this.height && currY > 0) {
			west = this.maze[tryWest][currY];
			if (west.isVisited() == false && !west.isWall()) {
				nextToCurr.add(west);
			}else {
				west = null;
			}
			
		}

		// 4. Inspect the east neighbor
		int tryEast = currX +1 ;
		if (tryEast < this.height && tryEast >= 0 && currY < this.height && currY > 0) {
			east = this.maze[tryEast][currY];
			if (east.isVisited() == false && !east.isWall()) {
				nextToCurr.add(east);
			}else {
				east = null;
			}
			
		}

		return nextToCurr;
	}
	

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		Queue<Node> q = new LinkedList<>();
		Node start = this.maze[startX][startY];
		start.visited = true;
		q.add(start);
		Node curr = start;
		while(!q.isEmpty() && curr.content != 'G') {
			 curr = q.poll();
			
			
			for (Node next: this.getNeighbors(curr)) {
				if (!next.visited) {
					next.visited = true;
					next.parent = curr;
					q.add(next);
				}
			}
			
		}// end of while loop
		
		if(curr.content == 'G'){
			//System.out.println("curr's parent is" + curr.parent);
			backtrack(curr);
			writeOutput();
			return;
		}else {
			writeOutput();
		}
		
		// we haven't found x`x
//		if (q.isEmpty()) {
//			writeOutput();
//			return;
//		}
		
		
	}
	
	// helper method
	public void DFS(Node curr) {
		curr.visited = true;
		if (curr.content == 'G') {
			System.out.println("found");
			backtrack(curr);
			
			return;
		}
		for(Node next: this.getNeighbors(curr)) {
			
			if (!next.visited) {
				next.parent = curr;
				DFS(next);
			}
		}
		
		
		return;
		
	}// end of DFS

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		// TODO for assignment12
		Node curr = this.maze[startX][startY];
		DFS(curr);
		this.writeOutput();
		
		
		
	}

}
