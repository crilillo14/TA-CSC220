package lab06;

import java.util.Arrays;

public class Tester {
    
    public static void main(String[] args) {
    
        SortedString zeb = new SortedString("zebra");
        System.out.println("Sorted String zeb: " + zeb);
        
        SortedString joy = new SortedString("joy");
        SortedString ski = new SortedString("ski");
        SortedString bel = new SortedString("below");
        SortedString Elb = new SortedString("Elbow");
        SortedString elb = new SortedString("elbow");

        System.out.println("joy.compareTo(ski): " + joy.compareTo(ski));
        System.out.println("bel.compareTo(Elbow): " + bel.compareTo(Elb));
        System.out.println("below.compareTo(elbow): " + bel.compareTo(elb));
        System.out.println("zeb.compareTo(joy): " + zeb.compareTo(joy));
        
        SortedString empty = new SortedString("");
        System.out.println("AnagramUtil.areAnagrams(empty, joy): " + AnagramUtil.areAnagrams(empty, joy));
        System.out.println("AnagramUtil.areAnagrams(joy, joy): " + AnagramUtil.areAnagrams(joy, joy));
        System.out.println("AnagramUtil.areAnagrams(bel, elb): " + AnagramUtil.areAnagrams(bel, elb));
        System.out.println("AnagramUtil.areAnagrams(joy, ski): " + AnagramUtil.areAnagrams(joy, ski));
        System.out.println("AnagramUtil.areAnagrams(Elb, bel): " + AnagramUtil.areAnagrams(Elb, bel));

        InsertionSort<Integer> intis = new InsertionSort<Integer>();
        Integer[] one = {1};
        Integer[] two = {2, 1};
        Integer[] sorte = {2, 4, 6, 8};
        Integer[] toSort = {5, 3, 7, 2, 1};

        String str = "";
        Integer[] sorted = intis.sort(one);
        for (Integer i : sorted) {
            System.out.println(i + "");
        }

        InsertionSort<SortedString> ssIS = new InsertionSort<SortedString>();
        String[] strs = {"joy", "ski", "fed", "cat"};
        SortedString[] sstrs = SortedString.toSortedString(strs);

        SortedString[] sortedSS = ssIS.sort(sstrs);
        for (SortedString i : sortedSS) {
            str += i.getUnsorted() + " ";
        }

        System.out.println("Sorted SortedStrings: " + str);
        intis.fit(toSort);

        String[] s3 = AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
    }
}
