/*

Count all possible paths from top left to bottom right of a mXn matrix
The problem is to count all the possible paths from top left to bottom right of a mXn matrix 
with the constraints that from each cell you can either move only to right or down

 */
package dyanamicprogramming;

/**
 * Created by poorvank on 5/28/15.
 */
public class CountAllPaths {

    public static void main(String[] args) {

        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}};
        System.out.println(recursive(matrix.length-1, matrix[0].length-1));
        DPMethod(matrix, matrix.length, matrix[0].length);
    }

    private static int recursive( int row, int col) {

        if (row == 0 || col == 0) {
            return 1;
        }

        return recursive(row - 1, col) + recursive(row, col - 1);

    }

    private static void DPMethod(int[][] matrix, int row, int col) {

        int[][] count = new int[row][col];

        // Count of paths to reach any cell in first column is 1
        for (int i = 0; i < row; i++)
            count[i][0] = 1;

        // Count of paths to reach any cell in first column is 1
        for (int j = 0; j < col; j++)
            count[0][j] = 1;

        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                count[i][j] = count[i - 1][j] + count[i][j - 1];
            }
        }

        System.out.println(count[row - 1][col - 1]);

    }


}
