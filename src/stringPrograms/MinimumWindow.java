/*

Find the smallest window in a string containing all characters of another string
Given two strings string1 and string2, find the smallest substring in string1 containing all 
characters of string2 efficiently.

For Example:

Input string1: “this is a test string”
Input string2: “tist”
Output string: “t stri”



 */
package stringPrograms;

/**
 * Created by poorvank on 7/26/15.
 */
public class MinimumWindow {

    public static void main(String[] args) {

        String s = "this is a test string";
        String t = "tist";

        int sLen = s.length();
        int tLen = t.length();

        int[] needToFind = new int[256];

        for (int i = 0; i < tLen; i++) {
            needToFind[(int) t.charAt(i)]++;
        }

        int[] hasFound = new int[256];
        int minWindowLength = Integer.MAX_VALUE;
        int minWindowBegin = 0;
        int minWindowEnd = 0;

        int count = 0;

        for (int begin = 0, end = 0; end < sLen; end++) {


            if (needToFind[(int) s.charAt(end)] == 0) {
                continue;
            }

            hasFound[(int) s.charAt(end)]++;

            if (hasFound[(int) s.charAt(end)] <= needToFind[(int) s.charAt(end)]) {
                count++;
            }


            if (count == tLen) {


                while (needToFind[(int) s.charAt(begin)] == 0 ||
                        hasFound[(int) s.charAt(begin)] > needToFind[(int) s.charAt(begin)]) {

                    if (hasFound[(int) s.charAt(begin)] > needToFind[(int) s.charAt(begin)]) {
                        hasFound[(int) s.charAt(begin)]--;
                    }

                    begin++;

                }

                int windowLength = end - begin + 1;

                if (minWindowLength > windowLength) {
                    minWindowLength = windowLength;
                    minWindowBegin = begin;
                    minWindowEnd = end;
                }


            }


        }

        if (minWindowEnd != 0)
            System.out.println("Window length - " + minWindowLength + " starts at - " + minWindowBegin + " ends at - " + minWindowEnd);
        else {
            System.out.println("No window found");
        }

    }

}


/*

 S = “acbbaca” and T = “aba“. The idea is mainly based on the help of two pointers (begin and end position of the window)
  and two tables (needToFind and hasFound) while traversing S. needToFind stores the total count of a character in T 
  and hasFound stores the total count of a character met so far. We also use a count variable to store the total 
  characters in T that’s met so far (not counting characters where hasFound[x] exceeds needToFind[x]). 
  When count equals T‘s length, we know a valid window is found.

Each time we advance the end pointer (pointing to an element x), we increment hasFound[x] by one. 
We also increment count by one if hasFound[x] is less than or equal to needToFind[x]. 
Why? When the constraint is met (that is, count equals to T‘s size), 
we immediately advance begin pointer as far right as possible while maintaining the constraint.

How do we check if it is maintaining the constraint? Assume that begin points to an element x, 
we check if hasFound[x] is greater than needToFind[x]. If it is, we can decrement hasFound[x] by one 
and advancing begin pointer without breaking the constraint. On the other hand, 
if it is not, we stop immediately as advancing begin pointer breaks the window constraint.

Finally, we check if the minimum window length is less than the current minimum. 
Update the current minimum if a new minimum is found.

Essentially, the algorithm finds the first window that satisfies the constraint, 
then continue maintaining the constraint throughout.

See - http://articles.leetcode.com/2010/11/finding-minimum-window-in-s-which.html


1) Build a count array count[] for string 2. The count array stores counts of characters.
count[‘i’] = 1
count[‘t’] = 2
count[‘s’] = 1

2) Scan the string1 from left to right until we find all the characters of string2. To check if all
 the characters are there, use count[] built in step 1. So we have substring “this is a t” containing all
  characters of string2. Note that the first and last characters of the substring must be present in string2.
   Store the length of this substring as min_len.

3) Now move forward in string1 and keep adding characters to the substring “this is a t”. Whenever a character is added,
 check if the added character matches the left most character of substring. If matches, then add the new character to the 
 right side of substring and remove the leftmost character and all other extra characters after left most character. 
 After removing the extra characters, get the length of this substring and compare with min_len and update min_len 
 accordingly.

Basically we add ‘e’ to the substring “this is a t”, then add ‘s’ and then’t’. ‘t’ matches the left most character,
 so remove ‘t’ and ‘h’ from the left side of the substring. So our current substring becomes “is a test”. Compare 
length of it with min_len and update min_len.
Again add characters to current substring “is a test”. So our string becomes “is a test str”. When we add ‘i’, 
we remove leftmost extra characters, so current substring becomes “t stri”. Again, compare length of it with min_len and 
update min_len. Finally add ‘n’ and ‘g’. Adding these characters doesn’t decrease min_len, so the smallest window remains 
“t stri”.

4) Return min_len.

Please write comments if you find the above algorithms incorrect, or find other ways to solve the same problem.

 */