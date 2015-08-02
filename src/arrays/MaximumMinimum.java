/*
 Given an array of integers, find the maximum and minimum of the array.
 */

package arrays;

/**
 * Created by poorvank on 5/15/15.
 */

class Result {

    int max;
    int min;

    public Result(int max, int min) {

        this.max = max;
        this.min = min;

    }

}

public class MaximumMinimum {

    public static void main(String[] args) {

        int[] array1 = new int[]{4, 3, 5, 1, 2, 6, 9, 2, 10, 11};
        int[] array2 = new int[]{4, 3, 5, 1, 2, 6, 9, 2, 10};

        method1(array1);
        method1(array2);
        
        Result result = divideConquerApproach(array1, 0, array1.length - 1);
        System.out.println("max - " + result.max + " min - " + result.min);

    }

    private static void method1(int[] array) {

        int max = array[0];
        int min = array[0];

        int length = (array.length % 2 == 0) ? array.length : array.length - 1;

        int i = 0;

        while (i < length) {
            int num1 = array[i];
            int num2 = array[i + 1];

            if (num1 > num2) {

                if (num1 > max) {
                    max = num1;
                }
                if (num2 < min) {
                    min = num2;
                }

            } else {
                if (num2 > max) {
                    max = num2;
                }
                if (num1 < min) {
                    min = num1;
                }
            }
            i = i + 2;
        }


        if (array.length % 2 != 0) {
            int last = array[array.length - 1];
            if (last < min) {
                min = last;
            } else if (last > max) {
                max = last;
            }
        }

        System.out.println("Maximum element is - " + max);
        System.out.println("Minimum element is - " + min);

    }
    
    
    /*
    
      Divide and conquer method
In this approach we are dividing the list in two parts, each part is recursively providing the
min and max of that part and then two min max are compared to decide the ultimate min and max.
Recursively when the array is reduced to only a single element then the element itself become min and max.
    
     */

    private static Result divideConquerApproach(int[] array, int start, int last) {

        if (start > last) {
            return null;
        } else if (start == last) {
            return new Result(array[start], array[start]);
        } else {
            Result left = divideConquerApproach(array, start, (start + last) / 2);
            Result right = divideConquerApproach(array, (start + last) / 2 + 1, last);

            if (left == null) {
                return right;
            }
            if (right == null) {
                return left;
            } else {
                return new Result(Math.max(left.max, right.max), Math.min(left.min, right.min));
            }
        }

    }

}

/*

 Brute force
We can keep two variables named max and min. 
We can iterate over the list and compare each number with the min and the max, 
if the number is greater than the max update max, if the number is less than the min, 
update the min. In this brute force solution the number of comparison is 2*n.


Better solution
If rather than comparing each number with max and min, 
we can first compare the numbers in pair with each other. 
Then compare the larger number with max and compare the smaller number with min. 
in this way the number of comparison for a pair of numbers are 3. So number of comparisons are 1.5 *n.

Further discussion
Now let's think what will happen if instead of two numbers we take three numbers and 
then first find the largest and smallest of them and then compare these two numbers with max and min. 
To find the largest and smallest among 3 numbers, we need 3 comparisons in worst case and 2 comparisons in best case. 
The average case is 2.5. So for 3 numbers we need total 5 comparisons in worst case and 4.5 in average case. 
So in worst case comparisons per number is 1.6 and average case 1.5. Similarly we can derive that the worst case comparison
is never less than 1.5. and best and average case is equal in case of taking numbers in pair.




 */