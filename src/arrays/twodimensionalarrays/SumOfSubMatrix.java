/*

Given a matrix of size m x n. Here m and n are very large, assume like 1 lakh.
You are given a series of submatrix inside this matrix. Find the sum of all elements inside each submatrix.
Submatrix positions are given interms of its: top_left_cell and bottom_right_cell.

 */

package arrays.twodimensionalarrays;

/**
 * Created by poorvank on 8/27/15.
 */
public class SumOfSubMatrix {

    public static void main(String[] args) {

        int[][] arr = new int[][]{{1,   2,  3,  4,  5,  6},
                                  {7,   8,  9, 10, 11, 12},
                                  {13, 14, 15, 16, 17, 18},
                                  {19, 20, 21, 22, 23, 24},
                                  {25, 26, 27, 28, 29, 30}};

        int maxCol = 2;
        int maxRow = 1;
        int minRow = 0;
        int minCol = 1;


        int[][] sumMatrix = new int[arr.length][arr[0].length];

        printMatrix(arr);

        System.out.println();

        fillSumMatrix(sumMatrix, arr);

        int sum = 0;
        if (minRow - 1 >= 0 && minCol - 1 >= 0) {
            sum = sumMatrix[maxRow][maxCol] - sumMatrix[minRow - 1][maxCol] - sumMatrix[maxRow][minCol - 1] + sumMatrix[minRow - 1][minCol - 1];
        } else if (minRow - 1 < 0) {
            sum = sumMatrix[maxRow][maxCol] - sumMatrix[maxRow][minCol-1];
        } else if (minCol - 1 < 0) {
            sum = sumMatrix[maxRow][maxCol] - sumMatrix[minRow-1][maxCol];
        } else {
            sum = sumMatrix[maxRow][maxCol];
        }
        System.out.println("\nSum is - " + sum);

    }

    private static void fillSumMatrix(int[][] sumMatrix, int[][] arr) {

        int r = arr.length;
        int c = arr[0].length;

        sumMatrix[0][0] = arr[0][0];
        for (int i = 1; i < c; i++) {
            sumMatrix[0][i] = arr[0][i] + sumMatrix[0][i - 1];
        }

        for (int i = 1; i < r; i++) {
            sumMatrix[i][0] = arr[i][0] + sumMatrix[i - 1][0];
        }

        for (int i = 1; i < r; i++) {

            for (int j = 1; j < c; j++) {

                sumMatrix[i][j] = arr[i][j] + sumMatrix[i - 1][j] + sumMatrix[i][j - 1] - sumMatrix[i - 1][j - 1];

            }

        }

        printMatrix(sumMatrix);

    }

    private static void printMatrix(int[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++)
                System.out.print(arr[i][j] + " ");
            System.out.println();
        }

    }


}

/*

Your "preprocessing" step is to build a new matrix of the same size, where each entry is the sum of the 
sub-matrix to the upper-left of that entry. Any arbitrary sub-matrix sum can be calculated by looking up 
and mixing only 4 entries in the SAT.

EDIT: Here's an example.

For the initial matrix

0 1 4
2 3 2
1 2 7
The SAT is

0 1 5
2 6 12
3 9 22
The SAT is obtained using S(x,y) = a(x,y) + S(x-1,y) + S(x,y-1) - S(x-1,y-1),

where S is the SAT matrix and a is the initial matrix .

If you want the sum of the lower-right 2x2 sub-matrix, the answer would be 22 + 0 - 3 - 5 = 14. 
Which is obviously the same as 3 + 2 + 2 + 7. Regardless of the size of the matrix, 
the sum of a sub matrix can be found in 4 lookups and 3 arithmetic ops. Building the SAT is O(n), 
similarly requiring only 4 lookups and 3 math ops per cell.

 */
