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

package Trees;

import java.util.Arrays;

/**
 * Created by poorvank on 4/22/15.
 */

class MinHeapNode {
    
    int element;
    int arrayIndex;
    int nextIndex;
    
}


class MinHeap {
    
    int heapSize;
    MinHeapNode[] minHeapNodes;
    
    public MinHeap(int k) {
        
        heapSize = k;
        minHeapNodes = new MinHeapNode[heapSize];
        
    }
    
    public int[] mergeKArrays(int[][] arr,int k,int n) {
        
        int[] output = new int[n*k];
        
        for (int i=0;i<k;i++) {
            
            minHeapNodes[i] = new MinHeapNode();
            minHeapNodes[i].element = arr[i][0];// Store the first element
            minHeapNodes[i].arrayIndex = i;// index of array
            minHeapNodes[i].nextIndex = 1;// Index of next element to be stored from array
            
        }

        minHeap(minHeapNodes);

        
        for (int count = 0;count<(n*k);count++) {

            MinHeapNode minHeapNode = minHeapNodes[0];
            output[count] = minHeapNode.element;

            if(minHeapNode.nextIndex < n) {
                minHeapNode.element = arr[minHeapNode.arrayIndex][minHeapNode.nextIndex];
                minHeapNode.nextIndex +=1;
            }
            else {
                minHeapNode.element = Integer.MAX_VALUE;
            }

            minHeap(minHeapNodes);
        }
        
        return output;
        
    }
    
    public void minHeap(MinHeapNode[] minHeapNodes) {
        
        for (int i = minHeapNodes.length/2;i>=0;i--)  {
            restoreDown(minHeapNodes,i);
        }
        
    }
    
    private void restoreDown(MinHeapNode[] minHeapNodes,int i) {

        int left = (2*i) + 1;
        int right = (2*i) + 2;
        MinHeapNode num = minHeapNodes[i];
        
        while (right<=minHeapNodes.length) {

            if(num.element <= minHeapNodes[right].element && num.element <= minHeapNodes[left].element) {
                minHeapNodes[i] = num;
                return;
            }
            else if(minHeapNodes[right].element < minHeapNodes[left].element) {
                minHeapNodes[i] = minHeapNodes[right];
                i = right;
            }
            else {
                minHeapNodes[i] = minHeapNodes[left];
                i = left;
            }
            
            left = (2*i) + 1;
            right = (2*i) + 2;
            
        }
        
        if(left == minHeapNodes.length-1 && minHeapNodes[left].element < num.element) {
            minHeapNodes[i] = minHeapNodes[left];
            i = left;
        }

        minHeapNodes[i] = num;
    }
    
}





public class MergeKSortedArrays {
    
    public static void main(String[] args) {
        
        int[][] arr = new int[][]{{2, 6, 12, 34},
                {1, 9, 20, 1000},
                {23, 34, 90, 2000}};
        
        int k = arr.length;
        int n = arr[0].length;
        
        MinHeap minHeap = new MinHeap(k);
        System.out.println(Arrays.toString(minHeap.mergeKArrays(arr, k, n)));
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

 */