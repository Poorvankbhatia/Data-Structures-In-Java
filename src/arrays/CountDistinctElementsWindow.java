/*

Count distinct elements in every window of size k
Given an array of size n and an integer k, return the of count of distinct numbers in all windows of size k.

Example:

Input:  arr[] = {1, 2, 1, 3, 4, 2, 3};
            k = 4
Output:
3
4
4
3

Explanation:
First window is {1, 2, 1, 3}, count of distinct numbers is 3
Second window is {2, 1, 3, 4} count of distinct numbers is 4
Third window is {1, 3, 4, 2} count of distinct numbers is 4
Fourth window is {3, 4, 2, 3} count of distinct numbers is 3

 */

package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by poorvank on 8/8/15.
 */
public class CountDistinctElementsWindow {

    public static void main(String[] args) {

        int[] array = new int[]{1, 2, 1, 3, 4, 2, 3};
        int k = 4;

        Map<Integer, Integer> map = new HashMap<>();
        int distinctCount = 0;
        int n = array.length;

        for (int i = 0; i < k; i++) {

            if (!map.containsKey(array[i])) {
                map.put(array[i], 1);
                distinctCount++;
            } else {
                int val = map.get(array[i]);
                map.put(array[i], val + 1);
            }

        }

        System.out.print(distinctCount + " ");

        for (int i = k; i < n; i++) {

            if (map.get(array[i - k]) == 1) {
                map.remove(array[i - k]);
                distinctCount--;
            } else {
                int val = map.get(array[i - k]);
                map.put(array[i - k], val - 1);
            }

            if (map.get(array[i]) != null) {
                int val = map.get(array[i]);
                map.put(array[i], val + 1);
            } else {
                map.put(array[i], 1);
                distinctCount++;
            }

            System.out.print(distinctCount + " ");

        }

    }

}


/*

An Efficient Solution is to use the count of previous window, while sliding the window. 
The idea is to create a hash map that stores elements of current widow. When we slide the window, 
we remove an element from hash and add an element. We also keep track of distinct elements. Below is algorithm.

1) Create an empty hash map. Let hash map be hM

2) Initialize distinct element count ‘dist_count’ as 0.

3) Traverse through first window and insert elements of first window to hM. The elements are used 
as key and their counts as value in hM. Also keep updating ‘dist_count’

4) Print ‘dist_count’ for first window.

3) Traverse through remaining array (or other windows).
….a) Remove the first element of previous window.
…….If the removed element appeared only once
…………..remove it from hM and do “dist_count–”
…….Else (appeared multiple times in hM)
…………..decrement its count in hM

….a) Add the current element (last element of new window)
…….If the added element is not present in hM
…………..add it to hM and do “dist_count++”
…….Else (the added element appeared multiple times)
…………..increment its count in hM

 */