/*

Given weights and values of n items, put these items in a knapsack of capacity W to get the maximum total value in the knapsack.
In other words, given two integer arrays val[0..n-1] and wt[0..n-1] which represent values and weights associated with n items respectively.
Also given an integer W which represents knapsack capacity, find out the maximum value subset of val[] such that sum of the weights of this
subset is smaller than or equal to W. You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).

 */
package dyanamicprogramming;

/**
 * Created by poorvank on 11/12/16.
 */
public class Knapsack {

    public static void main(String[] args) {

        int[] wt = new int[]{1,3,4,7};
        int[] val = new int[]{1,4,5,7};
        int maxWeight = 7;
        int n = wt.length;

        System.out.println(maxvalueDP(val,wt,n,maxWeight));

    }

    public static int maxValueRecursive(int[] val,int[] wt,int n,int maxWeight) {

        if(n==0 || maxWeight==0) {
            return 0;
        }

        /*
            If weight of the nth item is more than dpTablenapsacdpTable capacity W, then
            this item cannot be included in the optimal solution
         */
        if(wt[n-1]>maxWeight) {
            return maxValueRecursive(val,wt,n-1,maxWeight);
        }

        // Return the maximum of two cases:
        // (1) nth item included
        // (2) not included
        return Math.max((val[n-1]+maxValueRecursive(val,wt,n-1,maxWeight-wt[n-1])),maxValueRecursive(val,wt,n-1,maxWeight));

    }


    public static int maxvalueDP(int[] val,int[] wt,int n,int maxWeight) {

        int i, w;
        int dpTable[][] = new int[n+1][maxWeight+1];


        for (i = 0; i <= n; i++) {
            for (w = 0; w <= maxWeight; w++) {
                if (i == 0 || w == 0)
                    dpTable[i][w] = 0;
                else if (wt[i - 1] <= w)
                    dpTable[i][w] = Math.max(val[i - 1] + dpTable[i - 1][w - wt[i - 1]], dpTable[i - 1][w]);
                else
                    dpTable[i][w] = dpTable[i - 1][w];
            }
        }

        return dpTable[n][maxWeight];

    }

}

/*

1) Optimal Substructure:
To consider all subsets of items, there can be two cases for every item: (1) the item is included in the optimal subset,
 (2) not included in the optimal set.
Therefore, the maximum value that can be obtained from n items is max of following two values.
1) Maximum value obtained by n-1 items and W weight (excluding nth item).
2) Value of nth item plus maximum value obtained by n-1 items and W minus weight of the nth item (including nth item).

If weight of nth item is greater than W, then the nth item cannot be included and case 1 is the only possibility.


It should be noted that the above function computes the same subproblems again and again. See the following recursion tree,
K(1, 1) is being evaluated twice. Time complexity of this naive recursive solution is exponential (2^n).

In the following recursion tree, K() refers to knapSack().  The two
parameters indicated in the following recursion tree are n and W.
The recursion tree is for following sample inputs.
wt[] = {1, 1, 1}, W = 2, val[] = {10, 20, 30}

                       K(3, 2)         ---------> K(n, W)
                   /            \
                 /                \
            K(2,2)                  K(2,1)
          /       \                  /    \
        /           \              /        \
       K(1,2)      K(1,1)        K(1,1)     K(1,0)
       /  \         /   \          /   \
     /      \     /       \      /       \
K(0,2)  K(0,1)  K(0,1)  K(0,0)  K(0,1)   K(0,0)
Recursion tree for Knapsack capacity 2 units and 3 items of 1 unit weight.

 */
