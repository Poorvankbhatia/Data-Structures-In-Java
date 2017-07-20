/*

Maximize number of 0s by flipping a subarray
Given a binary array, find the maximum number zeros in an array with one flip of a subarray allowed.
A flip operation switches all 0s to 1s and 1s to 0s.

Examples:

Input :  arr[] = {0, 1, 0, 0, 1, 1, 0}
Output : 6
We can get 6 zeros by flipping the subarray {1, 1}

Input :  arr[] = {0, 0, 0, 1, 0, 1}
Output : 5

 */
package arrays;

/**
 * Created by poorvank on 20/07/17.
 */
public class FlipSubArrayForMaxZeroes {


    public static int maxZeroes(int[] arr) {

        if(arr==null || arr.length==0) {
            return 0;
        }

        int zeroCount = 0;
        int max =0;
        int n = arr.length;
        int currentMax = 0;

        for (int anArr : arr) {

            if (anArr == 0) {
                zeroCount++;
            }

            int val = anArr == 1 ? 1 : -1;

            currentMax = Math.max(val + currentMax, val);
            max = Math.max(currentMax, max);

        }

        max = Math.max(0,max);

        return max+zeroCount;

    }

    public static void main(String[] args) {
        int[] arr = new int[]{0, 1, 0, 0, 1, 1, 0};
        System.out.print(maxZeroes(arr));
    }


}

/*

This problem can be reduced to largest subarray sum problem. The idea is to consider every 0 as -1 and every 1 as 1,
find the sum of largest subarray sum in this modified array. This sum is our required max_diff ( count of 0s – count of 1s in any subarray).
 Finally we return the max_diff plus count of zeros in original array.

 */