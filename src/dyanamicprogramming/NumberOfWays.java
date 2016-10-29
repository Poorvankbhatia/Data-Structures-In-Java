/*

Count number of ways to cover a distance
Given a distance ‘dist, count total number of ways to cover the distance with 1, 2 and 3 steps.

Examples:

Input:  n = 3
Output: 4
Below are the four ways
 1 step + 1 step + 1 step
 1 step + 2 step
 2 step + 1 step
 3 step

Input:  n = 4
Output: 7

 */
package dyanamicprogramming;

/**
 * Created by poorvank.b on 23/10/16.
 */
public class NumberOfWays {

    public int count(int steps) {

        if(steps==0 || steps==1) {
            return steps;
        }
        int n = steps;

        int[] res = new int[n+1];
        res[0] = 0;
        res[1] = 1;
        res[2] = 2;
        res[3] = 4;

        for (int i=4;i<=n;i++) {
            res[i] = res[i-1]+res[i-2]+res[i-3];
        }

        return res[n];

    }

}
