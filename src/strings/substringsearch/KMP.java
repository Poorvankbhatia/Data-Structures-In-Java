/*

Backing up the pattern pointer. In KMP substring
search, we never back up the text pointer
i, and we use an array dfa[][] to record how
far to back up the pattern pointer j when a
mismatch is detected. For every character c,
dfa[c][j] is the pattern position to compare
against the next text position after comparing
c with pat.charAt(j). During the search,
dfa[txt.charAt(i)][j] is the pattern position
to compare with txt.charAt(i+1) after comparing
txt.charAt(i) with pat.charAt(j).
For a match, we want to just move on to the
next character, so dfa[pat.charAt(j)][j] is
always j+1. For a mismatch, we know not just
txt.charAt(i), but also the j-1 previous characters
in the text: they are the first j-1 characters
in the pattern. For each character c, imagine that
we slide a copy of the pattern over these j characters
(the first j-1 characters in the pattern followed
by c—we are deciding what to do when
these characters are txt.charAt(i-j+1..i)),
from left to right, stopping when all overlapping
characters match (or there are none).
This gives the next possible place the pattern
could match. The index of the pattern character
to compare with txt.charAt(i+1)
(dfa[txt.charAt(i)][j]) is precisely the
number of overlapping characters.

 */

package strings.substringsearch;

/**
 * Created by poorvank on 01/07/16.
 */
public class KMP {

    private final int R = 256;
    private int[][] dfa;
    private String pattern;



    public KMP(String pattern) {
        this.pattern = pattern;
        int M = pattern.length();
        dfa = new int[R][M];
        dfa[pattern.charAt(0)][0] = 1;
        for (int X=0,j=1;j<M;j++) {
            for (int c=0;c<R;c++) {
                dfa[c][j] = dfa[X][j];
            }
            dfa[pattern.charAt(j)][j] = j+1;
            /*
            We run simulation one character at a time ,as building machine, all we need to do is keep track of
            state that we would be at if we had run DFA on a pattern shifted over 1
             */
            X = dfa[pattern.charAt(j)][X];
        }
    }

    public int search(String text) {
        int N = text.length();
        int M = pattern.length();
        int i,j;
        for (i=0,j=0;i<N && j<M;i++) {
            /*
            During the search,
            dfa[txt.charAt(i)][j] is the pattern position
            to compare with txt.charAt(i+1) after comparing
            txt.charAt(i) with pat.charAt(j).

             */
            j = dfa[text.charAt(i)][j];
        }

        if(j==M) {
            return i-M;
        } else {
            return N;
        }
    }

    public static void main(String[] args) {

        String text = "abacadabrabracabracadabrabrabracad";
        String pattern = "rab";

        KMP kmp = new KMP(pattern);

        System.out.println("Pattern Found at = " + kmp.search(text));

        text = "abacadabrabracabracadabrabrabracad";
        pattern = "abacad";

        kmp = new KMP(pattern);

        System.out.println("Pattern found at = " + kmp.search(text));

    }

}


/*

Videos to watch  :
https://www.youtube.com/watch?v=iZ93Unvxwtw


pattern  : ABABAC
When you're matching the input string, you can only get into state 5 after matching the first 5 characters of the pattern,
and the first 5 characters of the pattern are ABABA. So no matter which input string you use, you know that the text preceding state 5 is
"ABABA".

So you if you get a mismatch in state 5, you could back up 4 characters and try matching again. But since you know what text has
to appear before state 5, you don't actually need the input text to figure out what would happen.
You can figure out beforehand what state you'd end up in when you got back to the same place.

backup 4 characters and go to state 0:

0 : BABA

A doesn't match, so advance and go to state 0

0: ABA

A matches, so go to state 1

1: BA

B matches, go to state 2

2: A

A matches, go to state 3

3:

now we're back to the place in the input where we saw state 5 before, but now we're in state 3.
This will always happen when we get a mismatch in state 5, so instead of actually doing this,
we just make a note that says "when we get a mismatch in state 5, go to state 3".
Note that most KMP implementations will actually make a failure table where failure_table[5]=3.
Your example implementation is building the full DFA[char][state] instead, so it copies all the transitions from state 3 into
state 5 for the failure cases. That says "when we get a mismatch in state 5, do whatever state 3 does", which works out the same.

UNDERSTAND EVERYTHING ABOVE BEFORE MOVING ON

Now let's speed up the calculation of those failure states...
When we get a mismatch in state 5, we can use the DFA we have so far to figure out what would happen
if we backed up and rescanned the input starting at the next possible match, by applying the DFA to "BABA".
We end up in state 3, so let's call state 3 the "failure state" for state 5.

It looks like we have to process 4 pattern charcters to calculate the failure state for state 5, but we already
did most of that work when we calculated the failure state for state 4 -- we applied the DFA to "BAB" and ended up in state 2.
So to figure out the failure state for state 5, we just start in the failure state for state 4 (state 2), and process the
next character in the pattern -- the "A" that came after state 4 in the input.

Knuth-Morris-Pratt substring search accesses no more than M  N
characters to search for a pattern of length M in a text of length N.
Proof. Immediate from the code: we access each pattern character once when computing
dfa[][] and each text character once (in the worst case) in search().

Another parameter comes into play: for an R-character alphabet, the total running time
(and space) required to build the DFA is proportional to MR. It is possible to remove
the factor of R by building a DFA where each state has a match transition and a mismatch
transition (not transitions for each possible character),




 */
