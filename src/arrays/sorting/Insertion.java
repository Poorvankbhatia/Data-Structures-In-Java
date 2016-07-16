package arrays.sorting;
import utility.SortFunctions;

/**
 * Created by poorvank.b on 16/07/16.
 */
public class Insertion {

    public static void sort(Comparable[] array) {

        int N = array.length;
        //larger entries are moved to the right one position rather than doing full exchanges(thus cutting the number of array accesses in half)
        for (int i=1;i<N;i++) {
            Comparable key = array[i];
            int j = i-1;
            while (j>=0 && SortFunctions.less(key,array[j])) {
                array[j+1] = array[j];
                j--;
            }
            SortFunctions.assign(array,j+1,key);
        }
    }


    public static void main(String[] args) {

        Integer[] array = new Integer[]{89,0,981,1,6,9,4};
        Insertion.sort(array);
        SortFunctions.print(array);
    }

}

/*

we need to make
space to insert the current item by moving larger items one position to the right, before
inserting the current item into the vacated position.
Unlike that of selection sort, the running time of insertion sort depends on the initial
order of the items in the input. For example, if the array is large and its entries are
already in order (or nearly in order), then insertion sort is much, much faster than if
the entries are randomly ordered or in reverse order.


Insertion sort uses Square(N)/4 compares and Square(N)/4 exchanges to sort
a randomly ordered array of length N with distinct keys, on the average. The worst
case is Square(N)/2 compares and Square(N)/2 exchanges and the best case is N-1 compares
and 0 exchanges.

More generally, we consider the concept of a partially sorted array, as follows: An inversion
is a pair of entries that are out of order in the array. For instance, E X A M P L E
has 11 inversions: E-A, X-A, X-M, X-P, X-L, X-E, M-L, M-E, P-L, P-E, and L-E. If the
number of inversions in an array is less than a constant multiple of the array size, we
say that the array is partially sorted. Typical examples of partially sorted arrays are the
following:
■ An array where each entry is not far from its final position
■ A small array appended to a large sorted array
■ An array with only a few entries that are not in place

The number of exchanges used by insertion sort is equal to the
number of inversions in the array, and the number of compares is at least equal to
the number of inversions and at most equal to the number of inversions plus the
array size minus 1.
Proof: Every exchange involves two inverted adjacent entries and thus reduces the
number of inversions by one, and the array is sorted when the number of inversions
reaches zero. Every exchange corresponds to a compare, and an additional
compare might happen for each value of i from 1 to N-1 (when a[i] does not
reach the left end of the array).

 */