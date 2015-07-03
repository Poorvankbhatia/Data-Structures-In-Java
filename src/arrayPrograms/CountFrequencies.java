/*

Count frequencies of all elements in array in O(1) extra space and O(n) time
Given an unsorted array of n integers which can contain integers from 1 to n. 
Some elements can be repeated multiple times and some other elements can be absent from the array.
Count frequency of all elements that are present and print the missing elements.

Examples:

Input: arr[] = {2, 3, 3, 2, 5}
Output: Below are frequencies of all elements
        1 -> 0
        2 -> 2
        3 -> 2
        4 -> 0
        5 -> 1

Input: arr[] = {4, 4, 4, 4}
Output: Below are frequencies of all elements
        1 -> 0
        2 -> 0
        3 -> 0
        4 -> 4

 */

package arrayPrograms;

/**
 * Created by poorvank on 6/24/15.
 */
public class CountFrequencies {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 3, 5, 7, 9, 1, 3, 5, 7, 9, 1};
        printFrequencies(arr, arr.length);

    }

    private static void printFrequencies(int[] arr, int n) {

        int i = 0;

        while (i < n) {

            if (arr[i] <= 0) {
                i++;
                continue;
            }

            int elementIndex = arr[i] - 1;

            if (arr[elementIndex] > 0) {

                arr[i] = arr[elementIndex];


                // After storing arr[elementIndex], change it
                // to store initial count of 'arr[i]'
                arr[elementIndex] = -1;

            } else {

                arr[elementIndex]--;

                // And initialize arr[i] as 0 means the element
                // 'i+1' is not seen so far
                arr[i] = 0;
                i++;

            }


        }

        System.out.println("The frequencies are  : ");

        for (i = 0; i < n; i++) {

            System.out.println(i + 1 + " -> " + Math.abs(arr[i]));

        }


    }

}

/*

his approach modifies the given array to store counts. 
The idea is to traverse the given array, use elements as index and store their counts at the index. 
For example, when we see element 7, we go to index 6 and store the count. 
There are few problems to handle, one is the counts can get mixed with the elements, 
this is handled by storing the counts as negative. Other problem is loosing the element which is replaced by count, 
this is handled by first storing the element to be replaced at current index.

Algorithm:
1) Initialize i as 0
2) Do following while i < n

   // If this element is already processed,
   // then nothing to do
   a) If arr[i] <= 0
        i++;
        continue the loop from beginning

    // Find index corresponding to this element
    // For example, index for 5 is 4
    b)  elementIndex = arr[i]-1;
    
    // If the elementIndex has an element that is not
    // processed yet, then first store that element
    // to arr[i] so that we don't loose anything.
    c) if (arr[elementIndex] > 0)
         (i) arr[i] = arr[elementIndex];

         // After storing arr[elementIndex], change it
         // to store initial count of 'arr[i]'
         (ii) arr[elementIndex] = -1;

     d) else       
            // If this is NOT first occurrence of arr[i],
            // then increment its count.
          (i) arr[elementIndex]--;

            // And initialize arr[i] as 0 means the element
            // 'i+1' is not seen so far
          (ii) arr[i] = 0;
          (iii) i++;

3) Now -arr[i] stores count of i+1.


How does above program work?
Let us take below example to see step by step processing of
above program:
arr[] = {2, 3, 3, 2, 5}

i = 0, arr[i] = 2, arr[] = {2, 3, 3, 2, 5}
Since arr[i] > 0, find elementIndex.
elementIndex = arr[i] – 1 = 2 – 1 = 1,
arr[elementIndex] or arr[1] is 3
Since arr[elementIndex] is postive,
arr[i] = arr[elementIndex] = 3
arr[elementIndex] = -1 // 2 is seen 1 times so far
i is not changed.

i = 0, arr[i] = 3, arr[] = {3, -1, 3, 2, 5}
Since arr[i] > 0, find elementIndex.
elementIndex = arr[i] – 1 = 3 – 1 = 2
arr[elementIndex] or arr[2] is 3
Since arr[elementIndex] is postive
arr[i] = arr[elementIndex] = 3
arr[elementIndex] = -1 // 3 is seen 1 times so far
i is not changed.

i = 0, arr[i] = 3, arr[] = {3, -1, -1, 2, 5}
Since arr[i] > 0, find elementIndex.
elementIndex = arr[i] – 1 = 3 – 1 = 2
arr[elementIndex] or arr[2] is -1
Since arr[elementIndex] is negative
arr[elementIndex] = arr[elementIndex] – 1
= -2 // 3 is seen 2 times so far
arr[i] = 0 // 1 is not seen so far
i is incremented

i = 1, arr[i] = -1, arr[] = {0, -1, -2, 2, 5}
Since arr[i] is negative, increment i

i = 2, arr[i] = -2, arr[] = {0, -1, -2, 2, 5}
Since arr[i] is negative, increment i

i = 3, arr[i] = 2, arr[] = {0, -1, -2, 2, 5}
Since arr[i] > 0, we find elementIndex.
elementIndex = arr[i] – 1 = 2 – 1 = 1
arr[elementIndex] or arr[1] is -1
Since arr[elementIndex] is negative
arr[elementIndex] = arr[elementIndex] – 1
= -2 // 2 is seen 2 times so far
arr[i] = 0 // 4 is not seen so far
i is incremented

i = 4, arr[i] = 5, arr[] = {0, -2, -2, 0, 5}
Since arr[i] > 0, we find elementIndex.
elementIndex = arr[i] – 1 = 5 – 1 = 4
arr[elementIndex] or arr[4] is 5
Since arr[elementIndex] is postive
arr[i] = arr[elementIndex] = 4
arr[elementIndex] = -1 // 5 is seen 1 times so far
i is not changed.

i = 1, arr[i] = -1, arr[] = {0, -2, -2, 0, -1}
Since arr[i] is negative, increment i

 */