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
			
		Node curr=end.parent;
		
		while(curr!=null && curr.content!='S') {
			curr.content='.';
			curr=curr.parent;
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			
			//dimensions		
			output.write(Integer.toString(height));
			output.write(" ");
			output.write(Integer.toString(width));
			output.write("\n");
//			System.out.println(Integer.toString(height) + " " + Integer.toString(width));
			
			//maze
			for(int i=0;i<height;i++) {
				//rows
				for(int j=0;j<width;j++) {
					output.write(maze[i][j].content);
//					System.out.println(maze[i][j].content);
				}
				
				output.write("\n");
//				System.out.println("\n");
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

			//dimensions
			String line=input.readLine();
			String[] arr = line.split(" ");
			height=Integer.parseInt(arr[0]);
			width=Integer.parseInt(arr[1]);
			maze = new Node[height][width];
			
			//fill maze
			for(int i=0;i<height;i++) {
				String nextLine=input.readLine();
				String[] nextArr = nextLine.split("");
				//rows
				
				for(int j=0;j<nextArr.length;j++) {
					//cols
					
					//check for "S"
					if(nextArr[j].equals("S")) {
						startX=i;
						startY=j;
					}
					
					maze[i][j]=new Node(i,j,nextArr[j].charAt(0));
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
		ArrayList<Node> neighbors = new ArrayList<Node>();
		int col=currentNode.col;
		int row=currentNode.row;

		// 1. Inspect the north neighbor
		if(row>0) {
			north=maze[currentNode.row-1][currentNode.col];
			if(!north.isVisited() && !north.isWall()) {
				north.visited=true;
				north.parent=currentNode;
				neighbors.add(north);
			}
		}

		// 2. Inspect the south neighbor
		if(row<height-1) {
			south=maze[currentNode.row+1][currentNode.col];
			if(!south.isVisited() && !south.isWall()) {
				south.visited=true;
				south.parent=currentNode;
				neighbors.add(south);
			}
		}

		// 3. Inspect the west neighbor
		if(col>0) {
			west=maze[currentNode.row][currentNode.col-1];
			if(!west.isVisited() && !west.isWall()) {
				west.visited=true;
				west.parent=currentNode;
				neighbors.add(west);
			}
		}

		// 4. Inspect the east neighbor
		if(col<width-1) {
			east=maze[currentNode.row][currentNode.col+1];
			if(!east.isVisited() && !east.isWall()) {
				east.visited=true;
				east.parent=currentNode;
				neighbors.add(east);
			}
		}

		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		// TODO for assignment12
		Queue<Node> q=new LinkedList<Node>();
		q.offer(maze[startX][startY]);
		
		while(!q.isEmpty()) {
			Node n = q.poll();
			n.visited=true;
			ArrayList<Node> neighbors=getNeighbors(n);
			
			for(Node n1:neighbors) {
				if(n1.content=='G') {
					backtrack(n1);
					writeOutput();
					return;
				}
				n1.parent=n;
				n1.visited=true;
				q.offer(n1);
			}
		}
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		Stack<Node> s=new Stack<Node>();
		s.push(maze[startX][startY]);
		
		while(!s.isEmpty()) {
			Node n = s.pop();
			n.visited=true;
			ArrayList<Node> neighbors=getNeighbors(n);
			
			for(Node n1:neighbors) {
				if(n1.content=='G') {
					backtrack(n1);
					writeOutput();
					return;
				}
				n1.parent=n;
				n1.visited=true;
				s.push(n1);
			}
		}
	}

}
