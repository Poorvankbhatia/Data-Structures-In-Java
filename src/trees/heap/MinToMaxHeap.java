package trees.heap;

import utility.priorityQueueClasses.MaxPriorityQueue;

/**
 * Created by poorvank on 11/06/16.
 */
public class MinToMaxHeap {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{3,5,9,6,8,20,10,12,18,9};
        MaxPriorityQueue<Integer> pq = new MaxPriorityQueue<>(arr);
        for (Integer element : pq) {
            System.out.print(element + " ");
        }
    }

}
