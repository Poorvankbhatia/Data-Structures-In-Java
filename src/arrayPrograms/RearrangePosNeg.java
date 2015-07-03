/*

Rearrange positive and negative numbers in O(n) time and O(1) extra space
An array contains both positive and negative numbers in random order. 
Rearrange the array elements so that positive and negative numbers are placed alternatively. 
Number of positive and negative numbers need not be equal. If there are more positive numbers they appear 
at the end of the array. If there are more negative numbers, they too appear in the end of the array.

For example, if the input array is [-1, 2, -3, 4, 5, 6, -7, 8, 9], then the output should be [9, -7, 8, -3, 5, -1, 2, 4, 6]

 */

package arrayPrograms;

import java.util.Arrays;

/**
 * Created by poorvank on 6/22/15.
 */
public class RearrangePosNeg {

    public static void main(String[] args) {

        int[] arr = new int[]{-1, 2, -3, 4, 5, 6, -7, 8, 9};

        int n = arr.length;

        int i = -1;

        for (int j = 0; j < n; j++) {

            if (arr[j] < 0) {

                i++;
                swap(i, j, arr);

            }

        }

        int pos = i + 1;
        int neg = 0;

        while (pos < n && neg < pos && arr[neg] < 0) {
            swap(pos, neg, arr);
            pos++;
            neg += 2;
        }

        System.out.println(Arrays.toString(arr));

    }

    private static void swap(int i, int j, int[] arr) {

        int a = arr[i];
        arr[i] = arr[j];
        arr[j] = a;

    }

}

/*

The solution is to first separate positive and negative numbers using partition process of QuickSort. 
In the partition process, consider 0 as value of pivot element so that all negative numbers are 
placed before positive numbers. Once negative and positive numbers are separated, we start from the first 
negative number and first positive number, and swap every alternate negative number with next positive number.


 */