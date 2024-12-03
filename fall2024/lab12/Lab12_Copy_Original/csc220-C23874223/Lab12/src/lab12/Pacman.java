package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

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
		private void backtrack(Node end) {
		    Node current = end;
		    while (current != null && current.content != 'S') {
		        if (current.content == ' ') {
		            current.content = '.';
		        }
		        current = current.parent;
		    }
		}


		/** writes the solution to file */
		public void writeOutput() {
		    try {
		        PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
		        output.println(height + " " + width);
		        for (int i = 0; i < height; i++) {
		            for (int j = 0; j < width; j++) {
		                output.print(maze[i][j].content);
		            }
		            output.println();
		        }
		        output.close();
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
		        String[] dimensions = input.readLine().split(" ");
		        height = Integer.parseInt(dimensions[0]);
		        width = Integer.parseInt(dimensions[1]);
		        maze = new Node[height][width];

		        String line;
		        for (int i = 0; i < height; i++) {
		            line = input.readLine();
		            for (int j = 0; j < width; j++) {
		                char c = line.charAt(j);
		                maze[i][j] = new Node(i, j, c);
		                if (c == 'S') {
		                    startX = i;
		                    startY = j;
		                }
		            }
		        }
		        input.close(); // Close the BufferedReader when done.
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
		    ArrayList<Node> neighbors = new ArrayList<>();
		    int row = currentNode.row;
		    int col = currentNode.col;

		    // Check North
		    if (inMaze(row - 1, height) && !maze[row - 1][col].isWall() && !maze[row - 1][col].isVisited()) {
		        Node north = maze[row - 1][col];
		        north.visited = true;
		        north.parent = currentNode;
		        neighbors.add(north);
		    }

		    // Check South
		    if (inMaze(row + 1, height) && !maze[row + 1][col].isWall() && !maze[row + 1][col].isVisited()) {
		        Node south = maze[row + 1][col];
		        south.visited = true;
		        south.parent = currentNode;
		        neighbors.add(south);
		    }

		    // Check West
		    if (inMaze(col - 1, width) && !maze[row][col - 1].isWall() && !maze[row][col - 1].isVisited()) {
		        Node west = maze[row][col - 1];
		        west.visited = true;
		        west.parent = currentNode;
		        neighbors.add(west);
		    }

		    // Check East
		    if (inMaze(col + 1, width) && !maze[row][col + 1].isWall() && !maze[row][col + 1].isVisited()) {
		        Node east = maze[row][col + 1];
		        east.visited = true;
		        east.parent = currentNode;
		        neighbors.add(east);
		    }

		    return neighbors;
		}


		/** Pacman uses BFS strategy to solve the maze */
		public void solveBFS() {
		    Queue<Node> queue = new LinkedList<>();
		    Node start = maze[startX][startY];
		    start.visited = true;
		    queue.add(start);

		    while (!queue.isEmpty()) {
		        Node current = queue.poll();

		        if (current.content == 'G') {
		            backtrack(current);
		            writeOutput();
		            return;
		        }

		        for (Node neighbor : getNeighbors(current)) {
		            neighbor.visited = true;
		            neighbor.parent = current;
		            queue.add(neighbor);
		        }
		    }
		    writeOutput();
		}


		/** Pacman uses DFS strategy to solve the maze */
		public void solveDFS() {
		    Stack<Node> stack = new Stack<>();
		    Node start = maze[startX][startY];
		    start.visited = true;
		    stack.push(start);

		    while (!stack.isEmpty()) {
		        Node current = stack.pop();

		        if (current.content == 'G') {
		            backtrack(current);
		            writeOutput();
		            return;
		        }

		        for (Node neighbor : getNeighbors(current)) {
		            neighbor.visited = true;
		            neighbor.parent = current;
		            stack.push(neighbor);
		        }
		    }
		    writeOutput();
		}


	}


