package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class AnagramUtil {
	
    // Reads words from a file (assumed to contain one word per line)
    // Returns the words as an array of strings.
    public static String[] readFile(String filename)
    {
        ArrayList<String> results = new ArrayList<String>();
        try
        {
            BufferedReader input = new BufferedReader(new FileReader(filename));
            while(input.ready())
            {
                results.add(input.readLine());
            }
            input.close();
        }
        catch(Exception e)
        {e.printStackTrace();}
        String[] retval = new String[1];
        return results.toArray(retval);
    }
    
    public static String[] getLargestAnagramGroup(String filename){
        String[] words = readFile(filename);
        String[] toReturn = getLargestAnagramGroup(words);
        return toReturn;
    }
    
    public static String[] getLargestAnagramGroup(String[] stringList){
        // @TAs: This is the easiest way to implement this function.
        // @TAs: There are more smart ways to accomplish this...
        
        insertionSort(stringList);
        
        if (stringList.length <= 1)
            return new String[0];
        
        int end = 0, length = 1, i = 0, maxLength = 0;
        
        for (i = 0; i < stringList.length-1; i++){
            if (areAnagrams(stringList[i], stringList[i+1])){
                length++;
            }else{
                if (length > maxLength && length > 1){
                    maxLength = length;
                    end = i;
                }
                length = 1;
            }
        }
        
        // don't forget one last check
        // if the longest list is the last one
        if (length > maxLength && length > 1){
            maxLength = length;
            end = i;
        }		
        
        
        //prepare to return
        String[] toReturn = new String[maxLength];
        for (int j = 0; j < maxLength; j++)
            toReturn[j] = stringList[j+end-maxLength+1];
        
        return toReturn;
    }
    
    public static boolean areAnagrams(String inputString1, String inputString2){
        return sort(inputString1).equals(sort(inputString2));
    }
    
    public static String sort(String inputString){
        if (inputString == null)
            return null;
        
        // are not allowed to use Arrays.sort
        char[] arr = inputString.toLowerCase().toCharArray();
        
        for(int i=1; i < arr.length; i++)
        {
            char index = arr[i];
            int j = i;
            while(j>0 && arr[j-1]>index)
            { 
                arr[j] = arr[j-1];
                j--; 
            } 
            arr[j] = index; 
        } 
        
        return new String(arr);
    }	
    
    public static void insertionSort(String[] inputList){
        if (inputList.length <= 1)
            return;
        
        OrderStrings c = new OrderStrings();
        
        for(int i=1; i < inputList.length; i++) 
        { 
            String index = inputList[i]; 
            int j = i; 
            while(j>0 && c.compare(sort(inputList[j-1]), sort(index))>0) 
            { 
                inputList[j] = inputList[j-1]; 
                j--; 
            } 
            inputList[j] = index;  
        } 
        
    }
	
	public static void main(String[] args){
		
		// Reads a text file with a single word per line, returns them as an array of Strings
		String[] words_copy = readFile("sample_word_list.txt");
		
				
		insertionSort(words_copy);
		
		System.out.println("===============");		
		
		for (int i = 0; i < words_copy.length; i++)
			System.out.println(sort(words_copy[i]));
		
		String[] s3 = getLargestAnagramGroup("sample_word_list.txt");
		
		System.out.println("===============");		
		
		for (int i = 0; i < s3.length; i++)
			System.out.println(s3[i]);		
	}
}