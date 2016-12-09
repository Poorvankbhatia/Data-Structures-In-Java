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

Complexity is exponential, to be precise O(2^(n-2)). (2 power n-2)

In each call you are calling the recursive function with length 1,2....n-1(in worst case).
To do the work of length n you are recursively doing the work of all the strings of length n-1, n-2, ..... 1.
So T(n) is the time complexity of your current call, you are internally doing a work of sum of T(n-1),T(n-2)....T(1).

Mathematically :

  T(n) = T(n-1) + T(n-2) +.....T(1);
  T(1) = T(2) = 1
If you really don't know how to solve this, an easier way to solve the above recurrence is by just substitute values.

  T(1) = T(2) = 1
  T(3) = T(1) + T(2) = 1+1 =2; // 2^1
  T(4) = T(1)+ T(2) + T(3) = 1+1+2 =4; //2^2
  T(5) = T(1) + T(2) +T(3) +T(4) = 1+1+2+4 =8; //2^3
So if you substitute first few values, it will be clear that the Time complexity is 2^(n-2)

 */