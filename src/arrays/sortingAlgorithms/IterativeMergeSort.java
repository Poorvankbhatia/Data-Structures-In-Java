package arrays.sortingalgorithms;

import java.util.Arrays;

/**
 * Created by poorvank on 8/1/15.
 */
public class IterativeMergeSort {

    public static void main(String[] args) {

        int[] array = new int[]{12, 11, 13, 5, 6, 7};

        System.out.println("Array before - " + Arrays.toString(array));
        mergeSort(array, array.length);

        System.out.println("Array after -  " + Arrays.toString(array));

    }

    private static void mergeSort(int[] array, int n) {

        int startingLeft;


        // Merge subarrays in bottom up manner.  First merge subarrays of
        // size 1 to create sorted subarrays of size 2, then merge subarrays
        // of size 2 to create sorted subarrays of size 4, and so on.
        for (int currentSize = 1; currentSize < n; currentSize = (2 * currentSize)) {

            for (startingLeft = 0; startingLeft < n - 1; startingLeft += (2 * currentSize)) {

                int mid = startingLeft + currentSize - 1;

                int rightEnd = Math.min(startingLeft + (2 * currentSize) - 1, n - 1);

                int[] temp = merge(array, startingLeft, mid, mid + 1, rightEnd);
                copy(array, temp, startingLeft, rightEnd);


            }


        }

    }

    private static int[] merge(int[] array, int low1, int high1, int low2, int high2) {

        int[] temp = new int[array.length];
        int i = low1;
        int j = low2;
        int k = low1;

        while (i <= high1 && j <= high2) {

            if (array[i] < array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }

        }


        while (i <= high1) {
            temp[k++] = array[i++];
        }

        while (j <= high2) {
            temp[k++] = array[j++];
        }

        return temp;

    }

    private static void copy(int[] array, int[] temp, int low, int high) {

        for (int i = low; i <= high; i++) {
            array[i] = temp[i];
        }

    }


}
