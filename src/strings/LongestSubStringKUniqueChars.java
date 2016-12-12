/*

Find the longest substring with k unique characters in a given string
Given a string you need to print longest possible substring that has exactly M unique characters. If there are more than one substring of longest possible length, then print any one of them.

Examples:

"aabbcc", k = 1
Max substring can be any one from {"aa" , "bb" , "cc"}.

"aabbcc", k = 2
Max substring can be any one from {"aabb" , "bbcc"}.

"aabbcc", k = 3
There are substrings with exactly 3 unique characters
{"aabbcc" , "abbcc" , "aabbc" , "abbc" }
Max is "aabbcc" with length 6.

"aaabbb", k = 3
There are only two unique characters, thus show error message.

 */
package strings;

/**
 * Created by poorvank on 12/12/16.
 */
public class LongestSubStringKUniqueChars {

    public int longestSubstring(String s,int k) {

        int maxSize = 0;
        int currentSize = 0;
        //The number of unique characters at that moment
        int uniqueCount = 0;

        int[] count = new int[26];
        int start=-1;
        int end = 0;
        int maxStart = 0,maxEnd=0;

        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 0) {
                if (start == -1) {
                    start = i;
                }
                if (uniqueCount == k) {
                    while (count[s.charAt(start) - 'a'] > 0 && uniqueCountPresent(count, k)) {
                        count[s.charAt(start) - 'a']--;
                        start++;
                    }
                    count[s.charAt(i) - 'a']++;
                    uniqueCount--;
                    end++;
                    currentSize = end - start + 1;
                } else {
                    count[s.charAt(i) - 'a']++;
                    currentSize++;
                }
                uniqueCount++;
            } else if (count[s.charAt(i) - 'a'] >= 1) {
                count[s.charAt(i) - 'a']++;
                currentSize++;
            }
            if (uniqueCount == k) {
                if (maxSize < currentSize) {
                    maxSize = currentSize;
                    end = i;
                    maxStart = start;
                    maxEnd = end;
                }
            }
        }

        System.out.println((maxSize!=0?"Longest substring : " +s.substring(maxStart,maxEnd+1):"No Substring found"));
        return maxSize;

    }

    //Takes constant time.
    private boolean uniqueCountPresent(int[] count, int k) {
        int val=0;
        for (int i=0;i<26;i++) {
            if(count[i]>0) {
                val++;
            }
        }
        return val>=k;
    }


    public static void main(String[] args) {
        System.out.println(new LongestSubStringKUniqueChars().longestSubstring("aabacbebebe",3));
    }

}

/*

The problem can be solved in O(n). Idea is to maintain a window and add elements to the window till it contains less or equal k,
update our result if required while doing so. If unique elements exceeds than required in window, start removing the elements from left side.



 */