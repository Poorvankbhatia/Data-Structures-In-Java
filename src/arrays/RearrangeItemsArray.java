/*


Rearrange an array such that ‘arr[j]’ becomes ‘i’ if ‘arr[i]’ is ‘j’
Given an array of size n where all elements are in range from 0 to n-1, 
change contents of arr[] so that arr[i] = j is changed to arr[j] = i.

Examples:

Example 1:
Input: arr[]  = {1, 3, 0, 2};
Output: arr[] = {2, 0, 3, 1};
Explanation for the above output.
Since arr[0] is 1, arr[1] is changed to 0
Since arr[1] is 3, arr[3] is changed to 1
Since arr[2] is 0, arr[0] is changed to 2
Since arr[3] is 2, arr[2] is changed to 3

Example 2:
Input: arr[]  = {2, 0, 1, 4, 5, 3};
Output: arr[] = {1, 2, 0, 5, 3, 4};

Example 3:
Input: arr[]  = {0, 1, 2, 3};
Output: arr[] = {0, 1, 2, 3};

Example 4:
Input: arr[]  = {3, 2, 1, 0};
Output: arr[] = {3, 2, 1, 0};


 */

package arrays;

import java.util.Arrays;

/**
 * Created by poorvank on 7/30/15.
 */
public class RearrangeItemsArray {

    public static void main(String[] args) {

        int[] array = new int[]{2, 0, 1, 4, 5, 3};
        int n = array.length;

        System.out.print("Array before - " + Arrays.toString(array));

        rearrangement(array, n);

        int[] temp = new int[n];

        // contents of arr[] so that arr[i] = j is changed to arr[j] = i.
        for (int i = 0; i < n; i++) {
            temp[array[i]] = i;
        }

        for (int i = 0; i < n; i++) {
            array[i] = temp[i];
        }

        System.out.print("\nArray after - " + Arrays.toString(array));

    }

    private static void rearrangement(int[] array, int n) {

        for (int i = 0; i < n; i++) {
            array[i]++;
        }


        for (int i = 0; i < n; i++) {
            if (array[i] > 0) {
                rearrangementUtil(array, i, n);
            }
        }

        for (int i = 0; i < n; i++) {
            array[i] = (-array[i]) - 1;
        }

    }

    private static void rearrangementUtil(int[] array, int i, int n) {

        int val = -(i + 1);

        i = array[i] - 1;

        while (array[i] > 0) {

            int new_i = array[i] - 1;

            array[i] = val;


            val = -(i + 1);
            i = new_i;

        }

    }

}


/*

// This function works only when output is a permutation
// with one cycle.
void rearrangeUtil(int arr[], int n)
{
    // 'val' is the value to be stored at 'arr[i]'
    int val = 0;   // The next value is determined
                  // using current index
    int i = arr[0];  // The next index is determined
                     // using current value

    // While all elements in cycle are not processed
    while (i != 0)
    {
        // Store value at index as it is going to be
        // used as next index
        int new_i = arr[i];

        // Update arr[]
        arr[i] = val;

        // Update value and index for next iteration
        val = i;
        i = new_i;
    }

    arr[0] = val;  // Update the value at arr[0]
}
The above function doesn’t work for inputs like {2, 0, 1, 4, 5, 3}; as there are two cycles. 
One cycle is (2, 0, 1) and 
other cycle is (4, 5, 3).
How to handle multiple cycles with the O(1) space constraint?
The idea is to process all cycles one by one. To check whether an element is processed or not,
 we change the value of processed items arr[i] as -arr[i]. Since 0 can not be made negative, 
 we first change all arr[i] to arr[i] + 1. In the end, we make all values positive and subtract 1 
 to get old values back.

 */