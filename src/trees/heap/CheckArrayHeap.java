/*

How to check if a given array represents a Binary Heap?
Given an array, how to check if the given array represents a Binary Max-Heap.

Examples:

Input:  arr[] = {90, 15, 10, 7, 12, 2}
Output: True
The given array represents below tree
       90
     /    \
   15      10
  /  \     /
 7    12  2
The tree follows max-heap property as every
node is greater than all of its descendants.

Input:  arr[] = {9, 15, 10, 7, 12, 11}
Output: False
The given array represents below tree
       9
     /    \
   15      10
  /  \     /
 7    12  11
The tree doesn't follows max-heap property 9 is
smaller than 15 and 10, and 10 is smaller than 11.

 */

package trees.heap;

/**
 * Created by poorvank on 11/06/16.
 */
public class CheckArrayHeap {

    public static void main(String[] args) {
        int[] arr =new int[]{90, 15, 10, 7, 12, 2, 7, 3};

        int n = arr.length;
        boolean isHeap = true;

        for (int i=0;i>=(n-2)/2;i++) {

            if(arr[i] < arr[(2*i)+1]) {
                isHeap = false;
                break;
            }

            if(arr[i] < arr[(2*i)+2]) {
                isHeap = false;
                break;
            }

        }

        if(!isHeap) {
            System.out.println("Nope");
        } else {
            System.out.println("Yes");
        }
    }

}


/*

The last internal node is present at index (n-2)/2 assuming that indexing begins with 0.

 */