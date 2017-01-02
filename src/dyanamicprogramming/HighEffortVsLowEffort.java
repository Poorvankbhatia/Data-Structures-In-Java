/*

Dynamic Programming | High-effort vs. Low-effort Tasks Problem
You are given n days and for each day (di) you could either perform a high effort tasks (hi) or a low effort tasks (li)
or no task with the constraint that you can choose a high-effort tasks only if you chose no task on the previous day.
Write a program to find the maximum amount of tasks you can perform within these n days.

Examples:

No. of days (n) = 5
Day      L.E.   H.E
1        1       3
2        5       6
3        4       8
4        5       7
5        3       6
Maximum amount of tasks
        = 3 + 5 + 4 + 5 + 3
        = 20


 */
package dyanamicprogramming;

/**
 * Created by poorvank on 30/12/16.
 */
public class HighEffortVsLowEffort {


    private int maxAmountTasks(int[] lowEffort, int[] highEffort, int n) {

        int[] dp = new int[n];

        dp[0] = highEffort[0];
        dp[1] = Math.max(highEffort[1],dp[0]+lowEffort[1]);

        for (int i=2;i<n;i++) {
            dp[i] = Math.max(highEffort[i]+dp[i-2],lowEffort[i]+dp[i-1]);
        }

        return dp[n-1];

    }

    public static void main(String[] args) {
        int n = 5;
        int high[] = {3, 6, 8, 7, 6};
        int low[] = {1, 5, 4, 5, 3};

        System.out.println(new HighEffortVsLowEffort().maxAmountTasks(low,high,n));

    }


}

/*

Optimal Substructure
To find the maximum amount of tasks done till i’th day, we need to compare 2 choices:

Go for high effort tasks on that day, then find the maximum amount of tasks done till (i – 2) th day.
Go for low effort task on that day and find the maximum amount of tasks done till (i – 1) th day.
Let high [1…n] be the input array for high effort task amount on i’th day and low [1…n] be the input
 array for low effort task amount on ith day.
Let max_task (high [], low [], i) be the function that returns maximum amount of task done till ith day,
so it will return max(high[i] + max_task(high, low, (i – 2)), low [i] + max_task (high, low, (i – 1)))

Time Complexity : O(n)

 */