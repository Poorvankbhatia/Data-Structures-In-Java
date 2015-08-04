/*

Given a boolean matrix mat[M][N] of size M X N, modify it such that if
 a matrix cell mat[i][j] is 1 (or true) then make all the cells of ith row and jth column as 1.

Example 1
The matrix
1 0
0 0
should be changed to following
1 1
1 0

Example 2
The matrix
0 0 0
0 0 1
should be changed to following
0 0 1
1 1 1

Example 3
The matrix
1 0 0 1
0 0 1 0
0 0 0 0
should be changed to following
1 1 1 1
1 1 1 1
1 0 1 1


 */

package arrays.twodimensionalarrays;

/**
 * Created by poorvank on 6/18/15.
 */
public class BooleanMatrix {

    public static void main(String[] args) {

        int[][] a = new int[][]{{1, 0, 0, 1},
                {0, 0, 1, 0},
                {0, 0, 0, 0}};


        int row = a.length;
        int col = a[0].length;


        //Call for noExtra Space method
        //noExtraSpace(a,row,col);
        
        /*
        
        1) Create two temporary arrays row[M] and col[N]. Initialize all values of row[] and col[] as 0.
2) Traverse the input matrix mat[M][N]. If you see an entry mat[i][j] as true, then mark row[i] and col[j] as true.
3) Traverse the input matrix mat[M][N] again. For each entry mat[i][j], 
check the values of row[i] and col[j]. If any of the two values (row[i] or col[j]) is true, then mark mat[i][j] as true.
        
         */
        int[] rowArr = new int[row];
        int[] colArr = new int[col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if (a[i][j] == 1) {
                    rowArr[i] = 1;
                    colArr[j] = 1;
                }

            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if (rowArr[i] == 1 || colArr[j] == 1) {
                    a[i][j] = 1;
                }

            }
        }

        System.out.println("Matrix is : ");

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                System.out.print(a[i][j] + " ");

            }
            System.out.println();
        }


    }


    private static void noExtraSpace(int[][] a, int row, int col) {

        boolean paintRow1 = false, painCol1 = false;

        int i = 0, j = 0;
        while (!paintRow1 && j < col) {
            if (a[0][j] == 1) {
                paintRow1 = true;
                break;
            }
        }

        while (!painCol1 && i < row) {
            if (a[i][0] == 1) {
                painCol1 = true;
                break;
            }
        }


        for (i = 1; i < row; i++) {
            for (j = 1; j < col; j++) {
                if (a[i][j] == 1) {
                    a[i][0] = 1;
                    a[0][j] = 1;
                }
            }
        }

        for (i = 1; i < row; i++) {
            for (j = 1; j < col; j++) {

                if (a[0][j] == 1 || a[i][0] == 1) {
                    a[i][j] = 1;
                }

            }
        }

        if (paintRow1) {
            for (i = 0; i < col; i++) {
                a[0][i] = 1;
            }

        }

        if (painCol1) {
            for (i = 0; i < row; i++) {
                a[i][0] = 1;
            }

        }

        System.out.println("Matrix is : ");

        for (i = 0; i < row; i++) {
            for (j = 0; j < col; j++) {

                System.out.print(a[i][j] + " ");

            }
            System.out.println();
        }

    }

}


/*

This method is a space optimized version of above method 1. 
This method uses the first row and first column of the input matrix in 
place of the auxiliary arrays row[] and col[] of method 1. So what we do is:
 first take care of first row and column and store the info about these two in two flag variables rowFlag 
 and colFlag. Once we have this info, we can use first row and first column as auxiliary arrays and apply method 1 
 for submatrix (matrix excluding first row and first column) of size (M-1)*(N-1).

1) Scan the first row and set a variable rowFlag to indicate whether we need to set all 1s in first row or not.
2) Scan the first column and set a variable colFlag to indicate whether we need to set all 1s in first column or not.
3) Use first row and first column as the auxiliary arrays row[] and col[] respectively, consider the matrix
 as submatrix starting from second row and second column and apply method 1.
4) Finally, using rowFlag and colFlag, update first row and first column if needed.

Time Complexity: O(M*N)
Auxiliary Space: O(1)

 */