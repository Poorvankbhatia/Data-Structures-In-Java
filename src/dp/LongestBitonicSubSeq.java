package dp;

import java.util.Arrays;

/**
 * Created by poorvank on 5/22/15.
 */
public class LongestBitonicSubSeq {

    public static void main(String[] args) {

        int[] array = new int[]{12, 11, 40, 5, 3, 1};
        int[] array1 = new int[]{12, 4, 78, 90, 45, 23};
        bitonicLength(array);
        bitonicLength(array1);

    }

    private static void bitonicLength(int[] array) {

        int n = array.length;
        int[] lis = new int[array.length];

        for (int i = 0; i < lis.length; i++) {
            lis[i] = 1;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (array[i] > array[j] && lis[j] + 1 > lis[i]) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        int[] lds = new int[array.length];

        for (int i = 0; i < lds.length; i++) {
            lds[i] = 1;
        }


        for (int i = n - 2; i >= 0; i--)
            for (int j = n - 1; j > i; j--)
                if (array[i] > array[j] && lds[i] < lds[j] + 1)
                    lds[i] = lds[j] + 1;

        System.out.println(Arrays.toString(lis) + " " + Arrays.toString(lds));

        int max = 0;

        for (int i = 0; i < array.length; i++) {
            if (max < lds[i] + lis[i] - 1) {
                max = lds[i] + lis[i] - 1;
            }
        }

        System.out.println("Max = " + max);

    }

}
