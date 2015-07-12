package treesPrograms.heap;

import java.util.Arrays;

/**
 * Created by poorvank on 4/21/15.
 */
public class HeapImplementation {

    public static void main(String[] args) {

        int arr[] = new int[]{Integer.MAX_VALUE, 25, 35, 18, 9, 46, 70, 48, 23, 78, 12, 95};
        maxHeap(arr);
        System.out.println("Maximum element is - " + arr[1] + " array is - " + Arrays.toString(arr));
        minHeap(arr);
        System.out.println("Minimum element is - " + arr[1] + " array is - " + Arrays.toString(arr));

    }

    //using restoreUp
    private static void maxHeap(int[] arr) {

        for (int i = 2; i < arr.length; i++) {
            restoreUp(arr, i);
        }

    }

    private static void restoreUp(int[] arr, int i) {
        /*
         O(nlogn) in worst case
         */
        int k = arr[i];
        int parentPosition = i / 2;

        while (arr[parentPosition] < k) {

            arr[i] = arr[parentPosition];
            i = parentPosition;
            parentPosition = i / 2;

        }

        arr[i] = k;

    }

    private static void minHeap(int[] arr) {
        /*
        
        The first non leaf node is - floor(size/2)
        Bottom up manner . Each node is a heap of size 1. 
        O(n)
         */
        for (int i = arr.length / 2; i >= 1; i--) {
            restoreDown(arr, i);
        }

    }

    private static void restoreDown(int[] arr, int i) {

        int leftChild = 2 * i;
        int rightChild = (2 * i) + 1;
        int num = arr[i];

        while (rightChild <= arr.length) {

            if (num <= arr[leftChild] && num <= arr[rightChild]) {
                arr[i] = num;
                return;
            } else if (arr[leftChild] < arr[rightChild]) {
                arr[i] = arr[leftChild];
                i = leftChild;
            } else {
                arr[i] = arr[rightChild];
                i = rightChild;
            }

            leftChild = 2 * i;
            rightChild = (2 * i) + 1;
        }
        /*
        
        When odd nodes all nodes will have 2 || 0 children
        In case of even nodes there is only 1 node with 1 left child
         */
        if (leftChild == arr.length - 1 && arr[leftChild] < num) {
            arr[i] = arr[leftChild];
            i = leftChild;
        }

        arr[i] = num;

    }

}
