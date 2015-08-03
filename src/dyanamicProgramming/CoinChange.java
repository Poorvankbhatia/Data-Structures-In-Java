package dyanamicprogramming;

/**
 * Created by poorvank on 5/21/15.
 */
public class CoinChange {

    public static void main(String[] args) {

        int[] coins = new int[]{1, 3, 4};
        int n = 5;


    }

    private static void method1(int[] array, int total) {

        int[] table = new int[total + 1];
        table[0] = 1;//One way to fill 0

        for (int i = 0; i < array.length; i++) {

            for (int j = 1; j <= total; j++) { // Loop to fill table Array

                if (j >= array[i]) {

                    table[j] = table[j] + table[j - array[i]];

                }

            }

        }

    }

}
