package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.ArrayList;

public class AnagramUtil {
    public static boolean areAnagrams(SortedString str1, SortedString str2) {
        return str1.getSorted().equals(str2.getSorted());
    }

    public static String[] getLargestAnagramGroup(String filename){
        SortedString[] words = readFile(filename);
        String[] toReturn = getLargestAnagramGroup(words);
        return toReturn;
    }

    public static String[] getLargestAnagramGroup(SortedString[] stringList) {
        Arrays.sort(stringList);
        int maxLength = 0;
        int end = 0, length = 1;

        for (int i = 0; i < stringList.length - 1; i++) {
            if (areAnagrams(stringList[i], stringList[i + 1])) {
                length++;
            } else {
                if (length > maxLength) {
                    maxLength = length;
                    end = i;
                }
                length = 1;
            }
        }

        // Final check in case the largest group is at the end of the list
        if (length > maxLength) {
            maxLength = length;
            end = stringList.length - 1;
        }

        // Build the largest anagram group
        String[] largestGroup = new String[maxLength];
        for (int i = 0; i < maxLength; i++) {
            largestGroup[i] = stringList[end - maxLength + 1 + i].getUnsorted();
        }
        return largestGroup;
    }

    public static SortedString[] readFile(String filename) {
        ArrayList<SortedString> results = new ArrayList<SortedString>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(filename));
            while(input.ready()) {
                results.add(new SortedString(input.readLine()));
            }
            input.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        SortedString[] retval = new SortedString[1];
        return results.toArray(retval);
    }
    

}
