/*

A Boolean Array Puzzle
Input: A array arr[] of two elements having value 0 and 1

Output: Make both elements 0.

Specifications: Following are the specifications to follow.
1) It is guaranteed that one element is 0 but we do not know its position.
2) We can’t say about another element it can be 0 or 1.
3) We can only complement array elements, no other operation like and, or, multi, division, …. etc.
4) We can’t use if, else and loop constructs.
5) Obviously, we can’t directly assign 0 to array elements.

 */

package arrays;

import java.util.Arrays;

/**
 * Created by poorvank on 6/12/15.
 */
public class BooleanArray {

    public static void main(String[] args) {

        int[] a = new int[]{0, 1};

        a[a[1]] = a[a[0]];

        System.out.println(Arrays.toString(a));

    }

}
