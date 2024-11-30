package lab06;

import java.util.Arrays;

/**
 * This class represents a string and its sorted version.
 * It is used for comparing strings based on their sorted character order.
 */
public class SortedString implements Comparable<SortedString> {

    private String unsorted;
    private String sorted;

    /**
     * Constructor for SortedString.
     * Initializes the unsorted and sorted versions of the input string.
     *
     * @param unsorted the original unsorted string
     */
    public SortedString(String unsorted) {
        this.unsorted = unsorted;
        char[] chars = unsorted.toLowerCase().toCharArray();
        Arrays.sort(chars);
        this.sorted = new String(chars);
    }

    /**
     * Convenience function to convert an array of strings to an array of SortedString.
     *
     * @param strings the input array of strings
     * @return the SortedString representation of the input strings
     */
    public static SortedString[] toSortedString(String[] strings) {
        SortedString[] out = new SortedString[strings.length];
        for (int i = 0; i < out.length; i++)
            out[i] = new SortedString(strings[i]);
        return out;
    }

    public String getSorted() {
        return sorted;
    }

    public String getUnsorted() {
        return unsorted;
    }

    /**
     * Compares this SortedString with another SortedString based on their sorted values.
     *
     * @param other the other SortedString to compare to
     * @return comparison result based on sorted strings
     */
    public int compareTo(SortedString other) {
        return this.sorted.compareTo(other.sorted);
    }

    @Override
    public String toString() {
        return unsorted + "/" + sorted;
    }
}
