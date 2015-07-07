package arrays;

import java.util.Arrays;

/**
 * Created by poorvank on 6/5/15.
 */
public class PythagoreanTriplet {

    public static void main(String[] args) {

        int[] array = new int[]{3, 1, 4, 6, 5};

        method1(array);

    }


    //Method uses sorting so index is not maintained
    private static void method1(int[] array) {

        Arrays.sort(array);

        int n = array.length;
        int[] squareArray = new int[n];

        for (int i = 0; i < squareArray.length; i++) {
            squareArray[i] = array[i] * array[i];
        }

        for (int i = n - 1; i >= 2; i--) {

            int left = 0;
            int right = i - 1;

            while (left < right) {
                if (squareArray[left] + squareArray[right] == squareArray[i]) {
                    System.out.println(array[left] + " " + array[right] + " " + array[i]);
                    return;
                }

                if (squareArray[left] + squareArray[right] < squareArray[i])
                    left++;
                else
                    right--;
            }

        }

        System.out.println("No triplet found");

    }


}


/*

We can solve this in O(n2) time by sorting the array first.

1) Do square of every element in input array. This step takes O(n) time.

2) Sort the squared array in increasing order. This step takes O(nLogn) time.

3) To find a triplet (a, b, c) such that a = b + c, do following.

Fix ‘a’ as last element of sorted array.
Now search for pair (b, c) in subarray between first element and ‘a’. A pair (b, c) 
with given sum can be found in O(n)
If no pair found for current ‘a’, then move ‘a’ one position back and repeat step 3.b.

 */
