/*
An obvious method for substring search is to
check, for each possible position in the text at which the pattern could match, whether
it does in fact match.
 */

package strings.substringsearch;

/**
 * Created by poorvank on 29/06/16.
 */
public class BruteForce {

    public int search1(String text,String pattern) {

        int N = text.length();
        int M = pattern.length();

        for (int i=0;i<=N-M;i++) {
            int j;
            for (j=0;j<M;j++) {
                if (text.charAt(i+j)!=pattern.charAt(j)) {
                    break;
                }
            }
            if(j==M) {
                return i;
            }

        }

        return N;

    }

    public int search2(String text,String pattern) {

        int N = text.length();
        int M = pattern.length();
        int i,j;
        //i in this code maintains the value of i+j in the previous code
        for (i=0,j=0; i<N && j<M ; i++) {

            if(text.charAt(i)==pattern.charAt(j)) {
                j++;
            } else {
                i = i-j;
                j=0;
            }

        }

        if(j==M) {
            return i-M;
        } else {
            return N;
        }

    }


    public static void main(String[] args) {

        String text = "abacadabrabracabracadabrabrabracad";
        String pattern = "abracadabra";

        BruteForce brute = new BruteForce();

        System.out.println("Pattern Found at = " + brute.search1(text,pattern));

        text = "abacadabrabracabracadabrabrabracad";
        pattern = "abacad";

        System.out.println("Pattern found at = " + brute.search2(text,pattern));

    }

}


/*

Brute-force substring search requires ~NM character compares to
search for a pattern of length M in a text of length N, in the worst case.
Proof: A worst-case input is when both pattern and text are all As followed by a B.
Then for each of the N  M  1 possible match positions, all the characters in the
pattern are checked against the text, for a total cost of M(N  M  1). Normally M
is very small compared to N, so the total is ~NM.

 */