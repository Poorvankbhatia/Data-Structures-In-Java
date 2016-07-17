package strings.stringsorts;

import java.util.Arrays;

/**
 * Created by poorvank on 09/07/16.
 */
public class KeyIndexCounting {

    private static final int R = 256;

    /*
         Fills the last occurrence of the character in the output first.
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

        //Read the example below
        for (int i=arr.length-1;i>=0;i--) {
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



To understand why counting sort is stable, you need to understand that counting sort can not only be used for sorting a list of integers,
 it can also be used for sorting a list of elements whose key is an integer, and these elements will be sorted by their keys while having
 additional information associated with each of them.

A counting sort example that sorts elements with additional information will help you to understand this. For instance, we want to sort
three stocks by their prices:

[(GOOG 3), (CSCO 1), (MSFT 1)]
Here stock prices are integer keys, and stock names are their associated information.

Expected output for the sorting should be:

[(CSCO 1), (MSFT 1), (GOOG 3)]
(containing both stock price and its name,
and the CSCO stock should appear before MSFT so that it is a stable sort)
A counts array will be calculated for sorting this (let's say stock prices can only be 0 to 3):

counts array: [0, 2, 0, 1] (price "1" appear twice, and price "3" appear once)
If you are just sorting an integer array, you can go through the counts array and output "1" twice and "3" once and it is done,
and the entire counts array will become an all-zero array after this.

But here we want to have stock names in sorting output as well. How can we obtain this additional information (it seems the counts array
already discards this piece of information)? Well, they are stored in the original unsorted array.
In the unsorted array [(GOOG 3), (CSCO 1), (MSFT 1)], we have both the stock name and its price available.
If we get to know which position (GOOG 3) should be in the final sorted array, we can copy this element to the sorted position in the sorted array.

To obtain the final position for each element in the sorted array, unlike sorting an integer array,
you don't use the counts array directly to output the sorted elements. Instead, counting sort has an additional
step which calculates the cumulative sum array from the counts array:

counts array: [0, 2, 2, 3] (i from 0 to 3: counts[i] = counts[i] + counts[i - 1])
This cumulative sum array tells us, if a $1 price stock appears at the first time, it should be outputted to the
second position of the sorted array and if a $3 price stock appears at the first time, it should be outputted to the
third position of the sorted array. If a $1 stock appears and its element gets copied to the sorted array, we will
decreased its count in the counts array.

counts array: [0, 1, 2, 3]
(so that the second appearance of $1 price stock's position will be 1)
So we can iterate the unsorted array from backwards (this is important to ensure the stableness), check its position in the
sorted array according to the counts array, and copied it to the sorted array.

sorted array: [null, null, null]
counts array: [0, 2, 2, 3]


iterate stocks in unsorted stocks from **backwards**
1. the last stock (MSFT 1)
sorted array: [null, (MSFT 1), null] (copy to the second position because counts[1] == 2)
counts array: [0, 1, 2, 3] (decrease counts[1] by 1)

2. the middle stock (CSCO 1)
sorted array: [(CSCO 1), (MSFT 1), null] (copy to the first position because counts[1] == 1 now)
counts array: [0, 0, 2, 3] (decrease counts[1] by 1)

3. the first stock (GOOG 3)
sorted array: [(CSCO 1), (MSFT 1), (GOOG 3)] (copy to the third position because counts[3] == 3)
counts array: [0, 0, 2, 2] (decrease counts[3] by 1)

As you can see, after the array gets sorted, the counts array (which is [0, 0, 2, 2]) doesn't become an all-zero array
like sorting an array of integers. The counts array is not used to tell how many times an integer appears in the unsorted array,
instead, it is used to tell which position the element should be in the final sorted array. And since we decrease the count every
time we output an element, we are essentially making the elements with same key's next appearance final position smaller.
**That's why we need to iterate the unsorted array from backwards to ensure its stableness.**

Since each element contains not only an integer as key, but also some additional information, even if their key is the same,
you could tell each element is different by using the additional information, so you will be able to tell if it is a stable sorting
algorithm (yes, it is a stable sorting algorithm if implemented appropriately).



 */
