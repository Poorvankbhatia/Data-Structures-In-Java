/*

Given a knapsack weight W and a set of n items with certain value vali and weight wti,
we need to calculate minimum amount that could make up this quantity exactly.
This is different from classical Knapsack problem, here we are allowed to use unlimited number of instances of an item.

Examples:

Input : W = 100
       val[]  = {1, 30}
       wt[] = {1, 50}
Output : 100
There are many ways to fill knapsack.
1) 2 instances of 50 unit weight item.
2) 100 instances of 1 unit weight item.
3) 1 instance of 50 unit weight item and 50
   instances of 1 unit weight items.
We get maximum value with option 2.

Input : W = 8
       val[] = {10, 40, 50, 70}
       wt[]  = {1, 3, 4, 5}
Output : 110
We get maximum value with one unit of
weight 5 and one unit of weight 3.

 */
package dyanamicprogramming;


/**
 * Created by poorvank on 18/01/17.
 */
public class UnboundedKnapsack {

    public int maxValue(int[] wt,int[] val,int w) {

        int[] dp = new int[w+1];
        int n =  val.length;

        for (int i=0;i<=w;i++) {
            for (int j=0;j<n;j++) {
                if(wt[j]<=i) {
                    dp[i] = Math.max(dp[i],(dp[i-wt[j]]+val[j]));
                }
            }
        }

        return dp[w];

    }

    public static void main(String[] args) {
        int[] wt = new int[]{1, 50};
        int[] val = new int[]{1, 30};
        int weight = 100;

        System.out.println(new UnboundedKnapsack().maxValue(wt,val,weight));
    }

}

/*

Its an unbounded knapsack problem as we can use 1 or more instances of any resource. A simple 1D array, say dp[W+1] can be used
such that dp[i] stores the maximum value which can achieved using all items and i capacity of knapsack. Note that we use
1D array here which is different from classical knapsack where we used 2D array. Here number of items never changes.
We always have all items available.

We can recursively compute dp[] using below formula

dp[i] = 0
dp[i] = max(dp[i], dp[n-wt[j]] + val[j]
                   where j varies from 0
                   to n-1 such that:
                   wt[j] <= i

result = d[W]

 */