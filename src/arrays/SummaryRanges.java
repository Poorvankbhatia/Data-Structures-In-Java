/*

Given a sorted integer array without duplicates, return the summary of its ranges for consecutive numbers.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].

 */
package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by poorvank on 7/31/15.
 */
public class SummaryRanges {

    public static void main(String[] args) {

        int[] numbers = new int[]{0, 1, 3, 5, 6, 8};


        int n = numbers.length;

        System.out.println(printSummary(numbers, n));

    }


    private static List<String> printSummary(int[] numbers, int n) {

        List<String> result = new ArrayList<>();

        if (numbers == null || numbers.length == 0) {
            return result;
        }

        if (numbers.length == 1) {
            result.add(numbers[0] + " ");
            return result;
        }

        int first = numbers[0];
        int prev = first;

        for (int i = 1; i < n; i++) {

            if (numbers[i] == prev + 1) {
                if (i == n - 1) {
                    result.add(first + "->" + numbers[i]);
                }
            } else {
                //For printing 3 in the above example .. otherwise it would have printed 3->3
                if (first == prev) {
                    result.add(first + " ");
                } else {
                    result.add(first + "->" + prev);
                }

                if (i == n - 1) {
                    result.add(numbers[i] + " ");
                }

                first = numbers[i];

            }

            prev = numbers[i];

        }

        return result;
    }

}
