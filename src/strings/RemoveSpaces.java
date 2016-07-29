/*

Remove spaces from a given string
Given a string, remove all spaces from the string and return it.

Input:  "g  eeks   for ge  eeks  "
Output: "geeksforgeeks"

Expected time complexity is O(n) and only one traversal of string.

 */

package strings;

/**
 * Created by poorvank on 25/07/16.
 */
public class RemoveSpaces {


    public static void main(String[] args) {

        String input = "g  eeks   for ge  eeks  ";
        char[] array = input.toCharArray();

        int j = 0;
        for (int i = 0; i < array.length; i++) {

            if (array[i] != ' ') {
                array[j] = array[i];
                j++;
            }

        }

        input = new String(array).substring(0, j);

        System.out.println(input);

    }

}

/*

A Better Solution can solve it in O(n) time. The idea is to keep track of count of non-space character seen so far.

1) Initialize 'count' = 0 (Count of non-space character seen so far)
2) Iterate through all characters of given string, do following
     a) If current character is non-space, then put this character
        at index 'count' and increment 'count'
3) Finally, put '\0' at index 'count'

 */
