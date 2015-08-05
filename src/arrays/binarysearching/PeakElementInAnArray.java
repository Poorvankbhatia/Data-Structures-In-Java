/*

Given an array of integers. Find a peak element in it. An array element is peak if it is NOT 
smaller than its neighbors. For corner elements, we need to consider only one neighbor. For example, 
for input array {5, 10, 20, 15}, 20 is the only peak element. For input array {10, 20, 15, 2, 23, 90, 67}, 
there are two peak elements: 20 and 90. Note that we need to return any one peak element.

Following corner cases give better idea about the problem.
1) If input array is sorted in strictly increasing order, the last element is always a peak element.
 For example, 50 is peak element in {10, 20, 30, 40, 50}.
2) If input array is sorted in strictly decreasing order, the first element is always a peak element. 
100 is the peak element in {100, 80, 60, 50, 20}.
3) If all elements of input array are same, every element is a peak element.



 */

package arrays.binarysearching;

/**
 * Created by poorvank on 8/5/15.
 */
public class PeakElementInAnArray {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 3, 20, 4, 1, 0};

        System.out.println("Peak element is - " + findPeak(arr, 0, arr.length - 1, arr.length));

    }

    private static int findPeak(int[] array, int low, int high, int n) {

        if (low < high) {

            int mid = (low + high) / 2;

            if ((mid == 0 || array[mid] >= array[mid - 1]) && (array[mid] >= array[mid + 1] || mid == n - 1)) {
                return array[mid];
            } else if (array[mid] < array[mid + 1]) {
                return findPeak(array, mid + 1, high, n);
            }

            return findPeak(array, low, mid - 1, high);

        }

        return -1;

    }

}

/*

We can use Divide and Conquer to find a peak in O(Logn) time. 
The idea is Binary Search based, we compare middle element with its neighbors. 
If middle element is greater than both of its neighbors, then we return it. 
If the middle element is smaller than the its left neighbor, then there is always a peak in left half 
If the middle element is smaller than the its right neighbor,
then there is always a peak in right half (due to same reason as left half).

 */