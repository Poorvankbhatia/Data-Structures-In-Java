/*

Given a set of strings, find the longest common prefix.

Input  : {“geeksforgeeks”, “geeks”, “geek”, “geezer”}
Output : "gee"

Input  : {"apple", "ape", "april"}
Output : "ap"

 */
package strings;

/**
 * Created by poorvank on 02/01/17.
 */
public class LongestCommonPrefix {

    /**
     *Time Complexity : Since we are iterating through all the strings and for each string we are iterating though each
     * characters, so we can say that the time complexity is O(N M) where,
     * N = Number of strings
     * M = Length of the largest string string
     */
    private String horizontalScanning(String[] strings) {

        if(null==strings || strings.length==0) {
            return "";
        }

        String prefix = strings[0];

        for (int i=1;i<strings.length;i++) {
            while (strings[i].indexOf(prefix)!=0) {
                prefix = prefix.substring(0,prefix.length()-1);
                if(prefix.equals("")) {
                    return "";
                }
            }
        }

        return prefix;

    }


    /**
     * Better than the above algorithm because
     * Suppose you have the input strings as- “geeksforgeeks”, “geeks”, “geek”, “geezer”, “x”.
     * Now there is no common prefix string of the above strings. By the “Word by Word Matching”
     * algorithm discussed in Set 1, we come to the conclusion that there is no common prefix string by
     * traversing all the strings.
     *
     * But if we use this algorithm, then in the first iteration itself we will
     * come to know that there is no common prefix string, as we don’t go further to look for the second character of each strings.
     * This algorithm has a huge advantage when there are too many strings.
     *
     * Time Complexity : Since we are iterating through all the characters of all the strings,
     * so we can say that the time complexity is O(N M) where,
     * N = Number of strings
     * M = Length of the largest string string
     * Auxiliary Space : To store the longest prefix string we are allocating space which is O(M).
     */

    public String verticalScanning(String[] strings) {

        if(null==strings || strings.length==0) {
            return "";
        }

        int minLength = minLength(strings);

        String prefix = "";
        char c;

        for (int i=0;i<minLength;i++) {

            c = strings[0].charAt(i);

            for (int j=1;j<strings.length;j++) {
                if(strings[j].charAt(i)!=c) {
                    return prefix;
                }
            }

            prefix += c;

        }

        return prefix;

    }

    private int minLength(String[] strings) {
        int minLength = Integer.MAX_VALUE;

        for (String s : strings) {
            if(s.length()<minLength) {
                minLength = s.length();
            }
        }
        return minLength;
    }


    /**
     *In the worst case we have n equal strings with length mm
     * Time complexity : O(S*log(n)), where S is the sum of all characters in all strings.
     * The algorithm makes log(n) iterations, for each of them there are S=m∗n comparisons,
     * which gives in total O(S*log(n)) time complexity.
     * Space complexity : O(1).
     *
     * T(M) = T(M/2) + O(MN)
     * where
     * N = Number of strings
     * M = Length of the largest string string
     * So we can say that the time complexity is O(NM log M)
     */
    public String binarySearchScanning(String[] strings) {

        if(null==strings || strings.length==0) {
            return "";
        }

        int minLength = minLength(strings);

        int low=0,high=minLength;

        while (low<=high) {

            int mid = low + (high-low)/2;
            if(isCommonPrefix(strings,mid)) {
                low = mid+1;
            } else {
                high = mid-1;
            }

        }

        return strings[0].substring(0,(low+high)/2);

    }

    private boolean isCommonPrefix(String[] strings,int len) {
        String prefix = strings[0].substring(0,len);
        for (int i = 1; i < strings.length; i++)
            if (!strings[i].startsWith(prefix))
                return false;
        return true;
    }


    public static void main(String[] args) {

        String[] strings = new String[]{"geeksforgeeks", "geeks", "geek", "geezer"};
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        System.out.println(longestCommonPrefix.verticalScanning(strings));
        System.out.println(longestCommonPrefix.horizontalScanning(strings));
        System.out.println(longestCommonPrefix.binarySearchScanning(strings));
        

    }

}

/*

Binary search method:

binary search method to find the string with maximum value L, which is common prefix of all of the strings.
The algorithm searches space is the interval (0 \ldots minLen)(0…minLen), where minLen is minimum string length and the maximum
possible common prefix. Each time search space is divided in two equal parts, one of them is discarded, because it is sure that it
doesn't contain the solution. There are two possible cases: S[1...mid] is not a common string. This means that for each j > i S[1..j]
is not a common string and we discard the second half of the search space. S[1...mid] is common string. This means that for for each
i < j S[1..i] is a common string and we discard the first half of the search space, because we try to find longer common prefix.

 */