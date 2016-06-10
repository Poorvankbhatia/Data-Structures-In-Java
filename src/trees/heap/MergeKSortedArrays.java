/*

Given k sorted arrays of size n each, merge them and print the sorted output.

Example:

Input:
k = 3, n =  4
arr[][] = { {1, 3, 5, 7},
            {2, 4, 6, 8},
            {0, 9, 10, 11}} ;

Output: 0 1 2 3 4 5 6 7 8 9 10 11

 */

package trees.heap;

import utility.priorityQueueClasses.MinPriorityQueue;

import java.util.Arrays;

/**
 * Created by poorvank on 4/22/15.
 */

public class MergeKSortedArrays {

    private static MinPriorityQueue<MinHeapNode> priorityQueue;

    public MergeKSortedArrays(int noOfArrays) {
        priorityQueue = new MinPriorityQueue<>(noOfArrays);
    }

    private static class MinHeapNode implements Comparable<MinHeapNode> {
        private Integer info;           //value of the element
        private Integer arrayIndex;     //Which array it the above element belongs to
        private Integer nextIndex;      //What is the next Index to be encountered


        @Override
        public int compareTo(MinHeapNode h) {
            if(this.info > h.info) {
                return 1;
            } else if(this.info < h.info) {
                return -1;
            } else {
                return 0;
            }
        }
    }


    public static void main(String[] args) {

        int arr[][] = { {1, 3, 5, 7},
                        {2, 4, 6, 8},
                        {0, 9, 10, 11}} ;

        int row = arr.length;
        int col = arr[0].length;
        MergeKSortedArrays merge = new MergeKSortedArrays(row);

        for (int i=0;i<row;i++) {
            MinHeapNode minHeapNode = new MergeKSortedArrays.MinHeapNode();
            minHeapNode.info = arr[i][0];
            minHeapNode.arrayIndex = i;
            minHeapNode.nextIndex = 1;
            priorityQueue.insert(minHeapNode);
        }

        int[] output = new int[(row*col)];
        for (int i=0;i<output.length;i++) {

            MinHeapNode current = priorityQueue.delMin();
            output[i] = current.info;
            MinHeapNode node = new MinHeapNode();
            if(current.nextIndex < col) {
                node.nextIndex = current.nextIndex + 1;
                node.info = arr[current.arrayIndex][current.nextIndex];
                node.arrayIndex = current.arrayIndex;
            } else {
                node.info = Integer.MAX_VALUE;
                node.arrayIndex = current.arrayIndex;
                node.nextIndex = current.nextIndex + 1;
            }

            priorityQueue.insert(node);

        }

        System.out.println(Arrays.toString(output));

    }

}

/*

A simple solution is to create an output array of size n*k and one by one copy all arrays to it. 
Finally, sort the output array using any O(nLogn) sorting algorithm. This approach takes O(nkLognk) time.

We can merge arrays in O(nk*Logk) time using Min Heap. Following is detailed algorithm.

1. Create an output array of size n*k.
2. Create a min heap of size k and insert 1st element in all the arrays into a the heap
3. Repeat following steps n*k times.
     a) Get minimum element from heap (minimum is always at root) and store it in output array.
     b) Replace heap root with next element from the array from which the element is extracted. 
     If the array doesn’t have any more elements, then replace root with infinite. 
     After replacing the root, heapify the tree.


The main step is 3rd step, the loop runs n*k times. In every iteration of loop, 
we call heapify which takes O(Logk) time. Therefore, the time complexity is O(nk Logk).
 */