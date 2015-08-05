/*

Given an array arr[], find the maximum j – i such that arr[j] > arr[i]
Given an array arr[], find the maximum j – i such that arr[j] > arr[i].

Examples:

  Input: {34, 8, 10, 3, 2, 80, 30, 33, 1}
  Output: 6  (j = 7, i = 1)

  Input: {9, 2, 3, 4, 5, 6, 7, 8, 18, 0}
  Output: 8 ( j = 8, i = 0)

  Input:  {1, 2, 3, 4, 5, 6}
  Output: 5  (j = 5, i = 0)

  Input:  {6, 5, 4, 3, 2, 1}
  Output: -1


 */

package arrays;

import java.util.Arrays;

/**
 * Created by poorvank on 8/5/15.
 */
public class MaximumDiffOfIndices {

    public static void main(String[] args) {

        int[] array = new int[]{9, 2, 3, 4, 5, 6, 7, 8, 18, 0};

        printDifferentIndex(array, array.length);

    }

    private static void printDifferentIndex(int[] array, int n) {

        int[] leftMin = new int[n];
        int[] rightMax = new int[n];

        leftMin[0] = array[0];

        for (int i = 1; i < n; i++) {
            leftMin[i] = Math.min(array[i], leftMin[i - 1]);
        }

        rightMax[n - 1] = array[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(array[i], rightMax[i + 1]);
        }

        int i = 0, j = 0, maxDifference = 1;
        int start = 0, end = 0;

        System.out.println("leftMin - " + Arrays.toString(leftMin));
        System.out.println("rightMax - " + Arrays.toString(rightMax));

        while (i < n && j < n) {
            
            if (leftMin[i] < rightMax[j]) {
                maxDifference = Math.max(maxDifference, j - i);
                start = i;
                end = j;
                j++;
            } else {
                i++;
            }

        }


        System.out.println("Maximum Difference = " + maxDifference + " from -> " + start + " to " + end);

    }

}

/*

To solve this problem, we need to get two optimum indexes of arr[]: left index i and right index j. 
For an element arr[i], we do not need to consider arr[i] for left index if there is an element smaller than arr[i] on 
left side of arr[i]. 
Similarly, if there is a greater element on right side of arr[j] then we do not need to consider this j for right index. 
So we construct two auxiliary arrays LMin[] and RMax[] such that LMin[i] holds the smallest element on left side of 
arr[i] including arr[i], and RMax[j] holds the greatest element on right side of arr[j] including arr[j]. 
After constructing these two auxiliary arrays, we traverse both of these arrays from left to right. 
While traversing LMin[] and RMa[] if we see that LMin[i] is greater than RMax[j], 
then we must move ahead in LMin[] (or do i++) because all elements on left of LMin[i] are greater than or equal 
to LMin[i]. Otherwise we must move ahead in RMax[j] to look for a greater j – i value.

 */