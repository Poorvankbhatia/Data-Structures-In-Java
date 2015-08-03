/*

Maximum sum such that no two elements are adjacent
Question: Given an array of positive numbers, find the maximum sum of a subsequence with
 the constraint that no 2 numbers in the sequence should be adjacent in the array. So 3 2 7 10 
 should return 13 (sum of 3 and 10) or 3 2 5 10 7 should return 15 (sum of 3, 5 and 7).

 */

package miscellaneous;

/**
 * Created by poorvank on 7/12/15.
 */
public class MaxSumNonContinuous {

    public static void main(String[] args) {

        int[] posArr = new int[]{5, 5, 10, 40, 50, 35};
        System.out.println(positiveArraySolution(posArr));

        int[] negArr = new int[]{4, 5, 17, 3, 12, 29, 0, -25, 25, 28, 16, 16, 16, 11, -10};
        System.out.println(dpSolution(negArr));

    }

    private static int positiveArraySolution(int[] arr) {
    
        /*
        
        Algorithm
        
        Loop for all elements in arr[] and maintain two sums incl and excl where incl = Max sum including the 
        previous element and excl = Max sum excluding the previous element.
        Max sum excluding the current element will be max(incl, excl) and max sum including the current 
        element will be excl + current element (Note that only excl is considered because elements cannot be adjacent).
        At the end of the loop return max of incl and excl.
        
         DRY RUN : 
         
          arr[] = {5,  5, 10, 40, 50, 35}

          inc = 5
          exc = 0

          For i = 1 (current element is 5)
          incl =  (excl + arr[i])  = 5
          excl =  max(5, 0) = 5

          For i = 2 (current element is 10)
          incl =  (excl + arr[i]) = 15
          excl =  max(5, 5) = 5

          For i = 3 (current element is 40)
          incl = (excl + arr[i]) = 45
          excl = max(5, 15) = 15

          For i = 4 (current element is 50)
          incl = (excl + arr[i]) = 65
          excl =  max(45, 15) = 45

          For i = 5 (current element is 35)
          incl =  (excl + arr[i]) = 80
          excl = max(5, 15) = 65

          And 35 is the last element. So, answer is max(incl, excl) =  80
        
        
         */

        int includePrev = arr[0];
        int excludePrev = 0;

        for (int i = 1; i < arr.length; i++) {

            int excludeNew = Math.max(includePrev, excludePrev);
            includePrev = arr[i] + excludePrev;
            excludePrev = excludeNew;

        }

        return Math.max(includePrev, excludePrev);

    }

    private static int dpSolution(int[] arr) {
        
        /*
        m(i) = max (m(i-1) , m(i-2) + arr[i])

         */

        int[] maxSum = new int[arr.length];
        maxSum[0] = arr[0];
        maxSum[1] = Math.max(arr[0], arr[1]);

        int max = maxSum[1];

        for (int i = 2; i < arr.length; i++) {
            maxSum[i] = Math.max(maxSum[i - 1], maxSum[i - 2] + arr[i]);
            if (maxSum[i] > max) {
                max = maxSum[i];
            }
        }

        return max;
    }

}
