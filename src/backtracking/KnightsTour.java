/*

The knight is placed on the first block of an empty board and, 
moving according to the rules of chess, must visit each square exactly once.



 */

package backtracking;

/**
 * Created by poorvank on 7/13/15.
 */
public class KnightsTour {

    /* xMove[] and yMove[] define next move of Knight.
      xMove[] is for next value of x coordinate
      yMove[] is for next value of y coordinate */
    private static final int[] XMOVE = new int[]{2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] YMOVE = new int[]{1, 2, 2, 1, -1, -2, -2, -1};
    private static final int NOTCOVERED = -1;
    private static final int N = 5;

    public static void main(String[] args) {

        kst();

    }

    private static void printSolution(int[][] sol) {

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }

    }

    private static void kst() {

        int[][] solution = new int[8][8];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                solution[i][j] = NOTCOVERED;
            }
        }

        solution[3][3] = 0;

        if (!kstUtil(solution, 1, 4, 4)) {
            System.out.println("Solution does not exists");
        } else {
            System.out.println("Solution found is - \n");
            printSolution(solution);
        }


    }

    private static boolean kstUtil(int[][] sol, int move, int x, int y) {

        int nextX = 0, nextY = 0;
        if (move == (N * N)) {
            return true;
        }

        for (int k = 0; k < 8; k++) {


            nextX = x + XMOVE[k];
            nextY = y + YMOVE[k];

            if (isSafe(sol, nextX, nextY)) {

                sol[nextX][nextY] = move;
                if (kstUtil(sol, move + 1, nextX, nextY)) {
                    return true;
                } else {
                    // System.out.println("Did not work for " + nextX + " & " + nextY);
                    sol[nextX][nextY] = NOTCOVERED;
                }


            }


        }

        return false;

    }


    private static boolean isSafe(int[][] sol, int x, int y) {

        return x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == NOTCOVERED;

    }


}

/*

Following is the Backtracking algorithm for Knight’s tour problem.

If all squares are visited
    print the solution
Else
   a) Add one of the next moves to solution vector and recursively
   check if this move leads to a solution. (A Knight can make maximum
   eight moves. We choose one of the 8 moves in this step).
   b) If the move chosen in the above step doesn't lead to a solution
   then remove this move from the solution vector and try other
   alternative moves.
   c) If none of the alternatives work then return false (Returning false
   will remove the previously added item in recursion and if false is
   returned by the initial call of recursion then "no solution exists" )

 */