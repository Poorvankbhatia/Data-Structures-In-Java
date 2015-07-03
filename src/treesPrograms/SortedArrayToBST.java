/*

Sorted Array to Balanced BST
Given a sorted array. Write a function that creates a Balanced Binary Search Tree using array elements.

Examples:

Input:  Array {1, 2, 3}
Output: A Balanced BST
     2
   /  \
  1    3

Input: Array {1, 2, 3, 4}
Output: A Balanced BST
      3
    /  \
   2    4
 /
1

 */

package treesPrograms;

/**
 * Created by poorvank on 6/16/15.
 */
public class SortedArrayToBST {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        Traversal.in_Order(convert(arr, 0, arr.length - 1));

    }

    private static Node convert(int[] arr, int start, int end) {

        if (start > end) {
            return null;
        }

        int mid = (start + end) / 2;

        Node root = new Node(arr[mid]);
        root.left = convert(arr, start, mid - 1);
        root.right = convert(arr, mid + 1, end);

        return root;

    }

}


/*

Constructing from sorted array in O(n) time is simpler as we can get the middle element 
in O(1) time. Following is a simple algorithm where we 
first find the middle node of list and make it root of the tree to be constructed.

1) Get the Middle of the array and make it root.
2) Recursively do same for left half and right half.
      a) Get the middle of left half and make it left child of the root
          created in step 1.
      b) Get the middle of right half and make it right child of the
          root created in step 1.

 */