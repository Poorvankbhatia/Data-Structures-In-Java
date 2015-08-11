/*

Given a matrix of n*n. Each cell contain 0, 1, -1.
0 denotes there is no diamond but there is a path.
1 denotes there is diamond at that location with a path
-1 denotes that the path is blocked.
Now you have start from 0,0 and reach to last cell & then return back to 0,0 collecting maximum no of diamonds.
While going to last cell you can move only right and down.
While returning back you can move only left and up.

 */

package interviews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 8/10/15.
 */
public class CollectDiamonds {

    private static final int ROW = 6;
    private static final int COL = 6;
    private static boolean flag = false;
    private static int reachEndCount = 0;
    private static int reachStartCount = 0;
    private static List<Integer> diamondCountList = new ArrayList<>();

    private static int[][] maze = {{1, 0, 1, 1, 1, 0},
            {0, -1, -1, 1, -1, 1},
            {0, -1, -1, 1, -1, 1},
            {0, -1, 0, 1, -1, 1},
            {0, -1, -1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1}};

    public static void main(String[] args) {

        boolean[][] visited = new boolean[ROW][COL];
        maxDiamondsFromStart(visited, 0, 0);
        flag = false;
        visited[ROW - 1][COL - 1] = false;
        if (maze[ROW - 1][COL - 1] == 1) {
            maze[ROW - 1][COL - 1] = 0;
        }
        System.out.println(reachEndCount);
        maxDiamondsFromEnd(visited, ROW - 1, COL - 1, 0);
        System.out.println(reachStartCount);

        System.out.println("Maximum Number of diamonds collected are -" + (reachEndCount + reachStartCount));


    }

    private static void maxDiamondsFromStart(boolean[][] visited, int i, int j) {


        if (flag) {
            return;
        }

        if (isSafe(i, j, visited)) {
            if (maze[i][j] == 1) {
                reachEndCount++;
            }
        } else {
            return;
        }

        if (i == ROW - 1 && j == COL - 1) {
            flag = true;
        }


        visited[i][j] = true;


        maxDiamondsFromStart(visited, i + 1, j);
        maxDiamondsFromStart(visited, i, j + 1);


    }

    private static boolean isSafe(int i, int j, boolean[][] visited) {

        return (i >= 0 && j >= 0 && i < ROW && j < COL && !visited[i][j] && (maze[i][j] != -1));

    }

    private static void maxDiamondsFromEnd(boolean[][] visited, int i, int j, int c) {


        if (isSafe(i, j, visited)) {
            if (maze[i][j] == 1) {
                c++;
                if (c > reachStartCount) {
                    reachStartCount = c;
                }
            }
        } else {
            return;
        }


        maxDiamondsFromEnd(visited, i - 1, j, c);
        maxDiamondsFromEnd(visited, i, j - 1, c);


    }

}


/*

The concept used is :

Start from the top left corner and calculate any path from start to end and mark all the nodes visited.

Then start from the bottom right corner and calculate maximum number of nodes connected until a visited 
node is reached

 */