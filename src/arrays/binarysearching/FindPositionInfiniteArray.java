/*

Find position of an element in a sorted array of infinite numbers
Suppose you have a sorted array of infinite numbers, how would you search an element in the array?

 */

package arrays.binarysearching;

/**
 * Created by poorvank on 8/12/15.
 */
public class FindPositionInfiniteArray {
    
    public static void main(String[] args) {
        
        int[] array = new int[]{3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170};
        int value = 130;
        int x=-1;
        if((x=findPos(array,value))!=-1) {
            System.out.println("Position is - " + x);
        }
        
    }
    
    private static int findPos(int[] array,int value) {
        
        int low = 0;
        int high =1;
        
        while (value>array[high]) {
            
            low = high;
            high = (high*2);
            
        }
        
        return binarySearch(high,low,array,value);
        
    }
    
    private static int binarySearch(int high,int low,int[] arr,int key) {
        
        if(low<high) {

            int mid = (low+high)/2;

            if(arr[mid]==key) {
                return mid;
            }
            else if(arr[mid]>key) {
                return binarySearch(mid-1,low,arr,key);
            }
            else {
                return binarySearch(high,mid+1,arr,key);
            }
            
        }
        
        return -1;
        
    } 
    
}

/*

Since array is sorted, the first thing clicks into mind is binary search,
 but the problem here is that we don’t know size of array.
If the array is infinite, that means we don’t have proper bounds to apply binary search.
 So in order to find position of key, first we find bounds and then apply binary search algorithm.

Let low be pointing to 1st element and high pointing to 2nd element of array, Now compare key with high index element,
->if it is greater than high index element then copy high index in low index and double the high index.
->if it is smaller, then apply binary search on high and low indices found.

 */