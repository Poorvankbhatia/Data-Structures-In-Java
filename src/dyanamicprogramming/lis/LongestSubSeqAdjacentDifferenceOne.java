/*

Longest subsequence such that difference between adjacents is one
Given an array of n size, the task is to find the longest subsequence such that difference between adjacents is one.

Examples:

Input :  arr[] = {10, 9, 4, 5, 4, 8, 6}
Output :  3
As longest subsequences with difference 1 are, "10, 9, 8",
"4, 5, 4" and "4, 5, 6"

Input :  arr[] = {1, 2, 3, 2, 3, 7, 2, 1}
Output :  7
As longest consecutive sequence is "1, 2, 3, 2, 3, 2, 1"

 */
package dyanamicprogramming.lis;

/**
 * Created by poorvank on 25/12/16.
 */
public class LongestSubSeqAdjacentDifferenceOne {


    private int longestSubSeq(int[] arr) {

        int n  = arr.length;
        int[] sequenceCount = new int[n];

        for (int i=0;i<n;i++) {
            sequenceCount[i] = 1;
        }

        for (int i=1;i<n;i++) {

            for (int j=0;j<i;j++) {

                if(Math.abs(arr[i]-arr[j])==1 && sequenceCount[i]<sequenceCount[j]+1) {
                    sequenceCount[i] = sequenceCount[j] + 1;
                }

            }
        }

        return sequenceCount[n-1];

    }


    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 2, 3, 7, 2, 1};

        System.out.println(new LongestSubSeqAdjacentDifferenceOne().longestSubSeq(arr));

    }




}


/*

A variation of LIS

Let arr[0..n-1] be the input array and
dp[i] be the length of the longest subsequence (with
differences one) ending at index i such that arr[i]
is the last element of the subsequence.

Then, dp[i] can be recursively written as:
dp[i] = 1 + max(dp[j]) where 0 < j < i and
       [arr[j] = arr[i] -1  or arr[j] = arr[i] + 1]
dp[i] = 1, if no such j exists.

To find the result for a given array, we need
to return max(dp[i]) where 0 < i < n.

 */