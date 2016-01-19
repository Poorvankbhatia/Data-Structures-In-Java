/*

Print all possible paths from top left to bottom right of a mXn matrix
The problem is to print all the possible paths from top left to bottom right of
a mXn matrix with the constraints that from each cell you can either move only to right or down.

 */

package arrays.twodimensionalarrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 5/28/15.
 */
public class PrintAllPaths {

    public static void main(String[] args) {

        int[][] matrix = new int[][]{{1, 2, 3},
                                     {4, 5, 6},
                                     {7, 8, 9}};
        printPaths(matrix, matrix.length, matrix[0].length, 0, 0, new ArrayList<Integer>());

    }

    private static void printPaths(int[][] matrix, int row, int col, int i, int j, List<Integer> path) {

        //Reached last row move left
        if (i == row - 1) {

            for (int k = j; k < col; k++) {
                path.add(matrix[i][k]);
            }

            System.out.println(path.toString());
            return;
        }

        //Reached last column move right
        if (j == col - 1) {

            for (int k = i; k < row; k++) {
                path.add(matrix[k][j]);
            }

            System.out.println(path.toString());
            return;

        }

        path.add(matrix[i][j]);

        printPaths(matrix, row, col, i + 1, j, new ArrayList<Integer>(path));

        printPaths(matrix, row, col, i, j + 1, new ArrayList<Integer>(path));

        //To Print diagonal  paths
        //printPaths(matrix,row,col,i+1,j+1,new ArrayList<Integer>(path));

    }

}

/*

The algorithm is a simple recursive algorithm, 
from each cell first print all paths by going down and then print all paths by going right. 
Do this recursively for each cell encountered.


T(m, n) = T(m, n-1) + T(n, m-1)	// for two recursive calls 
(at any cell we are reducing the matrix into (m-1) x n and m x (n-1) matrix in respective recursive calls)
T(m, N) = m + (m+n)	// for j == n - 1, (m+n) for printing the matrix
T(M, n) = n + (m+n)	// for i == m - 1, (m+n) for printing the matrix

T(M, N) = T(M, N-1) + T(M-1, N)
= N-1 + (M+N-1) + M-1 + (M-1+N)
= O(M+N)

 */
