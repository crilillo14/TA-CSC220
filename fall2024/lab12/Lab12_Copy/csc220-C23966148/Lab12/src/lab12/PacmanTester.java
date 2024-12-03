package lab12;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;

import java.io.*;


public class PacmanTester {
    public static void main(String[] args) {
        ArrayList<Boolean> results = new ArrayList<>();

        
        results.add(testMaze("bigMaze.txt", "bigMazeBFSSol.txt", "bigMazeDFSSol.txt"));
        results.add(testMaze("demoMaze.txt", "demoMazeBFSSol.txt", "demoMazeDFSSol.txt"));

       
        for (int i = 0; i < results.size(); i++) {
           
        	System.out.println("Test " + (i + 1) + ": " + (results.get(i) ? "Pass" : "Fail"));
        }
    }

    private static boolean testMaze(String inputFile, String bfsSolution, String dfsSolution) {
        
        Pacman pacmanRun = new Pacman(inputFile, "output.txt");

        System.out.println("BFS: " + inputFile);
        pacmanRun.solveBFS();
        
        printFileContent("output.txt", "BFS Output:");
        boolean bfsResult = compareFiles("output.txt", bfsSolution);
        System.out.println("BFS " + (bfsResult ? "pass" : "fail"));

        
        System.out.println("DFS: " + inputFile);
        pacmanRun.solveDFS();
        printFileContent("output.txt", "DFS Output:");
        boolean dfsResult = compareFiles("output.txt", dfsSolution);
        System.out.println("DFS " + (dfsResult ? "pass" : "fail"));

       
        return bfsResult && dfsResult;
    }

    private static void printFileContent(String fileName, String testResult) {
    	
        System.out.println(testResult);
       
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            
        	String line;
            
        	while ((line = reader.readLine()) != null) {
                
        		System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean compareFiles(String outputFile, String solvedFile) {
        
        try (BufferedReader outputReader = new BufferedReader(new FileReader(outputFile));
             
        	BufferedReader solutionReader = new BufferedReader(new FileReader(solvedFile))) {

            String outputSol;
            String solutionOut;
           
            while ((outputSol = outputReader.readLine()) != null && (solutionOut = solutionReader.readLine()) != null) {
                
            	if (!outputSol.equals(solutionOut)) {
                    
            		return false;
            		
                }
            }
            
            return outputReader.readLine() == null && solutionReader.readLine() == null;
       
        } catch (IOException e) {
        	
            e.printStackTrace();
            return false;
            
        }
    }
}