package dyanamicprogramming;

/**
 * Created by poorvank on 5/19/15.
 */
public class KadanesAlgorithm {

    public static void main(String[] args) {

        int[] array = new int[]{-2, -3, 4, -1, -2, 1, 5, -3};
        DPMethod1(array);
        int[] array2 = {-1, 3, -5, 4, 6, -1, 2, -7, 13, -3};
        DPMethod1(array2);
        int[] array3 = {-6, -2, -3, -4, -1, -5, -5};
        DPMethod1(array3);//Works For Negative Numbers

    }

    private static void DPMethod1(int[] array) {

        int maxStartIndex = 0;
        int cumulativeSum = 0;
        int maxEndIndex = 0;
        int maxSum = Integer.MIN_VALUE;
        int maxIndexUntilNow = 0;

        for (int i = 0; i < array.length; i++) {

            cumulativeSum += array[i];

            if (maxSum < cumulativeSum) {
                maxSum = cumulativeSum;
                maxStartIndex = maxIndexUntilNow;
                maxEndIndex = i;
            }
            if (cumulativeSum < 0) {
                maxIndexUntilNow = i + 1;
                cumulativeSum = 0;
            }

        }

        System.out.println("MaxSum = " + maxSum + " start = " + maxStartIndex + " end = " + maxEndIndex);

    }

    private static void DPMethod2(int[] array) {

        int maxSoFar = 0;
        int maxEndingHere = 0;
        int start = 0;
        int end = 0;
        int maxUntilNow = 0;

        for (int i = 0; i < array.length; i++) {

            maxSoFar += array[i];

            if (maxSoFar < 0) {
                maxUntilNow = i + 1;
                maxSoFar = 0;
            } else if (maxEndingHere < maxSoFar) {
                maxEndingHere = maxSoFar;
                start = maxUntilNow;
                end = i;
            }

        }

        System.out.println("MaxSum = " + maxEndingHere + " start = " + start + " end = " + end);

    }

    private static void DPMethod3(int[] array) {

        int currentSum = array[0];
        int maxSum = array[0];

        for (int i = 1; i < array.length; i++) {
            currentSum = Math.max(array[i], currentSum + array[i]);
            maxSum = Math.max(currentSum, maxSum);
        }

        System.out.println("Max sum is = " + maxSum);

    }

}
