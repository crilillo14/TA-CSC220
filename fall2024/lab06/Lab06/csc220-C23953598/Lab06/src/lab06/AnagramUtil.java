package lab06;

import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Utility class for handling anagram-related operations such as determining
 * if two strings are anagrams and finding the largest group of anagrams from
 * a list.
 */
public class AnagramUtil {

    public static boolean areAnagrams(SortedString str1, SortedString str2) {
        return str1.getSorted().equals(str2.getSorted());
    }

    public static String[] getLargestAnagramGroup(String filename) {
        SortedString[] words = readFile(filename);
        return getLargestAnagramGroup(words);
    }

    public static String[] getLargestAnagramGroup(SortedString[] stringList) {
        if (stringList.length == 0) {
            return new String[0];
        }

        Arrays.sort(stringList);

        int maxLength = 1, currentLength = 1, end = 0;
        for (int i = 1; i < stringList.length; i++) {
            if (areAnagrams(stringList[i], stringList[i - 1])) {
                currentLength++;
                if (currentLength > maxLength) {
                    maxLength = currentLength;
                    end = i;
                }
            } else {
                currentLength = 1;
            }
        }

        if (maxLength == 1) {
            return new String[0];
        }

        String[] result = new String[maxLength];
        for (int i = 0; i < maxLength; i++) {
            result[i] = stringList[end - maxLength + 1 + i].getUnsorted();
        }

        return result;
    }

    public static SortedString[] readFile(String filename) {
        ArrayList<SortedString> results = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = input.readLine()) != null) {
                results.add(new SortedString(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return results.toArray(new SortedString[0]);
    }
}
