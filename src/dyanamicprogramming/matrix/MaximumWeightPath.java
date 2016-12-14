/*

Maximum weight path ending at any element of last row in a matrix
Given a matrix of integers where every element represents weight of the cell.
Find the path having the maximum weight in matrix [N X N]. Path Traversal Rules are:

It should begin from top left element.
The path can end at any element of last row.
We can move to following two cells from a cell (i, j).
Down Move : (i+1, j)
Diagonal Move : (i+1, j+1)
Examples:

Input : N = 5
        mat[5][5] = {{ 4, 2 ,3 ,4 ,1 },
                     { 2 , 9 ,1 ,10 ,5 },
                     {15, 1 ,3 , 0 ,20 },
                     {16 ,92, 41, 44 ,1},
                     {8, 142, 6, 4, 8} };
Output : 255
Path with max weight : 4 + 2 +15 + 92 + 142 = 255

 */
package dyanamicprogramming.matrix;

/**
 * Created by poorvank on 04/12/16.
 */
public class MaximumWeightPath {

    private int maxWeight(int[][] mat) {

        int rows = mat.length;
        int cols = mat[0].length;

        int[][] dp = new int[rows][cols];

        dp[0][0] = mat[0][0];
        for (int i=1;i<rows;i++) {
            dp[i][0] = mat[i][0] + dp[i-1][0];
        }

        for (int i=1;i<rows;i++) {
            for (int j=1;j<i+1 && j<rows;j++) {
                dp[i][j] = Math.max(dp[i-1][j-1],dp[i-1][j]) + mat[i][j];
            }
        }

        int result = 0;

        for (int j=0;j<cols;j++) {
            if(dp[rows-1][j]>result) {
                result = dp[rows-1][j];
            }
        }

        return result;

        //return maxWeightUtilRecursive(0,0,mat,rows,cols);

    }

    private int maxWeightUtilRecursive(int i, int j, int[][] mat, int rows, int cols) {

        if(i>=rows || j>=cols) {
            return Integer.MAX_VALUE;
        }

        if(i==rows-1){
            return mat[i][j];
        }

        return mat[i][j] + Math.max(maxWeightUtilRecursive(i+1,j,mat,rows,cols), maxWeightUtilRecursive(i+1,j+1,mat,rows,cols));

    }

    public static void main(String[] args) {
        int[][] mat = new int[][] {{ 4, 2 ,3 ,4  ,1 },
                                   { 2, 9 ,1 ,10 ,5 },
                                   {15, 1 ,3 , 0 ,20 },
                                   {16,92 ,41, 44,1},
                                   {8 ,142,6 , 4 , 8} };

        System.out.print(new MaximumWeightPath().maxWeight(mat));
    }

}

/*

The above problem can be recursively defined.

Let maxCost(i, j) be the cost maximum cost to
reach mat[i][j].  Since end point can be any point
in last row, we finally return maximum of all values
maxCost(N-1, j) where j varies from 0 to N-1.

If i == 0 and j == 0
   maxCost(0, 0) = mat[0][0]

// We can traverse through first column only by
// down move
Else if j = 0
  maxCost(i, 0) = maxCost(i-1, 0) + mat[i][0]

// In other cases, a cell mat[i][j] can be reached
// through previous two cells ma[i-1][j] and
// mat[i-1][j-1]
Else
  maxCost(i, j) = mat[i][j] + max(maxCost(i-1, j),
                                maxCost(i-1, j-1)),

Time complexity : O(N*N)


 */