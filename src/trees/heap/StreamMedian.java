/*

Median in a stream of integers (running integers)
Given that integers are read from a data stream. Find median of elements read so for in efficient way.
For simplicity assume there are no duplicates. For example, let us consider the stream 5, 15, 1, 3 …

After reading 1st element of stream - 5 -> median - 5
After reading 2nd element of stream - 5, 15 -> median - 10
After reading 3rd element of stream - 5, 15, 1 -> median - 5
After reading 4th element of stream - 5, 15, 1, 3 -> median - 4, so on...
Making it clear, when the input size is odd, we take the middle element of sorted data. If the input size is even,
we pick average of middle two elements in sorted stream.

Note that output is effective median of integers read from the stream so far. Such an algorithm is called online algorithm.
Any algorithm that can guarantee output of i-elements
after processing i-th element, is said to be online algorithm. Let us discuss three solutions for the above problem.

 */

package trees.heap;

import utility.priorityQueueClasses.MaxPriorityQueue;
import utility.priorityQueueClasses.MinPriorityQueue;

/**
 * Created by poorvank on 13/06/16.
 */
public class StreamMedian {

    private MaxPriorityQueue<Integer> maxPriorityQueue;
    private MinPriorityQueue<Integer> minPriorityQueue;
    private int median;

    public StreamMedian() {
        maxPriorityQueue = new MaxPriorityQueue<>();
        minPriorityQueue = new MinPriorityQueue<>();
        median = 0;
    }

    public int getMedian() {
        return median;
    }

    public void calculateMedian(int element) {
        int sizeDifference = maxPriorityQueue.getSize() - minPriorityQueue.getSize();
        if(sizeDifference==1) { //Maxheap has more number of elements
            if(element>median) {
                minPriorityQueue.insert(element);
            } else {
                minPriorityQueue.insert(maxPriorityQueue.delMax());
                maxPriorityQueue.insert(element);
            }
            median = (minPriorityQueue.getMinimumElement() + minPriorityQueue.getMinimumElement()) / 2;
        } else if(sizeDifference==0) { //Equal number of elements
            if(element>median) {
                minPriorityQueue.insert(element);
                median = minPriorityQueue.getMinimumElement();
            } else {
                maxPriorityQueue.insert(element);
                median = maxPriorityQueue.getMaximumElement();
            }
        } else  if(sizeDifference==-1) { //Min heap has more number of elements
            if(element>median) {
                maxPriorityQueue.insert(minPriorityQueue.delMin());
                minPriorityQueue.insert(element);
            } else {
                minPriorityQueue.insert(element);
            }
            median = (minPriorityQueue.getMinimumElement() + maxPriorityQueue.getMaximumElement()) / 2;
        }
    }


    public static void main(String[] args) {

        StreamMedian sm = new StreamMedian();
        Integer[] array = new Integer[]{3, 15, 1,7,4};

        for (Integer element : array) {
            sm.calculateMedian(element);
            System.out.println("Current median in the system = " + sm.getMedian());
        }

    }


}


/*

We will use 2 heaps simultaneously, a max-heap and a min-heap with 2 requirements.
The first requirement is that the max-heap contains the smallest half of the numbers and min-heap contains the largest half.
So, the numbers in max-heap are always less than or equal to the numbers in min-heap. Let’s call this the order requirement.
The second requirement is that, the number of elements in max-heap is either equal to or 1 more than the number of elements in the min-heap.
So, if we received 2N elements (even) up to now, max-heap and min-heap will both contain N elements.
Otherwise, if we have received 2N+1 elements (odd), max-heap will contain N+1 and min-heap N. Let’s call this the size requirement.

In both cases we insert the new element to the max-heap, but perform different actions afterwards.
In the first case, if the total number of elements in the heaps is even before insertion, then there are N elements
both in max-heap and min-heap because of the size requirement. After inserting the new element to the max-heap,
it contains N+1 elements but this doesn’t violate the size requirement. Max-heap can contain 1 more element than min-heap.
In the second case, if the number of elements is odd before insertion, then there are N+1 elements in max-heap and N in min-heap.
After we insert the new element to the max-heap, it contains N+2 elements. But this violates the size constraint,
max-heap can contain at most 1 more element than min-heap. So we pop an element from max-heap and push it to min-heap.

So the max-heap contains the smaller half of the numbers and the min-heap contains the larger half


Note that by design the root of the max-heap is the maximum of the lower half, and root of the min-heap is the minimum of the upper half.
Keeping these in mind, we again take two different actions depending on whether the total number of elements is even or odd before insertion.
 In the even case we just inserted the new element to the max-heap. If the new element is less than all the elements in the min-heap,
 then the order constraint is satisfied and we’re done. We can perform this check by comparing the new element to the root of the min-heap
 in O(1) time since the root of the min-heap is the minimum. But if the new element is larger than the root of min-heap then we should
 exchange those elements to satisfy the order requirement. Note that in this case the root of the max-heap is the new element.
 So we pop the the root of min-heap and insert it to max-heap. Also pop the root of max-heap and insert it to min-heap.
 In second case, where the total number of elements before insertion is odd, we inserted the new element to max-heap,
 then we popped an element and pushed it to the min-heap. To satisfy the order constraint, we pop the maximum element of the max-heap,
 the root, and insert it to the min-heap. Insertion complexity is O(logN), which is the insertion complexity of a heap.

 http://www.ardendertat.com/2011/11/03/programming-interview-questions-13-median-of-integer-stream/

 Time Complexity: If we omit the way how stream was read, complexity of median finding is O(N log N),
 as we need to read the stream, and due to heap insertions/deletions.

 */