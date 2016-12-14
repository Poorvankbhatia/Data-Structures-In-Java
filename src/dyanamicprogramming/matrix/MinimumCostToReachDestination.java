/*

There are N stations on route of a train. The train goes from station 0 to N-1.
The ticket cost for all pair of stations (i, j) is given where j is greater than i. 
Find the minimum cost to reach the destination.

Consider the following example:

Input: 
cost[N][N] = { {0, 15, 80, 90},
              {INF, 0, 40, 50},
              {INF, INF, 0, 70},
              {INF, INF, INF, 0}
             };
There are 4 stations and cost[i][j] indicates cost to reach j 
from i. The entries where j < i are meaningless.

Output:
The minimum cost is 65
The minimum cost can be obtained by first going to station 1 
from 0. Then from station 1 to station 3.

 */

package dyanamicprogramming.matrix;

/**
 * Created by poorvank on 7/18/15.
 */
public class MinimumCostToReachDestination {

    private static final int INF = Integer.MAX_VALUE, N = 4;

    public static void main(String[] args) {

        int[][] cost = new int[][]{{0, 15, 80, 90},
                {INF, 0, 40, 50},
                {INF, INF, 0, 70},
                {INF, INF, INF, 0}};


        int[] distance = new int[N];

        for (int i = 0; i < N; i++)
            distance[i] = INF;

        distance[0] = 0;

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {

                if (distance[j] > distance[i] + cost[i][j]) {
                    distance[j] = distance[i] + cost[i][j];
                }

            }
        }

        System.out.print("Minimum distance is - " + distance[N - 1]);
    }

}


/*

The idea in below code is to first calculate min cost for station 1, then for station 2, and so on. 
These costs are stored in an array dist[0...N-1].

1) The min cost for station 0 is 0, i.e., dist[0] = 0

2) The min cost for station 1 is cost[0][1], i.e., dist[1] = cost[0][1]

3) The min cost for station 2 is minimum of following two.
     a) dist[0] + cost[0][2]
     b) dist[1] + cost[1][2]

3) The min cost for station 3 is minimum of following three.
     a) dist[0] + cost[0][3]
     b) dist[1] + cost[1][3]
     c) dist[2] + cost[2][3]

Similarly, dist[4], dist[5], ... dist[N-1] are calculated.


minCost(0, N-1) = MIN { cost[0][n-1],
                        cost[0][1] + minCost(1, N-1),
                        minCost(0, 2) + minCost(2, N-1),
                        ........,
                        minCost(0, N-2) + cost[N-2][n-1] } 

 */