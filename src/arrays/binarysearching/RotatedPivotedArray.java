/*


There is a rotated and pivoted array. Write the code to determine how many times the array is rotated.
No duplicates should be there

 */

package arrays.binarysearching;

/**
 * Created by poorvank on 7/13/15.
 */
public class RotatedPivotedArray {

    public static void main(String[] args) {

        int[] array = new int[]{5, 6, 1, 2, 3, 4};
        System.out.println("array is rotated - " + findPivotIndex(array, 0, array.length - 1) + " times");

    }

    /**
     * A modification of binary search
     *
     * @param array
     * @return
     */
    private static int findPivotIndex(int[] array, int low, int high) {

        int mid = (low + high) / 2;
        
        /*
        
         If the low index is smaller than the high index than we can safely assume that the array is sorted
         and the minimum element is at low position
        
         */
        if (array[low] <= array[high]) {
            return low;
        }
        int next = (mid + 1) % (array.length);//in case the mid index is the last index
        int previous = (mid - 1 + array.length) % (array.length); // in case it is the first element
        //length is added in case - 1 returns a negative value

        if ((mid == array.length - 1 || array[mid] <= array[mid + 1]) && (mid == 0 || array[mid] <= array[mid - 1])) {
            return mid;
        }
        
        /*
        
        It means that array from mid to high is sorted and pivot element cannot lie is in that zone
        
         */
        if (array[mid] <= array[high]) {
            return findPivotIndex(array, low, mid - 1);
        }

        /*

        It means that array from mid to high is sorted and pivot element cannot lie is in that zone

         */
        if (array[mid] >= array[low]) {
            return findPivotIndex(array, mid + 1, high);
        }

        return -1;

    }

}

/*

https://www.youtube.com/watch?v=4qjprDkJrjY

 */
