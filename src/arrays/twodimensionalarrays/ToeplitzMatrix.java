/*
Given a square matrix, find if it’s a Toeplitz matrix or not. A Toeplitz (or diagonal-constant)
 matrix is a matrix in which each descending diagonal from left to right is constant, i.e., all elements in a diagonal are same.

In general, any n×n matrix mat[][] is a Toeplitz matrix if every cell mat[i][j] is same as mat[i-1][j-1],
mat[i+1][j+1], mat[i-2][j-2], mat[i+2][j+2], .. for every cell mat[i][j] and all the valid cells mat[i+k][j+k] or mat[i-k][j-k]

Examples :

Input: mat[N][N] = {{ 6, 7, 8},
                    { 4, 6, 7},
                    { 1, 4, 6}},
Output : True;
Values in all diagonals are same.

Input: mat[N][N] = {{ 6, 7, 8, 9 },
                    { 4, 6, 7, 8 },
                    { 1, 4, 6, 7 },
                    { 0, 1, 4, 6 },
                    { 2, 0, 1, 4 }};
Output : True;

Input: mat[N][N] = {{ 6, 3, 8},
                    { 4, 9, 7},
                    { 1, 4, 6}},
Output : False;
 */
package arrays.twodimensionalarrays;

/**
 * Created by poorvank.b on 11/09/17.
 */
public class ToeplitzMatrix {

    public boolean isToeplitz(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        for (int j=0;j<n;j++) {

            if(!haveSameElementsAcross(matrix,0,j,m,n)) {
                return false;
            }

        }

        for (int i=0;i<m;i++) {

            if(!haveSameElementsAcross(matrix,i,0,m,n)) {
                return false;
            }

        }

        return true;

    }

    private boolean haveSameElementsAcross(int[][] matrix,int i,int j,int m,int n) {

        int res = matrix[i][j];

        while (++i<m && ++j<n) {
            if(res!=matrix[i][j]) {
                return false;
            }
        }

        return true;

    }

    public static void main(String[] args) {

        int[][] mat = new int[][] {
                { 6, 7, 8, 9 },
                { 4, 6, 7, 8 },
                { 1, 4, 6, 7 },
                { 0, 1, 4, 6 },
                { 2, 0, 1, 4 }
        };
        System.out.println(new ToeplitzMatrix().isToeplitz(mat));

    }

}

// O(n2)