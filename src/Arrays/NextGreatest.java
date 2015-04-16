/*

Replace every element with the next greatest
Given an array of integers, replace every element with the next greatest element (greatest element on the right side)
in the array. Since there is no element next to the last element, replace it with -1. For example,
if the array is {16, 17, 4, 3, 5, 2}, then it should be modified to {17, 5, 5, 5, 2, -1}.

 */

package Arrays;

import java.util.Arrays;

/**
 * Created by poorvank on 4/16/15.
 */
public class NextGreatest {
    
    public static void main(String[] args) {
        
        int[] array = new int[]{16, 17, 4, 3, 5, 2};
        
        int max = array[array.length-1];
        
        array[array.length-1] = -1;
        
        for (int i =array.length-2;i>=0;i--) {

            int a = array[i];
            
            array[i] = max;
            
            if(max < a) {
                max = a;
            }
            
        }
        
        System.out.println(Arrays.toString(array));
        
    }
    
}

/*

A tricky method is to replace all elements using one traversal of the array. 
The idea is to start from the rightmost element, move to the left side one by one, 
and keep track of the maximum element. Replace every element with the maximum element.

 */