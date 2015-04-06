/*

Find the maximum element in an array which is first increasing and then decreasing.

 */

package Arrays;

/**
 * Created by poorvank on 4/6/15.
 */
public class MaxElement {
    
    public static void main(String[] args) {
        
        int[] array = new int[]{8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1};
        System.out.println(modifiedBinarySearch(array,0,array.length-1));
        
    }
    
    private static int modifiedBinarySearch(int[] array,int start,int end) {
        
        if(start == end) {
            return array[start];
        }
        
        if(end == start+1 && array[start]>array[end]) {
            return array[start];
        }
        
        if (end == start+1 && array[start]>array[end]) {
            return array[end];
        }
        
        int mid = (start+end)/2;
        
        if(array[mid]>array[mid+1] && array[mid]>array[mid-1]) {
            return array[mid];
        }
        
        else if(array[mid] > array[mid+1] && array[mid]<array[mid-1]) {
            return modifiedBinarySearch(array,start,mid-1);
        }
        
        else {
            return modifiedBinarySearch(array,mid+1,end);
        }
        
    }
    
}


/*

We can modify the standard Binary Search algorithm for the given type of arrays.
i) If the mid element is greater than both of its adjacent elements, then mid is the maximum.
ii) If mid element is greater than its next element and smaller than the previous element then maximum lies on left side
 of mid. Example array: {3, 50, 10, 9, 7, 6}
iii) If mid element is smaller than its next element and greater than the previous element then maximum lies on right side
 of mid. Example array: {2, 4, 6, 8, 10, 3, 1}

 */