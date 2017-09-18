/**
 *  A data type that computes the suffix array of a string.
 *
 *   % java SuffixArray < abra.txt
 *    i ind lcp rnk  select
 *   ---------------------------
 *    0  11   -   0  "!"
 *    1  10   0   1  "A!"
 *    2   7   1   2  "ABRA!"
 *    3   0   4   3  "ABRACADABRA!"
 *    4   3   1   4  "ACADABRA!"
 *    5   5   1   5  "ADABRA!"
 *    6   8   0   6  "BRA!"
 *    7   1   3   7  "BRACADABRA!"
 *    8   4   0   8  "CADABRA!"
 *    9   6   0   9  "DABRA!"
 *   10   9   0  10  "RA!"
 *   11   2   2  11  "RACADABRA!"
 */

package strings;

import utility.Suffix;
import utility.LongestCommonPrefix;

import java.util.Arrays;

/**
 * Created by poorvank on 14/07/16.
 */

public class SuffixArray {

    private Suffix[] suffixes;

    public SuffixArray(String text) {
        suffixes = new Suffix[text.length()];
        for (int i=0;i<text.length();i++) {
            suffixes[i] = new Suffix(text,i);
        }
        Arrays.sort(suffixes);
    }

    public int length() {
        return suffixes.length;
    }

    /*
        Index of string in orignal unsorted array of suffixes
     */
    public int index(int i) {
        if(i<0 && i>=suffixes.length) {
            throw new IndexOutOfBoundsException();
        }
        return suffixes[i].getIndex();
    }

    // Return ith smallest string
    public String select(int i) {
        if (i < 0 || i >= suffixes.length) throw new IndexOutOfBoundsException();
        return suffixes[i].toString();
    }

    public String lcp(int i) {
        if (i < 1 || i >= suffixes.length) throw new IndexOutOfBoundsException();
        return LongestCommonPrefix.value(suffixes[i].toString(),suffixes[i-1].toString());
    }

    //Returns the number of suffixes strictly less than the query string.
    public int rank(String query) {
        int lo=0,hi=suffixes.length-1;
        while (lo<=hi) {

            int mid = (lo+hi)/2;
            int compare = compare(query,suffixes[mid]);
            if(compare<0) {
                lo = mid+1;
            } else if(compare > 0) {
                hi = mid-1;
            } else {
                return mid;
            }
        }
        return lo;
    }

    private int compare(String query,Suffix suffix) {
        int n = Math.min(query.length(),suffix.getLength());
        for (int i=0;i<n;i++) {
            if(query.charAt(i)>suffix.charAt(i)) {
                return -1;
            }
            if(query.charAt(i)<suffix.charAt(i)) {
                return 1;
            }
        }
        //Query should be small than the suffix according to above conditions.
        return suffix.getLength() - query.length();
    }


    public static void main(String[] args) {

        SuffixArray suffixArray = new SuffixArray("it was the best of times it was the");
        System.out.println(suffixArray.select(0) + " rank of (th) " + suffixArray.rank("th"));

    }


}


/**
 The implementation of index() is also a one-liner, but it is a
 bit tricky, based on the observation that the length of the suffix string uniquely determines
 its starting point. The suffix of length N starts at position 0, the suffix of length N-1 starts
 at position 1, the suufix of length N-2 starts at position 2, and so forth, so index(i)
 just returns N - suffixes[i].length().


 Ideally, the index
 would associate all possible substrings of the
 text string with each position where it occurs
 in the text string, as depicted at right. The basic
 problem with this ideal is that the number
 of possible substrings is too large to have a
 symbol-table entry for each of them (an Ncharacter
 text has N*(N-1)/2 substrings).
 The table for the example at right would need
 entries for b, be, bes, best, best o, best of,
 e, es, est, est o, est of, s, st, st o, st of,
 t, t o, t of, o, of, and many, many other substrings. Again, we can use a suffix sort to
 address this problem in a manner analogous to our first symbol-table implementation
 using binary search.


 The efficiency of suffix sorting depends on the fact that Java substring
 extraction uses a constant amount of space—each substring is composed of standard
 object overhead, a pointer into the original, and a length.

 It is very important to bear in mind that this approach is effective
 for long strings because of the Java representation for strings: when we exchange
 two strings, we are exchanging only references, not the whole string.
 Now, the cost of
 comparing two strings may be proportional to the length of the strings in the case when
 their common prefix is very long, but most comparisons in typical applications involve
 only a few characters.


 https://discuss.leetcode.com/topic/215/special-subsequence/2


 */