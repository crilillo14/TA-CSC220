package lab07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;

public class CheckLab {
	

	public static void main(String[] args) 
	{
		
		// they get up to 35% for submission, compile, run
		
		//*************************** sort is worth 10% *********************************************
		double []gradePoint = {0,0};
	    double []givenPoint = {35,30};
	    String output = "";
		try{
			
			try{
				double answer = Postfixer.InfixEvaluator("10 + 2"); 
				if (answer != 12)
		            output+="## InfixEvaluator(10 + 2) = 12 \n\t " + "yours: " + answer + "\n";
				else
					gradePoint[0]+=100.0/8;
			}catch(Exception exp){
				output+="## InfixEvaluator(10 + 2) = 12 \n\t " + "yours: " + exp + "\n";
			}
			try{
				double answer = Postfixer.InfixEvaluator("10 - 2");
		        if (answer != 8)
		            output+="## InfixEvaluator(10 - 2) = 8 \n\t " + "yours: " + answer + "\n";
		        else
					gradePoint[0]+=100.0/8;
			}catch(Exception exp){
				output+="## InfixEvaluator(10 - 2) = 8 \n\t " + "yours: " + exp + "\n";
			}
			try{
		        double answer =Postfixer.InfixEvaluator("100 * 2 + 12");
		        if (answer != 212)
		            output+="## InfixEvaluator(100 * 2 + 12) = 212 \n\t " + "yours: " + answer + "\n";
		        else
					gradePoint[0]+=100.0/8;
			}catch(Exception exp){
				output+="## InfixEvaluator(100 * 2 + 12) = 212 \n\t " + "yours: " + exp + "\n";
			}
			try{
		        double answer = Postfixer.InfixEvaluator("100 * ( 2 + 12 )");
		        if (answer != 1400)
		            output+="## InfixEvaluator(100*( 2 + 12 )) = 1400 \n\t " + "yours: " + answer + "\n";
		        else
					gradePoint[0]+=100.0/8;
			}catch(Exception exp){
				 output+="## InfixEvaluator(100*( 2 + 12 )) = 1400 \n\t " + "yours: " + exp + "\n";
			}
			try{
				double answer = Postfixer.InfixEvaluator("100 * ( 2 + 12/2 +2*3 )");
		        if (answer != 1400)
		            output+="## InfixEvaluator(100 * (2 + 12/2 + 2*3)) = 1400 \n\t " + "yours: " + answer + "\n";
		        else
					gradePoint[0]+=100.0/8;
			}catch(Exception exp){
				output+="## InfixEvaluator(100 * (2 + 12/2 + 2*3)) = 1400 \n\t " + "yours: " + exp + "\n";
			}
			try{
				double answer = Postfixer.InfixEvaluator("100 * ( 2 + 12 ) / 14");
		        if (answer != 100)
		            output+="## InfixEvaluator(100*(2+12)/14) = 100 \n\tyours: " + answer + "\n";
		        else
					gradePoint[0]+=100.0/8;
			}catch(Exception exp){
				output+="## InfixEvaluator(100*(2+12)/14) = 100  \n\tyours: " + exp + "\n";
			}
			try{
				double answer = Postfixer.InfixEvaluator("100 * ( 2 + 12 )^2 / 14");
		        if ( answer != 1400)
		            output+="## InfixEvaluator(100 * (2+12)^2 /14) = 1400  \n\tyours: " + answer + "\n";
		        else
					gradePoint[0]+=100.0/8;
			}catch(Exception exp){
				output+="## InfixEvaluator(100 * (2+12)^2 /14) = 1400  \n\tyours: " + exp + "\n";
			}
			try{
				double answer = Math.abs(Postfixer.InfixEvaluator(new String("((4+((5*6)/7))-8)")) - 0.2857142857142847);
		        if (answer > 0.000001)
		        	output+="## InfixEvaluator(((4+((5*6)/7))-8)) = 0.2857142857142847  \n\tyours: " + answer + "\n";
		        else
					gradePoint[0]+=100.0/8.0;
			}catch(Exception exp){
				output+="## InfixEvaluator(((4+((5*6)/7))-8)) = 0.2857142857142847  \n\tyours: " + exp + "\n";
			}
			
			
			// postfix
			
			try{
				String answer = Postfixer.PostfixConvertor(new String("(4+5)"));
		        if (!answer.equals("45+"))
		        	output+="## PostfixConvertor((4+5)) = 45+  \n\tyours: " + answer + "\n";
		        else
					gradePoint[1]+=100.0/7.0;
			}catch(Exception exp){
				output+="## PostfixConvertor((4+5)) = 45+  \n\tyours: " + exp + "\n";
			}
			try{
		        String answer = Postfixer.PostfixConvertor(new String("((4+5)*6)"));
		        if (!answer.equals("45+6*"))
		        	output+="## PostfixConvertor(((4+5)*6)) = 45+6*  \n\tyours: " + answer + "\n";
		        else
					gradePoint[1]+=100.0/7.0;
			}catch(Exception exp){
				output+="## PostfixConvertor(((4+5)*6)) = 45+6*  \n\tyours: " + exp + "\n";
			}
			try{
		        String answer = Postfixer.PostfixConvertor(new String("((4+((5*6)/7))-8)"));
		        if (!answer.equals("456*7/+8-"))
		        	output+="## PostfixConvertor(((4+((5*6)/7))-8)) = 456*7/+8-  \n\tyours: " + answer + "\n";
		        else
					gradePoint[1]+=100.0/7.0;
			}catch(Exception exp){
				output+="## PostfixConvertor(((4+((5*6)/7))-8)) = 456*7/+8-  \n\tyours: " + exp + "\n";
			}
			try{
		        String answer = Postfixer.PostfixConvertor(new String("((4+5*(6-7))/8)"));
		        if (!answer.equals("4567-*+8/"))
		        	output+="## PostfixConvertor(((4+5*(6-7))/8)) = 4567-*+8/  \n\tyours: " + answer + "\n";
		        else
					gradePoint[1]+=100.0/7.0;
			}catch(Exception exp){
				output+="## PostfixConvertor(((4+5*(6-7))/8)) = 4567-*+8/  \n\tyours: " + exp + "\n";
			}
			
			try{
		        String answer = Postfixer.PostfixConvertor(new String("(9+(8*7-(6/5^4)*3)*2)"));
		        if (!answer.equals("987*654^/3*-2*+"))
		        	output+="## PostfixConvertor((9+(8*7-(6/5^4)*3)*2)) = 4567-*+8/  \n\tyours: " + answer + "\n";
		        else
					gradePoint[1]+=100.0/7.0;
			}catch(Exception exp){
				output+="## PostfixConvertor((9+(8*7-(6/5^4)*3)*2)) = 4567-*+8/  \n\tyours: " + exp + "\n";
			}
			try{
		        String answer = Postfixer.PostfixConvertor(new String("(9+(8*7-(5-6/5^4)*3)*2)"));
		        if (!answer.equals("987*5654^/-3*-2*+"))
		        	output+="## PostfixConvertor((9+(8*7-(5-6/5^4)*3)*2)) = 987*5654^/-3*-2*+  \n\tyours: " + answer + "\n";
		        else
					gradePoint[1]+=100.0/7.0;
			}catch(Exception exp){
				output+="## PostfixConvertor((9+(8*7-(5-6/5^4)*3)*2)) = 987*5654^/-3*-2*+  \n\tyours: " + exp + "\n";
			}
			try{
				String answer = Postfixer.PostfixConvertor(new String("(9+(8*7-(5-6/5^4)*3)*2^4)"));
		        
		        if (!answer.equals("987*5654^/-3*-24^*+"))
		        	output+="## PostfixConvertor((9+(8*7-(5-6/5^4)*3)*2^4)) = 987*5654^/-3*-24^*+  \n\tyours: " + answer + "\n";
		        else
					gradePoint[1]+=100.0/7.0;
			}catch(Exception exp){
				output+="## PostfixConvertor((9+(8*7-(5-6/5^4)*3)*2^4)) = 987*5654^/-3*-24^*+  \n\tyours: " + exp + "\n";
			}
	       
		}catch(Exception ex){
			int endLen = ex.toString().length()<50?ex.toString().length():50;
			output+="## program threw " + ex.toString().substring(0, endLen) + "\n";
		}
		
		arrayTestPrint(gradePoint,givenPoint);
	    System.out.println(output);
	}
	
	public static void arrayTestPrint(double []arr,double []givenpoint){
		  System.out.print("$$");
		  int i=0;
		  for(double r: arr){
			  //System.out.println(givenpoint[i]);
			  System.out.print(Math.round((r*givenpoint[i])/100.0));
			  System.out.print("$$");
			  i++;
		  }
	  }
	
	
}
