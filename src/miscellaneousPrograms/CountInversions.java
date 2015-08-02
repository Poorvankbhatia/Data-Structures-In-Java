/*

Count Inversions in an array
Inversion Count for an array indicates – how far (or close) the array is from being sorted. 
If array is already sorted then inversion count is 0. If array is sorted in reverse order that inversion 
count is the maximum. 
Formally speaking, two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j

Example:
The sequence 2, 4, 1, 3, 5 has three inversions (2, 1), (4, 1), (4, 3).

 */

package miscellaneousPrograms;

import java.util.Arrays;

/**
 * Created by poorvank on 8/1/15.
 */
public class CountInversions {

    public static void main(String[] args) {

        int[] array = new int[]{2, 4, 1, 3, 5};

        System.out.println("Number of inversions required - " + merge(array, 0, array.length - 1));

        System.out.println(Arrays.toString(array));


    }

    private static int merge(int[] array, int low, int high) {

        int invCount = 0;

        if (low < high) {

            int mid = (low + high) / 2;

            invCount += merge(array, low, mid);
            invCount += merge(array, mid + 1, high);

            invCount += _merge(array, low, mid, mid + 1, high);

        }

        return invCount;


    }


    private static int _merge(int[] array, int low1, int high1, int low2, int high2) {

        int[] temp = new int[array.length];
        int i = low1;
        int j = low2;
        int k = low1;
        int invCount = 0;


        while ((i <= high1) && (j <= high2)) {


            if (array[i] < array[j]) {
                temp[k++] = array[i++];
            } else {
                invCount += (j - i);
                temp[k++] = array[j++];
            }

        }


        while (i <= high1) {
            temp[k++] = array[i++];
        }

        while (j <= high2) {
            temp[k++] = array[j++];
        }

        for (i = low1; i <= high2; i++) {
            array[i] = temp[i];
        }


        return invCount;

    }

}


/*

Suppose we know the number of inversions in the left half and right half of the array 
(let be inv1 and inv2), what kinds of inversions are not accounted for in Inv1 + Inv2? 
The answer is – the inversions we have to count during the merge step. Therefore, to get number of inversions, 
we need to add number of inversions in left subarray, right subarray and merge().

In merge process, let i is used for indexing left sub-array and j for right sub-array.
 At any step in merge(), if a[i] is greater than a[j], then there are (mid – i) inversions. 
 because left and right subarrays are sorted, so all the remaining elements in left-subarray 
 (a[i+1], a[i+2] … a[mid]) will be greater than a[j]


http://www.geeksforgeeks.org/counting-inversions/

 */