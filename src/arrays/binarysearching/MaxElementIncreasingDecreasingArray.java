/*

Find the maximum element in an array which is first increasing and then decreasing
Given an array of integers which is initially increasing and then decreasing, find the maximum value in the array.

Input: arr[] = {8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1}
Output: 500

Input: arr[] = {1, 3, 50, 10, 9, 7, 6}
Output: 50

Corner case (No decreasing part)
Input: arr[] = {10, 20, 30, 40, 50}
Output: 50

Corner case (No increasing part)
Input: arr[] = {120, 100, 80, 20, 0}
Output: 120

 */

package arrays.binarysearching;

/**
 * Created by poorvank on 7/29/15.
 */
public class MaxElementIncreasingDecreasingArray {

    public static void main(String[] args) {

        int[] array = new int[]{1, 30, 40, 50, 60, 70, 23, 20};

        System.out.print(array[maxIndex(array, 0, array.length - 1)]);

    }

    private static int maxIndex(int[] array, int low, int high) {

        if (low == high)
            return low;

        if (high == (low + 1)) {
            return (array[high] > array[low]) ? high : low;
        }

        if (low < high) {

            int mid = low + (high - low) / 2;

            if (array[mid] > array[mid + 1] && array[mid] > array[mid - 1]) {
                return mid;
            } else if (array[mid] > array[mid + 1] && array[mid] < array[mid - 1]) {
                return maxIndex(array, low, mid - 1);
            } else {
                return maxIndex(array, mid + 1, high);
            }

        }

        return -1;

    }

}

/*

We can modify the standard Binary Search algorithm for the given type of arrays.
i) If the mid element is greater than both of its adjacent elements, then mid is the maximum.
ii) If mid element is greater than its next element and smaller than the previous element then maximum 
lies on left side of mid. Example array: {3, 50, 10, 9, 7, 6}
iii) If mid element is smaller than its next element and greater than the previous element then maximum 
lies on right side of mid. Example array: {2, 4, 6, 8, 10, 3, 1}

 */
