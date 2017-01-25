/**
 * Count All Palindrome Sub-Strings in a String
 * Given a string, the task is to count all palindrome substring in a given string. Length of palindrome substring is greater then or equal to 2.
 * Examples:
 * Input : str = "abaab"
 * Output: 3
 * Explanation : All palindrome substring are : "aba" , "aa" , "baab"
 *
 * Input : str = "abbaeae"
 * Output: 4
 * Explanation : All palindrome substring are : "bb" , "abba" ,"aea","eae"
 *
 */
package dyanamicprogramming;

/**
 * Created by poorvank on 25/12/16.
 */
public class CountAllPalindromeSubStr {

    private int count(String s) {

        int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];

        int[][] dpTable = new int[n][n];

        for (int i= 0; i< n; i++)
            isPalindrome[i][i] = true;

        for (int i=0;i<n-1;i++) {
            if(s.charAt(i)==s.charAt(i+1)) {
                dpTable[i][i+1] = 1;
                isPalindrome[i][i+1] = true;
            }
        }

        for (int gap=2;gap<n;gap++) {

            for (int i=0;i<n-gap;i++) {

                int j = i+gap;

                //Checking palindrome by solving overlapping sub problems
                if(isPalindrome[i+1][j-1] && s.charAt(i)==s.charAt(j)) {
                    isPalindrome[i][j] = true;
                }

                if(isPalindrome[i][j]) {
                    dpTable[i][j] = dpTable[i+1][j] + dpTable[i][j-1] - dpTable[i+1][j-1] + 1;
                } else {
                    dpTable[i][j] = dpTable[i+1][j] + dpTable[i][j-1] - dpTable[i+1][j-1];
                }

            }

        }

        return dpTable[0][n-1];

    }

    public static void main(String[] args) {

        System.out.println(new CountAllPalindromeSubStr().count("cbcdcbc"));

    }

}

/*

Initial Values : i = 0, j = n-1;
Given string 'str'

CountPS(i, j)

   // If length of string is 2 then we
   // check both character are same or not
   If (j == i+1)
      return str[i] == str[j]

   Else If str[i..j] is PALINDROME
      // increment count by 1 and check for
      // rest palindromic substring (i, j-1), (i+1, j)
      // remove common palindrome substring (i+1, j-1)
      return  countPS(i+1, j) + countPS(i, j-1) + 1 -
                                   countPS(i+1, j-1);

    Else // if NOT PALINDROME
       // We check for rest palindromic substrings (i, j-1)
       // and (i+1, j)
       // remove common palindrome substring (i+1 , j-1)
       return  countPS(i+1, j) + countPS(i, j-1) -
                             countPS(i+1 , j-1);


Time complexity O(n^2)
Auxiliary Space O(n^2)




 */