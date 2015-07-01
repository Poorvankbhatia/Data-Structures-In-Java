/*

Same concept as count number of islands

1 -Given a mxn grid, each of its element be either ‘.’, ‘R’, ‘G’ or ‘B’,
where ‘.’ -> empty, ‘R’ -> Red, ‘G’ -> Green, ‘B’ -> Blue
A Blue strip has width 1 and length greater or equal to one.
A Red strip has length 1 and width greater or equal to one.
If a Red strip and a Blue strip overlaps, the overlapped portion will become ‘G’.
Find the minimum number of strips required to cover the whole grid.
1<= m,n<=100
Ex.
Input
2 4
..B.
..B.
Output
1
Explanation:
Blue strips are vertical.
Red strips are horizontal.
Ex 1:
Only 1 vertical strip from (0,2) to (1,2). [Indexing from (0,0)]
Ex 2:
1 vertical strip from (0,2) to (2,2)
1 horizontal strip from (1,2) to (1,4)
1 horizontal strip from (3,0) to (3,0)
1 horizontal strip from (4,0) to (4,0)
so total —4

 */

package interviews;

import java.util.Scanner;

/**
 * Created by poorvank on 4/25/15.
 */
public class MinimumStrips {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();

        char[][] grid = new char[row][col];

        Scanner scanner = new Scanner(System.in);

        System.out.println("Filling matrix");

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = scanner.next().charAt(0);
            }
        }

        System.out.println("Matrix is ");

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }

        System.out.println("Total number of strips are  : " + countStrips(grid, row, col));

    }

    private static int countStrips(char[][] grid, int row, int col) {

        boolean[][] visited = new boolean[row][col];

        int count = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((grid[i][j] == 'R' || grid[i][j] == 'G') && !visited[i][j]) {
                    dfs_horizontal(visited, grid, i, j);
                    count++;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                visited[i][j] = false;
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if ((grid[i][j] == 'B' || grid[i][j] == 'G') && !visited[i][j]) {
                    dfs_vertical(visited, grid, i, j);
                    count++;
                }
            }
        }


        return count;
    }

    private static void dfs_horizontal(boolean[][] visited, char[][] grid, int row, int col) {

        visited[row][col] = true;

        int colNbr = 1;
        int rowNbr = 0;

        for (int i = 0; i < grid[0].length; i++) {
            if (isSafe(row + rowNbr, col + colNbr, grid, visited) && (grid[row][col] == 'R' || grid[row][col] == 'G')) {
                dfs_horizontal(visited, grid, row + rowNbr, col + colNbr);
            }
        }

    }

    private static void dfs_vertical(boolean[][] visited, char[][] grid, int row, int col) {

        visited[row][col] = true;

        int colNbr = 0;
        int rowNbr = 1;

        for (int i = 0; i < grid.length; i++) {
            if (isSafe(row + rowNbr, col + colNbr, grid, visited) && (grid[row][col] == 'B' || grid[row][col] == 'G')) {
                dfs_vertical(visited, grid, row + rowNbr, col + colNbr);
            }
        }

    }


    private static boolean isSafe(int row, int col, char[][] grid, boolean[][] visited) {

        return ((row >= 0 && row < grid.length) && (col >= 0 && col < grid[0].length) && !visited[row][col]);

    }
}


/*


Red => Extend horizontally
Blue => Extend vertically

Empty cells can't be covered. So if there is a column containing 'B' in all the cells except the middle
 cell then it will require two Blue strips to cover this column. One strip from top till middle-1 and
  one strip from middle+1 to bottom.

If you encounter a green strip then you have to make sure that both Red & Blue strips cover that cell.
Simple example to clarify it: Consider a 2 X 2 matrix. First cell has G and rest of the cells are empty.
It will require two strips to cover the G cell (One Blue & One Red) and thus two strips to cover the grid/matrix.

Solutions:
This is a very simple extension of island problem. 
First pass: Make a column wise first pass on the matrix. Calculate the number of islands of B. 
If there are consecutive B's then they become part of the same island. 
If B's are divided by G then the G is also included in the island. 
If G comes alone (no upper & lower column with B) then count as a separate island.

Second pass: Same as above but this time it is for R and scanning is done horizontally. 
This will give you number of R's.

Number of strips = number from first pass + number from second pass

 */