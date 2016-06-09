/*

K’th largest element in a stream
Given an infinite stream of integers, find the k’th largest element at any point of time.

Example:

Input:
stream[] = {10, 20, 11, 70, 50, 40, 100, 5, ...}
k = 3

Output:    {_,   _, 10, 11, 20, 40, 50,  50, ...}
Extra space allowed is O(k).

 */

package trees.heap;

import utility.priorityQueueClasses.MinPriorityQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by poorvank on 7/3/15.
 */

public class KthLargestElementStream {


    public static void main(String[] args) {

        int k = 3;

        System.out.println("To mark the end of stream type \"end\" ");
        Scanner scanner = new Scanner(System.in);

        MinPriorityQueue<Integer> minPriorityQueue = new MinPriorityQueue<>(k);

        int count=0;

        while (true) {

            System.out.println("Enter a new element");
            String line = scanner.nextLine();

            if(("end").equals(line)) {
                if(count>k) {
                    System.out.println("Finally Minimum element at position = " + k  + " in stream = " + minPriorityQueue.min());
                }
                break;
            }

            Integer element = Integer.parseInt(line);
            if(count>=k) {
                if(element>minPriorityQueue.min()) {
                    minPriorityQueue.replaceRoot(element);
                }
            } else {
                minPriorityQueue.insert(element);
            }
            count++;

            if(count<k) {
                continue;
            }


            System.out.println("Currently Minimum element at position = " + k  + " in stream = " + minPriorityQueue.min());

        }

    }

}


/*

An Efficient Solution is to use Min Heap of size k to store k largest elements of stream. 
The k’th largest element is always at root and can be found in O(1) time.
How to process a new element of stream?
Compare the new element with root of heap. If new element is smaller, then ignore it. 
Otherwise replace root with new element and call heapify for the root of modified heap. 
Time complexity of finding the k’th largest element is O(Logk).


 */