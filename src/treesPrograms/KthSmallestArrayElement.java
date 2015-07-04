/*

K’th Smallest/Largest Element in Unsorted Array | Set 1
Given an array and a number k where k is smaller than size of array,
we need to find the k’th smallest element in the given array. It is given that ll array elements are distinct.

Examples:

Input: arr[] = {7, 10, 4, 3, 20, 15}
       k = 3
Output: 7

Input: arr[] = {7, 10, 4, 3, 20, 15}
       k = 4
Output: 10

 */

package treesPrograms;

/**
 * Created by poorvank on 7/3/15.
 */
public class KthSmallestArrayElement {

    private static int heapSize = 0;

    public static void main(String[] args) {

        int[] arr = new int[]{4, 7, 10};

        int k = 1;


        if (k > arr.length || k <= 0) {
            System.out.println("Not possible");
        } else {
            System.out.println(KthElement(arr, k));
        }


    }

    private static int KthElement(int[] arr, int k) {

        buildMinHeap(arr);

        heapSize = arr.length;
        for (int i = 1; i < k; i++) {
            extractMin(arr);
            heapSize--;
        }

        return arr[0];

    }

    private static void extractMin(int[] arr) {

        int root = arr[0];

        if (arr.length > 1) {
            arr[0] = arr[heapSize - 1];
            restoreDown(arr, 0, heapSize);
        }

    }

    private static void buildMinHeap(int[] arr) {

        for (int i = arr.length / 2; i >= 0; i--) {
            restoreDown(arr, i, arr.length);
        }

    }

    private static void restoreDown(int[] arr, int i, int length) {

        int leftChild = (2 * i) + 1;
        int rightChild = (2 * i) + 2;
        int num = arr[i];

        while (rightChild <= length - 1) {

            if (num <= arr[leftChild] && num <= arr[rightChild]) {
                arr[i] = num;
                return;
            } else if (arr[leftChild] < arr[rightChild]) {
                arr[i] = arr[leftChild];
                i = leftChild;
            } else if (arr[rightChild] < arr[leftChild]) {
                arr[i] = arr[rightChild];
                i = rightChild;
            }

            leftChild = (2 * i) + 1;
            rightChild = (2 * i) + 2;

        }
        
         /*
        
        When odd nodes all nodes will have 2 || 0 children
        In case of even nodes there is only 1 node with 1 left child
         */

        if (leftChild == length - 1 && arr[leftChild] < num) {
            arr[i] = arr[leftChild];
            i = leftChild;
        }

        arr[i] = num;

    }

}


/*

We can find k’th smallest element in time complexity better than O(nLogn).
A simple optimization is to create a Min Heap of the given n elements and call extractMin() k times.

 */