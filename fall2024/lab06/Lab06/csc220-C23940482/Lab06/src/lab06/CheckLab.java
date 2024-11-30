package lab06;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class CheckLab {
    public static String FILE_DIR = "/Users/CristobalLillo_1/TA/csc220-scripts_Automated_Grading_fromJerry/csc220-scripts/java/";
    
    public static boolean ListsEqual(String[] list1, String[] list2) {
        if (list1.length != list2.length)
            return false;
        if (list1 == null || list1.length == 0)
            return true;
            
        // Convert to SortedString arrays and sort them
        SortedString[] sorted1 = SortedString.toSortedString(list1);
        SortedString[] sorted2 = SortedString.toSortedString(list2);
        
        // Use your MergeSort implementation to sort the arrays
        MergeSort<SortedString> sorter = new MergeSort<>();
        sorted1 = sorter.sort(sorted1);
        sorted2 = sorter.sort(sorted2);
        
        // Compare the sorted strings
        for (int i = 0; i < sorted1.length; i++) {
            if (sorted1[i].compareTo(sorted2[i]) != 0)
                return false;
        }
        
        return true;
    }
    
    public static void printList(String[] list) {
        for (int i = 0; i < list.length; i++)
            System.out.print(list[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        // they get up to 35% for submission, compile, run
        
        //*************************** sort is worth 10% *********************************************
        int[] gradePoint = {0,0,0,0,0};
        int[] givenPoint = {10,10,15,15,15};
        String output = "";
        
        try {
            // Test SortedString functionality instead of direct string sorting
            try {
                SortedString test1 = new SortedString("joy");
                if (!test1.getSorted().equals("joy"))
                    output += "TEST FAILED -- sort -- for already sorted strings\n";
                else
                    gradePoint[0] += 50;
            } catch (Exception e) {
                output += "sort(joy) threw " + e + "\n";
            }

            try {
                SortedString test2 = new SortedString("Anagram");
                if (!test2.getSorted().equals("aaagmnr"))
                    output += "TEST FAILED -- sort\n";
                else
                    gradePoint[0] += 50;
            } catch (Exception e) {
                output += "sort(anagram) threw " + e + "\n";
            }

            //********************************** areAnagrams is worth 10% **************************************
            try {
                if (!AnagramUtil.areAnagrams(new SortedString(""), new SortedString("")))
                    output += "TEST FAILED -- areAnagrams -- both null strings\n";
                else
                    gradePoint[1] += 100 * 1.0 / 6.0;
            } catch (Exception e) {
                output += "## areAnagrams(\"\", \"\") threw " + e + "\n";
            }

			try {
				if (AnagramUtil.areAnagrams(new SortedString(""), new SortedString("Anagram")))
					output+="TEST FAILED -- areAnagrams -- null string versus another string\n";
				else
					gradePoint[1]+=100*1.0/6.0;
			} catch (Exception e) {
				output += "## areAnagrams(\"\", Anagram) threw " + e + "\n"; 
			}
			

			try { 
				if (!AnagramUtil.areAnagrams(new SortedString("anagram"), new SortedString("anagram")))
					output+="TEST FAILED -- areAnagrams -- exactly same strings\n";	
				else
					gradePoint[1]+=100*1.0/6.0;
			} catch (Exception e) {
				output += "## areAnagrams(anagram, anagram) threw " + e + "\n"; 
			}


			try {
				if (AnagramUtil.areAnagrams(new SortedString("Util"), new SortedString("Anagram")))
					output+="TEST FAILED -- areAnagrams -- two different strings\n";
				else
					gradePoint[1]+=100*1.0/6.0;
			} catch (Exception e) {
				output += "## areAnagrams(Util, Anagram) threw " + e + "\n"; 
			}


			try { 
				if (!AnagramUtil.areAnagrams(new SortedString("aaagmnr"), new SortedString("anagram")))
					output+="TEST FAILED -- areAnagrams -- shuffled strings\n";
				else
					gradePoint[1]+=100*1.0/6.0;
			} catch (Exception e) {
				output += "## areAnagrams(aaagmnr, anagram) threw " + e + "\n"; 
			}

			try {	
				if (!AnagramUtil.areAnagrams(new SortedString("aaagmnr"), new SortedString("Anagram")))
					output+="TEST FAILED -- areAnagrams -- shuffled strings - case sensitive\n";
				else
					gradePoint[1]+=100*1.0/6.0;
			} catch (Exception e) {
				output += "## areAnagrams(aaagmnr, Anagram) threw " + e + "\n"; 
			}


			// ************************************** insertionSort 15% ***************************************************

			try {
                String[] list1 = {"Anagram"};
                String[] list1_truth = {"Anagram"};
                

				InsertionSort<String> sorter = new InsertionSort<>();
				String[] sortedList1 = sorter.sort(list1);
                
                if (sortedList1.length != 1 || !ListsEqual(sortedList1, list1_truth))
                    output += "TEST FAILED -- insertionSort -- a list with a single element\n";
                else
                    gradePoint[2] += 100 * 1.0 / 3.0;
            } catch (Exception e) {
                output += "## insertionSort() threw " + e + "\n";
            }


			
			try{
				String[] list2 = {"Anagram", "Util", "tester", "insertion"}; 
				String[] list2_truth = {"Anagram", "tester", "insertion", "Util"};
				
				InsertionSort<String> sorter = new InsertionSort<>();
				String[] sortedList2 = sorter.sort(list2);
						
				if (sortedList2.length != list2_truth.length || !ListsEqual(sortedList2, list2_truth))
					output+="TEST FAILED -- insertionSort\n";
				else
					gradePoint[2]+=100*1.0/3.0;
			} catch (Exception e) {
				output += "## insertionSort() threw " + e + "\n"; 
			}

			try{
			
				String[] list3 = {"Anagram", "tester", "Util"}; 	
				String[] list3_truth = {"Anagram", "tester", "Util"};
				
				InsertionSort<String> sorter = new InsertionSort<>();
				String[] sortedList3 = sorter.sort(list3);
				
				if (sortedList3.length != list3_truth.length || !ListsEqual(sortedList3, list3_truth))
					output+="TEST FAILED -- insertionSort -- an already sorted list\n";
				else
					gradePoint[2]+=100*1.0/3.0;

			} catch(Exception e) {
				output += "## insertionSort() threw " + e + "\n"; 
			}


			//********************************** getLargestAnagramGroup is worth 15% **************************************

            // For getLargestAnagramGroup tests, use the existing implementation
            // as it already works with your AnagramUtil implementation

			String[] anagram_middle_truth = {"crates", "Reacts"};
			String[] anagram_end_truth = {"crates", "Reacts"};

            try {
                String[] no_anagram1 = AnagramUtil.getLargestAnagramGroup(FILE_DIR + "no_anagrams.txt");
                if (no_anagram1.length != 0) {
					output += "TEST FAILED -- getLargestAnagramGroup(file) -- list with no anagrams \n";
					output += "Expected: []\n";
					output += "Received: " + Arrays.toString(no_anagram1) + "\n";
				} else
                    gradePoint[3] += 100 * 1.0 / 5.0;
            } catch (Exception e) {
                output += "## getLargestAnagramGroup(file) threw " + e + "\n";
            }

			
			try { 
				
				String[] anagram_middle = AnagramUtil.getLargestAnagramGroup(FILE_DIR+"anagram_middle.txt");
				//String[] anagram_middle_truth = {"crates", "Reacts"};
		
				if (!ListsEqual(anagram_middle, anagram_middle_truth)) {
					output+="TEST FAILED -- getLargestAnagramGroup(file) -- 1 anagram in the middle\n";
					output += "Expected: " + Arrays.toString(anagram_middle_truth) + "\n";
					output += "Received: " + Arrays.toString(anagram_middle) + "\n";
				} 
				else
					gradePoint[3]+=100*1.0/5.0;
				
			} catch (Exception e) {
				output += "## getLargestAnagramGroup(file) threw " + e + "\n"; 
			}
				
			try { 
				
				String[] anagram_end = AnagramUtil.getLargestAnagramGroup(FILE_DIR+"anagram_end.txt");
				//String[] anagram_end_truth = {"crates", "Reacts"};
		
				if (!ListsEqual(anagram_end, anagram_end_truth)){
					output+="TEST FAILED -- getLargestAnagramGroup(file) -- 1 anagram in the end\n";
					output += "Expected: " + Arrays.toString(anagram_end_truth) + "\n";
					output += "Received: " + Arrays.toString(anagram_end) + "\n";
				}
				else {
					gradePoint[3]+=100*1.0/5.0;
				}

			} catch (Exception e) {
				output += "## getLargestAnagramGroup(file) threw " + e + "\n"; 
			}

			try { 
				String[] anagram_sample_file = AnagramUtil.getLargestAnagramGroup(FILE_DIR+"sample_word_list.txt");
				String[] anagram_sample_file_truth = {"carets", "Caters", "caster", "crates", "Reacts", "recast", "traces"};
		
				if (!ListsEqual(anagram_sample_file, anagram_sample_file_truth)) {
					output+="TEST FAILED -- getLargestAnagramGroup(file) -- sample_word_list.txt\n";
					output += "Expected: " + Arrays.toString(anagram_sample_file_truth) + "\n";
					output += "Received: " + Arrays.toString(anagram_sample_file) + "\n";
				}
				else {
					gradePoint[3]+=100*1.0/5.0;
				}
				
			} catch (Exception e) {
				output += "## getLargestAnagramGroup(file) threw " + e + "\n"; 
			}

			try {
				/**
				 * the empty file test has caused major issues for students; they seem to have forgotten 
				 * this case. as such, this is considered the hardest test to pass and will be 
				 * tested last. 
				 */
				
				String[] empty_list1 = AnagramUtil.getLargestAnagramGroup(FILE_DIR+"empty_file.txt");
				
				if (empty_list1.length != 0) {
					output+="TEST FAILED -- getLargestAnagramGroup(file) -- empty file\n";
					output += "Expected: []\n";
					output += "Received: " + Arrays.toString(empty_list1) + "\n";
				}
				else
					gradePoint[3]+=100*1.0/5.0;

			} catch (Exception e) {
				output += "## getLargestAnagramGroup(file) threw " + e + "\n"; 
			}
				
			
			//********** with array 15% 
			try {	

				String[] no_anagram2 = {"praised", "crates", "bats", "tea"};
				// has to be SortedString[] for the ListsEqual method to work, can't cast it.
				SortedString[] s_no_anagram2 = SortedString.toSortedString(no_anagram2);

		
				String[] result2 = AnagramUtil.getLargestAnagramGroup(s_no_anagram2);
				
				if (result2.length != 0) {
					output+="TEST FAILED -- getLargestAnagramGroup([]) -- list with no anagrams\n";
					output += "Expected: []\n";
					output += "Received: " + Arrays.toString(result2) + "\n";
				}
				else {
					gradePoint[4]+=100*1.0/4.0;
				}
			} catch (Exception e) {
				output += "## getLargestAnagramGroup([]) threw " + e + "\n"; 
			}
				
			try {	
				SortedString[] anagram_middle2 = {
					new SortedString("praised"), 
					new SortedString("crates"), 
					new SortedString("Reacts"), 
					new SortedString("bats"), 
					new SortedString("tea")};
				String[] result3 = AnagramUtil.getLargestAnagramGroup(anagram_middle2);
				
				if (!ListsEqual(result3, anagram_middle_truth)) {
					output+="TEST FAILED -- getLargestAnagramGroup([]) -- 1 anagram in the middle\n";
					output += "Expected: " + Arrays.toString(anagram_middle_truth) + "\n";
					output += "Received: " + Arrays.toString(result3) + "\n";
				}
				else
					gradePoint[4]+=100*1.0/4.0;
			} catch (Exception e) {
				output += "## getLargestAnagramGroup([]) threw " + e + "\n"; 
			}

			try {
				
				String[] anagram_end2 = {"praised", "bats", "tea", "crates", "Reacts"};
				SortedString[] s_anagram_end2 = SortedString.toSortedString(anagram_end2);

				String[] result4 = AnagramUtil.getLargestAnagramGroup(s_anagram_end2);
				
				if (!ListsEqual(result4, anagram_end_truth)) {
					output+="TEST FAILED -- getLargestAnagramGroup([]) -- 1 anagram in the end\n";
					output += "Expected: " + Arrays.toString(anagram_end_truth) + "\n";
					output += "Received: " + Arrays.toString(result4) + "\n";

				}
				else {
					gradePoint[4]+=100*1.0/4.0;

				}

			} catch (Exception e) {
				output += "## getLargestAnagramGroup([]) threw " + e + "\n"; 
			}

			try { 
				
				/**
				 * the empty list test has also caused major issues for students; 
				 * this has been moved to be the final test.
				 */
				String[] empty_list2 = AnagramUtil.getLargestAnagramGroup(new SortedString[0]);
				
				if (empty_list2.length != 0) {
					output+="TEST FAILED -- getLargestAnagramGroup([]) -- empty list\n";
					output += "Expected: []\n";
					output += "Received: " + Arrays.toString(empty_list2) + "\n";
				}
				else
					gradePoint[4]+=100*1.0/4.0;
			} catch (Exception e) {
				output += "## getLargestAnagramGroup([]) threw " + e + "\n"; 
			}


			// end of tests ****************************************************



        } catch (Exception ex) {
            output += "program threw " + ex + "\n";
        }

        arrayTestPrint(gradePoint, givenPoint);
        System.out.println(output);
    }
    
    public static void arrayTestPrint(int[] arr, int[] givenpoint) {
        System.out.print("$$");
        int i = 0;
        for (int r : arr) {
            System.out.print(Math.round((r * givenpoint[i]) / 100.0));
            System.out.print("$$");
            i++;
        }
    }

    public static String[] readFile(String filename) {
        ArrayList<String> results = new ArrayList<String>();
        try {
            BufferedReader input = new BufferedReader(new FileReader(filename));
            while (input.ready()) {
                results.add(input.readLine());
            }
            input.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] retval = new String[1];
        return results.toArray(retval);
    }
}