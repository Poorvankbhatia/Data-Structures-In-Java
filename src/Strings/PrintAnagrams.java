/*

Given a sequence of words, print all anagrams together 
Given an array of words, print all anagrams together. 
For example, if the given array is {“cat”, “dog”, “tac”, “god”, “act”}, 
then output may be “cat tac act dog god”.

TRIE SOLUTION ALSO available
 */

package strings;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank on 3/30/15.
 */
public class PrintAnagrams {

    public static void main(String[] args) {

        String[] array = new String[]{"art", "tar", "top", "pot", "neat", "rat", "tape", "nate", "peat", "random"};
        String[] sortedArray = sortAlphabetsInWord(array);
        HashMap<String, String> anagramMap = new HashMap<>();
        int i = 0;
        for (String s : sortedArray) {

            if (anagramMap.containsKey(s)) {
                anagramMap.put(s, anagramMap.get(s) + "," + array[i]);
            } else {
                anagramMap.put(sortedArray[i], array[i]);
            }
            i++;
        }

        System.out.println("Following are the pairs of anagrams : ");
        for (Map.Entry entry : anagramMap.entrySet()) {
            System.out.println(entry.getValue());
        }

    }

    private static String[] sortAlphabetsInWord(String[] array) {

        String[] sortedArray = new String[array.length];
        int i = 0;
        for (String s : array) {
            char[] arrayElement = s.toCharArray();
            Arrays.sort(arrayElement);
            sortedArray[i] = new String(arrayElement);
            i++;
        }

        return sortedArray;

    }

}


/*

You can ignore time complexity for sorting words which are bounded small and assume hashing function will gives in O(1). 

The total time complexity will be O(n) for n words.



 */