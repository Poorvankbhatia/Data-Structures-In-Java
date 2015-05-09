package Arrays;

/**
 * Created by poorvank on 4/16/15.
 */
public class MaximumDifference {

    public static void main(String[] args) {

        int[] array = new int[]{7, 9, 5, 6, 3, 2};

        int minElement = array[0];
        int maxDifference = array[1] - array[0];

        for (int i = 1; i < array.length; i++) {

            if (array[i] - minElement > maxDifference) {
                maxDifference = array[i + 1] - minElement;
            }
            if (minElement > array[i]) {
                minElement = array[i];
            }
        }

        System.out.println("Maximum difference - " + maxDifference);


    }

}


/*

In this method, instead of taking difference of the picked element with every other element,
we take the difference with the minimum element found so far. So we need to keep track of 2 things:
1) Maximum difference found so far (max_diff).
2) Minimum number visited so far (min_element).

 */