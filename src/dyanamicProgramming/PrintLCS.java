package dyanamicProgramming;

import java.util.Arrays;

/**
 * Created by poorvank on 5/29/15.
 */
public class PrintLCS {
    
    public static void main(String[] args) {

        String str1 = "AGGTAB";
        String str2 = "GXTXAYB";
        
        print(str1,str2);
        
    }
    
    private static void print(String str1,String str2) {
        
        int m = str1.length();
        int n = str2.length();
        
        int[][] count = new int[m+1][n+1];
        
        for (int i=0;i<=m;i++) {
            
            for (int j=0;j<=n;j++) {
                
                if(i==0 || j==0) {
                    count[i][j] = 0;
                    continue;
                }
                
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    count[i][j] = 1 + count[i-1][j-1];
                }
                else {
                    count[i][j] = Math.max(count[i-1][j],count[i][j-1]);
                }
                
            }
            
        }
        
        int N = count[m][n];
        
        char[] lcsArray = new char[N];
        
        int i =m,j=n;
        
        while (i>0 && j>0) {
            
            if(str1.charAt(i-1)==str2.charAt(j-1)) {
                lcsArray[N-1] = str1.charAt(i-1);
                i--;
                j--;
                N--;
            }
            else if(count[i-1][j]>count[i][j-1]) {
                i--;
            }
            else {
                j--;
            }
            
        }
        
        System.out.println(Arrays.toString(lcsArray));
        
    }
    
}


/*

Traverse the 2D array starting from L[m][n]. Do following for every cell L[i][j]
…..a) If characters (in X and Y) corresponding to L[i][j] are same (Or X[i-1] == Y[j-1]), then include this character as part of LCS.
…..b) Else compare values of L[i-1][j] and L[i][j-1] and go in direction of greater value.

The following table (taken from Wiki) shows steps (highlighted) followed by the above algorithm.

        0	1	2	3	4	5	6	7
        Ø	M	Z	J	A	W	X	U
0	Ø	0	0	0	0	0	0	0	0
1	X	0	0	0	0	0	0	1	1
2	M	0	1	1	1	1	1	1	1
3	J	0	1	1	2	2	2	2	2
4	Y	0	1	1	2	2	2	2	2
5	A	0	1	1	2	3	3	3	3
6	U	0	1	1	2	3	3	3	4
7	Z	0	1	2	2	3	3	3	4

 */