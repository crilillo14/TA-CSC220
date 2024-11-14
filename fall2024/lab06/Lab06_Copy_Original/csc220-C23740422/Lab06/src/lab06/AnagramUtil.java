package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Utility class for handling anagram-related operations such as determining
 * if two strings are anagrams and finding the largest group of anagrams from
 * a list.
 */
public class AnagramUtil {

    
    public static boolean areAnagrams(SortedString str1, SortedString str2) {
        // Compare the sorted strings of both SortedString objects
        return str1.compareTo(str2) == 0;
    }

   
    public static String[] getLargestAnagramGroup(String filename) {
        SortedString[] words = readFile(filename);
        String[] toReturn = getLargestAnagramGroup(words);
        return toReturn;
    }

     
    public static String[] getLargestAnagramGroup(SortedString[] stringList) {

        if (stringList == null || stringList.length == 0) {
            return new String[0];
        }

       
        MergeSort<SortedString> sorter = new MergeSort<>(); 
        SortedString[] sortedList = sorter.sort(stringList); // Sort the list

        int maxLength = 1;
        int currentLength = 1;
        int startOfMaxGroup = 0;

        // Iterate through sortedList to find the largest group of anagrams
        for (int i = 1; i < sortedList.length; i++) {
            if (areAnagrams(sortedList[i], sortedList[i - 1])) {
                currentLength++;
            } else {
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    startOfMaxGroup = i - currentLength;
                }
                currentLength = 1;
            }
        }

        // Final check to ensure the last group is handled
        if (currentLength > maxLength) {
            maxLength = currentLength;
            startOfMaxGroup = sortedList.length - currentLength;
        }

        // Prepare the return array containing the largest group of anagrams
        String[] toReturn = new String[maxLength];
        for (int j = 0; j < maxLength; j++) {
            toReturn[j] = sortedList[startOfMaxGroup + j].getUnsorted();
        }

        return toReturn;
    }

    
    public static SortedString[] readFile(String filename) {
        ArrayList<SortedString> results = new ArrayList<>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(filename));
            while (input.ready()) {
                results.add(new SortedString(input.readLine()));
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SortedString[] retval = new SortedString[results.size()];
        return results.toArray(retval);
    }
}
