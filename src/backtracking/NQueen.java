/*

The N Queen is the problem of placing N chess queens on an N×N chessboard so that no two queens attack each other. 
For example, following is a solution for 4 Queen problem. see images

The expected output is a binary matrix which has 1s for the blocks where queens are placed. 
              { 0,  1,  0,  0}
              { 0,  0,  0,  1}
              { 1,  0,  0,  0}
              { 0,  0,  1,  0}

 */

package backtracking;

/**
 * Created by poorvank on 7/14/15.
 */
public class NQueen {

    private static final int N = 8;

    public static void main(String[] args) {

        solveNQ();
    }

    private static void solveNQ() {

        int[][] board = new int[][]{{0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0}};

        if (!solveNQUtil(board, 0)) {
            System.out.println("No solution exists");
        } else {
            printSolution(board);
        }


    }

    private static void printSolution(int[][] sol) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }

    }


    private static boolean solveNQUtil(int[][] board, int col) {

        if (col >= N) {
            return true;
        }

        for (int i = 0; i < N; i++) {

            if (isSafe(board, i, col)) {

                board[i][col] = 1;

                if (solveNQUtil(board, col + 1)) {
                    return true;
                }

            }

            board[i][col] = 0;

        }

        return false;

    }

    private static boolean isSafe(int[][] board, int row, int col) {
        
        
        /* Check row on left side */
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }
        
        /*Check upper diagonal*/
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {

            if (board[i][j] == 1) {
                return false;
            }

        }
        
        /*Check lower diagonal*/
        for (int i = row, j = col; i < N && j >= 0; i++, j--) {

            if (board[i][j] == 1) {
                return false;
            }

        }

        return true;

    }

}


/*

Backtracking Algorithm
The idea is to place queens one by one in different columns, starting from the leftmost column. 
When we place a queen in a column, we check for clashes with already placed queens. 
In the current column, if we find a row for which there is no clash, we mark this row and column as part of the solution. 
If we do not find such a row due to clashes then we backtrack and return false.

1) Start in the leftmost column
2) If all queens are placed
    return true
3) Try all rows in the current column.  Do following for every tried row.
    a) If the queen can be placed safely in this row then mark this [row, 
        column] as part of the solution and recursively check if placing  
        queen here leads to a solution.
    b) If placing queen in [row, column] leads to a solution then return 
        true.
    c) If placing queen doesn't lead to a solution then umark this [row, 
        column] (Backtrack) and go to step (a) to try other rows.
3) If all rows have been tried and nothing worked, return false to trigger 
    backtracking.
    
    
    https://see.stanford.edu/materials/icspacs106b/H19-RecBacktrackExamples.pdf

 */