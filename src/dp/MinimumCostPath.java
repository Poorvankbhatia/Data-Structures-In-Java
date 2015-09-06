package dp;

/**
 * Created by poorvank on 8/10/15.
 */
public class MinimumCostPath {

    private static final int ROW = 3;
    private static final int COL = 3;

    public static void main(String[] args) {

        int[][] cost = new int[][]{{1, 2, 3},
                {4, 8, 2},
                {1, 5, 3}};

        System.out.println(minCostRecursive(cost, 2, 2));
        System.out.println(minCostDP(cost, 2, 2));

    }

    private static int minCostRecursive(int[][] cost, int row, int col) {

        if (row < 0 || col < 0 || row >= ROW || col >= COL) {
            return Integer.MAX_VALUE;
        }

        if (row == 0 && col == 0) {
            return cost[row][col];
        }

        return cost[row][col] + minOfThree(cost[row][col - 1], cost[row - 1][col - 1], cost[row - 1][col - 1]);

    }

    private static int minCostDP(int[][] cost, int row, int col) {

        if (row < 0 || col < 0 || row >= ROW || col >= COL) {
            return Integer.MAX_VALUE;
        }

        int table[][] = new int[ROW][COL];

        table[0][0] = cost[0][0];

        for (int i = 1; i < ROW; i++) {
            table[i][0] = table[i - 1][0] + cost[i][0];
        }

        for (int j = 1; j < COL; j++) {
            table[0][j] = table[0][j - 1] + cost[0][j];
        }

        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                table[i][j] = cost[i][j] + minOfThree(table[i - 1][j], table[i - 1][j - 1], table[i][j - 1]);
            }
        }

        return table[row][col];
    }

    private static int minOfThree(int a, int b, int c) {

        if (a < b) {
            return c < a ? c : a;
        } else {
            return c < b ? c : b;
        }

    }

}


/*

The path to reach (m, n) must be through one of the 3 cells: (m-1, n-1) or (m-1, n) or (m, n-1). 
So minimum cost to reach (m, n) can be written as “minimum of the 3 cells plus cost[m][n]”.

minCost(m, n) = min (minCost(m-1, n-1), minCost(m-1, n), minCost(m, n-1)) + cost[m][n]


It should be noted that the above function computes the same subproblems again and again. 
See the following recursion tree, there are many nodes which apear more than once. 
Time complexity of this naive recursive solution is exponential and it is terribly slow.

mC refers to minCost()
                                    mC(2, 2)
                          /            |           \
                         /             |            \             
                 mC(1, 1)           mC(1, 2)             mC(2, 1)
              /     |     \       /     |     \           /     |     \ 
             /      |      \     /      |      \         /      |       \
       mC(0,0) mC(0,1) mC(1,0) mC(0,1) mC(0,2) mC(1,1) mC(1,0) mC(1,1) mC(2,0) 

 */