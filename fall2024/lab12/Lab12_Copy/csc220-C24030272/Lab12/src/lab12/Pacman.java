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
		// TODO for assignment12
		while (end.parent!=null && end.parent.content!='S')
		{
			end.parent.content='.';
			end = end.parent;
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
		// TODO for lab12
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			// FILL IN
			output.print(height);
			output.print(" ");
			output.print(width);
			output.println();
			for (int r = 0; r< maze.length;r++)
			{
				for (int c = 0; c<maze[0].length; c++)
				{
					output.print(maze[r][c].content);
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
			String line1 = input.readLine();
			String h = "";
			String w = "";
			int index = 0;
			while (!Character.isWhitespace(line1.charAt(index)))
			{
				h += line1.charAt(index);
				index++;
			}
			index++;
			while (index<line1.length())
			{
				w+= line1.charAt(index);
				index++;
			}
			height = Integer.parseInt(h);
			width = Integer.parseInt(w);
			int row = 0;
			int col = 0;
			maze = new Node[height][width];
			char s = 'S';
			String line = "replace";
			while (line!=null)
			{
				line = input.readLine();
				if (line!=null)
				{
					for (int i =0; i<line.length();i++)
					{
						maze[row][col]= new Node(row, col,line.charAt(i));
						if (maze[row][col].content==s)
						{
							startX= row;
							startY= col;
						}
						col++;
					}
					col=0;
					row++;
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
		ArrayList<Node> list = new ArrayList<Node>();
		// 1. Inspect the north neighbor
		if (inMaze(currentNode.row-1,height))
		{
			if (!(maze[currentNode.row-1][currentNode.col].visited))
			{
				maze[currentNode.row-1][currentNode.col].visited= true;
				maze[currentNode.row-1][currentNode.col].parent= currentNode;
				list.add(maze[currentNode.row-1][currentNode.col]);
			}
		}
		// 2. Inspect the south neighbor
		if (inMaze(currentNode.row+1,height))
		{
			if (!(maze[currentNode.row+1][currentNode.col].visited))
			{
				maze[currentNode.row+1][currentNode.col].visited= true;
				maze[currentNode.row+1][currentNode.col].parent= currentNode;
				list.add(maze[currentNode.row+1][currentNode.col]);
			}
		}
		// 3. Inspect the west neighbor
		if (inMaze(currentNode.col-1,width))
		{
			if (!(maze[currentNode.row][currentNode.col-1].visited))
			{
				maze[currentNode.row][currentNode.col-1].visited= true;
				maze[currentNode.row][currentNode.col-1].parent= currentNode;
				list.add(maze[currentNode.row][currentNode.col-1]);
			}
		}
		// 4. Inspect the east neighbor
		if (inMaze(currentNode.col+1,width))
		{
			if (!(maze[currentNode.row][currentNode.col+1].visited))
			{
				maze[currentNode.row][currentNode.col+1].visited= true;
				maze[currentNode.row][currentNode.col+1].parent= currentNode;
				list.add(maze[currentNode.row][currentNode.col+1]);
			}
		}
		return list;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() 
	{
		// TODO for assignment12
		Queue<Node> bfs = new LinkedList<Node>();
		Node s = maze[startX][startY];
		bfs.offer(s);
		s.visited=true;
		while(bfs.size()!=0)
		{
			s = bfs.poll();
			ArrayList<Node> neighbors = getNeighbors(s);
			for (int i =0; i<neighbors.size();i++)
			{
				if(neighbors.get(i).content=='G')
				{
					neighbors.get(i).visited=true;
					neighbors.get(i).parent = s;
					s= neighbors.get(i);
					backTrack(s);
					writeOutput();
					return;
				}
				if(Character.isWhitespace(neighbors.get(i).content))
				{
					bfs.offer(neighbors.get(i));
					neighbors.get(i).visited=true;
					neighbors.get(i).parent= s;
				}
			}
		}
		System.out.println("Maze not solvable.");
		
	}

	private void backTrack(Node s) {
		// TODO Auto-generated method stub
		while (s.parent!=null && s.parent.content!='S')
		{
			s.parent.content='.';
			s = s.parent;
		}
		
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() 
	{
		// TODO for assignment12
		LinkedList<Node> dfs = new LinkedList<Node>();
		Node current = maze[startX][startY];
		dfs.add(current);
		current.visited=true;
		while(dfs.size()!=0)
		{
			current =dfs.removeLast();
			ArrayList<Node> neighbors = getNeighbors(current);
			for (int i = 0;i<neighbors.size();i++)
			{
				if(neighbors.get(i).content=='G')
				{
					neighbors.get(i).visited=true;
					neighbors.get(i).parent = current;
					current= neighbors.get(i);
					backTrack(current);
					writeOutput();
					return;
				}
				if(Character.isWhitespace(neighbors.get(i).content))
				{
					dfs.add(neighbors.get(i));
					neighbors.get(i).visited=true;
					neighbors.get(i).parent= current;
				}
			}
		}
		System.out.println("Maze not solvable.");
		
	}

}
