/*

Given an unsorted array with repetitions, the task is to group multiple occurrence of individual elements. 
The grouping should happen in a way that the order of first occurrences of all elements is maintained.

Examples:

Input: arr[] = {5, 3, 5, 1, 3, 3}
Output:        {5, 5, 3, 3, 3, 1}

Input: arr[] = {4, 6, 9, 2, 3, 4, 9, 6, 10, 4}
Output:        {4, 4, 4, 6, 6, 9, 9, 2, 3, 10}

 */

package arrays;

import java.util.HashMap;

/**
 * Created by poorvank on 6/5/15.
 */
public class GroupMultipleOccurrence {

    public static void main(String[] args) {

        int[] arr = new int[]{4, 6, 9, 2, 3, 4, 9, 6, 10, 4};

        HashMap<Integer, Integer> countMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {

            if (countMap.containsKey(arr[i])) {
                countMap.put(arr[i], countMap.get(arr[i]) + 1);
            } else {
                countMap.put(arr[i], 1);
            }

        }

        for (int i = 0; i < arr.length; i++) {

            Integer count = countMap.get(arr[i]);

            if (count != null) {
                for (int j = 0; j < count; j++) {
                    System.out.print(arr[i] + " ");
                }

                //Remove so that it is not encountered 2nd time
                countMap.remove(arr[i]);

            }

        }

    }


}


/*

Another method : 

Binary Search Tree based Method: The time complexity can be improved to O(nLogn) 
using self-balancing binary search tree like Red-Black Tree or AVL tree. Following is complete algorithm.
1) Create an empty Binary Search Tree (BST). Every BST node is going to contain an array element and its count.
2) Traverse the input array and do following for every element.
……..a) If element is not present in BST, then insert it with count as 0.
……..b) If element is present, then increment count in corresponding BST node.
3) Traverse the array again and do following for every element.
…….. If element is present in BST, then do following
……….a) Get its count and print the element ‘count’ times.
……….b) Delete the element from BST.



 */