/*

Given an array arr[0 … n-1] containing n positive integers, a subsequence of arr[] is called Bitonic 
if it is first increasing, then decreasing. Write a function that takes an array as argument and returns 
the length of the longest bitonic subsequence.
A sequence, sorted in increasing order is considered Bitonic with the decreasing part as empty. Similarly, 
decreasing order sequence is considered Bitonic with the increasing part as empty.

Examples:

Input arr[] = {1, 11, 2, 10, 4, 5, 2, 1};
Output: 6 (A Longest Bitonic Subsequence of length 6 is 1, 2, 10, 4, 2, 1)

Input arr[] = {12, 11, 40, 5, 3, 1}
Output: 5 (A Longest Bitonic Subsequence of length 5 is 12, 11, 5, 3, 1)

Input arr[] = {80, 60, 30, 40, 20, 10}
Output: 5 (A Longest Bitonic Subsequence of length 5 is 80, 60, 30, 20, 10)

 */

package dp;

import java.util.Arrays;

/**
 * Created by poorvank on 5/22/15.
 */
public class LongestBitonicSubSeq {

    public static void main(String[] args) {

        int[] array = new int[]{12, 11, 40, 5, 3, 1};
        int[] array1 = new int[]{12, 4, 78, 90, 45, 23};
        bitonicLength(array);
        bitonicLength(array1);

    }

    private static void bitonicLength(int[] array) {

        int n = array.length;
        int[] lis = new int[array.length];

        for (int i = 0; i < lis.length; i++) {
            lis[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j] && lis[j] + 1 > lis[i]) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        int[] lds = new int[array.length];

        for (int i = 0; i < lds.length; i++) {
            lds[i] = 1;
        }


        for (int i = n - 2; i >= 0; i--)
            for (int j = n - 1; j > i; j--)
                if (array[i] > array[j] && lds[i] < lds[j] + 1)
                    lds[i] = lds[j] + 1;

        System.out.println(Arrays.toString(lis) + " " + Arrays.toString(lds));

        int max = 0;

        for (int i = 0; i < array.length; i++) {
            if (max < lds[i] + lis[i] - 1) {
                max = lds[i] + lis[i] - 1;
            }
        }

        System.out.println("Max = " + max);

    }

}


/*

This problem is a variation of standard Longest Increasing Subsequence (LIS) problem.
 Let the input array be arr[] of length n. We need to construct two arrays lis[] and lds[] using Dynamic Programming 
 solution of LIS problem. lis[i] stores the length of the Longest Increasing subsequence ending with arr[i].
  lds[i] stores the length of the longest Decreasing subsequence starting from arr[i]. Finally, we need to 
  return the max value of lis[i] + lds[i] – 1 where i is from 0 to n-1.

 */