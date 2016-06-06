/*

k largest(or smallest) elements in an array
Question: Write an efficient program for printing k largest elements in an array. 
Elements in array can be in any order.

For example, if given array is [1, 23, 12, 9, 30, 2, 50] and you are asked for the largest 3 
elements i.e., k = 3 then your program should print 50, 30 and 23.

 */

package trees.heap;

import utility.Stack;
import utility.priorityQueueClasses.MinPriorityQueue;



/**
 * Created by poorvank on 4/22/15.
 */
@SuppressWarnings("unchecked")
public class KLargestElements {

    public static void main(String[] args) {

        Integer[] inputArray = new Integer[]{100, 23, 12, 9, 30, 2, 50};
        int k = 3;
        Integer[] dest = new Integer[k];
        System.arraycopy(inputArray, 0, dest, 0, k);
        MinPriorityQueue<Integer> pq = new MinPriorityQueue(dest);

        for (int i=k;i<inputArray.length;i++) {

            int element = inputArray[i];

            if(!pq.isEmpty() && pq.min()>element) {
                continue;
            }
            else {
                pq.replaceRoot(element);
            }
            //If number of elements are greater than k, remove one since we need only k
            if(pq.getSize()>k) {
                pq.delMin();
            }

        }

        Stack<Integer> stack = new Stack<>();
        while (!pq.isEmpty()) {
            stack.push(pq.delMin());
        }

        for (Integer item : stack) {
            System.out.print(item + " ");
        }


    }

}


/*


1) Build a Min Heap MH of the first k elements (arr[0] to arr[k-1]) of the given array. O(k)

2) For each element, after the kth element (arr[k] to arr[n-1]), compare it with root of MH.
……a) If the element is greater than the root then make it root and call heapify for MH
……b) Else ignore it.
// The step 2 is O((n-k)*logk)
because every large element is at root position should come down till leaf

3) Finally, MH has k largest elements and root of the MH is the kth largest element.

Time Complexity: O(k + (n-k)Logk) without sorted output. If sorted output is needed then O(k + (n-k)Logk + kLogk)


It is a variation of following method : 

K largest elements from arr[0..n-1]

1) Store the first k elements in a temporary array temp[0..k-1].
2) Find the smallest element in temp[], let the smallest element be min.
3) For each element x in arr[k] to arr[n-1]
If x is greater than the min then remove min from temp[] and insert x.
4) Print final k elements of temp[]

Time Complexity: O((n-k)*k). If we want the output sorted then O((n-k)*k + klogk)



 */