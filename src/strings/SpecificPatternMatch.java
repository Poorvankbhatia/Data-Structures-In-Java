/*

Find all strings that match specific pattern in a dictionary
Given a dictionary of words, find all strings that matches the given pattern where every character in the pattern is
uniquely mapped to a character in the dictionary.

Examples:

Input:
dict = ["abb", "abc", "xyz", "xyy"];
pattern = "foo"
Output: [xyy abb]
Explanation:
xyy and abb have same character at index 1 and 2 like the pattern

Input:
dict = ["abb", "abc", "xyz", "xyy"];
pat = "mno"
Output: [abc xyz]
Explanation:
abc and xyz have all distinct characters, similar to the pattern

Input:
dict = ["abb", "abc", "xyz", "xyy"];
pattern = "aba"
Output: []
Explanation:
Pattern has same character at index 0 and 2.
No word in dictionary follows the pattern.

Input:
dict = ["abab", "aba", "xyz", "xyx"];
pattern = "aba"
Output: [aba xyx]
Explanation:
aba and xyx have same character at index 0 and 2 like the pattern



 */

package strings;

import java.util.HashMap;

/**
 * Created by poorvank on 13/08/16.
 */
public class SpecificPatternMatch {

    private static String encodeString(String str) {

        StringBuilder sb = new StringBuilder();
        HashMap<Character,Integer> map = new HashMap<>();

        //For every character assign its value in the hash == first occurring position
        for (int i=0;i<str.length();i++) {
            char c = str.charAt(i);
            if(!map.containsKey(c)) {
                map.put(c,i+1);
            }

            sb.append(map.get(c));
        }

        return sb.toString();

    }

    public static void printMatchingStrings(String[] dictionary,String pattern) {

        String encodedPattern = encodeString(pattern);
        int count = 0;
        for (String dictionaryWord : dictionary) {

            if (dictionaryWord.length() == pattern.length() && encodeString(dictionaryWord).equals(encodedPattern)) {
                System.out.print(dictionaryWord + " ");
            }
            count++;

        }

        if(count==0) {
            System.out.println("No word same as pattern found");
        }

    }

    public static void main(String[] args) {

        String[] dictionary = new String[]{"abb", "abc", "xyz", "xyy"};
        String pattern = "mno";


        printMatchingStrings(dictionary,pattern);

    }

}




/*

The idea is to encode the pattern in such a way that any word from the dictionary that matches the pattern will have same hash as that of the
pattern after encoding. We iterate through all words in dictionary one by one and print the words that have same hash as that of the pattern.

 */