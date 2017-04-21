package arrays;

import java.util.Arrays;

/**
 * Created by poorvank on 8/10/15.
 */
public class RearrangeEvenOdd {

    private static int count = 0;

    public static void main(String[] args) {

        int[] array = new int[]{13,10,21,20};

        int i = -1, n = array.length;


        for (int j = 0; j < n; j++) {

            if (array[j] % 2 == 0) {
                i++;
                if(array[i]!=array[j]) {
                    count++;
                    swap(i,j,array);
                }

            }

        }

        System.out.println(Arrays.toString(array));

        int odd = i + 1;
        int even = 0;


        while (even < odd && odd < n && /*because when even crosses odd since both incrementing*/array[even] % 2 == 0) {

            swap(odd, even, array);
            odd++;
            even += 2;

        }

        System.out.println(Arrays.toString(array) + " " + count);

    }


    private static void swap(int i, int j, int[] arr) {

        int a = arr[i];
        arr[i] = arr[j];
        arr[j] = a;

    }
}
