/**
 * Given a grid of numbers, find maximum length Snake sequence and print it.
 * If multiple snake sequences exists with the maximum length, print any one of them.
 * A snake sequence is made up of adjacent numbers in the grid such that for each number,
 * the number on the right or the number below it is +1 or -1 its value. For example,
 * if you are at location (x, y) in the grid, you can either move right i.e. (x, y+1) if that number is ± 1 or move down i.e. (x+1, y)
 * if that number is ± 1.
 *
 * For example,
 * 9, 6, 5, 2
 * 8, 7, 6, 5
 * 7, 3, 1, 6
 * 1, 1, 1, 7
 *
 * In above grid, the longest snake sequence is: (9, 8, 7, 6, 5, 6, 7)
 */
package dyanamicprogramming.matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 27/12/16.
 */
public class SnakeSequence {

    private class Cell {
        int x,y;

        public Cell() {
        }

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private int maximumLength(int[][] matrix) {

        int rows = matrix.length;
        if(rows==0) {
            return 0;
        }

        int cols = matrix[0].length;

        int[][] dpTable = new int[rows][cols];

        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                dpTable[i][j] = -1;
            }
        }

        int max=0;

        Cell tail = new Cell();
        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                maxLengthUtil(i,j,dpTable,matrix,rows,cols);
                if(max<dpTable[i][j]) {
                    tail.x = i;
                    tail.y = j;
                    max = dpTable[i][j];
                }
            }
        }

        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                System.out.print(dpTable[i][j] + " ");
            }
            System.out.println();
        }

        printSnake(dpTable,tail,rows,cols);

        return max;

    }

    private void printSnake(int[][] dpTable,Cell tail,int rows,int cols) {

        int x = tail.x,y=tail.y;
        List<Cell> list = new ArrayList<>();

        while (x<rows && y<cols &&dpTable[x][y]!=1) {
            list.add(new Cell(x,y));
            if(dpTable[x][y]-1==dpTable[x+1][y]) {
                x++;
            } else if(dpTable[x][y]-1==dpTable[x][y+1]) {
                y++;
            }
        }

        if(x<rows && y<cols) {
            list.add(new Cell(x,y));
        }

        System.out.println(list);

    }


    private int maxLengthUtil(int i,int j,int[][] dpTable,int[][] matrix,int rows,int cols) {

        if (i<0 || i>=rows || j<0 || j>=cols)
            return 0;

        if(dpTable[i][j]!=-1) {
            return dpTable[i][j];
        }

        int maxDown = Integer.MIN_VALUE;
        int maxRight = Integer.MIN_VALUE;


        if(i+1<rows && Math.abs(matrix[i+1][j]-matrix[i][j])==1) {
            maxDown = 1+ maxLengthUtil(i+1,j,dpTable,matrix,rows,cols);
        }

        if(j+1<cols && Math.abs(matrix[i][j+1]-matrix[i][j])==1) {
            maxRight = 1+ maxLengthUtil(i,j+1,dpTable,matrix,rows,cols);
        }



        int finalValue  = Math.max(maxDown,maxRight);

        dpTable[i][j]=finalValue<0?1:finalValue;

        return dpTable[i][j];

    }


    public static void main(String[] args) {
        int[][] arr = new int[][]{
                {9, 6, 5, 2},
                {8, 7, 6, 5},
                {7, 3, 1, 6},
                {1, 1, 1, 7}
        };

        System.out.println("Maximum length - " + new SnakeSequence().maximumLength(arr));
    }

}

/*

Time complexity of above solution is O(M*N). Auxiliary space used by above solution is O(M*N).
If we are not required to print the snake, space  can be further reduced to O(N) as we only uses the result from last row.

 */