/*

Given a function rev(int i) which reverses the segment of array ar[] from 0-i, Implement a function sort() using rev().

 */

package arrayPrograms;

import java.util.Arrays;

/**
 * Created by poorvank on 7/5/15.
 */
public class PancakeSorting {
    
    public static void main(String[] args) {
        
        int[] array = new int[]{23, 10, 20, 11, 12, 6, 7};
        
        sort(array);
        
        System.out.println(Arrays.toString(array));
        
    }
    
    private static void sort(int[] array) {
        
        int n = array.length;
        
        for (int currentSize = n; currentSize > 1 ; --currentSize) {
            
            int maxIndex = findMax(array,currentSize);


            // Move the maximum element to end of current array if it's not
            // already at the end
            if(maxIndex!=currentSize-1) {

                // To move at the end, first move maximum number to beginning
                flip(array,maxIndex);

                // Now move the maximum number to end by reversing current array
                flip(array,currentSize-1);
                
            }
            
        }
        
    }
    
    private static int findMax(int[] array,int n) {
        
        int maxIndex = 0;
        
        for (int i=1;i<n;i++) {
            
            if(array[maxIndex] < array[i]) {
                maxIndex = i;
            }
            
        }
        
        return maxIndex;
        
    }
    
    private static void flip(int[] array,int i) {
        
        int start = 0;
        
        while (start<i) {
            
            int temp = array[start];
            array[start] = array[i];
            array[i] = temp;
            start++;
            i--;
            
        }
        
        
    }
    
}


/*


Following are the detailed steps. Let given array be arr[] and size of array be n.
1) Start from current size equal to n and reduce current size by one while it’s greater than 1. 
Let the current size be curr_size. Do following for every curr_size
……a) Find index of the maximum element in arr[0..curr_szie-1]. Let the index be ‘mi’
……b) Call flip(arr, mi)
……c) Call flip(arr, curr_size-1)



 */