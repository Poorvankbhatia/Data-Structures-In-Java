package strings.stringsorts;

import java.util.Arrays;

/**
 * Created by poorvank on 09/07/16.
 */
public class KeyIndexCounting {

    private static final int R = 256;

    /*
         Fills the first occurrence of the character in the output first.
     */
    public static void sortMethod1(char[] arr) {

        int[] count = new int[R];
        char[] aux = new char[arr.length];

        for (int i=0;i<arr.length;i++) {
            count[arr[i]]++;
        }

        for (int i=1;i<R;i++) {
            count[i] += count[i-1];
        }

        for (int i=0;i<arr.length;i++) {
            aux[count[arr[i]]-1] = arr[i];
            --count[arr[i]];
        }

        for (int i=0;i<arr.length;i++) {
            arr[i] = aux[i];
        }
    }

    /*
            Fills the first occurrence of the character in the output first.
     */
    public static void sortMethod2(char[] arr) {

        int[] count = new int[R+1];
        char[] aux = new char[arr.length];

        for (int i=0;i<arr.length;i++) {
            count[arr[i]+1]++;
        }

        for (int i=0;i<R;i++) {
            count[i+1] += count[i];
        }

        for (int i=0;i<arr.length;i++) {
            aux[count[arr[i]]] = arr[i];
            count[arr[i]]++;
        }

        for (int i=0;i<arr.length;i++) {
            arr[i] = aux[i];
        }

    }




    public static void main(String[] args) {

        char[] arr = new char[]{'g','e','e','k','s'};
        KeyIndexCounting.sortMethod2(arr);
        System.out.println(Arrays.toString(arr));

        arr = new char[]{'p','o','o','r','v'};
        KeyIndexCounting.sortMethod1(arr);
        System.out.println(Arrays.toString(arr));

    }


}


/*

We Transform counts to indices, so if there are three items with key
1 and five items with key 2, then the items with key
3 start at position 8 in the sorted array.

Proposition A. Key-indexed counting uses 8N + 3R + 1 array accesses to stably
sort N items whose keys are integers between 0 and R  1.

Proof: Immediate from the code. Initializing the arrays uses N+R+1 array accesses.
The first loop increments a counter for each of the N items (2N array accesses);
the second loop does R additions (2R array accesses); the third loop does
N counter increments and N data moves (3N array accesses); and the fourth loop
does N data moves (2N array accesses). Both moves preserve the relative order of
equal keys.

key-indexed counting does no
compares (it accesses data only through key()).
When R is within a constant factor of N, we have
a linear-time sort.

 */
