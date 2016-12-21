/*

Given a matrix of ‘O’ and ‘X’, replace ‘O’ with ‘X’ if surrounded by ‘X’
Given a matrix where every element is either ‘O’ or ‘X’, replace ‘O’ with ‘X’
 if surrounded by ‘X’. A ‘O’ (or a set of ‘O’) is considered to be by surrounded by ‘X’ if 
 there are ‘X’ at locations just below, just above, just left and just right of it.

 */

package graphs.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 6/8/15.
 */
public class ReplaceXO {

    private static class Cell {
        int x,y,value;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {

        char[][] matrix = new char[][]{{'O', 'X', 'X', 'O', 'X'},
                                       {'X', 'O', 'O', 'X', 'O'},
                                       {'X', 'O', 'X', 'O', 'X'},
                                       {'O', 'X', 'O', 'O', 'O'},
                                       {'X', 'X', 'O', 'X', 'O'}
        };


        int row = matrix.length;
        int col = matrix[0].length;

        for (int i = 0; i < row; i++) {

            for (int j = 0; j < col; j++) {

                if (matrix[i][j] == 'O') {
                    matrix[i][j] = '-';
                }

            }

        }

        algorithm(matrix, row, col);
        printMatrix(matrix);
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] aMatrix : matrix) {
            for (int j = 0; j < matrix[0].length; j++)
                System.out.print(aMatrix[j] + " ");
            System.out.println();
        }
    }

    private static void algorithm(char[][] matrix, int row, int col) {

        for (int i = 0; i < row; i++) {
            if (matrix[i][0] == '-')
                floodFill(matrix, 'O', '-', i, 0);
        }
        for (int i = 0; i < row; i++) {
            if (matrix[i][col - 1] == '-')
                floodFill(matrix, 'O', '-', i, col - 1);
        }
        for (int i = 0; i < col; i++) {
            if (matrix[0][i] == '-')
                floodFill(matrix, 'O', '-', 0, i);
        }
        for (int i = 0; i < col; i++) {
            if (matrix[row - 1][i] == '-')
                floodFill(matrix, 'O', '-', row - 1, i);
        }

        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                if (matrix[i][j] == '-')
                    matrix[i][j] = 'X';


    }

    private static void floodFill(char[][] screen, char newC, char prevC, int x, int y) {

        int row = screen.length;
        int col = screen[0].length;
        Queue<Cell> queue = new LinkedList<>();

        Cell c = new Cell(x,y);
        queue.add(c);

        while (!queue.isEmpty()) {

            Cell pop = queue.remove();
            screen[pop.x][pop.y]=newC;
            x = pop.x;
            y = pop.y;

            if(isValid(prevC,x+1,y,screen,row,col)) {
                queue.add(new Cell(x+1,y));
            }
            if(isValid(prevC,x-1,y,screen,row,col)) {
                queue.add(new Cell(x-1,y));
            }
            if(isValid(prevC,x,y+1,screen,row,col)) {
                queue.add(new Cell(x,y+1));
            }
            if(isValid(prevC,x,y-1,screen,row,col)) {
                queue.add(new Cell(x,y-1));
            }

        }

    }

    private static boolean isValid(char prevC,int x,int y,char[][] screen,int row,int col) {
        return (x>0 && x<row && y>0 && y<col && screen[x][y]==prevC);
    }

}


/*

This is mainly an application of Flood-Fill algorithm. The main difference here is that a 
‘O’ is not replaced by ‘X’ if it lies in region that ends on a boundary. 
Following are simple steps to do this special flood fill.

1) Traverse the given matrix and replace all ‘O’ with a special character ‘-‘.

2) Traverse four edges of given matrix and call floodFill(‘-‘, ‘O’) for every ‘-‘ on edges. 
The remaining ‘-‘ are the characters that indicate ‘O’s (in the original matrix) to be replaced by ‘X’.

3) Traverse the matrix and replace all ‘-‘s with ‘X’s.

 */