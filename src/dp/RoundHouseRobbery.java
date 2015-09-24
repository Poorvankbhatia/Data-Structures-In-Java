/*

all houses at this
place are arranged in a circle. That means the first house is the neighbor of the last
one. Meanwhile, the security system for these houses remain the same as for those in
the previous street.
Given a list of non-negative integers representing the amount of money of each
house, determine the maximum amount of money you can rob tonight without alerting
the police.

 */

package dp;

/**
 * Created by poorvank on 8/25/15.
 */
public class RoundHouseRobbery {

    public static void main(String[] args) {

        int[] arr = new int[]{4, 5, 17, 3, 12, 29, 31};
        int n = arr.length;
        //Include 1st house
        int[] dp = new int[arr.length - 1];

        dp[0] = arr[0];
        dp[1] = Math.max(dp[0], arr[1]);

        for (int i = 2; i < n - 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + arr[i]);
        }

        //Include Last house
        int[] dr = new int[arr.length];

        dr[0] = 0;
        dr[1] = arr[1];

        for (int i = 2; i < n; i++) {
            dr[i] = Math.max(dr[i - 1], dr[i - 2] + arr[i]);
        }

        System.out.println(Math.max(dp[n - 2], dr[n - 1]));

    }

}

/*

There are two cases here 1) 1st element is
included and last is not included 2) 1st is not included and last is included. Therefore,
we can use the similar dynamic programming approach to scan the array twice and
get the larger value.

 */