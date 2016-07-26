package dyanamicprogramming;

import java.util.Scanner;

/**
 * Created by poorvank on 5/14/15.
 */
public class FibonacciNumbers {

    private static int[] lookupMemoization;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number ");
        int n = scanner.nextInt();
        lookupMemoization = new int[n + 1];

        System.out.println("Fibonacci of number - " + n + " using memoization - "
                + memoizationFib(n) + " tabulation " + tabulationFib(n) + " recursive " + recursiveFib(n));

    }

    private static int recursiveFib(int n) {

        if (n <= 1) {
            return n;
        }
        return recursiveFib(n - 1) + recursiveFib(n - 2);

    }

    private static int memoizationFib(int n) {

        if (lookupMemoization[n] == 0) {
            if (n <= 1) {
                lookupMemoization[n] = n;
            } else {
                lookupMemoization[n] = memoizationFib(n - 1) + memoizationFib(n - 2);
            }
        }

        return lookupMemoization[n];

    }

    private static int tabulationFib(int n) {

        int[] table = new int[n + 1];
        table[0] = 0;
        table[1] = 1;

        for (int i = 2; i <= n; i++) {
            table[i] = table[i - 1] + table[i - 2];
        }


        return table[n];
    }

}
