/*

Given an unsorted array, find the largest pair sum in it. For example, the largest pair sum in {12, 34, 10, 6, 40} is 74.

 */

package arrays;

/**
 * Created by poorvank on 5/25/15.
 */
public class LargestSumPair {

    public static void main(String[] args) {

        int[] array = new int[]{12, 34, 10, 6, 40};

        int first = Math.max(array[0], array[1]);
        int second = Math.min(array[0], array[1]);

        for (int i = 2; i < array.length; i++) {

            if (array[i] > first) {
                second = first;
                first = array[i];
            } else if (array[i] > second) {
                second = array[i];
            }

        }

        System.out.println("Maximum sum = " + (first + second));

    }

}

/*

1) Initialize both first and second largest
   first = max(arr[0], arr[1])
   second = min(arr[0], arr[1])
2) Loop through remaining elements (from 3rd to end)
   a) If the current element is greater than first, then update first
       and second.
   b) Else if the current element is greater than second then update
    second
3) Return (first + second)

 */