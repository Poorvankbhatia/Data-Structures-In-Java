/*

Given a string, find if there is any subsequence of length 2 or more that repeats itself such
that the two subsequence don’t have same character at same position, i.e., any 0’th or 1st character
in the two subsequences shouldn’t have the same index in the original string.

Example.

Input: ABCABD
Output: Repeated Subsequence Exists (A B is repeated)

Input: ABBB
Output: Repeated Subsequence Exists (B B is repeated)

Input: AAB
Output: Repeated Subsequence Doesn't Exist (Note that
A B cannot be considered as repeating because B is at
same position in two subsequences).

Input: AABBC
Output: Repeated Subsequence Exists (A B is repeated)

Input: ABCDACB
Output: Repeated Subsequence Exists (A B is repeated)

Input: ABCD
Output: Repeated Subsequence Doesn't Exist

 */
package strings;

/**
 * Created by poorvank on 26/01/17.
 */
public class RepeatedSubSequence {

    public boolean hasRepeatedSubSequence(String s) {

        if(null==s || s.length()==0) {
            return false;
        }

        char[] count = new char[26];
        char[] stringArr = s.toCharArray();

        for (char c : stringArr) {
            count[c-'A']++;
            if(count[c-'A']>3) {
                return true;
            }
        }


        int k=0;
        for (int i=0;i<stringArr.length;i++) {
            if(count[stringArr[i]-'A']>1) {
                stringArr[k++] = stringArr[i];
            }
        }

        if(isPalindrome(stringArr,0,k-1)) {

            if((k&1)==1) {
                int mid = k/2;
                return stringArr[mid-1]==stringArr[mid];
            }

            return false;

        }

        return true;

    }

    private boolean isPalindrome(char[] characters,int i,int j) {
        while (i < j) {
            if(characters[i]!=characters[j]) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(new RepeatedSubSequence().hasRepeatedSubSequence("ZZZZ"));
    }

}


/*

The idea is to remove all the non-repeated characters from the string and check if the resultant string is palindrome or not.
If the remaining string is palindrome then it is not repeated, else there is a repetition. One special case we need to handle
for inputs like “AAA”, which are palindrome but their repeated subsequence exists. Repeated subsequence exists for a palindrome
 string if it is of odd length and its middle letter is same as left(or right) character.


 DP Approach:

 This problem is just the modification of Longest Common Subsequence problem. The idea is to find the LCS(str, str)
 where str is the input string with the restriction that when both the characters are same, they shouldn’t be on the
  same index in the two strings.

 */