package lab07;

import java.util.*;

//Extension of Chapter 14.4 Case Study: Expression Evaluator

public class Postfixer {

	
	public static boolean precident(String one, String two) {
        // @TA: Again there are many ways to do this!
	    return (charToPrcd(one) >= charToPrcd(two));
	}
	
	public static int charToPrcd(String s) {
	    switch (s) {
	        case "+" : case "-" : return 2;
	        case "*" : case "/" : return 3;
	        case "^" : return 5;
	        case "(" : return 1;
	        default : return 0;
	    }
	}	
	
	public static boolean isOperator(String token) {
        // @TA: Again there are many ways to do this!
		return (token.equals("+") || 
		        token.equals("-") || 
		        token.equals("*") || 
		        token.equals("/") ||
		        token.equals("^") ||
		        token.equals("(") ||
		        token.equals(")"));            
	}
	

	public static double evaluate(double operand2, String operator, double operand1){
		// @TA: be careful of the order of pop and evaluation!
        // @TA: DO NOT tell them they have to do it with switch/case - there are many many ways
        // to do this!
		switch(operator){
		case "+":
			return (operand1 + operand2);
		case "-":
			return (operand1 - operand2);
		case "*":
			return (operand1 * operand2);
		case "/":
			return (operand1 / operand2);
		case "^":
			return (Math.pow(operand1, operand2));
		default:
			throw new RuntimeException("illegal operator " + operator);
		}
	}
	
	public static double InfixEvaluator(String line){
		StringSplitter data = new StringSplitter(line);
		
		Stack<String> operators = new Stack<String>();
		Stack<Double> operands = new Stack<Double>();
		
		while( data.hasNext()){
			String next = data.next();
			if (next.equals("(")){
				operators.push(next);
			}else if (next.equals(")")){
				while(!operators.peek().equals("(")){
					operands.push(evaluate(operands.pop(), operators.pop(), operands.pop()));
				}
				//TODO: size check
				operators.pop(); // must be "("
			}else if (isOperator(next)){
				while (operators.size() >0 && precident(operators.peek(), next))
					operands.push(evaluate(operands.pop(), operators.pop(), operands.pop()));
				operators.push(next);
			}else{ // operand
				operands.push(Double.parseDouble(next));
			}
		}
		
		while (operators.size() > 0){
			operands.push(evaluate(operands.pop(), operators.pop(), operands.pop()));
		}
		
		if (operands.size() != 1)
			System.err.println("something went wrong " + operands.size());
		
		return operands.peek();
		
	}
	
	public static String PostfixConvertor(String line){
		StringSplitter data = new StringSplitter(line);
		Stack<String> operators = new Stack<String>();
		String postfix = new String();
		
		while( data.hasNext()){
			String next = data.next();
			if (next.equals("(")){ //open parenthesis
				operators.push(next);
				
			}else if (next.equals(")")){ //close parenthesis
				if (operators.size() != 0){
					while(operators.size() != 0 && !operators.peek().equals("("))
						postfix += operators.pop();
					operators.pop(); // pop "("
				}
				
			}else if (isOperator(next)){ //operator
				if (operators.peek().equals("(")){
					operators.push(next);
				}else{
					while(!operators.peek().equals("(") && precident(operators.peek(), next))
						postfix += operators.pop();
					operators.push(next);
				}
				
			}else{ //operand
				postfix += next;
			}
			
		}
		return postfix;
	}
	
	
	public static void main(String[] args){
		
        if (InfixEvaluator("10 + 2") != 12)
            System.err.println("test1 failed --> your answer should have been 12");
        
        if (InfixEvaluator("10 - 2 * 2 + 1") != 7)
            System.err.println("test1 failed --> your answer should have been 12");
        
        if (InfixEvaluator("100 * 2 + 12") != 212)
            System.err.println("test2 failed --> your answer should have been 212");
        
        if (InfixEvaluator("100 * ( 2 + 12 )") != 1400)
            System.err.println("test3 failed --> your answer should have been 1400");
        
        if (InfixEvaluator("100 * ( 2 + 12 ) / 14") != 100)
            System.err.println("test4 failed --> your answer should have been 100");
        
        
        System.out.println("Testing Done!!!");
        
        
        if (!PostfixConvertor(new String("(4+5)")).equals("45+"))
            System.err.println("test1 failed --> should have been 45+");
        
        if (!PostfixConvertor(new String("((4+5)*6)")).equals("45+6*"))
            System.err.println("test2 failed --> should have been 45+6*");
        
        if (!PostfixConvertor(new String("((4+((5*6)/7))-8)")).equals("456*7/+8-"))
            System.err.println("test3 failed --> should have been 456*7/+8-");
        
        if (!PostfixConvertor(new String("((4+5*(6-7))/8)")).equals("4567-*+8/"))
            System.err.println("test4 failed --> should have been 4567-*+8/");
        
        if (!PostfixConvertor(new String("(9+(8*7-(6/5^4)*3)*2))")).equals("987*654^/3*-2*+"))
            System.err.println("test5 failed --> should have been 987*654^/3*-2*+");
        
        System.out.println("Testing Done!!!");
		
		
	}	

}
