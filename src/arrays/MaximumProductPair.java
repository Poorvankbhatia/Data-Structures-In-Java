/*

Find a pair with maximum product in array of Integers
Given an array with both +ive and -ive integers, return a pair with highest product.

Examples:

Input: arr[] = {1, 4, 3, 6, 7, 0}
Output: {6,7}

Input: arr[] = {-1, -3, -4, 2, 0, -5}
Output: {-4,-5}


 */

package arrays;

/**
 * Created by poorvank on 7/10/15.
 */
public class MaximumProductPair {

    public static void main(String[] args) {

        int[] arr = new int[]{-1, -3, -4, 2, 0, -5};

        int pos1 = Integer.MIN_VALUE, pos2 = Integer.MIN_VALUE;
        int neg1 = Integer.MIN_VALUE, neg2 = Integer.MIN_VALUE;


        for (int anArr : arr) {

            if (anArr > pos1) {

                pos2 = pos1;
                pos1 = anArr;

            } else if (anArr > pos2) {
                pos2 = anArr;
            }

            if (anArr < 0 && Math.abs(anArr) > Math.abs(neg1)) {

                neg2 = neg1;
                neg1 = anArr;

            } else if (anArr < 0 && Math.abs(anArr) > Math.abs(neg2)) {

                neg2 = anArr;

            }


        }

        System.out.println((pos1 * pos2) > (neg1 * neg2) ? pos1 + "," + pos2 : neg1 + "," + neg2);

    }

}


/*

An Efficient Solution can solve the above problem in single traversal of input array. 
The idea is to traverse the input array and keep track of following four values.
a) Maximum positive value
b) Second maximum positive value
c) Maximum negative value i.e., a negative value with maximum absolute value
d) Second maximum negative value.
At the end of the loop, compare the products of first two and last two and print the maximum of two products.

 */