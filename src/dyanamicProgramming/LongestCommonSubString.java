/*

Given two strings ‘X’ and ‘Y’, find the length of the longest common substring. For example, if the given strings are
 “GeeksforGeeks” and “GeeksQuiz”, the output should be 5 as longest common substring is “Geeks”

 */

package dyanamicProgramming;

/**
 * Created by poorvank on 6/2/15.
 */
public class LongestCommonSubString {
    
    public static void main(String[] args) {
        
        String str1 = "OldSite:GeeksforGeeks.org";
        String str2 = "NewSite:GeeksQuiz.com";
        
        System.out.println("Longest common Substring - " + returnString(str1,str2));
        
    }
    
    private static int returnString(String str1,String str2) {
        
        int m = str1.length();
        int n = str2.length();
        
        int[][] LCSub = new int[m+1][n+1];
        int result = 0;
        
        for (int i=0;i<m;i++) {
            for (int j=0;j<=n;j++) {
                if(i==0 || j==0) {
                    LCSub[i][j] = 0;
                }
                else if(str1.charAt(i-1)==str2.charAt(j-1)) {
                    LCSub[i][j] = LCSub[i-1][j-1] + 1;
                    result = Math.max(result,LCSub[i][j]);
                }
                else LCSub[i][j]=0;
            }
        }
        
        return result;
        
    }
    
}

/*

Dynamic Programming can be used to find the longest common substring in O(m*n) time. 
The idea is to find length of the longest common suffix for all substrings of both strings and 
store these lengths in a table.

The longest common suffix has following optimal substructure property
   LCSuff(X, Y, m, n) = LCSuff(X, Y, m-1, n-1) + 1 if X[m-1] = Y[n-1]
                        0  Otherwise (if X[m-1] != Y[n-1])

The maximum length Longest Common Suffix is the longest common substring.
   LCSubStr(X, Y, m, n)  = Max(LCSuff(X, Y, i, j)) where 1 <= i <= m
                                                     and 1 <= j <= n

 */