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

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by poorvank on 7/3/15.
 */

class MinimumHeap {

    public static List<Integer> heapList = new ArrayList<>();
    public static int heapSize = 0;
    public static int count = 1;

    public static int leftChild(int i) {
        return (2 * i) + 1;
    }

    public static int rightChild(int i) {
        return (2 * i) + 2;
    }

    public static int parent(int i) {
        return (i - 1) / 2;
    }

    public static void replaceMin(int x) {
        heapList.set(0, x);
        restoreDown(0);

    }

    public static void KthLargest(int k) {

        while (true) {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter new element");
            int x = scanner.nextInt();

            if (count < k) {
                heapList.add(x);
                count++;
                heapSize++;
            } else {

                if (count == k) {
                    heapList.add(x);
                    heapSize++;
                    System.out.println(heapList.toString());
                    buildHeap();
                }

                //Check if the next element is >  root value. count at that time should be > k
                if (x > heapList.get(0) && count > k) {
                    replaceMin(x);
                }

                System.out.println(heapList.toString());
                System.out.println(k + " largest element is - " + heapList.get(0));

                count++;

            }

        }

    }


    public static void buildHeap() {

        for (int i = heapList.size() / 2; i >= 0; i--) {
            restoreDown(i);
        }

    }

    private static void restoreDown(int i) {

        int num = heapList.get(i);
        int rIndex = rightChild(i);
        int lIndex = leftChild(i);


        while (rIndex <= heapSize - 1) {
            if (num <= heapList.get(rIndex) && num <= heapList.get(lIndex)) {
                heapList.set(i, num);
                return;
            } else if (heapList.get(rIndex) < heapList.get(lIndex)) {
                heapList.set(i, heapList.get(rIndex));
                i = rIndex;
            } else if (heapList.get(lIndex) < heapList.get(rIndex)) {
                heapList.set(i, heapList.get(lIndex));
                i = lIndex;
            }

            rIndex = rightChild(i);
            lIndex = leftChild(i);
        }

        if (lIndex == heapSize - 1 && heapList.get(lIndex) < num) {
            heapList.set(i, heapList.get(lIndex));
            i = lIndex;
        }

        heapList.set(i, num);
    }

}

public class KthLargestElementStream {

    public static void main(String[] args) {

        int k = 3;
        MinimumHeap.KthLargest(k);
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