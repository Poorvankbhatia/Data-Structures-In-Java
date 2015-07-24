package treesPrograms;/*

Print encoding for an Array.
    Rules: consider BST made from given array. Let say number x is present in the BST and to reach x, 
    If you go right print 1, if left then 0.
    Now you are given an index i in the array A (so x = A[i]) and print the encoding without constructing BST to reach x
    and without space with least time complexity.

 */

/**
 * Created by poorvank on 6/6/15.
 */
public class PrintArrayEncoding {

    public static void main(String[] args) {

        int[] array = new int[]{50, 30, 10, 20, 70, 60, 55, 63, 80};
        int searchNumber = 63;
        int min = Integer.MIN_VALUE;
        int max = Integer.MAX_VALUE;

        for (int i = 0; i < array.length; i++) {

            if (array[i] < max && array[i] > min) {

                if (array[i] < searchNumber) {
                    System.out.print(1 + " ");
                    min = array[i];
                } else if (array[i] > searchNumber) {
                    System.out.print(0 + " ");
                    max = array[i];
                } else {
                    break;
                }

            }

        }

    }

}


/*

        ALGORITHM
        
       1. Scan array from left to right.
      2. Keep 2 variables min = INT_MIN and max = INT_MAX
                min and max are used to keep track of whether the subtree of current node will store desired number 
                searchNumber.
      3. If value of current elements is between min and max, then subtree of this node will contain searchNumber.
      4. If search number is less than current element, then update min = currentElement and print 1  since it will 
      take right path.
      5. If search number is greater than current element then update max = currentElement and print 0, 
      since it will take left path.


        */
