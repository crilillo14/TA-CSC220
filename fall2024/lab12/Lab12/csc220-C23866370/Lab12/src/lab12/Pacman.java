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
            parent = null;
            this.row = x;
            this.col = y;
        }

        public boolean isWall() {
            return content == 'X';
        }

        public boolean isVisited() {
            return visited;
        }
    }

    /** constructor */
    public Pacman(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        buildGraph();
    }

    /** helper method to construct a graph from a given input file */
    private void buildGraph() {
        try (BufferedReader input = new BufferedReader(new FileReader(inputFileName))) {
            // Read the first line to get height and width
            String[] dimensions = input.readLine().split(" ");
            height = Integer.parseInt(dimensions[0]);
            width = Integer.parseInt(dimensions[1]);

            // Initialize the maze
            maze = new Node[height][width];

            // Read the rest of the lines to populate the maze
            for (int i = 0; i < height; i++) {
                String line = input.readLine();
                for (int j = 0; j < width; j++) {
                    char c = line.charAt(j);
                    maze[i][j] = new Node(i, j, c);

                    // Check for the starting point 'S'
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

    /** returns an ArrayList of all unvisited neighbors in the order: North, South, West, East */
    public ArrayList<Node> getNeighbors(Node currentNode) {
        ArrayList<Node> neighbors = new ArrayList<>();

        // Define cardinal directions: (row, col) changes for North, South, West, East
        int[][] directions = {
            {-1, 0}, // North
            {1, 0},  // South
            {0, -1}, // West
            {0, 1}   // East
        };

        for (int[] dir : directions) {
            int newRow = currentNode.row + dir[0];
            int newCol = currentNode.col + dir[1];

            // Check bounds and ensure the neighbor is not visited or a wall
            if (inMaze(newRow, height) && inMaze(newCol, width)) {
                Node neighbor = maze[newRow][newCol];
                if (!neighbor.isWall() && !neighbor.isVisited()) {
                    neighbor.visited = true;  // Mark as visited
                    neighbor.parent = currentNode;  // Set parent for backtracking
                    neighbors.add(neighbor);
                }
            }
        }

        return neighbors;
    }

    /** helper method to check if an index is within bounds */
    private boolean inMaze(int index, int bound) {
        return index >= 0 && index < bound;
    }

    /** backtracks from the end node to the start node, marking the path with '.' */
    private void backtrack(Node end) {
        Node current = end;

        while (current != null && current.content != 'S') {
            if (current.content == ' ') {
                current.content = '.';  // Mark the path
            }
            current = current.parent;  // Move to the parent node
        }
    }

    /** writes the solution to file */
    public void writeOutput() {
        try (PrintWriter output = new PrintWriter(new FileWriter(outputFileName))) {
            // Write the height and width as the first line
            output.println(height + " " + width);

            // Write the maze row by row
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    output.print(maze[i][j].content);
                }
                output.println(); // Move to the next line after each row
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** solves the maze using BFS */
    public void solveBFS() {
        Queue<Node> queue = new LinkedList<>();
        Node startNode = maze[startX][startY];
        startNode.visited = true;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // Check if we've reached the goal
            if (current.content == 'G') {
                backtrack(current);
                writeOutput();
                return;
            }

            // Get valid neighbors and add them to the queue
            for (Node neighbor : getNeighbors(current)) {
                queue.add(neighbor);
            }
        }

        // If no path exists, the maze remains unchanged
        writeOutput();
    }

    /** solves the maze using DFS */
    public void solveDFS() {
        Stack<Node> stack = new Stack<>();
        Node startNode = maze[startX][startY];
        startNode.visited = true;
        stack.push(startNode);

        while (!stack.isEmpty()) {
            Node current = stack.pop();

            // Check if we've reached the goal
            if (current.content == 'G') {
                backtrack(current);
                writeOutput();
                return;
            }

            // Get valid neighbors and add them to the stack
            for (Node neighbor : getNeighbors(current)) {
                stack.push(neighbor);
            }
        }

        // If no path exists, the maze remains unchanged
        writeOutput();
    }
}

