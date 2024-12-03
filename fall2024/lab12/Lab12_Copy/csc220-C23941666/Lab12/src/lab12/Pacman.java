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
	private void backtrack(Node end)
	{
		// TODO for assignment12 part 2
		Node current = end;

		while (current != null)
		{
			if (current.content != 'S' && current.content != 'G') 
			{
	            current.content = '.';
	        }
			
	        current = current.parent;
	    }
	}

	/** writes the solution to file */
	public void writeOutput() 
	{
		// TODO for lab12 part 2
		try 
			{
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			output.println(height + " " + width);
				
			for (int i = 0; i < height; i++)
				{
					for (int j = 0; j < width; j++)
						{
							output.print(maze[i][j].content + " ");
						}
					
					output.println();
				}
			
				output.close();
		} 
		catch(IOException e) 
		{
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
	private void buildGraph() 
	{
		// TODO for lab12 part 1
		try 
		{
			// don't forget to close input when you're done
			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));
			// FILL IN

			String line = input.readLine();
			String[] size = line.split(" ");

			height = Integer.parseInt(size[0]);
			width = Integer.parseInt(size[1]);
			maze = new Node[height][width];

			int row = 0;
			
			while ((line = input.readLine()) != null)
				{
					String[] chars = line.split("");

					for (int col = 0; col < chars.length; col++)
						{
							char c = chars[col].charAt(0);

							if (c == 'S')
							{
								startX = row;
								startY = col;
							}
							maze[row][col] = new Node(row, col, c);
						}
					row++;
				}
			input.close();
			
		} 	
		catch (IOException e) 
		{
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
	public ArrayList<Node> getNeighbors(Node currentNode) 
	{
		// TODO for assignment12 part 1
		Node north, south, east, west;
		
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> neighbors = new ArrayList<Node>();

		// 1. Inspect the north neighbor
		if (inMaze(currentNode.row - 1, height)) 
		{
				north = maze[currentNode.row - 1][currentNode.col];
				if (!north.isVisited() && !north.isWall()) 
				{
						neighbors.add(north);
				}
		}

		// 2. Inspect the south neighbor
		if (inMaze(currentNode.row + 1, height)) 
		{
				south = maze[currentNode.row + 1][currentNode.col];
				
				if (!south.isVisited() && !south.isWall()) 
				{
						neighbors.add(south);
				}
		}

		// 3. Inspect the west neighbor
		if (inMaze(currentNode.col - 1, width)) 
		{
				west = maze[currentNode.row][currentNode.col - 1];
				
				if (!west.isVisited() && !west.isWall()) 
				{
						neighbors.add(west);
				}
		}

		// 4. Inspect the east neighbor
		if (inMaze(currentNode.col + 1, width)) 
		{
				east = maze[currentNode.row][currentNode.col + 1];
				
				if (!east.isVisited() && !east.isWall()) 
				{
						neighbors.add(east);
				}
		}

		return neighbors;

	}

	/** Pacman uses BFS strategy to solve the maze */
	//with debugging comments
	 public void solveBFS() 
	    {
	        // TODO for assignment12 part 3
	        Queue<Node> queue = new LinkedList<>();
	        Node start = maze[startX][startY];
	        start.visited = true;
	        queue.add(start);
	        
	        //System.out.println("Starting from (" + startX ", " + startY + ")");
	        
	        while (!queue.isEmpty()) 
	        {
				Node current = queue.poll();
				
				//System.out.println("Visiting node at (" + current.row + ", " + current.col + "), Content: " + current.content);
				if (current.content == 'G') 
				{
						//System.out.println("Goal found at (" + current.row + ", " + current.col + ")");
						backtrack(current);
						writeOutput();
						return;
				}
				for (Node neighbor : getNeighbors(current)) 
				{
						if (!neighbor.visited && !neighbor.isWall()) {
								//System.out.println("Adding neighbor (" + neighbor.row + ", " + neighbor.col + ") to the queue");
								neighbor.visited = true;
								neighbor.parent = current;
								
								queue.add(neighbor);
						} 
						else if (neighbor.isWall()) 
						{
								//System.out.println("Skipping wall (" + neighbor.row + ", " + neighbor.col + ")");
						}
				}
		}

		//System.out.println("No path found.");
}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() 
	{
		// TODO for assignment12 part 4

		Stack<Node> stack = new Stack<>();
		Node start = maze[startX][startY];
		start.visited = true;
		stack.push(start);

		while (!stack.isEmpty()) 
		{
				Node current = stack.pop();
				
				if (current.content == 'G') 
				{
						backtrack(current);
						writeOutput();
						return;
				}

				for (Node neighbor : getNeighbors(current)) 
				{
						if (!neighbor.visited && !neighbor.isWall()) 
						{
								neighbor.visited = true;
								neighbor.parent = current;
								
								stack.push(neighbor);
						}
				}
		}

}

}
