/*

Print all possible strings that can be made by placing spaces.

Given a string you need to print all possible strings that can be made by placing spaces (zero or one) in between them.

Input:  str[] = "ABC"
Output: ABC
        AB C
        A BC
        A B C

 */

package Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by poorvank on 3/27/15.
 */
public class StringUsingSpaces {
    
    public static void main(String[] args) {

        System.out.println("Enter string ");
        Scanner scanner = new Scanner(System.in);
        String line  = scanner.nextLine();
        printSpaceString(line);
        
    }
    
    private static void printSpaceString(String line) {
        
        char[] buffer = new char[line.length()*2];
        
        buffer[0] = line.charAt(0);
        
        spaceStringHelper(buffer,1,1,line,line.length());
        
        
        
    }
    
    private static void spaceStringHelper(char[] buffer,int i ,int j,String line,int n) {
        
        if(i == n) {
            
            buffer[j]=' ';
            System.out.println(Arrays.toString(buffer));
            return;
            
        }
        
        buffer[j] = line.charAt(i);
        spaceStringHelper(buffer,i+1,j+1,line,n);
        
        buffer[j] = ' ';
        buffer[j+1] = line.charAt(i);
        spaceStringHelper(buffer,i+1,j+2,line,n);
        
    }
    
}


/*

The idea is to use recursion and create a buffer that one by one contains all output strings having spaces.
 We keep updating buffer in every recursive call. If the length of given string is ‘n’ 
 our updated string can have maximum length of n + (n-1) i.e. 2n-1. So we create buffer size of 2n 
 (one extra character for string termination).
We leave 1st character as it is, starting from the 2nd character, we can either fill a space or a character.

Since number of Gaps are n-1, there are total 2^(n-1) patters each having length ranging from n to 2n-1.
 Thus overall complexity would be O(n*(2^n)).

 */