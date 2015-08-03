package dp;

/**
 * Created by poorvank on 5/28/15.
 */
public class MinInsertionsPalindrome {

    public static void main(String[] args) {

        String str = "poorvank";
        countInsertions(str);
    }

    private static void countInsertions(String str) {

        String rev = new StringBuilder(str).reverse().toString();

        int n = str.length();

        int[][] count = new int[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                if (i == 0 || j == 0) {
                    count[i][j] = 0;
                    continue;
                }

                if (str.charAt(i) == rev.charAt(j)) {
                    count[i][j] = 1 + count[i - 1][j - 1];
                } else {
                    count[i][j] = Math.max(count[i - 1][j], count[i][j - 1]);
                }

            }

        }

        int lcsLength = count[n - 1][n - 1];

        System.out.println("Minimum number of insertions required = " + (n - lcsLength));

    }


}


/*

The problem of finding minimum insertions can also be solved using Longest Common 
Subsequence (LCS) Problem. 
If we find out LCS of string and its reverse, we know how many maximum characters can form a palindrome. 
We need insert remaining characters. Following are the steps.
1) Find the length of LCS of input string and its reverse. Let the length be ‘l’.
2) The minimum number insertions needed is length of input string minus ‘l’

 */