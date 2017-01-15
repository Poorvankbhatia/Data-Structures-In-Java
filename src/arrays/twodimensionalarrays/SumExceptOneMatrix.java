/*

Find sum of all elements in a matrix except the elements in row and/or column of given cell?
Given a 2D matrix and a set of cell indexes e.g., an array of (i, j) where i indicates row and j column. 
For every given cell index (i, j), find sums of all matrix elements except the elements present in i’th row and/or 
j’th column.

Example:
mat[][]  = { {1, 1, 2}
             {3, 4, 6}
             {5, 3, 2} }
Array of Cell Indexes: {(0, 0), (1, 1), (0, 1)}
Output:  15, 10, 16

 */

package arrays.twodimensionalarrays;

/**
 * Created by poorvank on 7/30/15.
 */

public class SumExceptOneMatrix {


    private static class MatrixCell {

        int row;
        int col;

        MatrixCell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) {

        int[][] matrix = new int[][]{{1, 1, 2},
                                     {3, 4, 6},
                                     {5, 3, 2}};

        MatrixCell[] arr = new MatrixCell[]{new MatrixCell(0, 0), new MatrixCell(1, 1), new MatrixCell(0, 1)};

        int sum = 0;
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] r = new int[rows];
        int[] c = new int[cols];

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                sum  += matrix[i][j];
                r[i] += matrix[i][j];
                c[j] += matrix[i][j];

            }

        }

        for (MatrixCell cell : arr) {

            int ro = cell.row;
            int co = cell.col;

            System.out.println(sum - r[ro] - c[co] + matrix[ro][co]);

        }

    }

}

/*

An Efficient Solution can compute all sums in O(R x C + n) time. 
The idea is to precompute total sum, row and column sums before processing the given array of indexes. 
Below are details
1. Calculate sum of matrix, call it sum.
2. Calculate sum of individual rows and columns. (row[] and col[])
3. For a cell index (i, j), the desired sum will be “sum- row[i] – col[j] + arr[i][j]”

 */
