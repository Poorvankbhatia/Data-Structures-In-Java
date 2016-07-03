package strings.substringsearch;

/**
 * Created by poorvank on 03/07/16.
 */
public class BoyerMoore {

    private int R;
    private String pattern;
    int[] right;

    public BoyerMoore(String pattern) {
        this.R = 256;
        this.pattern = pattern;

        right = new int[R];
        for (int i=0;i<R;i++) {
            right[i] = -1;
        }

        for (int j=0;j<pattern.length();j++) {
            right[pattern.charAt(j)] = j;
        }
    }

    public int search(String text) {

        int N = text.length();
        int M = pattern.length();
        int skip;
        for (int i=0;i<=N-M;i+=skip) {
            skip = 0;
            for (int j=M-1;j>=0;j--) {
                if(pattern.charAt(j)!=text.charAt(i+j)) {
                    skip = Math.max(1,j-(right[text.charAt(i+j)]));
                    break;
                }
            }
            if(skip==0) {
                return i;
            }
        }
        return N;
    }

    public static void main(String[] args) {

        BoyerMoore bm = new BoyerMoore("rabrabracad");

        System.out.println("Found at = " + bm.search("abacadabrabracabracadabrabrabracad"));

    }

}

/*

We have an index
i moving from left to right through the text and an index j moving from right to left
through the pattern. The inner loop tests whether the pattern aligns with the text at
position i. If txt.charAt(i+j) is equal to pat.charAt(j) for all j from M-1 down to
0, then there is a match. Otherwise, there is a character mismatch, and we have one of
the following three cases:
■ If the character causing the mismatch is
not found in the pattern, we can slide the
pattern j+1 positions to the right (increment
i by j+1). Anything less would align
that character with some pattern character.
Actually, this move aligns some known
characters at the beginning of the pattern
with known characters at the end of the
pattern so that we could further increase
i by precomputing a KMP-like table
■ If the character c causing the mismatch is
found in the pattern, we use the right[] array to line up the pattern with the
text so that character will match its rightmost occurrence in the pattern. To do
so, we increment i by j minus right[c]. Again, anything less would align that
text character with a pattern character it could not match (one to the right of its
rightmost occurrence)
■ If this computation would not increase
i, we just increment i instead,
to make sure that the pattern always
slides at least one position to the
right.



Note that the
convention of using -1 in the right[]
array entries corresponding to characters
that do not appear in the pattern unifies
the first two cases (increment i by
j - right[txt.charAt(i+j)]).

On typical inputs, substring search with the Boyer-Moore mismatched
character heuristic uses ~N
M character compares to search for a pattern of length
M in a text of length N.
Discussion: This result can be proved for various random string models, but such
models tend to be unrealistic, so we shall skip the details. In many practical situations
it is true that all but a few of the alphabet characters appear nowhere in the
pattern, so nearly all compares lead to M characters being skipped, which gives the
stated result.

The longer the pattern gets the faster the search gets.

Worst case:

Can be as bad as ~NM


Example :
      test  B B B B B B B B B B B B
      pat   A B B B B
              A B B B B
                A B B B B

 */