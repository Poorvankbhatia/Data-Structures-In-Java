/*

Given a matrix of dimension m*n where each cell in the matrix can have values 0, 1 or 2 which has the following meaning:

0: Empty cell

1: Cells have fresh oranges

2: Cells have rotten oranges

So we have to determine what is the minimum time required so that all the oranges become rotten.
A rotten orange at index [i,j] can rot other fresh orange at indexes [i-1,j], [i+1,j], [i,j-1], [i,j+1]
(up, down, left and right). If it is impossible to rot every orange then simply return -1.

Examples:

Input:  arr[][C] = { {2, 1, 0, 2, 1},
                     {1, 0, 1, 2, 1},
                     {1, 0, 0, 2, 1}};
Output:
All oranges can become rotten in 2 time frames.


Input:  arr[][C] = { {2, 1, 0, 2, 1},
                     {0, 0, 1, 2, 1},
                     {1, 0, 0, 2, 1}};
Output:
All oranges cannot be rotten.

 */


package graphs.search;

import utility.Queue;

/**
 * Created by poorvank on 12/23/15.
 */


public class TimeToRotOrange {

    private final int ROW;
    private final int COL;
    private int minimumTime;

    public TimeToRotOrange(int[][] input) {
        ROW = input.length;
        COL = input[0].length;
        minimumTime = minTime(input);
        if(!checkAll(input)) {
            minimumTime = -1;
        }
    }

    public int getMinimumTime() {
        return minimumTime;
    }

    private class Cell {
        private int x,y;

        public Cell(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public static void main(String[] args) {

        int[][] input = new int[][] { {2, 1, 0, 2, 1},
                {0, 0, 1, 2, 1},
                {1, 0, 0, 2, 1}};

        TimeToRotOrange time =new TimeToRotOrange(input);


        System.out.println("Minimum time = " + time.getMinimumTime());

    }

    private boolean checkAll(int[][] input) {
        for (int i=0;i<ROW;i++) {
            for (int j=0;j<COL;j++) {
                if(input[i][j]==1) {
                    return false;
                }
            }
        }
        return true;
    }

    private int minTime(int[][] input) {

        Queue<Cell> queue = new Queue<>();
        int time = 0;
        boolean flag = false;

        for (int row=0;row<ROW;row++) {
            for (int col=0;col<COL;col++) {
                if(input[row][col]==2) {
                    Cell cell = new Cell(row,col);
                    queue.enqueue(cell);
                }
            }
        }

        Cell delimiter = new Cell(-1,-1);
        queue.enqueue(delimiter);

        while (!queue.isEmpty()) {

            Cell current = queue.dequeue();

            while (current.x!=-1 && current.y!=-1) {

                if(isValid(current.x+1,current.y,input)) {

                    if(!flag) {
                        time++;
                        flag = true;
                    }

                    input[current.x+1][current.y] = 2;
                    Cell newCell = new Cell(current.x+1,current.y);
                    queue.enqueue(newCell);

                }

                if(isValid(current.x,current.y+1,input)) {

                    if(!flag) {
                        time++;
                        flag = true;
                    }

                    input[current.x][current.y+1] = 2;
                    Cell newCell = new Cell(current.x,current.y+1);
                    queue.enqueue(newCell);

                }

                if(isValid(current.x-1,current.y,input)) {

                    if(!flag) {
                        time++;
                        flag = true;
                    }

                    input[current.x-1][current.y] = 2;
                    Cell newCell = new Cell(current.x-1,current.y);
                    queue.enqueue(newCell);

                }

                if(isValid(current.x,current.y-1,input)) {

                    if(!flag) {
                        time++;
                        flag = true;
                    }

                    input[current.x][current.y-1] = 2;
                    Cell newCell = new Cell(current.x,current.y-1);
                    queue.enqueue(newCell);

                }

                current = queue.dequeue();

            }

            if(current.equals(delimiter) && queue.getSize()!=0) {
                queue.enqueue(delimiter);
            }

            flag = false;


        }

        return time;

    }

    private boolean isValid(int x,int y,int[][] input) {
        return !(x < 0 || y < 0 || x >= ROW || y >= COL || input[x][y] != 1);
    }

}

