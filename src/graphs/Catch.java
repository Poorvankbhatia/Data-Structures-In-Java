package graphs;/*

User Starts from 'B'
Had to catch someone at 'C' find shortest distance
'.' is epmty cell and 'D' is block.

USE BFS

 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by poorvank on 24/02/17.
 */
public class Catch {

    static int[] xMove = new int[]{1,0,-1,0};
    static int[] yMove = new int[]{0,1,0,-1};

    private static class Cell {
        int x,y;
        int pathLength=0;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int getMinTime(char[][] grid) {

        int rows = grid.length;


        if(rows==0) {
            return -1;
        }

        int cols = grid[0].length;


        boolean[][] visited = new boolean[rows][cols];

        for (int i=0;i<rows;i++) {
            for (int j=0;j<cols;j++) {
                if(grid[i][j]=='B') {
                    return minTimeUtil(grid,rows,cols,i,j,visited,0);
                }
            }
        }

        return -1;

    }

    private static boolean isValidMove(int x,int y,int rows,int cols,boolean[][] visited,char[][] grid) {

        return (x>=0 && y>=0 && x<rows && y<cols && !visited[x][y] && grid[x][y]!='D');

    }

    private static int minTimeUtil(char[][] grid,int rows,int cols,int i,int j,boolean[][] visited,int result) {

        if(grid[i][j]=='C') {
            return result;
        }

        Queue<Cell> queue = new LinkedList<>();
        Cell cell = new Cell(i,j);
        queue.add(cell);

        while (!queue.isEmpty()) {

            Cell current = queue.poll();
            if(grid[current.x][current.y]=='C') {
                return current.pathLength;
            }
            i = current.x;
            j= current.y;
            visited[i][j] = true;

            for (int k=0;k<4;k++) {

                int nextX = i+xMove[k];
                int nextY = j+yMove[k];
                if(isValidMove(nextX,nextY,rows,cols,visited,grid)) {
                    Cell neigbour = new Cell(nextX,nextY);
                    neigbour.pathLength = current.pathLength+1;
                    queue.add(neigbour);
                }


            }

        }

        return -1;

    }

    public static void main (String[] args) throws java.lang.Exception {

        char[][] grid = new char[][]{
                {'.', '.', '.', '.', 'C', '.'},
                {'.', '.', 'D', '.', 'D', '.'},
                {'.', '.', 'D', '.', 'D', 'B'},
                {'.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', 'D', '.'},
                {'.', '.', 'D', '.', 'D', '.'},
                {'.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', 'D', '.'},
                {'.', '.', 'D', '.', 'D', '.'},
                {'.', '.', '.', '.', 'D', '.'},
                {'.', '.', 'D', '.', 'D', '.'},
                {'.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', 'D', '.'},
                {'.', '.', 'D', '.', 'D', '.'},
                {'.', '.', '.', '.', 'D', '.'},
                {'.', '.', 'D', '.', 'D', '.'},
                {'.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', 'D', '.'},
                {'.', '.', 'D', '.', 'D', '.'},
                {'.', '.', '.', '.', 'D', '.'},
                {'.', '.', 'D', '.', 'D', '.'},
                {'.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', 'D', '.'},
                {'.', '.', 'D', '.', 'D', '.'},
                {'.', '.', '.', '.', 'D', '.'},
                {'.', '.', 'D', '.', 'D', '.'},
                {'.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', 'D', '.'},
                {'.', '.', 'D', '.', 'D', '.'}
        };

        getMinTime(grid);


    }

    }
