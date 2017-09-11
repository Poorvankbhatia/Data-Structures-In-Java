/*
Given an array of length N, where array[i] is the count of ways we can produce the amount i,
find out the set of denominations.

e.g

input: [1, 0, 1, 0, 1, 1, 1, 2, 1, 2, 2] -> output: [2, 5, 7]
 */
package dyanamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by poorvank.b on 11/09/17.
 */
public class SetOfDenominations {

    public Integer[] findSet(int[] nums) {

        List<Integer> list = new ArrayList<>();
        for (int i=0;i<nums.length;i++) {
            Integer[] arr = list.toArray(new Integer[list.size()]);
            if(count(arr,i)<nums[i]) {
                list.add(i);
            }
        }

        return list.toArray(new Integer[list.size()]);

    }


    private int count(Integer[] den,int amount) {
        int[][] dp = new int[den.length+1][amount+1];
        dp[0][0] = 1;

        for (int i=0;i<=den.length;i++) {
            dp[i][0] = 1;
        }

        for (int i=1;i<=den.length;i++) {
            for (int j=1;j<=amount;j++) {
                if(j>=den[i-1]) {
                    dp[i][j] = dp[i-1][j]+dp[i][j-den[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        return dp[den.length][amount];
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 0, 1, 0, 1, 1, 1, 2, 1, 2, 2};
        System.out.println(Arrays.toString(new SetOfDenominations().findSet(arr)));
    }

}

/*

Here is an O(n^3) Solution.

First, I will use the function count(coins[], int n) which gives me the number of ways you can write n with the coins in coins[].
You can calculate this using dynamic programming.

 */
