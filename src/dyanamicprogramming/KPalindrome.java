/*
Find if string is K-Palindrome or not | Set 1
Given a string, find out if the string is K-Palindrome or not. A k-palindrome string transforms into a
 palindrome on removing at most k characters from it.

Examples :

Input : String - abcdecba, k = 1
Output : Yes
String can become palindrome by remo-
-ving 1 character i.e. either d or e)


Input  : String - abcdeca, K = 2
Output : Yes
Can become palindrome by removing
2 characters b and e.

Input : String - acdcb, K = 1
Output : No
String can not become palindrome by
removing only one character.
 */
package dyanamicprogramming;

/**
 * Created by poorvank.b on 17/09/17.
 */
public class KPalindrome {

    public int isKPalindrome(String s) {

        if(s==null || s.length()==0) {
            return 0;
        }

        StringBuilder sb = new StringBuilder(s).reverse();

        int n = s.length();
        return recursiveCheck(s,sb.toString(),n,n);



    }

    private int recursiveCheck(String s1,String s2,int m,int n) {

        if(m==0) {
            return n;
        }

        // If second string is empty, the only option is to
        // remove all characters of first string
        if(n==0) {
            return m;
        }

        // If last characters of two strings are same, ignore
        // last characters and get count for remaining strings.
        if(s1.charAt(m-1)==s2.charAt(n-1)){
            return recursiveCheck(s1,s2,m-1,n-1);
        }

        // If last characters are not same,
        // 1. Remove last char from str1 and recur for m-1 and n
        // 2. Remove last char from str2 and recur for m and n-1
        // Take minimum of above two operations
        return 1+Math.min(recursiveCheck(s1,s2,m-1,n),recursiveCheck(s1,s2,m,n-1));

    }

    private int dpCheck(String s1,String s2,int m,int n) {

        int[][] dp = new int[m+1][n+1];
        for (int j=0;j<=m;j++) {
            dp[0][j]=j;
        }
        for (int i=0;i<=n;i++){
            dp[i][0]=i;
        }

        for (int i=1;i<=m;i++) {
            for (int j=1;j<=n;j++) {

                if(s1.charAt(i-1)==s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1+Math.min(dp[i-1][j],dp[i][j-1]);
                }

            }
        }

        return dp[m][n];

    }

}
/*
If we carefully analyze the problem, the task is to transform the given string into its reverse by removing at most K
characters from it. The problem is basically a variation of Edit Distance. We can modify the Edit Distance problem
to consider given string and its reverse as input and only operation allowed is deletion. Since given string is
compared with its reverse, we will do at most N deletions from first string and N deletions from second string to
make them equal. Therefore, for a string to be k-palindrome, 2*N <= 2*K should hold true. Below are the detailed
steps of algorithm - Process all characters one by one staring from either from left or right sides of both strings.
Let us traverse from the right corner, there are two possibilities for every pair of character being traversed.

If last characters of two strings are same, we ignore last characters and get count for remaining strings. So we recur for lengths
m-1 and n-1 where m is length of str1 and n is length of str2.
If last characters are not same, we consider remove operation on last character of first string and last character of second string,
recursively compute minimum cost for the operations and take minimum of two values.
Remove last char from str1: Recur for m-1 and n.
Remove last char from str2: Recur for m and n-1.


Time complexity of above solution is O(m x n). We can improve time complexity by making use of the fact that only
k deletions are allowed. Auxiliary space used is O(m x n).


 */