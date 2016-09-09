/*

Find a pair with the given difference
Given an unsorted array and a number n, find if there exists a pair of elements in the array whose difference is n.

Examples:
Input: arr[] = {5, 20, 3, 2, 50, 80}, n = 78
Output: Pair Found: (2, 80)

Input: arr[] = {90, 70, 20, 80, 50}, n = 45
Output: No Such Pair

 */

package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by poorvank on 4/21/15.
 */
public class FindPair {

    public static void main(String[] args) {

        int[] arr = new int[]{5, 20, 3, 2, 50, 80};
        int diff = 78;

        pairPresent(arr, diff);

        arr = new int[]{90, 70, 20, 80, 50};
        diff = 60;

        pairPresentMap(arr, diff);
    }

    private static boolean pairPresent(int[] arr, int diff) {

        Arrays.sort(arr);

        int i = 0, j = 1;

        while (i < arr.length && j < arr.length) {

            if (arr[j] - arr[i] == diff) {
                System.out.println("Found pair - " + arr[i] + " " + arr[j]);
                return true;
            } else if (arr[j] - arr[i] > diff) {
                i++;
            } else {
                j++;
            }
        }

        System.out.println("No pair found");
        return false;

    }

    private static boolean pairPresentMap(int[] arr,int diff) {

        HashSet<Integer> set = new HashSet<>();

        for (int anArr : arr) {
            set.add(anArr);
        }

        for (int element  : arr) {
            if(set.contains(element-diff)) {
                System.out.println("Elmenents found are : " + element + " " +  (element-diff));
                return true;
            }
        }

        System.out.println("No pair found");
        return false;

    }

}

/*

We can use sorting and Binary Search to improve time complexity to O(nLogn). 
The first step is to sort the array in ascending order. Once the array is sorted, 
traverse the array from left to right, and for each element arr[i], binary search for arr[i] + n in arr[i+1..n-1]. 
If the element is found, return the pair.
Both first and second steps take O(nLogn). So overall complexity is O(nLogn).

The second step of the above algorithm can be improved to O(n). The first step remain same. 
The idea for second step is take two index variables i and j, 
initialize them as 0 and 1 respectively. Now run a linear loop. 
If arr[j] – arr[i] is smaller than n, we need to look for greater arr[j], 
so increment j. If arr[j] – arr[i] is greater than n, 
we need to look for greater arr[i], so increment i. 

Hashmap can also be used. Add all elements to a hashmap. then for every element check if arr[i]+n is present in th hashmap.
O(n)

 */
