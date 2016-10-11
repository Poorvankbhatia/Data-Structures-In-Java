/*

Find a Fixed Point in a given array
Given an array of n distinct integers sorted in ascending order, 
write a function that returns a Fixed Point in the array, 
if there is any Fixed Point present in array, else returns -1. 
Fixed Point in an array is an index i such that arr[i] is equal to i. Note that integers in array can be negative.

Examples:

  Input: arr[] = {-10, -5, 0, 3, 7}
  Output: 3  // arr[3] == 3 

  Input: arr[] = {0, 2, 5, 8, 17}
  Output: 0  // arr[0] == 0 


  Input: arr[] = {-10, -5, 3, 4, 7, 9}
  Output: -1  // No Fixed Point


 */

package arrays.binarysearching;

/**
 * Created by poorvank on 7/30/15.
 */
public class FixedPoint {

    public static void main(String[] args) {

        int[] array = new int[]{-10, -1, 0, 3, 10, 11, 30, 50, 100};

        System.out.print("Fixed point is - " + fixedPoint(array, 0, array.length - 1));

    }

    private static int fixedPoint(int[] array, int low, int high) {

        if (low <= high) {

            int mid = low + (high - low) / 2;

            if (mid == array[mid]) {
                return mid;
            } else if (mid > array[mid]) {
                return fixedPoint(array, mid + 1, high);
            } else {
                return fixedPoint(array, low, mid - 1);
            }
        }

        return -1;

    }

}


/*

First check whether middle element is Fixed Point or not. If it is, then return it; 
otherwise check whether index of middle element is greater than value at the index. 
If index is greater, then Fixed Point(s) lies on the right side of the middle point 
(obviously only if there is a Fixed Point). Else the Fixed Point(s) lies on left side.

 */