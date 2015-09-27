/*

Equilibrium index of an array
Equilibrium index of an array is an index such that the sum of elements at 
lower indexes is equal to the sum of elements at higher indexes. For example, in an arrya A:

A[0] = -7, A[1] = 1, A[2] = 5, A[3] = 2, A[4] = -4, A[5] = 3, A[6]=0

3 is an equilibrium index, because:
A[0] + A[1] + A[2] = A[4] + A[5] + A[6]

6 is also an equilibrium index, because sum of zero elements is zero, i.e., A[0] + A[1] + A[2] + A[3] + A[4] + A[5]=0

7 is not an equilibrium index, because it is not a valid index of array A.

 */


package arrays;

import java.util.Arrays;

/**
 * Created by poorvank on 6/6/15.
 */
public class EquilibriumIndex {

    public static void main(String[] args) {

        int[] array = new int[]{-7, 1, 5, 2, -4, 3, 0};
        method2(array);
        method1(array);

    }

    private static void method1(int[] array) {

        System.out.println("Using Extra Space");

        int n = array.length;

        int[] leftSum = new int[n];
        leftSum[0] = 0;
        int[] rightSum = new int[n];
        rightSum[n - 1] = 0;

        for (int i = 1; i < n; i++) {
            leftSum[i] = leftSum[i - 1] + array[i - 1];
        }

        for (int i = n - 2; i >= 0; i--) {
            rightSum[i] = rightSum[i + 1] + array[i + 1];
        }

        System.out.println(Arrays.toString(leftSum));
        System.out.println(Arrays.toString(rightSum));

        for (int i = 0; i < n; i++) {
            if (leftSum[i] == rightSum[i]) {
                System.out.println("Equilibrium Index found at index - " + i);
            }
        }


    }

    private static void method2(int[] array) {

        System.out.println("Without using Extra Space");

        int sum = 0, leftSum = 0;
        int n = array.length;

        for (int i = 0; i < n; i++) {
            sum += array[i];
        }

        for (int i = 0; i < n; i++) {

            sum = sum - array[i]; // sum is now right sum for index i

            if (sum == leftSum) {
                System.out.println("Equilibrium Index found at index - " + i);
            }

            leftSum += array[i];

        }

    }


}


