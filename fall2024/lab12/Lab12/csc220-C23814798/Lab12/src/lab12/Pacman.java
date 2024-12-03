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

    private boolean inMaze(int index, int bound) {
        return index < bound && index >= 0;
    }

    /** helper method to construct the solution path from S to G
     *  NOTE this path is built in reverse order, starting with the goal G.
    */
    private void backtrack(Node end) {
        Node current = end;
        while (current != null && current.content != 'S') {
            if (current.content != 'G') {
                current.content = '.';
            }
            current = current.parent;
        }
    }

    /** writes the solution to file */
    public void writeOutput() {
        try (PrintWriter output = new PrintWriter(new FileWriter(outputFileName))) {
            output.println(height + " " + width);
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
            String[] dimensions = input.readLine().split(" ");
            height = Integer.parseInt(dimensions[0]);
            width = Integer.parseInt(dimensions[1]);
            maze = new Node[height][width];

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

        if (inMaze(row - 1, height) && !maze[row - 1][col].visited && !maze[row - 1][col].isWall()) {
            neighbors.add(visitNeighbor(maze[row - 1][col], currentNode));
        }

        if (inMaze(row + 1, height) && !maze[row + 1][col].visited && !maze[row + 1][col].isWall()) {
            neighbors.add(visitNeighbor(maze[row + 1][col], currentNode));
        }

        if (inMaze(col - 1, width) && !maze[row][col - 1].visited && !maze[row][col - 1].isWall()) {
            neighbors.add(visitNeighbor(maze[row][col - 1], currentNode));
        }

        if (inMaze(col + 1, width) && !maze[row][col + 1].visited && !maze[row][col + 1].isWall()) {
            neighbors.add(visitNeighbor(maze[row][col + 1], currentNode));
        }

        return neighbors;
    }

    private Node visitNeighbor(Node neighbor, Node parent) {
        neighbor.visited = true;
        neighbor.parent = parent;
        return neighbor;
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

            queue.addAll(getNeighbors(current));
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

            stack.addAll(getNeighbors(current));
        }
        writeOutput(); 
    }
}
