/*

Find the minimum cost to reach destination using a train
There are N stations on route of a train. The train goes from station 0 to N-1. 
The ticket cost for all pair of stations (i, j) is given where j is greater than i. 
Find the minimum cost to reach the destination.

 */

package dyanamicProgramming;

/**
 * Created by poorvank on 6/2/15.
 */
public class MinimumDistance {
    
    private static final int INF = Integer.MAX_VALUE;
    
    public static void main(String[] args) {
        
        int[][] cost = new int[][]{{0, 15, 80, 90},
                {INF, 0, 40, 50},
                {INF, INF, 0, 70},
                {INF, INF, INF, 0}};
        
        
        System.out.println(distance(cost));
    }
    
    private static int distance(int[][] cost) {
        
        int n = cost[0].length;
        
        int[] distance = new int[n];
        
        for (int i=0;i<n;i++) {
           distance[i] = INF;   
        }
        distance[0] = 0;
        
        for (int i=0;i<n;i++) {
            for (int j= i+1;j<n;j++) {
                if(distance[j] > distance[i] + cost[i][j]) {
                    distance[j] = distance[i] + cost[i][j];
                }
            }
        }
        
        return distance[n-1];
    }
    
}


/*

The idea in below code is to first calculate min cost for station 1, 
then for station 2, and so on. These costs are stored in an array dist[0...N-1].

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


The minimum cost to reach N-1 from 0 can be recursively written as following:

minCost(0, N-1) = MIN { cost[0][n-1],
                        cost[0][1] + minCost(1, N-1),
                        minCost(0, 2) + minCost(2, N-1),
                        ........,
                        minCost(0, N-2) + cost[N-2][n-1] }

 */