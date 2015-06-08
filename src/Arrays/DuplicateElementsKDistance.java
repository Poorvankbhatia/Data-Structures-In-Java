/*


Given an unsorted array that may contain duplicates. 
Also given a number k which is smaller than size of array. 
Write a function that returns true if array contains duplicates within k distance.

Examples:

Input: k = 3, arr[] = {1, 2, 3, 4, 1, 2, 3, 4}
Output: false
All duplicates are more than k distance away.

Input: k = 3, arr[] = {1, 2, 3, 1, 4, 5}
Output: true
1 is repeated at distance 3.

Input: k = 3, arr[] = {1, 2, 3, 4, 5}
Output: false

Input: k = 3, arr[] = {1, 2, 3, 4, 4}
Output: true

 */

package Arrays;

import java.util.HashSet;

/**
 * Created by poorvank on 6/8/15.
 */
public class DuplicateElementsKDistance {
    
    public static void main(String[] args) {
        
        int[] arr = new int[]{1, 2, 3, 1, 4, 5};
        
        int k = 3;
        
        System.out.println(findDuplicate(arr,k));
        
    }
    
    private static boolean findDuplicate(int[] arr,int k) {

        HashSet<Integer> set  =new HashSet<>();
        
        for (int i=0;i<arr.length;i++) {
            
            if(set.contains(arr[i])) {
                return true; 
            }
            
            set.add(arr[i]);
            
            if(i>=k) {
                set.remove(arr[i-k]);
            }
            
            
        }
        
        return false;
        
    }
    
}

/*

We can solve this problem in Θ(n) time using Hashing. The idea is to one by add elements to hash. We also remove elements which are at more than k distance from current element. Following is detailed algorithm.

1) Create an empty hashtable.
2) Traverse all elements from left from right. Let the current element be ‘arr[i]’
….a) If current element ‘arr[i]’ is present in hashtable, then return true.
….b) Else add arr[i] to hash and remove arr[i-k] from hash if i is greater than or equal to k

 */
