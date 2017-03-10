/*

Given an array of positive and negative numbers, find if there is a subarray with 0 sum.

Examples:

Input: {4, 2, -3, 1, 6}
Output: true 
There is a subarray with zero sum from index 1 to 3.

Input: {4, 2, 0, 1, 6}
Output: true 
There is a subarray with zero sum from index 2 to 2.

Input: {-3, 2, 3, 1, 6}
Output: false
There is no subarray with zero sum.

 */

package arrays;

import java.util.HashMap;

/**
 * Created by poorvank on 7/5/15.
 */
public class LargestSubArrayZeroSum {

    public static void main(String[] args) {

        int[] arr = new int[]{4, 2, -3, 1, 6};
        if (!checkSum(arr)) {
            System.out.println("No sub array with 0 sum found");
        }

    }

    private static boolean checkSum(int[] arr) {

        HashMap<Integer, Integer> map = new HashMap<>();
        int startSumIndex = 0;
        int sum = 0;

        for (int i = 0; i < arr.length; i++) {

            sum += arr[i];

            if (arr[i] == 0) {
                System.out.println(i + " - " + i);
                return true;
            } else if (sum == 0) {
                System.out.println(0 + " - " + i);
                return true;
            } else if (map.get(sum) != null) {
                startSumIndex = map.get(sum);
                System.out.println(startSumIndex + 1 + " - " + i);
                return true;
            }

            map.put(sum, i);

        }

        return true;

    }

}


/*

We can also use hashing. The idea is to iterate through the array and for every element arr[i],
 calculate sum of elements form 0 to i (this can simply be done as sum += arr[i]).
  If the current sum has been seen before, then there is a zero sum array.
   Hashing is used to store the sum values, so that we can quickly store sum and find
    out whether the current sum is seen before or not.

 */