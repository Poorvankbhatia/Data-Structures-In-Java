/*

Given a matrix ,where each cell represents a value of strength.
The value could be positive negative or zero. At each cell the strength value is added.
You have to reach from point (0,0) to point (m-1,n-1) if at any point the strength becomes
zero or negative then you cant proceed further.Find the minimum strength required before starting to reach 
the end of the matrix.
eg . {{ 1,-3,0},
{-2,1,-1}} Ans: 2 ( (2+1+(-2)+1+(-1)=1 path taken )

 */

package miscellaneous;

/**
 * Created by poorvank on 12/19/15.
 */
public class MinimumStrengthReq {
    
    public static void main(String[] args) {
        
        int[][] arr = new int[][] {{ 1,-3,0},
                                   {-2,1,-1}};
        
        int row = arr.length;
        int col = arr[0].length;
        
        int[][] sumPath = new int[arr.length][arr[0].length];
        
        sumPath[0][0] = arr[0][0];
        
        for (int i=1;i<col;i++) {
            sumPath[0][i] += sumPath[0][i-1] + arr[0][i];
        }
        
        for (int j=1;j<row;j++) {
            sumPath[j][0] += sumPath[j-1][0] + arr[j][0];
        }
        
        for (int i=1;i<row;i++) {
            for (int j=1;j<col;j++) {
                sumPath[i][j] = Math.max(sumPath[i-1][j],sumPath[i][j-1]) + arr[i][j];
            }
        }
        
        for (int i=0;i<row;i++) {
            for (int j=0;j<col;j++) {
                System.out.print(sumPath[i][j] + " ");
            }
            System.out.println();
        }
        
        if(sumPath[row-1][col-1]<0) {
            System.out.println("Minimum sum required is - " + (Math.abs(sumPath[row-1][col-1])+1));
        }
        else {
            System.out.println("Not required");
        }
    }
    
}
