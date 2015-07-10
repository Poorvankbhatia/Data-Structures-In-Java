/*

Find the minimum distance between two numbers
Given an unsorted array arr[] and two numbers x and y, 
find the minimum distance between x and y in arr[].
 The array might also contain duplicates. You may assume that both x and y are different and present in arr[].

Examples:
Input: arr[] = {1, 2}, x = 1, y = 2
Output: Minimum distance between 1 and 2 is 1.

Input: arr[] = {3, 4, 5}, x = 3, y = 5
Output: Minimum distance between 3 and 5 is 2.

Input: arr[] = {3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3}, x = 3, y = 6
Output: Minimum distance between 3 and 6 is 4.

Input: arr[] = {2, 5, 3, 5, 4, 4, 2, 3}, x = 3, y = 2
Output: Minimum distance between 3 and 2 is 1.

 */

package arrays;

/**
 * Created by poorvank on 7/10/15.
 */
public class MinDistanceElements {
    
    public static void main(String[] args) {
        
        int[] arr = new int[]{3, 5, 4, 2, 6, 5, 6, 6, 5, 4, 8, 3};
        
        int x=3,y=6,n=arr.length;
        int prev = 0;
        int minDistance = Integer.MAX_VALUE;
        
        for (int i = 0;i<n;i++) {
            
            if(arr[i]==x || arr[i]==y) {
                prev = i;
                break;
            }
            
        }
        
        for (int i=prev+1;i<n;i++) {
            
            if(arr[i]==x || arr[i]==y) {
                
                if(arr[prev] !=arr[i] && minDistance>i-prev) {
                    minDistance = i-prev;
                    prev = i;
                }
                else {
                    prev = i;
                }
                
            }
            
        }
        
        System.out.println("Minimum distance between - " + x  + " and " +y + " is -> " + minDistance );
        
    }
    
}


/*

1) Traverse array from left side and stop if either x or y is found. Store index of this first occurrence in a variable
 say prev
2) Now traverse arr[] after the index prev. If the element at current index i matches with either x or y then check if
 it is different from arr[prev]. If it is different then update the minimum distance if needed.
  If it is same then update prev i.e., make prev = i.

 */