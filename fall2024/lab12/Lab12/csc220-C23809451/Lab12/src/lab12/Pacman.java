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
            parent = null;
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

    private boolean inMaze(int index, int bound) {
        return index < bound && index >= 0;
    }

    /** helper method to construct the solution path from S to G
     *  NOTE this path is built in reverse order, starting with the goal G.
     */
    private void backtrack(Node end) {
        Node current = end;
        while (current.parent != null) {
            if (current.content != 'S' && current.content != 'G') {
                current.content = '.';  // mark the path
            }
            current = current.parent;
        }
    }

    /** writes the solution to file */
    public void writeOutput() {
        try (PrintWriter output = new PrintWriter(new FileWriter(outputFileName))) {
            // Write the height and width
            output.println(height + " " + width);

            // Write the maze content
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    output.print(maze[i][j].content);
                }
                output.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(height).append(" ").append(width).append("\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                s.append(maze[i][j].content).append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    /** helper method to construct a graph from a given input file;
     *  all member variables (e.g. maze, startX, startY) should be
     *  initialized by the end of this method
     */
    private void buildGraph() {
        try (BufferedReader input = new BufferedReader(new FileReader(inputFileName))) {
            // Read the first line to get the height and width
            String[] dimensions = input.readLine().split(" ");
            height = Integer.parseInt(dimensions[0]);
            width = Integer.parseInt(dimensions[1]);

            // Initialize the maze array
            maze = new Node[height][width];

            // Read the maze content line by line
            for (int i = 0; i < height; i++) {
                String line = input.readLine();
                for (int j = 0; j < width; j++) {
                    char c = line.charAt(j);
                    maze[i][j] = new Node(i, j, c);
                    if (c == 'S') {
                        startX = i;
                        startY = j;
                    }
                }
            }
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

        // Check north
        if (row > 0 && !maze[row - 1][col].visited && maze[row - 1][col].content != 'X') {
            Node north = maze[row - 1][col];
            north.visited = true;
            north.parent = currentNode;
            neighbors.add(north);
        }

        // Check south
        if (row < maze.length - 1 && !maze[row + 1][col].visited && maze[row + 1][col].content != 'X') {
            Node south = maze[row + 1][col];
            south.visited = true;
            south.parent = currentNode;
            neighbors.add(south);
        }

        // Check west
        if (col > 0 && !maze[row][col - 1].visited && maze[row][col - 1].content != 'X') {
            Node west = maze[row][col - 1];
            west.visited = true;
            west.parent = currentNode;
            neighbors.add(west);
        }

        // Check east
        if (col < maze[0].length - 1 && !maze[row][col + 1].visited && maze[row][col + 1].content != 'X') {
            Node east = maze[row][col + 1];
            east.visited = true;
            east.parent = currentNode;
            neighbors.add(east);
        }

        return neighbors;
    }

    /** helper method to find the start node */
    private Node findStartNode() {
        return maze[startX][startY];
    }

    /** Pacman uses BFS strategy to solve the maze */
    public void solveBFS() {
        Queue<Node> queue = new LinkedList<>();
        Node start = findStartNode();
        start.visited = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.content == 'G') {
                backtrack(current);
                writeOutput();
                return;
            }

            ArrayList<Node> neighbors = getNeighbors(current);
            queue.addAll(neighbors);
        }

        // If no solution
        writeOutput();
    }

    /** Pacman uses DFS strategy to solve the maze */
    public void solveDFS() {
        Stack<Node> stack = new Stack<>();
        Node start = findStartNode();
        start.visited = true;
        stack.push(start);

        while (!stack.isEmpty()) {
            Node current = stack.pop();

            if (current.content == 'G') {
                backtrack(current);
                writeOutput();
                return;
            }

            ArrayList<Node> neighbors = getNeighbors(current);
            for (Node neighbor : neighbors) {
                stack.push(neighbor);
            }
        }

        // If no solution
        writeOutput();
    }

}
