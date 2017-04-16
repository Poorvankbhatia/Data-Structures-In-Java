/*

Give a N*N square matrix, return an array of its anti-diagonals. Look at the example for more details.

Example:


Input:

1 2 3
4 5 6
7 8 9

Return the following :

[
  [1],
  [2, 4],
  [3, 5, 7],
  [6, 8],
  [9]
]


Input :
1 2
3 4

Return the following  :

[
  [1],
  [2, 3],
  [4]
]

 */
package arrays.twodimensionalarrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 15/04/17.
 */
public class AntiDiagonals {

    public List<List<Integer>> diagonals(int[][] matrix) {

        int row = matrix.length;
        List<List<Integer>> result = new ArrayList<>();
        if(row==0) {
            return result;
        }
        int col = matrix[0].length;

        //First Row
        for (int j=0;j<col;j++) {
            int currentRow = 0;
            List<Integer> list = new ArrayList<>();
            list.add(matrix[currentRow][j]);
            int nextRow = currentRow+1,nextCol = j -1;
            while (nextRow>=0 && nextCol>=0 && nextRow<row && nextCol<col) {
                list.add(matrix[nextRow][nextCol]);
                nextRow +=1;
                nextCol -=1;
            }
            result.add(list);
        }

        //Last Col
        for (int i=1;i<row;i++) {
            int currentCol = col-1;
            List<Integer> list = new ArrayList<>();
            list.add(matrix[i][currentCol]);
            int nextRow = i+1,nextCol = currentCol-1;
            while (nextRow>=0 && nextCol>=0 && nextRow<row && nextCol<col) {
                list.add(matrix[nextRow][nextCol]);
                nextRow +=1;
                nextCol -=1;
            }
            result.add(list);
        }

        return result;

    }

    public static void main(String[] args) {

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        System.out.print(new AntiDiagonals().diagonals(matrix));
    }

}


/*

With every movement, row increases by one, and the column decreases by one ( or in other words (1, -1) gets added to the current co-ordinates ).

 */
