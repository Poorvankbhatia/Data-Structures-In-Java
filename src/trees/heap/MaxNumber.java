/*

Given two arrays where digits of one array  represent a number,maximize the number
by replacing it with elements of second array.
eg:
arr = {3,1,4,5,6}
sec = {1,9,5,2,3}
after replacement
arr = {9,5,4,5,6}
one digit of sec can be used to replace only once.

 */

package trees.heap;

import utility.priorityQueueClasses.MaxPriorityQueue;

import java.util.Arrays;

/**
 * Created by poorvank on 12/19/15.
 */
public class MaxNumber {

    public static void main(String[] args) {

        Integer[] primaryArray = new Integer[]{3, 1, 4, 5, 6};
        Integer[] secArray = new Integer[]{1, 9, 5, 2, 3};
        int length = secArray.length;

        MaxPriorityQueue<Integer> pq = new MaxPriorityQueue<>(secArray);

        for (int i=0;i<length;i++) {
            if(pq.isEmpty()) {
                System.out.println("No more items can be replaced");
                break;
            }
            int maxInteger = pq.getMaximumElement();
            if(maxInteger>primaryArray[i]) {
                primaryArray[i] = pq.delMax();
            }
        }

        System.out.println("New array - " + Arrays.toString(primaryArray));

    }

}

/*

construct a getMaximumElement heap of sec array.And ran a loop checking if the getMaximumElement element of sec was greater than the element
in arr and then  replaced it in arr and deleted the same from the maxheap.

 */