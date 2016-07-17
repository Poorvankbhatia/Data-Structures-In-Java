/*

Suffix Array construction in O(n(logn)^2) time:

https://discuss.codechef.com/questions/21385/a-tutorial-on-suffix-arrays
https://youtu.be/HKPrVm5FWvg?t=12m51s
http://www.stanford.edu/class/cs97si/suffix-array.pdf
 */

package strings;
import utility.Suffix;

import java.util.*;

/**
 * Created by poorvank on 17/07/16.
 */

class CustomSuffix extends Suffix {

    public Integer rank[] = new Integer[2];

    public CustomSuffix(String text, int i) {
        super(text, i);
    }

    /*
        The compareTo() method is used to compare two objects which have multiple properties. It will return an integer to indicate
        which of the objects that was compared is larger. It makes more sense if the objects being compared have properties which have
        a natural order.

        Return value:

        less than 0 -> indicates that the object is before the passed in object.
        more than 0 -> the object is after the passed object
        equal to 0 -> the two objects are at same level

     */
    @Override
    public int compareTo(Suffix that) {

        if ((this.rank[0]) == ((CustomSuffix) that).rank[0]) {
            if(this.rank[1] < ((CustomSuffix) that).rank[1]) {
                return -1;
            }
            else if (this.rank[1] > ((CustomSuffix) that).rank[1]) {
                return 1;
            } else {
                return this.getLength()-that.getLength();
            }
        } else {
            if(this.rank[0] < ((CustomSuffix) that).rank[0]) {
                return -1;
            } else if(this.rank[0] > ((CustomSuffix) that).rank[0]) {
                return 1;
            } else {
                return this.getLength()-that.getLength();
            }
        }
    }

    public String toString() {
        return  super.getIndex() + " :: " +  super.toString();
    }

}

public class SuffixArrayX {

    private CustomSuffix[] suffixes;


    public SuffixArrayX(String text) {
        int n = text.length();
        suffixes = new CustomSuffix[n];
        for (int i = 0; i < n; i++) {
            suffixes[i] = new CustomSuffix(text, i);
            suffixes[i].rank[0] = (int)text.charAt(i);
            suffixes[i].rank[1] = (i + 1) < n ? (int)(text.charAt(i + 1)) : -1;
        }

        Arrays.sort(suffixes);

        // At his point, all suffixes are sorted according to first
        // 2 characters.  Let us sort suffixes according to first 4
        // characters, then first 8 and so on
        // This array is needed to get the index in suffixes[]
        // from original index.  This mapping is needed to get
        // next suffix.
        int[] ind = new int[n];

        for (int k = 4; k < 2 * n; k = k * 2) {

            //Assign Rank & index to first suffix
            int rank = 0;
            int previousRank = suffixes[0].rank[0];
            suffixes[0].rank[0] = rank;
            ind[suffixes[0].getIndex()] = 0;

            // Assigning rank to suffixes
            for (int i = 1; i < n; i++) {

                if (suffixes[i].rank[0] == previousRank && Objects.equals(suffixes[i].rank[1], suffixes[i - 1].rank[1])) {
                    //Don't move previousRank = suffixes[i].rank[0]; outside if-else as rank changes
                    previousRank = suffixes[i].rank[0];
                    suffixes[i].rank[0] = rank;
                } else {
                    previousRank = suffixes[i].rank[0];
                    rank = rank+1;
                    suffixes[i].rank[0] = rank;
                }


                ind[suffixes[i].getIndex()] = i;

            }

            // Assign next rank to every suffix
            for (int i = 0; i < n; i++) {

                //suffix[i].getIndex() gives the length of the offset from where the suffix starts
                int nextIndex = suffixes[i].getIndex() + (k / 2);
                suffixes[i].rank[1] = (nextIndex < n) ? suffixes[ind[nextIndex]].rank[0] : -1;

            }

            Arrays.sort(suffixes);

        }


    }

    public void print() {
        for (CustomSuffix suffixe : suffixes) {
            System.out.println(suffixe);
        }
    }

    public Comparable[] getSuffixesArray() {
        return suffixes;
    }

    public static void main(String[] args) {

        SuffixArrayX suffixArrayX = new SuffixArrayX("aacaagtttacaagc");
        suffixArrayX.print();

    }

}

/*

The idea is to use the fact that strings that are to be sorted are suffixes of a single string.
We first sort all suffixes according to first character, then according to first 2 characters, then first 4 characters and so on while the
number of characters to be considered is smaller than 2n. The important point is, if we have sorted suffixes according to first 2i characters,
then we can sort suffixes according to first 2i+1 characters in O(nLogn) time using a nLogn sorting algorithm like Merge Sort. This is possible
as two suffixes can be compared in O(1) time (we need to compare only two values, see the below example and code).
The sort function is called O(Logn) times (Note that we increase number of characters to be considered in powers of 2). Therefore overall time
complexity becomes O(nLognLogn). See http://www.stanford.edu/class/cs97si/suffix-array.pdf for more details.

Let us build suffix array the example string “banana” using above algorithm.

Sort according to first two characters Assign a rank to all suffixes using ASCII value of first character. A simple way to assign rank is
to do “str[i] – ‘a'” for ith suffix of strp[]

Index     Suffix            Rank
 0        banana             1
 1        anana              0
 2        nana               13
 3        ana                0
 4        na                 13
 5        a                  0
For every character, we also store rank of next adjacent character, i.e., the rank of character at str[i + 1] (This is needed to sort the
suffixes according to first 2 characters). If a character is last character, we store next rank as -1

Index    Suffix            Rank          Next Rank
 0       banana             1              0
 1       anana              0              13
 2       nana               13             0
 3       ana                0              13
 4       na                 13             0
 5       a                  0             -1
Sort all Suffixes according to rank and adjacent rank. Rank is considered as first digit or MSD, and adjacent rank is considered as second digit.

Index    Suffix            Rank          Next Rank
 5        a                  0              -1
 1        anana              0               13
 3        ana                0               13
 0        banana             1               0
 2        nana               13              0
 4        na                 13              0
Sort according to first four character
Assign new ranks to all suffixes. To assign new ranks, we consider the sorted suffixes one by one. Assign 0 as new rank to first suffix.
For assigning ranks to remaining suffixes, we consider rank pair of suffix just before the current suffix. If previous rank pair of a suffix
is same as previous rank of suffix just before it, then assign it same rank. Otherwise assign rank of previous suffix plus one.

Index       Suffix          Rank
  5          a               0     [Assign 0 to first]
  1          anana           1     (0, 13) is different from previous
  3          ana             1     (0, 13) is same as previous
  0          banana          2     (1, 0) is different from previous
  2          nana            3     (13, 0) is different from previous
  4          na              3     (13, 0) is same as previous
For every suffix str[i], also store rank of next suffix at str[i + 2]. If there is no next suffix at i + 2, we store next rank as -1

Index       Suffix          Rank        Next Rank
  5          a               0             -1
  1          anana           1              1
  3          ana             1              0
  0          banana          2              3
  2          nana            3              3
  4          na              3              -1
Sort all Suffixes according to rank and next rank.

Index       Suffix          Rank        Next Rank
  5          a               0             -1
  3          ana             1              0
  1          anana           1              1
  0          banana          2              3
  4          na              3             -1
  2          nana            3              3

 */