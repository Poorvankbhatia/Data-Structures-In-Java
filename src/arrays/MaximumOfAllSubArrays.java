/*

Maximum of all subarrays of size k (Added a O(n) method)
Given an array and an integer k, find the maximum for each and every contiguous subarray of size k.

maximum number in sliding window

Examples:

Input :
arr[] = {1, 2, 3, 1, 4, 5, 2, 3, 6}
k = 3
Output :
3 3 4 5 5 5 6

Input :
arr[] = {8, 5, 10, 7, 9, 4, 15, 12, 90, 13}
k = 4
Output :
10 10 10 15 15 90 90



 */

package arrays;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by poorvank on 7/27/15.
 */
public class MaximumOfAllSubArrays {

    public static void main(String[] args) {

        int[] array = new int[]{12, 1, 78, 90, 57, 89, 56};
        int n = array.length;

        //A dequeue of indexes of array
        Deque<Integer> deque = new LinkedList<>();

        int k = 3;

        int i = 0;

        while (i < k) {

            while (!deque.isEmpty() && array[i] >= array[deque.getLast()]) {
                deque.removeLast();
            }

            deque.addLast(i);

            ++i;

        }

        while (i < n) {

            System.out.print(array[deque.getFirst()] + " ");

            //To remove the elements which are out of the window
            while (!deque.isEmpty() && deque.getFirst() <= i - k) {
                deque.removeFirst();
            }

            while (!deque.isEmpty() && array[i] >= array[deque.getLast()]) {
                deque.removeLast();
            }

            deque.addLast(i);

            i++;

        }

        System.out.print(array[deque.getFirst()]);

    }

}

/*

We create a Dequeue, Qi of capacity k, that stores only useful elements of current window of k elements. 
An element is useful if it is in current window and is greater than all other elements on left side of it in 
current window.
 We process all array elements one by one and maintain Qi to contain useful elements of current window and these
  useful 
 elements are maintained in sorted order. The element at front of the Qi is the largest and element at rear of Qi is 
 the smallest of current window.
 
 
 Time Complexity: O(n). It seems more than O(n) at first look. 
 If we take a closer look, we can observe that every element of array is added and removed at most once. 
 So there are total 2n operations.
Auxiliary Space: O(k)

 */
