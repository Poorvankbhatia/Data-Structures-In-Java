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