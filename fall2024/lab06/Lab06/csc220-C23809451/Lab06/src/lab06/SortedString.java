package lab06;

import java.util.Arrays;

public class SortedString implements Comparable<SortedString> {
    private String unsorted;
    private String sorted;

    public SortedString(String unsorted) {
        this.unsorted = unsorted;
        char[] chars = unsorted.toLowerCase().toCharArray();
        Arrays.sort(chars);
        this.sorted = new String(chars);
    }

    public static SortedString[] toSortedString(String[] strings) {
        SortedString[] out = new SortedString[strings.length];
        for (int i = 0; i < out.length; i++)
            out[i] = new SortedString(strings[i]);
        return out;
    }


    public String getUnsorted() {
        return unsorted;
    }

    @Override
    public int compareTo(SortedString other) {
        return this.sorted.compareTo(other.sorted);
    }

    public String getSorted() {
        return sorted;
    }

    @Override
    public String toString() {
        return unsorted + "/" + sorted;
    }
}
