/*

Check if string follows order of characters defined by a pattern or not
Given an input string and a pattern, check if characters in the input string follows the same order as determined
by characters present in the pattern. Assume there won’t be any duplicate characters in the pattern.

Examples:

Input:
string = "engineers rock"
pattern = "er";
Output: true
Explanation:
All 'e' in the input string are before all 'r'.

Input:
string = "engineers rock"
pattern = "egr";
Output: false
Explanation:
There are two 'e' after 'g' in the input string.

Input:
string = "engineers rock"
pattern = "gsr";
Output: false
Explanation:
There are one 'r' before 's' in the input string.

 */

package strings;

import java.util.HashMap;

/**
 * Created by poorvank on 14/08/16.
 */
public class FollowsPattern {

    HashMap<Character,CharOccurrence> map = new HashMap<>();

    private class CharOccurrence {
        Integer firstOccurrence;
        Integer lastOccurrence;

        public CharOccurrence(Integer firstOccurrence, Integer lastOccurrence) {
            this.firstOccurrence = firstOccurrence;
            this.lastOccurrence = lastOccurrence;
        }

        public Integer getFirstOccurrence() {
            return firstOccurrence;
        }

        public Integer getLastOccurrence() {
            return lastOccurrence;
        }
    }

    public FollowsPattern(String str) {

        //Calculate first & last occurrence of every character O(n)
        for (int i=0;i<str.length();i++) {
            Character c = str.charAt(i);
            if(map.containsKey(c)) {
                int firstOccurrence = map.get(c).getFirstOccurrence();
                map.put(c,new CharOccurrence(firstOccurrence,i));
            } else {
                map.put(c,new CharOccurrence(i,i));
            }
        }

    }

    public boolean followsPattern(String pattern) {

        if(pattern.length()==1) {
            return map.containsKey(pattern.charAt(0));
        }

        for (int i=0;i<pattern.length()-1;i++) {

            char c1 = pattern.charAt(i);
            char c2 = pattern.charAt(i+1);

            if(map.containsKey(c1) && map.containsKey(c2)) {

                if(map.get(c1).getLastOccurrence() > map.get(c2).firstOccurrence) {
                    return false;
                }

            } else {
                return false;
            }

        }

        return true;

    }

    public static void main(String[] args) {

        String input = "engineers rock";
        FollowsPattern fp = new FollowsPattern(input);
        String pattern = "ro";

        if(fp.followsPattern(pattern)) {
            System.out.println("FOLLOWS");
        } else {
            System.out.println("DOES NOT FOLLOW ");
        }

    }

}


/*


The idea is very simple. For every pair (x, y) of consecutive characters in the pattern string, we find the last occurrence of x
and first occurrence of y in the input string. If last occurrence of character x is after first occurrence of character y for any pair,
we return false. Checking for every pair of consecutive characters in the pattern string will suffice. For example, if we consider three
consecutive characters in the pattern say x, y and z, if (x, y) and (y, z) returns true, that implies (x, z) is also true.

We can also use a hashset to store all the characters of the pattern accept the last one. Once a match is found remove the
second last character.. so on.



 */