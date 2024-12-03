package lab12;

public class tester {
    public static void main(String[] args) {
        
        
        String[] mazeFiles = {
                "src/mazes/demoMaze.txt",
                "src/mazes/tinyMaze.txt",
                "src/mazes/classic.txt",
                "src/mazes/randomMaze.txt"
                
                
            };

            String bfsOutputFile = "src/outputBFSSol.txt";
            String dfsOutputFile = "src/outputDFSSol.txt";

            
            for (String mazeFile : mazeFiles) {
               
                Pacman pacman = new Pacman(mazeFile, bfsOutputFile);  
                System.out.println("Testing BFS for maze: " + mazeFile);

       
                pacman.solveBFS();
                System.out.println("BFS Solution written to: " + bfsOutputFile);

               
                pacman = new Pacman(mazeFile, dfsOutputFile);  
                System.out.println("Testing DFS for maze: " + mazeFile);

                
                pacman.solveDFS();
                System.out.println("DFS Solution written to: " + dfsOutputFile);
            }
            System.out.println("Testing done");
            
        }
      
    }
    

