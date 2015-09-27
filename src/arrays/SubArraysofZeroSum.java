/*

you have an array which has a set of positive and negative numbers, print all the subarrays sum which is equal to 0.


 */

package arrays;

/**
 * Created by poorvank on 8/4/15.
 */
public class SubArraysOfZeroSum {

    public static void main(String[] args) {

        int[] array = new int[]{2, 1, -1, 0, 2, -1, -1};
        solution(array, array.length);

    }

    private static void solution(int[] array, int n) {

        int[] sumArray = new int[array.length];

        sumArray[0] = array[0];

        for (int i = 1; i < array.length; i++) {

            sumArray[i] = sumArray[i - 1] + array[i];

        }


        boolean zeroFlag = false;

        for (int i = 0; i < n; i++) {

            for (int j = i + 1; j < n; j++) {

                if (sumArray[i] == sumArray[j]) {
                    print(i + 1, j, array);
                }

                //For case like {2,1,-1,-1,-1}
                if (sumArray[j] == 0 && !zeroFlag) {
                    zeroFlag = true;
                    print(i, j, array);
                }

            }

        }

    }

    private static void print(int i, int j, int[] array) {

        for (int k = i; k <= j; k++) {

            System.out.print(array[k] + " ");

        }

        System.out.println();

    }

}

/*

Let the array be arr
1.Create An array sum as below 
sum[i]=arr[0]+arr[1]+...arr[i]; 

2.Now see for duplicate values in sum array. 
for eg:- 
the array is 1,2,3,-2,-1,4,-5,1 
sum array 1,3,6, 4, 3,7,2,3 
so after finding duplicate values you will see the all the elements between 
their indices will sum upto zero and you can print it. 

 */