/*

Print all possible strings that can be made by placing spaces
Given a string you need to print all possible strings that can be made by placing spaces (zero or one) in between them.

Input:  str[] = "ABC"
Output: ABC
        AB C
        A BC
        A B C

 */

package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 7/31/15.
 */
public class PossibleStringWithSpaces {


    public static void main(String[] args) {

        String s = "ABC";

        List<Character> list = new ArrayList<>();
        printPermutations(s, list, 0, s.length() - 1);

    }

    private static void printPermutations(String s, List<Character> list, int i, int n) {

        if (i == n) {
            System.out.println(list.toString());
            return;
        }

        list.add(s.charAt(i));
        printPermutations(s, new ArrayList<Character>(list), i + 1, n);

        list.add(' ');
        printPermutations(s, new ArrayList<Character>(list), i + 1, n);


    }


}


/*

The idea is to use recursion and create a buffer that one by one contains all output strings having spaces.
We keep updating buffer in every recursive call.

 */