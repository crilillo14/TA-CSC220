package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.LinkedList;
//import java.util.Queue;
import java.util.ArrayList;

public class Pacman 
{

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
	
	//private int goalX;
	//private int goalY;

	/** A Node is the building block for a Graph. */
	private static class Node 
	{
		/** the content at this location */
	    private char content;
	    /** the row where this location occurs */
	    private int row;
	    /** the column where this location occurs */
	    private int col;
		private boolean visited;
		private Node parent;

	    public Node(int x, int y, char c) 
	    {
	        visited = false;
	        content = c;
	        parent =  null;
	        this.row = x;
	        this.col = y;
	    }

	    public boolean isWall() 
	    { 
	    	return content == 'X'; 
	    }
	    public boolean isVisited() 
	    { 
	    	return visited; 
	    }
	}

	/** constructor */
	public Pacman(String inputFileName, String outputFileName) 
	{
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		buildGraph();
	}

	private boolean inMaze(int index, int bound)
	{
		return index < bound && index >= 0;
	}

	/** helper method to construct the solution path from S to G
	 *  NOTE this path is built in reverse order, starting with the goal G.
	*/
	private void backtrack(Node end)
	{
		// TODO for assignment12
		/*Node current = end;
		
		while(current != null && current.parent != null)
		{
			if(current.content != 'S' && current.content != 'G')
			{
				current.parent.content = '.';
				//current = current.parent;
			}
			current = current.parent;
		}*/
//		if (end == null || end.parent == null) 
//		{
////			System.out.println("test");
//			return; // Guard clause for safety
//		}

	    while (end.parent.content != 'S') 
	    {
	        end.parent.content = '.'; // Mark the parent node
	        end = end.parent; // Move up the path
	    }
	}

	/** writes the solution to file */
	public void writeOutput() 
	{
		// TODO for lab12
		try 
		{
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			
			// Writing in all of the dimensions
			String s = "";
			s += height + " " + width + "\n";
			
			// Writing the maze layout row by row
			for(int i = 0; i < height; i++)
			{
				for(int j = 0; j < width; j++)
				{
					s += maze[i][j].content;
				}
				s += "\n";
			}
			output.println(s);
			output.close();
		} 
		
		
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}

	public String toString() 
	{
		String s = "";
		s += height + " " + width + "\n";
		for (int i = 0; i < height; i++)
		{
			for (int j = 0; j < width; j++)
			{
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
		// TODO for lab12
		try 
		{
			// don't forget to close input when you're done
			BufferedReader input = new BufferedReader(new FileReader(inputFileName));
			// FILL IN
			String [] dimensions = input.readLine().split(" ");
			this.height = Integer.parseInt(dimensions[0]);
			this.width = Integer.parseInt(dimensions[1]);
			
			// Initializing the entire maze array
			this.maze = new Node[height][width];
			
			// Reading the entire file in terms of populating the entire maze
			for(int i = 0; i < height; i++)
			{
				String line = input.readLine();
				for(int j = 0; j < width; j++)
				{
					char c = line.charAt(j);
					//maze[i][j] = new Node(i,j,c);
					
					// Identifying the position that you start at
					if(c == 'S')
					{
						this.startX = i;
						this.startY = j;
					}
					maze[i][j] = new Node(i,j,c);
				}
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
		// TODO for assignment12
		Node north, south, east, west;
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> neighbors = new ArrayList<>();
		int row = currentNode.row;
		int col = currentNode.col;

		// 1. Inspect the north neighbor
		if (inMaze(row - 1, height) && !maze[row - 1][col].isWall() && !maze[row - 1][col].isVisited()) 
		{
	        north = maze[currentNode.row - 1][col];
	        north.visited = true;
	        north.parent = currentNode;
	        neighbors.add(north);
	    }

		// 2. Inspect the south neighbor
	    if (inMaze(row + 1, height) && !maze[row + 1][col].isWall() && !maze[row + 1][col].isVisited()) 
	    {
	        south = maze[currentNode.row + 1][col];
	        south.visited = true;
	        south.parent = currentNode;
	        neighbors.add(south);
	    }

		// 3. Inspect the west neighbor
	    if (inMaze(col - 1, width) && !maze[row][col - 1].isWall() && !maze[row][col - 1].isVisited()) 
	    {
	        west = maze[currentNode.row][col - 1];
	        west.visited = true;
	        west.parent = currentNode;
	        neighbors.add(west);
	    }

		// 4. Inspect the east neighbor
	    if (inMaze(col + 1, width) && !maze[row][col + 1].isWall() && !maze[row][col + 1].isVisited()) 
	    {
	        east = maze[currentNode.row][col + 1];
	        east.visited = true;
	        east.parent = currentNode;
	        neighbors.add(east);
	    }

		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() 
	{
		// TODO for assignment12
		Node start = maze[startX][startY];
		
		Node current = start;

		start.visited = true;

		LinkedList<Node> queue = new LinkedList<Node>();
		
		queue.add(start);
		
		while(!queue.isEmpty() && current.content != 'G')
		{
			current = queue.poll();
			ArrayList<Node> neighbors = getNeighbors(current);
			

	        
	        // Add unvisited neighbors to the queue later on
	        for (int i = 0; i < neighbors.size(); i++)
	        {
	        	queue.add(neighbors.get(i));
	        }
		}
		
		//Node goal = maze[goalX][goalY];
		//System.out.println(goal.parent);
		if(!queue.isEmpty() && current.content == 'G')
			backtrack(current);
		
		writeOutput();
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() 
	{
		// TODO for assignment12
		Stack<Node> stack = new Stack<Node>();
		Node start = maze[startX][startY];
		
		Node current = start;

		start.visited = true;
		stack.push(start);
		
		while(!stack.isEmpty() && current.content != 'G')
		{
			current = stack.pop();
			ArrayList<Node> neighbors = getNeighbors(current);
			

	        
	        // Add unvisited neighbors to the queue later on
	        for (int i = 0; i < neighbors.size(); i++)
	        {
	        	stack.push(neighbors.get(i));
	        }
		}
		
		//Node goal = maze[goalX][goalY];
		//System.out.println(goal.parent);
		if(!stack.isEmpty() && current.content == 'G')
			backtrack(current);
		
		writeOutput();
		
	}

}
