/*

Find k closest elements to a given value
Given a sorted array arr[] and a value X, find the k closest elements to X in arr[]. 
Examples:

Input: K = 4, X = 35
       arr[] = {12, 16, 22, 30, 35, 39, 42, 
               45, 48, 50, 53, 55, 56}
Output: 30 39 42 45
Note that if the element is present in array, then it should not be in output, only the other closest elements are required.

In the following solutions, it is assumed that all elements of array are distinct.

 */

package arrays;

/**
 * Created by poorvank on 7/15/15.
 */
public class FindKClosestElements {

    public static void main(String[] args) {

        int[] arr = new int[]{12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56};

        int x = 35, n = arr.length, k = 4;

        int l = findCrossoverPoint(arr, x, 0, n - 1);
        int r = l + 1, count = 0;


        if (arr[l] == x) {
            l--;
        }

        while (l >= 0 && r < n && count < k) {

            if (x - arr[l] < arr[r] - x) {
                System.out.print(arr[l--] + " ");
            } else {
                System.out.print(arr[r++] + " ");
            }
            count++;

        }

        // If there are no more elements on right side, then
        // print left elements
        while (count < k && l >= 0) {
            System.out.print(arr[l--] + " ");
        }

        // If there are no more elements on left side, then
        // print right elements
        while (count < k && r < n) {
            System.out.print(arr[l--] + " ");
        }

    }

    private static int findCrossoverPoint(int[] arr, int x, int low, int high) {

        if (x >= arr[high]) {
            return high;
        }
        if (x <= arr[low]) {
            return low;
        }

        int mid = (high + low) / 2;
        
        /* If x is same as middle element, then return mid */
        if (arr[mid] <= x && arr[mid + 1] > x) {
            return mid;
        }
         /* If x is greater than arr[mid], then either arr[mid + 1]
          is ceiling of x or ceiling lies in arr[mid+1...high] */
        else if (arr[mid] < x) {
            return findCrossoverPoint(arr, x, mid + 1, high);
        }

        return findCrossoverPoint(arr, x, low, mid - 1);

    }

}

/*

An Optimized Solution is to find k elements in O(Logn + k) time. The idea is to use Binary Search to find the crossover point. 
Once we find index of crossover point, we can print k closest elements in O(k) time.

 */