package lab12;

import java.io.*;
import java.util.*;

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

        public boolean isWall() {
            return content == 'X';
        }

        public boolean isVisited() {
            return visited;
        }
    }

    public Pacman(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
        buildGraph();
    }

    private boolean inMaze(int index, int bound) {
        return index < bound && index >= 0;
    }

    private void backtrack(Node end) {
        Node current = end;
        while (current != null && current.content != 'S') {
            if (current.content != 'G') {
                current.content = '.';
            }
            current = current.parent;
        }
    }

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
        StringBuilder sb = new StringBuilder();
        sb.append(height).append(" ").append(width).append("\n");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(maze[i][j].content).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

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

    public ArrayList<Node> getNeighbors(Node currentNode) {
        ArrayList<Node> neighbors = new ArrayList<>();
        int row = currentNode.row;
        int col = currentNode.col;

        if (inMaze(row - 1, height) && !maze[row - 1][col].isWall() && !maze[row - 1][col].visited) {
            neighbors.add(maze[row - 1][col]);
        }
        if (inMaze(row + 1, height) && !maze[row + 1][col].isWall() && !maze[row + 1][col].visited) {
            neighbors.add(maze[row + 1][col]);
        }
        if (inMaze(col - 1, width) && !maze[row][col - 1].isWall() && !maze[row][col - 1].visited) {
            neighbors.add(maze[row][col - 1]);
        }
        if (inMaze(col + 1, width) && !maze[row][col + 1].isWall() && !maze[row][col + 1].visited) {
            neighbors.add(maze[row][col + 1]);
        }

        return neighbors;
    }

    public void solveBFS() {
        Queue<Node> queue = new LinkedList<>();
        Node startNode = maze[startX][startY];
        startNode.visited = true;
        queue.add(startNode);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current.content == 'G') {
                backtrack(current);
                return;
            }
            for (Node neighbor : getNeighbors(current)) {
                neighbor.visited = true;
                neighbor.parent = current;
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
            Node current = stack.pop();
            if (current.content == 'G') {
                backtrack(current);
                return;
            }
            for (Node neighbor : getNeighbors(current)) {
                neighbor.visited = true;
                neighbor.parent = current;
                stack.push(neighbor);
            }
        }
    }
}
