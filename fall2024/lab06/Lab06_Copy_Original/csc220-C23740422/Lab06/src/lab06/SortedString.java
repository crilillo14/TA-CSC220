package lab06;

import java.util.Arrays;


public class SortedString implements Comparable<SortedString> {

    // The original unsorted string
    private String unsorted;
    
    // The sorted version of the original string
    private String sorted;

    
    public SortedString(String unsorted) {
        this.unsorted = unsorted;
        // Convert to lowercase, sort the characters, and store the sorted version
        char[] chars = unsorted.toLowerCase().toCharArray();
        Arrays.sort(chars);
        this.sorted = new String(chars);
    }

    
    public static SortedString[] toSortedString(String[] strings) {
        SortedString[] out = new SortedString[strings.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = new SortedString(strings[i]);
        }
        return out;
    }

   
    public String getSorted() {
        return sorted;
    }

    
    public String getUnsorted() {
        return unsorted;
    }

   
    @Override
    public int compareTo(SortedString other) {
        return this.sorted.compareTo(other.getSorted());
    }

    
    @Override
    public String toString() {
        return unsorted + "/" + sorted;
    }
}
