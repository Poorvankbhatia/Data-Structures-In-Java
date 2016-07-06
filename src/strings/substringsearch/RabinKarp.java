/*

We compute a hash function for the pattern and then look for a match by using
the same hash function for each possible M-character substring of the text. If we find
a text substring with the same hash value as the pattern, we can check for a match.
This process is equivalent to storing the pattern in a hash table, then doing a search
for each substring of the text, but we do not need to reserve the memory for the hash
table because it would have just one entry.

A string of length M corresponds to an M-digit base-R number. To use a hash
table of size Q for keys of this type, we need a hash function to convert an M-digit base-R
number to an int value between 0 and Q-1.

 */

package strings.substringsearch;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by poorvank on 06/07/16.
 */
public class RabinKarp {

    private String pattern;
    private long patternHash;
    private long largePrime;
    private int R;              //Radix
    private long RM;            //R^(M-1) % Q


    /*

    The constructor computes a hash
value patHash for the pattern; it also computes
the value of RM1mod Q in the variable RM.

     */
    public RabinKarp(String pattern) {

        RM = 1;
        R = 256;
        int M = pattern.length();
        this.pattern = pattern;
        largePrime = largeRandomPrime();
        for (int i=0;i<M-1;i++) {
            RM = (RM*R)%largePrime;
        }
        patternHash = getPatternHash(pattern,M);
    }

    private long largeRandomPrime() {
        BigInteger b = BigInteger.probablePrime(31,new Random());
        return b.longValue();
    }

    private long getPatternHash(String key,int M) {
        long h =0;
        for (int j=0;j<M;j++) {
            h = (R*h + key.charAt(j))%largePrime;
        }

        return h;

    }

    public int search(String text) {

        int N = text.length();
        int M = pattern.length();
        if(N<M) {
            return N;
        }
        long textHash = getPatternHash(text,M);

        if ((patternHash == textHash) && check(text, 0))
            return 0;

        // check for hash match; if hash match, check for exact match
        for (int i = M; i < N; i++) {
            // Remove leading digit, add trailing digit, check for match.
            textHash = (textHash + largePrime - RM*text.charAt(i-M) % largePrime) % largePrime;
            textHash = (textHash*R + text.charAt(i)) % largePrime;

            // match
            int offset = i - M + 1;
            if ((patternHash == textHash) && check(text, offset))
                return offset;
        }

        // no match
        return N;


    }

    // Las Vegas version:
    private boolean check(String txt, int i) {
        for (int j = 0; j < pattern.length(); j++)
            if (pattern.charAt(j) != txt.charAt(i + j))
                return false;
        return true;
    }

    // Monte Carlo version: always return true
    private boolean check(int i) {
        return true;
    }

    public static void main(String[] args) {

        RabinKarp rk = new RabinKarp("rabrabracad");

        System.out.println("Found at = " + rk.search("abacadabrabracabracadabrabrabracad"));

    }


}


/*



A fundamental property of the modulus
operation is that if we take the remainder when divided by Q after each arithmetic
operation, then we get the same answer as if we were to perform all of the arithmetic
operations, then take the remainder
when divided by Q.


The Monte Carlo version of Rabin-Karp substring search is linear-time
and extremely likely to be correct, and the Las Vegas version of Rabin-Karp substring
search is correct and extremely likely to be linear-time.
Discussion: The use of the very large value of Q, made possible by the fact that we
need not maintain an actual hash table, makes it extremely unlikely that a collision
will occur. Rabin and Karp showed that when Q is properly chosen, we get a hash
collision for random strings with probability 1/Q, which implies that, for practical
values of the variables, there are no hash matches when there are no substring
matches and only one hash match if there is a substring match. Theoretically, a text
position could lead to a hash collision and not a substring match, but in practice it
can be relied upon to find a match.


 */