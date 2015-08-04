/*

Remove minimum elements from either side such that 2*min becomes more than max
Given an unsorted array, trim the array such that twice of minimum is greater than maximum in the trimmed array. 
Elements should be removed either end of the array.

Number of removals should be minimum.

Examples:

arr[] = {4, 5, 100, 9, 10, 11, 12, 15, 200}
Output: 4
We need to remove 4 elements (4, 5, 100, 200)
so that 2*min becomes more than max.


arr[] = {4, 7, 5, 6}
Output: 0
We don't need to remove any element as 
4*2 > 7 (Note that min = 4, max = 8)

arr[] = {20, 7, 5, 6}
Output: 1
We need to remove 20 so that 2*min becomes
more than max

arr[] = {20, 4, 1, 3}
Output: 3
We need to remove any three elements from ends
like 20, 4, 1 or 4, 1, 3 or 20, 3, 1 or 20, 4, 1

 */

package miscellaneous;

/**
 * Created by poorvank on 8/4/15.
 */
public class RemoveMinimumElements {

    public static void main(String[] args) {

        int[] array = new int[]{4, 5, 100, 9, 10, 11, 12, 15, 200};

        System.out.println("Number of elements removed = " + countRemovedElements(array, array.length));

    }


    /**
     * The idea is to find the maximum sized subarray such that 2*min > max.
     * We run two nested loops, the outer loop chooses a starting point and the inner loop chooses ending point for
     * the current starting point. We keep track of longest subarray with the given property.
     *
     * @param array
     * @param n
     * @return
     */
    private static int countRemovedElements(int[] array, int n) {

        int longestStart = -1, longestEnd = 0;

        for (int start = 0; start < n; start++) {

            int minimum = Integer.MAX_VALUE, maximum = Integer.MIN_VALUE;

            for (int end = start; end < n; end++) {

                int val = array[end];

                if (minimum > val) {
                    minimum = val;
                }
                if (maximum < val) {
                    maximum = val;
                }

                if (minimum * 2 <= maximum) {
                    break;
                }

                if (end - start > longestEnd - longestStart || longestStart == -1) {

                    longestEnd = end;
                    longestStart = start;

                }

            }

        }

        System.out.println(longestStart + " " + longestEnd);

        if (longestStart == -1) {
            return -1;
        }

        return (n - (longestEnd - longestStart + 1));

    }

}


/*

In efficient recurrence relation is :

minRemovals(arr, l+1, h) + 1"
   and "minRemovals(arr, l, h-1) + 1

 */