/*

Given array, find all possible sets of elements which add up to a given integer K.`

 */

package arrays;

import java.util.Stack;

/**
 * Created by poorvank on 6/6/15.
 */

class SubsetSum {

    private Stack<Integer> stack = new Stack<Integer>();
    private int stackSum = 0;

    public void checkSum(int sum, int startIndex, int endIndex, int[] arr) {

        if (stackSum == sum) {
            printStack(sum);
            return;
        }

        for (int i = startIndex; i < endIndex; i++) {

            if (stackSum + arr[i] <= sum) {

                stackSum += arr[i];
                stack.push(arr[i]);

                checkSum(sum, startIndex + 1, endIndex, arr);
                stackSum -= stack.pop();

            }

        }

    }

    private void printStack(int sum) {

        StringBuilder sb = new StringBuilder();
        sb.append(sum).append(" = ");
        for (Integer i : stack) {
            sb.append(i).append("+");
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1).toString());

    }


}

public class GenerateSubSets {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 3, 4, 5, 6, 2, 7, 8, 9, 10, 11, 13, 14, 15};
        int sum = 15;
        SubsetSum subsetSum = new SubsetSum();
        subsetSum.checkSum(sum, 0, arr.length - 1, arr);

    }


}
