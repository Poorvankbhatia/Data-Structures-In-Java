/*

Connect n ropes with minimum cost
There are given n ropes of different lengths, we need to connect these ropes into one rope. 
The cost to connect two ropes is equal to sum of their lengths. We need to connect the ropes with minimum cost.

For example if we are given 4 ropes of lengths 4, 3, 2 and 6. We can connect the ropes in following ways.
1) First connect ropes of lengths 2 and 3. Now we have three ropes of lengths 4, 6 and 5.
2) Now connect ropes of lengths 4 and 5. Now we have two ropes of lengths 6 and 9.
3) Finally connect the two ropes and all ropes have connected.

Total cost for connecting all ropes is 5 + 9 + 15 = 29. This is the optimized cost for connecting ropes. 
Other ways of connecting ropes would always have same or more cost. For example, if we connect 4 and 6 first
(we get three strings of 3, 2 and 10), then connect 10 and 3 (we get two strings of 13 and 2). 
Finally we connect 13 and 2. Total cost in this way is 10 + 13 + 15 = 38.

 */
package trees.heap;

import utility.priorityQueueClasses.MinPriorityQueue;

/**
 * Created by poorvank on 7/12/15.
 */

public class ConnectNRopes {

    public static void main(String[] args) {

        Integer[] arr = new Integer[]{4, 3, 2, 6};

        MinPriorityQueue<Integer> pq = new MinPriorityQueue<Integer>(arr);
        int currentRopeCount = pq.getSize();

        int cost = 0;

        while (currentRopeCount!=1) {
            int smallest = pq.delMin();
            int secondToSmallest = pq.delMin();
            int newRope = smallest+secondToSmallest;
            pq.insert(newRope);
            cost += newRope;
            currentRopeCount = pq.getSize();
        }

        System.out.println(cost);

    }


}

/*

If we observe the above problem closely, we can notice that the lengths of the ropes which are picked
 first are included more than once in total cost. Therefore, the idea is to connect smallest two ropes
  first and recur for remaining ropes. This approach is similar to Huffman Coding. We put smallest 
  ropes down the tree so that they can be repeated multiple times rather than the longer ropes.

Following is complete algorithm for finding the minimum cost for connecting n ropes.
Let there be n ropes of lengths stored in an array len[0..n-1]
1) Create a min heap and insert all lengths into the min heap.
2) Do following while number of elements in min heap is not one.
……a) Extract the minimum and second minimum from min heap
……b) Add the above two extracted values and insert the added value to the min-heap.
3) Return the value of only left item in min heap.

 */