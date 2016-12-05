/*
Given two sequences, print all longest common sequences present in both of them.

Examples:

Input:
string X = "AGTGATG"
string Y = "GTTAG"
Output:
GTAG
GTTG

Input:
string X = "AATCC"
string Y = "ACACG"
Output:
ACC
AAC

Input:
string X = "ABCBDAB"
string Y = "BDCABA"
Output:
BCAB
BCBA
BDAB

 */

package dyanamicprogramming.lcs;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by poorvank on 05/12/16.
 */
public class AllLcs {

    public Set<String> getAllLcs(String s1,String s2) {

        int n = s1.length();
        int m = s2.length();
        int[][] dp = getLCSTable(s1,s2);

        return getAllLcsUtil(s1,s2,dp,m,n);
    }

    private Set<String> getAllLcsUtil(String s1,String s2,int[][] dp,int i,int j) {

        Set<String> set = new HashSet<>();

        if (i == 0 || j == 0) {
            set.add("");
        } else if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
            Set<String> temp = getAllLcsUtil(s1, s2,dp, i - 1, j - 1);
            for (String lcs : temp) {
                set.add(lcs + s1.charAt(i - 1));
            }
        } else {
            if (dp[i - 1][j] >= dp[i][j - 1]) {
                set.addAll(getAllLcsUtil(s1,s2,dp, i - 1, j));
            }

            if (dp[i][j - 1] >= dp[i - 1][j]) {
                set.addAll(getAllLcsUtil(s1,s2,dp, i, j - 1));
            }
        }
        return set;

    }


    private int[][] getLCSTable(String s1,String s2) {

        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n+1][m+1];

        for (int i=0;i<=n;i++) {
            for (int j=0;j<=m;j++) {

                if(i==0 || j==0) {
                    continue;
                }
                if(s1.charAt(i-1)==s2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }

            }
        }

        return dp;

    }

    public static void main(String[] args){
        Set<String> set = new AllLcs().getAllLcs("abcd","abdc");
        for (String sb : set) {
            System.out.println(sb);
        }
    }
}

/*

For current cell L[i][j] in the matrix,

a) If the last characters of X and Y are same (i.e. X[i-1] == Y[j-1]), then the charcater must be present in all LCS of substring X[0…i-1] and
Y[0..j-1]. We simply recurse for L[i-1][j-1] in the matrix and append current character to all LCS possible of substring X[0…i-2] and Y[0..j-2].

b) If the last characters of X and Y are not same (i.e. X[i-1] != Y[j-1]), then LCS can be constructed from either top side of the matrix
(i.e. L[i-1][j]) or from left side of matrix (i.e. L[i][j-1]) depending upon which value is greater. If both the values are equal
(i.e. L[i-1][j] == L[i][j-1]), then it will be constructed from both sides of matrix.
So based on values at L[i-1][j] and L[i][j-1], we go in direction of greater value or go in both directions if the values are equal.

 */