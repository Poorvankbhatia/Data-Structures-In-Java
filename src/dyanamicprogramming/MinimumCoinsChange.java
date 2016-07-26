/*

Find minimum number of coins that make a given value
Given a value V, if we want to make change for V cents, 
and we have infinite supply of each of C = { C1, C2, .. , Cm} valued coins, 
what is the minimum number of coins to make the change?

Examples:

Input: coins[] = {25, 10, 5}, V = 30
Output: Minimum 2 coins required
We can use one coin of 25 cents and one of 5 cents 

Input: coins[] = {9, 6, 5, 1}, V = 11
Output: Minimum 2 coins required
We can use one coin of 6 cents and 1 coin of 5 cents

 */

package dyanamicprogramming;

/**
 * Created by poorvank on 8/18/15.
 */
public class MinimumCoinsChange {

    public static void main(String[] args) {

        int[] arr = new int[]{9, 6, 5, 1};
        int value = 11;

        int n = arr.length;

        System.out.println(recursiveMethod(arr, value, n));
        System.out.println(dpMethod(arr, value, n));

    }


    /**
     * The minimum number of coins for a value V can be computed using below recursive formula.
     * <p/>
     * If V == 0, then 0 coins required.
     * If V > 0
     * minCoin(coins[0..m-1], V) = min {1 + minCoins(V-coin[i])}
     * where i varies from 0 to m-1
     * and coin[i] <= V
     *
     * @param value
     * @param n
     * @return
     */
    private static int recursiveMethod(int[] arr, int value, int n) {

        if (value == 0) {
            return 0;
        }

        int result = Integer.MAX_VALUE;


        for (int i = 0; i < n; i++) {

            if (arr[i] <= value) {

                int subResult = recursiveMethod(arr, value - arr[i], n);

                if (subResult != Integer.MAX_VALUE && subResult + 1 < result) {
                    result = subResult + 1;
                }

            }

        }

        return result;
    }

    /**
     * when we start from V = 11, we can reach 6 by subtracting one 5 times and by subtracting 5 one times.
     * * So the subproblem for 6 is called twice.
     * Since same suproblems are called again, this problem has Overlapping Subprolems property.
     * So the min coins problem has both properties (see this and this) of a dynamic programming problem
     *
     * @param arr
     * @param value
     * @param n
     * @return
     */
    private static int dpMethod(int[] arr, int value, int n) {

        int[] table = new int[value + 1];

        // table[i] will be storing the minimum number of coins
        // required for i value.  So table[V] will have result
        table[0] = 0;
        for (int i = 1; i <= value; i++) {
            table[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= value; i++) {

            for (int j = 0; j < n; j++) {

                if (arr[j] <= i) {

                    int subResult = table[i - arr[j]];
                    if (subResult != Integer.MAX_VALUE && subResult + 1 < table[i]) {
                        table[i] = subResult + 1;
                    }

                }

            }

        }

        return table[value];

    }

}

/*

Time complexity of the above solution is O(mV).

 */
