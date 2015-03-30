/*

Build Lowest Number by Removing n digits from a given number
Given a string ‘str’ of digits and an integer ‘n’, build the lowest possible number by 
removing ‘n’ digits from the string and not changing the order of input digits.

Examples:

Input: str = "4325043", n = 3
Output: "2043"

Input: str = "765028321", n = 5
Output: "0221"

Input: str = "121198", n = 2
Output: "1118" 

 */

package miscellaneous;

import java.util.Scanner;

/**
 * Created by poorvank on 3/30/15.
 */
public class BuildLowestNo {
    
    public static void main(String[] args) {
        
        System.out.println("Enter number string ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        StringBuilder sb = new StringBuilder("");
        lowestNo(input,sb,3);
        
    }
    
    private static void lowestNo(String input,StringBuilder sb,int n) {

        // If there are 0 characters to remove from str,
        // append everything to result
        
        if(n==0) {
            sb.append(input);
            System.out.println("Smallest number is - " + sb.toString());
            return;
        }
        
        if(input.length() < n) {
            return;
        }
        
        int minIndex = 0;
        
        for (int i = 1 ; i <=n ;i++) {
            if(Character.getNumericValue(input.charAt(i)) < Character.getNumericValue(input.charAt(minIndex))) {
                minIndex = i;
            }
        }
        
        sb.append(input.charAt(minIndex));

        // substring starting from minIndex+1 to str.length() - 1.
        
        String newStr = input.substring(minIndex+1,input.length());
        
        //n = n - minindex because you have already removed 0 to min_index -1 numbers from the string.
        //Now remove n-min_index elements . if minindex is 2 u have already removed 2 elements
        lowestNo(newStr,sb,n-minIndex);
        
    }
    
}


/*


The idea is based on the fact that a character among first (n+1) characters must be there in resultant number.
 So we pick the smallest of first (n+1) digits and put it in result, and recur for remaining characters
 
 if we remove n numbers then (len -n) are left.. hence n+1th number is in the result

 */