/*

Longest Even Length Substring such that Sum of First and Second Half is same
Given a string ‘str’ of digits, find length of the longest substring of ‘str’, 
such that the length of the substring is 2k digits and sum of left k digits is equal to the sum of right k digits.

Examples:

Input: str = "123123"
Output: 6
The complete string is of even length and sum of first and second
half digits is same

Input: str = "1538023"
Output: 4
The longest substring with same first and second half sum is "5380"

http://www.geeksforgeeks.org/longest-even-length-substring-sum-first-second-half/

 */
package miscellaneous;

import java.util.Scanner;

/**
 * Created by poorvank on 12/15/15.
 */
public class LongestEvenSubString {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter String");
        String input = scanner.nextLine();

        int n = input.length();
        int ans = 0;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n - 1; i++) {

            int leftSum = 0;
            int rightSum = 0;

            int l = i, r = i + 1;

            while (r < n && l >= 0) {

                leftSum += input.charAt(l) - '0';
                rightSum += input.charAt(r) - '0';
                if (leftSum == rightSum) {
                    if (ans < r - l + 1) {
                        result = new StringBuilder("");
                        String s = input.substring(l, r + 1);
                        result.append(s);
                        ans = r - l + 1;
                    }
                }
                l--;
                r++;

            }

        }

        System.out.println("Length of longest even substring  = " + ans + " --> " + result.toString());

    }

}

/*

[A O(n2) time and O(1) extra space solution]
The idea is to consider all possible mid points (of even length substrings) and keep 
expanding on both sides to get and update optimal length as the sum of two sides become equal.

 */