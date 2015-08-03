package arrays.sortingalgorithms;

import java.util.Arrays;

/**
 * Created by poorvank on 8/1/15.
 */
public class MergeSort {

    public static void main(String[] args) {

        int[] array = new int[]{12, 11, 13, 5, 6, 7};

        System.out.println("Array before - " + Arrays.toString(array));
        mergeSort(array, 0, array.length - 1);

        System.out.println("Array after -  " + Arrays.toString(array));

    }

    private static void mergeSort(int[] array, int low, int high) {


        if (low < high) {

            int mid = (low + high) / 2;

            mergeSort(array, low, mid);
            mergeSort(array, mid + 1, high);
            int[] temp = merge(array, low, mid, mid + 1, high);
            copy(array, temp, low, high);

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

/*

O(nlogn)

Do See Iterative approach also

 */
