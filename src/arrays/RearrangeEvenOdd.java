package arrays;

import java.util.Arrays;

/**
 * Created by poorvank on 8/10/15.
 */
public class RearrangeEvenOdd {

    public static void main(String[] args) {

        int[] array = new int[]{4, 2, 5, 7, 3, 6, 9};

        int i = -1, n = array.length;

        for (int j = 0; j < n; j++) {

            if (array[j] % 2 == 0) {
                i++;
                swap(i, j, array);
            }

        }

        int odd = i + 1;
        int even = 0;


        while (even < odd && odd < n && /*because when even crosses odd since both incrementing*/array[even] % 2 == 0) {

            swap(odd, even, array);
            odd++;
            even += 2;

        }

        System.out.println(Arrays.toString(array));

    }


    private static void swap(int i, int j, int[] arr) {

        int a = arr[i];
        arr[i] = arr[j];
        arr[j] = a;

    }
}
