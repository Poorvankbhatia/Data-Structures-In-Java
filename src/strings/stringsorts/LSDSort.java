package strings.stringsorts;

import java.util.Arrays;

/**
 * Created by poorvank on 09/07/16.
 */
public class LSDSort {

    private static final int R = 256;

    public static void sort(String[] array,int w) {

        int n  = array.length;
        String[] aux = new String[n];

        for (int d=w-1;d>=0;d--) {

            int[] count = new int[R];
            for (int i=0;i<n;i++) {
                count[array[i].charAt(d)]++;
            }

            for (int i=1;i<R-1;i++) {
                count[i] += count[i-1];
            }

            // if we do a forward iteration, it won't remain stable. Read example in Key index Counting method
            // Consider the case ["ab","ac"] where a's count comes to be 2
            for (int i=n-1;i>=0;i--) {
                aux[count[array[i].charAt(d)]-1] = array[i];
                count[array[i].charAt(d)]--;
            }

            for (int i=0;i<n;i++) {
                array[i] = aux[i];
            }

        }

    }


    /*TODO 32 bit integer Sort:
    http://algs4.cs.princeton.edu/51radix/LSD.java.html*/


    public static void main(String[] args) {

        String[] arr = new String[]{"abb","abc","jah","efk","did","ueo","asw"};

        LSDSort.sort(arr,3);
        System.out.println(Arrays.toString(arr));

    }

}

/*

If the strings are each of length W,
we sort the strings W times with key-indexed counting, using
each of the positions as the key, proceeding from right to left

LSD string sort stably sorts fixed-length strings.
Proof: This fact depends crucially on the key-indexed counting implementation
being stable. After sorting keys on their i trailing
characters (in a stable manner), we know that any two keys appear in proper order
in the array (considering just those characters) either because the first of their i
trailing characters is different, in which case the sort on that character puts them in
order, or because the first of their ith trailing characters is the same, in which case
they are in order because of stability (and by induction, for i-1).

Another way to state the proof is to think about the future: if the characters that have
not been examined for a pair of keys are identical, any difference between the keys is restricted
to the characters already examined, so the keys have been properly ordered and
will remain so because of stability. If, on the other hand, the characters that have not
been examined are different, the characters already examined do not matter,
and a later pass will correctly order the pair based on the more significant
differences.

LSD string sort uses ~7WN+3WR array accesses and
extra space proportional to N+R to sort N items whose keys are W-character
strings taken from an R-character alphabet.
Proof: The method is W passes of key-indexed counting, except that the aux[] array
is initialized just once.


For typical applications, R is far smaller than N, so the total
running time is proportional to WN. An input array of N strings that each have W
characters has a total of WN characters, so the running time of LSD string sort is linear
in the size of the input.

 */