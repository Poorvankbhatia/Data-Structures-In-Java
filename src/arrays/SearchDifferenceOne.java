/*

Search an element in an array where difference between adjacent elements is 1
Given an array where difference between adjacent elements is 1, 
write an algorithm to search for an element in the array and return the
position of the element (return the first occurrence).

Examples:

Let element to be searched be x

Input: arr[] = {8, 7, 6, 7, 6, 5, 4, 3, 2, 3, 4, 3} 	
       x = 3
Output: Element 3 found at index 7

Input: arr[] =  {1, 2, 3, 4, 5, 4}
       x = 5
Output: Element 5 found at index 4

 */
package arrays;

/**
 * Created by poorvank on 8/1/15.
 */
public class SearchDifferenceOne {

    public static void main(String[] args) {

        int[] arr = new int[]{8, 7, 6, 7, 6, 5, 4, 3, 2, 3, 4, 3};
        int x = 3, n = arr.length;

        int i = 0;
        while (i < n) {

            if (arr[i] == x) {
                System.out.print("Position of " + x + " is = " + i);
                break;
            }

            i = i + Math.abs(arr[i] - x);

        }
    }

}

/*

The idea is to start comparing from the leftmost element and find the difference between 
current array element and x. Let this difference be ‘diff’. From the given property of array, we always know that x 
must be at-least ‘diff’ away, so instead of searching one by one, we jump ‘diff’. 

 */
