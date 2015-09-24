package dp;

/**
 * Created by poorvank on 5/22/15.
 */
public class LongestPalindromicSubSequence {

    public static void main(String[] args) {

        String input = "BBABCBCAB";

        System.out.println("Length - " + lengthRec(input, 0, input.length() - 1));
        lengthDP(input);

    }

    private static int lengthRec(String input, int i, int j) {

        if (i == j) {
            return 1;
        }
        if (input.charAt(i) == input.charAt(j) && i + 1 == j) {
            return 2;
        }
        if (input.charAt(i) == input.charAt(j)) {
            return lengthRec(input, i + 1, j - 1) + 2;
        } else {
            return Math.max(lengthRec(input, i, j - 1), lengthRec(input, i + 1, j));
        }


    }

    private static void lengthDP(String input) {

        int n = input.length();
        int cl;
        int[][] L = new int[n][n];

        for (int i = 0; i < n; i++) {
            L[i][i] = 1;
        }

        for (cl = 2; cl <= n; cl++) {

            for (int i = 0; i < n - cl + 1; i++) {

                int j = i + cl - 1;

                if (input.charAt(i) == input.charAt(j) && cl == 2) {
                    L[i][j] = 2;
                } else if (input.charAt(i) == input.charAt(j)) {
                    L[i][j] = L[i + 1][j - 1] + 2;
                } else
                    L[i][j] = Math.max(L[i][j - 1], L[i + 1][j]);

            }


        }

        System.out.println(L[0][n - 1]);


    }

}




/*

Let X[0..n-1] be the input sequence of length n and L(0, n-1) be the length of the longest palindromic 
subsequence of X[0..n-1].

If last and first characters of X are same, then L(0, n-1) = L(1, n-2) + 2.
Else L(0, n-1) = MAX (L(1, n-1), L(0, n-2)).

Following is a general recursive solution with all cases handled.

// Everay single character is a palindrom of length 1
L(i, i) = 1 for all indexes i in given sequence

// IF first and last characters are not same
If (X[i] != X[j])  L(i, j) =  max{L(i + 1, j),L(i, j - 1)}

// If there are only 2 characters and both are same
Else if (j == i + 1) L(i, j) = 2

// If there are more than two characters, and first and last
// characters are same
Else L(i, j) =  L(i + 1, j - 1) + 2 

 */