/**
 * Maximum decimal value path in a binary matrix
 * Given binary square matrix [n*n]. Find maximum integer value in a path from top left to bottom right.
 * We compute integer value using bits of traversed path. We start at index [0,0] and end at index [n-1][n-1]. from index [i, j],
 * we can move [i, j+1] or [i+1, j].
 *
 * Examples:
 *
 * Input : mat[][] = {{1, 1, 0, 1},
 * {0, 1, 1, 0},
 * {1, 0, 0, 1},
 * {1, 0, 1, 1}}
 * Output : 111
 * Explanation :
 * Path :   (0,0) -> (0,1) -> (1,1) -> (1,2) ->
 * (2,2) -> (3,2) ->(4,4)
 * Decimal value : 1*(2^0) + 1*(2^1) + 1*(2^2) + 1*(2^3) +
 * 0*(2^4) + 1*(2^5) + 1*(2^6) = 111
 *
 */

package dyanamicprogramming.matrix;

/**
 * Created by poorvank on 25/12/16.
 */
public class MaximumDecimalValue {

    private int maxValue(int[][] matrix) {

        int rows = matrix.length;
        if(rows==0) {
            return 0;
        }
        int cols = matrix[0].length;
        int[][] valueMatrix = new int[rows][cols];
        int p = 0;

        valueMatrix[0][0] = matrix[0][0]*(powerTwo(0));

        for (int j=1;j<cols;j++) {
            valueMatrix[0][j] = valueMatrix[0][j-1] + (matrix[0][j]*powerTwo(++p));
        }
        p=0;

        for (int i=1;i<rows;i++) {
            valueMatrix[i][0] = valueMatrix[i-1][0] + (matrix[i][0]*powerTwo(++p));
        }

        for (int i=1;i<rows;i++) {
            for (int j=1;j<cols;j++) {
                valueMatrix[i][j] = Math.max(valueMatrix[i-1][j],valueMatrix[i][j-1]) + (matrix[i][j]*powerTwo((i+j)));
            }
        }

        return valueMatrix[rows-1][cols-1];

    }

    private int powerTwo(int p) {
        return (int) (Math.pow(2,p));
    }

    public static void main(String[] args) {

        int[][] matrix = new int[][]{{1, 1, 0, 1},
                                     {0, 1, 1, 0},
                                     {1, 0, 0, 1},
                                     {1, 0, 1, 1}};

        System.out.println(new MaximumDecimalValue().maxValue(matrix));

    }

}

/**
 * The above problem can be recursively defined as below:

 // p indicates power of 2, initially  p = i = j = 0
 MaxDecimalValue(mat, i, j, p)

 // If i or j is our of boundary
 If i >= n || j >= n
 return 0

 // Compute rest of matrix find maximum decimal value
 result  max(MaxDecimalValue(mat, i, j+1, p+1),
 MaxDecimalValue(mat, i+1, j, p+1))

 If mat[i][j] == 1
 return power(2, p) + result
 Else
 return result

 Here matrix [3][3]
 (2 2)
 /        \
 (1 2)          (2 1)
 /     \        /     \
 (0 2)   (1 1)   (1 1)   (2 1)
 /  \    /   \    /  \    / \
 .    .  .    .    .   .   .  .
 .    .  .    .    .   .   .  . and so no
 If we see recursion tree of above recursive solution, we can observe overlapping sub-problems.
 Since the problem has overlapping subproblems, we can solve it efficiently using Dynamic Programming.


 Time Complexity : O(n^2)
 Auxiliary space : O(n^2)


 */
