/*

k largest(or smallest) elements in an array
Question: Write an efficient program for printing k largest elements in an array. 
Elements in array can be in any order.

For example, if given array is [1, 23, 12, 9, 30, 2, 50] and you are asked for the largest 3 
elements i.e., k = 3 then your program should print 50, 30 and 23.

 */

package trees.heap;

import java.util.Arrays;

/**
 * Created by poorvank on 4/22/15.
 */
public class KLargestElements {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 23, 12, 9, 30, 2, 50};
        int k = 3;
        int[] dst = new int[k];

        System.arraycopy(arr, 0, dst, 0, k);
        createMinHeap(dst);

        //System.out.println(Arrays.toString(dst));

        for (int i = k; i < arr.length; i++) {
            if (arr[i] > dst[0]) {
                dst[0] = arr[i];
                restoreDown(dst, 0);
            }
        }

        System.out.println(Arrays.toString(dst));

    }

    private static void createMinHeap(int[] arr) {

        for (int i = arr.length / 2; i >= 0; i--) {
            restoreDown(arr, i);
        }

    }

    private static void restoreDown(int[] arr, int i) {

        int left = (2 * i) + 1;
        int right = (2 * i) + 2;
        int num = arr[i];

        while (right <= arr.length) {

            if (num <= arr[right] && num <= arr[left]) {
                arr[i] = num;
                return;
            } else if (arr[right] < arr[left]) {
                arr[i] = arr[right];
                i = right;
            } else {
                arr[i] = arr[left];
                i = left;
            }

            left = (2 * i) + 1;
            right = (2 * i) + 2;
        }

        if (left == arr.length - 1 && arr[left] < num) {
            arr[i] = arr[left];
            i = left;
        }

        arr[i] = num;

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