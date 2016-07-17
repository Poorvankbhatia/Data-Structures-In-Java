/*
Top-down mergesort:
recursive
mergesort implementation based on this abstract inplace
merge. It is one of the best-known examples of the
utility of the divide-and-conquer paradigm for efficient
algorithm design. This recursive code is the basis for an
inductive proof that the algorithm sorts the array: if it
sorts the two subarrays, it sorts the whole array, by merging
together the subarrays.
To understand mergesort, it is worthwhile to consider
carefully the dynamics of the method calls, shown in the
trace at right. To sort a[0..15], the sort() method calls
itself to sort a[0..7] then calls itself to sort a[0..3]
and a[0..1] before finally doing the first merge of a[0]
with a[1] after calling itself to sort a[0] and then a[1]
(for brevity, we omit the calls for the base-case 1-entry
sorts in the trace). Then the next merge is a[2] with a[3]
and then a[0..1] with a[2..3] and so forth. From this
trace, we see that the sort code simply provides an organized
way to sequence the calls to the merge() method.

 */

package arrays.sorting;

import utility.SortFunctions;

import java.util.Arrays;

/**
 * Created by poorvank on 8/1/15.
 */
public class Merge {

    private static Comparable[] aux;

    public static void main(String[] args) {

        Integer[] array = new Integer[]{12, 11, 13, 5, 6, 7};

        System.out.println("Array before - " + Arrays.toString(array));
        sort(array);

        System.out.println("Array after -  " + Arrays.toString(array));

    }

    public static void sort(Comparable[] array) {

        aux = new Comparable[array.length];
        sort(array,0,array.length-1);
    }

    private static void sort(Comparable[] array,int lo,int hi) {

        if(hi<=lo) {
            return;
        }
        int mid = (lo+hi)/2;
        sort(array,lo,mid);
        sort(array,mid+1,hi);
        merge(array,lo,mid,hi);
    }

    public static void merge(Comparable[] array,int lo,int mid,int hi) {
        int i = lo,j=mid+1;

        for (int k=lo;k<=hi;k++) {
            aux[k] = array[k];
        }

        for (int k=lo;k<=hi;k++) {
            if(i>mid) {
                array[k] = aux[j++];
            } else if(j>hi) {
                array[k] = aux[i++];
            } else if(SortFunctions.less(aux[i],aux[j])) {
                array[k] = aux[i++];
            } else {
                array[k] = aux[j++];
            }
        }
    }


}

/*

O(nlogn)

Top-down mergesort uses between ½ N lg N and N lg N compares to
sort any array of length N.
Proof: Let C(N) be the number of compares needed to sort an array of length N.
We have C(0) = C(1) = 0 and for N > 0 we can write a recurrence relationship that
directly mirrors the recursive sort() method to establish an upper bound:
C(N ) <= C(⎣N/2⎦) + C(⎡N/2⎤) + N
The first term on the right is the number of compares to sort the left half of the array,
the second term is the number of compares to sort the right half, and the third
term is the number of compares for the merge. The lower bound
C(N ) <= C(⎣N/2⎦) + C(⎡N/2⎤) + ⎣N/2⎦
follows because the number of compares for the merge is at least ⎣N/2⎦ .

 */
