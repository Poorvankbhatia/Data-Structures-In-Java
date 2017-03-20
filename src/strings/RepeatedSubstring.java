/*

Given a string ‘str’, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.

Examples:

Input: str = "abcabcabc"
Output: true
The given string is 3 times repetition of "abc"

Input: str = "abadabad"
Output: true
The given string is 2 times repetition of "abad"

Input: str = "aabaabaabaab"
Output: true
The given string is 4 times repetition of "aab"

Input: str = "abcdabc"
Output: false

 */

package strings;

/**
 * Created by poorvank on 27/01/17.
 */
public class RepeatedSubstring {

    public boolean hasRepeatedSubstring(String s) {

        int n = s.length();
        int l = largestSuffixPrefix(s);
        return (l>0 && (n%(n-l)==0));

    }

    // same as kmp table building
    private int largestSuffixPrefix(String s) {

        int i=1,j=0;
        int[] lps = new int[s.length()];

        while (i<s.length()) {

            if(s.charAt(i)==s.charAt(j)) {
                lps[i] = j+1;
                i++;
                j++;
            } else {
                if(j!=0) {
                    j = lps[j-1];
                } else {
                    lps[i] = j;
                    i++;
                }
            }

        }

        return lps[s.length()-1];

    }

    public static void main(String[] args) {
        System.out.print(new RepeatedSubstring().hasRepeatedSubstring("abab"));
    }

}


/*

There can be many solutions to this problem. The challenging part is to solve the problem in O(n) time. Below is a O(n) algorithm.

Let the given string be ‘str’ and length of given string be ‘n’.

1) Find length of the longest proper prefix of ‘str’ which is also a suffix. Let the length of the longest proper prefix suffix be ‘len’.
 This can be computed in O(n) time using pre-processing step of KMP string matching algorithm.

2) If value of ‘n – len’ divides n (or ‘n % (n-len)’ is 0), then return true, else return false.

In case of ‘true’ , the substring ‘str[0..n-len-1]’ is the substring that repeats n%(n-len) times.

Let us take few examples.

Input: str = “ABCDABCD”, n = 8 (Number of characters in ‘str’)
The value of len is 4 (“ABCD” is the longest substring which is both prefix and suffix)
Since (n-len) divides n, the answer is true.

Input: str = “ABCDABC”, n = 7 (Number of characters in ‘str’)
The value of len is 3 (“ABC” is the longest substring which is both prefix and suffix)
Since (n-len) doesn’t divides n, the answer is false.

Input: str = “ABCABCABCABCABC”, n = 15 (Number of characters in ‘str’)
The value of len is 12 (“ABCABCABCABC” is the longest substring which is both prefix and suffix)
Since (n-len) divides n, the answer is true.

How does this work?
length of longest proper prefix-suffix (or len) is always between 0 to n-1. If len is n-1, then all characters in string are same.
For example len is 3 for “AAAA”. If len is n-2 and n is even, then two characters in string repeat n/2 times. For example “ABABABAB”,
length of lps is 6. The reason is if the first n-2 characters are same as last n-2 character, the starting from the first pair,
every pair of characters is identical to the next pair. The following diagram demonstrates same for substring of length 4.


Every number belongs to corresponding prefix ("a", "ab", "aba", ...) and for each prefix it represents
length of longest suffix of this string that matches prefix. We do not count whole string as suffix or prefix here,
it is called self-suffix and self-prefix (at least in Russian, not sure about English terms).

So we have string "ababaca". Let's look at it. KMP computes Prefix Function for every non-empty prefix.
 Let's define P(i) as Prefix function of i-th prefix. Parts can overlap.

# | String | P(#) | Longest matching prefix-suffix
0 | "a" | 0 | ""
1 | "ab" | 0 | ""
2 | "aba" | 1 | "a" -- a b a
3 | "abab" | 2 | "ab" -- ab ab
4 | "ababa" | 3 | "aba" -- ab a ba
5 | "ababac"| 0 | ""
6 |"ababaca"| 1 | "a" -- a babac a

 */