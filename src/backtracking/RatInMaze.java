/*

A Maze is given as N*N binary matrix of blocks where source block is the upper left most block i.e.,
 maze[0][0] and destination block is lower rightmost block i.e., maze[N-1][N-1]. 
 A rat starts from source and has to reach destination. The rat can move only in two directions: forward and down.
In the maze matrix, 0 means the block is dead end and 1 means the block can be used in the path from source 
to destination. Note that this is a simple version of the typical Maze problem. For example, a more complex version 
can be that the rat can move in 4 directions and a more complex version can be with limited number of moves.

 */

package backtracking;

/**
 * Created by poorvank on 7/14/15.
 */
public class RatInMaze {

    public static void main(String[] args) {

        int maze[][] = new int[][]{ {1, 0, 0, 0},
                                    {1, 1, 0, 1},
                                    {0, 1, 0, 0},
                                    {1, 1, 1, 1}};

        solveMaze(maze);

    }

    private static void printSolution(int[][] sol) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(sol[i][j] + " ");
            }
            System.out.println();
        }

    }


    /* This function solves the Maze problem using Backtracking.  It mainly uses
solveMazeUtil() to solve the problem. It returns false if no path is possible,
otherwise return true and prints the path in the form of 1s. Please note that
there may be more than one solutions, this function prints one of the feasible
solutions.*/
    private static void solveMaze(int[][] maze) {

        int[][] solution = new int[4][4];

        if (!solveMazeUtil(solution, maze, 0, 0)) {
            System.out.println("Solution not possible");
        } else {
            printSolution(solution);
        }

    }

    private static boolean isSafe(int[][] maze, int x, int y) {

        return (x >= 0 && x < 4 && y >= 0 && y < 4 && maze[x][y] == 1);

    }

    private static boolean solveMazeUtil(int[][] sol, int[][] maze, int x, int y) {

        if (x == 3 && y == 3) {
            sol[x][y] = 1;
            return true;
        }

        if (isSafe(maze, x, y)) {

            sol[x][y] = 1;

            if (solveMazeUtil(sol, maze, x + 1, y)) {
                return true;
            }

            if (solveMazeUtil(sol, maze, x, y + 1)) {
                return true;
            }

            sol[x][y] = 0;
            return false;

        }

        return false;

    }

}


/*

If destination is reached
    print the solution matrix
Else
   a) Mark current cell in solution matrix as 1.
   b) Move forward in horizontal direction and recursively check if this
       move leads to a solution.
   c) If the move chosen in the above step doesn't lead to a solution
       then move down and check if  this move leads to a solution.
   d) If none of the above solutions work then unmark this cell as 0
       (BACKTRACK) and return false.

 */