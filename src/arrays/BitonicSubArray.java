/*

Maximum Length Bitonic Subarray
Given an array A[0 … n-1] containing n positive integers, a subarray A[i … j] 
is bitonic if there is a k with i <= k <= j such that A[i] <= A[i + 1] ... <= A[k] >= A[k + 1] >= .. A[j – 1] > = A[j].
 Write a function that takes an array as argument and returns the length of the maximum length bitonic subarray.
Expected time complexity of the solution is O(n)

Simple Examples
1) A[] = {12, 4, 78, 90, 45, 23}, the maximum length bitonic subarray is {4, 78, 90, 45, 23} which is of length 5.

2) A[] = {20, 4, 1, 2, 3, 4, 2, 10}, the maximum length bitonic subarray is {1, 2, 3, 4, 2} which is of length 5.

Extreme Examples
1) A[] = {10}, the single element is bitnoic, so output is 1.

2) A[] = {10, 20, 30, 40}, the complete array itself is bitonic, so output is 4.

3) A[] = {40, 30, 20, 10}, the complete array itself is bitonic, so output is 4.



 */

package arrays;

import java.util.Arrays;

/**
 * Created by poorvank on 5/23/15.
 */
public class BitonicSubArray {

    public static void main(String[] args) {

        int[] array = new int[]{12, 4, 78, 90, 45, 23};
        subArray(array);
    }

    public static void subArray(int[] array) {

        int[] increasing = new int[array.length];
        int[] decreasing = new int[array.length];

        for (int i = 0; i < array.length; i++) {
            increasing[i] = 1;
            decreasing[i] = 1;
        }

        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[i - 1]) {
                increasing[i] = increasing[i - 1] + 1;
            }
        }

        for (int i = array.length - 2; i >= 0; i--) {

            if (array[i] > array[i + 1]) {
                decreasing[i] = decreasing[i + 1] + 1;
            }

        }

        int maxLength = 0;

        System.out.println(Arrays.toString(increasing) + " " + Arrays.toString(decreasing));

        for (int i = 0; i < array.length; i++) {

            if (maxLength < increasing[i] + decreasing[i] - 1) {
                maxLength = increasing[i] + decreasing[i] - 1;
            }
        }

        System.out.println(maxLength);

    }

}

/*

Let us consider the array {12, 4, 78, 90, 45, 23} to understand the soultion.
1) Construct an auxiliary array inc[] from left to right such that inc[i] contains length of 
the nondecreaing subarray ending at arr[i].
For for A[] = {12, 4, 78, 90, 45, 23}, inc[] is {1, 1, 2, 3, 1, 1}

2) Construct another array dec[] from right to left such that dec[i] contains length of nonincreasing 
subarray starting at arr[i].
For A[] = {12, 4, 78, 90, 45, 23}, dec[] is {2, 1, 1, 3, 2, 1}.

3) Once we have the inc[] and dec[] arrays, all we need to do is find the maximum value of (inc[i] + dec[i] – 1).
For {12, 4, 78, 90, 45, 23}, the max value of (inc[i] + dec[i] – 1) is 5 for i = 3.

 */


