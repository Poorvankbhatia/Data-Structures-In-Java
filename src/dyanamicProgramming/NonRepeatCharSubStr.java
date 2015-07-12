package dyanamicProgramming;

/**
 * Created by poorvank on 5/21/15.
 */
public class NonRepeatCharSubStr {

    public static void main(String[] args) {

        String string1 = "GEEKSFORGEEKS";
        String string2 = "abcabcbb";
        String string3 = "abccgef";

        longestSubStr(string1);
        longestSubStr(string2);
        longestSubStr(string3);

    }

    private static void longestSubStr(String string) {

        boolean[] visited = new boolean[256];
        int maxLength = 0;
        int start = 0, end = 0;
        int cumStart = 0, cumEnd = 0;

        while (end < string.length()) {
            if (visited[string.charAt(end)]) {
                if (end - start > maxLength) {
                    maxLength = end - start;
                    cumStart = start;
                    cumEnd = end;
                }
                //maxLength = Math.max(end - start,maxLength);
                while (string.charAt(end) != string.charAt(start)) {
                    visited[string.charAt(start)] = false;
                    start++;
                }
                start++;
                end++;
            } else {
                visited[string.charAt(end)] = true;
                end++;
            }
        }

        if (string.length() - start > maxLength) {
            maxLength = Math.max(string.length() - start, maxLength);
            cumEnd = string.length();
            cumStart = start;
        }

        System.out.println("longest substring without repeating characters for " + string + " is " + maxLength);
        System.out.println("And result is - " + string.substring(cumStart, cumEnd));

    }

}

/*

As you traverse through the string, update by using its ASCII value as index to the table. 
If the string only contains ‘a’-‘z’, you could save space by using a table of size 26 only. 
Assuming c is the character, then c-‘a’ will give you a value of 0-25 which can be used to index the table directly.


When you have found a repeated character (let’s say at index j), it means that the current substring 
(excluding the repeated character of course) is a potential maximum, so update the maximum if necessary. 
It also means that the repeated character must have appeared before at an index i, where i is less than j.

Since you know that all substrings that start before or at index i would be less than your current maximum, 
you can safely start to look for the next substring with head which starts exactly at index i+1.

Therefore, you would need two indices to record the head and the tail of the current substring. 
Since i and j both traverse at most n steps, the worst case would be 2n steps, which the run time complexity must be O(n).

 */
