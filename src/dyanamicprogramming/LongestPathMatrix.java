/*

Find the longest path in a matrix with given constraints
Given a n*n matrix where numbers all numbers are distinct and are distributed from range 1 to square(n),
find the maximum length path (starting from any cell) such that all cells along the path are increasing order with a difference of 1.

We can move in 4 directions from a given cell (i, j), i.e., we can move to (i+1, j) or (i, j+1) or (i-1, j) or (i, j-1)
with the condition that the adjacen

Example:

Input:  mat[][] = {{1, 2, 9}
                   {5, 3, 8}
                   {4, 6, 7}}
Output: 4
The longest path is 6-7-8-9.

 */
package dyanamicprogramming;

/**
 * Created by poorvank on 23/10/16.
 */
public class LongestPathMatrix {

    public static int longestPath(int[][] arr) {

        int m = arr.length;
        int[][] dp = new int[m][m];

        for (int i=0;i<m;i++) {
            for (int j=0;j<m;j++) {
                dp[i][j] = -1;
            }
        }

        int result = Integer.MIN_VALUE;
        for (int i=0;i<m;i++) {
            for (int j=0;j<m;j++) {
                if(dp[i][j]==-1) {
                    result = Math.max(result,longestPathUtil(arr,dp,i,j,m));
                }
            }
        }

        return result;

    }

    private static int longestPathUtil(int[][] arr,int[][] dp,int i,int j,int n) {

        if (i<0 || i>=n || j<0 || j>=n)
            return 0;

        if(dp[i][j]!=-1) {
            return dp[i][j];
        }

        if(i<n-1 && dp[i][j]+1==dp[i+1][j]) {
            dp[i][j] = 1 + longestPathUtil(arr,dp,i+1,j,n);
            return dp[i][j];
        }

        if(i>0 && dp[i][j]+1==dp[i-1][j]) {
            dp[i][j] = 1 + longestPathUtil(arr,dp,i-1,j,n);
            return dp[i][j];
        }

        if(j<n-1 && dp[i][j]+1==dp[i][j+1]) {
            dp[i][j] = 1 + longestPathUtil(arr,dp,i,j+1,n);
            return dp[i][j];
        }

        if(j>0 && dp[i][j]+1==dp[i][j-1]) {
            dp[i][j] = 1 + longestPathUtil(arr,dp,i,j-1,n);
            return dp[i][j];
        }

        dp[i][j] = 1;

        return dp[i][j];

    }

}

/*

The idea is simple, we calculate longest path beginning with every cell. Once we have computed longest for all cells,
we return maximum of all longest paths. One important observation in this approach is many overlapping subproblems.
Therefore this problem can be optimally solved using Dynamic Programming.


 */