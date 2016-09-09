/*

K’th Smallest/Largest Element in Unsorted Array | Set 1
Given an array and a number k where k is smaller than size of array,
we need to find the k’th smallest element in the given array. It is given that ll array elements are distinct.

Examples:

Input: arr[] = {7, 10, 4, 3, 20, 15}
       k = 3
Output: 7

Input: arr[] = {7, 10, 4, 3, 20, 15}
       k = 4
Output: 10

 */

package trees.heap;

import utility.priorityQueueClasses.MinPriorityQueue;

/**
 * Created by poorvank on 7/3/15.
 */
public class KthSmallestArrayElement {

    public static void main(String[] args) {

        Integer[] arr = new Integer[]{4, 7, 10};

        int k = 1;

        MinPriorityQueue<Integer> pq = new MinPriorityQueue<>(arr);

        int kthSmallestElement = -1;

        while (k!=0) {
            if(!pq.isEmpty()) {
                kthSmallestElement = pq.delMin();
            }
            k--;
        }

        System.out.println(" smallest element is = " + kthSmallestElement);


    }

}


/*

We can find k’th smallest element in time complexity better than O(nLogn).
A simple optimization is to create a Min Heap of the given n elements and call extractMin() k times.

 */