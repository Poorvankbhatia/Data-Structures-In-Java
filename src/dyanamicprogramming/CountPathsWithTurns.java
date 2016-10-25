/*

Count number of paths with at-most k turns
Given a “m x n” matrix, count number of paths to reach bottom right from top left with maximum k turns allowed.

What is a turn? A movement is considered turn, if we were moving along row and now move along column.
OR we were moving along column and now move along row.

There are two possible scenarios when a turn can occur
at point (i, j):

Turns Right: (i-1, j)  ->  (i, j)  ->  (i, j+1)
                      Down        Right

Turns Down:  (i, j-1)  ->  (i, j)  ->  (i+1, j)
                     Right        Down
Examples:

Input:  m = 3, n = 3, k = 2
Output: 4
See below diagram for four paths with
maximum 2 turns.

Input:  m = 3, n = 3, k = 1
Output: 2

 */
package dyanamicprogramming;

/**
 * Created by poorvank on 23/10/16.
 */
public class CountPathsWithTurns {

    private static int MAX = 100;
    private static int[][][][] dp = new int[MAX][MAX][MAX][2];

    private static int countPaths(int m,int n,int count) {

        m=m-1;n=n-1;

        for (int i=0;i<MAX;i++) {
            for (int j=0;j<MAX;j++) {
                for (int k=0;k<MAX;k++) {
                    for (int h=0;h<2;h++) {
                        dp[i][j][k][h] = -1;
                    }
                }
            }
        }

        return  countPathsUtil(m,n-1,count,1) // Moving along column
                +countPathsUtil(m-1,n,count,0); // Moving along row

    }

    private static int countPathsUtil(int i,int j,int count,int d) {

        // If invalid row or column indexes
        if (i < 0 || j < 0)
            return 0;

        // If current cell is top left itself
        if (i == 0 && j == 0)
            return 1;

        // If 0 turns left
        if (count == 0)
        {
            // If direction is row, then we can reach here
            // only if direction is row and row is 0.
            if (d == 0 && i == 0) return 1;

            // If direction is column, then we can reach here
            // only if direction is column and column is 0.
            if (d == 1 && j == 0) return 1;

            return 0;
        }

        if(dp[i][j][count][d]!=-1) {
            return dp[i][j][count][d];
        }

        // If current direction is row, then count paths for two cases
        // 1) We reach here through previous row.
        // 2) We reach here through previous column, so number of
        //    turns k reduce by 1.
        if(d==0) {
            return (countPathsUtil(i-1,j,count-1,1)+ countPathsUtil(i,j-1,count-1,0));
        } else {
            return (countPathsUtil(i-1,j,count,1)+ countPathsUtil(i,j-1,count-1,0));
        }

    }


    public static void main(String[] args) {

        int m = 3, n = 3, k = 2;

        System.out.print(countPaths(m,n,k));

    }




}


/*

Time complexity of above solution is O(m*n*k)

countPaths(i, j, k): Count of paths to reach (i,j) from (0, 0)
countPathsDir(i, j, k, 0): Count of paths if we reach (i, j)
                           along row.
countPathsDir(i, j, k, 1): Count of paths if we reach (i, j)
                           along column.
The fourth parameter in countPathsDir() indicates direction.

Value of countPaths() can be written as:
countPaths(i, j, k) = countPathsDir(i, j, k, 0) +
                      countPathsDir(i, j, k, 1)

And value of  countPathsDir() can be recursively defined as:

// Base cases

// If current direction is along row
If (d == 0)
  // Count paths for two cases
  // 1) We reach here through previous row.
  // 2) We reach here through previous column, so number of
  //    turns k reduce by 1.
  countPathsDir(i, j, k, d) = countPathsUtil(i, j-1, k, d) +
                              countPathsUtil(i-1, j, k-1, !d);

// If current direction is along column
Else
  // Similar to above
  countPathsDir(i, j, k, d) =  countPathsUtil(i-1, j, k, d) +
                               countPathsUtil(i, j-1, k-1, !d);
We can solve this problem in Polynomial Time using Dynamic Programming. The idea is to use a 4 dimensional
table dp[m][n][k][d] where m is number of rows, n is number of columns, k is number of allowed turns and d is direction.

 */