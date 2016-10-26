/*

Find minimum sum such that one of every three consecutive elements is taken
Given an array of n non-negative numbers, the task is to find the minimum sum of elements (picked from the array)
such that at least one element is picked out of every 3 consecutive elements in the array.

Examples:

Input : arr[] = {1, 2, 3}
Output : 1

Input : arr[] = {1, 2, 3, 6, 7, 1}
Output : 4
We pick 3 and 1  (3 + 1 = 4)
Note that there are following subarrays
of three consecutive elements
{1, 2, 3}, {2, 3, 6}, {3, 6, 7} and {6, 7, 1}
We have picked one element from every subarray.

Input : arr[] = {1, 2, 3, 6, 7, 1, 8, 6, 2,
                 7, 7, 1}
Output : 7
The result is obtained as sum of 3 + 1 + 2 + 1

 */

package dyanamicprogramming;

/**
 * Created by poorvank on 23/10/16.
 */
public class FindMinSum {


    public static int minSum(int[] arr) {

        //Sum when arr[i] is chosen as the element
        int n =arr.length;
        int[] sumArr = new int[n];



        sumArr[0] = arr[0];
        sumArr[1] = arr[1];
        sumArr[2] = arr[2];

        for (int i=3;i<n;i++) {
            sumArr[i] = arr[i] + Math.min(sumArr[i-1],Math.min(sumArr[i-2],sumArr[i-3]));
        }

        return Math.min(sumArr[n-1],Math.min(sumArr[n-2],sumArr[n-3]));

    }

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 6, 7, 1, 8, 6, 2, 7, 7, 1};

        System.out.print(minSum(arr));

    }

}

/*

Let sum(i) be the minimum possible sum when arr[i] is part of a solution sum (not necessarily result) and is last picked element.
Then our result is minimum of sum(n-1), sum(n-2) and sum(n-3) [We must pick at least one of the last three elements].
We can recursively compute sum(i) as sum of arr[i] and minimum(sum(i-1), sum(i-2), sum(i-3)). Since there are overlapping subproblems
in recursive structure of problem, we can use Dynamic Programming to solve this problem.

 */