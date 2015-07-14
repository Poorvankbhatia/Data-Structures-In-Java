/*

In MS-Paint, when we take the brush to a pixel and click, the color of the region of that pixel is replaced 
with a new selected color. Following is the problem statement to do this task. 
Given a 2D screen, location of a pixel in the screen and a color, replace color of the given pixel 
and all adjacent same colored pixels with the given color.

Example:

Input:
       screen[M][N] = {{1, 1, 1, 1, 1, 1, 1, 1},
                      {1, 1, 1, 1, 1, 1, 0, 0},
                      {1, 0, 0, 1, 1, 0, 1, 1},
                      {1, 2, 2, 2, 2, 0, 1, 0},
                      {1, 1, 1, 2, 2, 0, 1, 0},
                      {1, 1, 1, 2, 2, 2, 2, 0},
                      {1, 1, 1, 1, 1, 2, 1, 1},
                      {1, 1, 1, 1, 1, 2, 2, 1},
                      };
    x = 4, y = 4, newColor = 3
The values in the given 2D screen indicate colors of the pixels.
x and y are coordinates of the brush, newColor is the color that
should replace the previous color on screen[x][y] and all surrounding
pixels with same color.

Output:
Screen should be changed to following.
       screen[M][N] = {{1, 1, 1, 1, 1, 1, 1, 1},
                      {1, 1, 1, 1, 1, 1, 0, 0},
                      {1, 0, 0, 1, 1, 0, 1, 1},
                      {1, 3, 3, 3, 3, 0, 1, 0},
                      {1, 1, 1, 3, 3, 0, 1, 0},
                      {1, 1, 1, 3, 3, 3, 3, 0},
                      {1, 1, 1, 1, 1, 3, 1, 1},
                      {1, 1, 1, 1, 1, 3, 3, 1},
                      };

 */

package arrays;

/**
 * Created by poorvank on 6/8/15.
 */
public class FloodFill {

    public static void main(String[] args) {

        int[][] screen = new int[][]{{1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 0, 0},
                {1, 0, 0, 1, 1, 0, 1, 1},
                {1, 2, 2, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 0, 1, 0},
                {1, 1, 1, 2, 2, 2, 2, 0},
                {1, 1, 1, 1, 1, 2, 1, 1},
                {1, 1, 1, 1, 1, 2, 2, 1},
        };

        int newC = 3, prevC = 2;

        aboveAlgorithm(screen, newC, prevC, 4, 4);

        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[0].length; j++)
                System.out.print(screen[i][j] + " ");
            System.out.println();
        }

    }

    private static void aboveAlgorithm(int[][] screen, int newC, int prevC, int x, int y) {

        int row = screen.length;
        int col = screen[0].length;

        if (x < 0 || x >= row || y < 0 || y >= col) {
            return;
        }
        if (screen[x][y] != prevC) {
            return;
        }


        screen[x][y] = newC;

        aboveAlgorithm(screen, newC, prevC, x + 1, y);
        aboveAlgorithm(screen, newC, prevC, x - 1, y);
        aboveAlgorithm(screen, newC, prevC, x, y + 1);
        aboveAlgorithm(screen, newC, prevC, x, y - 1);


    }

}

/*

Flood Fill Algorithm:
The idea is simple, we first replace the color of current pixel, then recur for 4 surrounding points.
 The following is detailed algorithm.

// A recursive function to replace previous color 'prevC' at  '(x, y)' 
// and all surrounding pixels of (x, y) with new color 'newC' and
floodFil(screen[M][N], x, y, prevC, newC)
1) If x or y is outside the screen, then return.
2) If color of screen[x][y] is not same as prevC, then return
3) Recur for north, south, east and west.
    floodFillUtil(screen, x+1, y, prevC, newC);
    floodFillUtil(screen, x-1, y, prevC, newC);
    floodFillUtil(screen, x, y+1, prevC, newC);
    floodFillUtil(screen, x, y-1, prevC, newC); 

 */