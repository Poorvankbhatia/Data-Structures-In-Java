/*

Print Concatenation of Zig-Zag String in ‘n’ Rows
Given a string and number of rows ‘n’. Print the string formed by concatenating n rows when 
input string is written in row-wise Zig-Zag fashion.

Examples:

Input: str = "ABCDEFGH"
       n = 2
Output: "ACEGBDFH"
Explanation: Let us write input string in Zig-Zag fashion
             in 2 rows.
A   C   E   G   
  B   D   F   H
Now concatenate the two rows and ignore spaces 
in every row. We get "ACEGBDFH"

Input: str = "GEEKSFORGEEKS"
       n = 3
Output: GSGSEKFREKEOE
Explanation: Let us write input string in Zig-Zag fashion
             in 3 rows.
G       S       G       S
  E   K   F   R   E   K
    E       O       E
Now concatenate the two rows and ignore spaces 
in every row. We get "GSGSEKFREKEOE"

 */
package strings;

import java.util.Scanner;

/**
 * Created by poorvank on 1/5/16.
 */
public class ZigZagConcatenation {
    
    public static void main(String[] args) {
        
        String input = new Scanner(System.in).nextLine();
        int n = 3;
        
        String[] arrStrings = new String[n];
        
        boolean down = true;
        
        int length=input.length(),i=0,k=0;
        
        while (k<length) {
            
            if(arrStrings[i]!=null) {
                arrStrings[i] += input.charAt(k);
            }
            else {
                arrStrings[i] = Character.toString(input.charAt(k));
            }
            
            if(i==n-1) {
                down = false;
            }
            if(i==0) {
                down = true;
            }
            if(!down) {
                i--;
            }
            else {
                i++;
            }
            k++;
            
        }
        
        StringBuilder result = new StringBuilder();
        for (i=0;i<n;i++) {
            result.append(arrStrings[i]);
        }
        
        System.out.println(result.toString());
    }
    
}

/*

The idea is to traverse the input string. Every character has to go to one of the rows. 
One by one add all characters to different rows. Below is algorithm:

1) Create an array of n strings, arr[n]
2) Initialize direction as "down" and row as 0. The 
   direction indicates whether we need to move up or 
   down in rows. 
3) Traverse the input string, do following for every
   character.
   a) Append current character to string of current row.
   b) If row number is n-1, then change direction to 'up'
   c) If row number is 0, then change direction to 'down'
   d) If direction is 'down', do row++.  Else do row--.
4) One by one print all strings of arr[]. 

 */