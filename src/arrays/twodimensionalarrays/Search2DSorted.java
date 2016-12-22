/*

Search in a row wise and column wise sorted matrix
Given an n x n matrix, where every row and column is sorted in increasing order. 
Given a number x, how to decide whether this x is in the matrix. The designed algorithm should have linear time complexity.

 */

package arrays.twodimensionalarrays;

/**
 * Created by poorvank on 6/23/15.
 */
public class Search2DSorted {

    public static void main(String[] args) {

        int mat[][] = new int[][]{{10, 20, 30, 40},
                                  {15, 25, 35, 45},
                                  {27, 29, 37, 48},
                                  {32, 33, 39, 50}};


        findNo(mat, 39, 4);

    }

    private static void findNo(int[][] matrix, int no, int n) {

        int i = 0, j = n - 1;

        while (i < n && j >= 0) {

            if (matrix[i][j] == no) {
                System.out.println("Found number at : " + i + " , " + j);
                return;
            } else if (matrix[i][j] < no) {
                i++;
            } else {
                j--;
            }

        }

        System.out.print("Not found");

    }

}


/*

1) Start with top right element
2) Loop: compare this element e with x
….i) if they are equal then return its position
…ii) e < x then move it to down (if out of bound of matrix then break return false)
..iii) e > x then move it to left (if out of bound of matrix then break return false)
3) repeat the i), ii) and iii) till you find element or returned false

 */