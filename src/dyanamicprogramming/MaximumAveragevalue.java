/*

Path with maximum average value
Given a square matrix of size N*N, where each cell is associated with a specific cost.
A path is defined as a specific sequence of cells which starts from top left cell move only right or down and ends on bottom right cell.
We want to find a path with maximum average over all existing paths. Average is computed as total cost divided by number of cells visited in path.
Examples:

Input : Matrix = [1, 2, 3
                  4, 5, 6
                  7, 8, 9]
Output : 5.8
Path with maximum average is, 1 -> 4 -> 7 -> 8 -> 9
Sum of the path is 29 and average is 29/5 = 5.8

 */

package dyanamicprogramming;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 03/12/16.
 */
public class MaximumAverageValue {

    private class CellObj {
        int value;
        List<Integer> list;

        public CellObj(int value) {
            this(value,new ArrayList<>());
        }

        public CellObj(int value,List<Integer> list) {
            this.value = value;
            this.list = list;
        }

        private boolean isSmall(CellObj obj) {
            return this.value<=obj.value;
        }
    }

    public void maximumAverage(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int steps = rows+cols-1;

        CellObj[][] dpSum = new CellObj[rows][cols];

        dpSum[0][0] = new CellObj(matrix[0][0]);
        dpSum[0][0].list.add(matrix[0][0]);

        for (int j=1;j<cols;j++) {
            int value = dpSum[0][j-1].value+matrix[0][j];
            List<Integer> list = new ArrayList<>(dpSum[0][j-1].list);
            list.add(matrix[0][j]);
            dpSum[0][j] = new CellObj(value,list);
        }


        for (int i=1;i<rows;i++) {
            int value = dpSum[i-1][0].value+matrix[i][0];
            List<Integer> list = new ArrayList<>(dpSum[i-1][0].list);
            list.add(matrix[i][0]);
            dpSum[i][0] = new CellObj(value,list);
        }

        for (int i=1;i<rows;i++) {
            for (int j=1;j<cols;j++) {
                CellObj max = dpSum[i-1][j].isSmall(dpSum[i][j-1])?dpSum[i][j-1]:dpSum[i-1][j];
                int value = max.value + matrix[i][j];
                List<Integer> list = new ArrayList<>(max.list);
                list.add(matrix[i][j]);
                dpSum[i][j] = new CellObj(value,list);
            }
        }

        double average = (double) ((dpSum[rows-1][cols-1]).value)/steps ;

        System.out.println("Maximum average value = " + average);

        System.out.println("Path - " + dpSum[rows-1][cols-1].list.toString());

    }


    public static void main(String[] args) {
        int mat[][] = {{1, 2, 3},
                       {6, 4, 5},
                       {7, 3, 9}};

        new MaximumAverageValue().maximumAverage(mat);
    }

}

/*

One interesting observation is, the only allowed moves are down and right, we need N-1 down moves and N-1 right moves to reach destination
(bottom rightmost). So any path from from top left corner to bottom right corner requires 2N – 1 cells. In average value,
denominator is fixed and we need to just maximize numerator. Therefore we basically need to to find maximum sum path.

 */