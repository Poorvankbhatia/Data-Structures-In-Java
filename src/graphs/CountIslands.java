/*

Given a boolean 2D matrix, find the number of islands.

This is an variation of the standard problem: “Counting number of connected components in a undirected graph”.

 */

package graphs;

/**
 * Created by poorvank on 4/25/15.
 */
public class CountIslands {

    public static void main(String[] args) {

        int[][] array = new int[][]{{1, 1, 0, 0, 0},
                                    {0, 1, 0, 0, 1},
                                    {1, 0, 0, 1, 1},
                                    {0, 0, 0, 0, 0},
                                    {1, 0, 1, 0, 1}};

        int arraySize = array.length;
        int elementSize = array[0].length;


        System.out.println(count(array, arraySize, elementSize));

    }


    private static int count(int[][] array, int m, int n) {

        boolean[][] visited = new boolean[m][n];

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (array[i][j] != 0 && !visited[i][j]) {
                    dfs(array, visited, i, j);
                    count++;
                }
            }
        }

        return count;
    }


    private static void dfs(int[][] array, boolean[][] visited, int row, int col) {

        visited[row][col] = true;

        int[] rowNbr = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
        int[] colNbr = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};

        for (int k = 0; k < 8; k++) {
            if (isSafe(row + rowNbr[k], col + colNbr[k], visited, array)) {
                dfs(array, visited, row + rowNbr[k], col + colNbr[k]);
            }
        }

    }

    private static boolean isSafe(int row, int col, boolean[][] visited, int[][] array) {

        return ((row < visited.length && row >= 0) && (col >= 0 && col < visited[0].length) && !visited[row][col] && (array[row][col] != 0));

    }

}


/*


A graph where all vertices are connected with each other, has exactly one connected component, consisting of the whole 
graph. Such graph with only one connected 
component is called as Strongly Connected Graph.

The problem can be easily solved by applying DFS() on each component. In each DFS() call, a component or a sub-graph 
is visited. We will call DFS on the next un-visited component. The number of calls to DFS() gives the number of 
connected components. BFS can also be used.

What is an island?
A group of connected 1s forms an island. For example, the below matrix contains 5 islands

	                    {1, 1, 0, 0, 0},
                        {0, 1, 0, 0, 1},
                        {1, 0, 0, 1, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 1, 0, 1}
A cell in 2D matrix can be connected to 8 neighbors. So, unlike standard DFS(), where we recursively call for 
all adjacent vertices, here we can recursive call for 8 neighbors only. We keep track of the visited 1s so 
that they are not visited again.


The two arrays are used to compute the neighbors of a cell. The values of the arrays are added to the 
X and Y co-ordinates of a cell to get its neighbor.

A cell can be connected to another cell only through 8 ways. So, start from any cell, try to cover 
all connected cells which contain 1.

e.g. If a cell is located at M[row][col], its neighbor can be calculated by:
M[row + rowNbr[i] ][col + colNbr[i] ]
Where 0 <= i < 8

 */