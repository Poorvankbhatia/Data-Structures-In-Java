/*

In one of Amazon fulfillment centers, there are a no. of empty boxes kept in increasing order in a row. 
Kiva robots are designed to put a product in a box. The product size is given. 
Design a program to find the best fit box for given product size. 
First line contains no. of empty boxes and next line contains size of boxes with space. 
The next line contains size of given product. The output shows the best fit box size and -1 otherwise.

For example, Input: 6
		          2 7 9 11 13 16
	                       12
            Output: 13 
            
Simple binary search

 */

package arrays.binarySearch;

/**
 * Created by poorvank on 6/22/15.
 */
public class BestFitBox {

    public static void main(String[] args) {

        int[] boxes = new int[]{2, 7, 9, 11, 13, 16};
        int n = 6;
        int box = 7;

        System.out.println(aptBox(boxes, 0, n - 1, box, n));


    }

    private static int aptBox(int[] arr, int low, int high, int box, int n) {

        if (box <= arr[low]) {
            return arr[low];
        }

        if (box > arr[high]) {
            return -1;
        }

        int mid = (low + high) / 2;

        if (box == arr[mid]) {
            return arr[mid];
        } else if (arr[mid] < box) {

            if (mid + 1 <= high && box <= arr[mid + 1]) {
                return arr[mid + 1];
            } else {
                return aptBox(arr, mid + 1, high, box, n);
            }

        } else {

            if (mid - 1 >= low && box > arr[mid - 1]) {
                return arr[mid];
            } else {
                return aptBox(arr, low, mid - 1, box, n);
            }

        }

    }

}
