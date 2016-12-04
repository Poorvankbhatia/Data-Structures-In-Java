/*

Number of paths with exactly k coins
Given a matrix where every cell has some number of coins. Count number of ways to reach
bottom right from top left with exactly k coins. We can move to (i+1, j) and (i, j+1) from a cell (i, j).

Example:

Input:  k = 12
        mat[][] = { {1, 2, 3},
                    {4, 6, 5},
                    {3, 2, 1}
                  };
Output:  2
There are two paths with 12 coins
1 -> 2 -> 6 -> 2 -> 1
1 -> 2 -> 3 -> 5 -> 1

 */
package dyanamicprogramming.matrix;

/**
 * Created by poorvank.b on 23/10/16.
 */
public class PathWithKCoins {

    public static int pathCount(int[][] arr,int sum) {

        int m = arr.length;
        int n = arr[0].length;

        int[][][] dp = new int[m][n][1000];

        for (int i=0;i<m;i++) {
            for (int j=0;j<n;j++) {
                for (int k=0;k<1000;k++) {
                        dp[i][j][k] = -1;
                }
            }
        }

        return pathUtil(m-1,n-1,arr,sum,dp);

    }

    public static int pathUtilRecursive(int m,int n,int[][] arr,int sum) {

        if(m<0 || n<0 || sum<0) {
            return 0;
        }

        if(m==0 && n==0 && sum==arr[0][0]) {
            return 1;
        }

        sum = sum-arr[m][n];

        return pathUtilRecursive(m-1,n,arr,sum)+pathUtilRecursive(m,n-1,arr,sum);


    }

    public static int pathUtil(int m,int n,int[][] arr,int sum,int[][][] dp) {

        if(m<0 || n<0 || sum<0) {
            return 0;
        }

        if(m==0 && n==0 && sum==arr[m][n]) {
            return 1;
        }

        if(dp[m][n][sum]!=-1) {
            return dp[m][n][sum];
        }


        dp[m][n][sum] = pathUtil(m-1,n,arr,sum-arr[m][n],dp) + pathUtil(m,n-1,arr,sum-arr[m][n],dp);

        return dp[m][n][sum];

    }


    public static void main(String[] args) {

        int[][] arr = new int[][]{
                {1, 2, 3},
                {4, 6, 5},
                {3, 2, 1}
        };

        System.out.println(pathCount(arr,12));

    }

}


/*

The above problem can be recursively defined as below:

pathCount(m, n, k):   Number of paths to reach mat[m][n] from mat[0][0]
                      with exactly k coins

If (m == 0 and n == 0)
   return 1 if mat[0][0] == k else return 0
Else:
    pathCount(m, n, k) = pathCount(m-1, n, k - mat[m][n]) +
                         pathCount(m, n-1, k - mat[m][n])


 Time complexity of this solution is O(m*n*k).

 */