/*

Find position of an element in a sorted array of infinite numbers
Suppose you have a sorted array of infinite numbers, how would you search an element in the array?

Source: Amazon Interview Experience.

Since array is sorted, the first thing clicks into mind is binary search, but the problem here is that we don’t 
know size of array.
If the array is infinite, that means we don’t have proper bounds to apply binary search. So in order to find position 
of key, first we find bounds and then apply binary search algorithm.

Let low be pointing to 1st element and high pointing to 2nd element of array, Now compare key with high index element,
->if it is greater than high index element then copy high index in low index and double the high index.
->if it is smaller, then apply binary search on high and low indices found.

 */

package arrays.binarysearching;

/**
 * Created by poorvank on 7/28/15.
 */
public class InfiniteSortedArray {

    public static void main(String[] args) {

        int[] array = new int[]{3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170};

        int x;
        if ((x = findPos(array, 10)) != -1) {
            System.out.println("Found at - " + x);
        }

    }

    private static int findPos(int[] array, int key) {


        int low = 0;
        int high = 1;
        int value = array[low];

        while (value < key) {

            low = high;
            high = 2 * high;
            value = array[high];

        }

        return binarySearch(array, key, low, high);

    }


    private static int binarySearch(int[] array, int key, int low, int high) {

        if (low <= high) {

            int mid = low + (high - low) / 2;

            if (array[mid] == key) {
                return mid;
            } else if (array[mid] > key) {
                return binarySearch(array, key, low, mid - 1);
            }
            return binarySearch(array, key, mid + 1, high);

        }
        return -1;

    }

}

/*

Let p be the position of element to be searched. Number of steps for finding high index ‘h’ is O(Log p). 
The value of ‘h’ must be less than 2*p. The number of elements between h/2 and h must be O(p). 
Therefore, time complexity of Binary Search step is also O(Log p) and overall time complexity is 2*O(Log p) 
which is O(Log p).

 */