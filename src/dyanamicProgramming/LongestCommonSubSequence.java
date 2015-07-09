/*

LCS Problem Statement: Given two sequences, find the length of longest 
subsequence present in both of them. A subsequence is a sequence that appears in the same relative order, 
but not necessarily contiguous. For example, “abc”, “abg”, “bdf”, “aeg”, ‘”acefg”, .. etc are subsequences of 
“abcdefg”. So a string of length n has 2^n different possible subsequences.

It is a classic computer science problem, 
the basis of diff (a file comparison program that outputs the differences between two files), 
and has applications in bioinformatics.

Examples:
LCS for input Sequences “ABCDGH” and “AEDFHR” is “ADH” of length 3.
LCS for input Sequences “AGGTAB” and “GXTXAYB” is “GTAB” of length 4.



 */

package dyanamicProgramming;

/**
 * Created by poorvank on 5/29/15.
 */
public class LongestCommonSubSequence {
    
    public static void main(String[] args) {
        
        String str1 = "AGGTAB";
        String str2 = "GXTXAYB";
        
        System.out.println(recursive(str1,str2, str1.length(),str2.length()));
        DPMethod(str1,str2);
        
    }
    
    private static int recursive(String str1,String str2,int m,int n) {
        
        if(m==0 || n==0) {
            return 0;
        }
        if(str1.charAt(m-1)==str2.charAt(n-1)) {
            return recursive(str1,str2,m-1,n-1) + 1;
        }
        else {
            return Math.max(recursive(str1,str2,m-1,n),recursive(str1,str2,m,n-1));
        }
        
    }
    
    private static void DPMethod(String str1,String str2) {
        
        int m = str1.length();
        int n = str2.length();
        
        int[][] count = new int[m+1][n+1];
        
        for (int i=0;i<=m;i++) {
            count[i][0] = 0;
        }
        
        for (int i=0;i<=n;i++) {
            count[0][i] = 0;
        }
        
        for (int i=1;i<=m;i++) {
            
            for (int j=1;j<=n;j++) {
                
                if(str1.charAt(i-1)==str2.charAt(j-1)) {
                    count[i][j] = count[i-1][j-1]+1;
                }
                else {
                    count[i][j] = Math.max(count[i-1][j],count[i][j-1]);
                }
                
            }
            
        }
        
        System.out.println(count[m][n]);
        
    }
    
}


/*

Let the input sequences be X[0..m-1] and Y[0..n-1] of lengths m and n respectively. 
And let L(X[0..m-1], Y[0..n-1]) be the length of LCS of the two sequences X and Y. 
Following is the recursive definition of L(X[0..m-1], Y[0..n-1]).

If last characters of both sequences match (or X[m-1] == Y[n-1]) then
L(X[0..m-1], Y[0..n-1]) = 1 + L(X[0..m-2], Y[0..n-2])

If last characters of both sequences do not match (or X[m-1] != Y[n-1]) then
L(X[0..m-1], Y[0..n-1]) = MAX ( L(X[0..m-2], Y[0..n-1]), L(X[0..m-1], Y[0..n-2])

Examples:
1) Consider the input strings “AGGTAB” and “GXTXAYB”. Last characters match for the strings. 
So length of LCS can be written as:
L(“AGGTAB”, “GXTXAYB”) = 1 + L(“AGGTA”, “GXTXAY”)

2) Consider the input strings “ABCDGH” and “AEDFHR. Last characters do not match for the strings. 
So length of LCS can be written as:
L(“ABCDGH”, “AEDFHR”) = MAX ( L(“ABCDG”, “AEDFHR”), L(“ABCDGH”, “AEDFH”) )

So the LCS problem has optimal substructure property as the main problem can be solved using solutions to subproblems.

https://www.youtube.com/watch?v=P-mMvhfJhu8


 */