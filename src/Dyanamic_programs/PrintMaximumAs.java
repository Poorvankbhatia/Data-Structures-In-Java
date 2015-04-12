/*
   Revise and practice again
 */

package Dyanamic_programs;

import java.util.Arrays;

public class PrintMaximumAs {

    private static int[] array = new int[30];

    public static void main(String[] args) {

        System.out.println("for 7 keystrokes - " + findOptimal(7));

    }

    private static int findOptimal(int N) {

        if (N <= 6) {
            return N;
        }

        for (int n = 1; n <= 6; n++) {
            array[n - 1] = n;
        }

        for (int n = 7; n <= N; n++) {

            array[n - 1] = 0;

            for (int i = n - 3; i >= 1; i--) {

                //Dry run it to understand
                int currentNo = (n - i - 1) * array[i - 1];
                if (currentNo > array[n - 1]) {
                    array[n - 1] = currentNo;
                }
            }


        }

        System.out.println(Arrays.toString(array));

        return array[N - 1];

    }

}