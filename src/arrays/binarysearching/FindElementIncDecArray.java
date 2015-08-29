package arrays.binarysearching;

/**
 * Created by poorvank on 7/13/15.
 */
public class FindElementIncDecArray {

    public static void main(String[] args) {

        int[] array = new int[]{1,2,4,6,8,11,12,15,13,10,8};
        findElementIndex(array, 8, 0, array.length - 1);

    }

    private static void findElementIndex(int[] arr, int key, int low, int high) {

        int pivotIndex = findPivot(arr,low,high);
        
        if(pivotIndex!=-1) {
            
            if(arr[pivotIndex]==key) {
                System.out.println("Key found at " + pivotIndex);
            }
            else {

                int index1 = binarySearch(low,pivotIndex,arr,key,true);
                int index2 = binarySearch(pivotIndex,high,arr,key,false);
                
                System.out.println("Key found at " + index1 + " " + index2);
                
            }
            
        }
    }
    
    
    private static int binarySearch(int low,int high,int[] arr,int key,boolean flag) {
        
        if(low<=high) {
            
            
            int mid = (low+high)/2;

            if(arr[mid]==key) {
                return mid;
            }
            if(flag) {
                if(arr[mid] < key) {
                    return binarySearch(mid+1,high,arr,key,true);
                }
                else {
                    return binarySearch(low,mid-1,arr,key,true);
                }
            }
            else {
                if(arr[mid] < key) {
                    return binarySearch(low,mid-1,arr,key,false);
                }
                else {
                    return binarySearch(mid+1,high,arr,key, false);
                }
            }
            
        }
        
        return -1;
        
    }

    
    private static int findPivot(int[] arr,int low,int high) {
        
        if(low==high) {
            return low;
        }
        
        if(high<low) {
            return -1;
        }
        
        if(low<high) {
            
            int mid = (low+high)/2;
            
            if(arr[mid]>arr[mid+1] && arr[mid]>arr[mid-1]) {
                return mid;
            }
            
            else if(arr[mid]>arr[mid+1] && arr[mid]<arr[mid-1]) {
                return findPivot(arr,low,mid-1);
            }
            else {
                return findPivot(arr,mid+1,high);
            }
            
        }
        
        return -1;
        
    }
    
}
