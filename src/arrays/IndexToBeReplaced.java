/*

Find Index of 0 to be replaced with 1 to get longest continuous sequence of 1s in a binary array
Given an array of 0s and 1s, find the position of 0 to be replaced with 1 to get longest continuous sequence of 1s. 
Expected time complexity is O(n) and auxiliary space is O(1).
Example:

Input: 
   arr[] =  {1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1}
Output:
  Index 9
Assuming array index starts from 0, replacing 0 with 1 at index 9 causes
the maximum continuous sequence of 1s.

Input: 
   arr[] =  {1, 1, 1, 1, 0}
Output:
  Index 4

 */

package arrays;

/**
 * Created by poorvank on 7/20/15.
 */
public class IndexToBeReplaced {

    public static void main(String[] args) {

        int[] array = new int[]{1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1};
        System.out.println("Index to be replaced is  - " + findReplaceIndex(array));

    }

    private static int findReplaceIndex(int[] arr) {

        int n = arr.length;
        int prevZero = -1;
        int prevPrevZero = -1;
        int maxCount = 0;
        int replaceIndex = 0;

        for (int current = 0; current < n; current++) {

            if (arr[current] == 0) {

                if (maxCount < current - prevPrevZero) {

                    maxCount = current - prevPrevZero;
                    replaceIndex = prevZero;

                }

                prevPrevZero = prevZero;
                prevZero = current;

            }

        }


        //For last 0
        if (n - prevPrevZero > maxCount) {
            replaceIndex = prevZero;
        }

        return replaceIndex;


    }


}

/*

Using an Efficient Solution, the problem can solved in O(n) time. The idea is to keep track of three indexes, 
current index (curr), previous zero index (prev_zero) and previous to previous zero index (prev_prev_zero). 
Traverse the array,
 if current element is 0, calculate the difference between curr and prev_prev_zero 
 (This difference minus one is the number of 1s around the prev_zero). 
 If the difference between curr and prev_prev_zero is more than maximum so far, then update the maximum. 
 Finally return index of the prev_zero with maximum difference.

 */