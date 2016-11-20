/*

Rearrange array in alternating positive & negative items with O(1) extra space
Given an array of positive and negative numbers, arrange them in an alternate fashion 
such that every positive number is followed by negative and vice-versa maintaining the order of appearance.
Number of positive and negative numbers need not be equal. If there are more positive numbers 
they appear at the end of the array. If there are more negative numbers, they too appear in the end of the array.

Example:

Input:  arr[] = {1, 2, 3, -4, -1, 4}
Output: arr[] = {-4, 1, -1, 2, 3, 4}

Input:  arr[] = {-5, -2, 5, 2, 4, 7, 1, 8, 0, -8}
output: arr[] = {-5, 5, -2, 2, -8, 4, 7, 1, 8, 0} 


 */

package arrays;

import java.util.Arrays;

/**
 * Created by poorvank on 8/22/15.
 */
public class RearrangePosNegOrder {

    public static void main(String[] args) {

        int[] arr = new int[]{-5, -2, 5, 2, 4, 7, 1, 8, 0, -8};
        rearrangeArray(arr);
        System.out.println(Arrays.toString(arr));

    }

    private static void rearrangeArray(int[] array) {

        int n = array.length;
        int outPlace = -1;

        for (int index = 0; index < n; index++) {

            if (outPlace >= 0) {

                if ((array[index] >= 0 && array[outPlace] < 0) || (array[index] < 0 && array[outPlace] >= 0)) {

                    rightRotateArray(array, index, outPlace);

                    //Because after rotation the next two elements will be of the same sign , so outofPlace +2 would be at wrong index
                    if (index - outPlace > 2) {
                        outPlace = outPlace + 2;
                    } else {
                        outPlace = -1;
                    }

                }

            } else {

                if ((array[index] < 0 && (index % 2) != 0) || (array[index] > 0 && (index % 2) == 0)) {
                    outPlace = index;
                }

            }

        }

    }

    private static void rightRotateArray(int[] arr, int index, int outPlace) {

        int temp = arr[index];
        for (int i = index; i > outPlace; i--) {
            arr[i] = arr[i - 1];
        }
        arr[outPlace] = temp;

    }

}

/*

The idea is to process array from left to right. While processing, find the first out of place 
element in the remaining unprocessed array. An element is out of place if it is negative and at odd index, 
or it is positive and at even index. Once we find an out of place element, we find the first element after
 it with opposite sign. We right rotate the subarray between these two elements (including these two).

 */