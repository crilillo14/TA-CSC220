package lab06;

public class Tester {

    //You will need to write your own tests

    /* 
    * As a reminder these are the methods we did in lab:
    * 1.1) SortedString Constuctor
    * 1.2) SortedString compareTo(SortedString other)
    * 2) AnagramUtil areAnagrams(SortedString str1, SortedString str2)
    * 3) InsetionSort sort(E[] array) (HINT: use the toSortedString() convenience function from SortedString)
    * 4.1, 4.2, 4.3) InsertionSort  O(int n), fit(E[] array), and predict(int n)
    */

    /* 
    * As a reminder these are the methods we did in assignment:
    * 1) AnagramUtil getLargestAnagramGroup(SortedString[] stringList) (Hint: You can use getLargestAnagramGroup(String filename))
    * 2) MergeSort sort(E[] array1, E[] array2, int first, int last) (Hint: You can use sort(E[] array))
    * 3.1, 3.2, 3.3) MergeSort  O(int n), fit(E[] array), and predict(int n)
    */
	
	public static void main(String[] args) {
		SortedString zeb = new SortedString("zebra");
		System.out.println(zeb.toString());
		
		SortedString Zeb = new SortedString("Zebra");
		System.out.println(Zeb.toString());
		
		SortedString joy = new SortedString("joy");
		SortedString ski = new SortedString("ski");
		SortedString bel = new SortedString("below");
		SortedString Elb = new SortedString("Elbow");
		
		System.out.println("joy.compareTo(ski): " + joy.compareTo(ski));
		System.out.println("joy=" +joy.getSorted() + " ski=" + ski.getSorted());
		System.out.println("below.compareTo(Elbow): " + bel.compareTo(Elb));
		System.out.println("zebra.compareTo(joy): " + zeb.compareTo(joy));

		System.out.println("---------------------------------");
		
		SortedString empty = new SortedString("");
		SortedString elb = new SortedString("elbow");
		System.out.println("AnagramUtil.areAnagrams(empty, bel): " + AnagramUtil.areAnagrams(empty, bel));
		System.out.println("AnagramUtil.areAnagrams(bel, bel): " + AnagramUtil.areAnagrams(bel, bel));
		System.out.println("AnagramUtil.areAnagrams(bel, Elb): " + AnagramUtil.areAnagrams(bel,Elb));
		System.out.println("AnagramUtil.areAnagrams(bel, joy): " + AnagramUtil.areAnagrams(bel,joy));
		System.out.println("AnagramUtil.areAnagrams(bel, elb): " + AnagramUtil.areAnagrams(bel,elb));
		System.out.println("AnagramUtil.areAnagrams(zeb, Zeb): " + AnagramUtil.areAnagrams(zeb, Zeb));
		
		System.out.println("---------------------------------");
		
		InsertionSort<Integer> intIS = new InsertionSort<Integer>();
		Integer[] one = {1};
		Integer[] two = {6,2};
		Integer[] sorted = {1,2,3,4,5,6,7,8,9};
		Integer[] random = {5,3,7,1,8,2,9,6,4};
		
		Integer[] intArr = intIS.sort(one);
		String intArrStr = "[";
		for (Integer i:intArr) {
			intArrStr+=i+" ";
		}
		intArrStr+="]";
		System.out.println("One Element: " + intArrStr);
		intArr = intIS.sort(two);
		intArrStr = "[";
		for (Integer i:intArr) {
			intArrStr+=i+" ";
		}
		intArrStr+="]";
		System.out.println("Two Elements: " + intArrStr);
		intArr = intIS.sort(sorted);
		intArrStr = "[";
		for (Integer i:intArr) {
			intArrStr+=i+" ";
		}
		intArrStr+="]";
		System.out.println("Sorted Elements: " + intArrStr);
		intArr = intIS.sort(random);
		intArrStr = "[";
		for (Integer i:intArr) {
			intArrStr+=i+" ";
		}
		intArrStr+="]";
		System.out.println("Random Elements: " + intArrStr);
		
		System.out.println("---------------------------------");
		
		InsertionSort<SortedString> ssIs = new InsertionSort<SortedString>();
		String[] strArr = {"joy", "ski", "fed", "cat"};
		SortedString[] sortStrArr = SortedString.toSortedString(strArr);
		
		SortedString[] sortedArr = ssIs.sort(sortStrArr);
		String ssArrStr = "[";
		for(SortedString ss: sortedArr) {
			ssArrStr+=ss.getUnsorted() + " ";
		}
		ssArrStr+= "]";
		System.out.println("Sorted SortedString[]: " + ssArrStr);
		
		System.out.println("---------------------------------");
		
		Integer[] realRandom = new Integer[10];
		for (int i = 0; i<10; i++) {
			realRandom[i] = (int) (Math.random() * 10000);
		}
		intIS.fit(realRandom);
		System.out.println("To Sort 100000 elements using IS it would take: " + intIS.predict(100000) + " microseconds");
		
		String[] s3= AnagramUtil.getLargestAnagramGroup("sample_word_list.txt");
		String StrArr = "[";
		for(String str: s3) {
			StrArr += str + " ";
		}
		StrArr += "]";
		System.out.println("getLargestAnagramGroup: " + StrArr);
		
		MergeSort<Integer> intMS = new MergeSort<Integer>();
		Integer[] realRandom1 = new Integer[10];
		for (int i = 0; i<10; i++) {
			realRandom1[i] = (int) (Math.random() * 10000);
		}
		intMS.fit(realRandom);
		System.out.println("To Sort 100000 elements using MS it would take: " + intMS.predict(100000) + " microseconds");
		}

}

