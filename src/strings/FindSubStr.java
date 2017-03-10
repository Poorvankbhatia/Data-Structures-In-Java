package strings;/*

Consider a string, s = "abc". An alphabetically-ordered sequence of substrings of s would be {"a", "ab", "abc", "b", "bc", "c"}.
If we reduce this sequence to only those substrings that start with a vowel and end with a consonant, we're left with {"ab", "abc"}.
The alphabetically first element in this reduced list is "ab", and the alphabetically last element is "abc". As a reminder:
Vowels: a, e, i, o, and u.
Consonants: b, c, d, f, g, h, j, k, l, m, n, p, q, r, s, t, v, w, x, y, and z.

Complete the findSubstrings function in your editor. It has 1 parameter: a string, s, consisting of lowercase English letters (a − z).
The function must find the substrings of s that start with a vowel and end with a consonant, then print the alphabetically first and
alphabetically last of these substrings.

Input Format
The locked stub code in your editor reads a single string, s, from stdin and passes it to your function.

Constraints
3 ≤ length of s ≤ 5 × 10^5

Output Format
Your function must print two lines of output denoting the alphabetically first and last substrings of s that start with a vowel and
end with a consonant. Print the alphabetically first qualifying substring on the first line, and the alphabetically last qualifying
substring on the second line.

Sample Input 1
aba

Sample Output 1
ab
ab

Explanation 1
"ab" is the only possible substring which starts with a vowel (a) and ends with a consonant (b). Because we only have 1 qualifying substring, "ab" is both the alphabetically first and last qualifying substring and we print it as our first and second lines of output.

Sample Input 2
aab

Sample Output 2
aab
ab

Explanation 2
There are 2 possible substrings which start with a vowel and end with a consonant: "aab" and "ab". When ordered alphabetically, "aab"
comes before "ab". This means that we print "aab" (the alphabetically first qualifying substring) as our first line of output, and
we print "ab" (the alphabetically last qualifying substring) as our second line of output.

Sample Input 3
rejhiuecumovsutyrulqaeuouiecodjlmjeaummaoqkexylwaaopnfvlbiiiidyckzfhe

Sample Output 3
aaop
utyrulqaeuouiecodjlmjeaummaoqkexylwaaopnfvlbiiiidyckzfh

Explanation 3
There are 4830 substrings of s, but only 676 of them start with a vowel and end with a consonant. When ordered alphabetically, the first
substring is "aaop" and the last substring is "utyrulqaeuouiecodjlmjeaummaoqkexylwaaopnfvlbiiiidyckzfh"

 */
import java.util.*;

/**
 * Created by poorvank.b on 10/03/17.
 */
public class FindSubStr {

    public static void main(String[] args) {

        findSubstrings("rejhiuecumovsutyrulqaeuouiecodjlmjeaummaoqkexylwaaopnfvlbiiiidyckzfhe");

    }

    private static class Suffix implements Comparable<Suffix> {
        int originalIndex;
        int startHalf;
        int endhalf;
        @Override
        public int compareTo(Suffix arg) {
            if (arg.startHalf != startHalf)
                return startHalf - arg.startHalf;
            return endhalf - arg.endhalf;
        }
        Suffix(int id, int fh, int sh) {
            originalIndex = id;
            startHalf = fh;
            endhalf = sh;
        }
    };

    static int[] createSuffixArray(String str) {
        int n = str.length();
        int suffixRank[] = new int[n];
        Suffix sa[] = new Suffix[n];
        for (int i = 0; i < n;i++) {
            suffixRank[i] = str.charAt(i) -'a';
            sa[i] = new Suffix(i,suffixRank[i],0);
        }
        for (int c = 1 ; c < n; c *= 2) {

            for(int i = 0; i < n; i++) {
                sa[i].startHalf = suffixRank[i];
                sa[i].endhalf = i + c < n ? suffixRank[i + c] : -1;
                sa[i].originalIndex = i;
            }

            Arrays.sort(sa);

            suffixRank[sa[0].originalIndex] = 0;
            for(int i = 1, currRank = 0; i < n; i++) {
                if(sa[i - 1].startHalf != sa[i].startHalf || sa[i - 1].endhalf != sa[i].endhalf)
                    ++currRank;
                suffixRank[sa[i].originalIndex] = currRank;
            }
        }
        int[] res = new int[n];
        for(int i = 0; i < n; i++)
            res[i] = sa[i].originalIndex;

        return res;
    }

    private static void findSubstrings(String s) {
        if (s.length() >= 2) {
            Set<Character> vowelSet = new HashSet<>((Arrays.asList(new Character[] {'a', 'e', 'u', 'o', 'i'})));
            int last = s.length();
            for (int i = s.length() - 1; i >= 0; i--) {
                if (vowelSet.contains(s.charAt(i))) {
                    last --;
                } else
                    break;
            }
            s = s.substring(0, last);
            int[] sa = createSuffixArray(s);

            int i = 0;

            while (i < sa.length) {
                if (vowelSet.contains(s.charAt(sa[i]))) {
                    break;
                }
                i++;
            }
            if (i < sa.length) {
                String first = s.substring(sa[i]);
                for (i = 0; i < first.length(); i++) {
                    if (!vowelSet.contains(first.charAt(i)))
                        break;
                }
                System.out.println(first.substring(0, i + 1));

                for (i = sa.length - 1; i >= 0; i--) {
                    if (vowelSet.contains(s.substring(sa[i]).charAt(0))) {
                        break;
                    }
                }
                System.out.println(s.substring(sa[i]));
            }
        }
    }

}

/*

First cut all vowels at the end of the string. for example if s = aabaaaae, we can get s = aab, because there is no substring
which starts with vowel and end with consonant after character 'b'.
Then we can generate suffix array of string s.
The last element which starts with vowel in the suffix array is the last substrings of s that start with a vowel and end with a consonant.
The first element from the suffix array which starts with vowel need to be trimmed till to the first consonant and this is the first
substrings of s that start with a vowel and end with a consonant.
Time complexity depends on how suffix array was created

 */