/*

Given an input string and a dictionary of words, find out if the input string can be segmented into a space-separated
sequence of dictionary words. See following examples for more details.

Consider the following dictionary
{ i, like, sam, sung, samsung, mobile, ice,
  cream, icecream, man, go, mango}

Input:  ilike
Output: Yes
The string can be segmented as "i like".

Input:  ilikesamsung
Output: Yes
The string can be segmented as "i like samsung" or "i like sam sung".

 */
package dyanamicprogramming;

/**
 * Created by poorvank on 7/30/15.
 */

class Dictionary {

    public static String[] dictionary = new String[]{"mobile", "samsung", "sam", "sung", "man", "mango",
            "icecream", "and", "go", "i", "like", "ice", "cream"};


    public static boolean contains(String word) {

        for (String dWord : dictionary) {
            if (dWord.equals(word)) {
                return true;
            }
        }

        return false;
    }

}

public class WordBreakProblem {

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {

        String str = "samsungandmango";

        System.out.println(wordBreak(str));


    }


    private static boolean wordBreak(String str) {

        int size = str.length();

        if (size == 0) {
            System.out.println(sb.toString());
            return true;
        }

        for (int i = 1; i <= size; i++) {

            String newStr = str.substring(i, size);

            // The parameter for dictionaryContains is str.substr(0, i)
            // str.substr(0, i) which is prefix (of input string) of
            // length 'i'. We first check whether current prefix is in
            // dictionary. Then we recursively check for remaining string
            // str.substr(i, size-i) which is suffix of length size-i

            if (Dictionary.contains(str.substring(0, i))) {
                sb.append(str.substring(0, i) + " ");
                if (wordBreak(newStr)) {
                    return true;
                } else {
                    sb = new StringBuilder("");
                }
            }

        }

        return false;

    }


}


/*

The idea is simple, we consider each prefix and search it in dictionary. 
If the prefix is present in dictionary, we recur for rest of the string (or suffix). 
If the recursive call for suffix returns true, we return true, otherwise we try next prefix. 
If we have tried all prefixes and none of them resulted in a solution, we return false.


Check its DP METHOD too

 */