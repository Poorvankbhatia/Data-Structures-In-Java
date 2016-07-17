package arrays.sorting;

import java.util.Arrays;

/**
 * Created by poorvank on 8/1/15.
 */
public class Merge {

    public static void main(String[] args) {

        int[] array = new int[]{12, 11, 13, 5, 6, 7};

        System.out.println("Array before - " + Arrays.toString(array));
        mergeSort(array, 0, array.length - 1);

        System.out.println("Array after -  " + Arrays.toString(array));

    }

}

/*

O(nlogn)

Do See Iterative approach also

 */
