/*

Given a N x N matrix, determine the maximum K such that K x K is a submatrix with all equal elements i.e.,
all the elements in this submatrix must be same.

Constraints:
1 <= N <= 1000
0 <= Ai , j <= 109

Examples:


Input : a[][] = {{2, 3, 3},
                 {2, 3, 3},
                 {2, 2, 2}}
Output : 2
Explanation: A 2x2 matrix is formed from index
A0,1 to A1,2

Input : a[][]  = {{9, 9, 9, 8},
                  {9, 9, 9, 6},
                  {9, 9, 9, 3},
                  {2, 2, 2, 2}
Output : 3
Explanation : A 3x3 matrix is formed from index
A0,0 to A2,2

 */

package dyanamicprogramming.matrix;

/**
 * Created by poorvank.b on 05/04/17.
 */
public class MaxSquareEqualElements {

    private static int maxSubSquare(int[][] matrix) {

        int rows = matrix.length;

        if(rows==0) {
            return 0;
        }

        int cols = matrix[0].length;

        int[][] result = new int[rows][cols];

        int val=0;

        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if(i==0 || j==0) {
                    result[i][j] = 1;
                    continue;
                }
                if(matrix[i][j]==matrix[i-1][j] && matrix[i][j]==matrix[i-1][j-1] && matrix[i][j]==matrix[i][j-1]) {
                    result[i][j] = Math.min(Math.min(result[i][j-1],result[i-1][j]),result[i-1][j-1]) + 1;
                } else {
                    result[i][j] = 1;
                }

                val = Math.max(val,result[i][j]);
            }
        }

        return val;

    }

    public static void main(String[] args) {
        int[][] a = {{2, 2, 3, 3, 4, 4},
                     {5, 5, 7, 7, 7, 4},
                     {1, 2, 7, 7, 7, 4},
                     {4, 4, 7, 7, 7, 4},
                     {5, 5, 5, 1, 2, 7},
                     {8, 7, 9, 4, 4, 4}};
        System.out.print(maxSubSquare(a));
    }

}

/*

For each cell (i, j), we store the largest value of K such that K x K is a submatrix with all equal elements and position of (i, j) being the bottom-right most element.

And DPi,j depends upon {DPi-1, j, DPi, j-1, DPi-1, j-1}

If Ai, j is equal to {Ai-1, j, Ai, j-1, Ai-1, j-1},
   all the three values:
    DPi, j = min(DPi-1, j, DPi, j-1, DPi-1, j-1) + 1
Else
    DPi, j = 1  // Matrix Size 1

The answer would be the maximum of all DPi, j's

 */
