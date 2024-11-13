package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;

public class PathFinder {
	
	public static void printMaze(Node[][] maze, int width, int height){
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				System.out.print(maze[i][j].content);
			}
			System.out.println();
		}
	}	
	
	public static boolean InMaze(int index, int bound){
		if (index < bound && index >= 0)
			return true;
		return false;
	}
	
	public static void MarkPath(Node[][] nodes, Node end){
        while ( end.parent != null) 
        {                   // changes contents until it meets null pointer.
            end = end.parent;
            if (end.content == 'S')
            	break;
            nodes[end.row][end.col].content = '.';
        }
	}
	
	
	public static void solveMaze(String inputFile, String outputFile){
		try {
			BufferedReader input = null;

			input = new BufferedReader(new FileReader(inputFile));
			String[] dimensions = input.readLine().split(" ");
			int height = Integer.parseInt(dimensions[0]);
			int width = Integer.parseInt(dimensions[1]);
			
			Node[][] nodes;
			nodes = new Node[height][width];
			
			String currentLine;
			int startx = -1, starty = -1; 
			
			int row = 0;
			while ((currentLine = input.readLine()) != null) {
					for (int col = 0; col < width; col++){
						char currentChar = currentLine.charAt(col);
						nodes[row][col] = new Node(row, col, currentChar);
						if (currentChar == 'S'){
							startx = row;
							starty = col;
						}
					}
				row++;
			}
			
			//printMaze(nodes, width, height);			
			input.close();
			// make a copy to return
			Node[][] toReturn = new Node[height][width];
			for (int i = 0; i < height; i++)
				for (int j = 0; j < width; j++)
					toReturn[i][j] = nodes[i][j];
			
			// now start to solve
			Node north, south, east, west;
			
			Queue<Node> Q = new LinkedList<Node>();
			
			Node startNode = new Node(startx, starty, 'S');
			
			startNode.Mark();
			
			Q.add(startNode);
			
			while (!Q.isEmpty()){
				Node currentNode = (Node)Q.remove();
				if( currentNode.content == 'G' ){               
					MarkPath(toReturn, currentNode);
	            }				
				
				//inspect the north neighbor
				if (InMaze(currentNode.row, height)){
					north = nodes[(currentNode.row)-1][currentNode.col];
					if ( north.content != 'X' && !(north.isMarked()) ){
						// not a wall and not visited
			            north.Mark(); // set it as visited
			            
			            north.parent = currentNode;
			            Q.add(north);
			        }
				}// north neighbor
				
				//inspect the south neighbor
				if (InMaze(currentNode.row, height)){
					south = (nodes[(currentNode.row+1)][currentNode.col]);
					if ( south.content != 'X' && !(south.isMarked()) ){
						// not a wall and not visited
			            south.Mark(); // set it as visited
			            
			            south.parent = currentNode;
			            Q.add(south);
			        }
				}// south neighbor
				
				
				//inspect the east neighbor
				if (InMaze(currentNode.col, width)){
					east = (nodes[(currentNode.row)][currentNode.col-1]);
					if ( east.content != 'X' && !(east.isMarked()) ){
						// not a wall and not visited
						east.Mark(); // set it as visited
			            
						east.parent = currentNode;
			            Q.add(east);
			        }
				}// east neighbor				
				
				//inspect the west neighbor
				if (InMaze(currentNode.col, width)){
					west = (nodes[(currentNode.row)][currentNode.col+1]);
					if ( west.content != 'X' && !(west.isMarked()) ){
						// not a wall and not visited
						west.Mark(); // set it as visited
			            
						west.parent = currentNode;
			            Q.add(west);
			        }
				}// west neighbor								
			}//while
			
			//printMaze(toReturn, width, height);
			
			// write output
			PrintWriter output = new PrintWriter(new FileWriter(outputFile));
			output.println(height + " " + width);
			for (int i = 0; i < height; i++){
				for (int j = 0; j < width; j++){
					output.print(toReturn[i][j].content);
				}
				output.println();
			}
				
			output.close();

		} catch (IOException e) {
			e.printStackTrace();
		}		

	}

}
