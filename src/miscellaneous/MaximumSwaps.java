/*

Find Maximum number possible by doing at-most K swaps
Given a positive integer, find maximum integer possible by doing at-most K swap operations on its digits.

Examples:

Input: M = 254, K = 1
Output: 524

Input: M = 254, K = 2
Output: 542

Input: M = 68543, K = 1
Output: 86543

Input: M = 7599, K = 2
Output: 9975

Input: M = 76543, K = 1
Output: 76543

Input: M = 129814999, K = 4
Output: 999984211

 */
package miscellaneous;

/**
 * Created by poorvank on 17/12/16.
 */
public class MaximumSwaps {

    public static int max=0;

    public MaximumSwaps(int n,int k) {
        findMaxNo(n,k);
    }

    private void findMaxNo(int no,int k) {

        if(k==0) {
            return;
        }

        String s = Integer.toString(no);
        char[] arr = s.toCharArray();

        for (int i=0;i<arr.length;i++) {

            for (int j = i+1;j<arr.length;j++) {

                swap(i,j,arr);

                int n = Integer.parseInt(new String(arr));

                if(n>max) {
                    max = n;
                }

                findMaxNo(n,k-1);

                swap(i,j,arr);

            }

        }

    }


    private void swap(int i,int j,char[] arr) {
        char s = arr[i];
        arr[i] = arr[j];
        arr[j] = s;
    }

    public static void main(String[] args) {
        MaximumSwaps m = new MaximumSwaps(129814999,4);
        System.out.println(max);

    }

}

/*

Idea is to consider every digit and swap it with digits following it one at a time and see if it leads to the maximum number.
We repeat the process K times. The code can be further optimized if we swap only if current digit is less than the following digit.

k * Square(n) complexity

 */