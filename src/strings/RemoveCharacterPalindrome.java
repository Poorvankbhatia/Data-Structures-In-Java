/*

Remove a character from a string to make it a palindrome
Given a string, we need to check whether it is possible to make this string a palindrome after removing exactly one character from this.

Examples:

Input  : str = “abcba”
Output : Yes
we can remove character ‘c’ to make string palindrome

Input  : str = “abcbea”
Output : Yes
we can remove character ‘e’ to make string palindrome

Input : str = “abecbea”
It is not possible to make this string palindrome
just by removing one character

 */
package strings;

/**
 * Created by poorvank on 06/12/16.
 */
public class RemoveCharacterPalindrome {

    private int findChar(String s) {

        char[] chars = s.toCharArray();

        int i=0,j=chars.length-1;

        while (i<=j) {

            if(i==j) {
                return i;
            }

            if(chars[i]==chars[j]) {
                i++;
                j--;
            } else {
                if(isPalindrome(chars,i,j-1)) {
                    return j;
                } else if(isPalindrome(chars,i+1,j)) {
                    return i;
                } else {
                    //Can't make a palindrome
                    return -1;
                }
            }

        }

        //Consider "aa"
        return i;

    }


    private boolean isPalindrome(char[] chars,int start,int end) {

        while (start<end) {

            if(chars[start]!=chars[end]) {
                return false;
            }
            start++;end--;

        }

        return true;

    }

    public static void main(String[] args) {
        String s = "abcbea";
        int index = new RemoveCharacterPalindrome().findChar(s);
        if(index>=0) {
            System.out.println("Remove " + s.charAt(index));
        } else {
            System.out.println("Not possible");
        }
    }

}

/*

We can solve this problem by finding the position of mismatch. We start looping in the string by keeping two pointers at both the ends which
traverse towards mid position after each iteration, this iteration will stop when we find a mismatch, as it is allowed to remove just one character
we have two choices here,

At mismatch, either remove character pointed by left pointer or remove character pointed by right pointer.

We will check both the cases, remember as we have traversed equal number of steps from both sides, this mid string should also be a palindrome
after removing one character, so we check two substrings, one by removing left character and one by removing right character and if one of them is
palindrome then we can make complete string palindrome by removing corresponding character, and if both substrings are not palindrome then it is not
possible to make complete string a palindrome under given constraint.

 */
