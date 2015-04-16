/*

Write a program to print all the LEADERS in the array. An element is leader if it is greater than all the elements to
its right side. 
And the rightmost element is always a leader. For example int the array {16, 17, 4, 3, 5, 2}, leaders are 17, 5 and 2.

 */

package Arrays;

/**
 * Created by poorvank on 4/16/15.
 */
public class leadersArray {
    
    public static void main(String[] args) {
        
        int[] leaderArray = new int[]{16, 17, 4, 3, 5, 2};
        
        int max = Integer.MIN_VALUE;
        
        System.out.println("Leaders are as follows ");
        
        for (int i=leaderArray.length-1;i>=0;i--) {
            
            if(max < leaderArray[i]) {
                max = leaderArray[i];
                System.out.print(max + " ");
            }
            
        }
        
    }
}


/*

Method 2 (Scan from right)
Scan all the elements from right to left in array and keep track of maximum till now. 
When maximum changes it’s value, print it.

O(n)

 */