/*

Print two-dimensional array in spiral order

 */

package arrays.twodimensionalarrays;

/**
 * Created by poorvank on 7/25/15.
 */
public class SpiralPrintingMatrix {

    public static void main(String[] args) {

        int[][] matrix = new int[][]{{1, 2, 3, 4, 5}, 
                                     {18, 19, 20, 21, 6}, 
                                     {17, 28, 29, 22, 7}, 
                                     {16, 27, 30, 23, 8}, 
                                     {15, 26, 25, 24, 9}, 
                                     {14, 13, 12, 11, 10}};
        int row = matrix.length;
        int col = matrix[0].length;

        printMatrix(matrix, row, col);

        System.out.println();

        spiralPrinting(matrix, row, col);


    }

    private static void printMatrix(int[][] matrix, int row, int col) {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] > 9) {
                    System.out.print(matrix[i][j] + " ");
                } else {
                    System.out.print(matrix[i][j] + "  ");
                }
            }
            System.out.println();
        }

    }


    private static void spiralPrinting(int[][] matrix, int row, int col) {

        printTopRight(matrix, 0, 0, row - 1, col - 1);

    }

    private static void printTopRight(int[][] matrix, int x1, int y1, int x2, int y2) {

        for (int i = x1; i <= y2; i++) {
            System.out.print(matrix[x1][i] + " ");
        }

        for (int i = y1 + 1; i <= x2; i++) {
            System.out.print(matrix[i][y2] + " ");
        }

        if (x2 - x1 != 0) {
            printBottomLeft(matrix, x1 + 1, y1, x2, y2 - 1);
        }

    }

    private static void printBottomLeft(int[][] matrix, int x1, int y1, int x2, int y2) {

        for (int j = y2; j >= x1 - 1; j--) {
            System.out.print(matrix[x2][j] + " ");
        }

        for (int j = x2 - 1; j >= x1; j--) {
            System.out.print(matrix[j][y1] + " ");
        }

        if (x2 - x1 != 0) {
            printTopRight(matrix, x1, y1 + 1, x2 - 1, y2);
        }

    }

}


/*

The idea is to treat the matrix as a series of layers, top-right layers and bottom-left layers.
To print the matrix spirally we can peel layers from these matrix, print the peeled part and recursively call the print on 
the left over part. The recursion terminates when we don't have any more layers to print.

Input matrix:

1 2 3 4 
5 6 7 8
9 0 1 2   
3 4 5 6 
7 8 9 1
After peeling top-right layer:

 1 2 3 4 
       8
5 6 7  2
9 0 1  6   
3 4 5  1 
7 8 9
After peeling bottom-left layer from sub-matrix:

   6 7
5  0 1   
9  4 5
3 
7 8 9 
After peeling top-right layer from sub-matrix:

    6 7
      1   
   0  5
   4
After peeling bottom-left layer from sub-matrix:

  0
  4
Recursion terminates.

 */