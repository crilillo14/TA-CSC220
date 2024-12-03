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

	private Node[][] maze;
	private String inputFileName;
	private String outputFileName;
	private int height;
	private int width;
	private int startX;
	private int startY;

	private static class Node {
	    private char content;
	    private int row;
	    private int col;
		private boolean visited;
		private Node parent;

	    public Node(int x, int y, char c) {
	        visited = false;
	        content = c;
	        parent = null;
	        this.row = x;
	        this.col = y;
	    }

	    public boolean isWall() { return content == 'X'; }
	    public boolean isVisited() { return visited; }
	}

	public Pacman(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		buildGraph();
	}

	private boolean inMaze(int index, int bound){
		return index < bound && index >= 0;
	}

	private void backtrack(Node end){
		Node current = end;
	    while (current != null && current.parent != null) {
	        if (current.content != 'S' && current.content != 'G') {
	            current.content = '.';
	        }
	        current = current.parent; 
	    }
	}

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

	private void buildGraph() {
		try {
			BufferedReader input = new BufferedReader(new FileReader(inputFileName));
	        String[] dimensions = input.readLine().split(" ");
	        height = Integer.parseInt(dimensions[0]);
	        width = Integer.parseInt(dimensions[1]);
	        maze = new Node[height][width];
	        for (int i = 0; i < height; i++) {
	            String line = input.readLine();
	            for (int j = 0; j < width; j++) {
	                char content = line.charAt(j);
	                maze[i][j] = new Node(i, j, content);
	                if (content == 'S') {
	                    startX = i;
	                    startY = j;
	                }
	            }
	        }
	        input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Node> getNeighbors(Node currentNode) {
		Node north, south, east, west;
	    ArrayList<Node> neighbors = new ArrayList<>();
	    int row = currentNode.row;
	    int col = currentNode.col;
	    if (inMaze(row - 1, height) && !maze[row - 1][col].isVisited() && !maze[row - 1][col].isWall()) {
	        north = maze[row - 1][col];
	        north.visited = true; 
	        north.parent = currentNode; 
	        neighbors.add(north); 
	    }
	    if (inMaze(row + 1, height) && !maze[row + 1][col].isVisited() && !maze[row + 1][col].isWall()) {
	        south = maze[row + 1][col];
	        south.visited = true; 
	        south.parent = currentNode; 
	        neighbors.add(south); 
	    }
	    if (inMaze(col - 1, width) && !maze[row][col - 1].isVisited() && !maze[row][col - 1].isWall()) {
	        west = maze[row][col - 1];
	        west.visited = true; 
	        west.parent = currentNode;
	        neighbors.add(west); 
	    }
	    if (inMaze(col + 1, width) && !maze[row][col + 1].isVisited() && !maze[row][col + 1].isWall()) {
	        east = maze[row][col + 1];
	        east.visited = true; 
	        east.parent = currentNode; 
	        neighbors.add(east); 
	    }
	    return neighbors;
	}

	public void solveBFS() {
		Queue<Node> queue = new LinkedList<>();
	    Node startNode = maze[startX][startY];
	    startNode.visited = true; 
	    queue.add(startNode);
	    while (!queue.isEmpty()) {
	        Node currentNode = queue.poll();
	        if (currentNode.content == 'G') {
	            backtrack(currentNode);
	            writeOutput();
	            return;
	        }
	        ArrayList<Node> neighbors = getNeighbors(currentNode);
	        for (Node neighbor : neighbors) {
	            queue.add(neighbor);
	        }
	    }
	}

	public void solveDFS() {
	    Stack<Node> stack = new Stack<>();
	    Node startNode = maze[startX][startY];
	    startNode.visited = true; 
	    stack.push(startNode);
	    while (!stack.isEmpty()) {
	        Node currentNode = stack.pop();
	        if (currentNode.content == 'G') {
	            backtrack(currentNode);
	            writeOutput();
	            return;
	        }
	        ArrayList<Node> neighbors = getNeighbors(currentNode);
	        for (Node neighbor : neighbors) {
	            stack.push(neighbor);
	        }
	    }
	}

}
