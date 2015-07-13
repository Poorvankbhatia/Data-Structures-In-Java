package arrays;

/**
 * Created by poorvank on 7/13/15.
 */
public class FindElementPivotedRotatedArray {
    
    public static void main(String[] args) {

        int[] array = new int[]{11, 12, 15, 18, 2, 5, 6, 8};
        System.out.println("index of 12 is - " + findElementIndex(array,12,0,array.length-1));
        
        
    }
    
    private static int findElementIndex(int[] arr,int key,int low,int high) {
        
        
        while (low<=high) {
            
            int mid = (low) + (high-low)/2;
            
            if(arr[mid]==key) {
                return mid;
            }
            
            /*
            
              Lower half is sorted
            
             */
            
            if(arr[low] <= arr[mid]) {
                if(arr[low]<=key && key<arr[mid]) {
                    high = mid - 1;
                }
                else {
                    low = mid + 1;
                }
            }
            
            /*
            
              Upper Half is sorted
            
             */

            if(arr[mid] <= arr[high]) {
                if(arr[mid]<=key && key<arr[high]) {
                    low = mid + 1;
                }
                else {
                    high = mid-1;
                }
            }
            
        }
        
        return -1;
        
        
    }
    
}
