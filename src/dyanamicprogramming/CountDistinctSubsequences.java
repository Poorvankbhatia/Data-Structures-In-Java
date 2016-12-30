/**
 * Count distinct occurrences as a subsequence
 * Given a two strings S and T, find count of distinct occurrences of T in S as a subsequence.
 *
 * Examples:
 * Input  : S = banana, T = ban
 * Output : 3
 * T appears in S as below three subsequences.
 * [ban], [ba  n], [b   an]
 *
 * Input  : S = geeksforgeeks, T = ge
 * Output : 6
 * T appears in S as below three subsequences.
 * [ge], [     ge], [g e], [g    e] [g     e]
 * and [     g e]
 */
package dyanamicprogramming;

/**
 * Created by poorvank on 31/12/16.
 */
public class CountDistinctSubsequences {

    private int distinctCount(String s, String t) {

        int m = t.length();
        int n = s.length();

        if (m > n)
            return 0;

        int[][] dpTable = new int[n+1][m+1];

        //When T's length is zero
        for (int i = 0; i <= n; i++)
            dpTable[i][0] = 1;

        //When S's length is 0;
        for (int j = 1; j <= m; j++)
            dpTable[0][j] = 0;

        for (int i=1;i<=n;i++) {
            for (int j=1;j<=m;j++) {

                if(s.charAt(i-1)==t.charAt(j-1)) {
                    dpTable[i][j] = dpTable[i-1][j]+dpTable[i-1][j-1];
                } else {
                    dpTable[i][j] = dpTable[i-1][j];
                }

            }
        }


        return dpTable[n][m];

    }

    public static void main(String[] args) {
        System.out.print(new CountDistinctSubsequences().distinctCount("banana","ban"));
    }

}

/*

This problem can be recursively defined as below.

// Returns count of subsequences of S that match T
// m is length of T and n is length of S
subsequenceCount(S, T, n, m)

   // An empty string is subsequence of all.
   1) If length of T is 0, return 1.

   // Else no string can be a sequence of empty S.
   2) Else if S is empty, return 0.

   3) Else if last characters of S and T don't match,
      remove last character of S and recur for remaining
        return subsequenceCount(S, T, n-1, m)

   4) Else (Last characters match), the result is sum
      of two counts.

        // Remove last character of S and recur.
        a) subsequenceCount(S, T, n-1, m) +

        // Remove last characters of S and T, and recur.
        b) subsequenceCount(S, T, n-1, m-1)

Time Complexity : O(m*n)
Auxiliary Space : O(m*n)

Since dpTable[i][j] accesses elements of current
row and previous row only, we can optimize auxiliary space just by using two rows only reducing space from m*n to 2*n.

 */