/*

Highway Billboard Problem
Consider a highway of M miles. The task is to place billboards on the highway such that revenue is maximized.
The possible sites for billboards are given by number x1 < x2 < ….. < xn-1 < xn, specifying positions in miles measured from one end of the road.
If we place a billboard at position xi, we receive a revenue of ri > 0. There is a restriction that no two billboards can be placed within t miles or less than it.

Note : All possible sites from x1 to xn are in range from 0 to M as need to place billboards on a highway of M miles.

Examples:

Input : M = 20
        x[]       = {6, 7, 12, 13, 14}
        revenue[] = {5, 6, 5,  3,  1}
        t = 5
Output: 10
By placing two billboards at 6 miles and 12
miles will produce the maximum revenue of 10.

Input : M = 15
        x[] = {6, 9, 12, 14}
        revenue[] = {5, 6, 3, 7}
        t = 2
Output : 18

 */
package dyanamicprogramming;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank.b on 04/04/17.
 */
public class HighwayBillBoard {

    static Map<Integer,Integer> map = new HashMap<>();

    public static int maxRevenue(int[] val,int t,int[] x,int M) {


        int[] finalRevenue = new int[M+1];
        for (int i=0;i<x.length;i++) {
            map.put(x[i],val[i]);
        }

        int max = Integer.MIN_VALUE;
        for (int i=0;i<M;i++) {
            //There is a restriction that no two billboards can be placed within t miles or less than it.
            if(i-t>0) {
                finalRevenue[i] = Math.max((map.getOrDefault(i,0) + finalRevenue[i-t-1]),finalRevenue[i-1]);
                if(finalRevenue[i]>max) {
                    max = finalRevenue[i];
                }
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] x =   {6, 7, 12, 13, 14};
        int[] val = {5, 6, 5,  3,  1};
        int t=5;
        int M = 20;
        System.out.println(maxRevenue(val,t,x,M));
    }

}

/*

Let finalRevenue[i], 1 <= i <= M, be the maximum revenue generated from beginning to i miles on the highway. Now for each mile on the highway, 
we need to check whether this mile has the option for any billboard, if not then the maximum revenue generated till that mile would be same as maximum 
revenue generated till one mile before. But if that mile has the option for billboard then we have 2 options:
1. Either we will place the billboard, ignore the billboard in previous t miles, and add the revenue of the billboard placed.
2. Ignore this billboard. So finalRevenue[i] = max(finalRevenue[i-t-1] + revenue[i], finalRevenue[i-1])

 */