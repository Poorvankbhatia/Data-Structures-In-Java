/*

Maximum difference between two elements such that larger element appears after the smaller number
Given an array arr[] of integers, find out the difference between any two elements such that larger 
element appears after the smaller number in arr[].

Examples: If array is [2, 3, 10, 6, 4, 8, 1] then returned value should be 8 (Diff between 10 and 2). 
If array is [ 7, 9, 5, 6, 3, 2 ] then returned value should be 2 (Diff between 7 and 9)

 */

package arrays;

/**
 * Created by poorvank on 6/18/15.
 */
public class MaxDifference {

    public static void main(String[] args) {

        int[] arr = new int[]{2, 3, 10, 6, 4, 8, 1};

        int minElement = arr[0];
        int maxDifference = arr[1] - arr[0];
        
        /*
        
        In this method, instead of taking difference of the picked element with every other element,
        we take the difference with the minimum element found so far. So we need to keep track of 2 things:
        1) Maximum difference found so far (max_diff).
        2) Minimum number visited so far (min_element).
        
         */
        for (int i = 1; i < arr.length; i++) {

            if (maxDifference < arr[i] - minElement) {
                maxDifference = arr[i] - minElement;
            }
            if (minElement > arr[i]) {
                minElement = arr[i];
            }

        }

        System.out.println(maxDifference);

    }

}
