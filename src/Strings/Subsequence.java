

/*

Given two strings, find if first string is a subsequence of second
Given two strings str1 and str2, find if str1 is a subsequence of str2.
 A subsequence is a sequence that can be derived from another sequence by deleting
  some elements without changing the order of the remaining elements 

 */

package Strings;

import java.util.Scanner;

/**
 * Created by poorvank on 3/29/15.
 */
public class Subsequence {
    
    public static void main(String[] args) {
        
        System.out.println("Enter String to be checked ");
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        System.out.println("Enter String to be checked in ");
        String str2 = sc.nextLine();
        
        if(isPart(str1,str2)){
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        
        
    }
    
    private static boolean isPart(String str1,String str2) {
        
        int i=0,j=0;
        
        while (i<str2.length() && j<str1.length()) {
            if(str1.charAt(j)==str2.charAt(i)) {
                j++;
            }
            i++;
        }

        
        return (j==str1.length());
        
    }
    
}


/*

The idea is simple, we traverse both strings from one side to other side (say from rightmost character to leftmost).
 If we find a matching character, we move ahead in both strings. Otherwise we move ahead only in str2.

 */