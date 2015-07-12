/*

Count Possible Decodings of a given Digit Sequence
Let 1 represent ‘A’, 2 represents ‘B’, etc. Given a digit sequence, count the number of possible
 decodings of the given digit sequence.

Examples:

Input:  digits[] = "121"
Output: 3
// The possible decodings are "ABA", "AU", "LA"

Input: digits[] = "1234"
Output: 3
// The possible decodings are "ABCD", "LCD", "AWD"
An empty digit sequence is considered to have one decoding. It may be assumed that the input
 contains valid digits from 0 to 9 and there are no leading 0’s, no extra trailing 0’s and
 no two or more consecutive 0’s.

 */

package dyanamicProgramming;

/**
 * Created by poorvank on 5/27/15.
 */
public class PossibleDecodings {

    public static void main(String[] args) {

        String string = "152";
        // System.out.println(recursiveCount(string,string.length()));
        DPMethod(string);
    }


    private static int recursiveCount(String sequence, int n) {


        if (n == 0 || n == 1) {
            return 1;
        }

        int count = 0;

        if (sequence.charAt(n - 1) > '0') {
            count = recursiveCount(sequence, n - 1);
        }

        //Will only come when n>=2
        // If the last two digits form a number smaller than or equal to 26,
        // then consider last two digits and recur
        if (sequence.charAt(n - 2) < '2' || (sequence.charAt(n - 2) == '2' && sequence.charAt(n - 1) < '7')) {
            count += recursiveCount(sequence, n - 2);
        }

        return count;
    }

    private static void DPMethod(String sequence) {

        //count indicates number of possible decodings for the ith character of the string
        int[] count = new int[sequence.length() + 1];

        count[0] = 1;
        count[1] = 1;

        for (int i = 2; i <= sequence.length(); i++) {

            count[i] = 0;


            if (sequence.charAt(i - 1) > '0') {
                count[i] = count[i - 1];
            }

            System.out.println(sequence.charAt(i - 2));
            //Dependent on previous input if the conditions apply
            if (sequence.charAt(i - 2) < '2' || (sequence.charAt(i - 2) == '2' && sequence.charAt(i - 1) < '7')) {
                count[i] += count[i - 2];
            }

        }

        System.out.println(count[sequence.length()]);

    }


}


/*

This problem is recursive and can be broken in sub-problems. We start from end of the given digit sequence. 
We initialize the total count of decodings as 0. We recur for two subproblems.
1) If the last digit is non-zero, recur for remaining (n-1) digits and add the result to total count.
2) If the last two digits form a valid character (or smaller than 27), recur for remaining (n-2) 
digits and add the result to total count.

 */