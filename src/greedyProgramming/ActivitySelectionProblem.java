/*

 Activity Selection problem as our first example of Greedy algorithms. Following is the problem statement.
You are given n activities with their start and finish times. 
Select the maximum number of activities that can be performed by a single person, 
assuming that a person can only work on a single activity at a time.
Example:

Consider the following 6 activities. 
     start[]  =  {1, 3, 0, 5, 8, 5};
     finish[] =  {2, 4, 6, 7, 9, 9};
The maximum set of activities that can be executed 
by a single person is {0, 1, 3, 4}

 */

package greedyprogramming;

import java.util.Arrays;

/**
 * Created by poorvank on 7/31/15.
 */
public class ActivitySelectionProblem {

    public static void main(String[] args) {

        int[] startTime = new int[]{1, 3, 0, 5, 8, 5};
        int[] finishTime = new int[]{2, 4, 6, 7, 9, 9};

        Arrays.sort(finishTime);

        System.out.print(0 + " ");

        int prev = 0;

        for (int i = 1; i < 6; i++) {

            if (startTime[i] >= finishTime[prev]) {

                System.out.print(i + " ");
                prev = i;
            }

        }

    }

}


/*

The greedy choice is to always pick the next activity whose finish time is least among the 
remaining activities and the start time is more than or equal to the finish time of previously selected activity. 
We can sort the activities according to their finishing time so that we always consider the next activity as minimum 
finishing time activity.

1) Sort the activities according to their finishing time
2) Select the first activity from the sorted array and print it.
3) Do following for remaining activities in the sorted array.
…….a) If the start time of this activity is greater than the finish time of previously selected activity then 
select this activity and print it.


How does Greedy Choice work for Activities sorted according to finish time?
Let the give set of activities be S = {1, 2, 3, ..n} and activities be sorted by finish time. 
The greedy choice is to always pick activity 1. How come the activity 1 always provides one of the optimal solutions.
 We can prove it by showing that if there is another solution B with first activity other than 1, 
 then there is also a solution A of same size with activity 1 as first activity. Let the first activity selected
  by B be k, then there always exist A = {B – {k}} U {1}.(Note that the activities in B are independent and k has 
  smallest finishing time among all. Since k is not 1, finish(k) >= finish(1)).

 */