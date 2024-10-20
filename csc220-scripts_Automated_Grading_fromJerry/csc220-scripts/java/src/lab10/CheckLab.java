package lab10;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CheckLab {
		
	public static void main(String[] args){
		String path = args[0]; // points to student src folder 
		double []gradePoint = {0};
	    double []givenPoint = {65};
	    StringBuffer output = new StringBuffer("");
		try{
			String []mazes = {"tinyMaze","straight","demoMaze","turn","classic","mediumMaze","bigMaze","unsolvable"};
			String []mazesSol = {"tinyMazeSol","straightSol","demoMazeSol","turnSol","classicSol","mediumMazeSol","bigMazeSol","unsolvableSol"};
			
			for(int i=0;i<mazes.length;i++){
				try {
					String originalmaze = mazes[i]+".txt";
					String genMaze = mazes[i]+"temp.txt";
					PathFinder.solveMaze(path+originalmaze,path+genMaze);
					gradePoint[0] += two_file_compare(path+mazesSol[i]+".txt", path+genMaze, originalmaze, output);
					//gradePoint[0] += two_file_compare(mazesSol[i]+".txt", genMaze, originalmaze, output);
				} catch (Exception e) {
					output.append("## testing on " + mazes[i] + " threw " + e + "\n");
				}
			}
			gradePoint[0] = gradePoint[0]/mazes.length;
			
		}catch(Exception ex){
			int endLen = ex.toString().length()<50?ex.toString().length():50;
			output.append("## program threw "+ ex.toString().substring(0, endLen) + "\n");
		}
		
		arrayTestPrint(gradePoint,givenPoint);
	    System.out.println(output);
	}
	
	public static void arrayTestPrint(double []arr,double []givenpoint){
		  System.out.print("$$") ;
		  int i=0;
		  for(double r: arr){
			  //System.out.println(givenpoint[i]);
			  System.out.print(Math.round((r*givenpoint[i])/100.0));
			  System.out.print("$$") ;
			  i++;
		  }
	  }
	
	public static String allData(String file){
		String text = "";
		try {
			text = new String(Files.readAllBytes(Paths.get(file)), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return text;
	}
	
	public static int two_file_compare(String solution, String generated, String orifile ,StringBuffer message){
		int []sol_count = fourSymbols(allData(solution));
		int []test_count = fourSymbols(allData(generated));
		int points = 0;
		if((test_count[0] + test_count[1] +test_count[2] +test_count[3]) == 0){
			message.append("## "+ orifile+": your solution file is empty\n");
			return 0;
		}else{
			points += 20;
		}
		
		if(sol_count[0] == test_count[0]){
			points += 50;
		}else{
			message.append("## "+orifile+": dot(.) count is incorrect. Correct path size is "+sol_count[0]+"\n");
		}
		
		if(sol_count[1] == test_count[1]){
			points += 10;
		}else{
			message.append("## "+orifile+": wall(X) count is incorrect\n");
		}
		
		if(sol_count[2] == test_count[2]){
			points += 10;
		}else{
			message.append("## " + orifile + ": should have one 'S'; you have no 'S' or you have more than one\n");
		}
		
		if(sol_count[3] == test_count[3]){
			points += 10;
		}else{
			message.append("## "+orifile+": should have one 'G'; you have no 'G' or you have more than one\n");
		}
		return points;
	}
	
	public static int[] fourSymbols(String data){
		int x_count = countSymbols(data, 'X');
		int dot_count = countSymbols(data, '.');
		int s_count = countSymbols(data, 'S');
		int g_count = countSymbols(data, 'G');
		int []total_count = {dot_count,x_count,s_count,g_count};
		return total_count; 
	}
	
	public static int countSymbols(String data, char symb){
		int count = 0 ;
		for(int i=0;i<data.length();i++){
			if(data.charAt(i) == symb){
				count ++;
			}
		}
		return count;
	}
}