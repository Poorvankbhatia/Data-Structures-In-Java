/*

Minimum number of jumps to reach end
Given an array of integers where each element represents the max number of steps that can be made forward from 
that element. 
Write a function to return the minimum number of jumps to reach the end of the array (starting from the first element). 
If an element is 0, then cannot move through that element.

Example:

Input: arr[] = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9}
Output: 3 (1-> 3 -> 8 ->9)
First element is 1, so can only go to 3. Second element is 3, so can make at most 3 steps eg to 5 or 8 or 9.

 */

package dyanamicProgramming;

/**
 * Created by poorvank on 7/22/15.
 */
public class MinimumNumberOfJumps {

    public static void main(String[] args) {

        int[] array = new int[]{1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};

        System.out.println("Minimum number of steps to reach the end - " + minimumJumps(array));

    }

    private static int minimumJumps(int[] arr) {

        int n = arr.length;

        int[] jumps = new int[n];

        jumps[0] = 0;

        for (int i = 1; i < n; i++) {

            jumps[i] = Integer.MAX_VALUE;

            for (int j = 0; j < i; j++) {

                if (i <= j + arr[j] && arr[j] != Integer.MAX_VALUE) {

                    // Either the previous value
                    // or the current value 
                    jumps[i] = Math.min(jumps[i], jumps[j] + 1);

                }

            }

        }

        return jumps[n - 1];
    }

}

/*

In this method, we build a jumps[] array from left to right such that jumps[i] 
indicates the minimum number of jumps needed to reach arr[i] from arr[0]. Finally, we return jumps[n-1].

 */